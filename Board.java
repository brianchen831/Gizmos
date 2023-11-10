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
	public Board() {
		visibleMarbles = new ArrayList<>();
		for (int i = 0 ; i < 3 ; i++) {

			visibleMarbles.add(new Marble());

		}

	}
	
	public void resetMarbleDisp() {



	}
	
	public void resetGizmos() {
		

	}
	
	
	public void refreshBoard() {
		

	}
	
	
	public void paint(Graphics g) {
		//ts dont really work the paint is so weird, and also we need new marble images these are way too big and ugly

		for (Marble m : visibleMarbles) {

			m.setMarbleX(540);
			m.setMarbleY(m.getMarbleY() + 100);
			g.drawImage(m.getMarbleImage(), m.getMarbleX(), m.getMarbleY(), null);
		}
	}
}