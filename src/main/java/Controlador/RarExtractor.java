package Controlador;



import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RarExtractor {

    public void unRarFile(String targetPath, String absolutePath, String password) {

        try {

            // La ruta a winrar instalada en el sistema
            String cmd = "C:\\Program Files\\WinRAR\\WinRAR.exe";
            //String unrarCmd = cmd + " x -r -p- " + absolutePath + " "
                    //+ targetPath;
            String[] unrarCmd = {"C:\\Program Files\\WinRAR\\WinRAR.exe", "x", "-p"+password ,"-r", "-o+", absolutePath, targetPath};


            Runtime rt = Runtime.getRuntime();
            Process pre = rt.exec(unrarCmd);
            InputStreamReader isr = new InputStreamReader(pre.getInputStream());
            BufferedReader bf = new BufferedReader(isr);
            String line1 = null;
            while ((line1 = bf.readLine()) != null) {
                line1 = line1.trim();
                if ("".equals(line1)) {
                    continue;
                }
                System.out.println(line1);
            }



            bf.close();
            /*
            //ELIMINAR EL ARCHIVO.RAR
            File fichero = new File("C:\\Users\\Usuario-Dell\\Desktop\\RETO-NOMINA\\Pago nomina web 30-04-2023 - 1.rar");

            if (fichero.delete())
                System.out.println("El fichero ha sido borrado satisfactoriamente");
            else
                System.out.println("El fichero no puede ser borrado");

            String fileName = absolutePath + ".txt";
            String outputDir = "C:";

            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

                // Leer la primera línea y establecerla como el nombre de la columna
                String columnName = br.readLine();

                // Crear título y subtítulo
                String titulo = "SERVICIOS CORPORATIVOS";
                String subtitulo = "COMPROBANTE DE SERVICIO";
                String subtitulo2 = "TRANSFERENCIA DEL BANCO DE LOJA";

                String[] miArray = {"BATCH", "NOMBRE DEL CLIENTE", "IDENTIFICACIÓN" , "CTA_DÉBITO", "COMISIÓN","NRO DE TRANSACCIÓN POR CUENTA", "APROBADORES ", "NOMBRE DEL BENEFICIARIO" , "IDENTIFICACIÓN DEL BENEFICIARIO", "INSTITUCIÓN FINANCIERA RECEPTORA","CUENTA DE CRÉDITO ", "VALOR ", "REFERENCIA DE TRANSACCIÓN" , "FECHA INGRESO", "FECHA APROBACIÓN","OBSERVACIONES ", "ESTADO TRANSACCIÓN" };

                int count = 0;
                while ((columnName = br.readLine()) != null) {
                    // Crear una tabla con una única celda que contenga el texto de la línea actual
                    String line = columnName;
                    ArrayList<String> elementos = new ArrayList<>();
                    String[] splitLine = line.split("\t"); // separar los elementos
                    for (String element : splitLine) {
                        elementos.add(element.trim()); // agregar cada elemento al arreglo
                    }

                    String nombreSalida = new String();
                    try{
                        nombreSalida = elementos.get(0)+" "+elementos.get(7);
                    }catch(Exception e){
                        System.out.println(e);
                    }

                    // Crear un nuevo documento PDF
                    String pdfFileName = nombreSalida+".pdf";
                    PdfWriter writer = new PdfWriter(outputDir + pdfFileName);
                    PdfDocument pdfDoc = new PdfDocument(writer);
                    Document document = new Document(pdfDoc);

                    // Obtener la fecha y hora actual
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
                    String fechaHoraActual = now.format(formatter);

                    // Crear el texto con la fecha y hora actual
                    Paragraph fecha = new Paragraph("Fecha de Impresión: " + fechaHoraActual);
                    fecha.setFontSize(8f);
                    fecha.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                    Paragraph salto = new Paragraph("""
                        
                        """ );

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
                    Paragraph pSubtitulo2= new Paragraph(subtitulo2).setTextAlignment(TextAlignment.CENTER)
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

                    count++;

                    // Agregar la tabla y la imagen al documento y cerrarlo
                    //document.add(image);
                    document.add(table);

                    document.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }*/
        } catch (Exception e) {
            System.out.println ("Se produjo una excepción durante la descompresión");
        }



    }

    /**
     * @param args
     */


}
