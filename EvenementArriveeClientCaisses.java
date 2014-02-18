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
               
        if (caisse.estOuverteEtaccepte(client) && caisse.fileVide()) {
            caisse.ajouter(client);

            Evenement e = new EvenementFinServiceClient(date+client.getNbArticles()*tempsPourScannerUnArticle+tempsDePaimentCBouMonnaie, client,caisse);
            echeancier.ajouter(e);
        } else {
           

            if (caisse.numero() < ligneDeCaisses.nombreDeCaisses()/2 || caisse.numero() == 7){

                caisse=ligneDeCaisses.caisseADroiteDe(caisse.numero());
                Evenement e = new EvenementChoisirBonneCaisse(date+tempsPourChangerDeCaisse,caisse,client,1);
                echeancier.ajouter(e);
                

            } else {
                
                
                caisse= ligneDeCaisses.caisseAGaucheDe(caisse.numero());
                Evenement e = new EvenementChoisirBonneCaisse(date+tempsPourChangerDeCaisse,caisse,client,-1);
                echeancier.ajouter(e);

            }
        }




            
    	Caisse c = uneCaisseAuHazard(ligneDeCaisses);
    	Evenement e = new EvenementArriveeClientCaisses(date + loiDePoisson.suivant(), c);
    	echeancier.ajouter(e);

        
        long d = delaiDePatienceGrosCaddy;
        if (client.getNbArticles() <= maxArticleCaisseRapide ) {
            d = (delaiDePatiencePetitCaddy);
        }

        
        e = new EvenementClientGrincheuxRonchonne(date +d, client);
        echeancier.ajouter(e);

        
        
        long ddd = dureePauseCaissier;
        Evenement fpc = new EvenementFinPauseCaissier(ddd, c);

        
    }


    
    
    private static final LoiDePoisson loiDePoisson = new LoiDePoisson(96, frenceArriveeDesClients);
    
    private static PressRandomNumberGenerator randomGenerator = new PressRandomNumberGenerator(43);
    private static Caisse uneCaisseAuHazard(LigneDeCaisses ligneDeCaisses) {
    	int n = randomGenerator.intSuivant(ligneDeCaisses.nombreDeCaisses());
    	return ligneDeCaisses.caisse(n);
    }
}
