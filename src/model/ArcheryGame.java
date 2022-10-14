package model;

import java.util.ArrayList;

public class ArcheryGame {
	private Player[] players;
	private Round[] rounds;
	private ArrayList<Double> archerys;
	private int amountArcherys;
	private int k;
	private int c;
	
	public ArcheryGame(Player[] players, int amountArcherys, int k, int c) {
		this.players = players;
		this.archerys = generateArcherys();
		this.rounds = generateRounds();
		this.amountArcherys = amountArcherys;
		this.k = k;
		this.c = c;
	}

	private Round[] generateRounds() {
		Round[] aux = new Round[10];
		int count = 0;
		int position = -1;
		for (int i = 0; i < aux.length; i++) {
			ArrayList<Double> list = (ArrayList<Double>) archerys.subList(i*(amountArcherys+2), (i+1)*(amountArcherys+2));
			aux[i] = new Round(players, list, position, count);
			count = position != aux[i].getLuckyPlayer()?0:count+1==3?0:count+1;
			position = aux[i].getLuckyPlayer();
		}
		return aux;
	}

	private ArrayList<Double> generateArcherys() {
		return GenerateRi.getInstance().generateRi(k,c,10);
	}
	
}
