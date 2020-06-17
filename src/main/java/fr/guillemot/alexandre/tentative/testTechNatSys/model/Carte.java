package fr.guillemot.alexandre.tentative.testTechNatSys.model;

import java.util.LinkedList;

public class Carte implements Comparable<Carte> {
	
	private String carte;
	private final Integer NUM_VALEURS = 13;

	public Carte(String carte) {
		this.carte = carte;
	}


	public String getCarte() {
		return carte;
	}


	public int compareTo(Carte compareCarte) {
		LinkedList<String> jeux = JeuxDeCarte.getJeux();
		Integer idCarte = jeux.indexOf(carte);
		Integer idCompareCarte =jeux.indexOf(compareCarte.getCarte());
		
		if(idCarte >= NUM_VALEURS) {
			idCarte %= NUM_VALEURS;
		}
		
		if(idCompareCarte >= NUM_VALEURS) {
			idCompareCarte %= NUM_VALEURS;
		}
		
		if(idCarte < idCompareCarte) {
			return -1;
		} else if(idCarte == idCompareCarte) {
			return 0;
		} else {
			return 1;
		}
	}
}