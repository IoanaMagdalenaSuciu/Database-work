package start;


import Model.Orders;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * This class generate the invoice
 */
public class FileWriter {

    /**
     * This method generate the invoice
     * @param order the order for which the invoice is generated
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public void generateBill(Orders order) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Bill"+order.getId()+".pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Paragraph p1 = new Paragraph("Order number "+order.getId(), font);
        document.add(p1);
        Paragraph p2 = new Paragraph("Client name " + order.getClientName());
        document.add(p2);
        Paragraph p3 = new Paragraph("Product: "+ order.getProductName() + " quantity:  "+ order.getCant());
        document.add(p3);
        document.close();
    }
}
