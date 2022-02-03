using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Web;

namespace WebApplication2.Models
{
    public class CnxRequest
    {
        public string sendRequest(string portNumber, string serviceName, string operation, string requestMethod )
        {

            string response = String.Empty;

            WebRequest restRequest = WebRequest.Create(@"http://localhost:" + portNumber + "/" + serviceName + "/" + operation);

            restRequest.Method = requestMethod;

            HttpWebResponse restResponse = restRequest.GetResponse() as HttpWebResponse;

            using (Stream restResponseStream = restResponse.GetResponseStream())
            {
                StreamReader streamReader = new StreamReader(restResponseStream, Encoding.UTF8);
                response = streamReader.ReadToEnd();

            }


            return response;
            /*
                        WebRequest restRequest = WebRequest.Create(@"http://localhost:" + portNumber + "/" + serviceName + "/" + operation);

                        restRequest.Method = requestMethod;

                        WebResponse restResponse = restRequest.GetResponse() as WebResponse;

                        using (Stream restResponseStream = restResponse.GetResponseStream())
                        {
                            StreamReader streamReader = new StreamReader(restResponseStream, Encoding.UTF8);
                            response = streamReader.ReadToEnd();
                        }

                        return response;*/
        }

        public Stream sendRequest2(string portNumber, string serviceName, string operation, string requestMethod)
        {

            string response = String.Empty;

            // Creates an HttpWebRequest with the specified URL.
            HttpWebRequest myHttpWebRequest = (HttpWebRequest)WebRequest.Create(@"http://localhost:" + portNumber + "/" + serviceName + "/" + operation);
            // Sends the HttpWebRequest and waits for the response.			
            HttpWebResponse myHttpWebResponse = (HttpWebResponse)myHttpWebRequest.GetResponse();
            // Gets the stream associated with the response.
            Stream receiveStream = myHttpWebResponse.GetResponseStream();
            Encoding encode = System.Text.Encoding.GetEncoding("utf-8");
            // Pipes the stream to a higher level stream reader with the required encoding format.
            StreamReader readStream = new StreamReader(receiveStream, encode);
            /*
            WebRequest restRequest = WebRequest.Create(@"http://localhost:" + portNumber + "/" + serviceName + "/" + operation);

            restRequest.Method = requestMethod;

            HttpWebResponse restResponse = restRequest.GetResponse() as HttpWebResponse;

            using (Stream restResponseStream = restResponse.GetResponseStream())
            {
                StreamReader streamReader = new StreamReader(restResponseStream, Encoding.UTF8);
                response = streamReader.ReadToEnd();
                
            }

            byte[] byteArray = Encoding.ASCII.GetBytes(response);

            File.WriteAllBytes(AppDomain.CurrentDomain.BaseDirectory + @"\filesExported\" + "EtudSemestre.pdf", byteArray);

            return response;*/

            return receiveStream;
        }

    }
}