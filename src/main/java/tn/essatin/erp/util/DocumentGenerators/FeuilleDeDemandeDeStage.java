package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;
import java.io.ByteArrayOutputStream;


import static tn.essatin.erp.util.DocumentGenerators.DocumentFunction.exposant;

public class FeuilleDeDemandeDeStage {
    public static ByteArrayOutputStream createDoc(Enregistrement enregistrement, String nomsociete, int numcase, String designantion) {
        Font fontGras = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
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
            document.setPageSize(PageSize.A4);
            //document.setMargins(15f, 15f, 10f, 10f);
            document.open();
            DocumentFunction.ajouterEnteteSpeciale(document, "DEMANDE DE STAGE", "GES-IMP-17", "02", "21/10/2019", 1, 1);
            document.add(new Paragraph("A l'attention de Monsieur le Directeur de " + designantion +" : "+ nomsociete + "  \n"));
            document.add(new Paragraph(String.format("Objet : ", fontTitle)+" Demande de stage au profit des etudiants de l'Ecole Superieure des Sciences Appliquées               et de la Technologie privée de gabés."));
            document.add(new Paragraph(" \n"));
            Paragraph p = new Paragraph("L'Ecole Superieur des Sciences Appliquées et de la Technologie Privée de Gabés " +
                    "(ESSAT) propose à ses étudiants des cursus de formation à caractére technologique et scientifique " +
                    "comportant des specialités qui nécessitent, pour etre efficaces, la réalisation " +
                    "de stages au sein des entreprise et sociétés industrielles et autres, dans le cadre de l'ouverture " +
                    "de la faculté sur son environnement, et pour permettre aux étudiants d'enrichir leurs " +
                    "connaissances et d'acquérir une meilleure insertion professionnelle . De ce fait, nous avons " +
                    "l'honneur de venir par la présente solliciter l'acceptation de "+(etudiant.getSexe().equalsIgnoreCase("homme")?"l'étudiant":"l'étudiante")+" :" );
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            p.setFirstLineIndent(30);
            document.add(p);
            PdfPTable table=new PdfPTable(4);
            table.getDefaultCell().setFixedHeight(20);
            table.setWidthPercentage(100);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            PdfPCell cell = new PdfPCell(new Phrase(new Chunk("NOM :  ",DocumentFunction.HEAD_FONT)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(new Chunk(etudiant.getNom(),DocumentFunction.ATTRIB_FONT)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase(new Chunk("PRÉNOM :  ",DocumentFunction.HEAD_FONT)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(new Chunk(etudiant.getPrenom(),DocumentFunction.ATTRIB_FONT)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase(new Chunk("N°"+etudiant.getIdIdentificateur().getTypeIdentificateur()+" : ",DocumentFunction.HEAD_FONT)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(new Chunk(etudiant.getNumeroIdentificateur(),DocumentFunction.ATTRIB_FONT)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);


            cell = new PdfPCell(new Phrase(new Chunk("CLASSE :  ",DocumentFunction.HEAD_FONT)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(new Chunk(niveauxC,DocumentFunction.ATTRIB_FONT)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);




            document.add(table);
            document.add(new Phrase("\n"));
            if(numcase == 1){
              document.add(new Phrase("           Pour effectuer un stage \u2611  d'initiation \u2610  de perfectionnement \u2610 PFE , au sein de votre \n           honorable entreprise. "));}
            if(numcase == 2){
                document.add(new Phrase("           Pour effectuer un stage \u2610  d'initiation \u2611  de perfectionnement \u2610 PFE , au sein de votre \n            honorable entreprise "));}
            if(numcase == 3){
                document.add(new Phrase("           Pour effectuer un stage \u2610  d'initiation \u2610  de perfectionnement \u2611 PFE , au sein de votre \n            honorable entreprise"));}

            document.add(new Phrase(" \n           Dans le cas d'une réponse favorable de votre part, vous trouvez ci-joint la lettre d'affectation \n" +
                    "           de l'etudiant(e). \n \n" +
                    "                Il est bien entendu que notre école prendra en charge tous les frais occasionnés par tout \n " +
                    "           éventuel accident subi par l'etudiant durant de son stage. \n" +
                    "           Nous comptons beaucoup sur votre disponibilité et sur votre esprit de collaboration et vous \n" +
                    "           prions d'agréer, Monsieur/Madame, nos sincéres remerciments les plus dévoués. \n"));
           PdfPTable table1=new PdfPTable(4);
           table1.getDefaultCell().setFixedHeight(30);
           table1.setWidthPercentage(80);
           cell = new PdfPCell(new Phrase( "- Stage d'initiation en premiére année : ( 1 mois). \n \n " +
                   "- Stage de perfectionnement en deuxiéme année : ( 1 mois). \n \n " +
                   "- Stage de Projet de Fin d'Etudes : ( 4 mois). \n \n "));
           cell.setColspan(3);
           cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
           table1.addCell(cell);
           cell = new PdfPCell(new Phrase(" "));
           cell.setBorder(Rectangle.NO_BORDER);
           table1.addCell(cell);
           cell = new PdfPCell(new Phrase(" \n  \n  \n "));
           cell.setColspan(3);
           cell.setBorder(Rectangle.NO_BORDER);
           table1.addCell(cell);
           cell = new PdfPCell(new Phrase(" Gabés le   /   /      ******************* \n  \n  \n "));
           cell.setBorder(Rectangle.NO_BORDER);
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
           cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
           table1.addCell(cell);
           document.add(table1);

            DocumentFunction.ajouterPiedDePage(document);

            /***********/
            document.newPage();
            DocumentFunction.ajouterEnteteSpeciale(document, "LETTRE D'AFFECTATION AU STAGE", "GES-IMP-18", "02", "21/10/2019",1, 1);
            document.add(new Phrase(" \n"));
            PdfPTable table2 = new PdfPTable(1);
            table2.getDefaultCell().setFixedHeight(30);
            table2.setWidthPercentage(100);



            cell= new PdfPCell(new Paragraph("  \n Entreprise ou etablissement : ...................................................................................................................\n \n" +
                                                   " \n Adresse : ..................................................................................................................................................\n \n" +
                                                   " \n Responsable / Encadrant Professionnel : ................................................................................................\n \n" +
                                                   " \n Tél : ....................................... E-mail : ....................................................................................................\n " ));
            table2.addCell(cell);
            document.add(table2);
            document.add(new Paragraph("\n \n Nous attestons par la présente que l'étudiant (e) ....................................................................................\n" +
                    "\n a été affecté(e) à notre entreprise pour réaliser un stage de : \n \n"));
            PdfPTable table3=new PdfPTable(5);
            String chaineCrochee = null;
            if(numcase == 1){
                chaineCrochee=" \n \u2611  Initiation (1ére Année) \n \n \u2610  Perfectionnement (2éme Année) \n \n \u2610  PFE (3éme Année) \n \n ";}
            if(numcase == 2){
                chaineCrochee=" \n \u2610  Initiation (1ére Année) \n \n \u2611  Perfectionnement (2éme Année) \n \n \u2610 PFE (3éme Année) \n \n";}
            if(numcase == 1){
                chaineCrochee=" \n \u2610  Initiation (1ére Année) \n \n \u2610  Perfectionnement (2éme Année) \n \n \u2611 PFE (3éme Année) \n \n";}

            cell = new PdfPCell(new Phrase(chaineCrochee) );
            cell.setColspan(3);
            table3.addCell(cell);
            cell = new PdfPCell(new Phrase("\n \n \n ") );
            cell.setColspan(2);
            cell.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell);
            document.add(table3);
            document.add(new Phrase(" \n " +
                    "               et ce, du ..../..../........  au ..../..../........  \n \n "));
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
            document.add(new Paragraph(" \n \n \n \n \n"));
            PdfPTable table5=new PdfPTable(1);
            table1.getDefaultCell().setFixedHeight(20);
            table1.setWidthPercentage(100);
            cell=new PdfPCell(new Paragraph( " \n NB : Nous vous informons que notre école prendra en charge tous les frais \n occasionnes" +
                    " par tout éventuel accident subi par l'étudiant durant de ce stage. \n \n" ));
            table5.addCell(cell);
            document.add(table5);
            DocumentFunction.ajouterPiedDePage(document);
            document.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;}
}
