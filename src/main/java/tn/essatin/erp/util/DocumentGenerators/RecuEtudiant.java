package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.essatin.erp.model.Scolarite.*;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.payload.response.TransactionAvecModalite;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;


import static tn.essatin.erp.util.DocumentGenerators.DocumentFunction.exposant;


public class RecuEtudiant {
    public static Chunk Gras(String text) {
        Font fontGras = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, BaseColor.BLACK);
        Font supFont = new Font(fontGras);
        return new Chunk(text, supFont);
    }
    public static Chunk titre(String text) {
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25, BaseColor.BLACK);
        Font supFont = new Font(fontTitle);
        return new Chunk(text, supFont);
    }
    public static ByteArrayOutputStream createDoc(TransactionAvecModalite transaction, Niveau niveau) {

        double montant=0.0;
        for(ModaliteTransaction modaliteTransaction:transaction.getModaliteTransactionList())
            montant+=modaliteTransaction.getMontant();

        String nom = transaction.getClient().getNom();
        String prenom = transaction.getClient().getPrenom();
        String sexe = transaction.getClient().getSexe();
        boolean isHomme = sexe.equalsIgnoreCase("homme");
        LocalDate date= transaction.getDatePayement();

        Parcours parcours = niveau.getParcours();
        Specialite specialite = parcours.getSpecialite();
        Cycle cycle = specialite.getCycle();
        String niveauxC = niveau.getDesignation();
        String designationNiveaux = cycle.getDescription() + " " + parcours.getDesignation();
        String sex ;
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Document document = new Document();
        document.setPageSize(PageSize.A5);
        document.setMargins(15f, 15f, 10f, 10f);
        try {
            PdfWriter.getInstance(document, response);
            document.open();
            Image img = Image.getInstance("logoEssat.png");
            img.scaleAbsoluteHeight(30);
            img.scaleAbsoluteWidth(30);
            PdfPTable signature = new PdfPTable(7);
            signature.getDefaultCell().setFixedHeight(20);
            signature.setWidthPercentage(100);
            PdfPCell cell3 = new PdfPCell(new Paragraph(Gras(" ")));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setColspan(5);
            cell3.setBorder(Rectangle.NO_BORDER);
            signature.addCell(cell3);
            cell3 = new PdfPCell(new Paragraph(Gras(" Signature et Cachet")));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell3.setColspan(2);
            cell3.setBorder(Rectangle.NO_BORDER);
            signature.addCell(cell3);

            PdfPTable entete = new PdfPTable(7);
            entete.getDefaultCell().setFixedHeight(20);
            entete.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Paragraph(Gras("ESSAT Privée \n de Gabes")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(2);
            cell.setBorder(Rectangle.NO_BORDER);
            entete.addCell(cell);
            cell = new PdfPCell(new Phrase(titre("Reçu")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(4);
            cell.setBorder(Rectangle.NO_BORDER);
            entete.addCell(cell);
            cell= new PdfPCell(img);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setBorder(Rectangle.NO_BORDER);
            entete.addCell(cell);
            PdfPTable contenu = new PdfPTable(1);
            contenu.getDefaultCell().setFixedHeight(20);
            contenu.setWidthPercentage(100);
            PdfPCell cell1=new PdfPCell(new PdfPTable(entete));
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setBorder(Rectangle.NO_BORDER);
            contenu.addCell(cell1);
            if (isHomme)
                sex= "MONSIEUR";
            else
                sex = "MADAMME";
            cell1= new PdfPCell(new Phrase("DE "+sex+" : "+nom +" "+prenom));
            cell1.setBorder(Rectangle.NO_BORDER);
            contenu.addCell(cell1);

            Phrase niv = new Phrase("      Classe : " + niveauxC);
            if (Integer.parseInt(niveauxC) == 1) {
                niv.add(exposant("ère"));
                niv.add(" Année ");
            } else {
                niv.add(exposant("ème"));
                niv.add(" Années ");
            }
            niv.add(designationNiveaux);
            cell1= new PdfPCell(new Phrase(("Classe : "+ niv + "  Date : "+ date)));
            cell1.setBorder(Rectangle.NO_BORDER);
            contenu.addCell(cell1);
            //transaction.getType();
           // if(liste.size ==1){
            cell1= new PdfPCell( new Phrase( "La somme de : "+ montant));
            cell1.setBorder(Rectangle.NO_BORDER);
            contenu.addCell(cell1);
            //cell1= new PdfPCell(new Phrase(" "+type));
            //cell1.setBorder(Rectangle.NO_BORDER);
            //contenu.addCell(cell1);
            // else (parcourir toute la liste  et ajouter dans cette cellule
            cell1 = new PdfPCell( signature);
            cell1.setBorder(Rectangle.NO_BORDER);
            contenu.addCell(cell1);
            PdfPTable sur2= new PdfPTable(1);
            PdfPCell cell2 = new PdfPCell( contenu);sur2.addCell(cell2);
            cell2 = new PdfPCell( contenu);
            sur2.addCell(cell2);
            document.add(sur2);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
