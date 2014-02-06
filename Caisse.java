import java.util.ArrayList;

public class Caisse extends Constantes {
    
    private LigneDeCaisses ligneDeCaisses; // Correspondante
    
    private int numeroMem;
    
    private char status; // 'O' ou 'F' ou 'P';
    
    public boolean estRapide = false;
    
    private ArrayList<Client> listeDesClients;
    
    
    public int numero() {
	return numeroMem;
    }
    
    public int nombreDePauses = 0;
    
    public Caisse (LigneDeCaisses ldc, int nm, boolean er) {
	ligneDeCaisses = ldc;
	numeroMem = nm;
	estRapide = er;
	setStatus('O');
	listeDesClients = new ArrayList<Client>();
    }
    
    public boolean debugCheckAssertOnly() {
	if ( ! (status == 'O' || status == 'F' || status == 'P') ) {
	    return incoherenceOuTrucBizarre();
	}
	if ( ligneDeCaisses.caisse(numeroMem) !=  this ) {
	    return incoherenceOuTrucBizarre();
	}
	return true;
    }
    
    public void affiche () {
	if ( this.numero() < 10 ) {
	    System.out.print(' ');
	}
	System.out.print(numero());
	System.out.print(' ');
	System.out.print(status);
	System.out.print(' ');
	if ( estRapide ) {
	    System.out.print('R');
	} else {
	    System.out.print('-');
	}
	System.out.print(": ");
	int i = 0;
	while ( i < listeDesClients.size() ) {
	    listeDesClients.get(i).afficher();
	    i = i + 1;
	}
	System.out.println();
    }

    public void ajouter (Client client) {
	assert estOuverteEtaccepte(client);
	if ( listeDesClients.isEmpty() ) {
	    listeDesClients.add(client);
	} else {
	    aProgrammerPlusTard();
	}
    }
    
    public char getStatus () {
	return status;
    }
    
    public void setStatus (char s) {
	assert (s == 'O' || s == 'F' || s == 'P');
	status = s;
    }
    
    public boolean fileVide () {
	return (listeDesClients.size() == 0);
    }
    
    public int longueurFile () {
	return listeDesClients.size();
    }
    
    public boolean estOuverteEtaccepte ( Client client ) {
	if ( this.getStatus() != 'O' ) {
	    return false;
	}
	if ( estRapide ) {
	    return (client.getNbArticles() <= maxArticleCaisseRapide);
	}
	return true;
    }

}
