package tn.essatin.erp.util.DocumentGenerators;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static tn.essatin.erp.util.DocumentGenerators.DocumentFunction.exposant;

public class FicheDeNote {

    public static ByteArrayOutputStream createDoc(List<Enregistrement> enregistrementList, int colones) {
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
        Collections.sort(nomPrenom);
        String niveauxC = niveau.getDesignation();
        String designationNiveaux =cycle.getDescription() + " " + parcours.getDesignation();
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
        Font fonttabHed = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
        int nbpage = (nomPrenom.size() - 23) / 26;
        nbpage++;
        if (((nomPrenom.size() - 23) % 26) > 0)
            nbpage++;
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response);
            document.setPageSize(PageSize.A4);
            document.setMargins(15f, 15f, 10f, 10f);
            document.open();
            int start = 1;
            int col;
            for (int page = 0; page < nbpage; page++) {
                DocumentFunction.ajouterEntete(document, "FEUILLE DE NOTE", "GES-IMP-13", "01", "18/07/2019", session,  page,  nbpage);


                if (page == 0) {
                    PdfPTable head1 = new PdfPTable(2);
                    head1.getDefaultCell().setBorderWidth(15f);
                    head1.getDefaultCell().setFixedHeight(20);
                    head1.setWidthPercentage(100);
                    Phrase niv = new Phrase("      Classe : " + niveauxC);
                    if (Integer.parseInt(niveauxC) == 1) {
                        niv.add(exposant("ère"));
                        niv.add(" Année ");
                    } else {
                        niv.add(exposant("ème"));
                        niv.add(" Années ");
                    }
                    niv.add(designationNiveaux);
                    PdfPCell cell1 = new PdfPCell(niv);
                    cell1.setBorder(Rectangle.TOP | Rectangle.LEFT);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    cell1 = new PdfPCell(new Phrase(" Département : ............................................... "));
                    cell1.setBorder(Rectangle.TOP | Rectangle.RIGHT);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    cell1 = new PdfPCell(new Phrase("      Enseignant : ................................................... "));
                    cell1.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    cell1 = new PdfPCell(new Phrase(" Matiére : ........................................................  "));
                    cell1.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
                    cell1.setFixedHeight(20);
                    head1.addCell(cell1);
                    document.add(head1);
                    document.add(new Paragraph(Chunk.NEWLINE));
                }


                boolean ds = false, exam = false, tp = false, oral = false, controle = false;
                 col = 4;

                 int col2=colones;
                while (colones > 0) {
                    if (colones - 16 >= 0) {
                        controle = true;
                        colones -= 16;
                        col++;
                    }
                    if (colones - 8 >= 0) {
                        oral = true;
                        colones -= 8;
                        col++;
                    }
                    if (colones - 4 >= 0) {
                        tp = true;
                        colones -= 4;
                        col++;
                    }
                    if (colones - 2 >= 0) {
                        exam = true;
                        colones -= 2;
                        col++;
                    }
                    if (colones - 1 >= 0) {
                        ds = true;
                        colones -= 1;
                        col++;
                    }
                }
                colones=col2;
                PdfPTable t = new PdfPTable(col);
                t.getDefaultCell().setFixedHeight(20);
                t.setWidthPercentage(100);
                PdfPCell cel = new PdfPCell(new Phrase("   N°"));
                t.addCell(cel);
                cel = new PdfPCell(new Phrase("Nom & Prénom", fonttabHed));
                cel.setColspan(3);
                cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cel.setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.TOP);
                t.addCell(cel);


                if (ds) {
                    cel = new PdfPCell(new Phrase("DS"));
                    cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    t.addCell(cel);

                }
                if (exam) {
                    cel = new PdfPCell(new Phrase("Examen"));
                    cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    t.addCell(cel);

                }
                if (tp) {
                    cel = new PdfPCell(new Phrase("TP"));
                    cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    t.addCell(cel);

                }
                if (oral) {
                    cel = new PdfPCell(new Phrase("Orale"));
                    cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    t.addCell(cel);

                }
                if (controle) {
                    cel = new PdfPCell(new Phrase("Controle"));
                    cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cel.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    t.addCell(cel);

                }
                PdfPCell vide = new PdfPCell(new Phrase(" "));
                int n = nomPrenom.size();
                int fac1 = page == 0 ? 23 : 26;

                for (int i = start; i <= n; i++) {
                    String num = i < 10 ? "0" + i : "" + i;
                    PdfPCell cell2 = new PdfPCell(new Phrase(num));
                    t.addCell(cell2);
                    PdfPCell NP = new PdfPCell(new Phrase(nomPrenom.get(i - 1)));
                    NP.setColspan(3);
                    t.addCell(NP);

                    vide = new PdfPCell(new Phrase(" "));
                    vide.setFixedHeight(20);
                    for (int j = 0; j < (col - 4); j++)
                        t.addCell(vide);


                    if (page == 0) {
                        if (i % fac1 == 0){
                            start = i + 1 ;
                            break;}
                    } else {
                        if ((i - 23) % fac1 == 0){
                            start = i + 1 ;
                            break;}
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
                    int b = (n - (page > 0 ? 23 : 0)) % fac1;
                    int c = b == 0 ? b : fac1 - b;
                    if (nbpage > 1)
                        for (int z = c-1; z >= 0; z--)
                            tz.addCell(pc);
                    else
                        for (int z = c - 1; z >= 0; z--)
                            tz.addCell(pc);
                    document.add(tz);
                }
                PdfPTable S = new PdfPTable(3);
                S.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
                S.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                S.setWidthPercentage(100);
                vide.setBorder(Rectangle.NO_BORDER);
                S.addCell(vide);
                vide.setBorder(Rectangle.NO_BORDER);
                S.addCell(vide);
                S.addCell(" Date et Signature ");
                vide.setBorder(Rectangle.NO_BORDER);
                S.addCell(vide);
                vide.setBorder(Rectangle.NO_BORDER);
                S.addCell(vide);
                S.addCell(" \n \n  \n \n");
                document.add(S);
                DocumentFunction.ajouterPiedDePage(document);
                document.newPage();
            }
                document.close();
              } catch (Exception e) {
                     e.printStackTrace();
                    }
        return response;

    }
}
