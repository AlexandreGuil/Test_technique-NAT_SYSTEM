package fr.guillemot.alexandre.tentative.testTechNatSys.model;

import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JeuxDeCarte {

	public final static Log LOG = LogFactory.getLog(JeuxDeCarte.class);
	
	private final static Integer NUM_COULEURS = 4;
	private final static Integer NUM_VALEURS = 13;
	private static LinkedList<String> jeux = null;

	private JeuxDeCarte() {
		Integer cmpt = 0;
		jeux = new LinkedList<>();
		String[] valeurs = new String[NUM_VALEURS];
		String[] couleur = {"trèfle", "pique", "coeur", "carreau"};
		
		for(int i=0; i<NUM_VALEURS; i++) {
			if (i<9) {
				valeurs[i] = "" + (i + 2);
			} else {
				switch(i) {
				case 9 : valeurs[i] = "Valet";
				break;
				case 10 : valeurs[i] = "Reine";
				break;
				case 11 : valeurs[i] = "Roi";
				break;
				case 12 : valeurs[i] = "As";
				}
			}
		}
		
		for(int i=0; i<NUM_COULEURS; i++) {
			for(int j=0; j<NUM_VALEURS; j++) {
				jeux.add(cmpt++, valeurs[j] + " de " + couleur[i]);
			}
		}

	}

	public static LinkedList<String> getJeux() {
		if(jeux == null || jeux.isEmpty()) {
			new JeuxDeCarte();
		}
		return jeux;
	}
	
	public static void affiche() {
		LinkedList<String> jeux = getJeux();
		
		for(int i=0; i<NUM_VALEURS; i++) {
			if(jeux.get(i).contains("Valet") || jeux.get(i).contains("Reine")) {
				LOG.debug("\t\t" + jeux.get(i) + 
						"\t\t" + jeux.get(i + NUM_VALEURS) + 
						"\t\t" + jeux.get(i + NUM_VALEURS * 2) + 
						"\t\t" + jeux.get(i + NUM_VALEURS * 3));
			} else {
				LOG.debug("\t\t  " + jeux.get(i) + 
						"\t\t  " + jeux.get(i + NUM_VALEURS) + 
						"\t\t  " + jeux.get(i + NUM_VALEURS * 2) + 
						"\t\t  " + jeux.get(i + NUM_VALEURS * 3));
			}
		}
		
	}
	
}
