
package WEB.MODEL;

import METIER.Personne;

import java.util.ArrayList;
import java.util.List;


public class PersonneModel {
    
    private String validationAjout;   
    Personne p = new Personne();
    private List<Personne> list = new ArrayList<Personne>();
    
    public String getValidationAjout() {
        return validationAjout;
    }

    public void setValidationAjout(String validationAjout) {
        this.validationAjout = validationAjout;
    }

    public List<Personne> getList() {
        return list;
    }

    public void setList(List<Personne> list) {
        this.list = list;
    }

    public Personne getP() {
        return p;
    }

    public void setP(Personne p) {
        this.p = p;
    }
    
    
}
