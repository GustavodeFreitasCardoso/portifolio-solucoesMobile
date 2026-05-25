import React, { useEffect, useState } from 'react';
import {
  View,
  Text,
  Button,
  FlatList,
  StyleSheet,
  Switch,
  Alert
} from 'react-native';

import AsyncStorage from '@react-native-async-storage/async-storage';
import * as Location from 'expo-location';
import * as SQLite from 'expo-sqlite';

const db = SQLite.openDatabaseSync('locations.db');

export default function App() {
  const [darkMode, setDarkMode] = useState(false);
  const [locations, setLocations] = useState([]);

  useEffect(() => {
    createTable();
    loadTheme();
    loadLocations();
  }, []);

  // =====================
  // SQLITE
  // =====================
  function createTable() {
    try {
      db.execSync(`
        CREATE TABLE IF NOT EXISTS locations (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          latitude REAL,
          longitude REAL
        );
      `);
    } catch (error) {
      console.log(error);
    }
  }

  function loadLocations() {
    try {
      const result = db.getAllSync(
        'SELECT * FROM locations ORDER BY id DESC'
      );
      setLocations(result);
    } catch (error) {
      console.log(error);
    }
  }

  function saveLocation(latitude, longitude) {
    try {
      db.runSync(
        'INSERT INTO locations (latitude, longitude) VALUES (?, ?)',
        [latitude, longitude]
      );

      loadLocations();
      Alert.alert('Sucesso', 'Localização salva!');
    } catch (error) {
      console.log(error);
    }
  }

  function deleteLocation(id) {
    try {
      db.runSync(
        'DELETE FROM locations WHERE id = ?',
        [id]
      );

      loadLocations();
      Alert.alert('Sucesso', 'Localização excluída!');
    } catch (error) {
      console.log(error);
    }
  }

  // =====================
  // DARK MODE
  // =====================
  async function loadTheme() {
    try {
      const saved = await AsyncStorage.getItem('darkMode');

      if (saved !== null) {
        setDarkMode(JSON.parse(saved));
      }
    } catch (error) {
      console.log(error);
    }
  }

  async function toggleTheme(value) {
    try {
      setDarkMode(value);

      await AsyncStorage.setItem(
        'darkMode',
        JSON.stringify(value)
      );
    } catch (error) {
      console.log(error);
    }
  }

  // =====================
  // LOCATION
  // =====================
  async function captureLocation() {
    try {
      const { status } =
        await Location.requestForegroundPermissionsAsync();

      if (status !== 'granted') {
        Alert.alert(
          'Permissão negada',
          'Permita acesso à localização'
        );
        return;
      }

      const location =
        await Location.getCurrentPositionAsync({
          accuracy: Location.Accuracy.High
        });

      const lat = location.coords.latitude;
      const long = location.coords.longitude;

      saveLocation(lat, long);

    } catch (error) {
      console.log(error);
      Alert.alert(
        'Erro',
        'Não foi possível capturar localização'
      );
    }
  }

  const stylesDynamic = darkMode ? dark : light;

  return (
    <View style={stylesDynamic.container}>
      <Text style={stylesDynamic.title}>
        My Location
      </Text>

      <View style={styles.switchContainer}>
        <Text style={stylesDynamic.text}>
          Dark Mode
        </Text>

        <Switch
          value={darkMode}
          onValueChange={toggleTheme}
        />
      </View>

      <Button
        title="Capturar Localização"
        onPress={captureLocation}
      />

      <FlatList
  style={{ marginTop: 20, width: '100%' }}
  contentContainerStyle={{
    alignItems: 'center',
    paddingBottom: 20
  }}
  data={locations}
  keyExtractor={(item) => item.id.toString()}
  renderItem={({ item }) => (
    <View style={stylesDynamic.card}>
      <Text style={stylesDynamic.text}>
        Latitude: {item.latitude}
      </Text>

      <Text style={stylesDynamic.text}>
        Longitude: {item.longitude}
      </Text>

      <View style={styles.buttonContainer}>
        <Button
          title="Excluir localização"
          color="#ff0000"
          onPress={() =>
            Alert.alert(
              'Confirmar exclusão',
              'Deseja apagar esta localização?',
              [
                {
                  text: 'Cancelar',
                  style: 'cancel'
                },
                {
                  text: 'Excluir',
                  onPress: () =>
                    deleteLocation(item.id)
                }
              ]
            )
          }
        />
      </View>
    </View>
  )}
/>
    </View>
  );
}

const styles = StyleSheet.create({
  switchContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 20,
    width: '100%',
    paddingHorizontal: 20
  },
  buttonContainer: {
  marginTop: 15,
  width: '100%'
  }
});

const light = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 60,
    alignItems: 'center',
    backgroundColor: '#fff'
  },
  title: {
    fontSize: 28,
    marginBottom: 20
  },
  text: {
    color: '#000'
  },
  card: {
    marginTop: 10,
    padding: 15,
    backgroundColor: '#ddd',
    width: 300,
    borderRadius: 10
  }
});

const dark = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 60,
    alignItems: 'center',
    backgroundColor: '#121212'
  },
  title: {
    fontSize: 28,
    marginBottom: 20,
    color: '#fff'
  },
  text: {
    color: '#fff'
  },
  card: {
    marginTop: 10,
    padding: 15,
    backgroundColor: '#333',
    width: 300,
    borderRadius: 10
  }
});