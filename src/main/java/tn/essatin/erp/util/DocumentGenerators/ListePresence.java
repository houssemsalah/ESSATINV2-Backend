package tn.essatin.erp.util.DocumentGenerators;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.bytecode.enhance.spi.interceptor.AbstractLazyLoadInterceptor;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.util.Global;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

public class ListePresence {


    public static ByteArrayOutputStream createDoc(List<Enregistrement> enregistrementList) {
        Session session=enregistrementList.get(0).getIdSession();
        Niveau niveau=enregistrementList.get(0).getIdNiveau();
        Parcours parcours=niveau.getParcours();
        Specialite specialite=parcours.getSpecialite();
        Cycle cycle=specialite.getCycle();
        String designationNiveaux=niveau.getDesignation()+" "+cycle.getDescription()+" "+parcours.getDesignation();
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
        Font fontSection = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.BLUE);
        Font fonttabHed = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
        Font fontattrib = FontFactory.getFont(FontFactory.TIMES_BOLD, 15, BaseColor.BLACK);
        Phrase attrib;
        Chunk attribChunk;
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response);
            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(15f,15f,15f,1f);
            document.open();
            PdfPTable head = new PdfPTable(7 );
            head.getDefaultCell().setFixedHeight(20);
            head.setWidthPercentage(100f);
            //head.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            PdfPCell cell=new PdfPCell(new Phrase("logo"));
            cell.setRowspan(4);
            head.addCell(cell);
            cell=new PdfPCell(new Phrase("FEUILLE DE PRÉSENCE  \n ( Année Universitaire "+session.getSession()+" )",fontTitle));
            cell.setRowspan(4);
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            head.addCell(cell);
            cell=new PdfPCell(new Phrase("Ref:AFE-IMP-04"));
            head.addCell(cell);
            cell=new PdfPCell(new Phrase("Indice:01"));
            head.addCell(cell);
            cell=new PdfPCell(new Phrase("Date:11/04/2019"));
            head.addCell(cell);
            cell=new PdfPCell(new Phrase("Page:1/1"));
            head.addCell(cell);
            head.addCell("  \n \n  ");
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(head);
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(new Paragraph(Chunk.NEWLINE));


            PdfPTable head1 = new PdfPTable(2 );
            head1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            head1.getDefaultCell().setFixedHeight(20);
            head1.setWidthPercentage(100);
            PdfPCell cell1=new PdfPCell(new Phrase("  Section : "+ designationNiveaux ));
            head1.addCell(cell1);
            cell1=new PdfPCell(new Phrase(" "));
            head1.addCell(cell1);
            cell1=new PdfPCell(new Phrase("  Nature du module : ..................................................................................... \n   " ));
            head1.addCell(cell1);
            cell1=new PdfPCell(new Phrase("  Nom de l'enseignant : .............................................................................. \n  " ));
            head1.addCell(cell1);
            cell1=new PdfPCell(new Phrase("  Charge horaire : ......................................................................................... \n  " ));
            head1.addCell(cell1);
            cell1=new PdfPCell(new Phrase("  Mois de : .................................................................................................. \n  " ));
            head1.addCell(cell1);
            document.add(head1);

            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(new Paragraph(Chunk.NEWLINE));

            PdfPTable t = new PdfPTable(16 );
            t.getDefaultCell().setFixedHeight(20);
            t.setWidthPercentage(100f);
            PdfPCell cel=new PdfPCell(new Phrase("N° \n d' ordre"));
            cel.setRowspan(2);
            t.addCell(cel);
            cel=new PdfPCell(new Phrase("Nom & Prénom",fontTitle));
            cel.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cel.setColspan(3);
            cel.setRowspan(2);
            t.addCell(cel);
            cel=new PdfPCell(new Phrase("Désignation de séances",fontTitle));
            cel.setHorizontalAlignment(Element.ALIGN_CENTER);
            cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cel.setColspan(12);
            t.addCell(cel);
            PdfPCell vide=new PdfPCell(new Phrase(" "));
            t.addCell(vide);
            t.addCell(vide);
            t.addCell(vide);
            t.addCell(vide);
            t.addCell(vide);
            t.addCell(vide);
            t.addCell(vide);
            t.addCell(vide);
            t.addCell(vide);
            t.addCell(vide);
            t.addCell(vide);
            t.addCell(vide);
            int n = enregistrementList.size();
            for (int i = 1;i<=n;i++) {
                String num=null;
                if(i<10){
                   num="0"+i;}
                else{
                    num=""+i;}
                PdfPCell cell2=new PdfPCell(new Phrase(num));
                t.addCell(cell2);
                String nomPrenom=" ";
                PdfPCell NP=new PdfPCell(new Phrase(nomPrenom));
                NP.setColspan(3);
                t.addCell(NP);
                t.addCell(vide);
                t.addCell(vide);
                t.addCell(vide);
                t.addCell(vide);
                t.addCell(vide);
                t.addCell(vide);
                t.addCell(vide);
                t.addCell(vide);
                t.addCell(vide);
                t.addCell(vide);
                t.addCell(vide);
                t.addCell(vide);
            }
            document.add(t);
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(new Paragraph(Chunk.NEWLINE));
            PdfPTable tail = new PdfPTable(1);
            tail.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tail.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            tail.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tail.setWidthPercentage(100);
            tail.addCell("  NB: les enseignants sont priés de faire l'appel, de relever les absences de les communiquer à la fin de chaque module a l'administration ");
            tail.addCell("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            tail.addCell("  ESSAT Privée de Gabés, BP:91,Bureau Postal Mtorrech 6014 Gabés  \n  Tel:75 294 660 - Fax : 75 294 690 \n  contact@essat-gabes.com // www.essat-gabes.com ");
            document.add(tail);
            document.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
