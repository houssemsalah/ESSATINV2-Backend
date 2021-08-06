package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.parser.Line;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.util.Global;

import javax.validation.constraints.Null;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static tn.essatin.erp.util.DocumentGenerators.DocumentFunction.exposant;

public class FeuilleDeDemandeDeStage {
    public static ByteArrayOutputStream createDoc(Enregistrement enregistrement, String nomsociete, int numcase) {

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.BLACK);
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Personne etudiant = enregistrement.getIdInscription().getIdEtudiant().getIdPersonne();
        Session session = enregistrement.getIdSession();
        Niveau niveau = enregistrement.getIdNiveau();
        Parcours parcours = niveau.getParcours();
        Specialite specialite = parcours.getSpecialite();
        Cycle cycle = specialite.getCycle();
        String niveauxC = niveau.getDesignation();
        String designationNiveaux = cycle.getDescription() + " " + parcours.getDesignation();

        Phrase niv = new Phrase("" + niveauxC);
        if (Integer.parseInt(niveauxC) == 1) {
            niv.add(exposant("ère"));
            niv.add(" Année ");
        } else {
            niv.add(exposant("ème"));
            niv.add(" Années ");
        }
        niv.add(designationNiveaux);
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, response);
            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(15f, 15f, 10f, 10f);
            document.open();
            DocumentFunction.ajouterEnteteSpeciale(document, "DEMANDE DE STAGE", "GES-IMP-17", "02", "21/10/2019", 1, 1);
            document.add(new Paragraph("\n \n A l'attention de Monsieur le Directeur " + nomsociete + " \n"));
            document.add(new Phrase("Objet : ", fontTitle));
            document.add(new Paragraph("Demande de stage au profit des etudiants de l'Ecole Superieure des Sciences Appliquées et de la Technologie privée de gabés."));
            document.add(new Paragraph("\n \n"));
            document.add(new Paragraph("          L'Ecole Superieur des Sciences Appliquées et de la Technologie Privée de Gabés \n" +
                    " (ESSAT) propose à ses étudiants des cursus de formation à caractére technologique et \n" +
                    "scientifique comportant des specialités qui nécessitent, pour etre efficaces, la réalisation de \n" +
                    "stages au sein des entreprise et sociétés industrielles et autres, dans le cadre de l'ouverture \n" +
                    "de la faculté sur son environnement, et pour permettre aux étudiants d'enrichir leurs \n" +
                    "connaissances et d'acquérir une meilleure insertion professionnelle . De ce fait, nous avons \n" +
                    "l'honneur de venir par la présente solliciter l'acceptation de l'étudiants(e) : \n"));
            document.add(new Phrase("\n"));
            PdfPTable table=new PdfPTable(2);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            PdfPCell cell = new PdfPCell(new Phrase("NOM :"+etudiant.getNom()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("PRENOM :"+etudiant.getPrenom()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("N CIN :"+etudiant.getNumeroIdentificateur()));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("CLASSE :"+niv));
            table.addCell(cell);
            document.add(table);
            document.add(new Phrase("\n"));
            document.add(new Phrase("\n"));
            if(numcase == 1){
              document.add(new Phrase("Pour effectuer un stage \u2611  d'initiation \u2610  de perfectionnement \u2610 PFE , au sein de \n"));}
            if(numcase == 2){
                document.add(new Phrase("Pour effectuer un stage \u2610  d'initiation \u2611  de perfectionnement \u2610 PFE , au sein de \n"));}
            if(numcase == 3){
                document.add(new Phrase("Pour effectuer un stage \u2610  d'initiation \u2610  de perfectionnement \u2611 PFE , au sein de \n"));}

            document.add(new Phrase("votre honorable entreprise. \n " +
                    "Dans le cas d'une réponse favorable de votre part, vous trouvez ci-joint la lettre d'affectation \n" +
                    "de l'etudiant(e). \n \n" +
                    "     Il est bien entendu que notre école prendra en charge tous les frais occasionnés par tout \n " +
                    "éventuel accident subi par l'etudiant durant de son stage. \n" +
                    "Nous comptons beaucoup sur votre disponibilité et sur votre esprit de collaboration et vous \n" +
                    "prions d'agréer, Monsieur/Madame, nos sincéres remerciments les plus dévoués. \n"));
           PdfPTable table1=new PdfPTable(3);
           cell = new PdfPCell(new Phrase( "- Stage d'initiation en premiére année : ( 1 mois). \n" +
                   "- Stage de perfectionnement en deuxiéme année : ( 1 mois). \n" +
                   "- Stage de Projet de Fin d'Etudes : ( 4 mois). \n"));
           cell.setColspan(2);
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
           table1.addCell(cell);
           cell = new PdfPCell(new Phrase(" "));
           cell.setBorder(Rectangle.BOTTOM | Rectangle.LEFT | Rectangle.TOP);
           table1.addCell(cell);
           cell = new PdfPCell(new Phrase(" \n  \n  \n "));
           cell.setColspan(2);
           cell.setBorder(Rectangle.NO_BORDER);
           table1.addCell(cell);
           cell = new PdfPCell(new Phrase(" Gabés le   /   /      ******************* \n  \n  \n "));
           cell.setBorder(Rectangle.NO_BORDER);
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
           table1.addCell(cell);
           document.add(table1);

            DocumentFunction.ajouterPiedDePage(document);
            document.newPage();
            DocumentFunction.ajouterEnteteSpeciale(document, "LETTRE D'AFFECTATION AU STAGE", "GES-IMP-18", "02", "21/10/2019",1, 1);
            document.add(new Phrase(" \n"));
            PdfPTable table2 = new PdfPTable(1);
            cell= new PdfPCell(new Paragraph(" Entreprise ou etablissement : ..................................................................................\n \n" +
                                                   " Adresse : ......................................................................................................\n \n" +
                                                   " Tél : .................................. E-mail : ..............................................................\n " ));
            table2.addCell(cell);
            document.add(table2);
            document.add(new Paragraph("\n \n Nous attestons par la présente que l'étudiant (e) ..................................................................\n" +
                    "\n a été affecté(e) à notre entreprise pour réaliser un stage de :\n"));
            PdfPTable table3=new PdfPTable(5);
            String chaineCrochee = null;
            if(numcase == 1){
                chaineCrochee=" \u2611  Initiation (1ére Année) \n \u2610  Perfectionnement (2éme Année) \n \u2610 PFE (3éme Année) \n";}
            if(numcase == 2){
                chaineCrochee=" \u2610  Initiation (1ére Année) \n \u2611  Perfectionnement (2éme Année) \n \u2610 PFE (3éme Année) \n";}
            if(numcase == 1){
                chaineCrochee=" \u2610  Initiation (1ére Année) \n \u2610  Perfectionnement (2éme Année) \n \u2611 PFE (3éme Année) \n";}

            cell = new PdfPCell(new Phrase(chaineCrochee) );
            cell.setColspan(2);
            table3.addCell(cell);
            cell = new PdfPCell(new Phrase("\n \n \n ") );
            cell.setColspan(3);
            cell.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell);
            document.add(table3);
            document.add(new Phrase("\n \n " +
                    "et ce, du ..../..../........  au ..../..../........  \n \n \n \n "));
            PdfPTable table4=new PdfPTable(2);

            cell=new PdfPCell(new Paragraph( " Le responsable du service concerné \n \n  (Cachet et signature)" ));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setBorder(Rectangle.NO_BORDER);
            table4.addCell(cell);
            cell=new PdfPCell(new Paragraph( " Le ********** de l'ESSAt \n \n  (Cachet et signature)" ));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setBorder(Rectangle.NO_BORDER);
            table4.addCell(cell);
            document.add(table4);
            PdfPTable table5=new PdfPTable(1);
            cell=new PdfPCell(new Paragraph( " NB : Nous vous informons que notre école prendra en charge tous les frais occasionnes par tout \n " +
                    "éventuel accident subi par l'étudiant durant de ce stage." ));
            table5.addCell(cell);
            document.add(table5);
            DocumentFunction.ajouterPiedDePage(document);
            document.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;}
}
