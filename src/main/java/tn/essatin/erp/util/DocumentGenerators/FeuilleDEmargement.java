package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FeuilleDEmargement {
    public static Chunk exposant(String text) {
        Font f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        Font supFont = new Font(f);
        supFont.setSize(f.getSize() / 2f);
        Chunk c = new Chunk(text, supFont);
        c.setTextRise(7f);
        return c;
    }
    public static List<String> listeAleatoire(int colones, int nbPlaces,int nbEtudiant) {
        String col = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<String> place = new ArrayList<String>();
        List<String> l1 = new ArrayList<String>();
        List<String> l2 = new ArrayList<String>();
        List<String> l3 = new ArrayList<String>();
        int ligne = nbPlaces / colones;
        ligne = ((nbPlaces % colones) > 0) ? ligne+1 : ligne;

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
        place = place.subList(0,nbEtudiant);
        Collections.shuffle(place);
        return place;
    }

    public static ByteArrayOutputStream createDoc(List<Enregistrement> enregistrementList, int nbrecolones, int nombrePlace) {
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

        List<String> places = listeAleatoire(nbrecolones, nombrePlace,nomPrenom.size());
        Collections.sort(nomPrenom);
        String niveauxC = niveau.getDesignation();
        String designationNiveaux = cycle.getDescription() + " " + parcours.getDesignation();
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
        Font fonttabHed = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);

        int nbLignePageUne = 23;
        int nbLignePageDeux = 26;

        int nbpage = (nomPrenom.size() - nbLignePageUne) / nbLignePageDeux;
        nbpage++;
        if (((nomPrenom.size() - nbLignePageUne) % nbLignePageDeux) > 0)
            nbpage++;
        Document document = new Document();
        LineSeparator ls = new LineSeparator();
        Chunk ligneHorizentale = new Chunk(ls);
        try {
            PdfWriter.getInstance(document, response);
            document.setPageSize(PageSize.A4);
            document.setMargins(15f, 15f, 10f, 10f);
            document.open();
            Image img = Image.getInstance("logoEssat.png");
            img.scaleAbsoluteHeight(50);
            img.scaleAbsoluteWidth(50);
            int start = 1;
            for (int page = 0; page < nbpage; page++) {
                PdfPTable head = new PdfPTable(6);
                head.getDefaultCell().setFixedHeight(20);
                head.setWidthPercentage(100f);
                head.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                PdfPCell cell = new PdfPCell(img);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setRowspan(4);
                head.addCell(cell);
                cell = new PdfPCell(new Phrase("FEUILLE D'EMARGEMENT \n ( Année Universitaire "
                        + session.getSession() + " )", fontTitle));
                cell.setRowspan(4);
                cell.setColspan(4);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                head.addCell(cell);
                cell = new PdfPCell(new Phrase("Ref:GES-IMP-08"));
                head.addCell(cell);
                cell = new PdfPCell(new Phrase("Indice:01"));
                head.addCell(cell);
                cell = new PdfPCell(new Phrase("Date:18/07/2019"));
                head.addCell(cell);
                cell = new PdfPCell(new Phrase("Page:" + (page + 1) + "/" + nbpage));
                head.addCell(cell);
                head.addCell("  \n \n  ");
                document.add(new Paragraph(Chunk.NEWLINE));
                document.add(head);
                document.add(new Paragraph(Chunk.NEWLINE));
                if (page == 0) {
                    PdfPTable head1 = new PdfPTable(2);
                    head1.getDefaultCell().setBorderWidth(15f);
                    head1.getDefaultCell().setFixedHeight(20);
                    head1.setWidthPercentage(100);
                    PdfPCell cell1;
                    cell1 = new PdfPCell(new Phrase(" Département : .................................................. "));
                    cell1.setBorder(Rectangle.LEFT | Rectangle.TOP);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);

                    cell1 = new PdfPCell(new Phrase(" Enseignant : .................................................... "));
                    cell1.setBorder(Rectangle.RIGHT | Rectangle.TOP);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    cell1 = new PdfPCell(new Phrase(" Matiére : ...........................................................  "));
                    cell1.setBorder(Rectangle.LEFT);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    cell1 = new PdfPCell(new Phrase(" Date : .............................................................. "));
                    cell1.setBorder(Rectangle.RIGHT);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    Phrase niv = new Phrase(" Classe : " + niveauxC);
                    if (Integer.parseInt(niveauxC) == 1) {
                        niv.add(exposant("ère"));
                        niv.add(" Année ");
                    } else {
                        niv.add(exposant("ème"));
                        niv.add(" Années ");
                    }
                    niv.add(designationNiveaux);
                    cell1 = new PdfPCell(niv);
                    cell1.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    cell1 = new PdfPCell(new Phrase(" Horaire : .......................................................... "));
                    cell1.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    document.add(head1);
                    document.add(new Paragraph(Chunk.NEWLINE));
                }
                PdfPTable t = new PdfPTable(12);
                t.getDefaultCell().setFixedHeight(20);
                t.setWidthPercentage(100);
                PdfPCell cel = new PdfPCell(new Phrase("N°"));
                cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
                t.addCell(cel);
                cel = new PdfPCell(new Phrase("Emplacement\nEtudiant", fonttabHed));
                cel.setColspan(2);

                cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
                t.addCell(cel);
                cel = new PdfPCell(new Phrase("Nom & Prénom", fonttabHed));
                cel.setColspan(5);
                cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
                t.addCell(cel);
                cel = new PdfPCell(new Phrase(" Entrée "));
                cel.setColspan(2);

                cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
                t.addCell(cel);
                cel = new PdfPCell(new Phrase(" Sortie "));
                cel.setColspan(2);

                cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
                t.addCell(cel);
                PdfPCell vide = new PdfPCell(new Phrase(" "));
                vide.setFixedHeight(90);
                int n = nomPrenom.size();
                int fac1 = page == 0 ? nbLignePageUne : nbLignePageDeux;

                for (int i = start; i <= n; i++) {
                    String num = i < 10 ? "0" + i : "" + i;
                    PdfPCell cell2 = new PdfPCell(new Phrase(num));
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    t.addCell(cell2);
                    cell2 = new PdfPCell(new Phrase(places.get(i - 1)));
                    cell2.setColspan(2);
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    t.addCell(cell2);
                    PdfPCell NP = new PdfPCell(new Phrase(nomPrenom.get(i - 1)));
                    NP.setColspan(5);
                    vide = new PdfPCell(new Phrase(" "));
                    vide.setFixedHeight(20);
                    vide.setColspan(2);

                    t.addCell(NP);
                    for (int j = 0; j < 2; j++)
                        t.addCell(vide);
                    if (page == 0) {
                        if (i % fac1 == 0) {
                            start = i + 1;
                            break;
                        }
                    } else {
                        if (((i - nbLignePageUne) % fac1) == 0) {
                            start = i + 1;
                            break;
                        }
                    }
                }
                document.add(t);
                document.add(new Paragraph(Chunk.NEWLINE));
                if (page != (nbpage - 1)) {
                    PdfPTable sig = new PdfPTable(9);
                    sig.getDefaultCell().setFixedHeight(20);
                    PdfPCell cellVide = new PdfPCell(new Phrase(""));
                    cellVide.setFixedHeight(20);
                    cellVide.setBorder(Rectangle.NO_BORDER);
                    PdfPCell cellSig = new PdfPCell(new Phrase("Surveillants"));
                    cellSig.setColspan(2);
                    cellSig.setFixedHeight(20);
                    cellSig.setBorder(Rectangle.BOX);
                    sig.addCell(cellSig);
                    cellSig = new PdfPCell(new Phrase(" "));
                    cellSig.setColspan(3);
                    cellSig.setFixedHeight(20);
                    cellSig.setBorder(Rectangle.BOX);
                    sig.addCell(cellSig);
                    for (int i=0;i<4;i++)
                    sig.addCell(cellVide);
                    cellSig = new PdfPCell(new Phrase("Signature"));
                    cellSig.setColspan(2);
                    cellSig.setFixedHeight(20);
                    cellSig.setBorder(Rectangle.BOX);
                    sig.addCell(cellSig);
                    cellSig = new PdfPCell(new Phrase(" "));
                    cellSig.setColspan(3);
                    cellSig.setFixedHeight(20);
                    cellSig.setBorder(Rectangle.BOX);
                    sig.addCell(cellSig);
                    for (int i=0;i<4;i++)
                        sig.addCell(cellVide);
                    document.add(sig);
                }

                if (page == (nbpage - 1)) {
                    PdfPTable tz = new PdfPTable(1);
                    tz.getDefaultCell().setFixedHeight(20);
                    PdfPCell pc = new PdfPCell(new Phrase(""));
                    pc.setFixedHeight(20);
                    pc.setBorder(0);
                    int b = (n - (page > 0 ? nbLignePageUne : 0)) % fac1;
                    int c = b == 0 ? b : fac1 - b;
                    if (nbpage > 1)
                        for (int z = c; z >= 0; z--)
                            tz.addCell(pc);
                    else
                        for (int z = c - 1; z >= 0; z--)
                            tz.addCell(pc);
                    document.add(tz);


                    PdfPTable sig = new PdfPTable(9);
                    sig.getDefaultCell().setFixedHeight(20);
                    PdfPCell cellVide = new PdfPCell(new Phrase(""));
                    cellVide.setFixedHeight(20);
                    cellVide.setBorder(Rectangle.NO_BORDER);
                    PdfPCell cellSig = new PdfPCell(new Phrase("Surveillants"));
                    cellSig.setColspan(2);
                    cellSig.setFixedHeight(20);
                    cellSig.setBorder(Rectangle.BOX);
                    sig.addCell(cellSig);
                    cellSig = new PdfPCell(new Phrase(" "));
                    cellSig.setColspan(3);
                    cellSig.setFixedHeight(20);
                    cellSig.setBorder(Rectangle.BOX);
                    sig.addCell(cellSig);
                    sig.addCell(cellVide);
                    cellSig = new PdfPCell(new Phrase("Nombre d'absents"));
                    cellSig.setColspan(3);
                    cellSig.setFixedHeight(20);
                    cellSig.setBorder(Rectangle.BOX);
                    cellSig.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellSig.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    sig.addCell(cellSig);
                    cellSig = new PdfPCell(new Phrase("Signature"));
                    cellSig.setColspan(2);
                    cellSig.setFixedHeight(20);
                    cellSig.setBorder(Rectangle.BOX);
                    sig.addCell(cellSig);
                    cellSig = new PdfPCell(new Phrase(" "));
                    cellSig.setColspan(3);
                    cellSig.setFixedHeight(20);
                    cellSig.setBorder(Rectangle.BOX);
                    sig.addCell(cellSig);
                    sig.addCell(cellVide);
                    cellSig = new PdfPCell(new Phrase(" "));
                    cellSig.setColspan(3);
                    cellSig.setFixedHeight(20);
                    cellSig.setBorder(Rectangle.BOX);
                    sig.addCell(cellSig);
                    document.add(sig);
                }

                PdfPTable tail = new PdfPTable(1);
                tail.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                tail.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
                tail.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                tail.setWidthPercentage(100);

                tail.addCell("  ESSAT Privée de Gabés, BP:91,Bureau Postal Mtorrech 6014 Gabés  \n  Tel:75 294 660 - Fax : 75 294 690 \n  contact@essat-gabes.com // www.essat-gabes.com ");
                document.add(ligneHorizentale);
                document.add(tail);
                document.newPage();
            }
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
