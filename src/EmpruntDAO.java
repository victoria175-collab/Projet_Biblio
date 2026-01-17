import java.sql.*;
import java.time.LocalDate;

public class EmpruntDAO {

    // Méthode pou enregistrer un emprunt

    public void enregistrerEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO emprunt (membre_id, livre_id, date_emprunt, date_retour_prevue) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, emprunt.getMembreId());
            pstmt.setInt(2, emprunt.getLivreId());
            pstmt.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            pstmt.setDate(4, Date.valueOf(emprunt.getDateRetourPrevue()));
            
            pstmt.executeUpdate();
            System.out.println("Emprunt enregistré avec succès.");
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'emprunt : " + e.getMessage());
        }
    }

    // Méthode pour gérer le retour d'un livre

    public void enregistrerRetour(int idEmprunt, LocalDate dateRetourEffective) {
        String sql = "UPDATE emprunt SET date_retour_effective = ? WHERE id_emprunt = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, Date.valueOf(dateRetourEffective));
            pstmt.setInt(2, idEmprunt);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Retour validé.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors du retour : " + e.getMessage());
        }
    }
}