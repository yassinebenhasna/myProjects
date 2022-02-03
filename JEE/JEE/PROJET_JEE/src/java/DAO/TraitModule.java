
package DAO;

import METIER.Filiere;
import METIER.Module;
import METIER.Personne;
import WEB.MODEL.Infos_perso_module;
import WEB.MODEL.ModuleModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TraitModule {
    //********************************************* CNX BD *************************************************************************
    
                                                            private Connection connexion;

                                                            private void loadDatabase() {
                                                                // Chargement du driver
                                                                try {
                                                                    Class.forName("com.mysql.jdbc.Driver");
                                                                } catch (ClassNotFoundException e) {
                                                                }

                                                                try {
                                                                    connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet1", "root", "");

                                                                } catch (SQLException e) {
                                                                    e.printStackTrace();

                                                                }
                                                            }
    
//**************************************mthd Ajoutr des modules**********************************************************************************

        public Long ajouterModule(Module m){
            
            Long id = null;
                        
            // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;
            ResultSet resultat = null;

                try {

                    prestat = (PreparedStatement) connexion.prepareStatement("INSERT INTO module(nom_mod,ID_RESP,ID_SEM,ID_TYPE) VALUES (?,?,?,?);");
                    prestat.setString(1, m.getNom_mod());
                    prestat.setLong(2, m.getID_RESP());
                    prestat.setLong(3, m.getID_SEM());
                    prestat.setLong(4, m.getID_TYPE());
                    
                    
                    prestat.executeUpdate(); 
                    
                    
                    // retourner ID de ce module ajoutée
                    
                        prestat = (PreparedStatement) connexion.prepareStatement("SELECT ID_MODULE FROM module WHERE nom_mod=?");
                        prestat.setString(1, m.getNom_mod());
                        
                        resultat = prestat.executeQuery();
                        
                        if(resultat.next()){
                            
                            id = resultat.getLong("ID_MODULE");
                            
                        }  

                } catch (SQLException ex) {

                } finally {
                    // Fermeture de la connexion il faut les fermer en ordre de plus recent vers plus encien
                    try {
                        if (prestat != null)
                            prestat.close();
                        if (connexion != null) {
                            connexion.close();
                        }

                    } catch (SQLException ignore) {
                    }
                }
           return id;     
        }                  
        
    //**************************************mthd afficher la liste des modules by IDs**********************************************************************************
        
        public List<Module> listModules(String mc){
            
            List<Module> listModules = new ArrayList<Module>();
            
             // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;
            ResultSet resultat = null;
            try {
                
                if(mc == null || mc.equals("") || mc.equals("allModules") ){


                        prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM module  ");

                        resultat = prestat.executeQuery();
                        
                        while(resultat.next()){
                        
                            Module m = new Module ();
                                m.setNom_mod(resultat.getString("nom_mod"));
                                m.setID_MODULE(resultat.getLong("ID_MODULE"));
                                m.setID_RESP(resultat.getLong("ID_RESP"));
                                m.setID_ENS_CRS(resultat.getLong("ID_ENS_CRS"));
                                m.setID_ENS_TP(resultat.getLong("ID_ENS_TP"));
                                m.setID_ENS_TD(resultat.getLong("ID_ENS_TD"));
                                m.setID_SEM(resultat.getLong("ID_SEM"));
                                m.setID_TYPE(resultat.getLong("ID_TYPE"));
                                
                            listModules.add(m);
                        }   
                }else{
                
                        prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM module WHERE nom_mod LIKE ? ");
                        prestat.setString(1, "%"+mc+"%");
                        resultat = prestat.executeQuery();
                        
                        while(resultat.next()){
                        
                            Module m = new Module ();
                                m.setNom_mod(resultat.getString("nom_mod"));
                                m.setID_MODULE(resultat.getLong("ID_MODULE"));
                                m.setID_RESP(resultat.getLong("ID_RESP"));
                                m.setID_ENS_CRS(resultat.getLong("ID_ENS_CRS"));
                                m.setID_ENS_TP(resultat.getLong("ID_ENS_TP"));
                                m.setID_ENS_TD(resultat.getLong("ID_ENS_TD"));
                                m.setID_SEM(resultat.getLong("ID_SEM"));
                                m.setID_TYPE(resultat.getLong("ID_TYPE"));
                            listModules.add(m);
                            
                        }

                }
                
            }catch (SQLException ex) {
            
            }finally{
                try {   
                        if(resultat != null)
                            resultat.close();
                        if (prestat != null)
                            prestat.close();
                        if (connexion != null) {
                            connexion.close();
                        }

                    } catch (SQLException ignore) {
                        
                    }            
            }
            
        
        
        
        return listModules;
        }
   
//**************************************mthd valider les modules apres choix des enseignats **********************************************************************************
    public String validerModule(Module m){    
        // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;

            String msg = ""; 

                try {

                    prestat = (PreparedStatement) connexion.prepareStatement("INSERT INTO module(nom_mod,ID_RESP,ID_SEM,ID_TYPE) VALUES (?,?,?,?);");
                    prestat.setString(1, m.getNom_mod());
                    prestat.setLong(2, m.getID_RESP());
                    prestat.setLong(3, m.getID_SEM());
                    prestat.setLong(4, m.getID_TYPE());
                    
                    
                    prestat.executeUpdate();  

                    msg = "ajout succes";    

                } catch (SQLException ex) {
                    msg = "ajout failed";

                } finally {
                    // Fermeture de la connexion il faut les fermer en ordre de plus recent vers plus encien
                    try {
                        if (prestat != null)
                            prestat.close();
                        if (connexion != null) {
                            connexion.close();
                        }

                    } catch (SQLException ignore) {
                    }
                }
                return msg;
        }                  
      

//**************************************mthd affecter type d'enseignements au modules **********************************************************************************

        public String affecterModule(Module m){
            // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;
            ResultSet resultat = null;
            String msg = ""; 
            
                String nom_mod = m.getNom_mod();
                                
                try {
                    if(m.getID_ENS_CRS() != null){
                    // modifier ses choix precedants avant de stocker la derniere modification
                        prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM module WHERE nom_mod=?");
                        prestat.setString(1, m.getNom_mod());
                        resultat = prestat.executeQuery();
                        
                        if(resultat.next()){
                            if(resultat.getLong("ID_ENS_CRS") == m.getID_ENS_CRS()){
                                prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_CRS=null WHERE nom_mod=?");
                                prestat.setString(1, m.getNom_mod());
                                prestat.executeUpdate();
                            }
                            if(resultat.getLong("ID_ENS_TD") == m.getID_ENS_CRS()){
                                prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_TD=null WHERE nom_mod=?");
                                prestat.setString(1, m.getNom_mod());
                                prestat.executeUpdate();
                            }
                            if(resultat.getLong("ID_ENS_TP") == m.getID_ENS_CRS()){
                                prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_TP=null WHERE nom_mod=?");
                                prestat.setString(1, m.getNom_mod());
                                prestat.executeUpdate();
                            }
                        }
                        
                    // inserer maintenant la derniere modification    
                        
                        prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_CRS=? WHERE nom_mod=?");
                        prestat.setLong(1, m.getID_ENS_CRS());
                        prestat.setString(2, m.getNom_mod());
                        
                    }else if(m.getID_ENS_TD() != null){ 
                        
                        // modifier ses choix precedants avant de stocker la derniere modification
                        prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM module WHERE nom_mod=?");
                        prestat.setString(1, m.getNom_mod());
                        resultat = prestat.executeQuery();
                        
                        if(resultat.next()){
                            if(resultat.getLong("ID_ENS_CRS") == m.getID_ENS_TD()){
                                prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_CRS=null WHERE nom_mod=?");
                                prestat.setString(1, m.getNom_mod());
                                prestat.executeUpdate();
                            }
                            if(resultat.getLong("ID_ENS_TD") == m.getID_ENS_TD()){
                                prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_TD=null WHERE nom_mod=?");
                                prestat.setString(1, m.getNom_mod());
                                prestat.executeUpdate();
                            }
                            if(resultat.getLong("ID_ENS_TP") == m.getID_ENS_TD()){
                                prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_TP=null WHERE nom_mod=?");
                                prestat.setString(1, m.getNom_mod());
                                prestat.executeUpdate();
                            }
                        }
                        
                    // inserer maintenant la derniere modification
                    
                        prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_TD=? WHERE nom_mod=?");
                        prestat.setLong(1, m.getID_ENS_TD());
                        prestat.setString(2, m.getNom_mod());
                        
                    }else if(m.getID_ENS_TP() != null){ 
                        
                        // modifier ses choix precedants avant de stocker la derniere modification
                        prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM module WHERE nom_mod=?");
                        prestat.setString(1, m.getNom_mod());
                        resultat = prestat.executeQuery();
                        
                        if(resultat.next()){
                            if(resultat.getLong("ID_ENS_CRS") == m.getID_ENS_TP()){
                                prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_CRS=null WHERE nom_mod=?");
                                prestat.setString(1, m.getNom_mod());
                                prestat.executeUpdate();
                            }
                            if(resultat.getLong("ID_ENS_TD") == m.getID_ENS_TP()){
                                prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_TD=null WHERE nom_mod=?");
                                prestat.setString(1, m.getNom_mod());
                                prestat.executeUpdate();
                            }
                            if(resultat.getLong("ID_ENS_TP") == m.getID_ENS_TP()){
                                prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_TP=null WHERE nom_mod=?");
                                prestat.setString(1, m.getNom_mod());
                                prestat.executeUpdate();
                            }
                        }
                        
                    // inserer maintenant la derniere modification
                    
                        prestat = (PreparedStatement) connexion.prepareStatement("UPDATE module SET ID_ENS_TP=? WHERE nom_mod=?");
                        prestat.setLong(1, m.getID_ENS_TP());
                        prestat.setString(2, m.getNom_mod());
                        
                    }else{
                    }
                    
                    prestat.executeUpdate();  

                    msg = "ajout succes";    

                } catch (SQLException ex) {
                    msg = "ajout failed";

                } finally {
                    // Fermeture de la connexion il faut les fermer en ordre de plus recent vers plus encien
                    try {
                        if (prestat != null)
                            prestat.close();
                        if (connexion != null) {
                            connexion.close();
                        }

                    } catch (SQLException ignore) {
                    }
                }
                return msg;
        }  
        

//****************************************************** mthds de recuperer la liste des personnes et selectionner chaque type d'enseignements *****************************************
        
        public List<Infos_perso_module> list_mod_per(List<Personne> list_perso){
        
            List<Infos_perso_module> list_pers_mod = new ArrayList<Infos_perso_module>();
        
        // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat1 = null;  PreparedStatement prestat2 = null;  PreparedStatement prestat3 = null;  PreparedStatement prestat4 = null;  PreparedStatement prestat5 = null;
            ResultSet resultat1 = null;         ResultSet resultat2 = null;         ResultSet resultat3 = null;         ResultSet resultat4 = null;         ResultSet resultat5 = null;
            
            for(int i = 0;i<list_perso.size(); i++){
            
                try{
                    
                    prestat1 = (PreparedStatement)connexion.prepareStatement("SELECT nom_mod FROM module WHERE ID_ENS_CRS=?");
                    prestat1.setLong(1, list_perso.get(i).getId_pers());
                    
                    resultat1 = prestat1.executeQuery();
                    
                    prestat2 = (PreparedStatement)connexion.prepareStatement("SELECT nom_mod FROM module WHERE ID_ENS_TD=?");
                    prestat2.setLong(1, list_perso.get(i).getId_pers());
                    
                    resultat2 = prestat2.executeQuery();
                    
                    prestat3 = (PreparedStatement)connexion.prepareStatement("SELECT nom_mod FROM module WHERE ID_ENS_TP=?");
                    prestat3.setLong(1, list_perso.get(i).getId_pers());
                    
                    resultat3 = prestat3.executeQuery();
                    
                    prestat4 = (PreparedStatement)connexion.prepareStatement("SELECT nom_fct FROM fonction WHERE ID_fct=?");
                    prestat4.setLong(1, list_perso.get(i).getId_fct());
                    
                    resultat4 = prestat4.executeQuery();
                    
                    prestat5 = (PreparedStatement)connexion.prepareStatement("SELECT NOM_GRADE FROM grade WHERE ID_GRADE=?");
                    prestat5.setLong(1, list_perso.get(i).getId_grade());
                    
                    resultat5 = prestat5.executeQuery();
                    
                    Infos_perso_module per_mod = new Infos_perso_module();
                        
                        per_mod.setP(list_perso.get(i));
                    
                    if(resultat1.next()){
                        per_mod.setModule_cours(resultat1.getString("nom_mod"));
                    }
                    if(resultat2.next()){
                        per_mod.setModule_TD(resultat2.getString("nom_mod"));
                    }
                    if(resultat3.next()){
                        per_mod.setModule_TP(resultat3.getString("nom_mod"));
                    }
                    if(resultat4.next()){
                        per_mod.setFct(resultat4.getString("nom_fct"));
                    }
                    if(resultat5.next()){
                        per_mod.setGrade(resultat5.getString("NOM_GRADE"));
                    }
                
                    list_pers_mod.add(per_mod);
                
                
                }catch(SQLException e){
                
                }

            
            }
        // Fermeture de la connexion il faut les fermer en ordre de plus recent vers plus encien
            try {
                if (resultat1 != null)
                    resultat1.close();
                if (resultat2 != null)
                    resultat2.close();
                if (resultat3 != null)
                    resultat3.close();
                if (resultat4 != null)
                    resultat4.close();
                if (resultat5 != null)
                    resultat5.close();
                
                if (prestat1 != null)
                    prestat1.close();
                if (prestat2 != null)
                    prestat2.close();
                if (prestat3 != null)
                    prestat3.close();
                if (prestat4 != null)
                    prestat4.close();
                if (prestat5 != null)
                    prestat5.close();
                
                if (connexion != null) {
                    connexion.close();
                }

            } catch (SQLException ignore) {
            }
        

        
            return list_pers_mod;
        }
        
        
//****************************************************** mthds de recuperer tous les infos sur un module et transformer ces IDs en nom *****************************************
        
        public List<ModuleModel> transformer_IDs(List<Module> listModule){
            List<ModuleModel> list_apres_transf = new ArrayList<ModuleModel>();
            
            // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat1 = null;  PreparedStatement prestat2 = null;  PreparedStatement prestat3 = null;  PreparedStatement prestat4 = null;
            ResultSet resultat1 = null;         ResultSet resultat2 = null;         ResultSet resultat3 = null;         ResultSet resultat4 = null;
            
            PreparedStatement prestat5 = null;  PreparedStatement prestat6 = null;  PreparedStatement prestat7 = null;  PreparedStatement prestat8 = null;
            ResultSet resultat5 = null;         ResultSet resultat6 = null;         ResultSet resultat7 = null;         ResultSet resultat8 = null;
            
            for(int i = 0;i<listModule.size(); i++){
            
                try{
                    
                    prestat1 = (PreparedStatement)connexion.prepareStatement("SELECT nom FROM personne WHERE ID_PERS=?");
                    prestat1.setLong(1, listModule.get(i).getID_RESP());
                    
                    resultat1 = prestat1.executeQuery();
                    
                    prestat2 = (PreparedStatement)connexion.prepareStatement("SELECT nom FROM personne WHERE ID_PERS=?");
                    prestat2.setLong(1, listModule.get(i).getID_ENS_CRS());
                    
                    resultat2 = prestat2.executeQuery();
                    
                    prestat3 = (PreparedStatement)connexion.prepareStatement("SELECT nom FROM personne WHERE ID_PERS=?");
                    prestat3.setLong(1, listModule.get(i).getID_ENS_TD());
                    
                    resultat3 = prestat3.executeQuery();

                    prestat4 = (PreparedStatement)connexion.prepareStatement("SELECT nom FROM personne WHERE ID_PERS=?");
                    prestat4.setLong(1, listModule.get(i).getID_ENS_TP());
                    
                    resultat4 = prestat4.executeQuery();
                    
                    prestat5 = (PreparedStatement)connexion.prepareStatement("SELECT nom_sem FROM semestre WHERE ID_SEM=?");
                    prestat5.setLong(1, listModule.get(i).getID_SEM());
                    
                    resultat5 = prestat5.executeQuery();
                    
                    prestat6 = (PreparedStatement)connexion.prepareStatement("SELECT type_module FROM type WHERE ID_TYPE=?");
                    prestat6.setLong(1, listModule.get(i).getID_TYPE());
                    
                    resultat6 = prestat6.executeQuery();
    
    
                    ModuleModel info_module = new ModuleModel();
                        
                        info_module.setNom_module(listModule.get(i).getNom_mod());
                    
                    if(resultat1.next()){
                        info_module.setNom_responsable(resultat1.getString("nom"));
                    }
                    if(resultat2.next()){
                        info_module.setNom_ens_cours(resultat2.getString("nom"));
                    }
                    if(resultat3.next()){
                        info_module.setNom_ens_td(resultat3.getString("nom"));
                    }
                    if(resultat4.next()){
                        info_module.setNom_ens_tp(resultat4.getString("nom"));
                    }
                    if(resultat5.next()){
                        info_module.setNom_semetre(resultat5.getString("nom_sem"));
                    }
                    if(resultat6.next()){
                        info_module.setNom_type(resultat6.getString("type_module"));
                    }


//recuperer d'abord les ID de la filiere qui contient ce module 
                    prestat7 = (PreparedStatement)connexion.prepareStatement("SELECT ID_FIL FROM mod_fil WHERE ID_MODULE=?");
                    prestat7.setLong(1, listModule.get(i).getID_MODULE());
                    
                    resultat7 = prestat7.executeQuery();
    
                    List<Long> id_filieres = new ArrayList<Long>();
                    
                    while(resultat7.next()){
                        id_filieres.add(resultat7.getLong("ID_FIL"));
                    }                    
                    
    //recuperer maintenant les filieres de ce module a partir de ces IDs
                    List<String> nom_filiers = new ArrayList<String>();
                    for(int j = 0;j<id_filieres.size(); j++){
                                                                  
                        prestat8 = (PreparedStatement)connexion.prepareStatement("SELECT fil FROM filiere WHERE ID_FIL=?");
                        Long id = id_filieres.get(j);
                        prestat8.setLong(1, id);

                        resultat8 = prestat8.executeQuery();
                                     
                        if(resultat8.next()){
                            nom_filiers.add(resultat8.getString("fil"));
                        }

                    }
                    
                    info_module.setId_module(listModule.get(i).getID_MODULE());
                    info_module.setNom_filiere(nom_filiers);
            
                    list_apres_transf.add(info_module);
                
                
                }catch(SQLException e){
                
                }

            
            }
        // Fermeture de la connexion il faut les fermer en ordre de plus recent vers plus encien
            try {
                
                if (resultat1 != null)
                    resultat1.close();
                if (resultat2 != null)
                    resultat2.close();
                if (resultat3 != null)
                    resultat3.close();
                if (resultat4 != null)
                    resultat4.close();
                if (resultat5 != null)
                    resultat5.close();
                if (resultat6 != null)
                    resultat6.close();
                if (resultat7 != null)
                    resultat7.close();
                if (resultat8 != null)
                    resultat8.close();
                
                if (prestat1 != null)
                    prestat1.close();
                if (prestat2 != null)
                    prestat2.close();
                if (prestat3 != null)
                    prestat3.close();
                if (prestat4 != null)
                    prestat4.close();
                if (prestat5 != null)
                    prestat5.close();
                if (prestat6 != null)
                    prestat6.close();
                if (prestat7 != null)
                    prestat7.close();
                if (prestat8 != null)
                    prestat8.close();
                
                
                if (connexion != null) {
                    connexion.close();
                }

            } catch (SQLException ignore) {
            }
        
        
        return list_apres_transf;
        }
     
//****************************************************** mthds supprimer un module *****************************************
       
    public void supprimer(Long mc){
        // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;
            
            try{
                    
                    prestat = (PreparedStatement)connexion.prepareStatement("DELETE FROM module WHERE ID_MODULE=?");
                    prestat.setLong(1, mc);
                    
                    prestat.executeUpdate();
            
            }catch(SQLException e){}
    
            try{
                if(prestat != null)
                    prestat.close();
                if(connexion != null)
                    connexion.close();
            }catch(SQLException e){}
    }

}