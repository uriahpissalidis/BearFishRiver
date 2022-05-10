package proj1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Animal.Gender;

public class AnimalBearFishRiverTest{
	private River r;
	private Fish a;
	private Bear b;
	private Bear c;
	private Fish d;

	public void startUp(){
		r = new River(8);
		a = new Fish(0, Gender.FEMALE);
		b = new Bear(0, Gender.FEMALE);
		c = new Bear(8, Gender.MALE);
		d = new Fish(3, Gender.FEMALE);
	}

	public void testMaxAge(){
		assertFalse(b.maxAge());
		assertTrue(c.maxAge());
		assertFalse(a.maxAge());
		assertTrue(d.maxAge());
	}

	public void testIncrAge(){
		assertFalse(c.incrAge());
		assertTrue(b.incrAge());
		assertFalse(d.incrAge());
		assertTrue(a.incrAge());
	}

	public void testGetStrength() {
		assertEquals(1,b.getStrength());
	}

	public void testToString(){
		assertEquals("BF0", b.toString());
		assertEquals("FF0", a.toString());
	}
	
	//River
	public void testGetLength() {
		assertEquals(8, r.getLength());
		
		//see if it resets correctly
		r = new River(10);
		assertEquals(10, r.getLength());
	}

	public void testNumEmpty(){
		//initially, nothing in r
		assertEquals(8, r.numEmpty());
		
		Animal mommaBear = new Bear(3, Gender.FEMALE);
		r.addRandom(mommaBear);
		assertEquals(7, r.numEmpty());
		
		Animal salmon = new Fish(2, Gender.MALE);
		Animal papaBear = new Bear(3, Gender.FEMALE);
		Animal tilapia = new Fish(2, Gender.MALE);
		Animal tiny = new Bear(3, Gender.FEMALE);
		
		r.addRandom(salmon);
		r.addRandom(papaBear);
		r.addRandom(tilapia);
		r.addRandom(tiny);
		
		//5 Animals added
		assertEquals(3, r.numEmpty());
	}

	public void testAddRandom(){
		Animal a1 = new Bear(0, Gender.MALE);
		assertTrue(r.addRandom(a1));
		
		Animal a2 = new Fish(0, Gender.MALE);
		Animal a3 = new Bear(0, Gender.FEMALE);
		Animal a4 = new Fish(0, Gender.MALE);
		Animal a5 = new Bear(0, Gender.FEMALE);
		Animal a6 = new Fish(0, Gender.MALE);
		Animal a7 = new Bear(0, Gender.FEMALE);
		Animal a8 = new Fish(0, Gender.MALE);
		
		r.addRandom(a2);
		r.addRandom(a3);
		r.addRandom(a4);
		r.addRandom(a5);
		r.addRandom(a6);
		r.addRandom(a7);
		r.addRandom(a8);
		
		//Cannot add because full
		assertFalse(r.addRandom(new Fish(0,Gender.MALE)));
	}

}


