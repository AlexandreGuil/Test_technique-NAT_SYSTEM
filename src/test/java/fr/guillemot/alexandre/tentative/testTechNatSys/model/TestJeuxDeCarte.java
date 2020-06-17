package fr.guillemot.alexandre.tentative.testTechNatSys.model;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestJeuxDeCarte {

	private static LinkedList<String> jeux;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		jeux = JeuxDeCarte.getJeux();
	}
	
	@Test
	public void testGetJeux() {
		LinkedList<String> jeuxTest = JeuxDeCarte.getJeux();
		
		if(jeuxTest == jeux) {
			assertTrue(true);
		} else {
			fail();
		}
		
		if(jeux.size() == 52) {
			assertTrue(true);
		} else {
			fail();
		}
	}

}
