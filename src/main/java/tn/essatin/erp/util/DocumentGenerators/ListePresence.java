package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListePresence {
    public static Chunk exposant(String text) {
        Font f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        Font supFont = new Font(f);
        supFont.setSize(f.getSize() / 2f);
        Chunk c = new Chunk(text, supFont);
        c.setTextRise(7f);
        return c;
    }
    public static ByteArrayOutputStream createDoc(List<Enregistrement> enregistrementList) {
        Session session = enregistrementList.get(0).getIdSession();
        Niveau niveau = enregistrementList.get(0).getIdNiveau();
        Parcours parcours = niveau.getParcours();
        Specialite specialite = parcours.getSpecialite();
        Cycle cycle = specialite.getCycle();
        List<Personne> personneList = new ArrayList<>();
        for (Enregistrement e : enregistrementList) {
            personneList.add(e.getIdInscription().getIdEtudiant().getIdPersonne());
        }
        List<String> nomPrenom = new ArrayList<>();
        for (Personne personne : personneList) {
            nomPrenom.add(personne.getNom() + " " + personne.getPrenom());
        }
        for (Personne personne : personneList) {
            nomPrenom.add(personne.getNom() + " " + personne.getPrenom());
        }
        for (Personne personne : personneList) {
            nomPrenom.add(personne.getNom() + " " + personne.getPrenom());
        }
        for (Personne personne : personneList) {
            nomPrenom.add(personne.getNom() + " " + personne.getPrenom());
        }
        Collections.sort(nomPrenom);
        String niveauxC = niveau.getDesignation();
        String designationNiveaux =cycle.getDescription() + " " + parcours.getDesignation();
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
        Font fonttabHed = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
        int nbpage = (nomPrenom.size() - 10) / 13;
        nbpage++;
        if (((nomPrenom.size() - 10) % 13) > 0)
            nbpage++;
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response);
            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(15f, 15f, 10f, 10f);
            document.open();
            Image img = Image.getInstance("logoEssat.png");
            img.scaleAbsoluteHeight(50);
            img.scaleAbsoluteWidth(50);
            for (int page = 0; page < nbpage; page++) {
                PdfPTable head = new PdfPTable(7);
                head.getDefaultCell().setFixedHeight(20);
                head.setWidthPercentage(100f);
                //head.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                PdfPCell cell = new PdfPCell(img);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setRowspan(4);
                head.addCell(cell);
                cell = new PdfPCell(new Phrase("FEUILLE DE PRÉSENCE  \n ( Année Universitaire " + session.getSession() + " )", fontTitle));
                cell.setRowspan(4);
                cell.setColspan(5);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                head.addCell(cell);
                cell = new PdfPCell(new Phrase("Ref:AFE-IMP-04"));
                head.addCell(cell);
                cell = new PdfPCell(new Phrase("Indice:01"));
                head.addCell(cell);
                cell = new PdfPCell(new Phrase("Date:11/04/2019"));
                head.addCell(cell);
                cell = new PdfPCell(new Phrase("Page:" + (page + 1) + "/" + nbpage));
                head.addCell(cell);
                head.addCell("  \n \n  ");
                document.add(new Paragraph(Chunk.NEWLINE));
                document.add(head);
                document.add(new Paragraph(Chunk.NEWLINE));
                if (page == 0) {
                    PdfPTable head1 = new PdfPTable(2);
                    head1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    head1.getDefaultCell().setFixedHeight(20);
                    head1.setWidthPercentage(100);
                    Phrase niv = new Phrase("  Section : " + niveauxC);
                    if (Integer.parseInt(niveauxC)==1) {
                        niv.add(exposant("ère"));
                        niv.add(" Année ");
                    }
                    else{
                        niv.add(exposant("ème"));
                        niv.add(" Années ");
                    }
                        niv.add(designationNiveaux);
                    PdfPCell cell1 = new PdfPCell(niv);
                    cell1.setBorder(Rectangle.NO_BORDER);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    cell1 = new PdfPCell(new Phrase(" "));
                    cell1.setBorder(Rectangle.NO_BORDER);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    cell1 = new PdfPCell(new Phrase("  Nature du module : ..................................................................................... \n   "));
                    cell1.setBorder(Rectangle.NO_BORDER);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    cell1 = new PdfPCell(new Phrase("  Nom de l'enseignant : .............................................................................. \n  "));
                    cell1.setBorder(Rectangle.NO_BORDER);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    cell1 = new PdfPCell(new Phrase("  Charge horaire : ......................................................................................... \n  "));
                    cell1.setBorder(Rectangle.NO_BORDER);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    cell1 = new PdfPCell(new Phrase("  Mois de : .................................................................................................. \n  "));
                    cell1.setBorder(Rectangle.NO_BORDER);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    document.add(head1);
                    document.add(new Paragraph(Chunk.NEWLINE));
                }
                PdfPTable t = new PdfPTable(30);
                t.getDefaultCell().setFixedHeight(20);
                t.setWidthPercentage(100);
                PdfPCell cel = new PdfPCell(new Phrase("   N° d'ordre"));
                cel.setRowspan(2);
                cel.setRotation(270);
                t.addCell(cel);
                cel = new PdfPCell(new Phrase("Nom & Prénom", fonttabHed));
                cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cel.setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.TOP);
                cel.setColspan(7);
                cel.setRowspan(2);
                t.addCell(cel);
                cel = new PdfPCell(new Phrase("Désignation"));
                cel.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
                cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cel.setRotation(90);
                cel.setRowspan(2);
                t.addCell(cel);
                PdfPCell vide = new PdfPCell(new Phrase(" "));
                vide.setFixedHeight(90);
                vide.setRowspan(2);
                for (int j = 0; j < 21; j++)
                    t.addCell(vide);
                int n = nomPrenom.size();
                int fac = page < 2 ? 10 : 13;
                int fac1 = page == 0 ? 10 : 13;
                for (int i = 1 + ((page) * fac); i <= n; i++) {
                    String num = i < 10 ? "0" + i : "" + i;
                    PdfPCell cell2 = new PdfPCell(new Phrase(num));
                    t.addCell(cell2);
                    PdfPCell NP = new PdfPCell(new Phrase(nomPrenom.get(i - 1)));
                    NP.setColspan(8);
                    vide = new PdfPCell(new Phrase(" "));
                    vide.setFixedHeight(20);
                    t.addCell(NP);
                    for (int j = 0; j < 21; j++)
                        t.addCell(vide);
                    if (page == 0) {
                        if (i % fac1 == 0)
                            break;
                    } else {
                        if (i - 10 % fac1 == 0)
                            break;
                    }
                }
                document.add(t);
                document.add(new Paragraph(Chunk.NEWLINE));
                if (page == (nbpage - 1)) {
                    PdfPTable tz = new PdfPTable(1);
                    tz.getDefaultCell().setFixedHeight(20);
                    PdfPCell pc = new PdfPCell(new Phrase(""));
                    pc.setFixedHeight(20);
                    pc.setBorder(0);
                    int b = (n - (page > 0 ? 10 : 0)) % fac1;
                    int c = b == 0 ? b : fac1 - b;
                    if (nbpage > 1)
                        for (int z = c; z >= 0; z--)
                            tz.addCell(pc);
                    else
                        for (int z = c - 1; z >= 0; z--)
                            tz.addCell(pc);
                    document.add(tz);
                }
                PdfPTable tail = new PdfPTable(1);
                tail.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                tail.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
                tail.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                tail.setWidthPercentage(100);
                tail.addCell("  NB: les enseignants sont priés de faire l'appel, de relever les absences de les communiquer à la fin de chaque module a l'administration ");
                tail.addCell("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                tail.addCell("  ESSAT Privée de Gabés, BP:91,Bureau Postal Mtorrech 6014 Gabés  \n  Tel:75 294 660 - Fax : 75 294 690 \n  contact@essat-gabes.com // www.essat-gabes.com ");
                document.add(tail);
                document.newPage();
            }
            document.close();

            } catch (Exception e) {
                 e.printStackTrace(); }
        return response;
    }


}





