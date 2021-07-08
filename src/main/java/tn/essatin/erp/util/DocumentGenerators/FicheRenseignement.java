package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tn.essatin.erp.model.ContactEtudiant;
import tn.essatin.erp.model.DiplomeEtudiant;
import tn.essatin.erp.model.Etudiants;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.util.Global;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FicheRenseignement {

    public static ByteArrayOutputStream createDoc(Etudiants etu, List<ContactEtudiant> ce, List<DiplomeEtudiant> de) {
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Personne etudiant = etu.getIdPersonne();
        System.out.println(etudiant);
        String identificateur = "";//+etudiant.getIdIdentificateur().getTypeIdentificateur();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK);
        Font fontSection = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.BLUE);
        Font fonttabHed = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);

        Font fontattrib = FontFactory.getFont(FontFactory.TIMES_BOLD, 15, BaseColor.BLACK);
        Phrase attrib = null;
        Chunk attribChunk = null;

        Document document = new Document();
        try {
            //PdfWriter.getInstance(document, new FileOutputStream("PFADOC/" + n + ".pdf"));
            PdfWriter.getInstance(document, response);
            document.open();
            Phrase title = new Phrase();
            Chunk titleChunk = new Chunk(Global.ficheDeRenseignementTitle, fontTitle);
            title.add(titleChunk);
            PdfPTable head = new PdfPTable(3);
            head.getDefaultCell().setFixedHeight(50);
            head.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            for (int i = 0; i < 9; i++) {
                head.addCell("");
            }
            PdfPTable t = new PdfPTable(1);
            t.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            t.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            t.setWidthPercentage(100);

            t.addCell(title);

            PdfPTable tc = new PdfPTable(2);
            tc.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tc.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);
            tc.getDefaultCell().setExtraParagraphSpace(2);
            tc.setWidthPercentage(100);

            attrib = new Phrase();
            attribChunk = new Chunk("N°" + identificateur + ": ", fontattrib);
            attrib.add(attribChunk);
            attrib.add(etudiant.getNumeroIdentificateur());

            tc.addCell(attrib);
            tc.addCell("");

            attrib = new Phrase();
            attribChunk = new Chunk("Nom: ", fontattrib);
            attrib.add(attribChunk);
            attrib.add(etudiant.getNom());
            tc.addCell(attrib);

            attrib = new Phrase();
            attribChunk = new Chunk("Prénom: ", fontattrib);
            attrib.add(attribChunk);
            attrib.add(etudiant.getPrenom());
            tc.addCell(attrib);

            attrib = new Phrase();
            attribChunk = new Chunk("Sexe: ", fontattrib);
            attrib.add(attribChunk);
            attrib.add(etudiant.getSexe());
            tc.addCell(attrib);

            attrib = new Phrase();
            attribChunk = new Chunk("Nationalité: ", fontattrib);
            attrib.add(attribChunk);
            attrib.add(etudiant.getIdNationalite().getLibelle());
            tc.addCell(attrib);

            SimpleDateFormat simpleFormat = new SimpleDateFormat("dd MMMMMM yyyy", new Locale("FR"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

            attrib = new Phrase();
            attribChunk = new Chunk("Date de naissance: ", fontattrib);
            attrib.add(attribChunk);
            attrib.add(etudiant.getDateDeNaissance().format(formatter));
            tc.addCell(attrib);

            attrib = new Phrase();
            attribChunk = new Chunk("Lieu: ", fontattrib);
            attrib.add(attribChunk);
            attrib.add(etudiant.getLieuDeNaissance());
            tc.addCell(attrib);

            attrib = new Phrase();
            attribChunk = new Chunk("N°Tel: ", fontattrib);
            attrib.add(attribChunk);
            attrib.add(etudiant.getTel());
            tc.addCell(attrib);

            attrib = new Phrase();
            attribChunk = new Chunk("E-mail: ", fontattrib);
            attrib.add(attribChunk);
            attrib.add(etudiant.getMail());
            tc.addCell(attrib);

            attrib = new Phrase();
            attribChunk = new Chunk("Contact: ", fontattrib);
            attrib.add(attribChunk);
            tc.addCell(attrib);

            for (ContactEtudiant c : ce) {
                tc.addCell(c.getNom() + "(" + c.getDesignation() + "): " + c.getNumero());
                tc.addCell("");
            }
            tc.addCell("");

            PdfPTable tdipe = new PdfPTable(4);
            tdipe.getDefaultCell().setBorder(Rectangle.BOX);
            tdipe.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            tdipe.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tdipe.getDefaultCell().setFixedHeight(25);
            tdipe.setWidthPercentage(100);
            Phrase ph = new Phrase();
            ph.add(new Chunk("Diplome", fonttabHed));
            tdipe.addCell(ph);
            ph = new Phrase();
            ph.add(new Chunk("Spécialité", fonttabHed));
            tdipe.addCell(ph);
            ph = new Phrase();
            ph.add(new Chunk("Année", fonttabHed));
            tdipe.addCell(ph);/*
             * ph = new Phrase(); ph.add(new Chunk("Niveau", fonttabHed));
             * tdipe.addCell(ph); ph = new Phrase(); ph.add(new Chunk("État", fonttabHed));
             * tdipe.addCell(ph);
             */
            ph = new Phrase();
            ph.add(new Chunk("Établissement", fonttabHed));
            tdipe.addCell(ph);

            PdfPTable tsep = new PdfPTable(1);
            tsep.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tsep.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            tsep.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tsep.getDefaultCell().setFixedHeight(2);
            tsep.setWidthPercentage(100);

            tsep.addCell("");

            PdfPTable tdip = new PdfPTable(4);
            tdip.getDefaultCell().setBorder(Rectangle.BOX);
            tdip.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            tdip.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tdip.getDefaultCell().setExtraParagraphSpace(10);
            tdip.setWidthPercentage(100);
            for (DiplomeEtudiant dd : de) {
                if (dd.getStatus().equals("Complet")) {
                    tdip.addCell(dd.getIdDiplome().getNomDiplome());
                    tdip.addCell(dd.getSpecialite());
                    tdip.addCell("" + dd.getAnnee());
                    tdip.addCell(dd.getEtablissement());
                } else {
                    tdip.addCell(dd.getIdDiplome().getNomDiplome());
                    tdip.addCell(dd.getSpecialite());
                    if (dd.getNiveau() > 1)
                        tdip.addCell("" + dd.getStatus() + "\n(" + dd.getNiveau() + " ans réussis)");
                    else
                        tdip.addCell("" + dd.getStatus() + "\n(" + dd.getNiveau() + " an réussi)");
                    tdip.addCell(dd.getEtablissement());
                }
            }

            PdfPTable tail = new PdfPTable(3);
            tail.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tail.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            tail.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tail.setWidthPercentage(100);
            tail.addCell("");
            tail.addCell("");
            tail.addCell("Fait à Gabès le " + simpleFormat.format(new Date()));
            tail.addCell("");
            tail.addCell("");
            tail.addCell("");
            tail.addCell("");
            tail.addCell("");
            tail.addCell("Signature");
            document.add(head);
            document.add(t);
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(new Paragraph(Chunk.NEWLINE));
            Phrase section = new Phrase();
            Chunk sectionChunk = new Chunk("Informations Personelles", fontSection);
            section.add(sectionChunk);
            document.add(section);
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(tc);
            section = new Phrase();
            sectionChunk = new Chunk("Études et formations suivies", fontSection);
            section.add(sectionChunk);
            document.add(section);
            document.add(tdipe);
            document.add(tsep);
            document.add(tdip);
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(new Phrase(
                    "Je déclare sur l'honneur que toutes les informations que j'ai fournies sont correctes et complètes"));
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(tail);

            document.close();
        } catch (DocumentException  ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }

        return response;

    }
}
