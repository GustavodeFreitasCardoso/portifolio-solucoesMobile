import hamilton from "./assets/hamilton.jpg";
import verstappen from "./assets/verstappen.jpg";
import leclerc from "./assets/leclerc.png";

const pilots = [
  {
    id: 1,
    name: "Lewis Hamilton",
    image: hamilton,
    description: "Heptacampeão mundial de Fórmula 1",
    team: "Mercedes",
    country: "Reino Unido",
    stars: 5
  },
  {
    id: 2,
    name: "Max Verstappen",
    image: verstappen,
    description: "Campeão mundial dominante da atualidade",
    team: "Red Bull Racing",
    country: "Holanda",
    stars: 5
  },
  {
    id: 3,
    name: "Charles Leclerc",
    image: leclerc,
    description: "Grande talento da Ferrari",
    team: "Ferrari",
    country: "Mônaco",
    stars: 4
  }
];

export default pilots;