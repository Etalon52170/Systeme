import java.util.*;

public class LigneDeCaisses extends Constantes {
	
	private Caisse[] tableauDesCaisses;
	
	private ArrayList<Client> listeDesRonchons;
	
	private LinkedList<Client> listeClientsSortis;
	
	public LigneDeCaisses (Echeancier echeancier) {
		tableauDesCaisses = new Caisse[nombreTotalDeCaisses];
		int i = 0;
		int rapide = ratioDeCaissesRapides;
		while (	i  < tableauDesCaisses.length ) {
			rapide = rapide - 1;
			Caisse caisse = new Caisse (this, i + 1, rapide == 0);
			tableauDesCaisses[i] = caisse;
			if ( rapide == 0 ) {
				rapide = ratioDeCaissesRapides;
			}
			if ( i == tableauDesCaisses.length / 4 ) {
				caisse.setStatus('F');
				echeancier.ajouter(new EvenementFinPauseCaissier(dureePauseCaissier / 4, caisse));
			} else if ( i == tableauDesCaisses.length / 2 ) {
				caisse.setStatus('F');
				echeancier.ajouter(new EvenementFinPauseCaissier(dureePauseCaissier / 2, caisse));
			}
			i = i + 1;
		}
		listeDesRonchons = new ArrayList<Client>();
		listeClientsSortis = new LinkedList<Client>();
	}
	
	public int nombreDeCaisses() {
		return tableauDesCaisses.length;
	}
	
	public Caisse caisse(int num) {
		return tableauDesCaisses[num - 1];
	}
	
	public boolean caisseExiste(int num) {
		if ( num >= 1 ) {
			return num <= nombreDeCaisses();
		}
		return false;
	}
	
	public Caisse caisseADroiteDe(int num) {
		if ( num != this.nombreDeCaisses() ) {
			return caisse(num + 1);
		} else {
			return null;
		}
	}
	
	public Caisse caisseAGaucheDe(int num) {
		if ( num != 1 ) {
			return caisse(num - 1);
		} else {
			return null;
		}
	}

	public boolean debugCheckAssertOnly() {
		int i = 0;
		while ( i < tableauDesCaisses.length ) {
			tableauDesCaisses[i].debugCheckAssertOnly();
			i = i + 1;
		}
		i = 0;
		while ( i < listeDesRonchons.size() ) {
			Client c = listeDesRonchons.get(i);
			assert c.debugCheckAssertOnly();
			c.debugCheckAssertOnly();
			assert c.nombreDeRonchonnements > 0;
			i = i + 1;
		}
		return true;
	}

	public void affiche () {
		int i = 0;
		int j = 0;
		while ( i < tableauDesCaisses.length ) {
			tableauDesCaisses[i].affiche();
			i = i + 1;
		}
		System.out.print("Tous les ronchons: ");
		i = 0;
		int nbTotalRonchons = 0;
		int nbTotalRonchonnement = 0;
		if ( listeDesRonchons.size() == 0 ) {
			System.out.print("<vide>");
		} else {
			while ( i < listeDesRonchons.size() ) {
			Client client = listeDesRonchons.get(i);
			client.afficher();
			System.out.print(" ; ");
			nbTotalRonchons = nbTotalRonchons + 1;
			nbTotalRonchonnement = nbTotalRonchonnement + client.nombreDeRonchonnements;
			i = i + 1;
			}
		}
		System.out.println();
		System.out.print("Nombre total de ronchons: ");
		System.out.println(nbTotalRonchons);
		System.out.print("Nombre total de ronchonnements: ");
		System.out.println(nbTotalRonchonnement);
		int maxClientsSortisAffichable = 5;
		System.out.println("Nombre total de clients sortis: " + listeClientsSortis.size()); 
		System.out.print("Derniers sortis: ");
		i = 0;
		if ( listeClientsSortis.size() == 0 ) {
			System.out.print("<vide>");
		} else {
		    if ( listeClientsSortis.size() > maxClientsSortisAffichable ) {
			System.out.print("... ; ");
			j = listeClientsSortis.size() - maxClientsSortisAffichable;	
		    }
		    while ( i+j < listeClientsSortis.size() && i < maxClientsSortisAffichable ) {
			Client client = listeClientsSortis.get(i+j);
			client.afficher();
			System.out.print(" ; ");
			i = i + 1;
		    }
		}
		System.out.print("\n");
    }

    public void clientSortis(Client c ){
    	listeClientsSortis.add(c);
    }

	
}
