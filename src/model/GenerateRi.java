package model;

import java.util.ArrayList;

public class GenerateRi {
	private static GenerateRi generaterRi = null;
	private double[] ri;
	
	public static GenerateRi getInstance() {
		if (generaterRi == null) {
			generaterRi = new GenerateRi();
		}
		return generaterRi;
	}
	
	private GenerateRi() {
		ri = null;
	}
	
	public ArrayList<Double> generateRi(int k, int c, int g) {
		ArrayList<Double> aux = new ArrayList<Double>();
		int count = 0;
		do {
			ri = new Congruencial(k+count, c+count, g).getRi();
			aux = convertList(ri);
			count += 2;
		} while (!TestRi.getInstance().testNumbers(aux));
		return aux;
	}

	private ArrayList<Double> convertList(double[] ri) {
		ArrayList<Double> aux = new ArrayList<Double>();
		for (int i = 0; i < ri.length-1; i++) {
			if (!isRepeated(ri[i])) {
				aux.add(ri[i]);
			}
		}
		return aux;
	}
	
	private boolean isRepeated(double d) {
		if ((d+"").length() > 6) {
			char c = (d+"").charAt(2);
			for (int i = 2; i < (d+"").length(); i++) {
				if (c != (d+"").charAt(i)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public double[] getRi() {
		return ri;
	}
	
}
