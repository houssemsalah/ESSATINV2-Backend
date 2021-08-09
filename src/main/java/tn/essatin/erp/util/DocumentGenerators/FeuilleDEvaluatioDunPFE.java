package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;


import java.io.ByteArrayOutputStream;


public class FeuilleDEvaluatioDunPFE {
    public static ByteArrayOutputStream createDoc( ) {

        Font fontGras = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, BaseColor.BLACK);
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25, BaseColor.BLACK);
        ByteArrayOutputStream response = new ByteArrayOutputStream();


       Document document= new Document();
        try {

            PdfWriter.getInstance(document, response);
            document.setPageSize(PageSize.A4);
            document.setMargins(15f, 15f, 10f, 10f);
            document.open();
            DocumentFunction.ajouterEnteteSpeciale(document, "EVALUATION D'UN PFE", "GES-IMP-27", "02", "22/07/2019", 1,1);
            document.add(new Phrase(" \n"));
            //table 1
            PdfPTable table=new PdfPTable(1);
            table.getDefaultCell().setFixedHeight(20);
            table.setWidthPercentage(100f);
            PdfPCell cell =new PdfPCell(new Paragraph("Date de soutenance : ..../..../........ à .... h .... mn    Salle: ................... ", fontGras));
            table.addCell(cell);
            document.add(table);
            document.add (new Paragraph(" \n "));
            //table A
            PdfPTable tableA=new PdfPTable(1);
            tableA.getDefaultCell().setFixedHeight(20);
            tableA.setWidthPercentage(100f);
            PdfPCell cell1 =new PdfPCell(new Paragraph(" A- TITRE DU SUJET : \n" +
                                                             "    .....................................................................................  " +
                                                             "    .....................................................................................  ", fontGras));
            table.addCell(cell1);
            document.add(tableA);
            document.add (new Paragraph(" \n "));
            // table B
            PdfPTable tableB=new PdfPTable(5);
            tableB.getDefaultCell().setFixedHeight(20);
            tableB.setWidthPercentage(100f);
            PdfPCell cellB =new PdfPCell(new Phrase(" B- ETUDIANTS : ", fontGras));
            cellB.setColspan(5);
            tableB.addCell(cellB);
            cellB=new PdfPCell(new Phrase(" Étudiant 1 :"));
            tableB.addCell(cellB);
            cellB=new PdfPCell(new Phrase("  "));
            cellB.setColspan(4);
            tableB.addCell(cellB);
            cellB=new PdfPCell(new Phrase(" Étudiant 2 :"));
            tableB.addCell(cellB);
            cellB=new PdfPCell(new Phrase("  "));
            cellB.setColspan(4);
            tableB.addCell(cellB);
            document.add(tableB);
            document.add (new Paragraph(" \n "));
            // table C
            PdfPTable tableC=new PdfPTable(8);
            tableC.getDefaultCell().setFixedHeight(20);
            tableC.setWidthPercentage(100f);
            PdfPCell cellC =new PdfPCell(new Phrase(" C- EVALUATION : ", fontGras));
            cellB.setColspan(7);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase("  ÉVOLUTION SUR   ",fontGras));
            cellC.setColspan(2);
            cellC.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase("  CRITERES D'ÉVOLUTION   ",fontGras));
            cellC.setColspan(4);
            cellC.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" ETUDIANT 1 ",fontGras));
            cellC.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" ETUDIANT 2 ",fontGras));
            cellC.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableC.addCell(cellC);

            cellC= new PdfPCell(new Phrase(" LE RAPPORT \n (6 POINTS)",fontGras));
            cellC.setBorder(Rectangle.BOTTOM);
            cellC.setColspan(2);
            cellC.setRowspan(3);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(String.format("Fond : ", fontGras)+"(Mise en contexte du travail et description de la problématique, \n" +
                                                               "Critique de l'existant, Conception, Réalisation, Auto critique et \n" +
                                                               "perspectives) (4 points) "));
            cellC.setColspan(4);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /4 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /4 ",fontGras));
            tableC.addCell(cellC);

            cellC= new PdfPCell(new Phrase(String.format("Forme : ", fontGras)+"(Organization du rapport, Mise en page, Expression) \n" +
                    "(2 points) "));
            cellC.setColspan(4);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /2 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /2 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(String.format("Plagiat : ", fontGras)+"\n             *  Si 30<= taux <50 alors -1 " +
                    "\n             *  Si 50<= taux <60 alors -2 \n             *  Si 30<= taux <50 alors -1 "));
            cellC.setColspan(4);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase("  ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase("  ",fontGras));
            tableC.addCell(cellC);

            cellC= new PdfPCell(new Phrase(" "));
            cellC.setBorder(Rectangle.BOTTOM);
            cellC.setColspan(2);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" LA SOUTENANCE \n (6 POINTS)",fontGras));
            cellC.setRowspan(3);
            cellC.setColspan(2);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(String.format("Forme et Organisation des slides : ", fontGras)+"(claré, ergonomie, \n" +
                    "  (2 points) "));
            cellC.setColspan(4);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /1 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /1 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(String.format("Speech : ", fontGras)+"(fluidité du speech, français,communication avec l'auditoire) \n" +
                    "(2 points) "));
            cellC.setColspan(4);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /2 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /2 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" Réponces aux questions (3 points)"));
            cellC.setColspan(4);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /3 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /3 ",fontGras));
            tableC.addCell(cellC);

            cellC= new PdfPCell(new Phrase(" L'APPLICATION \n (8 POINTS)",fontGras));
            cellC.setRowspan(3);
            cellC.setColspan(2);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(String.format("Quantité du travail : ", fontGras)+" Besoins fonctionnels (3 points) "));
            cellC.setColspan(4);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /3 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /3 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(String.format("Maitrise technique : ", fontGras)+"Technologie utilisées, maitrise su code, \n" +
                    " qualité du code (besoins non fonctionnels)(2 points) "));
            cellC.setColspan(4);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /3 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /3 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(String.format ("Originalité et complexité de l'application ",fontGras)+"(2 points)"));
            cellC.setColspan(4);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /2 ",fontGras));
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /2 ",fontGras));
            tableC.addCell(cellC);
            cellC=new PdfPCell(new Phrase(" "));
            cellC.setColspan(2);
            cellC.setBorder(Rectangle.BOX | Rectangle.LEFT);
            tableC.addCell(cellC);
            cellC=new PdfPCell(new Phrase("TOTAL",fontGras));
            cellC.setColspan(4);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /20 ",fontGras));
            cellC.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableC.addCell(cellC);
            cellC= new PdfPCell(new Phrase(" /20 ",fontGras));
            cellC.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tableC.addCell(cellC);
            document.add(tableC);
            // table D
            PdfPTable tableD= new PdfPTable(6);
            tableD.getDefaultCell().setFixedHeight(20);
            tableD.setWidthPercentage(100f);
            PdfPCell celld =new PdfPCell(new Paragraph(" D- MEMOIRE : ", fontGras));
            celld.setColspan(6);
            tableD.addCell(celld);
            celld =new PdfPCell(new Paragraph(" Le mémoire nécessite des corrections  : "));
            celld.setColspan(5);
            tableD.addCell(celld);
            celld =new PdfPCell(new Paragraph(" \u2610 Oui  \u2610  Non"));
            tableD.addCell(celld);
            celld =new PdfPCell(new Paragraph(" Le mémoire corrigé mérite d'etre déposé à la bibliothèque de l'institut  : "));
            celld.setColspan(5);
            tableD.addCell(celld);
            celld =new PdfPCell(new Paragraph(" \u2610 Oui  \u2610  Non"));
            tableD.addCell(celld);
            document.add(tableD);

            //table E
            PdfPTable tableE = new PdfPTable(3);
            tableE.getDefaultCell().setFixedHeight(20);
            tableE.setWidthPercentage(100f);
            PdfPCell cellE= new PdfPCell(new Phrase(" Aprés délibération, le jury a décidé de :", fontGras));
            cellE.setRowspan(2);
            tableE.addCell(cellE);
            cellE= new PdfPCell(new Phrase(" \u2610  Valider le projet avec la mention :............................"));
            cellE.setColspan(2);
            tableE.addCell(cellE);
            cellE= new PdfPCell(new Phrase(" \u2610  Ne pas Valider le projet."));
            cellE.setColspan(2);
            tableE.addCell(cellE);
            document.add(tableE);

            //table F
            PdfPTable tableF= new PdfPTable(3);
            tableF.getDefaultCell().setFixedHeight(20);
            tableF.setWidthPercentage(100f);
            PdfPCell cellF= new PdfPCell(new Paragraph( String.format(" Encadrant(s) :",fontGras)+" \n .........................................." +
                                                                                                        " \n .........................................."));
            tableF.addCell(cellF);
            cellF= new PdfPCell(new Paragraph( String.format(" Raporteur :",fontGras)+" \n .........................................." +
                    " \n .........................................."));
            tableF.addCell(cellF);
            cellF= new PdfPCell(new Paragraph( String.format(" Président :",fontGras)+" \n .........................................." +
                    " \n .........................................."));
            tableF.addCell(cellF);
            document.add(tableF);
            document.add(new Paragraph("\n \n \n "));
            LineSeparator ls = new LineSeparator();
            Chunk ligneHorizentale = new Chunk(ls);
            document.add(ligneHorizentale);
            document.add(new Phrase(String.format("Mention : Excellent : ", fontGras)+"19 - 19.99"+String.format(" Trés bien : ", fontGras)+"16 - 18"
                    +String.format("Bien : ", fontGras)+"14 - 15.99"+String.format("Assez bien : ", fontGras)+"12 - 13.99"+String.format("Passable : ", fontGras)+"10 - 11.99"));
            DocumentFunction.ajouterPiedDePage(document);
            document.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;}
}
