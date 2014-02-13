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
        boolean fin = false;

        do {
            nbEssais = nbEssais + 1;
            if (!caisse.estOuverteEtaccepte(client)) {
                // On en essaye une autre:
                caisse = prochaineCaisse(ligneDeCaisses);
            } else if (nbEssais <= 5) {
                // On est exigeant et on cherche une caisse vide:
                if (caisse.fileVide()) {
                    caisse.ajouter(client);
                    fin = true;
                } else {
                    caisse = prochaineCaisse(ligneDeCaisses);
                }
            } else if (nbEssais <= 10) {
                // On est moins exigeant et on cherche une caisse pas trop
                // longue:
                if (caisse.longueurFile() <= 2) {
                    caisse.ajouter(client);
                    fin = true;
                } else {
                    caisse = prochaineCaisse(ligneDeCaisses);
                }
            } else {
                // On est encore moins exigeant et on prend tout de suite:
                caisse.ajouter(client);
                fin = true;
            }
        } while (!fin);
    }

    private Caisse prochaineCaisse(LigneDeCaisses ligneDeCaisses) {
        Caisse res;
        if (direction == 1) {
            // Droite
            res = ligneDeCaisses.caisseADroiteDe(caisse.numero());
        } else {
            // Gauche
            res = ligneDeCaisses.caisseAGaucheDe(caisse.numero());
        }

        // Si on est en bout de ligne de caisse, on change la direction
        if (res == null) {
            direction *= -1;
            res = prochaineCaisse(ligneDeCaisses);
        }
        return res;
    }

}