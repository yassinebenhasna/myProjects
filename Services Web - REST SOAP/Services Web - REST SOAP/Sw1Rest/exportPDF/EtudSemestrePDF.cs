using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Web;
using iTextSharp.text;
using iTextSharp.text.pdf;
using Sw1Rest.affichageEntities;
using Color = iTextSharp.text.Color;
using Font = iTextSharp.text.Font;

namespace Sw1Rest.exportPDF
{
    public class EtudSemestrePDF
    {

        DataTable makeDataTable(List<EtudSemestre> listPresoEnv)
        {
            //Create etudTable table object
            DataTable etudTable = new DataTable();

            //Define columns
            etudTable.Columns.Add("Nom");
            etudTable.Columns.Add("Prenom");
            etudTable.Columns.Add("Annee");

            if (listPresoEnv != null)
            {
                foreach (var VARIABLE in listPresoEnv)
                {
                    etudTable.Rows.Add(VARIABLE.Nom, VARIABLE.Prenom, VARIABLE.Annee);
                }
            }

            return etudTable;
        }

        public void generatePDF(List<EtudSemestre> listPresoEnv, string masterParam, string semestreParam)
        {
            DataTable etudTable = makeDataTable(listPresoEnv );

            System.IO.FileStream fs = new FileStream(AppDomain.CurrentDomain.BaseDirectory + @"\filesExported\" + "EtudSemestre.pdf", 
                                                        FileMode.Create, 
                                                            FileAccess.Write, 
                                                                FileShare.None
                                                    );
            //le document retourné
            Document document = new Document();
            document.SetPageSize(iTextSharp.text.PageSize.A4);
            PdfWriter writer = PdfWriter.GetInstance(document, fs);
            document.Open();

            //Report Header
            BaseFont bfntHead = BaseFont.CreateFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font fntHead = new Font( bfntHead, 16, 1, Color.CYAN);
            Paragraph prgHeading = new Paragraph();
            prgHeading.Alignment = Element.ALIGN_CENTER;
            prgHeading.Add(new Chunk("Les étudiants inscrits.", fntHead));
            document.Add(prgHeading);

            //Author
            Paragraph prgMasterSem = new Paragraph();
            BaseFont btnAuthor = BaseFont.CreateFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font fntAuthor = new Font(btnAuthor, 8, 2, Color.GRAY);
            prgMasterSem.Alignment = Element.ALIGN_RIGHT;
            prgMasterSem.Add(new Chunk("Master : "+masterParam, fntAuthor));
            prgMasterSem.Add(new Chunk("\nSemestre : "+semestreParam /*+ DateTime.Now.ToShortDateString() */, fntAuthor) );
            document.Add(prgMasterSem);

            //Add a line seperation
            Paragraph p = new Paragraph(new Chunk(new iTextSharp.text.pdf.draw.LineSeparator(0.0F, 100.0F, Color.BLACK, Element.ALIGN_LEFT, 1)));
            document.Add(p);

            //Add line break
            document.Add(new Chunk("\n", fntHead));

            //Write the table
            PdfPTable table = new PdfPTable(etudTable.Columns.Count);

            //Table header
            BaseFont btnColumnHeader = BaseFont.CreateFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font fntColumnHeader = new Font(btnColumnHeader, 10, 1, Color.WHITE);
            for (int i = 0; i < etudTable.Columns.Count; i++)
            {
                PdfPCell cell = new PdfPCell();
                cell.BackgroundColor = Color.GRAY;
                cell.AddElement(new Chunk(etudTable.Columns[i].ColumnName.ToUpper(), fntColumnHeader));
                table.AddCell(cell);
            }
            //table Data
            for (int i = 0; i < etudTable.Rows.Count; i++)
            {
                for (int j = 0; j < etudTable.Columns.Count; j++)
                {
                    table.AddCell(etudTable.Rows[i][j].ToString());
                }
            }

            document.Add(table);
            document.Close();
            writer.Close();
            fs.Close();

        }

    }
}