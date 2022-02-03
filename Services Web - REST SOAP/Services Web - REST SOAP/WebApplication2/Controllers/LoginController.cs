using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.Mvc;
using WebApplication2.Models;

namespace WebApplication2.Controllers
{
    public class LoginController : Controller
    {
        CnxRequest req = new CnxRequest();

        // GET: Login
        public ActionResult Login()
        {
            return View();
        }


        [HttpPost]
        public ActionResult trtLogin(string nApoge, string Cne)
        {
            if (nApoge == string.Empty || Cne == String.Empty)
            {
                ViewBag.errMsg = "Votre login ou mot de passe est incorrecte";
                return View("Login");
            }

            SqlConnection connexion = new SqlConnection();
            connexion.ConnectionString = ConfigurationManager.ConnectionStrings["DBconnexion"].ToString();
            connexion.Open();
            SqlCommand cmd = new SqlCommand("select NApoge,Password,Role,Nom from Personne where [NApoge] = @param1  and [Password] = @param2 ", connexion);
            cmd.Parameters.AddWithValue("param1",nApoge);
            cmd.Parameters.AddWithValue("param2", Cne);

            List<string> perso = new List<string>();


            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                perso.Add(dr[0].ToString() + ":" + dr[1].ToString() + ":" + dr[2].ToString() + ":" + dr[3].ToString());
            }
            connexion.Close();

            //string resultCnx = req.sendRequest("58584", "Service1.svc", "verify/"+nApoge+"/"+Cne, "GET");
            //resultCnx = Regex.Replace(resultCnx, "<.*/\">","");
            //resultCnx = Regex.Replace(resultCnx, "<.*", "");

            if (perso.Count > 0)
            {

                if (perso[0].Split(':')[2] == "admin")
                {

                    Session["apoge"] = nApoge;
                    Session["pass"] = Cne;
                    Session["role"] = "admin";
                    Session["nom"] = perso[0].Split(':')[3];

                    var cookie = new HttpCookie("apoge");
                    cookie.Value = nApoge;
                    cookie.Expires = DateTime.Now.AddMinutes(30);
                    Response.Cookies.Add(cookie);


                    /*
                    HttpCookie cookie = new HttpCookie("ck");
                    cookie.Values.Add("apoge", nApoge);
                    cookie.Values.Add("pass", Cne);
                    cookie.Values.Add("role", "admin");
    
                    cookie.Expires = DateTime.Now.AddSeconds(10);
    
                    HttpContext.Response.SetCookie(cookie);
                    TempData["myCookie"] = nApoge + "/" + Cne;
    
                    //Response.Cookies.Add(cookie);
                    //Request.Cookies.Add(cookie);
                    */
                    return RedirectToAction("Home", "Admin");

                }
                else if (perso[0].Split(':')[2] == "etud")
                {
                    Session["apoge"] = nApoge;
                    Session["pass"] = Cne;
                    Session["role"] = "etud";
                    Session["nom"] = perso[0].Split(':')[3].ToUpper();

                    var cookie = new HttpCookie("apoge");
                    cookie.Value = nApoge;
                    cookie.Expires = DateTime.Now.AddMinutes(30);
                    Response.Cookies.Add(cookie);

                    return RedirectToAction("Home", "Etud");
                }
                else
                {
                    ViewBag.errMsg = "Votre login ou mot de passe est incorrecte";
                    return View("Login");
                }
            }
            else
            {
                ViewBag.errMsg = "Votre login ou mot de passe est incorrecte";
                return View("Login");
            }
        }
    }
}