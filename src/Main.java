import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LivreDAO livreDAO = new LivreDAO();
    private static final MembreDAO membreDAO = new MembreDAO();
    private static final EmpruntDAO empruntDAO = new EmpruntDAO();

    public static void main(String[] args) {
        System.out.println("SYSTÈME DE GESTION DE BIBLIOTHÈQUE");
        boolean continuer = true;

        while (continuer) {
            afficherMenu();
            int choix = scanner.nextInt();
            scanner.nextLine(); 
            switch (choix) {
                case 1:
                    ajouterNouveauLivre();
                    break;
                case 2:
                    rechercherLivres();
                    break;
                case 3:
                    inscrireNouveauMembre();
                    break;
                case 4:
                    enregistrerEmprunt();
                    break;
                case 5:
                    gererRetour();
                    break;
                case 0:
                    continuer = false;
                    System.out.println("Fermeture de l'application...");
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Rechercher un livre");
        System.out.println("3. Inscrire un membre");
        System.out.println("4. Enregistrer un emprunt");
        System.out.println("5. Gérer un retour et pénalités");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    private static void ajouterNouveauLivre() {
        System.out.print("Titre : "); String titre = scanner.nextLine();
        System.out.print("Auteur : "); String auteur = scanner.nextLine();
        System.out.print("Catégorie : "); String cat = scanner.nextLine();
        System.out.print("ISBN : "); String isbn = scanner.nextLine();
        System.out.print("Nombre d'exemplaires : "); int nb = scanner.nextInt();

        Livre livre = new Livre(0, titre, auteur, cat, isbn, nb);
        livreDAO.ajouterLivre(livre);
    }

    private static void rechercherLivres() {
        System.out.println("Rechercher par : 1. Titre | 2. Auteur | 3. Catégorie");
        int c = scanner.nextInt(); scanner.nextLine();
        String critere = (c == 1) ? "titre" : (c == 2) ? "auteur" : "categorie";
        System.out.print("Valeur recherchée : ");
        String valeur = scanner.nextLine();

        List<Livre> resultats = livreDAO.rechercherLivres(critere, valeur);
        for (Livre l : resultats) {
            l.afficherDetails();
        }
    }

    private static void inscrireNouveauMembre() {
        System.out.print("Nom : "); String nom = scanner.nextLine();
        System.out.print("Prénom : "); String prenom = scanner.nextLine();
        System.out.print("Email : "); String email = scanner.nextLine();

        Membre membre = new Membre(0, nom, prenom, email, LocalDate.now());
        membreDAO.inscrireMembre(membre);
    }

    private static void enregistrerEmprunt() {
        System.out.print("ID du membre : "); int mId = scanner.nextInt();
        System.out.print("ID du livre : "); int lId = scanner.nextInt();
        
        LocalDate dateEmprunt = LocalDate.now();
        LocalDate dateRetourPrevue = dateEmprunt.plusDays(14); 

        Emprunt emprunt = new Emprunt(0, mId, lId, dateEmprunt, dateRetourPrevue);
        empruntDAO.enregistrerEmprunt(emprunt);
        System.out.println("Date de retour prévue : " + dateRetourPrevue);
    }

    private static void gererRetour() {
        System.out.print("ID de l'emprunt : "); int eId = scanner.nextInt();
        LocalDate dateRetourEffective = LocalDate.now();
        
        empruntDAO.enregistrerRetour(eId, dateRetourEffective);
    }
}