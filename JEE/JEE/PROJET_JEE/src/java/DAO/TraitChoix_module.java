
package DAO;

import METIER.Choix_module;
import METIER.Module;
import METIER.Personne;
import WEB.MODEL.Choix_moduleModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TraitChoix_module {
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
    
//**************************************mthd enrigistrer les choix des modules**********************************************************************************

        public String addChoixModule(Choix_module choix_m){
            // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;
            ResultSet resultat = null;
            String msg = ""; 

                try {
                    
                    prestat = (PreparedStatement) connexion.prepareStatement("SELECT ID_PERS FROM choix_module WHERE ID_PERS=?");
                    
                    prestat.setLong(1, choix_m.getID_PERS());
                    
                    resultat = prestat.executeQuery();
                    
                    if(resultat.next()){
                    
                        prestat = (PreparedStatement) connexion.prepareStatement("UPDATE choix_module SET ID_MODULE1=?,ID_MODULE2=?,ID_MODULE3=? WHERE ID_PERS=? ");

                        prestat.setLong(4, choix_m.getID_PERS());
                        prestat.setLong(1, choix_m.getID_MODULE1());
                        prestat.setLong(2, choix_m.getID_MODULE2());
                        prestat.setLong(3, choix_m.getID_MODULE3());


                        prestat.executeUpdate();
                    
                    }else{
                    

                        prestat = (PreparedStatement) connexion.prepareStatement("INSERT INTO choix_module(ID_PERS,ID_MODULE1,ID_MODULE2,ID_MODULE3) VALUES (?,?,?,?);");

                        prestat.setLong(1, choix_m.getID_PERS());
                        prestat.setLong(2, choix_m.getID_MODULE1());
                        prestat.setLong(3, choix_m.getID_MODULE2());
                        prestat.setLong(4, choix_m.getID_MODULE3());


                        prestat.executeUpdate();
                    }

                    msg = "votre validation a été bien effectué";    

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
               
        
    //**************************************mthd Afficher list des choix By Noms **********************************************************************************

    public List<Choix_moduleModel> infoByIDs(List<Choix_module> c){
        
        List<Choix_moduleModel> listChoix_ByNom = new ArrayList<Choix_moduleModel>();
        
        // lancer cnx a BD
    loadDatabase();
    PreparedStatement prestat1 = null;
    PreparedStatement prestat2 = null;
    PreparedStatement prestat3 = null;
    PreparedStatement prestat4 = null;
    ResultSet resultat1 = null;
    ResultSet resultat2 = null;
    ResultSet resultat3 = null;
    ResultSet resultat4 = null;
    
    for(int i = 0; i<c.size(); i++ ){
    
          try {
                          
            prestat1 = (PreparedStatement) connexion.prepareStatement("SELECT nom,ID_PERS FROM personne WHERE ID_PERS=?");
                prestat1.setLong(1, c.get(i).getID_PERS());
                
            prestat2 = (PreparedStatement) connexion.prepareStatement("SELECT nom_mod FROM module WHERE ID_MODULE=?");
                prestat2.setLong(1, c.get(i).getID_MODULE1());
            
            prestat3 = (PreparedStatement) connexion.prepareStatement("SELECT nom_mod FROM module WHERE ID_MODULE=?");
                prestat3.setLong(1, c.get(i).getID_MODULE2()); 
                
            prestat4 = (PreparedStatement) connexion.prepareStatement("SELECT nom_mod FROM module WHERE ID_MODULE=?");
                prestat4.setLong(1, c.get(i).getID_MODULE3());

            resultat1 = prestat1.executeQuery();
            resultat2 = prestat2.executeQuery();
            resultat3 = prestat3.executeQuery();
            resultat4 = prestat4.executeQuery();
            
            Choix_moduleModel info = new Choix_moduleModel();
            
            if(resultat1.next())
                info.setNom_ens(resultat1.getString("nom"));
                info.setId_ens(resultat1.getLong("ID_PERS"));
            
            if(resultat2.next())
                info.setCh_module1(resultat2.getString("nom_mod"));
            
            if(resultat3.next())
                info.setCh_module2(resultat3.getString("nom_mod"));
            
            if(resultat4.next())
                info.setCh_module3(resultat4.getString("nom_mod"));
            
            listChoix_ByNom.add(info);
            
        }catch (SQLException ex) {

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
                if (prestat1 != null)
                    prestat1.close();
                if (prestat2 != null)
                    prestat2.close();
                if (prestat3 != null)
                    prestat3.close();
                if (prestat4 != null)
                    prestat4.close();
                if (connexion != null) {
                    connexion.close();
                }

            } catch (SQLException ignore) {
            }
        
        
        return listChoix_ByNom;
    
    }
       
        

   //**************************************mthd Afficher list des choix By ID**********************************************************************************

    public List<Choix_module> listChoix(){
        List<Choix_module> listChoix = new ArrayList<Choix_module>();
        
        // lancer cnx a BD
    loadDatabase();
    PreparedStatement prestat = null;
    ResultSet resultat = null;
            
        try {
            
            prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM choix_module");
            
            resultat = prestat.executeQuery();  
            
            while(resultat.next()){
            
                Choix_module ch = new Choix_module();
                
                    ch.setID_PERS(resultat.getLong("ID_PERS"));
                    ch.setID_MODULE1(resultat.getLong("ID_MODULE1"));
                    ch.setID_MODULE2(resultat.getLong("ID_MODULE2"));
                    ch.setID_MODULE3(resultat.getLong("ID_MODULE3"));
                    
                listChoix.add(ch);
            }
            
        } catch (SQLException ex) {
            
        } finally {
            // Fermeture de la connexion il faut les fermer en ordre de plus recent vers plus encien
            try {
                if (resultat != null)
                    resultat.close();
                if (prestat != null)
                    prestat.close();
                if (connexion != null) {
                    connexion.close();
                }

            } catch (SQLException ignore) {
            }
        }
        
        return listChoix;
    
    }
  
   //**************************************mthd Afficher list des choix d'un enseignat *********************************************************************************
    
   public List<Module> listChoix_dePerson(Personne p){
        List<Module> listChoix = new ArrayList<Module>();
        
        // lancer cnx a BD
    loadDatabase();
    PreparedStatement prestat = null;
    ResultSet resultat = null;
            
        try {
            
            prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM choix_module WHERE ID_PERS=?");
            prestat.setLong(1, p.getId_pers());
            resultat = prestat.executeQuery();  
           
            Choix_module ch = new Choix_module();
           
            while(resultat.next()){
            
                    ch.setID_PERS(resultat.getLong("ID_PERS"));
                    ch.setID_MODULE1(resultat.getLong("ID_MODULE1"));
                    ch.setID_MODULE2(resultat.getLong("ID_MODULE2"));
                    ch.setID_MODULE3(resultat.getLong("ID_MODULE3"));
            }
            
            prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM module WHERE ID_MODULE=?");
    
    if(ch.getID_MODULE1() != null || ch.getID_MODULE2() != null || ch.getID_MODULE3() != null ){
            
            prestat.setLong(1, ch.getID_MODULE1());
            resultat = prestat.executeQuery();
            if(resultat.next()){
                Module m = new Module();    m.setNom_mod(resultat.getString("nom_mod"));   listChoix.add(m);
            }   
            
            prestat.setLong(1, ch.getID_MODULE2());
            resultat = prestat.executeQuery();
            if(resultat.next()){
                Module m = new Module();    m.setNom_mod(resultat.getString("nom_mod"));   listChoix.add(m);
            }

            prestat.setLong(1, ch.getID_MODULE3());
            resultat = prestat.executeQuery();                        
            if(resultat.next()){
                Module m = new Module();    m.setNom_mod(resultat.getString("nom_mod"));    listChoix.add(m);
            }
    }        
            
            
        } catch (SQLException ex) {
            
        } finally {
            // Fermeture de la connexion il faut les fermer en ordre de plus recent vers plus encien
            try {
                if (resultat != null)
                    resultat.close();
                if (prestat != null)
                    prestat.close();
                
                if (connexion != null) {
                    connexion.close();
                }

            } catch (SQLException ignore) {
            }
        }
        
        return listChoix;
    
    }
}
