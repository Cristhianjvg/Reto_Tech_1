package Controlador;

import Vista.VentanaPrincipal2;

public class Archivo {

    public Archivo(String ingreso , String destino) {
        System.out.println("Lllef archivo");
        String file = "C:\\Users\\Usuario-Dell\\Desktop\\RETO-NOMINA\\Pago nomina web 30-04-2023\\pago nomina web 30-04-2023.txt";
        String pasword = "C4SH_BL*@5$2Q4$";
        RarExtractor unrar = new RarExtractor();
        unrar.unRarFile(destino, ingreso, pasword);
        //ReadTxt txt = new ReadTxt();
        //txt.READTXT(file);
        TextToPDF pdf = new TextToPDF();
        pdf.pdf(destino, ingreso);

        String fileName = file;

        TextReader reader = new TextReader();
        reader.readTxtFile(fileName);
    }

}
