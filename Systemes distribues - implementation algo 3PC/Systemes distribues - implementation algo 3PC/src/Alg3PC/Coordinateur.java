/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alg3PC;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author yassine
 */

public class Coordinateur extends Thread{
    
/**
 * 
 * le coordinateur doit communiquer avec les serveurs du backup pour assuer est ce que ces serveurs sont capable de modifier le montant aussi ou non
 * donc on doit diffuser un msg au ces serveur
 * et appliquer l'algorithme 3PC
 * 
*/
    Socket ServeurBackup1;
    Socket ServeurBackup2;
    String typeOperation ;
    String montantSaisit;
    String choixScenario;
    String decisionGlobale;
    
    int delaiDeGarde = 15000;// 15 sec
    
    
    public Coordinateur( String typeOperation , String montantSaisit , String choixScenario){        
        this.typeOperation = typeOperation;  
        this.montantSaisit = montantSaisit;
        this.choixScenario = choixScenario;
    }

//*************************************** phase1 : demande de vote & attendre les reponses ******************************************    
    public void phase1(){
         
        try{             
            
            System.out.println("\n\t\t*)phase1 : demande de vote et attendre les reponses :");
            
            //serveur Backup 1
                ServeurBackup1 = new Socket("localhost" , 1235);
                    
            //serveur Backup 2
                ServeurBackup2 = new Socket("localhost" , 1236);
                  
            //etablir la communication avec les serveurs du backup
                Voter commServBack1 = new Voter(ServeurBackup1);
                Voter commServBack2 = new Voter(ServeurBackup2);
            
                    commServBack1.start();
                    commServBack2.start();
                    
            System.out.println("\t\t\t\t\t_ La demande de vote est envoyé ");
            
            //les reponses des serveurs backup lorsque ils votent ( oui ou non )     
            
            System.out.println("\t\t\t\t\t_ Attente de leur reponse(delai de garde 15 sec) ...");
                sleep(17000);//on ajoute 2sec pour le delai de garde pour assurer l'arrive du reponse
                String reponseServeurBackup1 = commServBack1.reponse;
                String reponseServeurBackup2 = commServBack2.reponse;                
                System.out.println("\t\t\t\t\t\treponse du serveur backup 1 = " + reponseServeurBackup1);
                System.out.println("\t\t\t\t\t\treponse du serveur backup 2 = " + reponseServeurBackup2);                              
                
            //decider maintenant si la decision globale sera "preparer" ou "annuler" !
                //1)si tous les reponses sont oui ===> alors envoyer la pre-validation au tous les serveur de backup 
                        if(reponseServeurBackup1.equals("oui") && reponseServeurBackup2.equals("oui")){
                            decisionGlobale = "preparer";
                        }
                //2)si une reponse est non ou qu'un serveur ne vote pas ===> alors anvoyer annuler l'operation
                        else{
                            decisionGlobale = "annuler";
                        }
                        
            System.out.println("\n\t\t==> La décision globale donc sera \"" + decisionGlobale + "\"");
                
        }catch(Exception ex){
            System.out.println("erreeeur coordinateur mthd(phase1) = " + ex);
        }
    }
    
//*************************************** phase2 : diffuser à tous les serveur backup de la décision globale & attente des acquittements de la réception ******************************************
    public String phase2(){
        
        String ValiderOUAnnulerOUPlanter = "";        
        
        //si le scenarion choisit est que le coordinateur envoie la décision globale
            if(!choixScenario.equals("5")){
                try{                                        

                    //etablir la communication avec les serveurs du backup
                        Diffuser commServBack1 = new Diffuser(ServeurBackup1);
                        Diffuser commServBack2 = new Diffuser(ServeurBackup2);

                        //si la decision globale est "preparer" -----> attendre les aquittement            
                            if(decisionGlobale.equals("preparer")){

                                commServBack1.start();
                                commServBack2.start(); 

                                System.out.println("\n\t\t*)phase2 : diffuser à tous les serveur backup de la décision globale \"preparer\" et attente des acquittements de la réception :");
                                System.out.println("\t\t\t\t\t_ La décision globale est diffusé à tous les serveur de backup.");

                                //les reponses des serveurs backup s'il reçoivent la decision globale ou non     

                                System.out.println("\t\t\t\t\t_ Attente des aquittements(delai de garde 15 sec)...");
                                    sleep(17000);//on ajoute 2sec pour le delai de garde pour assurer l'arrive du reponse
                                    String reponseServeurBackup1 = commServBack1.ACK;
                                    String reponseServeurBackup2 = commServBack2.ACK;
                                    System.out.println("\t\t\t\t\t\taquittement du serveur backup 1 = " + reponseServeurBackup1);
                                    System.out.println("\t\t\t\t\t\taquittement du serveur backup 2 = " + reponseServeurBackup2);
                                    ValiderOUAnnulerOUPlanter = "valider";
                            }
                        //si la decision globale est "annuler" -----> pas la peine d'attendre les aquittement
                            else{

                                commServBack1.start();
                                commServBack2.start();

                                System.out.println("\n\t\t*)phase2 : diffuser à tous les serveur backup de la décision globale \"annuler\" et terminer l'echange des messages.");
                                System.out.println("\t\t\t\t\t_ La décision globale est diffusé à tous les serveur de backup.");
                                ValiderOUAnnulerOUPlanter = "annuler";
                            }
                    

                }catch(Exception ex){
                    System.out.println("erreeeur coordinateur mthd(phase2) = " + ex);
                }
                
            }
            
        //si le scenarion choisit est que le coordinateur n'envoie pas la décision globale
        else{
            System.out.println("\n\t\t*)phase2 : diffuser à tous les serveur backup de la décision globale.");
            System.out.println("\n\t\t\t\t je suis planté : je peux pas envoyé la décision globale");
            ValiderOUAnnulerOUPlanter = "planter";
        }
        
        return ValiderOUAnnulerOUPlanter;
    }
    
//*************************************** phase3 : diffusion de la demande d'exécution de larequête (sauf si décision annuler) ******************************************
    public void phase3(){

        System.out.println("\n\t\t*)phase3 : diffusion de la demande d'exécution de la requête :");
        
        //si le scenarion choisit est que le coordinateur envoie la validation
            if(!choixScenario.equals("6")){
                try{                                        

                    //etablir la communication avec les serveurs du backup
                        Valider commServBack1 = new Valider(ServeurBackup1);
                        Valider commServBack2 = new Valider(ServeurBackup2);

                            commServBack1.start();
                            commServBack2.start();

                        System.out.println("\t\t\t\t\t_ La validation est diffusé à tous les serveur de backup.");


                }catch(Exception ex){
                    System.out.println("erreeeur coordinateur mthd(phase3) = " + ex);
                }
            }
            
        //si le scenarion choisit est que le coordinateur n'envoie pas la validation
        else{
            System.out.println("\t\t\t\t\t_ je suis planté : je peux pas envoyé la validation.");
        }
        
    }
    
    
//*************************************** thread qui demande les votes des serveurs backup ******************************************
    class Voter extends Thread{
        
