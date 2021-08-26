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
import static tn.essatin.erp.util.DocumentGenerators.RecuEtudiant.mini;
import static tn.essatin.erp.util.DocumentGenerators.RecuEtudiant.textNormalMin;

public class DechargeAnnulation {
    public static ByteArrayOutputStream createDocEtudiant(MotifAnnulationRejetModalite motifAnnulationRejetModalite, Niveau niveau) {

        Personne personneEtudiant = motifAnnulationRejetModalite.getModaliteTransaction().getTransaction().getClient();
        Personne personneFinancier = motifAnnulationRejetModalite.getPersonneFinancier();
        Session session = motifAnnulationRejetModalite.getModaliteTransaction().getTransaction().getSession();
        String nomPrenomE = personneEtudiant.getPrenom() + " " + personneEtudiant.getPrenom();
        String nomPrenomF = personneFinancier.getPrenom() + " " + personneFinancier.getPrenom();
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
        document.setMargins(10f, 10f, 10f, 10f);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, response);
            document.open();
            Image img = Image.getInstance("logoEssat.png");
            PdfContentByte canvas = writer.getDirectContentUnder();

            float[] ts = {2f, 6f, 2f};
            PdfPTable entete = new PdfPTable(ts);
            entete.setWidthPercentage(100);
            entete.getDefaultCell().setBorder(0);
            entete.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            entete.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            entete.getDefaultCell().setFixedHeight(50);
            entete.addCell(img);
            entete.addCell(text(titre("Décharge", true), textNormalMin("\n\nN°")));
            entete.addCell(new Phrase(mini("Ecole Supérieure des Sciences Appliquées et de la Technologie Privée de Gabès")));
            document.add(entete);
            document.add(new Phrase("\n\n"));
            document.add(new Phrase("\n\n"));
            document.add(new Phrase("\n\n"));
            Paragraph paragraph = new Paragraph();
            paragraph.setFirstLineIndent(20f);
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraph.setIndentationLeft(30f);
            paragraph.setIndentationRight(30f);
            paragraph.setLeading(30);
            String lool = "Moi, soussignée, ________, certifie avoir été remboursé(e), par l'administration de la Faculté _____, " +
                    "de la somme de__________, qui figure sur le reçu N°________,effectué le__________, par(chèque/ espèce...), suite à une annulation pour le motif suivant:";
            paragraph.add(text(
                    textNormal("Moi, soussignée,", false),
                    textNormal(nomPrenomE, true),
                    textNormal((isHommeE ? ", etudian en " : ", etudiante en "), false)));
            paragraph.add(niveauS);
            paragraph.add(text(
                    textNormal(", certifie avoir été " + (isHommeE ? "remboursé" : "remboursée") + ", par " + sexeF + " ", false),
                    textNormal(nomPrenomF, true),
                    textNormal(", de la somme de ", false),
                    textNormal(somme, true),
                    textNormal(", qui figure sur le reçu N°", false),
                    textNormal(numRecu, true),
                    textNormal(" effectué le ", false),
                    textNormal(dateTransaction, true),
                    textNormal((isEspece ? ", en " : ", par "), false),
                    textNormal(forme, true),
                    textNormal(", suite à une annulation pour le motif suivant:\n\n", false),
                    textNormal(motifAnnulationRejetModalite.getMotife() + "\n\n", false)
            ));
            float[] ts2 = {200f};
            PdfPTable pied = new PdfPTable(1);
            pied.setTotalWidth(ts2);
            pied.getDefaultCell().setBorder(0);
            pied.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            pied.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            pied.getDefaultCell().setFixedHeight(50);
            pied.addCell("");
            pied.addCell(text(
                    textNormal(nomPrenomF, true),
                    textNormalMin("\nLe: "),
                    textNormalMin(dateAnnulation)
            ));
            pied.writeSelectedRows(0, -1, document.leftMargin() + 220, 30 + pied.getTotalHeight(), canvas);
            document.close();
        } catch (Exception e) {

        }
        return response;
    }
}
