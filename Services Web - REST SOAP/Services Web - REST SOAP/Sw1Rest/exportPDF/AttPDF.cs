using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using iTextSharp.text;
using iTextSharp.text.pdf;

namespace Sw1Rest.exportPDF
{
    public class AttPDF
    {
        public void genererAttPDF(string nApoge, string nom, string prenom, string master, string mention)
        {

            
            System.IO.FileStream fs = new FileStream(AppDomain.CurrentDomain.BaseDirectory + @"\filesExported\" + "attestation.pdf",
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
            Font fntHead = new Font(bfntHead, 16, 1, Color.CYAN);
            Paragraph prgHeading = new Paragraph();
            prgHeading.Alignment = Element.ALIGN_CENTER;
            prgHeading.Add(new Chunk("ATTESTATION DE REUSSITE AU DIPLOME", fntHead));
            document.Add(prgHeading);

            //Add 4 line break
            document.Add(new Chunk("\n\n\n\n", fntHead));

            //add infos
            BaseFont bfntCntent = BaseFont.CreateFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font fntCntent = new Font(bfntCntent, 12, 1, Color.BLACK);
            Paragraph prgCntent = new Paragraph();
            prgCntent.Alignment = Element.ALIGN_CENTER;

            prgCntent.Add(new Chunk("Le Doyen de la faculté des sciences d'Oujda atteste que:", fntCntent));
            prgCntent.Add(new Chunk("\n\n\tL'étudiant(e): " + nom.ToUpper() + " " + prenom.ToUpper(), fntCntent));
            prgCntent.Add(new Chunk("\n\n\tCode Apogé de l'étudiant: " + nApoge, fntCntent));
            prgCntent.Add(new Chunk("\n\na obtenu le Master", fntCntent));
            prgCntent.Add(new Chunk("\n\n\tFilère: " + master, fntCntent));
            prgCntent.Add(new Chunk("\n\n\tMention: " + mention.ToUpper(), fntCntent)); 

            document.Add(prgCntent);

            Paragraph dateCnt = new Paragraph();
            dateCnt.Alignment = Element.ALIGN_RIGHT;

            dateCnt.Add(new Chunk("\n\nFait le: " + DateTime.Now.ToShortDateString(), fntCntent));

            document.Add(dateCnt);



            document.Close();
            writer.Close();
            fs.Close();

            

        
        }
    }
}