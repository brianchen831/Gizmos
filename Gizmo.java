
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.util.*;

import javax.imageio.ImageIO;

public class Gizmo {
	private BufferedImage gizmoImage;
	private int tier;
	private String gizmoColor;
	public Gizmo(BufferedImage gizmoImage, int t) {

		tier = t;
		
		this.gizmoImage = gizmoImage;
		//System.out.println(new Color(gizmoImage.getRGB(30, 255)));
		Color temp = new Color(gizmoImage.getRGB(4, 123));
        if (temp==new Color(55, 55, 55)) { gizmoColor="Grey"; }
		else if (temp==new Color(170, 28, 35)) { gizmoColor="Red"; }
		else if (temp==new Color(222, 175, 31)) { gizmoColor="Yellow"; }
		else if (temp==new Color(56, 95, 163)) { gizmoColor="Blue"; }
		


	}

	public BufferedImage getImage() {

		return gizmoImage;

	}
	

}
