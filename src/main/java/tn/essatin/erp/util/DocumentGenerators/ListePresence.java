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

import static tn.essatin.erp.util.DocumentGenerators.DocumentFunction.exposant;

public class ListePresence {


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
        Collections.sort(nomPrenom);
        String niveauxC = niveau.getDesignation();
        String designationNiveaux = cycle.getDescription() + " " + parcours.getDesignation();
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
            int start = 1;

            for (int page = 0; page < nbpage; page++) {
                DocumentFunction.ajouterEntete(document, "FEUILLE DE PRESENCE", "AFE-IMP-04", "01", "11/04/2019", session,  page,  nbpage);
                if (page == 0) {
                    PdfPTable head1 = new PdfPTable(2);
                    head1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                    head1.getDefaultCell().setFixedHeight(20);
                    head1.setWidthPercentage(100);
                    Phrase niv = new Phrase("  Section : " + niveauxC);
                    if (Integer.parseInt(niveauxC) == 1) {
                        niv.add(exposant("??re"));
                        niv.add(" Ann??e ");
                    } else {
                        niv.add(exposant("??me"));
                        niv.add(" Ann??es ");
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
                PdfPCell cel = new PdfPCell(new Phrase("   N?? d'ordre"));
                cel.setRowspan(2);
                cel.setRotation(270);
                t.addCell(cel);
                cel = new PdfPCell(new Phrase("Nom & Pr??nom", fonttabHed));
                cel.setHorizontalAlignment(Element.ALIGN_CENTER);
                cel.setVerticalAlignment(Element.ALIGN_BOTTOM);
                cel.setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.TOP);
                cel.setColspan(7);
                cel.setRowspan(2);
                t.addCell(cel);
                cel = new PdfPCell(new Phrase("D??signation"));
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
                int fac1 = page == 0 ? 10 : 13;

                for (int i = start; i <= n; i++) {
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
                        if (i % fac1 == 0) {
                            start = i + 1;
                            break;
                        }
                    } else {
                        if (((i - 10) % fac1) == 0) {
                            start = i + 1;
                            break;
                        }
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
                tail.addCell("  NB: les enseignants sont pri??s de faire l'appel, de relever les absences de les communiquer ?? la fin de chaque module a l'administration ");
                document.add(tail);
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





