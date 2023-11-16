import java.awt.image.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class BoardPanel extends JPanel implements MouseListener{
	
	private BufferedImage marbleDispenser;
	private HashMap<Integer, ArrayList<Gizmo>> playableGizmos;
	private ArrayList<Marble> visibleMarbles;
	private ArrayList<Player> players;
	private boolean firstDraw;
	private BufferedImage background, playergui;
	private int yellowCount, redCount, greyCount, blueCount;
	
	public BoardPanel() {
		players = new ArrayList<>();
		visibleMarbles = new ArrayList<>();
		int temp = 0;
		for (int i = 0 ; i < 6 ; i++) {
			Marble marble = new Marble();
			marble.incBoundY(temp);
			visibleMarbles.add(marble);
			temp+=25;
		}
		try {
            // Load images
			marbleDispenser = ImageIO.read(Board.class.getResource("/images/Dispenser.png"));
			background = ImageIO.read(Board.class.getResource("/images/gameback.png"));
			playergui = ImageIO.read(Board.class.getResource("/images/playergui.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		players.add(new Player("Charlie"));
		addMouseListener(this);
		firstDraw = true;

	}
	public void pickMarble(int playerNum, int index) {
		Marble newMarble = new Marble();
		
		if (visibleMarbles.get(index).toString().equals("Red")) { redCount++; }
		else if (visibleMarbles.get(index).toString().equals("Yellow")) { yellowCount++; }
		else if (visibleMarbles.get(index).toString().equals("Grey")) { greyCount++; }
		else { blueCount++; }
    	players.get(playerNum).addMarble(visibleMarbles.remove(index));
		visibleMarbles.add(0, newMarble); 
    	repaint();
	}
	
	public void resetMarbleDisp() {
	
	}
	
	public void resetGizmos() {
		
	}
	
	
	public void refreshBoard() {
		
	}
	
	
	public void paint(Graphics g) {
		//g.drawString(": " + yellowCount)
		//g.drawString(": " + blueCount);
		//g.drawString(": " + greyCount);
		
		g.drawImage(background, 0, 0, null);
		g.drawImage(playergui, 0, 0, null);
		g.drawImage(marbleDispenser, 0, 0, null);
		g.setFont(new Font("Proxima Nova", Font.PLAIN, 25));
		g.setColor(Color.RED);
		g.drawString(":" + redCount, 87, 828);
		g.setColor(Color.GRAY);
		g.drawString(":" + greyCount, 87, 872);
		g.setColor(Color.BLUE);
		g.drawString(":" + blueCount, 142, 828);
		g.setColor(Color.YELLOW);
		g.drawString(":" + yellowCount, 142, 872);
		int i = 0;
		for (Marble m : visibleMarbles) {
			m.setMarbleX(0);
			g.drawImage(m.getMarbleImage(), m.getMarbleX(), m.getMarbleY() + i, null);
			i+=25;
		}


	}

		
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println(x + ", " + y);
		
		
		for (int i = 0 ; i < visibleMarbles.size() - 1 ; i++) {

			if (visibleMarbles.get(i).getMarbleBounds().contains(e.getPoint())) {
				
				pickMarble(0, i);
				repaint();
				break;
			}

		}	
		
		repaint();
		//System.out.println(players.get(0).getHeldMarbles());
		
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