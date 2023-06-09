package Controlador;

import Vista.VentanaAviso;
import Vista.VentanaPrincipal3;
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

import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class TextToPDF {

    public void pdf (String targetPath, String absolutePath , VentanaPrincipal3 progreso) { // targetPath
        String outputDir = targetPath+"\\";
        String errores = "";
        int contador = 0;
        int excepciones = 0;
        float avance = 0;
        String espacio = "";

        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {

            // Leer la primera línea y establecerla como el nombre de la columna
            String columnName = br.readLine();

            // Crear título y subtítulo
            String titulo = "SERVICIOS CORPORATIVOS";
            String subtitulo = "COMPROBANTE DE SERVICIO";
            String subtitulo2 = "TRANSFERENCIA DEL BANCO DE LOJA";

            String[] miArray = {"BATCH", "NOMBRE DEL CLIENTE", "IDENTIFICACIÓN" , "CTA_DÉBITO", "COMISIÓN","NRO DE TRANSACCIÓN POR CUENTA", "APROBADORES ", "NOMBRE DEL BENEFICIARIO" , "IDENTIFICACIÓN DEL BENEFICIARIO", "INSTITUCIÓN FINANCIERA RECEPTORA","CUENTA DE CRÉDITO ", "VALOR ", "REFERENCIA DE TRANSACCIÓN" , "FECHA INGRESO", "FECHA APROBACIÓN","OBSERVACIONES ", "ESTADO TRANSACCIÓN" };

            BarraDeProgreso barra = new BarraDeProgreso();

            while ((columnName = br.readLine()) != null) {
                // Crear una tabla con una única celda que contenga el texto de la línea actual
                String line = columnName;
                ArrayList<String> elementos = new ArrayList<>();
                String[] splitLine = line.split("\t"); // separar los elementos
                for (String element : splitLine) {
                    elementos.add(element.trim()); // agregar cada elemento al arreglo
                }

                String nombreSalida = new String();
                try {
                    nombreSalida = elementos.get(0) + " " + elementos.get(7);
                } catch (Exception e) {
                    System.out.println(e);
                }

                //System.out.println(contador); // Controlador para ir analizando si se generan los PDFs

                //Crear un nuevo documento PDF---------------------------------------------------
                boolean correct = false;
                int count = 0;
                do {
                    String pdfFileName = "\\"+nombreSalida + ".pdf";
                    String rutaCarpetas = outputDir+ elementos.get(0);
                    if(count>0){
                        pdfFileName = nombreSalida + count + ".pdf";
                    }

                    File carpeta = new File(rutaCarpetas);
                    if (!carpeta.exists()) {
                        // Crea la carpeta y todas las carpetas padre necesarias
                        carpeta.mkdirs();
                    }

                    try {
                        PdfWriter writer = new PdfWriter(rutaCarpetas+pdfFileName);
                        PdfDocument pdfDoc = new PdfDocument(writer);
                        Document document = new Document(pdfDoc);
                        correct = true;

                        // Obtener la fecha y hora actual------------------------------------------------
                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
                        String fechaHoraActual = now.format(formatter);

                        //COMIENZA EL FORMATEADO PARA EL PDF Y SU CREACIÓN---------------------------------
                        // Crear el texto con la fecha y hora actual---------------------------------------
                        Paragraph fecha = new Paragraph("Fecha de Impresión: " + fechaHoraActual);
                        fecha.setFontSize(8f);
                        fecha.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                        Paragraph salto = new Paragraph("""
                                                        
                                """);

                        // Agregar el texto al encabezado alineado a la derecha ----------------------------

                        fecha.setWidth(pdfDoc.getDefaultPageSize().getWidth() - 72);
                        //Cell cell = new Cell().add(fecha).setHorizontalAlignment(HorizontalAlignment.RIGHT);
                        document.add(fecha);
                        document.add(salto);


                        // Crear un estilo para el texto
                        Style estiloTextop = new Style()
                                //.setFont(PdfFontFactory.createFont(StyleConstants.FontConstants.HELVETICA))
                                .setFontSize(9)
                                .setFontColor(ColorConstants.BLACK);

                        // Crear un estilo para el texto
                        Style estiloTextop1 = new Style()
                                //.setFont(PdfFontFactory.createFont(StyleConstants.FontConstants.HELVETICA))
                                .setFontSize(11)
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

                        //agregar un espacio
                        Paragraph esp = new Paragraph(espacio).setTextAlignment(TextAlignment.CENTER)
                                .setFontSize(14).setBold();
                        document.add(esp);


                        // Crear una tabla con una única celda que contenga el texto de la línea actual
                        Table table = new Table(2);
                        int x = 0;
                        for (String element : elementos) {
                            Paragraph p = new Paragraph(element).addStyle(estiloTextop);
                            Paragraph p1 = new Paragraph(miArray[x]).addStyle(estiloTextop1);
                            Cell cell1 = new Cell().add(p1).setTextAlignment(TextAlignment.CENTER);
                            Cell cell2 = new Cell().add(p).setTextAlignment(TextAlignment.CENTER);
                            table.addCell(cell1);
                            table.addCell(cell2);
                            x++;
                        }


                        //crear la imagen del encabezado dentro del ciclo
                        ImageData logoData = ImageDataFactory.create("src/main/java/Resources/LOGOTIPO INSTITUCIONAL SIMPLIFICADO HORIZONTAL Y VERTICAL-03.png");
                        Image encabezado = new Image(logoData);
                        encabezado.setFixedPosition(320, 765);
                        encabezado.scale(0.5f, 0.5f);

                        // Crear la imagen de fondo dentro del ciclo
                        ImageData imageData = ImageDataFactory.create("src/main/java/Resources/LOGOTIPO INSTITUCIONAL SIMPLIFICADO HORIZONTAL Y VERTICAL-05.png");
                        Image image = new Image(imageData);
                        image.setFixedPosition(125, 350);
                        image.scale(1.3f, 1.3f);

                        // Agregar la tabla y la imagen al documento y cerrarlo
                        document.add(encabezado);
                        document.add(image);
                        document.add(table);

                        document.close();

                    } catch (Exception e) { // En este apartado se genera el control de las excepciones ---------------
                        count++;
                        System.out.println("Error en creación");
                        errores += line+"\n";
                        excepciones++;
                        correct = true;
                    }
                    break;
                } while (correct == false);

                contador++;
                avance += 0.05f;
                int auxi = (int)avance;
                barra.actualizarBarra(contador);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("End the end");
        }

        System.out.println(errores);

        // Se crea un .txt con las excepciones para poder controlarlas manualmente----------------------------
        try{
            FileWriter rExcepciones = new FileWriter(targetPath+"\\Excepciones.txt");
            rExcepciones.write(errores);
            rExcepciones.close();
            System.out.println("El archivo se ha creado correctamente.");
        }catch (IOException e){
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }

        // Se genera una ventana que avise el resultado de la descompresión ----------------------------------
        VentanaAviso reporte = new VentanaAviso(contador-excepciones , excepciones , targetPath );
    }
}

