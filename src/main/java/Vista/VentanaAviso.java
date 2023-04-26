package Vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.Desktop;
import java.io.IOException;

public class VentanaAviso extends JFrame {

    public String mensaje = new String();
    public String rutaGuardado = new String();

    public VentanaAviso(int correctos , int problema){ // Ventana para avisar al usuario el resultado de la conversión
        rutaGuardado = "C:\\Users\\JULY\\Documents\\Custom Production Presets 7.0";
        //Detalles de la ventana
        JFrame ventana = new JFrame(mensaje+" ...");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ventana.setSize(400, 320);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);

        //Paneles que se usaran para la distribución de los elementos en el programa
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JLabel resultado = new JLabel();
        JLabel destino = new JLabel();
        JTextArea informe = new JTextArea("\n- - RESULTADOS DE LA CONVERSIÓN - - - - - - -" +
                "\n\n    Informes generados correctamente: "+correctos
                +"\n    Informes con error:"+problema
                +"\n\n    Los informes se han almacenado en:\n"+rutaGuardado);


        resultado.setText("La operación ha sido ejecutada con exito");
        destino.setText("Los PDFs generados se almacenaron en:\n"+ rutaGuardado);
        System.out.println("fgrtg--------"+rutaGuardado);

        JButton abrir = new JButton("Mostrar en carpeta");
        JPanel panel1 = new JPanel();
        panel1.add(abrir);

        abrir.addActionListener(e -> { //Creamos un action Listener para mostrar los PDF generados en el directorio del SO
                    // Se abre la carpeta directorio del SO con los PDFs ya generados
                    try {
                        Desktop.getDesktop().open(new File(rutaGuardado));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });


        panel.add(informe);
        panel.add(panel1);

        ventana.add(panel);
        ventana.setVisible(true);
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public static void main (String [] args){
        VentanaAviso ventana = new VentanaAviso(3,5);
    }
}
