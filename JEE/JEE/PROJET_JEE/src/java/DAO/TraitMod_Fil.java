package DAO;


import METIER.Mod_Fil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TraitMod_Fil {
                                    
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

    public void ajouter(Mod_Fil mod_fil) {

        //loadDatabase();
        loadDatabase();
        PreparedStatement prestat = null;

        try {

            // Exécution de la requête
            prestat = (PreparedStatement) connexion.prepareStatement("INSERT INTO mod_fil(ID_MODULE,ID_FIL) VALUES (?,?)");
            prestat.setLong(1,mod_fil.getID_MODULE());
            prestat.setLong(2,mod_fil.getID_FIL());
            
            prestat.executeUpdate();

        } catch (SQLException e) {
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


    }
  
}
