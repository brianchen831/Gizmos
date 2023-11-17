
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.util.*;

import javax.imageio.ImageIO;

public class Gizmo {
	private BufferedImage gizmoImage;
	private int tier;

	public Gizmo(BufferedImage gizmoImage) {
		
		
		this.gizmoImage = gizmoImage;
        
		
	}

	public BufferedImage getImage() {

		return gizmoImage;

	}
	

}
