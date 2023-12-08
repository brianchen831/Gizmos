import java.awt.*;
import javax.swing.*;

public class BoardFrame extends JFrame{


//test
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;
	public BoardFrame (String framename) {

		
		super(framename);
		add (new BoardPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
	
		setVisible(true);


	}




}
