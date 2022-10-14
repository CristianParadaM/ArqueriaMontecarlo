package model;

public class Manager {
	public static final int AMOUNT_LUCKY_SHOT = 10;
	public static final int AMOUNT_MAX_FORTUNE = 3;
	private Player[] players;
	private int[] amountShotPerPlayers;
	private int k;
	private int c;
	
	
	private int[] amountShotPerPlayers() {
		int[] shots = new int[players.length];
		for (int i = 0; i < shots.length; i++) {
			shots[i] = amountShotPerPlayer(players[i]);
		}
		return shots;
	}
	
	private int calculateAmountShots() {
		int amount = 0;
		for (int i = 0; i < amountShotPerPlayers.length; i++) {
			amount += amountShotPerPlayers[i];
		}
		return amount;
	}
	
	private int amountShotPerPlayer(Player player) {
		return player.getResistance()/5;
	}
}
