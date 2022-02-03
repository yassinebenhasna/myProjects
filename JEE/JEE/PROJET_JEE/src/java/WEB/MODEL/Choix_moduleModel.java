
package WEB.MODEL;

import METIER.Choix_module;
import java.util.ArrayList;
import java.util.List;

public class Choix_moduleModel {
 //************************** tri de user pour saisir ses propres choix de modules*************************************
    Choix_module listChoix = new Choix_module();
    private String validationChoix;

    public Choix_module getListChoix() {
        return listChoix;
    }

    public void setListChoix(Choix_module listChoix) {
        this.listChoix = listChoix;
    }

    public String getValidationChoix() {
        return validationChoix;
    }

    public void setValidationChoix(String validationChoix) {
        this.validationChoix = validationChoix;
    }

    
    
//    ******************** trait admin pour afficher la liste des chois des users **********************************
    
    
    List<Choix_module> list = new ArrayList<Choix_module>();

    public List<Choix_module> getList() {
        return list;
    }

    public void setList(List<Choix_module> list) {
        this.list = list;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //*********************************************************************
    
    private Long id_ens;
    private String nom_ens;
    private String ch_module1;
    private String ch_module2;
    private String ch_module3;

    public Long getId_ens() {
        return id_ens;
    }

    public void setId_ens(Long id_ens) {
        this.id_ens = id_ens;
    }

    

    public String getNom_ens() {
        return nom_ens;
    }

    public void setNom_ens(String nom_ens) {
        this.nom_ens = nom_ens;
    }

    public String getCh_module1() {
        return ch_module1;
    }

    public void setCh_module1(String ch_module1) {
        this.ch_module1 = ch_module1;
    }

    public String getCh_module2() {
        return ch_module2;
    }

    public void setCh_module2(String ch_module2) {
        this.ch_module2 = ch_module2;
    }

    public String getCh_module3() {
        return ch_module3;
    }

    public void setCh_module3(String ch_module3) {
        this.ch_module3 = ch_module3;
    }

    
    
}
