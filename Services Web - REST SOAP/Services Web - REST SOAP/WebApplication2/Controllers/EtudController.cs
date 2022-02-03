using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using WebApplication2.Models;

namespace WebApplication2.Controllers
{
    public class EtudController : Controller
    {
        CnxRequest req = new CnxRequest();

        // GET: User
        public ActionResult Home()
        {
            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
                {
                    return View();
                }
                else
                {
                    return RedirectToAction("Login", "Login");
                }


            }
            catch (Exception ex)
            {
                return RedirectToAction("Login", "Login");
            }
        }







        [HttpPost]
        public ActionResult Rlv(string sem)
        {

            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
                {

                    
                        Stream pdfData = req.sendRequest2("58584", "Service1.svc", "rlvPDF/" + Session["apoge"] + "/" + sem, "GET");
                        //byte[] byteArray = Encoding.ASCII.GetBytes(pdfData);

                        //MemoryStream stream = new MemoryStream(byteArray);
                        //Stream stream2 = stream;

                        //StreamReader reader = new StreamReader(new MemoryStream(byt));
                        //string text = reader.ReadToEnd();

                        return File(pdfData, "application/pdf", "rlv.pdf");
                    
                }
                else
                {
                    return RedirectToAction("Login", "Login");
                }


            }
            catch (Exception ex)
            {
                return RedirectToAction("Login", "Login");
            }


        }





        
        public ActionResult Att()
        {

            try
            {
                if (Request.Cookies["apoge"] == null || Session["apoge"] == null)
                {
                    return RedirectToAction("Login", "Login");
                }

                if (Request.Cookies["apoge"].Value == Session["apoge"].ToString())
                {


                    Stream pdfData = req.sendRequest2("58584", "Service1.svc", "att/" + Session["apoge"], "GET");

                    Stream pdfData2 = pdfData;
                    //byte[] byteArray = Encoding.ASCII.GetBytes(pdfData);

                    //MemoryStream stream = new MemoryStream(byteArray);
                    //Stream stream2 = stream;

                    StreamReader reader = new StreamReader(pdfData2);
                    string text = reader.ReadToEnd();

                    if (text == "pas encore")
                    {
                        TempData["msg"] = "Ooops! Vous pouvez pas actuellement récupérer cette Attestation (un ou plusieurs semestre(s) ne sont pas validés)";
                        return RedirectToAction("Home", "Etud");
                    }


                    Stream pdfData3 = req.sendRequest2("58584", "Service1.svc", "att/" + Session["apoge"], "GET");

                    return File(pdfData3, "application/pdf", "attestation.pdf");


                }
                else
                {
                    return RedirectToAction("Login", "Login");
                }


            }
            catch (Exception ex)
            {
                return RedirectToAction("Login", "Login");
            }


        }



        public ActionResult LogOut()
        {
            Session.Clear();
            Response.Cookies.Clear();
            return RedirectToAction("Login", "Login");
        }


    }
}