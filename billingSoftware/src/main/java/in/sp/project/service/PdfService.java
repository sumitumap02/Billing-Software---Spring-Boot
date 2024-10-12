package in.sp.project.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import in.sp.project.entity.Billing_Product;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class PdfService {

    private static final String PDF_DIRECTORY = "src/main/resources/static/PDF/";

    // Method to generate a PDF file in the server
    public void generateBillPdf(Billing_Product bill) throws DocumentException, IOException {
        // Create the PDF directory if it doesn't exist
        File directory = new File(PDF_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();  // Create the directory if it doesn't exist
        }

        // Generate the PDF file path
        String pdfFilePath = PDF_DIRECTORY + "bill_" + bill.getId() + ".pdf";

        // Create and open the PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
        
        document.open();

        // Add billing details to the document
        document.add(new Paragraph("======================================"));
        document.add(new Paragraph("             BILL RECEIPT             "));
        document.add(new Paragraph("======================================"));
        document.add(new Paragraph("Bill ID: " + bill.getId()));
        document.add(new Paragraph("Customer Name: " + bill.getCustomerName()));
        document.add(new Paragraph("Item: " + bill.getItem()));
        document.add(new Paragraph("Amount: " + bill.getAmount()));
        document.add(new Paragraph("Date: " + bill.getDate()));
        document.add(new Paragraph("======================================"));
        document.add(new Paragraph("Thank you for your purchase!"));
        document.add(new Paragraph("======================================"));

        // Close the document
        document.close();
    }

    // Method to generate a PDF file for download in the response
    public void generatePdf(HttpServletResponse response, Billing_Product bill) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=bill_" + bill.getId() + ".pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        
        document.open();

        // Add billing details to the document for the response
        document.add(new Paragraph("======================================"));
        document.add(new Paragraph("             BILL RECEIPT             "));
        document.add(new Paragraph("======================================"));
        document.add(new Paragraph("Bill ID: " + bill.getId()));
        document.add(new Paragraph("Customer Name: " + bill.getCustomerName()));
        document.add(new Paragraph("Item: " + bill.getItem()));
        document.add(new Paragraph("Amount: " + bill.getAmount()));
        document.add(new Paragraph("Date: " + bill.getDate()));
        document.add(new Paragraph("======================================"));
        document.add(new Paragraph("Thank you for your purchase!"));
        document.add(new Paragraph("======================================"));

        // Close the document
        document.close();
    }
}
