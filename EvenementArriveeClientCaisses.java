public class EvenementArriveeClientCaisses extends Evenement {
	
    private Caisse caisse = null; // de départ.
    
    public EvenementArriveeClientCaisses(long d, Caisse c) {
    	super(d);
    	caisse = c;
    }

    public EvenementArriveeClientCaisses(long d, LigneDeCaisses ligneDeCaisses) {
    	super(d);
    	caisse = uneCaisseAuHazard(ligneDeCaisses);
    }
    
    public void afficheDetails(LigneDeCaisses ligneDeCaisses) {
    	System.out.print("ACC ");
    	System.out.print(caisse.numero());
    }
    
    public void traiter(LigneDeCaisses ligneDeCaisses, Echeancier echeancier) {
    	Client client = new Client(date);
    	if ( caisse.estOuverteEtaccepte(client) ) {
    		caisse.ajouter(client);
    	} else {
    		aProgrammerPlusTard();
    	}
    	Caisse c = uneCaisseAuHazard(ligneDeCaisses);
    	Evenement e = new EvenementArriveeClientCaisses(date + loiDePoisson.suivant(), c);
    	echeancier.ajouter(e);

        
        long d = delaiDePatienceGrosCaddy;
        if (client.getNbArticles() <= maxArticleCaisseRapide ) {
            d = delaiDePatiencePetitCaddy;
        }

        
        e = new EvenementClientGrincheuxRonchonne(d, client);
        echeancier.ajouter(e);

        long dd = (client.getNbArticles()*tempsPourScannerUnArticle)+(tempsDePaimentCBouMonnaie); 
        Evenement efsc = new EvenementFinServiceClient(dd+ date, client, caisse);
        echeancier.ajouter(efsc);
    }


    
    
    private static final LoiDePoisson loiDePoisson = new LoiDePoisson(96, frenceArriveeDesClients);
    
    private static PressRandomNumberGenerator randomGenerator = new PressRandomNumberGenerator(43);
    private static Caisse uneCaisseAuHazard(LigneDeCaisses ligneDeCaisses) {
    	int n = randomGenerator.intSuivant(ligneDeCaisses.nombreDeCaisses());
    	return ligneDeCaisses.caisse(n);
    }
}
