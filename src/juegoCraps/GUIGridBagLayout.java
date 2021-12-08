package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame
{
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
  private JButton lanzar, ayuda, salir;
  private JPanel panelDados;
  private ImageIcon imagenDado;
  private JTextArea mensajeSalida, resultadosDados;
  private Escucha escucha;
  private ModelCraps modelCraps;

  public GUIGridBagLayout()
  {
    initGUI();
    //Default JFrame configuration
    this.setTitle("Juego Craps");
    this.setUndecorated(true);
    this.setBackground(new Color(255, 255, 255, 0));
    this.pack();
    this.setResizable(true);
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void initGUI()
  {
    //Set up JFrame Container's Layout
    this.getContentPane().setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    //Create Listener Object or Control Object
    escucha = new Escucha();
    modelCraps = new ModelCraps();

    //Set up JComponents
    headerProject = new Header("Mesa Juego Craps", Color.BLACK);
    constraints.gridx=0;
    constraints.gridy=0;
    constraints.gridwidth=2;
    constraints.fill=GridBagConstraints.HORIZONTAL;
    this.add(headerProject,constraints);

    ayuda = new JButton("?");
    ayuda.addActionListener(escucha);
    constraints.gridx=0;
    constraints.gridy=1;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.LINE_START;
    this.add(ayuda, constraints);

    salir = new JButton("Salir");
    salir.addActionListener(escucha);
    constraints.gridx=1;
    constraints.gridy=1;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.LINE_END;
    this.add(salir, constraints);

    imagenDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
    dado1 = new JLabel(imagenDado);
    dado2 = new JLabel(imagenDado);

    panelDados = new JPanel();
    panelDados.setPreferredSize(new Dimension(300,180));
    panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
    panelDados.add(dado1);
    panelDados.add(dado2);
    constraints.gridx=0;
    constraints.gridy=2;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.BOTH;
    constraints.anchor=GridBagConstraints.CENTER;
    add(panelDados, constraints);

    resultadosDados = new JTextArea(4, 31);
    resultadosDados.setBorder(BorderFactory.createTitledBorder("Resultados"));
    resultadosDados.setText("Debes lanzar los dados");
    resultadosDados.setEditable(false);
    constraints.gridx=1;
    constraints.gridy=2;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.BOTH;
    constraints.anchor=GridBagConstraints.CENTER;
    add(resultadosDados, constraints);

    lanzar = new JButton("Lanzar");
    lanzar.addActionListener(escucha);
    constraints.gridx=0;
    constraints.gridy=3;
    constraints.gridwidth=2;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.CENTER;
    add(lanzar, constraints);

    mensajeSalida = new JTextArea(4, 31);
    mensajeSalida.setText("Usa el boton (?) para ver las reglas del juego");
    mensajeSalida.setBorder(BorderFactory.createTitledBorder("Mensajes"));
    mensajeSalida.setEditable(false);
    constraints.gridx=0;
    constraints.gridy=4;
    constraints.gridwidth=2;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.CENTER;
    add(mensajeSalida, constraints);
  }

  public static void main(String[] args){
    EventQueue.invokeLater(() -> {
      GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
    });
  }

  private class Escucha implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent objectEvent)
    {
      if(objectEvent.getSource()==lanzar)
      {
        modelCraps.calcularTiro();
        int[] caras = modelCraps.getCaras();
        imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
        dado1.setIcon(imagenDado);
        imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
        dado2.setIcon(imagenDado);
        modelCraps.determinarJuego();

        resultadosDados.setText(modelCraps.getEstadoToString()[0]);
        mensajeSalida.setText(modelCraps.getEstadoToString()[1]);
      }
      else
      {
        if(objectEvent.getSource()==ayuda)
        {
          JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
        }
        else
        {
          System.exit(0);
        }
      }
    }
  }
}