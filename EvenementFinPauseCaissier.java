public class EvenementFinPauseCaissier extends Evenement {
    
    private Caisse caisse;
    
    public EvenementFinPauseCaissier(long d, Caisse c) {
       super(d);
       caisse = c;
   }
   
   public void afficheDetails(LigneDeCaisses ligneDeCaisses) {
       System.out.print("FPC ");
       System.out.print(caisse.numero());
   }
   
   public void traiter(LigneDeCaisses ligneDeCaisses, Echeancier echeancier) {
      
       this.caisse.setStatus('O');
   }
   
}
