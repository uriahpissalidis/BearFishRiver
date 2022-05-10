import proj1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Animal.Gender;

public class BearTest 
{
	private Bear b;
	private Bear c;
	
	public void startUp(){
		b = new Bear(0, Gender.FEMALE);
		c = new Bear(9, Gender.MALE);
	}

	public void testMaxAge(){
		assertFalse(b.maxAge());
		assertTrue(c.maxAge());
	}

	public void testIncrAge(){
		assertFalse(c.incrAge());
		assertTrue(b.incrAge());
	}

	public void testGetStrength(){
		assertEquals(1,b.getStrength());
	}

	public void testToString() {
		assertEquals("BF0",b.toString());
	}
}