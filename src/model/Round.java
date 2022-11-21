package model;

import java.util.ArrayList;
import java.util.List;

public class Round {

	private Player[] players;
	private List<Double> archerys;
	private double[] fortune;
	private ArrayList<Object[]> table;
	private int positionSuperLuckyShot;
	private int count;

	public Round(Player[] players, int position, int count, List<Double> archerys) {
		this.archerys = archerys;
		this.players = players;
		this.positionSuperLuckyShot = position;
		this.count = count;
		this.fortune = new double[this.players.length];
		generateFortune();
		this.table = generateResultTable();
	}

	/**
	 * Metodo que genera la fortuna de los jugadores para esta ronda
	 */
	private void generateFortune() {
		int aux = Integer.parseInt(archerys.get(0).toString().charAt(2) + "");
		int aux2 = Integer.parseInt(archerys.get(1).toString().charAt(2) + "");
		this.fortune = new DistribucionUniforme(1, 3, GenerateRi.getInstance()
				.generateRiDouble(aux % 2 == 0 ? aux : aux + 1, aux2 % 2 != 0 ? aux2 : aux2 + 1, 9))
						.generatePseudorandomNumbers();
		for (int i = 0; i < players.length; i++) {
			players[i].setFortune(fortune[i]);
		}
	}

	/**
	 * Metodo que buscar el jugador con mas suerte
	 * 
	 * @return posicion del jugador con mas suerte
	 */
	public int getLuckyPlayer() {
		int position = 0;
		for (int i = 0; i < players.length; i++) {
			position = fortune[position] < fortune[i] ? i : position;
		}
		return position;
	}

	/**
	 * Metodo que genera la tabla con los resultados de esta ronda
	 * 
	 * @return tabla con resulatados de la ronda
	 */
	public ArrayList<Object[]> generateResultTable() {
		ArrayList<Object[]> table = new ArrayList<Object[]>(calculateMaxShots());
		int count = 0;
		for (int i = 0; i < calculateMaxShots(); i++) {
			Object[] aux = new Object[10];
			for (int j = 0; j < aux.length; j++) {
				if (calculateShots(j) > i) {
					aux[j] = calculateScore(archerys.get(count++), j);
				} else {
					aux[j] = -1;
				}
			}
			table.add(aux);
		}
		return table;
	}

	/**
	 * Metodo para calcular el puntaje de un tiro
	 * 
	 * @param double1  numero pseudoaleatorio que representa el tiro
	 * @param position posicion del jugador que esta tirando
	 * @return puntaje del tiro
	 */
	private int calculateScore(double double1, int position) {
		if (players[position].getGender() == Gender.FEMALE) {
			if (double1 < Gender.probFemaleMontecatlo[0]) {
				return Score.CENTER.getScore();
			} else if (double1 < Gender.probFemaleMontecatlo[1]) {
				return Score.INTERMEDIATE.getScore();
			} else if (double1 < Gender.probFemaleMontecatlo[2]) {
				return Score.EXTERIOR.getScore();
			} else {
				return Score.ERROR.getScore();
			}
		} else {
			if (double1 < Gender.probMaleMontecatlo[0]) {
				return Score.CENTER.getScore();
			} else if (double1 < Gender.probMaleMontecatlo[1]) {
				return Score.INTERMEDIATE.getScore();
			} else if (double1 < Gender.probMaleMontecatlo[2]) {
				return Score.EXTERIOR.getScore();
			} else {
				return Score.ERROR.getScore();
			}
		}
	}

	/**
	 * Metodo que calcula la cantidad de tiros de un jugador
	 * 
	 * @param position posicion del jugador
	 * @return cantidad de tiros
	 */
	private int calculateShots(int position) {
		if (position == getLuckyPlayer()) {
			if (count == 2 && this.positionSuperLuckyShot == position) {
				return (players[position].getResistance() / 5) + 2;
			} else {
				return (players[position].getResistance() / 5) + 1;
			}
		}
		return players[position].getResistance() / 5;
	}

	/**
	 * Metodo que calcula la cantidad maxima de tiros de un jugador en una ronda
	 * 
	 * @return cantidad maxima de tiros
	 */
	private int calculateMaxShots() {
		int max = 0, aux = 0;
		for (int i = 0; i < players.length; i++) {
			aux = calculateShots(i);
			max = aux > max ? aux : max;
		}
		return max;
	}

