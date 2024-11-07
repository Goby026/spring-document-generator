package dev.grover.booksauthors.view.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import dev.grover.booksauthors.domain.Documento;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.util.Map;

@Component("pages/documentos/documentos")
public class OficioPdf extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Documento oficio = (Documento) model.get("oficio");

        try {
        /*PdfPTable table2 = new PdfPTable(1);
        table2.addCell("Oficio N°");
        table2.addCell(""+oficio.getNumero());*/
            Phrase phrase1 = new Phrase(1, new Chunk("Año de la Unidad, la Paz y el Desarrollo \n", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(0, 0, 0))));
            Phrase phrase2 = new Phrase(1, "Chanchamayo, 18 de diciembre de 2023.", FontFactory.getFont(FontFactory.HELVETICA, 10, new Color(56, 54, 54)));

            //Paragraph p1 = new Paragraph("Año de la Unidad, la Paz y el Desarrollo",
            //        FontFactory.getFont(FontFactory.HELVETICA, 15, Font.BOLD, new Color(0, 0, 180)));

            BaseFont bf_helv = BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false);
            Paragraph paragraph = new Paragraph();
            paragraph.add(phrase1);
            paragraph.add(phrase2);

            // headers and footers must be added before the document is opened
            HeaderFooter header = new HeaderFooter( paragraph, false);
            header.setBorder(Rectangle.NO_BORDER);
            header.setAlignment(Element.ALIGN_RIGHT);
            //header.setGrayFill(0.8f);
            document.setHeader(header);

            Image img = Image.getInstance("escudopdf.jpg");
            img.setAbsolutePosition(document.getPageSize().getLeft() + 100,document.getPageSize().getHeight()-100);

            //numeracion de pagina
            //HeaderFooter footer = new HeaderFooter(new Phrase("This is page: ", new Font(bf_helv)), true);
            HeaderFooter footer = new HeaderFooter(new Phrase(8, "JR. LAS GUANABANAS S/N - PAMPA HUASAHUASI - LA MERCED -  CHANCHAMAYO - TELEFONO: 064 532619 \n Correo electrónico: mesadepartes.umlchanchamayo@gmail.com", FontFactory.getFont(FontFactory.HELVETICA, 7, new Color(56, 54, 54))), false);

            footer.setBorder(Rectangle.NO_BORDER);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.setFooter(footer);

            Color lineColor = new Color(30, 29, 29);

            document.setMargins(90, 80, 40, 36);

            document.open();
            document.add(img);
            document.add(new Paragraph(" "));
            document.add( Chunk.NEWLINE );
            document.add(new Phrase(oficio.getTitulo(), FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD, new Color(0, 0, 0))));
            document.add(new Paragraph(new Phrase("DOCTOR(A): ", FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD, new Color(0, 0, 0)))));

            document.add(new Paragraph(new Phrase("cargo--->", FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD, new Color(0, 0, 0)))));
            document.add(new Paragraph(new Phrase("despacho--->", FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD, new Color(0, 0, 0)))));
            document.add(new Paragraph(new Phrase("CIUDAD.- " + oficio.getCiudad(), FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD, new Color(0, 0, 0)))));
            document.add(new Paragraph(new Phrase("ASUNTO: " + oficio.getAsunto(), FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD, new Color(0, 0, 0)))));
            document.add(new Paragraph(new Phrase("REF.: " + oficio.getReferido(), FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD, new Color(0, 0, 0)))));
            document.add(new Paragraph(new Phrase("CASO FISCAL.: " + oficio.getCasofiscal(), FontFactory.getFont(FontFactory.HELVETICA, 12,Font.BOLD, new Color(0, 0, 0)))));
            document.add(new LineSeparator(1, 100,lineColor,Element.ALIGN_CENTER,-7f));
            document.add( Chunk.NEWLINE );
            Paragraph startContent = new Paragraph("\t\t\t\t\t\t\t\tTengo el agrado de dirigirme a Usted, a fin de expresarle mis cordiales saludos y a la vez en atención al documento de la referencia.");
            //startContent.setFirstLineIndent(0.2F);
            document.add(startContent);
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph("\t\t\t\t\t\t\t\t"+oficio.getContenido()));
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph("\t\t\t\t\t\t\t\tLo que informo para los fines pertinentes, y sin otro en particular le reitero mi saludo y le expreso mi especial consideración y estima personal."));
            document.add( Chunk.NEWLINE );
            document.add(new Phrase("Attentamente,"));

        } catch (Exception ex) {
            System.out.println("no image for you");
        } finally {
            document.close();
        }

    }
}
