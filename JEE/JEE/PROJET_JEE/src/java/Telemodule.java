

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Telemodule"})
public class Telemodule extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String nom_module = (String)request.getAttribute("nom_module");
            String nom_ens_cours = (String)request.getAttribute("nom_ens_cours");
            String nom_ens_td = (String)request.getAttribute("nom_ens_td");
            String nom_ens_tp = (String)request.getAttribute("nom_ens_tp");

        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        try {

            
               
                
                    try {
                        Document document = new Document();
                        PdfWriter.getInstance(document, out);

                        document.open();

                        PdfPTable table = new PdfPTable(4);
                        PdfPCell cellule1 = new PdfPCell(new Paragraph("Module", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));
                        PdfPCell cellule2 = new PdfPCell(new Paragraph("Ens-cours", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));
                        PdfPCell cellule3 = new PdfPCell(new Paragraph("Ens-TD", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));
                        PdfPCell cellule4 = new PdfPCell(new Paragraph("Ens-TP", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));
                        
                        table.addCell(cellule1);
                        table.addCell(cellule2);
                        table.addCell(cellule3);
                        table.addCell(cellule4);
                        
                        table.addCell(nom_module);
                        table.addCell(nom_ens_cours);
                        table.addCell(nom_ens_td);
                        table.addCell(nom_ens_tp);
                        
                        document.add(table);

                        document.close();

                    } catch (Exception ex) {
                        ex.getMessage();
                    }

                

            

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
