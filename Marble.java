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
            redMarble = ImageIO.read(new File("red.png"));
			yellowMarble = ImageIO.read(new File("yellow.png"));
			blueMarble = ImageIO.read(new File("blue.png"));
			greyMarble = ImageIO.read(new File("grey.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		double r = Math.floor(Math.random() * 3);
		if (r==0) { marbleColor = "Blue"; }
		if (r==1) { marbleColor = "Red"; }
		if (r==2) { marbleColor = "Grey"; }
		if (r==3) { marbleColor = "Yellow"; }
		
		
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
 