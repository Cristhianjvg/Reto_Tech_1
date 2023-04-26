package Controlador;

import java.io.File;
import java.util.Scanner;

public class ReadTxt {

    public void READTXT(String rutatxt) {
        int contador=0;
        try {
            // Ruta del archivo a leer
            File file = new File(rutatxt);

            // Scanner para leer el archivo
            Scanner scanner = new Scanner(file);

            // Leer cada línea del archivo
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //System.out.println(line);
            }
            System.out.println("se leyo el txt correctamente");

            scanner.close();
        } catch (Exception e) {
            System.out.println(contador+" -- Ocurrió un error al leer el archivo: " + e.getMessage());
            contador++;
        }
    }

}
