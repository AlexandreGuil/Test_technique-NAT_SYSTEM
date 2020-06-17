package fr.guillemot.alexandre.tentative.testTechNatSys.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Bataille {

	private final Integer NUM_JOUEUR;
	private final Integer CARTES_PAR_JOUEUR;
	private final Integer NUM_CARTES = 52;


	public Bataille(Integer numJoueur) {
		NUM_JOUEUR = numJoueur;
		CARTES_PAR_JOUEUR = 52/numJoueur;
	}

	public HashMap<String, LinkedList<Carte>> getDistibDesCartes() {
		Integer j, i;
		LinkedList<Carte> tmp;
		LinkedList<String> jeux = JeuxDeCarte.getJeux();
		HashMap<String, LinkedList<Carte>> gamePlay = new HashMap<String, LinkedList<Carte>>();

		for(i=0; i<NUM_JOUEUR; i++) {
			for(j=0; j<CARTES_PAR_JOUEUR; j++) {
				try {
					Integer rand = (int)(Math.random()*NUM_CARTES);
					if(gamePlay.containsKey("joueur_" + (i+1))) {
						gamePlay.get("joueur_" + (i+1)).push(new Carte(jeux.get(rand)));
					} else {
						tmp = new LinkedList<>();
						tmp.push(new Carte(jeux.get(rand)));
						gamePlay.putIfAbsent("joueur_" + (i+1), tmp);
					}
					jeux.remove(jeux.get(rand));
				} catch (IndexOutOfBoundsException err) {
					j--;
				}
			} 
		}

		return gamePlay;
	}
	
	public HashMap<String, Carte> getPli(HashMap<String, LinkedList<Carte>> gamePlay) {
		Integer rand;
		Set<String> key = gamePlay.keySet();
		HashMap<String, Carte> pli = new HashMap<>();

		for(String k : key) {
			 rand = (int)(Math.random()*CARTES_PAR_JOUEUR);
			pli.put(k, gamePlay.get(k).get(rand));
		}
		
		return pli.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap( 
						Map.Entry::getKey, 
					    Map.Entry::getValue, 
					    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	}

	public Entry<String, Carte> getGagnant(HashMap<String, Carte> pli) {
		ArrayList<Entry<String, Carte>> entryPli = new ArrayList<>(pli.entrySet());
		return entryPli.get(entryPli.size() - 1);
	}
	
	public HashMap<String, LinkedList<Carte>> distribCarteApresPli(HashMap<String, LinkedList<Carte>> gamePlay, 
			HashMap<String, Carte> pli) {
		
		Entry<String, Carte> gagnant = getGagnant(pli);
		pli.remove(gagnant.getKey());
		
		for(Map.Entry<String, Carte> entry : pli.entrySet()) {
			gamePlay.get(entry.getKey()).remove(entry.getValue());
			gamePlay.get(gagnant.getKey()).push(entry.getValue());
		}
		
		return gamePlay;
	}

}
