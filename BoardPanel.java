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
	public BoardPanel() {
		players = new ArrayList<>();
		visibleMarbles = new ArrayList<>();
		for (int i = 0 ; i < 6 ; i++) {

			visibleMarbles.add(new Marble());

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
	public void pickMarble(int playerNum) {
		//Adds a marble to the top of the visibleMarbles and takes out the one that is picked to move it to the corresponding "playerNum" player
		visibleMarbles.add(0, new Marble());
		players.get(playerNum).addMarble(visibleMarbles.remove(visibleMarbles.size() - 1));
		repaint();
	}
	
	public void resetMarbleDisp() {
	
	}
	
	public void resetGizmos() {
		
	}
	
	
	public void refreshBoard() {
		
	}
	
	
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		g.drawImage(playergui, 0, 0, null);
		g.drawImage(marbleDispenser, 0, 0, null);
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
		if (x >= 765 && x<=930 && y >= 750 && y <= 813) { pickMarble(0);}
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