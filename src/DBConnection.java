import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    // Paramètres de connexion
    private static final String URL = "jdbc:postgresql://localhost:5432/gestion_bibliotheque";
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "admin"; 

    
    //  Établir et retourner une connexion à la base de données.
     
    public static Connection getConnection() {
        Connection connection = null;
        try {
            
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à PostgreSQL réussie.");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Driver PostgreSQL non trouvé. Vérifiez le dossier lib.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur de connexion à la base de données.");
            e.printStackTrace();
        }
        return connection;
    }
}