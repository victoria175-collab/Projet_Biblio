import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    // Méthode pour ajouter un livre

    public void ajouterLivre(Livre livre) {
        String sql = "INSERT INTO livre (titre, auteur, categorie, isbn, nombre_exemplaires) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, livre.getTitre());
            pstmt.setString(2, livre.getAuteur());
            pstmt.setString(3, livre.getCategorie());
            pstmt.setString(4, livre.getIsbn());
            pstmt.setInt(5, livre.getNombreExemplaires());
            
            pstmt.executeUpdate();
            System.out.println("Livre ajouté avec succès !");
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du livre : " + e.getMessage());
        }
    }

    // Méthode pour rechercher des livres par titre, auteur, catégorie ou code IBSN 
    
    public List<Livre> rechercherLivres(String critere, String valeur) {
        List<Livre> livres = new ArrayList<>();
       
        String sql = "SELECT * FROM livre WHERE " + critere + " LIKE ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + valeur + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Livre l = new Livre(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getString("categorie"),
                    rs.getString("isbn"),
                    rs.getInt("nombre_exemplaires")
                );
                livres.add(l);
            }
        } catch (SQLException e) {
            System.err.println("Erreur de recherche : " + e.getMessage());
        }
        return livres;
    }
}