	/**
	 * Metodo que retorna el mejor jugadro de la ronda (mas puntos)
	 * 
	 * @return posicion del jugador
	 */
	public int bestPlayer() {
		ArrayList<Integer> best = bestPlayers();
		if (best.size() > 1) {
			int aux = Integer.parseInt(archerys.get(archerys.size() - 1).toString().charAt(2) + "");
			int aux2 = Integer.parseInt(archerys.get(archerys.size() - 2).toString().charAt(2) + "");
			return bestPlayer(best, GenerateRi.getInstance().generateRi(aux % 2 == 0 ? aux : aux + 1,
					aux2 % 2 != 0 ? aux2 : aux2 + 1, 7));
		} else {
			return best.get(0);
		}
	}

	/**
	 * Metodo que retorna el mejor jugador despues del desempate
	 * 
	 * @param best     lista de mejores jugadores
	 * @param archerys tiros
	 * @return mejor jugador
	 */
	private int bestPlayer(ArrayList<Integer> best, ArrayList<Double> archerys) {
		int count = 0;
		while (best.size() > 1) {
			List<Double> aux = archerys.subList(count, count + best.size());
			count += best.size();
			for (int i = 0; i < aux.size() - 1; i++) {
				if (calculateScore(aux.get(i), best.get(i)) > calculateScore(aux.get(i + 1), best.get(i + 1))) {
					aux.remove(i + 1);
					best.remove(i + 1);
				} else {
					aux.remove(i);
					best.remove(i);
					i--;
				}
			}
		}
		return best.get(0);
	}

	/**
	 * Metodo que busca a los jugadores con mayor puntaje
	 * 
	 * @return lista de las posiciones de los jugadores
	 */
	private ArrayList<Integer> bestPlayers() {
		ArrayList<Integer> best = new ArrayList<Integer>();
		int maxScore = scoreMax();
		for (int i = 0; i < players.length; i++) {
			if (scoreForPlayer(i) == maxScore) {
				best.add(i);
			}
		}
		return best;
	}

	/**
	 * Metodo que calcula el puntaje maximo
	 * 
	 * @return puntaje maximo
	 */
	private int scoreMax() {
		int score = 0;
		int aux = 0;
		for (int i = 0; i < players.length; i++) {
			aux = scoreForPlayer(i);
			score = aux > score ? aux : score;
		}
		return score;
	}

	/**
	 * Metodo que calcula el puntaje total obtenido por un jugador en la ronda
	 * 
	 * @param position posicion edl jugador
	 * @return puntaje del jugador
	 */
	public int scoreForPlayer(int position) {
		int count = 0, aux = 0;
		if (position == getLuckyPlayer()) {
			aux = count == 2 && this.positionSuperLuckyShot == position ? 2 : 1;
		}
		for (int i = 0; i < table.size() - aux; i++) {
			count += (int) table.get(i)[position];
		}
		return count;
	}

	/**
	 * Metodo que calcula el puntaje del primer equipo
	 * 
	 * @return puntaje
	 */
	public int scoreFirstTeam() {
		return scoreTeam(0);
	}

	/**
	 * Metodo que calcula el puntaje del segundo equipo
	 * 
	 * @return puntaje
	 */
	public int scoreSecondTeam() {
		return scoreTeam(5);
	}

	/**
	 * Metodo que calcula el puntaje de un equipo
	 * 
	 * @param position posicion del primer jugador del equipo
	 * @return puntaje
	 */
	private int scoreTeam(int position) {
		int score = 0;
		for (int i = 0; i < table.size(); i++) {
			score = (int) table.get(i)[position] != -1 ? score + (int) table.get(i)[position] : score;
			score = (int) table.get(i)[position+1] != -1 ? score + (int) table.get(i)[position+1] : score;
			score = (int) table.get(i)[position+2] != -1 ? score + (int) table.get(i)[position+2] : score;
			score = (int) table.get(i)[position+3] != -1 ? score + (int) table.get(i)[position+3] : score;
			score = (int) table.get(i)[position+4] != -1 ? score + (int) table.get(i)[position+4] : score;
		}
		return score;
	}

	public ArrayList<Object[]> getTable() {
		return this.table;
	}

	public double[] getFortune() {
		return fortune;
	}
}
