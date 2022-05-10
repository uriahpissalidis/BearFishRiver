package proj1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Animal.Gender;

public class AnimalTest{
	private Animal mommaBear;
	private Animal salmon;

	public void startUp(){
		mommaBear = new Bear(2, Gender.FEMALE);
		salmon = new Fish(1, Gender.MALE);
	}

	public void testGetAge(){
		assertEquals(2, mommaBear.getAge());
		assertEquals(1, salmon.getAge());
	}
}