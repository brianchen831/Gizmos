import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
//what is this

public class GUI {
    private BufferedImage playergui;
    public GUI() {

        try {
			playergui = ImageIO.read(BoardFrame.class.getResource("/images/playergui.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
    
}
