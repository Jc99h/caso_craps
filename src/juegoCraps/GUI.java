package juegoCraps;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * This class is used as view craps class
 * @autor Camilo Ordoñez 1827625-2711 juan.ordonez.hurtado@correounivalle.edu.co
 * @version v.1.0.0 date:03/12/2021
 */
public class GUI extends JFrame {

    private static final String MENSAJE_INICIO= "Bienvenido a Craps \n"+
                                                "Oprime el boton lanzar para iniciar el juego \n"+
                                                "Si tu tiro de salida es 7 u 11 ganas con Natural \n"+
                                                "Si tu tiro de salida es 2, 3 u 12 pierdes con Craps \n"+
                                                "Si sacas cualquier otro valor estableceras el Punto \n"+
                                                "Estado en punto podras seguir lanzando los dados \n"+
                                                "pero ahora ganaras si sacas nuevamente el valor del Punto \n"+
                                                "sin que previamente hayas sacado 7";

    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imagenDado;
    private JTextArea resultados;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Juego Craps");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH);

        imagenDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1 = new JLabel(imagenDado);
        dado2 = new JLabel(imagenDado);

        lanzar = new JButton("Lanzar");

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);

        this.add(panelDados, BorderLayout.CENTER);

        resultados = new JTextArea(7, 31);
        resultados.setText(MENSAJE_INICIO);
        resultados.setBorder(BorderFactory.createTitledBorder("Que debes hacer"));
        JScrollPane scroll = new JScrollPane(resultados);
        this.add(scroll, BorderLayout.EAST);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha {

    }
}
