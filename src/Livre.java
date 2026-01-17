public class Livre {
    // Attributs privés 
    private int id;
    private String titre;
    private String auteur;
    private String categorie;
    private String isbn; // Ajouté pour la rigueur
    private int nombreExemplaires;

    // Constructeur 
    public Livre(int id, String titre, String auteur, String categorie, String isbn, int nombreExemplaires) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.isbn = isbn;
        this.nombreExemplaires = nombreExemplaires;
    }

    // Getters et Setters 
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getNombreExemplaires() { return nombreExemplaires; }
    public void setNombreExemplaires(int nombreExemplaires) { this.nombreExemplaires = nombreExemplaires; }

    // Méthode pour afficher les détails 
    public void afficherDetails() {
        System.out.println("Livre ID: " + id);
        System.out.println("Titre: " + titre);
        System.out.println("Auteur: " + auteur);
        System.out.println("Catégorie: " + categorie);
        System.out.println("ISBN: " + isbn);
        System.out.println("Exemplaires: " + nombreExemplaires);
        System.out.println("-----------------------------");
    }
}