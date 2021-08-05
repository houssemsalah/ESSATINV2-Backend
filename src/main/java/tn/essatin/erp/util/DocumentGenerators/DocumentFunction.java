package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import tn.essatin.erp.model.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DocumentFunction {

    public static Chunk exposant(String text) {
        Font f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        Font supFont = new Font(f);
        supFont.setSize(f.getSize() / 2f);
        Chunk c = new Chunk(text, supFont);
        c.setTextRise(7f);
        return c;
    }

    public static List<String> listeAleatoire(int colones, int nbPlaces, int nbEtudiant) {
        String col = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<String> place = new ArrayList<>();
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        List<String> l3 = new ArrayList<>();
        int ligne = nbPlaces / colones;
        ligne = ((nbPlaces % colones) > 0) ? ligne + 1 : ligne;

        for (int i = 1; i < ligne; i += 2) {
            for (int j = 0; j < colones; j++) {
                l1.add("" + col.charAt(j) + (i + 1));
            }
        }
        for (int i = 2; i < ligne; i += 2) {
            for (int j = 0; j < colones; j++) {
                l2.add("" + col.charAt(j) + (i + 1));
            }
        }
        for (int j = 0; j < colones; j++) {
            l3.add("" + col.charAt(j) + (1));
        }
        Collections.shuffle(l1);
        Collections.shuffle(l2);
        Collections.shuffle(l3);
        place.addAll(l1);
        place.addAll(l2);
        place.addAll(l3);
        place = place.subList(0, nbEtudiant);
        Collections.shuffle(place);
        return place;
    }


    public static void ajouterEntete(Document document, String titre, String ref, String indice, String Date, Session session, int page, int nbpage) {
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
        Font fontRefs = FontFactory.getFont(FontFactory.TIMES_ROMAN , 10, BaseColor.BLACK);

        Image img ;
        PdfPTable head ;
        try {
            img = Image.getInstance("logoEssat.png");
            img.scaleAbsoluteHeight(50);
            img.scaleAbsoluteWidth(50);
            head = new PdfPTable(7);
            head.getDefaultCell().setFixedHeight(20);
            head.setWidthPercentage(100f);
            PdfPCell cell = new PdfPCell(img);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(4);
            head.addCell(cell);
            cell = new PdfPCell(new Phrase(titre + "\n ( Année Universitaire " + session.getSession() + " )", fontTitle));
            cell.setRowspan(4);
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            head.addCell(cell);
            cell = new PdfPCell(new Phrase("Ref : " + ref,fontRefs));
            head.addCell(cell);
            cell = new PdfPCell(new Phrase("Indice : " + indice,fontRefs));
            head.addCell(cell);
            cell = new PdfPCell(new Phrase("Date : " + Date,fontRefs));
            head.addCell(cell);
            cell = new PdfPCell(new Phrase("Page : " + (page + 1) + "/" + nbpage,fontRefs));
            head.addCell(cell);
            head.addCell("  \n \n  ");
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(head);
            document.add(new Paragraph(Chunk.NEWLINE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ajouterPiedDePage(Document document) {
        try {
            LineSeparator ls = new LineSeparator();
            Chunk ligneHorizentale = new Chunk(ls);
            PdfPTable tail = new PdfPTable(1);
            tail.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tail.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            tail.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tail.setWidthPercentage(100);
            tail.addCell("  ESSAT Privée de Gabés, BP:91,Bureau Postal Mtorrech 6014 Gabés  \n  Tel:75 294 660 - Fax : 75 294 690 \n  contact@essat-gabes.com // www.essat-gabes.com ");
            document.add(ligneHorizentale);
            document.add(tail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}