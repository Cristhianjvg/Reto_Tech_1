package Controlador;

public class Archivo {

    public static void main(String[] args) {
        String targetPath = "C:\\Users\\Usuario-Dell\\Desktop\\RETO-NOMINA";
        String rarFilePath = "C:\\Users\\Usuario-Dell\\Desktop\\Pago nomina web 30-04-2023.rar";
        String file = "C:\\Users\\Usuario-Dell\\Desktop\\RETO-NOMINA\\Pago nomina web 30-04-2023\\pago nomina web 30-04-2023.txt";
        String pasword = "C4SH_BL*@5$2Q4$";
        RarExtractor unrar = new RarExtractor();
        unrar.unRarFile(targetPath, rarFilePath, pasword);
        //ReadTxt txt = new ReadTxt();
        //txt.READTXT(file);

        String fileName = file;

        TextReader reader = new TextReader();
        reader.readTxtFile(fileName);
    }


}
