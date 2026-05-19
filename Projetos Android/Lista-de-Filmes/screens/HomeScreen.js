import React, { useEffect, useState } from 'react';

import {
  View,
  Text,
  FlatList,
  TouchableOpacity,
  ActivityIndicator,
  StyleSheet,
  Image,
} from 'react-native';

import { getMovies } from '../services/api';

export default function HomeScreen({ navigation }) {
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function loadMovies() {
      const data = await getMovies();
      setMovies(data);
      setLoading(false);
    }

    loadMovies();
  }, []);

  if (loading) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" color="#000" />
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <FlatList
        data={movies}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <TouchableOpacity
            style={styles.card}
            onPress={() =>
              navigation.navigate('Details', { movie: item })
            }
          >
            <Image
              source={{ uri: item.image?.medium }}
              style={styles.image}
            />

            <View style={styles.infoContainer}>
              <Text style={styles.title}>
                {item.name}
              </Text>

              <Text style={styles.genre}>
                {item.genres.join(', ')}
              </Text>
            </View>
          </TouchableOpacity>
        )}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    padding: 10,
  },

  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },

  card: {
    flexDirection: 'row',
    backgroundColor: '#f2f2f2',
    borderRadius: 10,
    marginBottom: 10,
    overflow: 'hidden',
  },

  image: {
    width: 100,
    height: 140,
  },

  infoContainer: {
    flex: 1,
    padding: 10,
    justifyContent: 'center',
  },

  title: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 5,
  },

  genre: {
    fontSize: 14,
    color: '#555',
  },
});