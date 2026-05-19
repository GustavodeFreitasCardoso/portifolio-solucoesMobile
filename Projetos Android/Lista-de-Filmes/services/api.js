const API_URL = 'https://api.tvmaze.com/shows';

export async function getMovies() {
  try {
    const response = await fetch(API_URL);
    const data = await response.json();

    return data.slice(0, 20);
  } catch (error) {
    console.log('Erro ao buscar filmes:', error);
    return [];
  }
}