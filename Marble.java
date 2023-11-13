import java.io.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Marble {
	private int marbleX, marbleY;
	private String marbleColor;
	private BufferedImage redMarble, blueMarble, yellowMarble, greyMarble;

	public Marble() {
		try {
            // Load images
            redMarble = ImageIO.read(Marble.class.getResource("/images/red.png"));
			yellowMarble = ImageIO.read(Marble.class.getResource("/images/yellow.png"));
			blueMarble = ImageIO.read(Marble.class.getResource("/images/blue.png"));
			greyMarble = ImageIO.read(Marble.class.getResource("/images/grey.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		
		int r = (int)(Math.random() * 4) + 1;
		if (r==1) { marbleColor = "Blue"; }
		if (r==2) { marbleColor = "Red"; }
		if (r==3) { marbleColor = "Grey"; }
		if (r==4) { marbleColor = "Yellow"; }
		
		
	}
	
	
	public String getMarbleColor() {
		
		return marbleColor;
		
	}

	public void setMarbleX(int x) {

		marbleX = x;

	}
	public void setMarbleY(int x) {

		marbleY = x;

	}

	public int getMarbleX() {

		return marbleX;

	}
	public int getMarbleY() {

		return marbleY;

	}

	public BufferedImage getMarbleImage() {
		if (marbleColor.equals("Blue")) { return blueMarble; }
		else if (marbleColor.equals("Red")) { return redMarble; }
		else if (marbleColor.equals("Grey")) { return greyMarble; }
		else { return yellowMarble; }
	}

}
 