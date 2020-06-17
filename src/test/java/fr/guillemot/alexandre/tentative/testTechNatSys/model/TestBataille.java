package fr.guillemot.alexandre.tentative.testTechNatSys.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestBataille {
	
	private static Integer NUM_JOUEUR = 4;
	private Integer NUM_CARTES = 52;
	private Integer CARTES_PAR_JOUEUR = NUM_CARTES/NUM_JOUEUR;
	private static Bataille bataille;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		bataille = new Bataille(NUM_JOUEUR);
	}
	
	@Test
	public void testGetDistibDesCartes() {
		Integer j, i, cmptNumCarte = NUM_CARTES, sizeLinkedListCartes = 0;
		String tst;
		Carte compare0, compare1;
		LinkedList<Carte> tmp = null;
		LinkedList<String> jeux = JeuxDeCarte.getJeux();
		Set<String> tstUnicite = new HashSet<>();
		HashMap<String, LinkedList<Carte>> gamePlay = new HashMap<String, LinkedList<Carte>>();

		for(i=0; i<NUM_JOUEUR; i++) {
			for(j=0; j<CARTES_PAR_JOUEUR; j++) {
				try {
					Integer rand = (int)(Math.random()*NUM_CARTES);
					
					if(rand >= 0 && rand < 52) {
						assertTrue(true);
					} else {
						fail();
					}
					
					if(gamePlay.containsKey("joueur_" + (i+1))) {
						gamePlay.get("joueur_" + (i+1)).push(new Carte(jeux.get(rand)));
						
						compare0 = gamePlay.get("joueur_" + (i+1))
								.get(0);
						compare1 = new Carte(jeux.get(rand));
						
						if(compare0.compareTo(compare1) == 0) {
							assertTrue(true);
						} else {
							fail();
						}
						
					} else {
						tmp = new LinkedList<>();
						tmp.push(new Carte(jeux.get(rand)));
						gamePlay.putIfAbsent("joueur_" + (i+1), tmp);
						
						compare0 = gamePlay.get("joueur_" + (i+1))
								.get(gamePlay.get("joueur_" + (i+1)).size() - 1);
						compare1 = new Carte(jeux.get(rand));
						
						if(compare0.compareTo(compare1) == 0) {
							assertTrue(true);
						} else {
							fail();
						}
						
					}
					
					tst = jeux.get(rand);
					jeux.remove(tst);
					
					if(!jeux.contains(tst)) {
						assertTrue(true);
					} else {
						fail();
					}
					
					if(jeux.size() == --cmptNumCarte) {
						assertTrue(true);
					} else {
						fail();
					}
					
				} catch (IndexOutOfBoundsException err) {
					j--;
				}
			} 
		}
		
		for(Map.Entry<String, LinkedList<Carte>> entry : gamePlay.entrySet()) {
			sizeLinkedListCartes += entry.getValue().size();
			for(Carte c : entry.getValue()) {
				tstUnicite.add(c.getCarte());
			}
		}
		
		if(sizeLinkedListCartes == (CARTES_PAR_JOUEUR*NUM_JOUEUR)) {
			assertTrue(true);
		} else {
			fail();
		}
		
		if(tstUnicite.size() == sizeLinkedListCartes) {
			assertTrue(true);
		} else {
			fail();
		}
	}
	
	@Test
	public void testGetPli() {
		Integer rand;
		HashMap<String, LinkedList<Carte>> gamePlay = bataille.getDistibDesCartes();
		Set<String> key = gamePlay.keySet();
		HashMap<String, Carte> pli = new HashMap<>();

		for(String k : key) {
			rand = (int)(Math.random()*CARTES_PAR_JOUEUR);
			
			if(rand >= 0 && rand < 13) {
				assertTrue(true);
			} else {
				fail();
			}
			
			pli.put(k, gamePlay.get(k).get(rand));
		}
		
		if(pli.keySet().size() == NUM_JOUEUR) {
			assertTrue(true);
		} else {
			fail();
		}
		
		pli = pli.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap( 
						Map.Entry::getKey, 
					    Map.Entry::getValue, 
					    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		
		
	}
	
	@Test
	public void testGetGagnant() {
		HashMap<String, Carte> pli = bataille.getPli(bataille.getDistibDesCartes());
		ArrayList<Entry<String, Carte>> testPli;
		testPli = new ArrayList<>(pli.entrySet());
		
		for(int i=NUM_JOUEUR - 1; i>0; i--) {
			if(testPli.get(i).getValue()
					.compareTo(testPli.get(i - 1).getValue()) == 1 ||
					testPli.get(i).getValue().compareTo(testPli.get(i - 1).getValue()) == 0) {
				assertTrue(true);
			} else {
				fail();
			}
		}
	}
	
	@Test
	public void testDistribCarteApresPli() {
		HashMap<String, LinkedList<Carte>> gamePlay = bataille.getDistibDesCartes();
		HashMap<String, Carte> pli = bataille.getPli(gamePlay);
		Entry<String, Carte> gagnant = bataille.getGagnant(pli);
		
		pli.remove(gagnant.getKey());
		
		if(!pli.containsKey(gagnant.getKey()) || !pli.containsValue(gagnant.getValue())) {
			assertTrue(true);
		} else {
			fail();
		}
		
		for(Map.Entry<String, Carte> entry : pli.entrySet()) {
			gamePlay.get(entry.getKey()).remove(entry.getValue());
			gamePlay.get(gagnant.getKey()).push(entry.getValue());
		}
		
		if(gamePlay.get(gagnant.getKey()).size() == (CARTES_PAR_JOUEUR + NUM_JOUEUR - 1)) {
			assertTrue(true);
		} else {
			fail();
		}
		
		gamePlay.remove(gagnant.getKey());
		
		for(Map.Entry<String, LinkedList<Carte>> entry : gamePlay.entrySet()) {
			if(entry.getValue().size() == CARTES_PAR_JOUEUR - 1) {
				assertTrue(true);
			} else {
				fail();
			}
		}
	}
	
}
