package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * This class is used for ...
 * @autor Carlos Felipe Montoya carlos.felipe.montoya@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class GUI extends JFrame {

    private Header headerProject;
    private JPanel squareColor;
    private Timer timer;
    private Escucha escucha;
    private JButton initTimer;
    private int counter;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Timer Swinger App");
        this.setSize(400,400);
        //this.pack();
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
        counter = 0;
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        escucha = new Escucha();
        //Set up JComponents
        headerProject = new Header("Swing Timer", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        squareColor = new JPanel();
        squareColor.setBackground(Color.CYAN);
        squareColor.setPreferredSize(new Dimension(100, 100));

        this.add(squareColor, BorderLayout.CENTER);

        initTimer = new JButton("Timer");
        initTimer.setEnabled(false);
        this.add(initTimer, BorderLayout.SOUTH);

        timer = new Timer(1000, escucha);
        timer.start();
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
    private class Escucha implements ActionListener {
        private Random random;

        public Escucha(){
            random = new Random();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("El timer está corriendo? " + String.valueOf(timer.isRunning()));
            if(e.getSource()==timer){
                if(counter <= 7) {
                    squareColor.setBackground(new Color(random.nextInt(256),
                            random.nextInt(256),
                            random.nextInt(256)));
                    counter++;
                }else{
                    timer.stop();
                    initTimer.setEnabled(true);
                    initTimer.addActionListener(escucha);
                }
            }else {
                counter = 0;
                timer.start();
                initTimer.setEnabled(false);
                initTimer.removeActionListener(escucha);
            }

        }
    }
}
