import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO {

    // Méthode pours inscrire un nouveau membre
    
    public void inscrireMembre(Membre membre) {
        String sql = "INSERT INTO membre (nom, prenom, email, date_adhesion) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, membre.getNom());
            pstmt.setString(2, membre.getPrenom());
            pstmt.setString(3, membre.getEmail());
            pstmt.setDate(4, Date.valueOf(membre.getAdhesionDate()));
            
            pstmt.executeUpdate();
            System.out.println("Membre inscrit avec succès !");
            
        } catch (SQLException e) {
            System.err.println("Erreur d'inscription : " + e.getMessage());
        }
    }

    // Méthode pour rechercher un membre par nom

    public List<Membre> rechercherParNom(String nom) {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM membre WHERE nom ILIKE ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + nom + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Membre m = new Membre(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email"),
                    rs.getDate("date_adhesion").toLocalDate()
                );
                membres.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Erreur de recherche membre : " + e.getMessage());
        }
        return membres;
    }

    // Méthode pour supprimer un membre

    public void supprimerMembre(int id) {
        String sql = "DELETE FROM membre WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Membre supprimé.");
        } catch (SQLException e) {
            System.err.println("Erreur de suppression : " + e.getMessage());
        }
    }
}