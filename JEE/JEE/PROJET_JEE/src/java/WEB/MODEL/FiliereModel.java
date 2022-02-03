package WEB.MODEL;

import METIER.Filiere;
import java.util.ArrayList;
import java.util.List;


public class FiliereModel {
    
    List<Filiere> listFiliere = new ArrayList<Filiere>();
    
    Filiere filiere = new Filiere();

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public List<Filiere> getListFiliere() {
        return listFiliere;
    }

    public void setListFiliere(List<Filiere> listFiliere) {
        this.listFiliere = listFiliere;
    }
    
    
    
}
