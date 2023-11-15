import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MenuPanel extends JPanel implements MouseListener{
    private JButton start, exit , settings;
    private BufferedImage background;
    public MenuPanel() { 
        start = new JButton("Start");
        settings = new JButton("Settings");
        exit = new JButton("Exit");
        add(start);
        add(settings);
        add(exit);
        addMouseListener(this);
        try {
            background = ImageIO.read(MenuPanel.class.getResource("/images/back.png"));
        } catch (IOException e) {    
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {

        g.drawImage(background, 0, 0, null);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
        
        System.out.println();
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
 }
