package proj1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Animal.Gender;

public class FishTest{
	//variables
	private Fish b;
	private Fish c;
	
	public void startUp(){
		b = new Fish(0, Gender.FEMALE);
		c = new Fish(3, Gender.MALE);
	}

	public void testMaxAge(){
		assertFalse(a.maxAge());
		assertTrue(d.maxAge());
	}

	public void testIncrAge(){
		assertFalse(d.incrAge());
		assertTrue(a.incrAge());
	}

	public void testToString(){
		assertEquals("FF0",b.toString());
	}
}