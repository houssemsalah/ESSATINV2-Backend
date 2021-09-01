package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import tn.essatin.erp.model.Scolarite.Cycle;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.Scolarite.Parcours;
import tn.essatin.erp.model.Scolarite.Specialite;
import tn.essatin.erp.model.Session;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class DocumentFunction {
    public static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK);
    public static final Font SECTION_FONT = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.BLUE);
    public static final Font HEAD_FONT = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
    public static final Font ATTRIB_FONT = FontFactory.getFont(FontFactory.TIMES_BOLD, 15, BaseColor.BLACK);
    public static final Font FONT_GRAS = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK);
    public static final Font FONT_GRAS_OBLIQUE = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC, 14, BaseColor.BLACK);
    public static final Font FONT_OBLIQUE = FontFactory.getFont(FontFactory.TIMES_ITALIC, 14, BaseColor.BLACK);
    public static final Chunk ligneHorizentale = new Chunk(new LineSeparator());
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    public static BaseColor essat = new BaseColor(33, 140, 185);


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

    public static void ajouterEnteteSpeciale(Document document, String titre, String ref, String indice, String Date, int page, int nbpage) {
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
        Font fontRefs = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);

        Image img;
        PdfPTable head;
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
            cell = new PdfPCell(new Phrase(titre, fontTitle));
            cell.setRowspan(4);
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            head.addCell(cell);
            cell = new PdfPCell(new Phrase("Ref : " + ref, fontRefs));
            head.addCell(cell);
            cell = new PdfPCell(new Phrase("Indice : " + indice, fontRefs));
            head.addCell(cell);
            cell = new PdfPCell(new Phrase("Date : " + Date, fontRefs));
            head.addCell(cell);
            cell = new PdfPCell(new Phrase("Page : " + page + "/" + nbpage, fontRefs));
            head.addCell(cell);
            head.addCell("  \n \n  ");
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(head);
            document.add(new Paragraph(Chunk.NEWLINE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ajoutercadre(Document document) {
        try {
            Rectangle rect = new Rectangle(36, 108);
            rect.enableBorderSide(1);
            rect.enableBorderSide(2);
            rect.enableBorderSide(4);
            rect.enableBorderSide(8);
            rect.setBorder(2);
            rect.setBorderColor(BaseColor.BLACK);
            document.add(rect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ajouterEntete(Document document, String titre, String ref, String indice, String Date, Session session, int page, int nbpage) {
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
        Font fontRefs = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);

        Image img;
        PdfPTable head;
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
            cell = new PdfPCell(new Phrase("Ref : " + ref, fontRefs));
            head.addCell(cell);
            cell = new PdfPCell(new Phrase("Indice : " + indice, fontRefs));
            head.addCell(cell);
            cell = new PdfPCell(new Phrase("Date : " + Date, fontRefs));
            head.addCell(cell);
            cell = new PdfPCell(new Phrase("Page : " + (page + 1) + "/" + nbpage, fontRefs));
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


    public static Phrase niveaux(Niveau niveau) {
        Parcours parcours = niveau.getParcours();
        Specialite specialite = parcours.getSpecialite();
        Cycle cycle = specialite.getCycle();
        String niveauxC = niveau.getDesignation();
        String designationNiveaux = cycle.getDescription() + " " + parcours.getDesignation();
        Phrase niv = new Phrase(niveauxC, FONT_GRAS_OBLIQUE);
        if (Integer.parseInt(niveauxC) == 1) {
            niv.add(DocumentFunction.exposant("ère"));
        } else {
            niv.add(DocumentFunction.exposant("ème"));
        }
        niv.add(" Année ");
        niv.add(new Chunk(designationNiveaux, FONT_GRAS_OBLIQUE));
        return niv;
    }


    public static Chunk exposant(String text, boolean isGras) {
        Font f;
        if (isGras)
            f = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK);
        else
            f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        Font supFont = new Font(f);
        supFont.setSize(f.getSize() / 2f);
        Chunk c = new Chunk(text, supFont);
        c.setTextRise(7f);
        return c;
    }

    public static Chunk titre(String text, boolean isGras) {
        Font f;
        if (isGras)
            f = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.BLACK);
        else
            f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, BaseColor.BLACK);
        Font supFont = new Font(f);
        return new Chunk(text, supFont);
    }

    public static Chunk textNormalMinMin(String text, boolean isGras) {
        Font f;
        if (isGras)
            f = FontFactory.getFont(FontFactory.TIMES_BOLD, 8, BaseColor.BLACK);
        else
            f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, BaseColor.BLACK);
        Font supFont = new Font(f);
        return new Chunk(text, supFont);
    }

    public static Chunk textNormal(String text, boolean isGras) {
        Font f;
        if (isGras)
            f = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK);
        else
            f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        Font supFont = new Font(f);
        return new Chunk(text, supFont);
    }

    public static Chunk textNormal(String text, boolean isGras, int size) {
        Font f;
        if (isGras)
            f = FontFactory.getFont(FontFactory.TIMES_BOLD, size, BaseColor.BLACK);
        else
            f = FontFactory.getFont(FontFactory.TIMES_ROMAN, size, BaseColor.BLACK);
        Font supFont = new Font(f);
        return new Chunk(text, supFont);
    }

    public static Chunk textNormal(String text, boolean isGras, BaseColor baseColor) {
        Font f;
        if (isGras)
            f = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, baseColor);
        else
            f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, baseColor);
        Font supFont = new Font(f);
        return new Chunk(text, supFont);
    }

    public static Chunk textNormal(String text, boolean isGras, int size, BaseColor baseColor) {
        Font f;
        if (isGras)
            f = FontFactory.getFont(FontFactory.TIMES_BOLD, size, baseColor);
        else
            f = FontFactory.getFont(FontFactory.TIMES_ROMAN, size, baseColor);
        Font supFont = new Font(f);
        return new Chunk(text, supFont);
    }

    public static Chunk textNormalArialUni(String text, boolean isGras, int size, BaseColor baseColor) {
        FontFactory.register("src/main/resources/font/Traditional-Arabic_font.ttf");
        Font f;
        if (isGras)
            f = FontFactory.getFont("src/main/resources/font/Traditional-Arabic_font.ttf", BaseFont.IDENTITY_H,
                    true, size, Font.FontStyle.BOLD.ordinal(), baseColor);
        else
            f = FontFactory.getFont("src/main/resources/font/arialuni_font.ttf", BaseFont.IDENTITY_H,
                    true, size, Font.FontStyle.NORMAL.ordinal(), baseColor);
        return new Chunk(text, f);
    }

    public static Chunk textNormalMin(String text, boolean isGras) {
        Font f;
        if (isGras)
            f = FontFactory.getFont(FontFactory.TIMES_BOLD, 10, BaseColor.BLACK);
        else
            f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, BaseColor.BLACK);
        Font supFont = new Font(f);
        return new Chunk(text, supFont);
    }

    public static Phrase text(Chunk... chunks) {
        Phrase phrase = new Phrase();
        Collections.addAll(phrase, chunks);
        return phrase;
    }

    public static void addPapierEntete(PdfWriter writer) throws DocumentException, IOException {
        PdfContentByte canvas = writer.getDirectContentUnder();
        Image img = Image.getInstance("logoEssat.png");
        float[] ts = {4f, 6f, 4f};
        PdfPTable entete = new PdfPTable(ts);
        entete.setTotalWidth(PageSize.A4.getWidth()-40);
        entete.getDefaultCell().setBorder(0);
        entete.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        entete.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        entete.getDefaultCell().setFixedHeight(100);
        entete.addCell(text(
                textNormal("République Tunisienne\n".toUpperCase(Locale.ROOT), true, 8, essat),
                textNormal("Ministère de l'Enseignament Supérieure\n", false, 8, essat),
                textNormal("et de la Recherche Scientifique\n", false, 8, essat),
                textNormal("L'École Supperieure des Sciences Appliquées\n", false, 8, essat),
                textNormal("et de la Téchnologie Privée de Gabès\n", false, 8, essat),
                textNormal("Agrément N°: 05/2007", false, 8, essat)
        ));
        entete.addCell(img);
        PdfPCell pdfPCell = new PdfPCell(text(
                textNormalArialUni("الجمهورية التونسية\n", false, 9, essat),
                textNormalArialUni("وزارة التعليـــــم العــــــالي\n", false, 8, essat),
                textNormalArialUni("و البحــــث العلمـــــي\n", false, 8, essat),
                textNormalArialUni("المدرسة العليا للعلوم التطبيقية\n", false, 8, essat),
                textNormalArialUni("و التكنولوجيا الخاصة بقابس\n", false, 8, essat),
                textNormalArialUni("التأشيرة عدد: 05/2007", false, 8, essat)
        ));
        pdfPCell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        pdfPCell.setBorder(0);
        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfPCell.setFixedHeight(100);
        pdfPCell.setLeading(10,0);
        entete.addCell(pdfPCell);
        entete.writeSelectedRows(0, -1, 20, PageSize.A4.getHeight() - 20, canvas);

        float[] tsf = {2f, 5.5f, 4.5f};
        PdfPTable piedDePage = new PdfPTable(tsf);
        piedDePage.setTotalWidth(PageSize.A4.getWidth() - 50);
        piedDePage.getDefaultCell().setBorder(0);
        piedDePage.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        piedDePage.getDefaultCell().setVerticalAlignment(Element.ALIGN_BOTTOM);
        piedDePage.getDefaultCell().setFixedHeight(10);
        piedDePage.getDefaultCell().setPaddingBottom(0);
        piedDePage.getDefaultCell().setPaddingLeft(0);
        piedDePage.getDefaultCell().setPaddingRight(0);
        piedDePage.getDefaultCell().setPaddingTop(0);
        PdfPCell e = new PdfPCell(text(textNormal("ESSAT", true, 13, essat)));
        e.setBorder(0);
        e.setHorizontalAlignment(Element.ALIGN_CENTER);
        e.setVerticalAlignment(Element.ALIGN_MIDDLE);
        e.setFixedHeight(13);
        e.setPaddingBottom(0);
        e.setPaddingLeft(0);
        e.setPaddingRight(0);
        e.setPaddingTop(0);
        piedDePage.addCell(e);
        piedDePage.addCell("");
        PdfPCell addr = new PdfPCell(text(
                textNormal("ESSAT Privée de Gabés, BP:91,Bureau Postal Mtorrech 6014 Gabés  \n", false, 7, essat),
                textNormal("Tel:75 294 660 - Fax : 75 294 690  \n", false, 9, essat),
                textNormal("contact@essat-gabes.com     -     www.essat-gabes.com  ", false, 9, essat)
        ));
        addr.setBorder(0);
        addr.setHorizontalAlignment(Element.ALIGN_RIGHT);
        addr.setVerticalAlignment(Element.ALIGN_BOTTOM);
        addr.setRowspan(3);
        addr.setFixedHeight(10);
        addr.setPaddingBottom(0);
        addr.setPaddingLeft(0);
        addr.setPaddingRight(0);
        addr.setPaddingTop(0);
        piedDePage.addCell(addr);
        PdfPCell p = new PdfPCell(text(textNormal("Privée de Gabès", false, 12, essat)));
        p.setBorder(0);
        p.setHorizontalAlignment(Element.ALIGN_CENTER);
        p.setVerticalAlignment(Element.ALIGN_MIDDLE);
        p.setFixedHeight(13);
        p.setPaddingBottom(0);
        p.setPaddingLeft(0);
        p.setPaddingRight(0);
        p.setPaddingTop(0);
        piedDePage.addCell(p);

        piedDePage.addCell("");
        Image trai = Image.getInstance("src/main/resources/Images/trai.png");
        PdfPCell trai1 = new PdfPCell(trai, true);
        trai1.setBorder(0);
        trai1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        trai1.setVerticalAlignment(Element.ALIGN_BOTTOM);
        trai1.setFixedHeight(10);
        trai1.setColspan(2);
        piedDePage.addCell(trai1);
        piedDePage.writeSelectedRows(0, -1, 25, piedDePage.getTotalHeight() + 30, canvas);

    }
}
