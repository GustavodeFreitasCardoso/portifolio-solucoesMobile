import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  Image,
  ScrollView
} from 'react-native';

export default function DetailsScreen({ route }) {
  const { movie } = route.params;

  return (
    <ScrollView style={styles.container}>
      <Image
        source={{ uri: movie.image?.original }}
        style={styles.image}
      />

      <View style={styles.content}>
        <Text style={styles.title}>{movie.name}</Text>

        <Text style={styles.subtitle}>
          Gêneros: {movie.genres.join(', ')}
        </Text>

        <Text style={styles.subtitle}>
          Nota: {movie.rating.average || 'Sem avaliação'}
        </Text>

        <Text style={styles.description}>
          {movie.summary
            ?.replace(/<[^>]*>/g, '')}
        </Text>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },

  image: {
    width: '100%',
    height: 400,
  },

  content: {
    padding: 15,
  },

  title: {
    fontSize: 28,
    fontWeight: 'bold',
    marginBottom: 10,
  },

  subtitle: {
    fontSize: 16,
    marginBottom: 8,
    color: '#555',
  },

  description: {
    marginTop: 15,
    fontSize: 16,
    lineHeight: 24,
  },
});