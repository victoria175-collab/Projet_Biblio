import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprunt {
    // Attributs privés

    private int idEmprunt;
    private int membreId;
    private int livreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    // Constructeur

    public Emprunt(int idEmprunt, int membreId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue) {
        this.idEmprunt = idEmprunt;
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
    }

    // Getters et Setters

    public int getIdEmprunt() { return idEmprunt; }
    public int getMembreId() { return membreId; }
    public int getLivreId() { return livreId; }
    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public LocalDate getDateRetourPrevue() { return dateRetourPrevue; }
    
    public LocalDate getDateRetourEffective() { return dateRetourEffective; }
    public void setDateRetourEffective(LocalDate dateRetourEffective) { 
        this.dateRetourEffective = dateRetourEffective; 
    }

    
    //   Calcule les pénalités : 100 F CFA par jour de retard.
    //   Le retard est calculé entre dateRetourPrevue et dateRetourEffective.
     
    public double calculerPenalites() {
        if (dateRetourEffective == null || !dateRetourEffective.isAfter(dateRetourPrevue)) {
            return 0.0;
        }
        long joursDeRetard = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetourEffective);
        return joursDeRetard * 100.0;
    }
}