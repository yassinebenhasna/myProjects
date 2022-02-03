
package WEB.MODEL;

import METIER.Personne;
import java.util.ArrayList;
import java.util.List;


public class Infos_perso_module {// ce personne p et les types denseignement pour chaque modele
    
    Personne p = new Personne();
    
    private String module_cours;
    private String module_TD;
    private String module_TP;
    private String fct;
    private String grade;
    
    List<Infos_perso_module> list_info_mod_ens = new ArrayList<Infos_perso_module>();

    public String getFct() {
        return fct;
    }

    public void setFct(String fct) {
        this.fct = fct;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Personne getP() {
        return p;
    }

    public void setP(Personne p) {
        this.p = p;
    }

    public String getModule_cours() {
        return module_cours;
    }

    public void setModule_cours(String module_cours) {
        this.module_cours = module_cours;
    }

    public String getModule_TD() {
        return module_TD;
    }

    public void setModule_TD(String module_TD) {
        this.module_TD = module_TD;
    }

    public String getModule_TP() {
        return module_TP;
    }

    public void setModule_TP(String module_TP) {
        this.module_TP = module_TP;
    }

    public List<Infos_perso_module> getList_info_mod_ens() {
        return list_info_mod_ens;
    }

    public void setList_info_mod_ens(List<Infos_perso_module> list_info_mod_ens) {
        this.list_info_mod_ens = list_info_mod_ens;
    }
    
    
    
}
