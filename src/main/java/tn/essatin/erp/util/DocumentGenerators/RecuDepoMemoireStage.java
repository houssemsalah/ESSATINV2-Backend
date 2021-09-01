package tn.essatin.erp.util.DocumentGenerators;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import tn.essatin.erp.model.Scolarite.Enregistrement;
import tn.essatin.erp.model.Signataire;

import java.io.ByteArrayOutputStream;

public class RecuDepoMemoireStage {

    public static ByteArrayOutputStream createDoc(Enregistrement enregistrement, String nomGroupe, Signataire signataire) {



        ByteArrayOutputStream response = new ByteArrayOutputStream();
        Document document = new Document();
        Rectangle pageSize = PageSize.A4;


        document.setPageSize(pageSize);
        document.setMargins(10f, 10f, 10f, 10f);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, response);
            document.open();



            document.close();
        } catch (Exception e) {

        }
        return response;
    }
}
