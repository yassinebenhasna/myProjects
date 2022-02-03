/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbreDecision;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yassine
 */
public class ArbreGrafic2 extends javax.swing.JFrame {
    
//*********************************************************** ATTRIBUT ***************************************************************************
    
    
    
    Map<String , String> parametreAttributEtModalite = new HashMap<>();
    List<String> CheminsBienFormate = new ArrayList<>();
    
//*************************************************************************************************************************************************

    /**
     * Creates new form ArbreGrafic2
     */
    public ArbreGrafic2() {
        
        this.setResizable(false);
        initComponents();
        
        Toolkit tlkit = Toolkit.getDefaultToolkit();
        int xsize = tlkit.getScreenSize().width;
            int ysize = tlkit.getScreenSize().height;
            this.setSize(xsize * 79 /100, ysize * 89 / 100);
            
            setLocationRelativeTo(getComponentAt(xsize, ysize));
        
        //desactiver l'ecriture deans ces zones
            zoneTxt_Arbre.setEditable(false);
            zoneText_InstanceReponse.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        zoneTxt_Arbre = new javax.swing.JTextArea();
        btn_meteo = new javax.swing.JRadioButton();
        btn_maladie = new javax.swing.JRadioButton();
        btn_contact = new javax.swing.JRadioButton();
        btn_testInstance = new javax.swing.JButton();
        btn_genererArbre = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        zoneText_instance = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        zoneText_InstanceReponse = new javax.swing.JTextArea();
        btn_valider = new javax.swing.JButton();
        btn_annuler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PROJET DM - Arbre De Décision");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Choisissez un fichier SVP :");

        zoneTxt_Arbre.setBackground(new java.awt.Color(204, 204, 204));
        zoneTxt_Arbre.setColumns(20);
        zoneTxt_Arbre.setRows(5);
        jScrollPane1.setViewportView(zoneTxt_Arbre);

        buttonGroup1.add(btn_meteo);
        btn_meteo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_meteo.setText("meteorologique.arff");

        buttonGroup1.add(btn_maladie);
        btn_maladie.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_maladie.setText("maladies.arff");

        buttonGroup1.add(btn_contact);
        btn_contact.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_contact.setText("contacts.arff");

