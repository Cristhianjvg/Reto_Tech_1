package Vista;

import Controlador.Archivo;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class VentanaPrincipal3 extends JFrame { // Ventana para seleccionar la ruta para guardar los archivos PDF y mandar a procesar

    public String rutaPDFs = new String();
    public String rutaIngreso;
    Archivo archivo;

    public VentanaPrincipal3(String rutaIngreso) throws HeadlessException {
        this.rutaIngreso = rutaIngreso;
        System.out.println("Llego a 3");

        JFrame ventana = new JFrame("Seleccionar carpeta");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ventana.setSize(450, 470);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);

        //Detalles de los componentes a mostrar
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JLabel rutaGuardado = new JLabel("Seleccione la carpeta de guardado");
        JButton boton = new JButton("Seleccionar carpeta");
        JButton boton2 = new JButton("Convertir");

        //Paneles que se usaran para la distribución de los elementos en el programa
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel(new ImageIcon("src/main/java/Resources/LOGOTIPO INSTITUCIONAL SIMPLIFICADO HORIZONTAL Y VERTICAL-05.png")));

        JPanel panel2 = new JPanel(new GridLayout(2, 1));
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        rutaGuardado.setBounds(20,20,200,50);

        panel3.add(rutaGuardado);
        panel3.add(boton);
        panel4.add(boton2);

        panel2.add(panel3);
        panel2.add(panel4);

        //Activador del boton que ejecutara la función de mostrar un gestor de carpetas para seleccionar la carpeta destino donde se guardaran los PDF
        boton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleccionar carpeta");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int userSelection = fileChooser.showOpenDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = fileChooser.getSelectedFile();
                // Aquí puedes hacer lo que quieras con la carpeta seleccionada
                System.out.println("Carpeta seleccionada: " + selectedFolder.getAbsolutePath());
                rutaGuardado.setText(selectedFolder.getAbsolutePath());
                rutaPDFs = selectedFolder.getAbsolutePath();
            }
        });

        //Activador del boton 2 que mandara a iniciar el proceso de conversión de los PDF
        boton2.addActionListener(e -> {
            archivo = new Archivo(rutaIngreso , rutaPDFs);
        });

        panel.add(panel1);
        panel.add(panel2);
        ventana.add(panel);
        ventana.setVisible(true);
    }
}
