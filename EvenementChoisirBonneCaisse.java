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
    	nbEssais = nbEssais + 1;
    	if ( ! caisse.estOuverteEtaccepte(client) ) {
	    // On en essaye une autre:
    		if(this.direction == 1) {
                if( this.caisse.numero() == 15) {
                    this.direction = -1;

                }

                
            }else{
                if( this.caisse.numero() == 1) {
                    this.direction =1;
                }
                }
          

    	} else if ( nbEssais <= 5 ) {
	    // On est exigeant et on cherche une caisse vide:
    		if ( caisse.fileVide() ) {
    			echeancier.ajouter(this);
    		} else {
    			
    		}
    	} else if ( nbEssais <= 10 ) {
	    // On est moins exigeant et on cherche une caisse pas trop longue:
    		if ( caisse.longueurFile() <= 2 ) {
    			aProgrammerPlusTard();
    		} else {
    			aProgrammerPlusTard();
    		}
    	} else {
	    // On est encore moins exigeant et on prend tout de suite:
    		aProgrammerPlusTard();
    	}
    }

}