        btn_testInstance.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_testInstance.setForeground(new java.awt.Color(0, 153, 153));
        btn_testInstance.setText("Tester une instance");
        btn_testInstance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_testInstanceActionPerformed(evt);
            }
        });

        btn_genererArbre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_genererArbre.setForeground(new java.awt.Color(0, 153, 51));
        btn_genererArbre.setText("Générer l'arbre");
        btn_genererArbre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_genererArbreActionPerformed(evt);
            }
        });

        zoneText_instance.setBackground(new java.awt.Color(204, 204, 204));
        zoneText_instance.setColumns(20);
        zoneText_instance.setRows(5);
        jScrollPane2.setViewportView(zoneText_instance);

        zoneText_InstanceReponse.setBackground(new java.awt.Color(204, 204, 204));
        zoneText_InstanceReponse.setColumns(20);
        zoneText_InstanceReponse.setRows(5);
        jScrollPane3.setViewportView(zoneText_InstanceReponse);

        btn_valider.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_valider.setForeground(new java.awt.Color(51, 0, 204));
        btn_valider.setText("Valider");
        btn_valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_validerActionPerformed(evt);
            }
        });

        btn_annuler.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btn_annuler.setForeground(new java.awt.Color(255, 0, 0));
        btn_annuler.setText("Réinitialiser");
        btn_annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_annulerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_maladie)
                            .addComponent(btn_meteo)
                            .addComponent(btn_contact)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btn_testInstance)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_annuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_valider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(78, 78, 78)
                    .addComponent(btn_genererArbre)
                    .addContainerGap(1299, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addComponent(btn_meteo)
                        .addGap(28, 28, 28)
                        .addComponent(btn_contact)
                        .addGap(32, 32, 32)
                        .addComponent(btn_maladie))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_testInstance)
                                .addGap(110, 110, 110))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(btn_valider)
                        .addGap(28, 28, 28)
                        .addComponent(btn_annuler)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(469, 469, 469)
                    .addComponent(btn_genererArbre)
                    .addContainerGap(472, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
//*************************************************** boutton de generation de l'arbre ******************************************************************************
    
    private void btn_genererArbreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_genererArbreActionPerformed
        // TODO add your handling code here:
                
        if( btn_contact.isSelected() ){ 
            
            zoneText_instance.setText("");zoneText_InstanceReponse.setText("");
                        
            LireFichier lireFichier = new LireFichier();
            Arbre arbre = new Arbre();
            
            String textAffiche = "";
            textAffiche += "\t\t******************************************************************************************************************************************\n\n";
            textAffiche += "\t\t\t\t       Arbre de Décision : contact.arff\n\n";
            textAffiche += "\t\t******************************************************************************************************************************************\n\n"; 
            
            lireFichier.lire_fichier("src/contact.arff");//zoneTxt_Arbre.setText("test1");
            
            //construire la racine et charger ses modalites
            arbre.list_attributs = lireFichier.getList_attributs();
            arbre.list_modalites = lireFichier.getList_modalites();
            arbre.list_instances = lireFichier.getList_instances();
            arbre.attributs_AND_LeurInstances = lireFichier.getAttributs_AND_LeurInstances();
            arbre.list_TsFichier = lireFichier.getList_TsFichier();
                        
            arbre.construireRacine(  );
            
        //construire toute l'arbre
            arbre.construireArbre();
            this.CheminsBienFormate = arbre.CheminsBienFormate;
            
            zoneTxt_Arbre.setFont(new java.awt.Font("Dialog", 1, 14));
                zoneTxt_Arbre.setText(textAffiche + arbre.textAfficheDansGrafic.toString());
                
        //donnees pour construire le grafic3 : attribut et ses modalites          
            parametreAttributEtModalite.clear();        
            for( int i=0; i<arbre.list_attributs.size() - 1 ; i++ ){
                
                String attribut = arbre.list_attributs.get(i);
                
                String modalites = "{ ";
                for(String modalite : arbre.info.get_list_Modalite_pour_attribut(arbre.list_TsFichier, attribut)){
                    
                    modalites += modalite + " ";
                            
                }
                
                modalites += " }";
                
                parametreAttributEtModalite.put(attribut, modalites);                

            }
                        
        }else if( btn_maladie.isSelected() ){
            
            zoneText_instance.setText("");zoneText_InstanceReponse.setText("");
            
            LireFichier lireFichier = new LireFichier();
            Arbre arbre = new Arbre();
            
            String textAffiche = "";
            textAffiche += "\t\t******************************************************************************************************************************************\n\n";
            textAffiche += "\t\t\t\t       Arbre de Décision : maladies.arff\n\n";
            textAffiche += "\t\t******************************************************************************************************************************************\n\n"; 
            
            lireFichier.lire_fichier("src/maladies.arff");     //zoneTxt_Arbre.setText("test2");   
            
            //construire la racine et charger ses modalites
            arbre.list_attributs = lireFichier.getList_attributs();
            arbre.list_modalites = lireFichier.getList_modalites();
            arbre.list_instances = lireFichier.getList_instances();
            arbre.attributs_AND_LeurInstances = lireFichier.getAttributs_AND_LeurInstances();
            arbre.list_TsFichier = lireFichier.getList_TsFichier();
                        
            arbre.construireRacine(  );
            this.CheminsBienFormate = arbre.CheminsBienFormate;
            
        //construire toute l'arbre
            arbre.construireArbre();
            
            
            zoneTxt_Arbre.setFont(new java.awt.Font("Dialog", 1, 14));
                zoneTxt_Arbre.setText(textAffiche + arbre.textAfficheDansGrafic.toString());
                
        //donnees pour construire le grafic3 : attribut et ses modalites          
            parametreAttributEtModalite.clear();        
            for( int i=0; i<arbre.list_attributs.size() - 1 ; i++ ){
                
                String attribut = arbre.list_attributs.get(i);
                
                String modalites = "{ ";
                for(String modalite : arbre.info.get_list_Modalite_pour_attribut(arbre.list_TsFichier, attribut)){
                    
                    modalites += modalite + " ";
                            
                }
                
                modalites += " }";
                
                parametreAttributEtModalite.put(attribut, modalites);                

            }
            
        }else if( btn_meteo.isSelected() ){
            
            zoneText_instance.setText("");zoneText_InstanceReponse.setText("");
            
            LireFichier lireFichier = new LireFichier();
            Arbre arbre = new Arbre();
            
            String textAffiche = "";
            textAffiche += "\t\t******************************************************************************************************************************************\n\n";
            textAffiche += "\t\t\t\t       Arbre de Décision : meteorologique.arff\n\n";
            textAffiche += "\t\t******************************************************************************************************************************************\n\n";
            
            lireFichier.lire_fichier("src/meteorologique.arff");//zoneTxt_Arbre.setText("test3");
            
            //construire la racine et charger ses modalites
            arbre.list_attributs = lireFichier.getList_attributs();
            arbre.list_modalites = lireFichier.getList_modalites();
            arbre.list_instances = lireFichier.getList_instances();
            arbre.attributs_AND_LeurInstances = lireFichier.getAttributs_AND_LeurInstances();
            arbre.list_TsFichier = lireFichier.getList_TsFichier();
                        
            arbre.construireRacine(  );
            
        //construire toute l'arbre
            arbre.construireArbre();
            this.CheminsBienFormate = arbre.CheminsBienFormate;
            
            zoneTxt_Arbre.setFont(new java.awt.Font("Dialog", 1, 14));
                zoneTxt_Arbre.setText(textAffiche + arbre.textAfficheDansGrafic.toString());
                
        //donnees pour construire le grafic3 : attribut et ses modalites          
            parametreAttributEtModalite.clear();        
            for( int i=0; i<arbre.list_attributs.size() - 1 ; i++ ){
                
                String attribut = arbre.list_attributs.get(i);
                
                String modalites = "{ ";
                for(String modalite : arbre.info.get_list_Modalite_pour_attribut(arbre.list_TsFichier, attribut)){
                    
                    modalites += modalite + " ";
                            
                }
                
                modalites += " }";
                
                parametreAttributEtModalite.put(attribut, modalites);                

            }
            
        }
        
        
    }//GEN-LAST:event_btn_genererArbreActionPerformed

    private void btn_testInstanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_testInstanceActionPerformed
        // TODO add your handling code here:
        
        if( zoneTxt_Arbre.getText().isEmpty() ){  
                                        
            ArbreGrafic3 arbg3 = new ArbreGrafic3("Erreur : Il faut générer l'arbre tout d'abord SVP !");
            
            arbg3.setVisible(true);
            
        }else{
        
                String infosAffichesDanZonetxt_Instance = "";
                for( String attribut : parametreAttributEtModalite.keySet() ){

                    String modalites = parametreAttributEtModalite.get(attribut);

                    infosAffichesDanZonetxt_Instance += "     " + attribut + " " + modalites + " ==> \n";
                    infosAffichesDanZonetxt_Instance += "-------------------------------------------------------------------------------------\n";

                }

                zoneText_instance.setFont(new java.awt.Font("Dialog", 1, 14));zoneText_instance.setForeground(Color.black);
                        zoneText_instance.setText( infosAffichesDanZonetxt_Instance );
        }
        
    }//GEN-LAST:event_btn_testInstanceActionPerformed

    private void btn_annulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_annulerActionPerformed
        // TODO add your handling code here:
        
        if(! zoneText_instance.getText().isEmpty() ){
            
            zoneText_InstanceReponse.setText("");
            
            String infosAffichesDanZonetxt_Instance = "";
                for( String attribut : parametreAttributEtModalite.keySet() ){

                    String modalites = parametreAttributEtModalite.get(attribut);

                    infosAffichesDanZonetxt_Instance += "     " + attribut + " " + modalites + " ==> \n";
                    infosAffichesDanZonetxt_Instance += "-------------------------------------------------------------------------------------\n";

                }

                zoneText_instance.setFont(new java.awt.Font("Dialog", 1, 14));zoneText_instance.setForeground(Color.black);
                        zoneText_instance.setText( infosAffichesDanZonetxt_Instance );
        }
        
    }//GEN-LAST:event_btn_annulerActionPerformed

    private void btn_validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_validerActionPerformed
        // TODO add your handling code here:
        
        if(! zoneText_instance.getText().isEmpty() ){  
            
            boolean ok = false;
            
            Map<String , String> donnesEntreClient = new HashMap<>();
            
            String infosClient = zoneText_instance.getText();
            
            String lignes[] = infosClient.split("\n");
            
            for( String line : lignes ){
                 
                if( !line.isEmpty() || !line.equals("") ){
                    //text += line + "\n\n";
                    String attributChoisit = line.replaceAll("\\{.*", "").trim();
                    String modaliteChoisit = line.replaceAll(".*==>", "").trim();                   
                    
                    for( String attribut : parametreAttributEtModalite.keySet() ){

                        if( attributChoisit . equals( attribut.trim() ) ){
                        
                            String modalites = parametreAttributEtModalite.get(attribut).replaceAll("(\\{)|(\\})", "").replaceAll("( )+", " ").trim();
                            String modalitesAttr[] = modalites.split(" ");
                              
                            ok = false;
                            for( String modalite : modalitesAttr ){
 
                                if( modaliteChoisit . equals( modalite.trim() ) ){
                                    
                                    donnesEntreClient.put(modaliteChoisit , attribut); 
                                    ok = true;
                                    break;
                                }                                                                
                            
                            }  
                            
                            if( ok == false )
                                break;
                            
                        }                                                                        
                    }
                    
                    if( ok == false )
                        break;
                    
                }
                
            }
            
            if( ok == false ){
                                    
                ArbreGrafic3 arbg3 = new ArbreGrafic3("Erreur : modalitée(s) choisi(s) incorrecte(s) !");

                    arbg3.setVisible(true);
                    
            }else{
            
            //appeler la classe qui traite les données d'un client
        
            TraiterInstance traiterInstance = new TraiterInstance();
                //on donne comme parametres les donnees saisient par client et la liste des chemins de l'arbre 
                String resultat = traiterInstance.traitInstance(donnesEntreClient, this.CheminsBienFormate);
                
                zoneText_InstanceReponse.setFont(new java.awt.Font("Dialog", 1, 14));
                        zoneText_InstanceReponse.setText( resultat );
            }
            
        }                
        
    }//GEN-LAST:event_btn_validerActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_annuler;
    private javax.swing.JRadioButton btn_contact;
    private javax.swing.JButton btn_genererArbre;
    private javax.swing.JRadioButton btn_maladie;
    private javax.swing.JRadioButton btn_meteo;
    private javax.swing.JButton btn_testInstance;
    private javax.swing.JButton btn_valider;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea zoneText_InstanceReponse;
    private javax.swing.JTextArea zoneText_instance;
    private javax.swing.JTextArea zoneTxt_Arbre;
    // End of variables declaration//GEN-END:variables



}
