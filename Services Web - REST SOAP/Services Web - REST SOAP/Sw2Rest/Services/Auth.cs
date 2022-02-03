using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Web;

namespace Sw2Rest.Services
{
    public class Auth
    {

        public string verifierAuth(string apoge, string password)
        {
            string msgResult = null;

            using (SWDBEntities2 dbCnx = new SWDBEntities2())
            {

                Personne personne = dbCnx.Personnes.FirstOrDefault(perso => perso.NApoge == apoge && perso.Password == password);

                if (personne != null)
                {
                    string role = personne.Role;

                    if (role.Equals("admin"))
                        msgResult = "admin";
                    else
                        msgResult = "etud";

                }
                else
                {
                    msgResult = "Access Denied";
                }


            }

            return msgResult;
        }

    }
   
}