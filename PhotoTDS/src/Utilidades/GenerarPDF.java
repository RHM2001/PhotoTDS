package Utilidades;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import Modelo.Usuario;;

public class GenerarPDF {

    public static void generatePDF(List<Usuario> usuarios) throws IOException {
        try {
            PdfWriter writer = new PdfWriter("pdfPhotoTDS.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
            Table table = new Table(3);
            table.setWidth(100);

            table.addCell(createCell("Nombre", font, true));
            table.addCell(createCell("Email", font, true));
            table.addCell(createCell("Presentación", font, true));

            for (Usuario usuario : usuarios) {
                table.addCell(createCell(usuario.getNombre(), font, false));
                table.addCell(createCell(usuario.getEmail(), font, false));
                table.addCell(createCell(usuario.getPresentacionPerfil(), font, false));
            }

            document.add(table);

            document.close();

            System.out.println("PDF generado correctamente.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Cell createCell(String content, PdfFont font, boolean isHeader) {
        Cell cell = new Cell();
        
        // Verificar si el contenido es nulo
        if (content != null) {
            Text text = new Text(content).setFont(font);
            cell.add(new Paragraph(text));
        } else {
            // Establecer un valor predeterminado si el contenido es nulo
            cell.add(new Paragraph("-vacío-"));
        }

        if (isHeader) {
            cell.setTextAlignment(TextAlignment.CENTER);
            cell.setBold();
        }

        return cell;
    }

}
