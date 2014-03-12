import java.util.*;

public class Echeancier extends Constantes {
    //
    // La liste triée chronologiquement des événements du simulateur.
    //
	
	private LinkedList<Evenement> listeEvenements; 
    // La liste triée des événements.

	public boolean debugCheckAssertOnly() {
		int i = 0;
		long d = 0;
		while ( i < listeEvenements.size() ) {
			Evenement e = listeEvenements.get(i);
			assert d <= e.date;
			if ( d > e.date ) {
				return incoherenceOuTrucBizarre();
			}
			i = i + 1;
		}
		return true;
	}
	
	public Echeancier() {
		listeEvenements = new LinkedList<Evenement>();
	}
	
	public boolean estVide () {
		return listeEvenements.size() == 0;
	}
	
	public void ajouter (Evenement e) {
		int pos = 0;
		while ( pos < listeEvenements.size() ) {
			if (((Evenement) listeEvenements.get(pos)).date >= e.date) {
				listeEvenements.add(pos, e);
				return;
			} else {
				pos++;
			}
		}
		listeEvenements.add(pos, e);
	}
	
	public Evenement retourneEtEnlevePremier () {
		Evenement e = (Evenement) listeEvenements.getFirst();
		listeEvenements.removeFirst();
		return e;
	}

	public void retirerGrincheux(Client client) {
		List liste = Collections.synchronizedList(listeEvenements);
		List<Evenement> listecopie = new LinkedList<Evenement>();
		listecopie.addAll(liste);

		
		for(Evenement e: listecopie){

			if(e instanceof EvenementClientGrincheuxRonchonne) {
			EvenementClientGrincheuxRonchonne ecgr = (EvenementClientGrincheuxRonchonne)e;
				if(ecgr.getClient() == client){
				listeEvenements.remove(e);
			}
	}


	}

	}
	public void affiche (LigneDeCaisses ascenseur) {
		System.out.print("Echeancier: ");
		int index = 0;
		while ( index < listeEvenements.size() ) {
			((Evenement) listeEvenements.get(index)).affiche(ascenseur);
			index++;
			if ( index < listeEvenements.size() ) {
				System.out.print(',');
			}
		}
		System.out.println("");
	}

	
	}
	

