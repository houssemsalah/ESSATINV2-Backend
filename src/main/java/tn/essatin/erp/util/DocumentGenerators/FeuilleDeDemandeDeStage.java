package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import tn.essatin.erp.dao.SignataireDao;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;
import tn.essatin.erp.model.Signataire;

import java.io.*;
import java.time.LocalDate;

import static tn.essatin.erp.util.DocumentGenerators.DocumentFunction.*;

public class FeuilleDeDemandeDeStage {
    final SignataireDao signataireDao;

    public FeuilleDeDemandeDeStage(SignataireDao signataireDao) {
        this.signataireDao = signataireDao;
    }

    public static ByteArrayOutputStream createDoc(Enregistrement enregistrement, String nomsociete, int numcase,
                                                  String designantion, Signataire signataire, LocalDate localDate) {
        Image uncheked1 = null;
        Image cheked1 = null;

        try {
            ByteArrayOutputStream ous = new ByteArrayOutputStream();
            InputStream ios = new ClassPathResource("Images/uncheked.png").getInputStream();
            ous.write(ios.readAllBytes());
            uncheked1 = Image.getInstance(ous.toByteArray());

            uncheked1.scaleAbsoluteHeight(15);
            uncheked1.scaleAbsoluteWidth(15);

            ByteArrayOutputStream ous1 = new ByteArrayOutputStream();
            InputStream ios1 = new ClassPathResource("Images/cheked.png").getInputStream();
            ous1.write(ios1.readAllBytes());
            cheked1 = Image.getInstance(ous1.toByteArray());

            cheked1.scaleAbsoluteHeight(15);
            cheked1.scaleAbsoluteWidth(15);
        } catch (IOException | BadElementException ioe) {
            System.out.println();
        }
        PdfPCell uncheked = new PdfPCell(uncheked1);
        uncheked.setBorder(0);
        PdfPCell cheked = new PdfPCell(cheked1);
        cheked.setBorder(0);
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
        Paragraph espacementVerticale = new Paragraph(new Phrase("\n"));
        espacementVerticale.setLeading(2f, 2f);

        try {
            PdfWriter.getInstance(document, response);
            document.setPageSize(PageSize.A4);
            document.open();

            DocumentFunction.ajouterEnteteSpeciale(document, "DEMANDE DE STAGE", "GES-IMP-17", "02", "21/10/2019", 1, 1);
            document.add(espacementVerticale);
            Paragraph paragraph = new Paragraph();
            paragraph.setMultipliedLeading(1.5f);
            Phrase phrase = new Phrase("A l'attention de Monsieur le Directeur de " + designantion + ": " + nomsociete + "\n\n\n");
            paragraph.add(phrase);
            Chunk chank = new Chunk("Objet: ", DocumentFunction.FONT_GRAS);
            paragraph.add(chank);
            phrase = new Phrase("Demande de stage au profit des etudiants de l'Ecole Superieure des Sciences Appliquées et de la Technologie privée de gabés.");
            paragraph.add(phrase);
            document.add(paragraph);
            document.add(espacementVerticale);
            Paragraph p = new Paragraph("L'Ecole Superieur des Sciences Appliquées et de la Technologie Privée de Gabés " +
                    "(ESSAT) propose à ses étudiants des cursus de formation à caractére technologique et scientifique " +
                    "comportant des specialités qui nécessitent, pour etre efficaces, la réalisation " +
                    "de stages au sein des entreprise et sociétés industrielles et autres, dans le cadre de l'ouverture " +
                    "de la faculté sur son environnement, et pour permettre aux étudiants d'enrichir leurs " +
                    "connaissances et d'acquérir une meilleure insertion professionnelle . De ce fait, nous avons " +
                    "l'honneur de venir par la présente solliciter l'acceptation de " + (etudiant.getSexe().equalsIgnoreCase("homme") ? "l'étudiant" : "l'étudiante") + " :");
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            p.setFirstLineIndent(30);
            document.add(p);
            document.add(espacementVerticale);
            PdfPTable table = new PdfPTable(6);
            table.getDefaultCell().setFixedHeight(20);
            table.setWidthPercentage(100);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            PdfPCell cell = new PdfPCell(new Phrase(new Chunk("Nom :  ", DocumentFunction.FONT_GRAS)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(new Chunk(etudiant.getNom(), DocumentFunction.FONT_GRAS_OBLIQUE)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(new Chunk("Prénom :  ", DocumentFunction.FONT_GRAS)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(new Chunk(etudiant.getPrenom(), DocumentFunction.FONT_GRAS_OBLIQUE)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(new Chunk("N°" + etudiant.getIdIdentificateur().getTypeIdentificateur() + " : ", DocumentFunction.FONT_GRAS)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(new Chunk(etudiant.getNumeroIdentificateur(), DocumentFunction.FONT_GRAS_OBLIQUE)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(new Chunk("Classe :  ", DocumentFunction.FONT_GRAS)));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            cell = new PdfPCell(DocumentFunction.niveaux(enregistrement.getIdNiveau()));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(3);
            table.addCell(cell);
            document.add(table);
            document.add(espacementVerticale);

            /*
            float[] columnWidths = {7, 0.7f, 4, 0.7f, 6, 0.7f, 7};
            PdfPTable pf = new PdfPTable(columnWidths);
            pf.setWidthPercentage(100);
            pf.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            pf.getDefaultCell().setVerticalAlignment(Element.ALIGN_TOP);
            pf.addCell("Pour effectuer un stage ");
            String init = "d'initiation";
            String perf = "de perfectionnement";
            String pfe = "PFE , au sein de votre";
            String fin = "honorable entreprise.";
            if (numcase == 1) {
                pf.addCell(cheked);
                pf.addCell(init);
                pf.addCell(uncheked);
                pf.addCell(perf);
                pf.addCell(uncheked);
                pf.addCell(pfe);
            }
            if (numcase == 2) {
                pf.addCell(uncheked);
                pf.addCell(init);
                pf.addCell(cheked);
                pf.addCell(perf);
                pf.addCell(uncheked);
                pf.addCell(pfe);
            }
            if (numcase == 3) {
                pf.addCell(uncheked);
                pf.addCell(init);
                pf.addCell(uncheked);
                pf.addCell(perf);
                pf.addCell(cheked);
                pf.addCell(pfe);
            }
            pf.addCell(fin);
            pf.addCell("");
            pf.addCell("");
            pf.addCell("");
            pf.addCell("");
            pf.addCell("");
            pf.addCell("");
            document.add(pf);
            */
            String debut="Pour effectuer un stage ";
            String init = "d'initiation";
            String perf = "de perfectionnement";
            String pfe = "de PFE";
            String fin = ",  au sein de votrehonorable entreprise.";
            Phrase pr = new Phrase(debut);
            Chunk c=null;
            if (numcase == 1) {
                c=new Chunk(init,FONT_GRAS);
            }
            if (numcase == 2) {
                c=new Chunk(perf,FONT_GRAS);
            }
            if (numcase == 3) {
                c=new Chunk(pfe,FONT_GRAS);
            }
            pr.add(c);
            pr.add(fin);
            document.add(new Paragraph(pr));
            document.add(espacementVerticale);
            p= new Paragraph("Dans le cas d'une réponse favorable de votre part, vous trouvez ci-joint la lettre d'affectation \n" +
                    "de "+ (etudiant.getSexe().equalsIgnoreCase("homme") ? "l'étudiant" : "l'étudiante") + ".");
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            p.setFirstLineIndent(30);
            document.add(p);
            p= new Paragraph("Il est bien entendu que notre école prendra en charge tous les frais occasionnés par tout " +
                    "éventuel accident subi par "+ (etudiant.getSexe().equalsIgnoreCase("homme") ? "l'étudiant" : "l'étudiante") + " durant de son stage." +
                    "Nous comptons beaucoup sur votre disponibilité et sur votre esprit de collaboration et vous " +
                    "prions d'agréer, Monsieur/Madame, nos sincéres remerciments les plus dévoués.");
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            p.setFirstLineIndent(30);
            document.add(p);
            PdfPTable table1 = new PdfPTable(3);
            table1.getDefaultCell().setFixedHeight(30);
            table1.setWidthPercentage(80);
            table1.getDefaultCell().setBorder(0);
            table1.addCell("");table1.addCell("");
            cell = new PdfPCell(new Phrase(" Gabés le  "+ (localDate==null ? LocalDate.now().format(FORMATTER):localDate.format(FORMATTER))+" Le "+signataire.getPoste()+"\n"+signataire.getEmployer().getPersonne().getNom()+" "+signataire.getEmployer().getPersonne().getPrenom()));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            table1.addCell(cell);
            document.add(table1);
            document.add(espacementVerticale);
            document.add(espacementVerticale);

            DocumentFunction.ajouterPiedDePage(document);

            document.newPage();
            DocumentFunction.ajouterEnteteSpeciale(document, "LETTRE D'AFFECTATION AU STAGE", "GES-IMP-18", "02", "21/10/2019", 1, 1);
            document.add(new Phrase(" \n"));
            PdfPTable table2 = new PdfPTable(1);
            table2.getDefaultCell().setFixedHeight(30);
            table2.setWidthPercentage(100);


            cell = new PdfPCell(new Paragraph("  \n Entreprise ou etablissement : ...................................................................................................................\n \n" +
                    " \n Adresse : ..................................................................................................................................................\n \n" +
                    " \n Responsable / Encadrant Professionnel : ................................................................................................\n \n" +
                    " \n Tél : ....................................... E-mail : ....................................................................................................\n "));
            table2.addCell(cell);
            document.add(table2);
            document.add(new Paragraph("\n \n Nous attestons par la présente que l'étudiant (e) ....................................................................................\n" +
                    "\n a été affecté(e) à notre entreprise pour réaliser un stage de : \n \n"));
            PdfPTable table3 = new PdfPTable(5);
            String chaineCrochee = null;
            if (numcase == 1) {
                chaineCrochee = " \n \u2611  Initiation (1ére Année) \n \n \u2610  Perfectionnement (2éme Année) \n \n \u2610  PFE (3éme Année) \n \n ";
            }
            if (numcase == 2) {
                chaineCrochee = " \n \u2610  Initiation (1ére Année) \n \n \u2611  Perfectionnement (2éme Année) \n \n \u2610 PFE (3éme Année) \n \n";
            }
            if (numcase == 1) {
                chaineCrochee = " \n \u2610  Initiation (1ére Année) \n \n \u2610  Perfectionnement (2éme Année) \n \n \u2611 PFE (3éme Année) \n \n";
            }

            cell = new PdfPCell(new Phrase(chaineCrochee));
            cell.setColspan(3);
            table3.addCell(cell);
            cell = new PdfPCell(new Phrase("\n \n \n "));
            cell.setColspan(2);
            cell.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell);
            document.add(table3);
            document.add(new Phrase(" \n " +
                    "               et ce, du ..../..../........  au ..../..../........  \n \n "));
            PdfPTable table4 = new PdfPTable(2);

            cell = new PdfPCell(new Paragraph(" Le responsable du service concerné \n \n  (Cachet et signature)"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setBorder(Rectangle.NO_BORDER);
            table4.addCell(cell);
            cell = new PdfPCell(new Paragraph(" Le ********** de l'ESSAt \n \n  (Cachet et signature)"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            cell.setBorder(Rectangle.NO_BORDER);
            table4.addCell(cell);
            document.add(table4);
            document.add(new Paragraph(" \n \n \n \n \n"));
            PdfPTable table5 = new PdfPTable(1);
            table1.getDefaultCell().setFixedHeight(20);
            table1.setWidthPercentage(100);
            cell = new PdfPCell(new Paragraph(" \n NB : Nous vous informons que notre école prendra en charge tous les frais \n occasionnes" +
                    " par tout éventuel accident subi par l'étudiant durant de ce stage. \n \n"));
            table5.addCell(cell);
            document.add(table5);
            DocumentFunction.ajouterPiedDePage(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
