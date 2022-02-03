
package DAO;

import METIER.Semestre;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TraitSemestre {
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
   
    //**************************************mthd afficher la liste des semestres**********************************************************************************
        
        public List<Semestre> listSemetre(){
            
            List<Semestre> listSem = new ArrayList<Semestre>();
            
             // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;
            ResultSet resultat = null;
            try {

                        prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM semestre  ");

                        resultat = prestat.executeQuery();
                        
                        while(resultat.next()){
                        
                            Semestre s = new Semestre ();
                                s.setID_SEM(resultat.getLong("ID_SEM"));
                                s.setNom_sem(resultat.getString("nom_sem"));
                                
                            listSem.add(s);
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
            
        
        
        
        return listSem;
        }


}
