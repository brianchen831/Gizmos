import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class MenuPanel extends JPanel {
    private JButton start, exit, settings;
    public MenuPanel() {
        //Ik this part is convluluted as hell but theres no better  way to do it it works thats all that matters
        super();
        setOpaque(false);
        setSize(400, 400);
        start = new JButton(new ImageIcon("start.png"));
        settings = new JButton(new ImageIcon("settings.png"));
        exit = new JButton(new ImageIcon("exit.png"));
        start.setBounds(600, 210, 260, 90);
        settings.setBounds(600, 420, 260, 90);
        exit.setBounds(600, 630, 260, 90);
        start.setFocusPainted(false);
        settings.setFocusPainted(false);  
        exit.setFocusPainted(false);
        start.setBackground(new Color(0f,0f,0f,.5f ));
        settings.setBackground(new Color(0f,0f,0f,.5f ));
        exit.setBackground(new Color(0f,0f,0f,.5f ));
        add(exit, JLayeredPane.DEFAULT_LAYER);
        add(settings, JLayeredPane.DEFAULT_LAYER);
        add(start, JLayeredPane.DEFAULT_LAYER);
        setVisible(true);
        
    }
    
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start");
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {   
                System.out.println("Exit");
            }
        }); 
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Settings");
            }
        });
       
    }
}