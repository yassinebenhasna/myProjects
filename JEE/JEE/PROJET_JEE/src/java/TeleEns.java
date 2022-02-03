import WEB.MODEL.Infos_perso_module;
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

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/TeleEns"})
public class TeleEns extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Infos_perso_module info = (Infos_perso_module)request.getAttribute("list_pers_mod");
            List<Infos_perso_module> list_ens = info.getList_info_mod_ens();
                    
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        try {
       
                    try {
                        Document document = new Document();
                        PdfWriter.getInstance(document, out);

                        document.open();

                        PdfPTable table = new PdfPTable(4);
                        PdfPCell cellule1 = new PdfPCell(new Paragraph("Enseignant", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));
                        PdfPCell cellule2 = new PdfPCell(new Paragraph("cours", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));
                        PdfPCell cellule3 = new PdfPCell(new Paragraph("TD", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));
                        PdfPCell cellule4 = new PdfPCell(new Paragraph("TP", FontFactory.getFont("Arial", 10, Font.BOLD, BaseColor.RED)));
                        
                        table.addCell(cellule1);
                        table.addCell(cellule2);
                        table.addCell(cellule3);
                        table.addCell(cellule4);
                        
                        for(int i = 0; i<list_ens.size(); i++){
                        
                            table.addCell(list_ens.get(i).getP().getNom());
                            table.addCell(list_ens.get(i).getModule_cours());
                            table.addCell(list_ens.get(i).getModule_TD());
                            table.addCell(list_ens.get(i).getModule_TP());
                        }
                                                
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
