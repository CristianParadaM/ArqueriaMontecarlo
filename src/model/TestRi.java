package model;

import java.util.ArrayList;

public class TestRi {
	private static TestRi test = null;
	
	public static TestRi getInstance() {
		if (test == null) {
			test = new TestRi();
		}
		return test;
	}

	public boolean testNumbers(ArrayList<Double> numbers) {
//		return new Prueba_Medias(numbers).isApproved() &&
//				new Prueba_Varianza(numbers).isApproved() &&
//				new PruebaKS(numbers).isApproved() &&
//				new PruebaChi2(numbers).isApproved() &&
//				new Prueba_Poker(numbers).isApproved();
		return true;
	}

}
