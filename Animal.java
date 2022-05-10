package proj1;

import java.util.Random;

/**
* @author Uriah Pissalidis
* 
* Abstract animal class, reduce code repitition between similar
* characteristics of different animals
*/

public abstract class Animal{
	protected enum Gender{
		female, male
	}

	//required variables for bear/fish objects
	protected Gender gender;
	protected int age;
	public static final int BEAR_MAX_AGE = 8;
	public static final int FISH_MAX_AGE = 3;

	//default constructor, assigns gender and 
	public Animal(){
		int genderType = RandomSingleton.getInstance().nextInt(2);
		if (genderType==0) gender = Gender.MALE;
		else gender = Gender.FEMALE;
	}

	//parameterized constructor, accepts age and gender
	public Animal(int age, Gender gender){
		this.age = age;
		this.gender = gender;
	}

	//getters
	public int getAge(){
		return age;
	}

	// returns true if the age of the animal has reached it's limit
	public abstract boolean maxAge();

	//if the age has been incremented return true, false otherwise
	public abstract boolean incrementAge();
}