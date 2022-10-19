package model;

public enum Gender {
	MALE, FEMALE;
	
	public static final double[] probabilitysFemale = new double[] {0.3,0.38,0.27,0.05};
	public static final double[] probabilitysMale = new double[] {0.2,0.33,0.4,0.07};
	public static final double[] probFemaleMontecatlo = new double[] {0.29,0.67,0.94,0.99};
	public static final double[] probMaleMontecatlo = new double[] {0.19,0.52,0.92,0.99};
	
}
