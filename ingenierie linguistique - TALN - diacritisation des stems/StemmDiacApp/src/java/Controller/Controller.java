/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Crawler.JsoupCrawler;
import Services.Service;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yassine
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller", ""})
public class Controller extends HttpServlet {                

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String webPath = getServletContext().getRealPath("/");
        String srcPath = webPath + "..\\..\\src\\java\\";
        
        Service srv = new Service();
            srv.setSrcPath(srcPath);

            
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        
        /*request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        PrintWriter pwr = response.getWriter();
        */
        /*System.out.println("defaultCharacterEncoding by property: " + System.getProperty("file.encoding"));
        System.out.println("defaultCharacterEncoding by code: " + getDefaultCharEncoding());
        System.out.println("defaultCharacterEncoding by charSet: " + Charset.defaultCharset());
      
        System.setProperty("file.encoding", "UTF-8");
        
        System.out.println("defaultCharacterEncoding by property after updating file.encoding : " + System.getProperty("file.encoding"));
        System.out.println("defaultCharacterEncoding by code after updating file.encoding : " + getDefaultCharEncoding());
        System.out.println("defaultCharacterEncoding by java.nio.Charset after updating file.encoding : " + Charset.defaultCharset());
        */
        //String x = "فييييييييييييييييبي";
        //pwr.print("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh "+x);
        /*
        System.out.println("Controller.Controller.doGet()yyyyyyyyyy = " + getServletContext().getInitParameter("file-upload"));

        System.out.println("test UTF8 = " + new String( x.getBytes("UTF8") ) );
        System.out.println("test Cp1252 = " + new String( x.getBytes("Cp1252") ) );
        System.out.println("test windows-1252 = " + new String( x.getBytes("windows-1252") ) );
        */
        
        //System.out.println("encodiing = " + getDefaultCharEncoding());
        
        /*List<String> fileContent = srv.chargerFichier("testTxt.txt");
        
        for(String v : fileContent)
            System.out.println("Controller.Controller.doGet() = " + v);
        
        System.out.println("x = " + x);
        System.out.println("x 16 = " + new String(x.getBytes(), StandardCharsets.UTF_16));
        System.out.println("x 8 = " + new String(x.getBytes(), StandardCharsets.UTF_8));
        System.out.println("x ascii = " + new String(x.getBytes(), StandardCharsets.US_ASCII));
        System.out.println("x win1252 = " + new String(x.getBytes("Windows-1252")));
        System.out.println("x Cp1252 = " + new String(x.getBytes("Cp1252")));
        System.out.println("x iso = " + new String(x.getBytes(StandardCharsets.ISO_8859_1)));
                
        byte[] bytes = x.getBytes("Cp1252");
        x = new String(bytes, "UTF-8");

        
        System.out.println("x = " + x);
        
        //request.setAttribute("info", fileContent);

        */
    
        
    }

    
    /*public static String getDefaultCharEncoding(){
        byte [] bArray = {'f'};
        InputStream is = new ByteArrayInputStream(bArray);
        InputStreamReader reader = new InputStreamReader(is);
        String defaultCharacterEncoding = reader.getEncoding();
        return defaultCharacterEncoding;
    }*/
}
