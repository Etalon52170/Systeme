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
     
      caisse.retirer(client);
     if(caisse.fileVide() == false) {
      Evenement e = new EvenementFinServiceClient(date+client.getNbArticles()*tempsPourScannerUnArticle+tempsDePaimentCBouMonnaie, client,caisse);
            echeancier.ajouter(e);
   }

   ligneDeCaisses.clientSortis(client);
   
}

}
