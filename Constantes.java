public class Constantes { // et autres utilitaires.

    static final int nombreTotalDeCaisses = 15;
    
    static final int ratioDeCaissesRapides = 5;
    
    static final int maxArticleCaisseRapide = 10;
    
    static final int tempsPourScannerUnArticle = 5;
    
    static final int dureePauseCaissier = 3000;
    
    static final int tempsPourChangerDeCaisse = 10;
    
    static final int frenceArriveeDesClients = 55;
    
    static final int delaiDePatiencePetitCaddy = 2400;
    
    static final int delaiDePatienceGrosCaddy = 6000;
    
    static final int tempsDePaimentCBouMonnaie = 600;
    
    public static void aProgrammerPlusTard () {
       String s = null;
	s.charAt(0); // Provoque un plantage volontaire !
}

public static boolean incoherenceOuTrucBizarre () {
	String s = null;
	assert false : "incoherenceOuTrucBizarre";
	s.charAt(0); // Provoque un plantage volontaire !
	return false;
}

}
