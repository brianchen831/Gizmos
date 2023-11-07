import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainMenu extends JFrame{
    JButton start, settings, exit;
    JLabel background;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 630;
    public MainMenu(String framename) {
        //sets the background of the menu and inits it and also adds jbuttons for each option
        super(framename);
        Icon temp = new ImageIcon("Start.png");
        start = new JButton(temp);
        temp = new ImageIcon("Settings.png");
        settings = new JButton(temp);
        temp = new ImageIcon("Exit.png");
        exit = new JButton(temp);
        setLayout(null);
        background = new JLabel(new ImageIcon("back.png"), JLabel.CENTER);
        background.setBounds(0, 0, 1200, 630);
        add(background);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);
    }
   
    public void actionPerformed(ActionEvent e) {
        //we will change the prints to actual functions once other classes are made
        if (e.getSource() == start) {
            
            System.out.println("Start");

        } else if (e.getSource() == settings) {

            System.out.println("Open Settings");

        } else {

            System.out.println("Terminate the game");

        }

    }
    




}


