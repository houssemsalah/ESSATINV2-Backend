package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.financier.ETypeModaliteTransaction;
import tn.essatin.erp.model.financier.MotifAnnulationRejetModalite;
import tn.essatin.erp.util.ConvertierMontantEnLettre;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

import static tn.essatin.erp.util.DocumentGenerators.DocumentFunction.*;

public class DechargeAnnulation {
    public static ByteArrayOutputStream createDocEtudiant(MotifAnnulationRejetModalite motifAnnulationRejetModalite, Niveau niveau, boolean avecEntete) {

        Personne personneEtudiant = motifAnnulationRejetModalite.getModaliteTransaction().getTransaction().getClient();
        Personne personneFinancier = motifAnnulationRejetModalite.getPersonneFinancier();
        Session session = motifAnnulationRejetModalite.getModaliteTransaction().getTransaction().getSession();
        String nomPrenomE = personneEtudiant.getPrenom() + " " + personneEtudiant.getNom();
        String nomPrenomF = personneFinancier.getPrenom() + " " + personneFinancier.getNom();
        boolean isHommeE = personneEtudiant.getSexe().equalsIgnoreCase("Homme");
        boolean isHommeF = personneFinancier.getSexe().equalsIgnoreCase("Homme");
        String sexeE = isHommeE ? "Monsieur" : "Madame";
        String sexeF = isHommeF ? "Monsieur" : "Madame";
        Phrase niveauS = DocumentFunction.niveaux(niveau);
        String numRecu = String.format("%010d", motifAnnulationRejetModalite.getModaliteTransaction().getTransaction().getId());
        String forme = motifAnnulationRejetModalite.getModaliteTransaction().getType().toString();
        String somme = ConvertierMontantEnLettre.enTouteLettre(motifAnnulationRejetModalite.getModaliteTransaction().getMontant()) + " (" + motifAnnulationRejetModalite.getModaliteTransaction().getMontant() + " DT)";
        String dateTransaction = motifAnnulationRejetModalite.getModaliteTransaction().getTransaction().getDatePayement().format(DocumentFunction.FORMATTER);
        String dateAnnulation = LocalDate.now().format(DocumentFunction.FORMATTER);
        boolean isEspece = motifAnnulationRejetModalite.getModaliteTransaction().getType().equals(ETypeModaliteTransaction.ESPECES);


        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Document document = new Document();
        Rectangle pageSize = PageSize.A4;
        document.setPageSize(pageSize);
        document.setMargins(30f, 30f, 30f, 30f);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, response);
            document.open();
            PdfContentByte canvas = writer.getDirectContentUnder();
            document.add(new Phrase("\n\n\n\n\n\n"));
            PdfPTable entete = new PdfPTable(1);
            entete.setWidthPercentage(100);
            entete.getDefaultCell().setBorder(0);
            entete.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            entete.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            entete.getDefaultCell().setFixedHeight(50);
            entete.addCell(text(titre("Décharge", true)));
            document.add(entete);
            document.add(new Phrase("\n\n"));
            Paragraph paragraph = new Paragraph();
            paragraph.setFirstLineIndent(20f);
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraph.setIndentationLeft(30f);
            paragraph.setIndentationRight(30f);
            paragraph.setLeading(30);
            paragraph.add(text(
                    textNormal("Moi, soussignée, ", false),
                    textNormal(nomPrenomE, true),
                    textNormal((isHommeE ? ", etudian en " : ", etudiante en "), false)));
            paragraph.add(niveauS);
            paragraph.add(text(
                    textNormal(", certifie avoir été " + (isHommeE ? "remboursé" : "remboursée") + ", par " + sexeF + " ", false),
                    textNormal(nomPrenomF, true),
                    textNormal(", pour l'operation de ", false),
                    textNormal(somme, true),
                    textNormal((isEspece ? ", en " : ", par "), false),
                    textNormal(forme, true),
                    textNormal(", qui figure sur le reçu N°", false),
                    textNormal(numRecu, true),
                    textNormal(" du ", false),
                    textNormal(dateTransaction, true),
                    textNormal(", suite à une annulation pour le motif suivant:\n\n", false)
            ));
            document.add(paragraph);
            Paragraph paragraph1 = new Paragraph(textNormal(motifAnnulationRejetModalite.getMotife() + "\n\n", false));
            paragraph1.setFirstLineIndent(20f);
            paragraph1.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraph1.setIndentationLeft(40f);
            paragraph1.setIndentationRight(40f);
            paragraph1.setLeading(30);
            document.add(paragraph1);
            float[] ts2 = {200f};
            PdfPTable pied = new PdfPTable(1);
            pied.setTotalWidth(ts2);
            pied.getDefaultCell().setBorder(0);
            pied.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            pied.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            pied.getDefaultCell().setFixedHeight(20);
            pied.addCell("");
            pied.addCell(text(textNormal(nomPrenomE, true)));
            pied.addCell(text(textNormal(dateAnnulation, false)));
            pied.writeSelectedRows(0, -1, document.leftMargin() + 300, 150 + pied.getTotalHeight(), canvas);
            if (avecEntete)
                addPapierEntete(writer);
            document.close();
        } catch (Exception e) {
        }
        return response;
    }


    public static ByteArrayOutputStream createDocFinancier(MotifAnnulationRejetModalite motifAnnulationRejetModalite, Niveau niveau, boolean avecEntete) {

        Personne personneEtudiant = motifAnnulationRejetModalite.getModaliteTransaction().getTransaction().getClient();
        Personne personneFinancier = motifAnnulationRejetModalite.getPersonneFinancier();
        Session session = motifAnnulationRejetModalite.getModaliteTransaction().getTransaction().getSession();
        String nomPrenomE = personneEtudiant.getPrenom() + " " + personneEtudiant.getNom();
        String nomPrenomF = personneFinancier.getPrenom() + " " + personneFinancier.getNom();
        boolean isHommeE = personneEtudiant.getSexe().equalsIgnoreCase("Homme");
        boolean isHommeF = personneFinancier.getSexe().equalsIgnoreCase("Homme");

        Phrase niveauS = DocumentFunction.niveaux(niveau);
        String numTransaction = "" + motifAnnulationRejetModalite.getModaliteTransaction().getTransaction().getId();
        String forme = motifAnnulationRejetModalite.getModaliteTransaction().getType().toString();
        String somme = ConvertierMontantEnLettre.enTouteLettre(motifAnnulationRejetModalite.getModaliteTransaction().getMontant()) + " (" + motifAnnulationRejetModalite.getModaliteTransaction().getMontant() + " DT)";
        String dateTransaction = motifAnnulationRejetModalite.getModaliteTransaction().getTransaction().getDatePayement().format(DocumentFunction.FORMATTER);
        String dateAnnulation = LocalDate.now().format(DocumentFunction.FORMATTER);
        boolean isEspece = motifAnnulationRejetModalite.getModaliteTransaction().getType().equals(ETypeModaliteTransaction.ESPECES);


        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Document document = new Document();
        Rectangle pageSize = PageSize.A4;
        document.setPageSize(pageSize);
        document.setMargins(30f, 30f, 30f, 30f);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, response);
            document.open();
            PdfContentByte canvas = writer.getDirectContentUnder();
            document.add(new Phrase("\n\n\n\n\n\n"));
            PdfPTable entete = new PdfPTable(1);
            entete.setWidthPercentage(100);
            entete.getDefaultCell().setBorder(0);
            entete.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            entete.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            entete.getDefaultCell().setFixedHeight(50);
            entete.addCell(text(titre("Décharge", true)));
            document.add(entete);
            document.add(new Phrase("\n\n"));
            Paragraph paragraph = new Paragraph();
            paragraph.setFirstLineIndent(20f);
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraph.setIndentationLeft(30f);
            paragraph.setIndentationRight(30f);
            paragraph.setLeading(30);
            paragraph.add(text(
                    textNormal("Moi, soussignée, ", false),
                    textNormal(nomPrenomF, true),
                    textNormal(", certifie avoir annulé l'operation de ", false),
                    textNormal(somme, true),
                    textNormal((isEspece ? ", en " : ", par "), false),
                    textNormal(forme, true),
                    textNormal(", qui figure sur la transaction N°", false),
                    textNormal(numTransaction, true),
                    textNormal(" du ", false),
                    textNormal(dateTransaction, true),
                    textNormal((isHommeE ? " de l'etudian " : " de l'etudiante "), false),
                    textNormal(nomPrenomE,true),
                    textNormal((isHommeE ? ", inscrit en " : ", inscrite en "), false)
            ));
            paragraph.add(niveauS);
            paragraph.add(text(
                    textNormal(", pour le motif suivant:\n\n", false)
            ));
            document.add(paragraph);
            Paragraph paragraph1 = new Paragraph(textNormal(motifAnnulationRejetModalite.getMotife() + "\n\n", false));
            paragraph1.setFirstLineIndent(20f);
            paragraph1.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraph1.setIndentationLeft(40f);
            paragraph1.setIndentationRight(40f);
            paragraph1.setLeading(30);
            document.add(paragraph1);
            float[] ts2 = {200f};
            PdfPTable pied = new PdfPTable(1);
            pied.setTotalWidth(ts2);
            pied.getDefaultCell().setBorder(0);
            pied.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            pied.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            pied.getDefaultCell().setFixedHeight(20);
            pied.addCell("");
            pied.addCell(text(textNormal(nomPrenomF, true)));
            pied.addCell(text(textNormal(dateAnnulation, false)));
            pied.writeSelectedRows(0, -1, document.leftMargin() + 300, 150 + pied.getTotalHeight(), canvas);
            if (avecEntete)
                addPapierEntete(writer);
            document.close();
        } catch (Exception e) {
        }
        return response;
    }
}
