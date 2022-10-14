package model;

public class Player {
	private Gender gender;
	private int resistance;
	private int experience;
	private double fortune;
	private int experienceRounds;
	public Player(Gender gender, int resistance) {
		this.gender = gender;
		this.resistance = resistance;
		this.experience = 10;
		this.fortune = 0;
		this.experienceRounds = 0;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getResistance() {
		return resistance;
	}
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public double getFortune() {
		return fortune;
	}
	public void setFortune(double fortune) {
		this.fortune = fortune;
	}
	public int getExperienceRounds() {
		return experienceRounds;
	}
	public void setExperienceRounds(int experienceRounds) {
		this.experienceRounds = experienceRounds;
	}
	
}
