package proj1;

import java.util.Random;

import Animal.Gender;

public class Fish extends Animal{

	//default constructor
	public Fish() {
		super();
		age = RandomSingleton.getInstance().nextInt(5);
	}

	//parameterized constructor
	public Fish(int age, Gender gender){
		super(age, gender);
	}

	
	//returns true if the current age of the animal has reached the 
	//limit for the species; else, return false.
	public boolean maxAge(){
		return age >= FISH_MAX_AGE;
	}

	//increment the age is if it is less than the max and return true;
	//else, return false
	public boolean incrAge(){
		if(age < FISH_MAX_AGE){
			age++;
			return true;
		}
		return false;
	}

	//toString
	public String toString(){
		String g;
		if(gender == Gender.FEMALE) g = "F";
		else g = "M";
		
		return "F" + g + age;
	}
}