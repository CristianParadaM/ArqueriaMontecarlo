package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Manager {
	public static final int AMOUNT_LUCKY_SHOT = 10;
	public static final int AMOUNT_MAX_FORTUNE = 3;
	private Player[] players;
	private ArcheryGame[] games;
	private int[] originalResistance;
	private int k;
	private int c;
	
	public Manager(int k, int c) {
		this.originalResistance = genearteResistance();
		this.players = generatePLayers();
		this.k = k;
		this.c = c;
		this.games = createGames();
	}
	
	/**
	 * Metodo que genera los juegos de arqueria
	 * @return juegos como lista
	 */
	private ArcheryGame[] createGames() {
		ArcheryGame[] games = new ArcheryGame[20000];
		games[0] = new ArcheryGame(players, k, c);
		for (int i = 1; i < games.length; i++) {
			returnOriginalStatistics();
			int k = Integer.parseInt((games[i-1].getNextK()+"").substring(2, 4));
			int c = Integer.parseInt((games[i-1].getNextC()+"").substring(2, 4));
			games[i] = new ArcheryGame(players, k%2==0?k:k+1, c%2!=0?c:c+1);
		}
		return games;
	}
	
	/**
	 * Metodo para devolver las estadisticas de un jugador a las originales
	 */
	private void returnOriginalStatistics() {
		for (int i = 0; i < players.length; i++) {
			players[i].setResistance(originalResistance[i]);
			players[i].setExperience(0);
			players[i].setExperienceRounds(0);
		}
	}

	/**
	 * Metodo para generar las resistencias de los jugadores con una distribucion normal
	 * @return resistencia de los jugadores
	 */
	private int[] genearteResistance() {
		GenerateRi.getInstance().generateRi(k, c, 7);
		double[] ni = new DistribucionNormal(35, 10, GenerateRi.getInstance().getRi()).generateNi();
		int[] resistance = new int[10];
		int count = 0;
		for (int i = 0; i < ni.length || count == 10; i++) {
			if (ni[i] > 25 && ni[i] < 35) {
				resistance[count++] = (int) ni[i];
			}
		}
		return resistance;
	}
	
	/**
	 * Metodo que genera los jugadores del juego al azar
	 * @return jugadores
	 */
	private Player[] generatePLayers() {
		ArrayList<Double> ri = GenerateRi.getInstance().generateRi(k, c, 7);
		Player[] players = new Player[10];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player(ri.get(i) < 0.5?Gender.FEMALE:Gender.MALE,originalResistance[i]);
		}
		return players;
	}
	
	public static void main(String[] args) {
		Manager manager = new Manager(4, 3);
	}
	
}
