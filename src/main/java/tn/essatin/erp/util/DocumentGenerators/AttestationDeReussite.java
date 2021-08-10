package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.Session;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import static tn.essatin.erp.util.DocumentGenerators.DocumentFunction.exposant;

public class AttestationDeReussite {
    public static ByteArrayOutputStream createDoc(Enregistrement enregistrement, String TypeSession, int NumMention, Date date) {

        Font fontGras = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, BaseColor.BLACK);
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25, BaseColor.BLACK);
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Personne etudiant = enregistrement.getIdInscription().getIdEtudiant().getIdPersonne();
        Session session = enregistrement.getIdSession();
        Niveau niveau = enregistrement.getIdNiveau();
        Parcours parcours = niveau.getParcours();
        Specialite specialite = parcours.getSpecialite();
        Cycle cycle = specialite.getCycle();
        String niveauxC = niveau.getDesignation();
        String designationNiveaux = cycle.getDescription() + " " + parcours.getDesignation();

        Phrase niv = new Phrase(" ");
        if (Integer.parseInt(niveauxC) == 1) {
            niv.add(exposant("ère"));
            niv.add(" Année ");
        } else {
            niv.add(exposant("ème"));
            niv.add(" Années ");
        }
        Document document = new Document();
        Image img ;
        String Mention= null;
        try {
            PdfWriter.getInstance(document, response);
            document.setPageSize(PageSize.A4);
            document.setMargins(15f, 15f, 10f, 10f);
            document.open();
            PdfPTable table=new PdfPTable(3);
            table.getDefaultCell().setFixedHeight(20);
            table.setWidthPercentage(100f);
            PdfPCell cell =new PdfPCell(new Paragraph("République Tunisienne \n Ecole Supérieur des Science Appliquées \n " +
                                                            "et de la Technologie Privée de Gabés \n ", fontGras));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            cell= new PdfPCell(new Paragraph(" "));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            img = Image.getInstance("logoEssat.png");
            img.scaleAbsoluteHeight(50);
            img.scaleAbsoluteWidth(50);
            cell=new PdfPCell(img);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            cell= new PdfPCell(new Paragraph(" "));
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            cell=new PdfPCell(new Phrase(" Attestation de Réussite ",fontTitle));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(2);
            table.addCell(cell);
            document.add(table);
            document.add (new Paragraph(" \n \n          Le Directeur de l'Ecole des Science Appliquées et de la \n  " +
                    "Technologie Privée de Gabés, soussigné atteste, que l'étudiant(e) :  \n "));
            PdfPTable table1=new PdfPTable(5);
            table1.getDefaultCell().setFixedHeight(20);
            table1.setWidthPercentage(100f);
            PdfPCell cell1 =new PdfPCell(new Phrase(" Nom        :"));
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);
            cell1=new PdfPCell(new Phrase(etudiant.getNom(),fontGras));
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setColspan(4);
            table1.addCell(cell1);
            cell1=new PdfPCell(new Phrase(" Prénom     :"));
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);
            cell1=new PdfPCell(new Phrase(etudiant.getPrenom(),fontGras));
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setColspan(4);
            table1.addCell(cell1);
            cell1 =new PdfPCell(new Phrase(" Né(e) le   :"));
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);
            cell1 =new PdfPCell(new Phrase(String.valueOf(etudiant.getDateDeNaissance()),fontGras));
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);
            cell1 =new PdfPCell(new Phrase(" à "));
            cell1.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell1);
            cell1=new PdfPCell(new Phrase(etudiant.getLieuDeNaissance(),fontGras));
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setColspan(2);
            table1.addCell(cell1);
            cell1=new PdfPCell(new Phrase(" Titulaire de la carte d'identité Nationale N° : "));
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setColspan(3);
            table1.addCell(cell1);
            cell1=new PdfPCell(new Phrase(etudiant.getNumeroIdentificateur(),fontGras));
            cell1.setBorder(Rectangle.NO_BORDER);
            cell1.setColspan(4);
            table1.addCell(cell1);
            document.add(table1);
            if (NumMention==1)
                Mention="Excellent";
            if (NumMention==2)
                Mention="Trés Bien";
            if (NumMention==3)
                Mention="Bien";
            if (NumMention==4)
                Mention = "Assez bien";
            if (NumMention==5)
                Mention="Passable";
            document.add(new Paragraph("   Inscrit(e) sous le numéro en l'année univercitaire "+etudiant.getNumeroIdentificateur()+"a subi avec succèes les \n " +
                    "épreuves des examens de la  "+niv + "  \n"));
            document.add(new Phrase( " du Diplome :  "+ designationNiveaux+" \n \n \n "));
            document.add(new Phrase( " A la Session :      "+ TypeSession+"              avec la Mention :    "+ Mention +" \n"));
            document.add(new Phrase("                La présente attestation est attribuée à l'intéressé(e) pour servir et valoir ce \n " +
                    "que de droit. \n"));
            PdfPTable table2= new PdfPTable(3);
            PdfPCell cell2=new PdfPCell(new Phrase( " "));
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setColspan(2);
            table2.addCell(cell2);
            cell2= new PdfPCell(new Paragraph("Fait à Gabés, le  "+date+"  "));
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);
            cell2=new PdfPCell(new Phrase( " "));
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setColspan(2);
            table2.addCell(cell2);
            cell2= new PdfPCell(new Paragraph(" Le Directeur ",fontGras));
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);
            cell2=new PdfPCell(new Phrase( " "));
            cell2.setBorder(Rectangle.NO_BORDER);
            cell2.setColspan(2);
            table2.addCell(cell2);
            cell2= new PdfPCell(new Paragraph("********Signateur***********  "));
            cell2.setBorder(Rectangle.NO_BORDER);
            table2.addCell(cell2);
            document.add(table2);
            document.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;}
}


