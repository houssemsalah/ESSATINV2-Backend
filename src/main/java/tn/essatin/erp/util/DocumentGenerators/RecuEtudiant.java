package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.core.io.ClassPathResource;
import tn.essatin.erp.model.Scolarite.Cycle;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.Scolarite.Parcours;
import tn.essatin.erp.model.Scolarite.Specialite;
import tn.essatin.erp.model.financier.ETypeModaliteTransaction;
import tn.essatin.erp.model.financier.ModaliteTransaction;
import tn.essatin.erp.payload.response.TransactionAvecModalite;
import tn.essatin.erp.util.ConvertierMontantEnLettre;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Collections;


public class RecuEtudiant {
    public static Chunk exposant(String text, boolean isGras) {
        Font f;
        if (isGras)
            f = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK);
        else
            f = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        Font supFont = new Font(f);
        supFont.setSize(f.getSize() / 2f);
        Chunk c = new Chunk(text, supFont);
        c.setTextRise(7f);
        return c;
    }

    public static Chunk Gras(String text) {
        Font fontGras = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, BaseColor.BLACK);
        Font supFont = new Font(fontGras);
        return new Chunk(text, supFont);
    }

    public static Chunk titre(String text) {
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD, 18, BaseColor.BLACK);
        Font supFont = new Font(fontTitle);
        return new Chunk(text, supFont);
    }

    public static Chunk mini(String text) {
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES, 8, BaseColor.BLACK);
        Font supFont = new Font(fontTitle);
        return new Chunk(text, supFont);
    }

    public static Chunk textNormal(String text) {
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
        Font supFont = new Font(fontTitle);
        return new Chunk(text, supFont);
    }

    public static Chunk textGras(String text) {
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK);
        Font supFont = new Font(fontTitle);
        return new Chunk(text, supFont);
    }

    public static Chunk textNormalMin(String text) {
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.BLACK);
        Font supFont = new Font(fontTitle);
        return new Chunk(text, supFont);
    }

    public static Chunk textGrasMin(String text) {
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD, 10, BaseColor.BLACK);
        Font supFont = new Font(fontTitle);
        return new Chunk(text, supFont);
    }

    private static Phrase text(Chunk... chunks) {
        Phrase phrase = new Phrase();
        Collections.addAll(phrase, chunks);
        return phrase;
    }

    public static ByteArrayOutputStream createDoc(TransactionAvecModalite transaction, Niveau niveau) {
        boolean isEmpty = transaction.getModaliteTransactionList().isEmpty();

        double montant = 0.0;
        for (ModaliteTransaction modaliteTransaction : transaction.getModaliteTransactionList())
            montant += modaliteTransaction.getMontant();

        String nom = transaction.getClient().getNom();
        String prenom = transaction.getClient().getPrenom();
        String sexe = transaction.getClient().getSexe();
        boolean isHommeE = sexe.equalsIgnoreCase("homme");
        LocalDate date = transaction.getDatePayement();
        Parcours parcours = niveau.getParcours();
        Specialite specialite = parcours.getSpecialite();
        Cycle cycle = specialite.getCycle();
        String niveauxC = niveau.getDesignation();
        String designationNiveaux = cycle.getDescription() + " " + parcours.getDesignation();
        String sex = isHommeE ? "Monsieur" : "Madame";

        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Document document = new Document();
        Rectangle pageSize = PageSize.A6.rotate();


        document.setPageSize(pageSize);
        document.setMargins(10f, 10f, 10f, 10f);
        PdfPCell cell;

        try {
            PdfWriter writer = PdfWriter.getInstance(document, response);
            document.open();
            Image img;

            //File i = ResourceUtils.getFile("classpath:Images/logoEssat.png");
            //FileInputStream ios = new FileInputStream(i);

            ByteArrayOutputStream ous = new ByteArrayOutputStream();
            InputStream ios = new ClassPathResource("Images/logoEssat.png").getInputStream();
            ous.write(ios.readAllBytes());
            img = Image.getInstance(ous.toByteArray());
            PdfContentByte canvas = writer.getDirectContentUnder();

            Image image;
            ByteArrayOutputStream ous1 = new ByteArrayOutputStream();
            InputStream ios1 = new ClassPathResource("Images/arpRecu.png").getInputStream();
            ous1.write(ios1.readAllBytes());
            image = Image.getInstance(ous1.toByteArray());

            image.scaleAbsolute(PageSize.A6.rotate());
            image.setAbsolutePosition(0, 0);
            canvas.addImage(image);

            Image image2;
            //File i2 = ResourceUtils.getFile("classpath:Images/arpRecu.png");
            ByteArrayOutputStream ous2 = new ByteArrayOutputStream();
            InputStream ios2 = new ClassPathResource("Images/arpRecu.png").getInputStream();
            ous2.write(ios2.readAllBytes());
            image2 = Image.getInstance(ous2.toByteArray());

            image2.scaleAbsolute(PageSize.A6.rotate());
            image2.setAbsolutePosition(0, PageSize.A6.rotate().getHeight());
            canvas.addImage(image2);

            float[] ts = {2f, 6f, 2f};
            PdfPTable entete = new PdfPTable(ts);
            entete.setWidthPercentage(100);
            entete.getDefaultCell().setBorder(0);
            entete.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            entete.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            entete.getDefaultCell().setFixedHeight(50);
            entete.addCell(img);
            entete.addCell(text(titre("Re??u de paiement"), textNormalMin("\n\nN??"), textNormalMin(String.format("%010d", transaction.getId()))));
            entete.addCell(new Phrase(mini("Ecole Sup??rieure des Sciences Appliqu??es et de la Technologie Priv??e de Gab??s")));
            document.add(entete);
            if (transaction.getModaliteTransactionList().size() == 1)
                document.add(new Phrase("\n\n"));
            document.add(new Phrase("\n"));
            Paragraph paragraph = new Paragraph();
            paragraph.setFirstLineIndent(20f);
            paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraph.setIndentationLeft(30f);
            paragraph.setIndentationRight(30f);
            if (transaction.getModaliteTransactionList().size() == 1)
                paragraph.setLeading(20);
            else
                paragraph.setLeading(15);

            if (!isEmpty) {


                paragraph.add(text(
                        textNormal("Re??u de payement des frais de scolarit?? de "),
                        textGras(prenom + " " + nom),
                        textNormal(isHommeE ? ", inscrit en " : ", inscrite en "),
                        textGras(niveauxC),
                        exposant((niveauxC.equalsIgnoreCase("1")) ? "??re" : "??me", true),
                        textGras(" ann??e " + designationNiveaux),
                        textNormal(", remis contre la somme de "),
                        textGras(ConvertierMontantEnLettre.enTouteLettre(montant) + " (" + montant + " DT)"),
                        textNormal(", ")


                ));
                Phrase p1 = new Phrase();
                if (transaction.getModaliteTransactionList().size() == 1) {
                    if (transaction.getModaliteTransactionList().get(0).getType().equals(ETypeModaliteTransaction.ESPECES)) {
                        p1.add(textNormal("sous forme d'"));
                        p1.add(textGras(ETypeModaliteTransaction.ESPECES.toString()));
                    } else {
                        p1.add(textNormal("sous forme de "));
                        p1.add(textGras(transaction.getModaliteTransactionList().get(0).getType().toString()));
                        p1.add(textNormal(" portant le num??ro: "));
                        p1.add(textGras(transaction.getModaliteTransactionList().get(0).getNumero()));
                        if (transaction.getModaliteTransactionList().get(0).getType().equals(ETypeModaliteTransaction.CHEQUE) && transaction.getModaliteTransactionList().get(0).getDate() != null) {
                            p1.add(textNormal(" disponible ?? partir du: "));
                            p1.add(textGras(transaction.getModaliteTransactionList().get(0).getDate().format(DocumentFunction.FORMATTER)));
                        }
                        if (transaction.getModaliteTransactionList().get(0).getType().equals(ETypeModaliteTransaction.VIREMENT_BANCAIRE) && transaction.getModaliteTransactionList().get(0).getDate() != null) {
                            p1.add(textNormal(" r??alis?? le: "));
                            p1.add(textGras(transaction.getModaliteTransactionList().get(0).getDate().format(DocumentFunction.FORMATTER)));
                        }
                    }
                    p1.add(textNormal(".\n"));
                } else
                    p1.add(textNormal("r??partie comme suit :\n"));
                paragraph.add(p1);
                document.add(paragraph);
                Paragraph listModalite = new Paragraph();
                listModalite.setAlignment(Element.ALIGN_JUSTIFIED);
                listModalite.setIndentationLeft(40f);
                listModalite.setIndentationRight(30f);
                listModalite.setLeading(15);
                if (transaction.getModaliteTransactionList().size() > 1) {
                    for (ModaliteTransaction modaliteTransaction : transaction.getModaliteTransactionList()) {
                        if (modaliteTransaction.getType().equals(ETypeModaliteTransaction.ESPECES)) {
                            listModalite.add(text(
                                    textNormalMin("- "),
                                    textGrasMin(modaliteTransaction.getType().toString()),
                                    textNormalMin(": "),
                                    textGrasMin(ConvertierMontantEnLettre.enTouteLettre(modaliteTransaction.getMontant()) + " (" + modaliteTransaction.getMontant() + " DT)"),
                                    textNormalMin(".\n")
                            ));
                        }
                        if (modaliteTransaction.getType().equals(ETypeModaliteTransaction.CHEQUE)) {
                            listModalite.add(text(
                                    textNormalMin("- "),
                                    textGrasMin(modaliteTransaction.getType().toString()),
                                    textNormalMin(" N??"),
                                    textGrasMin(modaliteTransaction.getNumero()),
                                    textNormalMin(": "),
                                    textGrasMin(ConvertierMontantEnLettre.enTouteLettre(modaliteTransaction.getMontant()) + " (" + modaliteTransaction.getMontant() + " DT)")

                            ));
                            if (transaction.getModaliteTransactionList().get(0).getDate() != null) {
                                listModalite.add(text(
                                        textNormalMin(" disponible ?? partir du: "),
                                        textGrasMin(transaction.getModaliteTransactionList().get(0).getDate().format(DocumentFunction.FORMATTER))
                                ));
                            }
                            listModalite.add(textNormalMin(".\n"));
                        }
                        if (modaliteTransaction.getType().equals(ETypeModaliteTransaction.VIREMENT_BANCAIRE)) {
                            listModalite.add(text(
                                    textNormalMin("- "),
                                    textGrasMin(modaliteTransaction.getType().toString()),
                                    textNormalMin(" N??"),
                                    textGrasMin(modaliteTransaction.getNumero()),
                                    textNormalMin(": "),
                                    textGrasMin(ConvertierMontantEnLettre.enTouteLettre(modaliteTransaction.getMontant()) + " (" + modaliteTransaction.getMontant() + " DT)")

                            ));
                            if (transaction.getModaliteTransactionList().get(0).getDate() != null) {
                                listModalite.add(text(
                                        textNormalMin(" r??alis?? le: "),
                                        textGrasMin(transaction.getModaliteTransactionList().get(0).getDate().format(DocumentFunction.FORMATTER))
                                ));
                            }
                            listModalite.add(textNormalMin(".\n"));
                        }
                    }
                }
                document.add(listModalite);
                float[] ts2 = {200f};
                PdfPTable pied = new PdfPTable(1);
                pied.setTotalWidth(ts2);
                pied.getDefaultCell().setBorder(0);
                pied.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                pied.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                pied.getDefaultCell().setFixedHeight(50);
                pied.addCell("");
                pied.addCell(text(
                        textNormalMin("Re??u par "),
                        textGrasMin(transaction.getFinancier().getPersonne().getPrenom() + " " + transaction.getFinancier().getPersonne().getNom()),
                        textNormalMin("\nLe: "),
                        textNormalMin(LocalDate.now().format(DocumentFunction.FORMATTER))
                ));
                pied.writeSelectedRows(0, -1, document.leftMargin() + 220, 30 + pied.getTotalHeight(), canvas);


            }else{
                paragraph.add(text(
                        textNormal("Toutes les modalit?? ce cette transaction sont annul?? ou reget??, aucun re??u ne peut ??tre imprim??")
                ));
                document.add(paragraph);
            }


            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static ByteArrayOutputStream createDoc(ByteArrayOutputStream doc) {

        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Document document = new Document();
        Rectangle pageSize = PageSize.A6.rotate();
        document.setPageSize(pageSize);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, response);
            document.addAuthor("ESSAT-In Application");
            document.addCreator("Mohamed Nasr");
            document.addCreationDate();
            document.addLanguage("fr_FR");
            document.addTitle("Re??ue de payement");
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            PdfReader reader = new PdfReader(doc.toByteArray());
            PdfImportedPage page = writer.getImportedPage(reader, 1);
            cb.addTemplate(page, 0, -1, 1, 0, 0, PageSize.A6.rotate().getHeight());
            document.newPage();
            cb.addTemplate(page, 0, -1, 1, 0, 0, PageSize.A6.rotate().getHeight());
            document.close();
        } catch (Exception e) {

        }
        return response;

    }
}
