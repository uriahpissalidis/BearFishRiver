package proj1;

import java.util.Random;

public class Bear extends Animal{
	
	//bear's "strength"
	public int strength;

	//creates an instance of a bear object with an age and gender
	public Bear(int age, Gender gender){
		super(age, gender);
		age = RandomSingleton.getInstance().nextInt(10);
	}

	//returns true if the current age of the animal has reached it's
	//age limit for the species, else false
	public boolean maxAge(){
		return age >= BEAR_MAX_AGE;
	}

	//if the current age of the animal is less than the maximum for the
	//species, increment the age of the animal by one and return true;
	//otherwise, age doesn't change and returns false
	public boolean incrAge(){
		if(age < BEAR_MAX_AGE){
			age++;
			return true;
		}
		return false;
	}

	//return the strength of the bear
	public int getStrength(){
		int[] howOld = {0,1,2,3,4,5,6,7,8};
		int[] power = {1,2,3,4,5,3,2,1,0};

		for(int i=0; i<howOld.length; i++){
			if(howOld[i] == age){
				strength = power[i];
			}
		}
		return strength;
	}

	public String toString(){
		string g;
		if(gender == Gender.FEMALE){
			g = "F";
		}
		else {
			g = "M";
		}
		return "B" + g + age;
	}
}