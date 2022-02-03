
package METIER;


public class Departement {
    
    private Long ID_DEP;
    private String nom_dep;
    private Long ID_CHEF;
    private Long ID_ADJ;

    public Long getID_DEP() {
        return ID_DEP;
    }

    public void setID_DEP(Long ID_DEP) {
        this.ID_DEP = ID_DEP;
    }

    public String getNom_dep() {
        return nom_dep;
    }

    public void setNom_dep(String nom_dep) {
        this.nom_dep = nom_dep;
    }

    public Long getID_CHEF() {
        return ID_CHEF;
    }

    public void setID_CHEF(Long ID_CHEF) {
        this.ID_CHEF = ID_CHEF;
    }

    public Long getID_ADJ() {
        return ID_ADJ;
    }

    public void setID_ADJ(Long ID_ADJ) {
        this.ID_ADJ = ID_ADJ;
    }
    
    
}
