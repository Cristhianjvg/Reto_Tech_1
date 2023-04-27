package Controlador;

import Vista.VentanaPrincipal2;
import Vista.VentanaPrincipal3;

import javax.swing.*;

public class Archivo {

    public VentanaPrincipal3 porcentaje;
    public Archivo(String ingreso , String directorioPadre, String destino , String nomArchivo , VentanaPrincipal3 porcentaje) {
        this.porcentaje = porcentaje;

        // Imprimir en pantalla las rutas para control de las direcciones tanto de ingreso como exportación
        System.out.println("Ingreso:   "+ingreso);
        System.out.println("DirectorioPadre:   "+directorioPadre);
        System.out.println("Destino:   "+destino);
        System.out.println("Archivo:   "+nomArchivo);

        String file = destino+"\\"+nomArchivo+"\\"+nomArchivo+".txt"; // Dirección del archivo .txt el cual se leera
        System.out.println(file); // La ruta donde el archivo txt al descomprimir se encontrara
        String pasword = "C4SH_BL*@5$2Q4$";

        //Extracción del comprimido .RAR ------------------------------------------------------
        RarExtractor unrar = new RarExtractor();
        unrar.unRarFile(destino, ingreso, pasword);

        //Generación de los PDFs una vez ya extraido el archivo .txt---------------------------
        TextToPDF pdf = new TextToPDF();
        pdf.pdf(destino, file , porcentaje);

    }

}
