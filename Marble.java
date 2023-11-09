import java.io.*;
import java.awt.image.*;
import java.util.*;

public class Marble {

	private String marbleColor;
	private BufferedImage redMarble, blueMarble, yellowMarble, greyMarble;

	public Marble() {
		
		//set all the images
		
	}
	
	public void setRandColor() {
		double r = Math.floor(Math.random() * 3);
		if (r==0) { marbleColor = "Blue"; }
		if (r==1) { marbleColor = "Red"; }
		if (r==2) { marbleColor = "Grey"; }
		if (r==3) { marbleColor = "Yellow"; }
	}
	
}
 