package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import tn.essatin.erp.model.Personne;
import tn.essatin.erp.model.Scolarite.Niveau;
import tn.essatin.erp.model.financier.Transaction;

import java.io.ByteArrayOutputStream;

public class RecuEtudiant {
    public static ByteArrayOutputStream createDoc(Transaction transaction, Niveau niveau) {
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response);
            document.setPageSize(PageSize.A4.rotate());
            document.setMargins(15f, 15f, 10f, 10f);
            document.open();


            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
