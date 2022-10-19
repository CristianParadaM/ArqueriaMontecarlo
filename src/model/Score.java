package model;

public enum Score {
	CENTER(10), INTERMEDIATE(9), EXTERIOR(8), ERROR(0);
	
	private int score;
	
	private Score(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
}