        Socket ServeurBackup;
        String reponse; 
        
        public Voter( Socket ServeurBackup ){
            this.ServeurBackup = ServeurBackup;
        }
    
        @Override
        public void run(){
            
            try{
            //charger les entrees sorties pour communisuer avec les serveur backup
                    //entrees
                        InputStream is = ServeurBackup.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            BufferedReader bfr = new BufferedReader(isr);

                    //sorties
                        OutputStream os = ServeurBackup.getOutputStream();
                            PrintWriter pwr = new PrintWriter(os , true);
                            
            //envoyer la demande de vote avec le choix du client en meme temps
                pwr.println("votez SVP est ce que vous etes pret ?" + "," + typeOperation + "," + montantSaisit + "," + choixScenario);
                
            //armer un delai de garde
                ServeurBackup.setSoTimeout(delaiDeGarde);//
                
                //si le serveur du backup repond
                    try{
                        //cette reponse peut etre oui ou non
                        this.reponse = bfr.readLine();
                                                
                //si le serveur du backup ne repond pas et le delai de garde terminé alors il faut annuler l'operation
                    }catch(Exception ex){                       
                        this.reponse = "le delai expiré et ce serveur ne repond pas";
                    }            
                            
            }catch(Exception ex){
                System.out.println("erreeeur coordinateur class(communiquer) = " + ex);
            }
        }
        
    }
    
//*************************************** thread qui diffuser à tous les serveur backup de la décision globale ******************************************
    class Diffuser extends Thread{
        
        Socket ServeurBackup;
        String ACK;
        
        public Diffuser( Socket ServeurBackup ){
            this.ServeurBackup = ServeurBackup;
        }         
    
        @Override
        public void run(){
            
            try{
            //charger les entrees sorties pour communisuer avec les serveur backup
                    //entrees
                        InputStream is = ServeurBackup.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            BufferedReader bfr = new BufferedReader(isr);

                    //sorties
                        OutputStream os = ServeurBackup.getOutputStream();
                            PrintWriter pwr = new PrintWriter(os , true);                                        
            
                
            //si la decision globale est "preparer" : tous les serveur du backup repondent "oui"
            if(decisionGlobale.equals("preparer")){
                
                //diffuser "preparer" aux serveurs du backup
                    pwr.println( decisionGlobale );
                
                //armer un delai de garde
                    ServeurBackup.setSoTimeout(delaiDeGarde);

                    //si le serveur du backup envoie son ACK
                        try{
                            this.ACK = bfr.readLine();

                    //si le serveur du backup n'envoie pas son ACK et le delai de garde terminé 
                        }catch(Exception ex){                       
                            this.ACK = "le delai expiré et ce serveur n'envoie pas son aquittement";
                        }
            }
            
            //si la decision globale est "annuler" : un serveur repond "non" ou il ne repond pas
            else{
                //diffuser "annuler" aux serveurs du backup 
                    pwr.println( decisionGlobale );
            }
                            
            }catch(Exception ex){
                System.out.println("erreeeur coordinateur class(preparer) = " + ex);
            }
        }
        
    }
    
//*************************************** thread qui diffuser à tous les serveur backup de la décision globale ******************************************
    class Valider extends Thread{
        
        Socket ServeurBackup;
        
        public Valider( Socket ServeurBackup ){
            this.ServeurBackup = ServeurBackup;
        }         
    
        @Override
        public void run(){
            
            try{
            //charger les entrees sorties pour communisuer avec les serveur backup
                    //entrees
                        InputStream is = ServeurBackup.getInputStream();
                            InputStreamReader isr = new InputStreamReader(is);
                            BufferedReader bfr = new BufferedReader(isr);

                    //sorties
                        OutputStream os = ServeurBackup.getOutputStream();
                            PrintWriter pwr = new PrintWriter(os , true);                                        
            
                
            //envoyer la validation
                pwr.println( "valider" );
                            
            }catch(Exception ex){
                System.out.println("erreeeur coordinateur class(valider) = " + ex);
            }
        }
        
    }
    
}
