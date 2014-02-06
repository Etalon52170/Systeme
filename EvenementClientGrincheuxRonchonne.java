public class EvenementClientGrincheuxRonchonne extends Evenement {
    
    Client client;
    
    public EvenementClientGrincheuxRonchonne(long d, Echeancier echeancier, Client c) {
       super(d);
       client = c;
       aProgrammerPlusTard();
   }
   
   public void afficheDetails(LigneDeCaisses ligneDeCaisses) {
       System.out.print("CGR ");
       client.afficher();
   }
   
   public void traiter(LigneDeCaisses ligneDeCaisses, Echeancier echeancier) {
       aProgrammerPlusTard();
   }
   
}
