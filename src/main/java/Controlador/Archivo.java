package Controlador;

import Vista.VentanaPrincipal2;

public class Archivo {

    public Archivo(String ingreso , String directorioPadre, String destino , String nomArchivo) {

        System.out.println("Ingreso:   "+ingreso);
        System.out.println("DirectorioPadre:   "+directorioPadre);
        System.out.println("Destino:   "+destino);
        System.out.println("Archivo:   "+nomArchivo);

        //String file = "C:\\Users\\Usuario-Dell\\Desktop\\RETO-NOMINA\\Pago nomina web 30-04-2023\\pago nomina web 30-04-2023.txt";
        String file = destino+"\\"+nomArchivo+/*"\\"+nomArchivo+*/"\\"+nomArchivo+".txt";
        System.out.println(file); // La ruta donde el archivo txt al descomprimir se encontrara
        String pasword = "C4SH_BL*@5$2Q4$";

        RarExtractor unrar = new RarExtractor();
        unrar.unRarFile(destino, ingreso, pasword);
        //ReadTxt txt = new ReadTxt();
        //txt.READTXT(file);
        TextToPDF pdf = new TextToPDF();
        pdf.pdf(destino, file);

        //String fileName = file;

        //TextReader reader = new TextReader();
        //reader.readTxtFile(fileName);
    }

}
