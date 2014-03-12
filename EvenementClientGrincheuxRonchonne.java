public class EvenementClientGrincheuxRonchonne extends Evenement {
    
    Client client;
    
    public EvenementClientGrincheuxRonchonne(long d, Client c) {
       super(d);
       client = c;
       
   }

   public Client getClient(){
    return this.client;
   }
   
   public void afficheDetails(LigneDeCaisses ligneDeCaisses) {
       System.out.print("CGR ");
       client.afficher();
   }
   
   public void traiter(LigneDeCaisses ligneDeCaisses, Echeancier echeancier) {
       Client c = new Client(date);

       // ajout du client au ronchons
       ligneDeCaisses.clientGrincheux(c);

    
   }

  
  
   
}
