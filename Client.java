public class Client extends Constantes {

    private int numero; // identifiant.
    
    private int nbArticles;
    
    private long dateDebut;
    
    int nombreDeRonchonnements = 0;
    
    int dateFin = 0; // date de fin de service.
    
    private static int compteurGlobalDeCreationDesClients = 0;

    public boolean debugCheckAssertOnly() {
    	assert nbArticles >= 0;
    	if ( nbArticles < 0 ) {
    		return incoherenceOuTrucBizarre();
    	}
    	assert nombreDeRonchonnements >= 0;
    	if ( nombreDeRonchonnements < 0 ) {
    		return incoherenceOuTrucBizarre();
    	}
    	assert (dateFin == 0) || (dateDebut < dateFin); 
    	return true;
    }

    public Client (long dd) {
    	compteurGlobalDeCreationDesClients++;
    	nbArticles = randomGenerator.intSuivant(50);
    	numero = compteurGlobalDeCreationDesClients;
    	dateDebut = dd;	
    }
    
    public void afficher() {
    	System.out.print('#');
    	System.out.print(numero);
    	System.out.print(':');
    	System.out.print(nbArticles);
    	System.out.print("a:");
    	System.out.print(dateDebut);
    	System.out.print("t:");
    	System.out.print(nombreDeRonchonnements);
    	System.out.print('r');
    	if ( dateFin > 0 ) {
    		System.out.print(':');
    		System.out.print(dateFin);
    		System.out.print('f');
    	}
    }
    
    public int getNbArticles () {
    	return nbArticles;
    }
    
    private static final PressRandomNumberGenerator randomGenerator = new PressRandomNumberGenerator(34);
    
}
