
package DAO;

import METIER.Filiere;
import METIER.Fonction;
import METIER.Grade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TraitFiliere {
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

    
    //**************************************mthd Afficher list des grades**********************************************************************************

    public List<Filiere> ListFilier(){
        List<Filiere> listFiliere = new ArrayList<Filiere>();
        
        // lancer cnx a BD
    loadDatabase();
    PreparedStatement prestat = null;
    ResultSet resultat = null;
            
        try {
            
            prestat = (PreparedStatement) connexion.prepareStatement("SELECT * FROM filiere");
            
            resultat = prestat.executeQuery();  
            
            while(resultat.next()){
            
                Filiere f = new Filiere();
                
                    f.setID_FIL(resultat.getLong("ID_FIL"));
                    f.setFil(resultat.getString("fil"));
                    
                listFiliere.add(f);
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
        
        return listFiliere;
    
    }
    
}
