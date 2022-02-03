
package WEB;

import DAO.TraitChoix_module;
import DAO.TraitEtat;
import DAO.TraitFiliere;
import DAO.TraitFonction;
import DAO.TraitGrade;
import DAO.TraitMod_Fil;
import DAO.TraitModule;
import DAO.TraitPersonne;
import DAO.TraitSemestre;
import DAO.TraitType;
import METIER.Choix_module;
import METIER.Etat;
import METIER.Filiere;
import METIER.Fonction;
import METIER.Grade;
import METIER.Mod_Fil;
import METIER.Module;
import METIER.Personne;
import METIER.Semestre;
import METIER.Type;
import WEB.MODEL.Choix_moduleModel;
import WEB.MODEL.EtatModel;
import WEB.MODEL.FiliereModel;
import WEB.MODEL.FonctionModel;
import WEB.MODEL.GradeModel;
import WEB.MODEL.Infos_perso_module;
import WEB.MODEL.Mod_FilModel;
import WEB.MODEL.ModuleModel;
import WEB.MODEL.PersonneModel;
import WEB.MODEL.SemestreModel;
import WEB.MODEL.TypeModel;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Controleur_admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //String path = request.getServletPath();

        
        String page = (String)request.getParameter("page");
        
if(page == null || page.equals("")){
            getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request,response);
            
}else if(page.equals("admin_home")){
            getServletContext().getRequestDispatcher("/WEB-INF/admin/admin.jsp").forward(request,response);
            
}else if(page.equals("ajouterEns")){
            // liste de fonction
            TraitFonction listFct = new TraitFonction();
            
                List<Fonction> list_fct = listFct.ListFct();
            //stocker resultat dans un model de fct            
            FonctionModel fctModel = new FonctionModel();
            
                fctModel.setListFct(list_fct);
                
            request.setAttribute("listFct",fctModel);
        
            //list de grade
            TraitGrade listGrd = new TraitGrade();
            
                List<Grade> list_grd = listGrd.ListGrd();
            //stocker resultat dans un model de grade        
            GradeModel grdModel = new GradeModel();
            
                grdModel.setListGrd(list_grd);
                
            request.setAttribute("listGrd",grdModel);
           
            getServletContext().getRequestDispatcher("/WEB-INF/admin/addEnsei.jsp").forward(request,response);
            
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
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherEns.jsp").forward(request,response);
            
}else if(page.equals("ajouterModule")){
            
            //resupere la liste des nom des personnes
            TraitPersonne listNomPersonne = new TraitPersonne();
                List<Personne> listNom = listNomPersonne.listPersonne("allNames");
                
            // stocker ces resultats dans un model de personne
            PersonneModel persoModel = new PersonneModel();
                persoModel.setList(listNom);
                
            request.setAttribute("listNom",persoModel);
            
            //resupere la liste des semestres
            TraitSemestre listSemestre = new TraitSemestre();
                List<Semestre> listSem = listSemestre.listSemetre() ;
               
            // stocker ces resultats dans un model de Semestre
            SemestreModel semModel = new SemestreModel();
                semModel.setListSemestre(listSem);
                
            request.setAttribute("listSem",semModel);
            
            //recupere la liste des types
            TraitType listType = new TraitType();
                List<Type> listTyp = listType.listType() ;
                
            // stocker ces resultats dans un model de type
            TypeModel typeModel = new TypeModel();
                typeModel.setListType(listTyp);
                
            request.setAttribute("listType",typeModel);
            
            //resupere la liste des nom des filieres
            TraitFiliere traitFiliere = new TraitFiliere();
                List<Filiere> listFil = traitFiliere.ListFilier();
                
            // stocker ces resultats dans un model de filieres
                FiliereModel filModel = new FiliereModel();
                    filModel.setListFiliere(listFil);
            
            request.setAttribute("listFil", filModel);
            
            
           getServletContext().getRequestDispatcher("/WEB-INF/admin/ajouterModule.jsp").forward(request,response);
           
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
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherModule.jsp").forward(request,response);
            
}else if(page.equals("affectation")){ 
            
            //recupere la liste des choix des modules des enseingnats by IDs
            TraitChoix_module trait_ch = new TraitChoix_module();
            
                List<Choix_module> list_ch = trait_ch.listChoix();
                
            // stocker ces resultats dans un model 
            Choix_moduleModel ch_model = new Choix_moduleModel();
            
                ch_model.setList(list_ch);
                
            // traiter cette liste qui contient la liste des choix par IDs et recuperer les infos from ces IDs
            
                List<Choix_moduleModel> list_IDToInfo = trait_ch.infoByIDs(list_ch);
                
                
            request.setAttribute("listChoix",list_IDToInfo); 
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin/affectation.jsp").forward(request,response);
            
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
        String username = (String)session.getAttribute("admin");
        String password = (String)session.getAttribute("pass");    
        
        // recuperer la liste des personne from Personne
            TraitPersonne listNomPersonne = new TraitPersonne();
                List<Personne> listNom = listNomPersonne.listPersonne(username);
                
            // stocker ces resultats dans un model de personne
            PersonneModel persoModel = new PersonneModel();
                persoModel.setList(listNom);
        
        request.setAttribute("list",listNom);    
        getServletContext().getRequestDispatcher("/WEB-INF/admin/modif_compte.jsp").forward(request,response);

}else{
            getServletContext().getRequestDispatcher("/WEB-INF/NotFound.jsp").forward(request,response);

}

        
         
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String page = (String)request.getParameter("page");
        
        if(page.equals("ajouterEns")){  
            
           Personne p = new Personne ();

                            p.setNom((String)request.getParameter("nom"));
                            p.setPrenom((String)request.getParameter("prenom"));
                            p.setCin((String)request.getParameter("CIN"));
                            p.setEmail((String)request.getParameter("email"));
                            p.setTel((String)request.getParameter("tel"));
                            p.setPassword((String)request.getParameter("pass"));                            
                            p.setId_fct(Long.parseLong(request.getParameter("fct")));
                            p.setId_grade(Long.parseLong(request.getParameter("grd")));

            TraitPersonne ajouterPerson = new TraitPersonne();

                            String validationAjout = ajouterPerson.ajouterPerson( p );

            PersonneModel perModel = new PersonneModel();
                            perModel.setP(p);
                            perModel.setValidationAjout(validationAjout);

            request.setAttribute("msgModel",perModel);
            
            
            
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
            
            
            
           
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherEns.jsp").forward(request,response);
            
}else if(page.equals("ajouterModule")){  
        // ajouter d'abord le module dans la table module
            Module m = new Module ();

                           m.setNom_mod((String)request.getParameter("Module"));
                           m.setID_RESP(Long.parseLong(request.getParameter("Responsable")));
                           m.setID_SEM(Long.parseLong(request.getParameter("Semetre")));
                           m.setID_TYPE(Long.parseLong(request.getParameter("Type")));
                           
            // stocker les donnees ajoutee dans le model de module
            ModuleModel moduleModel = new ModuleModel();
                            
                            moduleModel.setModule(m);
                                                                 
            //inserer le module dans BD
            TraitModule ajouterModule = new TraitModule();
                            //inserer resultat retourne(ID module ajouté) dans le model
                           Long id = ajouterModule.ajouterModule(m);
                           
                           moduleModel.setId_return(id);
                           
        // recuperer le ID de ce module ajouté et son filiere et les stockenet dans la table mod_fil
        
            Mod_Fil mod_fil = new Mod_Fil();
                
                mod_fil.setID_MODULE(moduleModel.getId_return());
                mod_fil.setID_FIL(Long.parseLong(request.getParameter("Filiere")));
            
            // stocker ces donnes dans le model mod_fil
                
            Mod_FilModel mod_filModel = new Mod_FilModel();
                  
                mod_filModel.setMod_fil(mod_fil);
                
            //stocker le ID de module et filiere dans la table mod_fil dans BD
            
            TraitMod_Fil trait_m_f = new TraitMod_Fil();
                trait_m_f.ajouter(mod_fil);
                
                
                // recuperer la liste des module from module
            TraitModule traitModule = new TraitModule();
                List<Module> listModule = traitModule.listModules("allModules");
                
            // stocker ces resultats dans un model de module
                moduleModel.setListModules(listModule);
                            
            //recupere la liste d'ensengnement (responsable,cours,td,tp,semetre,filiere,type) pour chaque module avec leur nom
                   
                List<ModuleModel> list_apres_trans = traitModule.transformer_IDs(listModule);
                
            //stocker ces resultat dans le model ModuleModel
                       
                moduleModel.setList_trasnformation(list_apres_trans);
                
            
            request.setAttribute("listTransformationEnNom",moduleModel);
                
                
                            
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherModule.jsp").forward(request,response);
            
        }else if(page.equals("modification")){ 
            
            String id = (String)request.getParameter("id_ens");
            String nom = (String)request.getParameter("nom");
            String m1 = (String)request.getParameter("m1");
            String m2 = (String)request.getParameter("m2");
            String m3 = (String)request.getParameter("m3");
 
            request.setAttribute("id",id);
            request.setAttribute("nom",nom);
            request.setAttribute("m1",m1);
            request.setAttribute("m2",m2);
            request.setAttribute("m3",m3);
            
            // charger la liste des types
            
            TraitType traitType = new TraitType();
            
                List<Type> listType = traitType.listType();
            
            request.setAttribute("listType",listType);

            getServletContext().getRequestDispatcher("/WEB-INF/admin/modification.jsp").forward(request,response);

}else if(page.equals("validermodule")){                     
            
            String id_ens = (String)request.getParameter("id");
            
            String m1 = (String)request.getParameter("m1");
            String m2 = (String)request.getParameter("m2");
            String m3 = (String)request.getParameter("m3");
            
            String type1 = (String)request.getParameter("type1");
            String type2 = (String)request.getParameter("type2");
            String type3 = (String)request.getParameter("type3");
            
            //stocker ces donnees dans le module et class metier
            
            Module module1 = new Module();
            
                if(type1.equals("cours")){
                    module1.setID_ENS_CRS(Long.parseLong(id_ens));
                    module1.setNom_mod(m1);

                    //stocker dans model
                    ModuleModel module = new ModuleModel();
                        module.setModule(module1);

                }else if(type1.equals("TD")){
                    module1.setID_ENS_TD(Long.parseLong(id_ens));
                    module1.setNom_mod(m1);

                    //stocker dans model
                    ModuleModel module = new ModuleModel();
                        module.setModule(module1);

                }else if(type1.equals("TP")){
                    module1.setID_ENS_TP(Long.parseLong(id_ens));
                    module1.setNom_mod(m1);

                    //stocker dans model
                    ModuleModel module = new ModuleModel();
                        module.setModule(module1);
                }
                
            Module module2 = new Module();
            
                if(type2.equals("cours")){
                    module2.setID_ENS_CRS(Long.parseLong(id_ens));
                    module2.setNom_mod(m2);

                    //stocker dans model
                    ModuleModel module = new ModuleModel();
                        module.setModule(module2);

                }else if(type2.equals("TD")){
                    module2.setID_ENS_TD(Long.parseLong(id_ens));
                    module2.setNom_mod(m2);

                    //stocker dans model
                    ModuleModel module = new ModuleModel();
                        module.setModule(module2);

                }else if(type2.equals("TP")){
                    module2.setID_ENS_TP(Long.parseLong(id_ens));
                    module2.setNom_mod(m2);

                    //stocker dans model
                    ModuleModel module = new ModuleModel();
                        module.setModule(module2);
                }
                
            Module module3 = new Module();
            
                if(type3.equals("cours")){
                    module3.setID_ENS_CRS(Long.parseLong(id_ens));
                    module3.setNom_mod(m3);

                    //stocker dans model
                    ModuleModel module = new ModuleModel();
                        module.setModule(module3);

                }else if(type3.equals("TD")){
                    module3.setID_ENS_TD(Long.parseLong(id_ens));
                    module3.setNom_mod(m3);

                    //stocker dans model
                    ModuleModel module = new ModuleModel();
                        module.setModule(module3);

                }else if(type3.equals("TP")){
                    module3.setID_ENS_TP(Long.parseLong(id_ens));
                    module3.setNom_mod(m3);

                    //stocker dans model
                    ModuleModel module = new ModuleModel();
                        module.setModule(module3);
                }
            
            TraitModule traitmod = new TraitModule();
            if(!type1.equals("")){
                traitmod.affecterModule(module1);
            }
            if(!type2.equals("")){
                traitmod.affecterModule(module2);
            }
            if(!type3.equals("")){
                traitmod.affecterModule(module3);
            }
            
                    
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
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherEns.jsp").forward(request,response);
            
}else if(page.equals("modification_final")){ 
            
            String id = (String)request.getParameter("id_ens");
            String nom = (String)request.getParameter("nom");
            
            Personne p = new Personne();
                p.setId_pers(Long.parseLong(id));
            
            TraitChoix_module trait_choix = new TraitChoix_module();
            List<Module> listChoix = trait_choix.listChoix_dePerson(p);
            
            if(!listChoix.isEmpty()){
            
            ModuleModel mMod = new ModuleModel();
                mMod.setListModules(listChoix);
            
            request.setAttribute("list",mMod);
            
            /*String m1 = (String)request.getParameter("m1");
            String m2 = (String)request.getParameter("m2");
            String m3 = (String)request.getParameter("m3");*/
 
            request.setAttribute("id",id);
            request.setAttribute("nom",nom);
            /*request.setAttribute("m1",m1);
            request.setAttribute("m2",m2);
            request.setAttribute("m3",m3);*/

            // charger la liste des types
            
            TraitType traitType = new TraitType();
            
                List<Type> listType = traitType.listType();
            
            request.setAttribute("listType",listType);

            getServletContext().getRequestDispatcher("/WEB-INF/admin/modification_final.jsp").forward(request,response);
            
            }else{
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
               request.setAttribute("msg","cet enseignant n'a pas encore choisis ses modules !"); 
                
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherEns.jsp").forward(request,response);
            }
            
}else if(page.equals("supprimer")){ 
            
            String id = (String)request.getParameter("id_module");
            Long id_module = Long.parseLong(id);
            TraitModule traitModule = new TraitModule();

                traitModule.supprimer(id_module);

            // recuperer la liste des module from module
                List<Module> listModule = traitModule.listModules("allModules");
                
            // stocker ces resultats dans un model de module
            ModuleModel moduleModel = new ModuleModel();
                moduleModel.setListModules(listModule);
                            
            //recupere la liste d'ensengnement (responsable,cours,td,tp,semetre,filiere,type) pour chaque module avec leur nom
                   
                List<ModuleModel> list_apres_trans = traitModule.transformer_IDs(listModule);
                
            //stocker ces resultat dans le model ModuleModel
                       
                moduleModel.setList_trasnformation(list_apres_trans);

                request.setAttribute("listTransformationEnNom",moduleModel);
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherModule.jsp").forward(request,response);

}else if(page.equals("activer")){ 
            
            String id = (String)request.getParameter("id_ens");
            Long id_ens = Long.parseLong(id);
            
            TraitPersonne traitPerso = new TraitPersonne();
            
                traitPerso.activer(id_ens);
                
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
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherEns.jsp").forward(request,response);

}else if(page.equals("desactiver")){ 
            
            String id = (String)request.getParameter("id_ens");
            Long id_ens = Long.parseLong(id);
            
            TraitPersonne traitPerso = new TraitPersonne();
            
                traitPerso.desactiver(id_ens);
                
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
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherEns.jsp").forward(request,response);

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
            
            
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherEns.jsp").forward(request,response);

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
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherModule.jsp").forward(request,response);

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
            
            getServletContext().getRequestDispatcher("/WEB-INF/admin/afficherEns.jsp").forward(request,response);

}else{
            getServletContext().getRequestDispatcher("/WEB-INF/NotFound.jsp").forward(request,response);

        }
        
    }  
}
