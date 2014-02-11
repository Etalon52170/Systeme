public class EvenementFinServiceClient extends Evenement {
    
    Client client;
    
    Caisse caisse;
    
    public EvenementFinServiceClient(long d, Client cl, Caisse ca) {
       super(d);
       client = cl;
       caisse = ca;
   }
   
   public void afficheDetails(LigneDeCaisses ligneDeCaisses) {
       System.out.print("FSC ");
       client.afficher();
   }
   
   public void traiter(LigneDeCaisses ligneDeCaisses, Echeancier echeancier) {
       Client c = new Client(date);
   }
   
}
