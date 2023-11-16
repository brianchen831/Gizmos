
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.util.*;

import javax.imageio.ImageIO;

public class Gizmo {
	private BufferedImage gizmoImage;
	private int tier;

	public Gizmo() {
		
		try {
			gizmoImage = ImageIO.read(Board.class.getResource("/images/playergui.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		
	}
	

}
