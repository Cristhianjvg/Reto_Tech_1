package Controlador;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class BarraDeProgreso extends JFrame {

    private JProgressBar progressBar;

    public BarraDeProgreso() {

        setTitle("Barra de progreso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        add(progressBar);

        setVisible(true);
    }

    public void actualizarBarra(int progreso) {
        System.out.println("Se actualiza"+ progreso);
        progressBar.setValue(progreso);
        progressBar.setString(String.format("%d%%", progreso));
    }

}
