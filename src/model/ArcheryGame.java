package model;

import java.util.ArrayList;
import java.util.List;

public class ArcheryGame {
	private Player[] players;
	private Round[] rounds;
	private int k;
	private int c;
	
	public ArcheryGame(Player[] players, int k, int c) {
		this.k = k;
		this.c = c;
		this.players = players;
		this.rounds = generateRounds();
	}
	
	/**
	 * Metodo que genera la simulacion 
	 * @return
	 */
	private Round[] generateRounds() {
		Round[] aux = new Round[10];
		int count = 0;
		int position = -1;
		aux[0] = new Round(players, position, count, k, c);
		for (int i = 1; i < 10; i++) {
			int k = Integer.parseInt((aux[i-1].getArcherys().get(0)+"").substring(2, 4));
			int c = Integer.parseInt((aux[i-1].getArcherys().get(56)+"").substring(2, 4));
			aux[i] = new Round(players, position, count, k, c);
			count = position != aux[i].getLuckyPlayer()?1:count+1==3?0:count+1;
			position = aux[i].getLuckyPlayer();
			Player bestPlayer = players[aux[i].bestPlayer()];
			if (bestPlayer.getExperience() < 9) {
				bestPlayer.setExperience(bestPlayer.getExperience()+3);
				if (bestPlayer.getExperience() == 9) {
					bestPlayer.setExperience(0);
					bestPlayer.setExperienceRounds(2);
				}
			}
			subtractTiredness();
		}
		return aux;
	}
	
	/**
	 * Metodo que resta el cansancio de una ronda a la resistencia de los jugadores
	 */
	private void subtractTiredness() {
		for (int i = 0; i < players.length; i++) {
			if (players[i].getExperienceRounds() > 0) {
				players[i].setResistance(players[i].getResistance()-1);
			}else {
				players[i].setResistance(players[i].getResistance()-2);
			}
		}
	}
	
	/**
	 * Metodo que retorna al equipo ganador de una ronda
	 * @param round numero de ronda (1-10)
	 * @return equipo ganador
	 */
	public int getTeamWonForRound(int round) {
		int scoreFistTeam = this.rounds[round].scoreFirstTeam(), scoreSecondTeam = this.rounds[round].scoreSecondTeam();
		if ( scoreFistTeam == scoreSecondTeam) {
			return 0;
		}
		return scoreFistTeam > scoreSecondTeam?1:2;
	}
	
	/**
	 * Metodo que calcula el puntaje del primer equipo
	 * @param round numero de ronda (0-9)
	 * @return puntaje
	 */
	public int scoreFirstTeam(int round) {
		return this.rounds[round].scoreFirstTeam();
	}
	
	/**
	 * Metodo que calcula el puntaje del segundo equipo
	 * @param round numero de ronda (0-9)
	 * @return puntaje
	 */
	public int scoreSecondTeam(int round) {
		return this.rounds[round].scoreSecondTeam();
	}
	
	/**
	 * Metodo que retorna a los mejores jugadores del juego
	 * @return lista de mejores jugadores
	 */
	public List<Player> getBestPlayer() {
		return moreTimesWon(bestPlayerForRound());
	}
	
	/**
	 * Metodo que obtiene a los mejores jugadores de cada ronda
	 * @return mejor jugador por ronda
	 */
	public int[] bestPlayerForRound() {
		int[] best = new int[rounds.length];
		for (int i = 0; i < best.length; i++) {
			best[i] = rounds[i].bestPlayer();
		}
		return best;
	}
	
	/**
	 * Metodo para obtener a los jugadores que ganaron mas rondas
	 * @param best mejor jugador de cada ronda (posicion)
	 * @return lista de mejores jugadores
	 */
	private List<Player> moreTimesWon(int[] best) {
		List<Player> bests = new ArrayList<Player>();
		for (int i = 0; i < best.length; i++) {
			if (gamesWon(best[i], best) == gamesMoreWon(best)) {
				if (!bests.contains(players[best[i]])) {
					bests.add(players[best[i]]);
				}
			}
		}
		return bests;
	}
	
	/**
	 * Metodo que calcula la cantidad maxima de rondas ganadas por un jugador
	 * @param best mejor jugador de cada ronda (posicion)
	 * @return cantidad de veces
	 */
	private int gamesMoreWon(int[] best) {
		int aux = 0;
		for (int i = 1; i < best.length; i++) {
			aux = gamesWon(best[i], best) > gamesWon(aux, best)?best[i]:aux;
		}
		return aux;
	}

	/**
	 * Metodo que calcula las veces que un jugador fue mejor en las rondas
	 * @param position posicion del jugador
	 * @param best mejor jugador de cada ronda (posicion)
	 * @return numero de veces en las que fue el mejor jugador
	 */
	private int gamesWon(int position, int[] best) {
		int count = 0;
		for (int i = 0; i < best.length; i++) {
			if (position == best[i]) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Metodo que retorna al equipo ganador del juego
	 * @return equipo ganador
	 */
	public int teamWonGame() {
		int amountFirstTeam = amountTeamWonRound(1), amountSecondTeam = amountTeamWonRound(2);
		if (amountFirstTeam == amountSecondTeam) {
			return 0;
		}
		return amountFirstTeam > amountSecondTeam?1:2;
	}
	
	/**
	 * Metodo que calcula la cantidad de rondas ganadas por un equipo
	 * @param numTeam numero del equipo
	 * @return cantidad de rondas ganadas
	 */
	private int amountTeamWonRound(int numTeam) {
		int[] aux = teamWonRound();
		int count = 0;
		for (int i = 0; i < aux.length; i++) {
			if (numTeam == aux[i]) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Metodo que retorna a los equipos ganadores de cada ronda
	 * @return equipo ganador por ronda
	 */
	private int[] teamWonRound() {
		int[] aux = new int[10];
		for (int i = 0; i < aux.length; i++) {
			aux[i] = getTeamWonForRound(i);
		}
		return aux;
	}
	
	/**
	 * Metodo que obtiene el puntaje acumulado de un equipo hasta cierta ronda
	 * @param round ronda
	 * @param team numero del equipo
	 * @return puntaje acumulado de dicho equipo
	 */
	public int cumulativeScore(int round, int team) {
		int count = 0;
		if (team == 1) {
			for (int i = 0; i < round; i++) {
				count += scoreFirstTeam(i);
			}
		}else {
			for (int i = 0; i < round; i++) {
				count += scoreSecondTeam(i);
			}
		}
		return count;
	}
	
	/**
	 * Metodo que retorna la tabla de resultados
	 * @param round numero de ronda
	 * @return tabla
	 */
	public ArrayList<Object[]> getTableForRound(int round){
		return this.rounds[round].getTable();
	}
	
	public double getNextK() {
		return this.rounds[9].getArcherys().get(20);
	}
	
	public double getNextC() {
		return this.rounds[9].getArcherys().get(40);
	}
	
}
