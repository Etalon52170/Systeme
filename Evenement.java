public abstract class Evenement extends Constantes {
    
    protected long date; // en dixieme de secondes
    
    public Evenement (long d) {
    	date = d;
    }
    
    public abstract void traiter (LigneDeCaisses ligneDeCaisses, Echeancier echeancier);
    
    public void affiche (LigneDeCaisses ligneDeCaisses) {
       System.out.print('[');
       System.out.print(date);
       System.out.print(',');
       this.afficheDetails(ligneDeCaisses);
       System.out.print(']');
   }
   
   public abstract void afficheDetails (LigneDeCaisses ligneDeCaisses);
   

   public Long getDate() {
    return date;
   }
}
