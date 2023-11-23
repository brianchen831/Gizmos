import java.awt.*;
import javax.swing.*;

public class Board extends JFrame{


//test
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;
	public Board (String framename) {


		super(framename);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		add (new BoardPanel());
		setVisible(true);


	}




}
