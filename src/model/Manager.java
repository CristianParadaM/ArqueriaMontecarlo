package model;

import java.util.ArrayList;

import controller.Controller;

public class Manager {

	public static final int AMOUNT_LUCKY_SHOT = 10;
	public static final int AMOUNT_MAX_FORTUNE = 3;
	private static final int QUANTY_GAMES = 2000;
	private Player[] players;
	private ArcheryGame[] games;
	private int[] originalResistance;
	private int k;
	private int c;
	private int kE;
	private int cE;

	public Manager(int k, int c) {
		this.k = k;
		this.c = c;
		this.kE = 2;
		this.cE = 1;
		this.originalResistance = genearteResistance();
		this.players = generatePLayers();
		this.games = createGames();
	}

	/**
	 * Metodo que genera los juegos de arqueria
	 * 
	 * @return juegos como lista
	 */
	private ArcheryGame[] createGames() {
		ArcheryGame[] games = new ArcheryGame[QUANTY_GAMES];
		games[0] = new ArcheryGame(players, k % 2 == 0 ? k : k + 1, c % 2 != 0 ? c : c + 1);
		for (int i = 1; i < games.length; i++) {
			Controller.getInstance().setProgress(i);
			returnOriginalStatistics();
			int k = Integer.parseInt((games[i - 1].getNextK() + "").substring(2, 5));
			int c = Integer.parseInt((games[i - 1].getNextC() + "").substring(2, 5));
			games[i] = new ArcheryGame(players, k % 2 == 0 ? k : k + 1, c % 2 != 0 ? c : c + 1);
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
	 * Metodo para generar las resistencias de los jugadores con una distribucion
	 * normal
	 * 
	 * @return resistencia de los jugadores
	 */
	private int[] genearteResistance() {
		int k = this.k / 100, c = this.c / 100;
		k = k == 0?k+(kE+=2):k;
		c= c == 0?c+(cE++):c;
		double[] ni = new DistribucionNormal(35, 10, GenerateRi.getInstance().generateRiDouble(k,c,9)).generateNi();
		int[] resistance = new int[10];
		int count = 0;
		for (int i = 0; count < 10 && i < ni.length; i++) {
			if (ni[i] > 25 && ni[i] < 35) {
				resistance[count++] = (int) ni[i];
			}
		}
		return resistance;
	}

	/**
	 * Metodo que genera los jugadores del juego al azar
	 * 
	 * @return jugadores
	 */
	private Player[] generatePLayers() {
		ArrayList<Double> ri = GenerateRi.getInstance().generateRi(k, c, 7);
		Player[] players = new Player[10];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player(ri.get(i) < 0.5 ? Gender.FEMALE : Gender.MALE, originalResistance[i]);
		}
		return players;
	}

	public ArcheryGame[] getGames() {
		return games;
	}

	public double[] getLuck(int game, int round) {
		return games[game].getRound(round).getFortune();
	}

	public int getPointsRoundTeamFirst(int game, int round) {
		return games[game].getRound(round).scoreFirstTeam();
	}

	public int getPointsRoundTeamSecond(int game, int round) {
		return games[game].getRound(round).scoreSecondTeam();
	}

	public int getBestPlayer(int game, int round) {
		return games[game].getRound(round).bestPlayer() + 1;
	}

	public ArrayList<Object[]> getTableRound(int game, int round) {
		return games[game].getRound(round).getTable();
	}

	public double getTotalPointsOfPlayer(int playerPos) {
		double aux = 0;
		for (int i = 0; i < games.length; i++) {
			for (int j = 0; j < games[i].roundsSize(); j++) {
				aux += games[i].getRound(j).scoreForPlayer(playerPos);
			}
		}
		return aux;
	}

	public ArrayList<Object[]> getInitialStatics() {
		ArrayList<Object[]> aux = new ArrayList<Object[]>();
		for (int i = 0; i < players.length; i++) {
			aux.add(new Object[] { "P" + (i + 1), originalResistance[i], players[i].getGender().toString() });
		}
		return aux;
	}

	public ArrayList<Object[]> getResults() {
		ArrayList<Object[]> aux = new ArrayList<Object[]>();
		for (int i = 0; i < games.length; i++) {
			aux.add(new Object[] { (i + 1), "P" + (games[i].getLuckyPlayerGame() + 1),
					"P" + (games[i].getXPplayer() + 1), games[i].genreMaxVictories() });
		}
		return aux;
	}

}
