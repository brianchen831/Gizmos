import java.io.*;
import java.awt.Rectangle;
import java.awt.image.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Marble {
	private int marbleX, marbleY;
	private String marbleColor;
	private BufferedImage marbleImage;
	

	public Marble(String color) {
		
		try {
            // Load images
			marbleColor = color;
			if(color == "Red")
            	marbleImage = ImageIO.read(Marble.class.getResource("/images/red.png"));
			else if(color == "Yellow")
				marbleImage = ImageIO.read(Marble.class.getResource("/images/yellow.png"));
			else if(color == "Blue")
				marbleImage = ImageIO.read(Marble.class.getResource("/images/blue.png"));
			else
				marbleImage = ImageIO.read(Marble.class.getResource("/images/grey.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }	
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
		 return marbleImage; 
	}

	public String toString() {

		return marbleColor;

	}

}
 