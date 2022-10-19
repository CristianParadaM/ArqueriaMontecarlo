package model;

import java.util.ArrayList;

public class TestRi {
	private static TestRi test = null;
	private Prueba_Medias pMedias;
	private Prueba_Varianza pVarianza;
	private PruebaKS pKS;
	private PruebaChi2 pChi2;
	private Prueba_Poker pPoker;
	
	public static TestRi getInstance() {
		if (test == null) {
			test = new TestRi();
		}
		return test;
	}
	
	private TestRi() {
		this.pMedias = new Prueba_Medias();
		this.pVarianza = new Prueba_Varianza();
		this.pKS = new PruebaKS();
		this.pChi2 = new PruebaChi2();
		this.pPoker = new Prueba_Poker();
	}

	public boolean testNumbers(ArrayList<Double> numbers) {
		pMedias.setList(numbers);
		pVarianza.setList(numbers);
		pKS.setList(numbers);
		pChi2.setList(numbers);
		pPoker.setList(numbers);
		return pMedias.isApproved() &&
				pVarianza.isApproved() &&
				pKS.isApproved() &&
				pChi2.isApproved() &&
				pPoker.isApproved();
	}

}
