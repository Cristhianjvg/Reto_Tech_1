package Vista;

import Controlador.Archivo;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaPrincipal2 {

    public static String rutaIngreso = new String();

    Archivo archivo;
    public static void main (String [] args) { // Ventana de carga del archivo .RAR a procesar
        //Ventana principal del programa y sus caracteristicas
        JFrame ventana = new JFrame("Cargar archivo RAR");
        ventana.setSize(500, 260);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);

        //Paneles que se usaran para la distribución de los elementos en el programa
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(3, 1));
        panel2.add(new Panel());
        JPanel panel3 = new JPanel();
        ventana.add(panel);

        //Detalles de los componentes a mostrar
        JLabel etiqueta = new JLabel("Selecciona un archivo .rar");
        panel3.add(etiqueta);

        JButton boton = new JButton("Cargar archivo");
        panel3.add(boton);
        panel2.add(panel3);

        panel1.add(new JLabel(new ImageIcon("src/main/java/Resources/LOGOTIPO INSTITUCIONAL SIMPLIFICADO HORIZONTAL Y VERTICAL-05.png")));
        panel.add(panel1);
        panel.add(panel2);

        //Activador que ejecutara al función de abrir un seleccionador de carpetas para seleccionar el archivo .RAR
        boton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleccionar archivo .rar");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo RAR", "rar"));
            int userSelection = fileChooser.showOpenDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File archivoRAR = fileChooser.getSelectedFile();
                String rutaArchivoRAR = archivoRAR.getAbsolutePath();
                rutaIngreso = rutaArchivoRAR;
                etiqueta.setText("Archivo seleccionado: " + rutaArchivoRAR);
                ventana.setVisible(false);
                VentanaPrincipal3 destino = new VentanaPrincipal3(rutaIngreso); // Se abrira la ventana posterior para continuar el proces
                System.out.println("LLega hasta aqui");
            }

        });

        ventana.setVisible(true);
    }
}

