package Controlador;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;

import javax.swing.text.StyleConstants;
import java.io.BufferedReader;
import java.io.FileReader;


public class TextToPDF {

    public static void main(String[] args) {
        String fileName = "C:\\Users\\Usuario-Dell\\Desktop\\RETO-NOMINA\\Pago nomina web 30-04-2023\\pago nomina web 30-04-2023.txt";
        String outputDir = "C:\\Users\\Usuario-Dell\\Desktop\\RETO-NOMINA\\Pago nomina web 30-04-2023\\pdfs";
        String line = "";



        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Leer la primera línea y establecerla como el nombre de la columna
            String columnName = br.readLine();

            // Leer cada línea restante y crear un PDF para cada una
            System.out.println("Hola guapo");
            //Image image = new Image(ImageDataFactory.create("C:\\Users\\Usuario-Dell\\Desktop\\RETO-NOMINA\\LOGOTIPO INSTITUCIONAL SIMPLIFICADO HORIZONTAL Y VERTICAL-01.png"));

            // Crear título y subtítulo
            String titulo = "SERVICIOS CORPORATIVOS";
            String subtitulo = "COMPROBANTE DE SERVICIO";
            String subtitulo2 = "TRANSFERENCIA DEL BANCO DE LOJA";

            int count = 0;
            while ((line = br.readLine()) != null && count < 2) {
                // Crear un nuevo documento PDF
                String pdfFileName = "hola"+count+".pdf";
                PdfWriter writer = new PdfWriter(outputDir + pdfFileName);
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc);

                // Crear un estilo para el texto
                Style estiloTexto = new Style()
                        //.setFont(PdfFontFactory.createFont(StyleConstants.FontConstants.HELVETICA))
                        .setFontSize(12)
                        .setFontColor(ColorConstants.BLACK);



                // Agregar título al documento
                Paragraph pTitulo = new Paragraph(titulo).setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(20).setBold();
                document.add(pTitulo);

                // Agregar subtítulo al documento
                Paragraph pSubtitulo = new Paragraph(subtitulo).setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(16).setBold();
                document.add(pSubtitulo);

                // Agregar subtítulo2 al documento
                Paragraph pSubtitulo2= new Paragraph(subtitulo2).setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(16).setBold();
                document.add(pSubtitulo2);


                // Crear una tabla con una única celda que contenga el texto de la línea actual
                Table table = new Table(1);
                Paragraph p = new Paragraph(line).addStyle(estiloTexto);
                Cell cell = new Cell().add(p).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);


                /*// Crear una tabla con una única celda que contenga el texto de la línea actual
                Table table = new Table(1);
                Paragraph p = new Paragraph(line);
                Cell cell = new Cell().add(p).setTextAlignment(TextAlignment.CENTER);
                //table.setFixedPosition();
                table.addCell(cell);*/


                // Crear la imagen dentro del ciclo
                ImageData imageData = ImageDataFactory.create("C:\\\\Users\\\\Usuario-Dell\\\\Desktop\\\\RETO-NOMINA\\\\LOGOTIPO INSTITUCIONAL SIMPLIFICADO HORIZONTAL Y VERTICAL-05.png");
                Image image = new Image(imageData);
                image.setFixedPosition(125, 350);
                image.scale(1.3f, 1.3f);

                count++;

                // Agregar la tabla y la imagen al documento y cerrarlo
                document.add(image);
                document.add(table);

                document.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

