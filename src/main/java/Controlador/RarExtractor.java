package Controlador;


import java.io.*;

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

            //ELIMINAR EL ARCHIVO.RAR
            File fichero = new File(absolutePath);

            if (fichero.delete())
                System.out.println("El fichero ha sido borrado satisfactoriamente");
            else
                System.out.println("El fichero no puede ser borrado");

        } catch (Exception e) {
            System.out.println ("Se produjo una excepción durante la descompresión");
        }



    }

}
