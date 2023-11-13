import java.awt.image.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements MouseListener{
	
	private BufferedImage marbleDispenser;
	private HashMap<Integer, ArrayList<Gizmo>> playableGizmos;
	private ArrayList<Marble> visibleMarbles;
	private ArrayList<Player> players;
	public Board() {
		players = new ArrayList<>();
		visibleMarbles = new ArrayList<>();
		for (int i = 0 ; i < 6 ; i++) {

			visibleMarbles.add(new Marble());

		}
		try {
            // Load images
			marbleDispenser = ImageIO.read(new File("Dispenser.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

	}
	public void pickMarble(int playerNum) {
		//Adds a marble to the top of the visibleMarbles and takes out the one that is picked to move it to the corresponding "playerNum" player
		visibleMarbles.add(visibleMarbles.size() - 1, new Marble());
		players.get(playerNum).addMarble(visibleMarbles.remove(0));
	}
	
	public void resetMarbleDisp() {

		

	}
	
	public void resetGizmos() {
		

	}
	
	
	public void refreshBoard() {
		

	}
	
	
	public void paint(Graphics g) {
		//ts dont really work the paint is so weird, and also we need new marble images these are way too big and ugly
		g.drawImage(marbleDispenser, 0, 0, null);
		int i = 0;
		for (Marble m : visibleMarbles) {

			m.setMarbleX(0);
			m.setMarbleY(m.getMarbleY() + i);
			i+=12.5;
			g.drawImage(m.getMarbleImage(), m.getMarbleX(), m.getMarbleY(), null);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
	}
}