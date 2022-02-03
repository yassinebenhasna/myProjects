using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Sw1Rest.affichageEntities;

namespace Sw1Rest
{
    public class AttestationService
    {


        public string Nom { get; set; }
        public string Prenom { get; set; }
        public string Master { get; set; }

        List<double> moySemestres = new List<double>();

        public List<double> getMoySemestres()
        {
            return moySemestres;
        }



        public Dictionary<string, string> getNotesModulesValides(EtudModule rlvSem)
        {

            Dictionary<string, string> notesFinal = new Dictionary<string, string>();//la liste return qui contient les notes final de chaque modules

            string nom = rlvSem.Nom;
            Nom = nom;
            string prenom = rlvSem.Prenom;
            Prenom = prenom;
            string master = rlvSem.Master;
            Master = master;
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
                        notesFinal.Add(nameModule, VARIABLE.Note);

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

                        notesFinal.Add(nameModule, noteMax);

                    }
                }
            }

            return notesFinal;
        }

        public bool isYearValid(Dictionary<string, string> sem1, Dictionary<string, string> sem2, string sem)
        {
            Dictionary<string, string> notesYear = new Dictionary<string, string>();
            //notesYear.Union(sem1).Union(sem2);

            foreach (var VARIABLE in sem1)
            {
                notesYear.Add(VARIABLE.Key , VARIABLE.Value);
            }
            foreach (var VARIABLE in sem2)
            {
                notesYear.Add(VARIABLE.Key, VARIABLE.Value);
            }

            bool isYearValid = false;

            //verifier est ce que les notes sont trouvés ou qu'ils sont en cours 
            if (notesYear.All(pair => !pair.Value.Equals("en cours")))
            {

                //verifier is year is valid or not
                    isYearValid = notesYear.All(pair => Int32.Parse(pair.Value) >= 10);

                    if (isYearValid == false)
                    {
                        int nbrModuleInf10Sup7 =
                            notesYear.Count(pair => Int32.Parse(pair.Value) < 10 && Int32.Parse(pair.Value) >= 7);

                        if (nbrModuleInf10Sup7 == 1 /* && notesYear.Sum(pair => Int32.Parse(pair.Value)) / 12 >= 10*/)
                        {
                            double moySem1 = 0, moySem2 = 0;

                            foreach (var VARIABLE in sem1)
                            {
                                moySem1 += Convert.ToDouble(VARIABLE.Value);
                            }

                            moySem1 /= 6;

                            foreach (var VARIABLE in sem2)
                            {
                                moySem2 += Convert.ToDouble(VARIABLE.Value);
                            }

                            if (!sem.Equals("sem34"))
                                moySem2 /= 6;

                            moySemestres.Add(moySem1);
                            moySemestres.Add(moySem2);

                            if (((moySem1 + moySem2) / 2) >= 10)
                                isYearValid = true;

                        }

                    }
                    else
                    {
                        double moySem1 = 0, moySem2 = 0;

                        foreach (var VARIABLE in sem1)
                        {
                            moySem1 += Convert.ToDouble(VARIABLE.Value);
                        }

                        moySem1 /= 6;

                        foreach (var VARIABLE in sem2)
                        {
                            moySem2 += Convert.ToDouble(VARIABLE.Value);
                        }

                        if (!sem.Equals("sem34"))
                            moySem2 /= 6;

                        moySemestres.Add(moySem1);
                        moySemestres.Add(moySem2);
                    }

            }

            return isYearValid;
        }
    }
}