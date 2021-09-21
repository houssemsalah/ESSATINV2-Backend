package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.Signataire;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static tn.essatin.erp.util.DocumentGenerators.DocumentFunction.*;

public class CertificatDInscription {
    public static ByteArrayOutputStream createDoc(Enregistrement enr, Signataire signataire, boolean entete) {
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Personne etudiant = enr.getIdInscription().getIdEtudiant().getIdPersonne();
        String identificateur = etudiant.getIdIdentificateur().getTypeIdentificateur();
        Session session = enr.getIdSession();
        Inscription inscription = enr.getIdInscription();
        Niveau niveau = enr.getIdNiveau();
        Parcours parcour = niveau.getParcours();
        Specialite specialite = parcour.getSpecialite();
        Cycle cycle = specialite.getCycle();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD, 20, BaseColor.BLACK);
        Font fontTitle2 = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
        // Font fontTabHed = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16,
        // BaseColor.BLACK);
        Font fontAttrib = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
        Font fontText = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);
        Phrase attrib;
        Chunk attribChunk;
        Phrase text;
        Chunk textChunk;
        Phrase title;
        Chunk titleChunk;
        Phrase title2;
        Chunk title2Chunk;
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, response);

            document.open();
            PdfContentByte canvas = writer.getDirectContentUnder();

            // ajouter un espace vide pour le papier entête
            PdfPTable head = new PdfPTable(1);
            head.getDefaultCell().setFixedHeight(200);
            head.getDefaultCell().setBorder(Rectangle.NO_BORDER);

            head.addCell("");

            document.add(head);

            // ajouter le titre et le soustitre au document
            title = new Phrase();
            titleChunk = new Chunk("Attéstation d'inscription", fontTitle);
            title.add(titleChunk);
            title2 = new Phrase();
            title2Chunk = new Chunk("Année Universitaire: " + session.getSession(), fontTitle2);
            title2.add(title2Chunk);
            PdfPTable t = new PdfPTable(1);
            t.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            t.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
            t.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            t.getDefaultCell().setExtraParagraphSpace(10);
            t.setWidthPercentage(100);
            t.addCell(title);
            t.addCell(title2);
            document.add(t);
            document.add(new Paragraph(Chunk.NEWLINE));


            Paragraph p = new Paragraph();
            p.setFirstLineIndent(15);
            p.add(text(
                    textNormal("Le ", false),
                    textNormal(signataire.getPoste(), false),
                    etudiant.getSexe().equalsIgnoreCase("Femme") ?
                            textNormal(" de l'École Supérieure des Sciences Appliquées et de la Téchnologie Privée de Gabès atteste que l'étudiante.", false) :
                            textNormal(" de l'École Supérieure des Sciences Appliquées et de la Téchnologie Privée de Gabès atteste que l'étudiant.", false)
            ));

            document.add(p);

            document.add(new Paragraph(Chunk.NEWLINE));
            // ajouter les information de l'étudiant(e)
            PdfPTable tc = new PdfPTable(2);
            tc.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tc.getDefaultCell().setVerticalAlignment(Element.ALIGN_RIGHT);
            tc.getDefaultCell().setExtraParagraphSpace(4);
            tc.setWidthPercentage(100);
            //tc.addCell("\n");tc.addCell(" ");
            // nom et prénom
            attrib = new Phrase();
            attribChunk = new Chunk("Nom et Prénom: ", fontAttrib);
            attrib.add(attribChunk);
            tc.addCell(attrib);
            text = new Phrase();
            textChunk = new Chunk(etudiant.getNom() + " " + etudiant.getPrenom(), fontText);
            text.add(textChunk);
            tc.addCell(text);


            // date et lieux de naissance
            SimpleDateFormat simpleFormat = new SimpleDateFormat("dd MMMMMM yyyy", new Locale("FR"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
            attrib = new Phrase();
            attribChunk = new Chunk("Date et lieu de naissance: ", fontAttrib);
            attrib.add(attribChunk);
            tc.addCell(attrib);
            text = new Phrase();
            textChunk = new Chunk(
                    etudiant.getDateDeNaissance().format(formatter) + " à " + etudiant.getLieuDeNaissance(),
                    fontText);
            text.add(textChunk);
            tc.addCell(text);

            // Numero d'identificateur (CIN,Passport ...etc.)
            attrib = new Phrase();
            attribChunk = new Chunk("N°" + identificateur + ": ", fontAttrib);
            attrib.add(attribChunk);
            tc.addCell(attrib);
            text = new Phrase();
            textChunk = new Chunk(etudiant.getNumeroIdentificateur(), fontText);
            text.add(textChunk);
            tc.addCell(text);

            // Classe
            attrib = new Phrase();
            attribChunk = new Chunk("Classe: ", fontAttrib);
            attrib.add(attribChunk);
            tc.addCell(attrib);
            text = new Phrase();
            if (Integer.parseInt(niveau.getDesignation()) > 1)
                textChunk = new Chunk("" + niveau.getDesignation() + "ème", fontText);
            else
                textChunk = new Chunk("" + niveau.getDesignation() + "ère", fontText);
            text.add(textChunk);
            tc.addCell(text);

            // section
            attrib = new Phrase();
            attribChunk = new Chunk("Section: ", fontAttrib);
            attrib.add(attribChunk);
            tc.addCell(attrib);
            text = new Phrase();
            if (parcour.getDesignation().equalsIgnoreCase("tronc commun")) {
                textChunk = new Chunk(
                        cycle.getDesignation() + " " + specialite.getDesignation() + " - " + parcour.getDesignation(),
                        fontText);
            } else {
                textChunk = new Chunk(cycle.getDesignation() + " "/* + specialite.getDesignation() + " - " */
                        + parcour.getDesignation(), fontText);
            }
            text.add(textChunk);
            tc.addCell(text);

            document.add(tc);

            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(new Paragraph(Chunk.NEWLINE));

            // ajouter le texte finale de l'attestation
            //text = new Phrase();
            /*
            if (etudiant.getSexe().equalsIgnoreCase("Femme"))
                textChunk = new Chunk("    Est inscrite, dans notre école pour l'année universitaire "
                        + session.getSession() + " sous le numéro " + inscription.getNumeroInscription()
                        + ".\nCette attestation est delivrée à l'intéressée pour servir et valoir ce que de droit.",
                        fontText);
            else
                textChunk = new Chunk("    Est inscrit, dans notre école pour l'année universitaire "
                        + session.getSession() + " sous le numéro " + inscription.getNumeroInscription()
                        + ".\nCette attestation est delivrée à l'intéressé pour servir et valoir ce que de droit.",
                        fontText);

            document.add(textChunk);*/


            Paragraph paragraph2 = new Paragraph();
            paragraph2.setFirstLineIndent(15);
            paragraph2.add(text(
                    etudiant.getSexe().equalsIgnoreCase("Femme") ?
                            textNormal("Est inscrite, dans notre école pour l'année universitaire " + session.getSession() + " sous le numéro" + inscription.getNumeroInscription() +
                                    ".\nCette attestation est delivrée à l'intéressée pour servir et valoir ce que de droit.", false) :
                            textNormal("Est inscrit, dans notre école pour l'année universitaire " +
                                    session.getSession() + " sous le numéro " + inscription.getNumeroInscription() +
                                    ".\nCette attestation est delivrée à l'intéressé pour servir et valoir ce que de droit.", false)
            ));
            document.add(paragraph2);


            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(new Paragraph(Chunk.NEWLINE));
            document.add(new Paragraph(Chunk.NEWLINE));
            //signature:
            String sigTitre = "Le " + signataire.getPoste();
            String sigNom = signataire.getEmployer().getPersonne().getPrenom() + " " + signataire.getEmployer().getPersonne().getNom();
            float[] ts2 = {200f};
            PdfPTable pied = new PdfPTable(1);
            pied.setTotalWidth(ts2);
            pied.getDefaultCell().setBorder(0);
            pied.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            pied.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            pied.getDefaultCell().setFixedHeight(20);
            pied.addCell("");
            pied.addCell(text(textNormal("Gabès le " + simpleFormat.format(new Date()), false)));
            pied.addCell(text(textNormal(sigTitre, true)));
            pied.addCell(text(textNormal(sigNom, false)));
            pied.writeSelectedRows(0, -1, document.leftMargin() + 300, 150 + pied.getTotalHeight(), canvas);


            if (entete)
                addPapierEntete(writer);

            document.close();
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
