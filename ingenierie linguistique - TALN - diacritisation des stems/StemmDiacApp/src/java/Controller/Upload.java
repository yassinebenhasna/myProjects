/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.*;

/**
 *
 * @author yassine
 */
@WebServlet(name = "Upload", urlPatterns = {"/Upload"})
public class Upload extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 10 * 1024 * 1024;
    private int maxMemSize = 4 * 1024 * 1024;
    private File file;
    
    

    public void init() {
        // Get the file location where it would be stored.
        String webPath = getServletContext().getRealPath("/");
        filePath = webPath + "..\\..\\src\\java\\filesUploaded\\";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with " + getClass().getName() + ": POST method required.");
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        
        String msgSucc = "", msgFailed = "";

        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();

        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File(filePath));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();


            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    String contentType = fi.getContentType();
                    if( contentType.equals("text/plain") ){
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        //String fileName = fi.getName();
                        String fileName = "clientFile.txt";

                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();

                        // Write the file
                        if (fileName.lastIndexOf("\\") >= 0) {
                            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                        } else {
                            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                        }
                        fi.write(file);
                        //out.println("Uploaded Filename: " + fileName + "<br>");
                        msgSucc = "Votre fichier : \"" + fi.getName() + "\" est prêt .";
                    }else{
                        msgFailed = "le format du fichier n'est pas compatible!\nChoisissez un format .txt";
                        break;
                    }
                }
            }
            
            
                request.setAttribute("msgSucc", msgSucc);
                request.setAttribute("msgFailed", msgFailed);
                
                request.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            
        } catch (Exception ex) {
            System.out.println("ERROR Upload Class ------------------------------> " + ex);
        }
    }

}
