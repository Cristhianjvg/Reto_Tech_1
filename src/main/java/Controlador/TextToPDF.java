package Controlador;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import com.itextpdf.io.image.ImageDataFactory;

import javax.swing.text.StyleConstants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class TextToPDF {

    public void pdf (String targetPath, String absolutePath) {

        //String fileName = "C:\\Users\\Usuario-Dell\\Desktop\\Pruebas\\Pago nomina web 30-04-2023\\pago nomina web 30-04-2023.txt";
        //String outputDir = "C:\\Users\\Usuario-Dell\\Desktop\\RETO-NOMINA\\";

        String sub = absolutePath.substring(0, absolutePath.length() - 4);
        System.out.println(sub);
        String fileName = sub+"\\pago nomina web 30-04-2023.txt";
        String outputDir = targetPath+"\\";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            // Leer la primera línea y establecerla como el nombre de la columna
            String columnName = br.readLine();

            // Crear título y subtítulo
            String titulo = "SERVICIOS CORPORATIVOS";
            String subtitulo = "COMPROBANTE DE SERVICIO";
            String subtitulo2 = "TRANSFERENCIA DEL BANCO DE LOJA";

            String[] miArray = {"BATCH", "NOMBRE DEL CLIENTE", "IDENTIFICACIÓN" , "CTA_DÉBITO", "COMISIÓN","NRO DE TRANSACCIÓN POR CUENTA", "APROBADORES ", "NOMBRE DEL BENEFICIARIO" , "IDENTIFICACIÓN DEL BENEFICIARIO", "INSTITUCIÓN FINANCIERA RECEPTORA","CUENTA DE CRÉDITO ", "VALOR ", "REFERENCIA DE TRANSACCIÓN" , "FECHA INGRESO", "FECHA APROBACIÓN","OBSERVACIONES ", "ESTADO TRANSACCIÓN" };


            while ((columnName = br.readLine()) != null) {
                // Crear una tabla con una única celda que contenga el texto de la línea actual
                String line = columnName;
                ArrayList<String> elementos = new ArrayList<>();
                String[] splitLine = line.split("\t"); // separar los elementos
                for (String element : splitLine) {
                    elementos.add(element.trim()); // agregar cada elemento al arreglo
                }

                /*for (String element : elementos) {
                    System.out.println(element);
                }*/

                String nombreSalida = new String();
                try {
                    nombreSalida = elementos.get(0) + " " + elementos.get(7);
                } catch (Exception e) {
                    System.out.println(e);
                }

                //Crear un nuevo documento PDF
                boolean correct = false;
                int count = 0;
                do {
                    String pdfFileName = nombreSalida + ".pdf";
                    if(count>0){
                        pdfFileName = nombreSalida + count + ".pdf";
                    }
                    try {
                        PdfWriter writer = new PdfWriter(outputDir + pdfFileName);
                        PdfDocument pdfDoc = new PdfDocument(writer);
                        Document document = new Document(pdfDoc);
                        correct = true;

                        // Obtener la fecha y hora actual
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
                        String fechaHoraActual = now.format(formatter);

                        // Crear el texto con la fecha y hora actual
                        Paragraph fecha = new Paragraph("Fecha de Impresión: " + fechaHoraActual);
                        fecha.setFontSize(8f);
                        fecha.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                        Paragraph salto = new Paragraph("""
                                                        
                                """);

                        // Agregar el texto al encabezado alineado a la derecha

                        fecha.setWidth(pdfDoc.getDefaultPageSize().getWidth() - 72);
                        //Cell cell = new Cell().add(fecha).setHorizontalAlignment(HorizontalAlignment.RIGHT);
                        document.add(fecha);
                        document.add(salto);


                        // Crear un estilo para el texto
                        Style estiloTexto = new Style()
                                //.setFont(PdfFontFactory.createFont(StyleConstants.FontConstants.HELVETICA))
                                .setFontSize(12)
                                .setFontColor(ColorConstants.BLACK);

                        // Agregar título al documento
                        Paragraph pTitulo = new Paragraph(titulo).setTextAlignment(TextAlignment.CENTER)
                                .setFontSize(18).setBold();
                        document.add(pTitulo);

                        // Agregar subtítulo al documento
                        Paragraph pSubtitulo = new Paragraph(subtitulo).setTextAlignment(TextAlignment.CENTER)
                                .setFontSize(14).setBold();
                        document.add(pSubtitulo);

                        // Agregar subtítulo2 al documento
                        Paragraph pSubtitulo2 = new Paragraph(subtitulo2).setTextAlignment(TextAlignment.CENTER)
                                .setFontSize(14).setBold();
                        document.add(pSubtitulo2);


                        // Crear una tabla con una única celda que contenga el texto de la línea actual
                        Table table = new Table(2);
                        int x = 0;
                        for (String element : elementos) {
                            Paragraph p = new Paragraph(element).addStyle(estiloTexto);
                            Paragraph p1 = new Paragraph(miArray[x]).addStyle(estiloTexto);
                            Cell cell1 = new Cell().add(p1).setTextAlignment(TextAlignment.CENTER);
                            Cell cell2 = new Cell().add(p).setTextAlignment(TextAlignment.CENTER);
                            table.addCell(cell1);
                            table.addCell(cell2);
                            x++;
                        }


                        // Crear la imagen dentro del ciclo
                        ImageData imageData = ImageDataFactory.create("C:\\\\Users\\\\Usuario-Dell\\\\Desktop\\\\RETO-NOMINA\\\\LOGOTIPO INSTITUCIONAL SIMPLIFICADO HORIZONTAL Y VERTICAL-05.png");
                        Image image = new Image(imageData);
                        image.setFixedPosition(125, 350);
                        image.scale(1.3f, 1.3f);

                        // Agregar la tabla y la imagen al documento y cerrarlo
                        //document.add(image);
                        document.add(table);

                        document.close();

                    } catch (Exception e) {
                        count++;
                        System.out.println("Error en creación");
                    }
                } while (correct == false);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

