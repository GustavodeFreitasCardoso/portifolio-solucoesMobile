import { View, Text, Image, ScrollView, StyleSheet } from "react-native";
import pilots from "./pilots";

export default function App() {
  return (
    <ScrollView style={styles.container}>
      {pilots.map((pilot) => (
        <View key={pilot.id} style={styles.card}>
          
          <Image source={pilot.image} style={styles.image} />

          <Text style={styles.name}>{pilot.name}</Text>

          <Text style={styles.description}>{pilot.description}</Text>

          <Text style={styles.info}>Equipe: {pilot.team}</Text>
          <Text style={styles.info}>País: {pilot.country}</Text>

          <Text style={styles.stars}>
            {"⭐".repeat(pilot.stars)}
          </Text>

        </View>
      ))}
    </ScrollView>
  );
}
  const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#0f172a", // azul escuro bonito
  },

  content: {
    paddingTop: 40, // resolve o problema de ficar colado em cima
    paddingHorizontal: 15,
    paddingBottom: 20,
  },

  title: {
    fontSize: 26,
    color: "#fff",
    fontWeight: "bold",
    marginBottom: 20,
    textAlign: "center",
  },

  card: {
    backgroundColor: "#1e293b",
    borderRadius: 20,
    padding: 15,
    marginBottom: 20,
    alignItems: "center",
    shadowColor: "#000",
    shadowOpacity: 0.3,
    shadowRadius: 5,
    elevation: 5,
  },

  image: {
    width: 220,
    height: 140,
    borderRadius: 12,
    marginBottom: 10,
  },

  name: {
    fontSize: 20,
    color: "#fff",
    fontWeight: "bold",
    marginTop: 5,
  },

  description: {
    fontSize: 14,
    color: "#cbd5f5",
    textAlign: "center",
    marginVertical: 8,
  },

  infoBox: {
    marginTop: 5,
    alignItems: "center",
  },

  info: {
    fontSize: 14,
    color: "#94a3b8",
  },

  stars: {
    fontSize: 18,
    marginTop: 8,
  },
});