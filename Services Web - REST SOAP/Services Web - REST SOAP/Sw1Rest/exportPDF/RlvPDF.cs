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
    public class RlvPDF
    {

        DataTable rlvTable(EtudModule etudModule)
        {

            List<ModuleAffich> ModulesForEtud = etudModule.Module;

            //Create moduleTable table object
            DataTable moduleTable = new DataTable();

            //Define columns
            moduleTable.Columns.Add("Module");
            moduleTable.Columns.Add("Etat");
            moduleTable.Columns.Add("Session");
            moduleTable.Columns.Add("Note");
            moduleTable.Columns.Add("Année");

            if (ModulesForEtud != null)
            {
                foreach (var VARIABLE in ModulesForEtud)
                {
                    string etat = VARIABLE.Etat;
                    string note = VARIABLE.Note;

                    if (etat.Equals("0"))
                    {
                        etat = "PRS";
                    }
                    else if (etat.Equals("-1"))
                    {
                        etat = "ABS";
                        note = "ABS";
                    }
                    else
                    {
                        etat = "ABSNJ";
                        note = "ABS";
                    }

                    moduleTable.Rows.Add(VARIABLE.Module, etat, VARIABLE.Session, note, VARIABLE.Annee);

                }

            }

            return moduleTable;
        }

        public void generatePDF(EtudModule etudModule, string apogeParam, string semestreParam)
        {
            DataTable etudTable = rlvTable(etudModule );

            System.IO.FileStream fs = new FileStream(AppDomain.CurrentDomain.BaseDirectory + @"\filesExported\" + "rlvEtud.pdf", 
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
            prgHeading.Add(new Chunk("Relevé de notes", fntHead));
            document.Add(prgHeading);

            //Author
            Paragraph prgMasterSem = new Paragraph();
            BaseFont btnAuthor = BaseFont.CreateFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font fntAuthor = new Font(btnAuthor, 8, 2, Color.GRAY);
            prgMasterSem.Alignment = Element.ALIGN_RIGHT;
            prgMasterSem.Add(new Chunk("Nom : " + etudModule.Nom, fntAuthor));
            prgMasterSem.Add(new Chunk("\nPrenom : " + etudModule.Prenom, fntAuthor));
            prgMasterSem.Add(new Chunk("\nMaster : "+etudModule.Master, fntAuthor));
            prgMasterSem.Add(new Chunk("\nSemestre : "+semestreParam /* DateTime.Now.ToShortDateString()*/ , fntAuthor));
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


            Paragraph prgMasterSem1 = new Paragraph();
            BaseFont btnAuthor1 = BaseFont.CreateFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font fntAuthor1 = new Font(btnAuthor1, 8, 1, Color.BLACK);
            prgMasterSem1.Alignment = Element.ALIGN_LEFT;
            prgMasterSem1.Add(new Chunk("\nMoyenne : " + etudModule.Moyenne, fntAuthor1));
            prgMasterSem1.Add(new Chunk("\nMention : " + etudModule.Mention, fntAuthor1));
            document.Add(prgMasterSem1);

            document.Close();
            writer.Close();
            fs.Close();

        }

    }
}