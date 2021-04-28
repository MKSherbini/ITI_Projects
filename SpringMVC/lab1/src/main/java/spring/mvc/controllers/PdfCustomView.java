package spring.mvc.controllers;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.Map;

public class PdfCustomView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"my-pdf-file.pdf\"");
        document.add(new Paragraph("Generated at " + LocalDate.now()));

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setColor(BaseColor.WHITE);

        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.DARK_GRAY);
        cell.setPadding(5);

        // write table header
        cell.setPhrase(new Phrase("As", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Bs", font));
        table.addCell(cell);

        for (int i = 0; i < 10; i++) {
            table.addCell("A" + i);
            table.addCell("B" + i);
        }
        document.add(table);
    }


//    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
//                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
//        setContentType("application/pdf");
//        Table table = new Table(2);
//        table.addCell("As");
//        table.addCell("Bs");
//        for (int i = 0; i < 10; i++) {
//            table.addCell("A" + i);
//            table.addCell("B" + i);
//        }
//        document.add(table);
//    }
}
