import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainMenu extends JFrame{
    
    JLabel background;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 630;
    public MainMenu(String framename) {
        //sets the background of the menu and inits it and also adds jbuttons for each option
        super(framename);
        MenuPanel p = new MenuPanel();
        setLayout(null);
        background = new JLabel(new ImageIcon("back.png"), JLabel.CENTER);
        background.setBounds(0, 0, 1200, 630);
        add(p);
        add(background);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setLocationRelativeTo(null);
        
    }
   

    




}


