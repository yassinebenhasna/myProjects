
package DAO;

import METIER.Semestre;
import METIER.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TraitType {
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
   
    //**************************************mthd afficher la liste des types**********************************************************************************
        
        public List<Type> listType(){
            
            List<Type> listType = new ArrayList<Type>();
            
             // lancer cnx a BD
            loadDatabase();
            PreparedStatement prestat = null;
            ResultSet resultat = null;
            try {

                        prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM type  ");

                        resultat = prestat.executeQuery();
                        
                        while(resultat.next()){
                        
                            Type t = new Type ();
                                t.setID_TYPE(resultat.getLong("ID_TYPE"));
                                t.setType_module(resultat.getString("type_module"));
                                
                            listType.add(t);
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
            
        
        
        
        return listType;
        }


}
