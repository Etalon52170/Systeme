public class EvenementChoisirBonneCaisse extends Evenement {
	
	private Caisse caisse;
	
	private Client client;
	
    private int direction; // 1 ou -1
    
    private int nbEssais = 1;
    
    public EvenementChoisirBonneCaisse(long d, Caisse cai, Client cli, int dir) {
    	super(d);
    	caisse = cai;
    	client = cli;
    	direction = dir;
    }
    
    public void afficheDetails(LigneDeCaisses ligneDeCaisses) {
    	System.out.print("CBC ");
    	System.out.print(caisse.numero());
    	System.out.print(' ');
    	client.afficher();
    	System.out.print(' ');
    	System.out.print(direction);
    	System.out.print(' ');
    	System.out.print(nbEssais);
    }
    
  public void traiter(LigneDeCaisses ligneDeCaisses, Echeancier echeancier) {
       
    nbEssais++;
    Evenement e;
    Caisse nCaisse;
    long d;
    if(direction == -1){
        nCaisse = ligneDeCaisses.caisseAGaucheDe(caisse.numero());
    }else{
        nCaisse = ligneDeCaisses.caisseADroiteDe(caisse.numero());
    }
    if ( ! caisse.estOuverteEtaccepte(client) ) {
        // On en essaye une autre:
        if(direction == -1){
            nCaisse = ligneDeCaisses.caisseAGaucheDe(caisse.numero());
        }else{
            nCaisse = ligneDeCaisses.caisseADroiteDe(caisse.numero());
        }
        
        this.date+=tempsPourChangerDeCaisse;
        this.caisse=nCaisse;
        echeancier.ajouter(this);
    } else if ( nbEssais <= 5 ) {
        // On est exigeant et on cherche une caisse vide:
        if ( caisse.fileVide() ) {
            caisse.ajouter(client);
            e = new EvenementFinServiceClient(date + tempsDePaimentCBouMonnaie + tempsPourScannerUnArticle*client.getNbArticles(), client, caisse);
            echeancier.ajouter(e);
        } else {
            if(direction == -1){
                nCaisse = ligneDeCaisses.caisseAGaucheDe(caisse.numero());
            }else{
                nCaisse = ligneDeCaisses.caisseADroiteDe(caisse.numero());
            }
           
              this.date+=tempsPourChangerDeCaisse;
                this.caisse=nCaisse;
            echeancier.ajouter(this);
        }
    } else if ( nbEssais <= 10 ) {
        // On est moins exigeant et on cherche une caisse pas trop longue:
        if ( caisse.longueurFile() <= 2 ) {
            caisse.ajouter(client);
        } else {
            if(direction == -1){
                nCaisse = ligneDeCaisses.caisseAGaucheDe(caisse.numero());
            }else{
                nCaisse = ligneDeCaisses.caisseADroiteDe(caisse.numero());
            }
           
            this.date+=tempsPourChangerDeCaisse;
            this.caisse=nCaisse;
              echeancier.ajouter(this);
        }
    } else {
        // On est encore moins exigeant et on prend tout de suite:
        caisse.ajouter(client);
    }
    }


}