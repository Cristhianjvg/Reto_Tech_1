package Controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {

    public void readTxtFile(String fileName) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            // Leer cada línea del archivo
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\t"); // Separar en columnas usando un tabulador como delimitador

                // Mostrar los valores de cada columna
                for (String column : columns) {
                    //System.out.print(column + "\t");
                }
                //System.out.println(); // Nueva línea después de cada fila
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
