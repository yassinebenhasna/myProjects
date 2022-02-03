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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/pdf"})
public class pdf extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();
        try {

            try {
                Connection connexion = null;
                Statement st = null;
                ResultSet rs = null;

                Class.forName("com.mysql.jdbc.Driver");
                connexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/projet1", "root", "");
                st = (Statement) connexion.createStatement();
                rs = st.executeQuery("SELECT * FROM fonction");
                if (connexion != null) {
                    try {
                        Document document = new Document();
                        PdfWriter.getInstance(document, out);

                        document.open();

                        PdfPTable table = new PdfPTable(2);
                        PdfPCell cellule1 = new PdfPCell(new Paragraph("ID-fct", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell cellule2 = new PdfPCell(new Paragraph("NOM-fct", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        table.addCell(cellule1);
                        table.addCell(cellule2);
                        while (rs.next()) {
                            table.addCell(rs.getString(1));
                            table.addCell(rs.getString(2));

                        }
                        document.add(table);

                        document.close();

                    } catch (Exception ex) {
                        ex.getMessage();
                    }

                }

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
