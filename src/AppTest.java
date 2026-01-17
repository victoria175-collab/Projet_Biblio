import java.time.LocalDate;
import java.util.List;

public class AppTest {

    public static void main(String[] args) {
        System.out.println(" DÉBUT DES TESTS TECHNIQUES ");

        testConnexion();
        testAjoutEtRechercheLivre();
        testCalculPenalites();

        System.out.println(" FIN DES TESTS ");
    }

    // 1. Test de Connexion

    private static void testConnexion() {
        System.out.print("Test Connexion : ");
        if (DBConnection.getConnection() != null) {
            System.out.println("SUCCÈS");
        } else {
            System.out.println("ÉCHEC");
        }
    }

    // 2. Test DAO Livre

    private static void testAjoutEtRechercheLivre() {
        System.out.print("Test Insertion Livre : ");
        LivreDAO dao = new LivreDAO();
        Livre testLivre = new Livre(0, "Test Unit", "Auteur Test", "Tech", "000-000", 5);
        
        dao.ajouterLivre(testLivre);
        List<Livre> resultats = dao.rechercherLivres("titre", "Test Unit");
        
        if (!resultats.isEmpty()) {
            System.out.println("SUCCÈS (Livre trouvé)");
        } else {
            System.out.println("ÉCHEC (Livre non trouvé)");
        }
    }

    // 3. Test Logique de Pénalité 
    private static void testCalculPenalites() {
        System.out.print("Test Calcul Pénalités (3 jours de retard) : ");
        
        LocalDate aujourdhui = LocalDate.now();
        LocalDate prevue = aujourdhui.minusDays(3); // Date de retour était il y a 3 jours
        
        Emprunt empruntTest = new Emprunt(1, 1, 1, aujourdhui.minusDays(10), prevue);
        empruntTest.setDateRetourEffective(aujourdhui); // Rendu aujourd'hui
        
        double penalite = empruntTest.calculerPenalites();
        
        if (penalite == 300.0) {
            System.out.println("SUCCÈS (300.0 F CFA)");
        } else {
            System.out.println("ÉCHEC (Attendu 300.0, reçu : " + penalite + ")");
        }
    }
}