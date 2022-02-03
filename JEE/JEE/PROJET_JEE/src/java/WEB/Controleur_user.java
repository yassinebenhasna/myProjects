
package WEB;


import DAO.TraitChoix_module;
import DAO.TraitModule;
import DAO.TraitPersonne;
import METIER.Choix_module;
import METIER.Module;
import METIER.Personne;
import WEB.MODEL.Choix_moduleModel;
import WEB.MODEL.Infos_perso_module;
import WEB.MODEL.ModuleModel;
import WEB.MODEL.PersonneModel;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Controleur_user extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //String path = request.getServletPath();

        
        String page = (String)request.getParameter("page");
        
if(page == null || page.equals("") || page.equals("user_home")){
            getServletContext().getRequestDispatcher("/WEB-INF/user/user.jsp").forward(request,response);
            
}else if(page.equals("choisirModule")){ 
            //charger la liste des modules
            TraitModule traitmodule = new TraitModule();
            
                List<Module> listeModules = traitmodule.listModules("allModules");
                
            ModuleModel modulModel = new ModuleModel();
                
                modulModel.setListModules(listeModules);
            
            request.setAttribute("listeModules",modulModel);
            
            getServletContext().getRequestDispatcher("/WEB-INF/user/choisirModule.jsp").forward(request,response);
            
}else if(page.equals("afficherModule")){ 
            // recuperer la liste des module from module
            TraitModule traitModule = new TraitModule();
                List<Module> listModule = traitModule.listModules("allModules");
                
            // stocker ces resultats dans un model de module
            ModuleModel moduleModel = new ModuleModel();
                moduleModel.setListModules(listModule);
                            
            //recupere la liste d'ensengnement (responsable,cours,td,tp,semetre,filiere,type) pour chaque module avec leur nom
                   
                List<ModuleModel> list_apres_trans = traitModule.transformer_IDs(listModule);
                
            //stocker ces resultat dans le model ModuleModel
                       
                moduleModel.setList_trasnformation(list_apres_trans);
                
            
            request.setAttribute("listTransformationEnNom",moduleModel);
            
            getServletContext().getRequestDispatcher("/WEB-INF/user/afficherModule.jsp").forward(request,response);
            
}else if(page.equals("afficherEns")){ 
            // recuperer la liste des personne from Personne
            TraitPersonne listNomPersonne = new TraitPersonne();
                List<Personne> listNom = listNomPersonne.listPersonne("allNames");
                
            // stocker ces resultats dans un model de personne
            PersonneModel persoModel = new PersonneModel();
                persoModel.setList(listNom);
                            
            //recupere la liste d'ensengnement (cours,td,tp) pour chaque enseignant
            
            TraitModule traitModule = new TraitModule();
                
                List<Infos_perso_module> list_pers_mod = traitModule.list_mod_per(listNom);
                
            //stocker ces resultat dans le model Infos_perso_module
            
            Infos_perso_module list_info_Model = new Infos_perso_module();
            
                list_info_Model.setList_info_mod_ens(list_pers_mod);
                
            
            request.setAttribute("list_pers_mod",list_info_Model);
            
            getServletContext().getRequestDispatcher("/WEB-INF/user/afficherEns.jsp").forward(request,response);
            
}else if(page.equals("Telecharger_ens")){ 
            // recuperer la liste des personne from Personne
            TraitPersonne listNomPersonne = new TraitPersonne();
                List<Personne> listNom = listNomPersonne.listPersonne("allNames");
                
            // stocker ces resultats dans un model de personne
            PersonneModel persoModel = new PersonneModel();
                persoModel.setList(listNom);
                            
            //recupere la liste d'ensengnement (cours,td,tp) pour chaque enseignant
            
            TraitModule traitModule = new TraitModule();
                
                List<Infos_perso_module> list_pers_mod = traitModule.list_mod_per(listNom);
                
            //stocker ces resultat dans le model Infos_perso_module
            
            Infos_perso_module list_info_Model = new Infos_perso_module();
            
                list_info_Model.setList_info_mod_ens(list_pers_mod);
                
            
            request.setAttribute("list_pers_mod",list_info_Model);
            
            getServletContext().getRequestDispatcher("/TeleEns").forward(request,response);

}else if(page.equals("modif_compte")){ 
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("user");
        String password = (String)session.getAttribute("pass");    
        
        // recuperer la liste des personne from Personne
            TraitPersonne listNomPersonne = new TraitPersonne();
                List<Personne> listNom = listNomPersonne.listPersonne(username);
                
            // stocker ces resultats dans un model de personne
            PersonneModel persoModel = new PersonneModel();
                persoModel.setList(listNom);
        
        request.setAttribute("list",listNom);    
        getServletContext().getRequestDispatcher("/WEB-INF/user/modif_compte.jsp").forward(request,response);

}else{
            getServletContext().getRequestDispatcher("/WEB-INF/NotFound.jsp").forward(request,response);

        }

        
         
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        String page = (String)request.getParameter("page");
                
        if(page.equals("choisirModule")){
        //charger la liste
             TraitModule traitmodule = new TraitModule();
            
                List<Module> listeModules = traitmodule.listModules("allModules");
                
            ModuleModel modulModel = new ModuleModel();
                
                modulModel.setListModules(listeModules);
            
            request.setAttribute("listeModules",modulModel);
       
        // recupere le user courant
            String user = (String)request.getParameter("user");

        // recuperons maintenant son ID
        
            TraitPersonne traitPerso = new TraitPersonne();
            
                List<Personne> list_perso = traitPerso.listPersonne(user);
                
        //on stocke les donnees dans les class metier et model
        
            Choix_module choix_module = new Choix_module();
            
                choix_module.setID_PERS(list_perso.get(0).getId_pers());
                choix_module.setID_MODULE1(Long.parseLong(request.getParameter("Module1")));
                choix_module.setID_MODULE2(Long.parseLong(request.getParameter("Module2")));
                choix_module.setID_MODULE3(Long.parseLong(request.getParameter("Module3")));
            
            Choix_moduleModel ch_mo_model = new Choix_moduleModel();
                ch_mo_model.setListChoix(choix_module);
                
        // on appel la couche metier pour faire les traitement avec notre BD
        
            TraitChoix_module trai_choix = new TraitChoix_module();
                
                String validation_choix = trai_choix.addChoixModule(choix_module);
                
        // stocker le resultat dans notre model
            ch_mo_model.setValidationChoix(validation_choix);
            
        // envoyer la reponse vers notre page jsp
            request.setAttribute("reponse_validation", ch_mo_model);

            getServletContext().getRequestDispatcher("/WEB-INF/user/choisirModule.jsp").forward(request,response);
            
}else if(page.equals("chercherMod")){ 
    //recuperer le mot cle cherché
            String mc = (String)request.getParameter("mc");
    // recuperer la liste des module from module
            TraitModule traitModule = new TraitModule();
                List<Module> listModule = traitModule.listModules(mc);
                
            // stocker ces resultats dans un model de module
            ModuleModel moduleModel = new ModuleModel();
                moduleModel.setListModules(listModule);
                            
            //recupere la liste d'ensengnement (responsable,cours,td,tp,semetre,filiere,type) pour chaque module avec leur nom
                   
                List<ModuleModel> list_apres_trans = traitModule.transformer_IDs(listModule);
                
            //stocker ces resultat dans le model ModuleModel
                       
                moduleModel.setList_trasnformation(list_apres_trans);
                
            
            request.setAttribute("listTransformationEnNom",moduleModel);
            
            getServletContext().getRequestDispatcher("/WEB-INF/user/afficherModule.jsp").forward(request,response);

}else if(page.equals("chercherEns")){ 
    //recuperer le mot cle cherché
            String mc = (String)request.getParameter("mc");
    // recuperer la liste des personne from Personne
            TraitPersonne listNomPersonne = new TraitPersonne();
                List<Personne> listNom = listNomPersonne.listPersonne(mc);
                
            // stocker ces resultats dans un model de personne
            PersonneModel persoModel = new PersonneModel();
                persoModel.setList(listNom);
                            
            //recupere la liste d'ensengnement (cours,td,tp) pour chaque enseignant
            
            TraitModule traitModule = new TraitModule();
                
                List<Infos_perso_module> list_pers_mod = traitModule.list_mod_per(listNom);
                
            //stocker ces resultat dans le model Infos_perso_module
            
            Infos_perso_module list_info_Model = new Infos_perso_module();
            
                list_info_Model.setList_info_mod_ens(list_pers_mod);
                
            
            request.setAttribute("list_pers_mod",list_info_Model);
            
            
            
            getServletContext().getRequestDispatcher("/WEB-INF/user/afficherEns.jsp").forward(request,response);

}else if(page.equals("Telecharger_module")){ 
            
            String nom_module = (String)request.getParameter("nom_module");
            String nom_ens_cours = (String)request.getParameter("nom_ens_cours");
            String nom_ens_td = (String)request.getParameter("nom_ens_td");
            String nom_ens_tp = (String)request.getParameter("nom_ens_tp");
            
            request.setAttribute("nom_module",nom_module);
            request.setAttribute("nom_ens_cours",nom_ens_cours);
            request.setAttribute("nom_ens_td",nom_ens_td);
            request.setAttribute("nom_ens_tp",nom_ens_tp);
            
            
            
            getServletContext().getRequestDispatcher("/Telemodule").forward(request,response);

}else if(page.equals("modif_compte")){ 

    
            Personne p = new Personne ();
                            p.setId_pers(Long.parseLong(request.getParameter("id")));
                            p.setNom((String)request.getParameter("nom"));
                            p.setPrenom((String)request.getParameter("prenom"));
                            p.setCin((String)request.getParameter("CIN"));
                            p.setEmail((String)request.getParameter("email"));
                            p.setTel((String)request.getParameter("tel"));
                            p.setPassword((String)request.getParameter("pass"));                            
                            
            TraitPersonne traiPers = new TraitPersonne();
                        
                    traiPers.modifier(p);
                    
                        
    // recuperer la liste des personne from Personne
            TraitPersonne listNomPersonne = new TraitPersonne();
                List<Personne> listNom = listNomPersonne.listPersonne("");
                
            // stocker ces resultats dans un model de personne
            PersonneModel persoModel = new PersonneModel();
                persoModel.setList(listNom);
                            
            //recupere la liste d'ensengnement (cours,td,tp) pour chaque enseignant
            
            TraitModule traitModule = new TraitModule();
                
                List<Infos_perso_module> list_pers_mod = traitModule.list_mod_per(listNom);
                
            //stocker ces resultat dans le model Infos_perso_module
            
            Infos_perso_module list_info_Model = new Infos_perso_module();
            
                list_info_Model.setList_info_mod_ens(list_pers_mod);
                
            
            request.setAttribute("list_pers_mod",list_info_Model);
            
            getServletContext().getRequestDispatcher("/WEB-INF/user/afficherEns.jsp").forward(request,response);

}else{
            getServletContext().getRequestDispatcher("/WEB-INF/NotFound.jsp").forward(request,response);

        }
        
    }  
}
