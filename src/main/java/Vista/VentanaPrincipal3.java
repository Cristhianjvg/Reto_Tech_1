package Vista;

import Controlador.Archivo;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.JProgressBar;

public class VentanaPrincipal3 extends JFrame { // Ventana para seleccionar la ruta para guardar los archivos PDF y mandar a procesar

    public String rutaPDFs = new String();
    public String rutaIngreso , nomArchivo;
    Archivo archivo;
    private JProgressBar barraDeProgreso;
    public JLabel porcentajeLabel;
    private float avance = 0;

    public JFrame ventana;

    public VentanaPrincipal3(String rutaIngreso, String guardadoDefault , String nomArchivo) throws HeadlessException {
        this.rutaIngreso = rutaIngreso;
        this.rutaPDFs = guardadoDefault;
        this.nomArchivo = nomArchivo;

        //Ventana principal del programa y sus caracteristicas------------------------------------------------
        ventana = new JFrame("SECD v1.1 Selección de Destino");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ventana.setSize(450, 470);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);

        //Detalles de los componentes a mostrar-----------------------------------------------------------------
        JPanel panel = new JPanel(new GridLayout(2,1 ));
        JLabel ruta = new JLabel("Carpeta de Destino\n");
        JLabel rutaGuardado = new JLabel(guardadoDefault);
        JButton boton = new JButton("Seleccionar carpeta");
        JButton boton2 = new JButton("Convertir");
        //barraDeProgreso = new JProgressBar();
        //barraDeProgreso.setStringPainted(true);

        //Paneles que se usaran para la distribución de los elementos en el programa-----------------------------
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel(new ImageIcon("src/main/java/Resources/LOGOTIPO INSTITUCIONAL SIMPLIFICADO HORIZONTAL Y VERTICAL-05.png")));

        JPanel panel2 = new JPanel(new GridLayout(2, 1));
        JPanel panel3 = new JPanel(new GridLayout(2, 1));
        JPanel panel4 = new JPanel(new GridLayout(2, 1));
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();

        panel5.add(ruta);
        panel6.add(rutaGuardado);
        panel3.add(panel5);
        panel3.add(panel6);
        porcentajeLabel = new JLabel("0%");
        panel8.add(porcentajeLabel);
        panel8.setVisible(false);
        /*barraDeProgreso.setMinimum(0);
        barraDeProgreso.setMaximum(100);
        barraDeProgreso.setStringPainted(true);
        panel8.add(barraDeProgreso);*/
        panel7.add(boton);
        panel7.add(boton2);
        panel4.add(panel7);
        panel4.add(panel8);


        panel2.add(panel3);
        panel2.add(panel4);


        //Activador del boton que ejecutara la función de mostrar un gestor de carpetas para seleccionar la carpeta destino donde se guardaran los PDF----
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

        //Activador del boton 2 que mandara a iniciar el proceso de conversión de los PDF-----------------------------
        boton2.addActionListener(e -> {

            archivo = new Archivo(rutaIngreso , guardadoDefault , rutaPDFs , nomArchivo , this);
            ventana.setVisible(false);

        });

        panel.add(panel1);
        panel.add(panel2);
        ventana.add(panel);
        ventana.setVisible(true);
    }

    public void startProgress(float progreso) {
        avance += progreso;
        System.out.println(avance);
        porcentajeLabel.setText(avance + " %");
        ventana.setVisible(true);
    }
}
