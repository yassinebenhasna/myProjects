using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Sw1Rest.affichageEntities
{
    public class EtudModule
    {
        public List<ModuleAffich> Module { get; set; }

        public string Nom { get; set; }

        public string Prenom { get; set; }

        public string Master { get; set; }

        public string Moyenne { get; set; }

        public string Mention { get; set; }

    }
}