package model;

import java.util.ArrayList;

public class GenerateRi {
	private static GenerateRi generaterRi = null;
	
	public static GenerateRi getInstance() {
		if (generaterRi == null) {
			generaterRi = new GenerateRi();
		}
		return generaterRi;
	}
	
	private GenerateRi() {
		
	}
	
	public ArrayList<Double> generateRi(int k, int c, int g) {
		ArrayList<Double> ri = new ArrayList<Double>();
		int count = 0;
		do {
			ri = convertList(new Congruencial(k+count, c+count, g).getRi());
			count += 2;
		} while (!TestRi.getInstance().testNumbers(ri));
		return ri;
	}

	private ArrayList<Double> convertList(double[] ri) {
		ArrayList<Double> aux = new ArrayList<Double>();
		for (int i = 0; i < ri.length; i++) {
			aux.add(ri[i]);
		}
		return aux;
	}
	
}
