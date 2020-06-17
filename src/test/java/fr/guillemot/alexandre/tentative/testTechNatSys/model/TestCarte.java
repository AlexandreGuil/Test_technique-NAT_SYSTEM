package fr.guillemot.alexandre.tentative.testTechNatSys.model;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestCarte {

	private static Carte infCarte;
	private static Carte equalCarte0;
	private static Carte equalCarte1;
	private static Carte supCarte;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		infCarte = new Carte("2 de trèfle");
		equalCarte0 = new Carte("10 de coeur");
		equalCarte1 = new Carte("10 de pique");
		supCarte = new Carte("As de carreau");
	}
	
	@Test
	public void testCompareTo() {
		
		if(infCarte.compareTo(supCarte) == -1) {
			assertTrue(true);
		} else {
			fail();
		}
		
		if(equalCarte0.compareTo(equalCarte1) == 0) {
			assertTrue(true);
		} else {
			fail();
		}
		
		if(supCarte.compareTo(infCarte) == 1) {
			assertTrue(true);
		} else {
			fail();
		}
	}

}
