import java.awt.image.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel{
	
	private BufferedImage marbleDispenser;
	private HashMap<Integer, ArrayList<Gizmo>> playableGizmos;
	private ArrayList<Marble> visibleMarbles;
	private ArrayList<Player> players;
	public Board() {
		players = new ArrayList<>();
		visibleMarbles = new ArrayList<>();
		for (int i = 0 ; i < 3 ; i++) {

			visibleMarbles.add(new Marble());

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
		int i = 0;
		for (Marble m : visibleMarbles) {

			m.setMarbleX(540);
			m.setMarbleY(m.getMarbleY() + i);
			i+=50;
			g.drawImage(m.getMarbleImage(), m.getMarbleX(), m.getMarbleY(), null);
		}
	}
}