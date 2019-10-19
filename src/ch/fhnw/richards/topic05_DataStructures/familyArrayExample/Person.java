package ch.fhnw.richards.topic05_DataStructures.familyArrayExample;

public class Person {
	private String name;
	private double height;
	private double weight;
	
	public Person(String name, double height, double weight) {
		this.name = name;
		this.height = height;
		this.weight = weight;
	}

	// Getters and setters
	public String getName() {
		return name;
	}

	public double getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}
}
