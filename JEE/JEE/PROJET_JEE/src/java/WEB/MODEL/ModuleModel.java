
package WEB.MODEL;

import METIER.Filiere;
import METIER.Module;
import java.util.ArrayList;
import java.util.List;

public class ModuleModel {
    
    private Module module = new Module();
    private List<Module> listModules = new ArrayList<Module>();
    private String validationModule;
    private Long id_return;
    
    

    public Long getId_return() {
        return id_return;
    }

    public void setId_return(Long id_return) {
        this.id_return = id_return;
    }
    

    public List<Module> getListModules() {
        return listModules;
    }

    public void setListModules(List<Module> listModules) {
        this.listModules = listModules;
    }
    

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getValidationModule() {
        return validationModule;
    }

    public void setValidationModule(String validationModule) {
        this.validationModule = validationModule;
    }
    
//--------------------------------- a l'etape de transformer les IDs des personne et semetre et type et filiere en String -------------------------------------------    
    
    private Long id_module;
    private String nom_module;
    private String nom_responsable;
    private String nom_ens_cours;
    private String nom_ens_td;
    private String nom_ens_tp;
    private String nom_semetre;
    private String nom_type;
    private List<String> nom_filiere;

    public Long getId_module() {
        return id_module;
    }

    public void setId_module(Long id_module) {
        this.id_module = id_module;
    }
    
    
    
    List<ModuleModel> list_trasnformation = new ArrayList<ModuleModel>();

    public List<ModuleModel> getList_trasnformation() {
        return list_trasnformation;
    }

    public void setList_trasnformation(List<ModuleModel> list_trasnformation) {
        this.list_trasnformation = list_trasnformation;
    }

    public String getNom_module() {
        return nom_module;
    }

    public void setNom_module(String nom_module) {
        this.nom_module = nom_module;
    }

    public String getNom_responsable() {
        return nom_responsable;
    }

    public void setNom_responsable(String nom_responsable) {
        this.nom_responsable = nom_responsable;
    }

    public String getNom_ens_cours() {
        return nom_ens_cours;
    }

    public void setNom_ens_cours(String nom_ens_cours) {
        this.nom_ens_cours = nom_ens_cours;
    }

    public String getNom_ens_td() {
        return nom_ens_td;
    }

    public void setNom_ens_td(String nom_ens_td) {
        this.nom_ens_td = nom_ens_td;
    }

    public String getNom_ens_tp() {
        return nom_ens_tp;
    }

    public void setNom_ens_tp(String nom_ens_tp) {
        this.nom_ens_tp = nom_ens_tp;
    }

    public String getNom_semetre() {
        return nom_semetre;
    }

    public void setNom_semetre(String nom_semetre) {
        this.nom_semetre = nom_semetre;
    }

    public String getNom_type() {
        return nom_type;
    }

    public void setNom_type(String nom_type) {
        this.nom_type = nom_type;
    }

    public List<String> getNom_filiere() {
        return nom_filiere;
    }

    public void setNom_filiere(List<String> nom_filiere) {
        this.nom_filiere = nom_filiere;
    }

    
}
