using Sw1Rest.affichageEntities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Sw1Rest.Services
{
    public class RlvSrv
    {

        public Dictionary<string, string> getNotesModulesValides(EtudModule rlvSem)
        {

            Dictionary<string, string> notesFinal = new Dictionary<string, string>();//la liste return qui contient les notes final de chaque modules

           
            List<ModuleAffich> modulesForEtud = rlvSem.Module;


            foreach (var VARIABLE in modulesForEtud)
            {
                //get chaque module name
                string nameModule = VARIABLE.Module;

                if (!notesFinal.ContainsKey(nameModule))
                {
                    //get all infos for this module
                    List<ModuleAffich> infosNameModule =
                        modulesForEtud.FindAll(infos => infos.Module.Equals(nameModule));

                    //traiter ces data
                    if (infosNameModule.Count == 1) //donc ce module validé au session normal
                    {
                        notesFinal.Add(nameModule, VARIABLE.Note + ";" + VARIABLE.Annee);

                    }
                    else
                    {
                        //find l'année la plus recente
                        string annee = infosNameModule.Max(infos => Int32.Parse(infos.Annee.Split('-')[0])).ToString();

                        List<ModuleAffich> notesAnnee =
                            infosNameModule.FindAll(infos => infos.Annee.Split('-')[0].Equals(annee));

                        string noteMax = notesAnnee.Max(

                            infos => Int32.Parse(infos.Note = (infos.Etat.Equals("0")) ? infos.Note : "0")

                        ).ToString();

                        notesFinal.Add(nameModule, noteMax + ";" + VARIABLE.Annee);

                    }
                }
            }

            return notesFinal;
        }


        public string getMoyenne(Dictionary<string, string> notesModules)
        {
            double somme = 0;

            foreach (var variable in notesModules)
            {

                somme += Convert.ToInt32(variable.Value.Split(';')[0]);

            }

            return (somme / 6).ToString();
        }

    }
}