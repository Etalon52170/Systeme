import java.io.BufferedReader;
import java.util.InputMismatchException;
import java.io.InputStreamReader;

public class Main  extends Constantes { // Programme de simulation d'une Ligne De Caisses

private static boolean assert_flag = false; 

public static void main (String args[]) {
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	Echeancier echeancier = new Echeancier();
	LigneDeCaisses ligneDeCaisses = new LigneDeCaisses(echeancier);
	echeancier.ajouter(new EvenementArriveeClientCaisses(frenceArriveeDesClients, ligneDeCaisses));
	int loop = 1;
	int simulationStepCounter = 0;
	assert (assert_flag = true);
	assert (ligneDeCaisses.debugCheckAssertOnly());
     	// Boucle principale du simulateur: 
	while ( ! echeancier.estVide() ) {
		if ( loop == 1 ) {
			System.out.print("----- Etat actuel du simulateur (nombre total de pas = ");
				System.out.print(simulationStepCounter);
				if ( assert_flag ) {
					System.out.print(", assert ON");
				} else {
					System.out.print(", assert OFF");
				}
				System.out.println(") -----");
				ligneDeCaisses.affiche();
				echeancier.affiche(ligneDeCaisses);
			}
			if (loop == 1) {
				System.out.println(
					"Taper \"Enter\" ou le nombre de pas de simulation que vous voulez réaliser");
				try {
					loop = Integer.parseInt(input.readLine());
					if (loop < 0) {
						loop = 1;
					}
				} catch (Exception e) {
					loop = 1;
				}
			} else {
				loop--;
			}
			Evenement evenement = echeancier.retourneEtEnlevePremier();
			evenement.traiter(ligneDeCaisses, echeancier);
			assert (ligneDeCaisses.debugCheckAssertOnly());
	    ligneDeCaisses.debugCheckAssertOnly(); // Pour ceux qui vont oublier de mettre les assert... à commenter pour aller plus vite !
	    assert echeancier.debugCheckAssertOnly();
	    echeancier.debugCheckAssertOnly();  // Pour ceux qui vont oublier de mettre les assert... à commenter pour aller plus vite !
	    simulationStepCounter++;
	}
	System.out.println("********** SIMULATION TERMINEE **********");
}
}
