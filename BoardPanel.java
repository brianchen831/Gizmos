import java.awt.image.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.*;
import static java.lang.System.*;	
import javax.swing.*;


public class BoardPanel extends JPanel implements MouseListener{
	
	private BufferedImage marbleDispenser;
	private ArrayList<Gizmo> t1Gizmos, t2Gizmos, t3Gizmos;
	private ArrayList<Marble> visibleMarbles;
	private ArrayList<Player> players;
	private boolean firstDraw;
	private BufferedImage background, playergui, gizmoSheet1, gizmoSheet2;
	private int yellowCount, redCount, greyCount, blueCount;
	private Rectangle marbleBound1, marbleBound2, marbleBound3, marbleBound4, marbleBound5, marbleBound6; //temporary hardcode so we can start the more difficult part
	private Rectangle gizmoBound1_1, gizmoBound1_2, gizmoBound1_3, gizmoBound1_4;
	private Rectangle gizmoBound2_1, gizmoBound2_2, gizmoBound2_3;
	private Rectangle gizmoBound3_1, gizmoBound3_2;
	private Rectangle fileBound, pickBound, buildBound, researchBound, archiveBound;
	private Rectangle upgBound, convertBound;


	public BoardPanel() {
		players = new ArrayList<>();
		visibleMarbles = new ArrayList<>();
		t1Gizmos = new ArrayList<>();
		t2Gizmos = new ArrayList<>();
		t3Gizmos = new ArrayList<>();
		int temp = 0;
		for (int i = 0 ; i < 6 ; i++) {
			Marble marble = new Marble();			
			visibleMarbles.add(marble);
			temp+=25;
		}
		marbleBound1 = new Rectangle(941, 249, 21, 21);
		marbleBound2 = new Rectangle(941, 273, 21, 21);
		marbleBound3 = new Rectangle(941, 298, 21, 21);
		marbleBound4 = new Rectangle(941, 324, 21, 21);
		marbleBound5 = new Rectangle(941, 348, 21, 21);
		marbleBound6 = new Rectangle(941, 373, 21, 21);

		gizmoBound1_1 = new Rectangle(200, 390, 140, 130);
		gizmoBound1_2 = new Rectangle(370, 390, 140, 130);
		gizmoBound1_3 = new Rectangle(540, 390, 140, 130);
		gizmoBound1_4 = new Rectangle(710, 390, 140, 130);

		gizmoBound2_1 = new Rectangle(200, 235, 140, 130);
		gizmoBound2_2 = new Rectangle(370, 235, 140, 130);
		gizmoBound2_3 = new Rectangle(540, 235, 140, 130);

		gizmoBound3_1 = new Rectangle(200, 80, 140, 130);
		gizmoBound3_2 = new Rectangle(370, 80, 140, 130);

		try {
            // Load images
			marbleDispenser = ImageIO.read(BoardFrame.class.getResource("/images/Dispenser.png"));
			background = ImageIO.read(BoardFrame.class.getResource("/images/gameback.png"));
			playergui = ImageIO.read(BoardFrame.class.getResource("/images/playergui.png"));
			gizmoSheet1 = ImageIO.read(BoardFrame.class.getResource("/images/sheet1.jpg"));
			gizmoSheet2 = ImageIO.read(BoardFrame.class.getResource("/images/sheet1.jpg"));	
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


		for (int i = 0 ; i <= 3 ; i++) {
			//subimage makes it so we dont need 900000 diff images, basically a bitmap, each gizmo is 490x490, if u want to get the image of the 2nd gizmo on the top for example it would be getSubimage(x: 490, y: 0, width: 490, height: 490)
			for (int j = 0 ; j <= 7 /*why only 3 */ ; j++) {
				t1Gizmos.add(new Gizmo(gizmoSheet1.getSubimage(j*490, i*490, 490, 490)));
			}
			
		}
		for(int j = 0; j <= 3; j++){
			t1Gizmos.add(new Gizmo(gizmoSheet1.getSubimage(j*490, 4*490, 490, 490)));
		}

		// for(int j = 4; j <= 7; j++){
		// 	t2Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(j*490, 4*490, 490, 490)));
		// }
		for (int i = 5 ; i <= 7; i++) {
			//subimage makes it so we dont need 900000 diff images, basically a bitmap, each gizmo is 490x490, if u want to get the image of the 2nd gizmo on the top for example it would be getSubimage(x: 490, y: 0, width: 490, height: 490)
			for (int j = 0 ; j <= 3 ; j++) {
				t2Gizmos.add(new Gizmo(gizmoSheet1.getSubimage(j*490, i*490, 490, 490)));
			}
			//we need to add from sheet2 as well
		}
		//for(sheet 2 stuff)

		//doesnt work rn
		for (int i = 2 ; i <= 5; i++) {
			//subimage makes it so we dont need 900000 diff images, basically a bitmap, each gizmo is 490x490, if u want to get the image of the 2nd gizmo on the top for example it would be getSubimage(x: 490, y: 0, width: 490, height: 490)
			for (int j = 0 ; j <= 6 ; j++) {
				t3Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(j*490, i*490, 490, 490))); //fix this later
			}
		}
		Collections.shuffle(t1Gizmos);
		Collections.shuffle(t2Gizmos);
		Collections.shuffle(t3Gizmos);
		players.add(new Player("Mark"));
		addMouseListener(this);
		firstDraw = true;
	}
	public void pickMarble(int playerNum, int index) {
		Marble newMarble = new Marble();
		System.out.println("Picking a marble");
		if (visibleMarbles.get(index).toString().equals("Red")) { redCount++; }
		else if (visibleMarbles.get(index).toString().equals("Yellow")) { yellowCount++; }
		else if (visibleMarbles.get(index).toString().equals("Grey")) { greyCount++; }
		else { blueCount++; }
    	players.get(playerNum).addMarble(visibleMarbles.remove(index));
		visibleMarbles.add(0, newMarble); 
    	repaint();
	}
	
	public void resetMarbleDisp() {
	
	}
	
	public void resetGizmos() {
		
	}
	
	
	public void refreshBoard() {
		
	}
	
	
	public void paint(Graphics g) {
		//g.drawString(": " + yellowCount)
		//g.drawString(": " + blueCount);
		//g.drawString(": " + greyCount);
		
		g.drawImage(background, 0, 0, null);
		g.drawImage(playergui, 0, -180, null);
		g.drawImage(marbleDispenser, 0, 0, null);
		g.setFont(new Font("Proxima Nova", Font.PLAIN, 25));
		g.setColor(Color.RED);
		g.drawString(":" + redCount, 87, 828);
		g.setColor(Color.GRAY);
		g.drawString(":" + greyCount, 87, 872);
		g.setColor(Color.BLUE);
		g.drawString(":" + blueCount, 142, 828);
		g.setColor(Color.YELLOW);
		g.drawString(":" + yellowCount, 142, 872);
		int temp = 0;
		for (Gizmo x : t1Gizmos) {
			g.drawImage(x.getImage(), 200 + temp, 390, 143, 130, null);
			temp+=170;
			if (temp > 510) { break; }
		}
		temp = 0;
		for (Gizmo x : t2Gizmos) {
			g.drawImage(x.getImage(), 200 + temp, 235, 143, 130, null);
			temp+=170;
			if (temp > 340) { break; }  
		}
		temp = 0;
		for (Gizmo x : t3Gizmos) {
			g.drawImage(x.getImage(), 200 + temp, 80, 143, 130, null);
			temp+=170;
			if (temp > 170) { break; }  
		}
		int i = 0;
		for (Marble m : visibleMarbles) {
			m.setMarbleX(0);
			g.drawImage(m.getMarbleImage(), m.getMarbleX(), m.getMarbleY() + i, null);
			i+=25;
		}
	}

		
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println(x + ", " + y);
		if(marbleBound1.contains(e.getPoint())) {	  
			pickMarble(0, 0);
		} 
		else if(marbleBound2.contains(e.getPoint())){
			pickMarble(0, 1);
		}
		else if(marbleBound3.contains(e.getPoint())){
			pickMarble(0, 2);
		}
		else if(marbleBound4.contains(e.getPoint())){
			pickMarble(0, 3);
		}
		else if(marbleBound5.contains(e.getPoint())){
			pickMarble(0, 4);
		}
		else if(marbleBound6.contains(e.getPoint())){
			pickMarble(0, 5);
		}
		else if(gizmoBound1_1.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Level 1 first card");		
		else if(gizmoBound1_2.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Level 1 second card");
		else if(gizmoBound1_3.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Level 1 third card");
		else if(gizmoBound1_4.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Level 1 4th card");
		else if(gizmoBound2_1.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Level 2 first card");
		else if(gizmoBound2_2.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Level 2 second card");
		else if(gizmoBound2_3.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Level 2 third card");			
		else if(gizmoBound3_1.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Level 3 first card");
		else if(gizmoBound3_2.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Level 3 second card");
		else
			System.out.println(e.getX() + " , " + e.getY() + " out of bound of any card in display area");
		repaint();		
		//for (int i = 0 ; i < visibleMarbles.size() - 1 ; i++) {

			//if (visibleMarbles.get(i).getMarbleBounds().contains(e.getPoint())) {
				//System.out.println(visibleMarbles.get(i).getMarbleBounds());
				//pickMarble(0, i);
				//repaint();
				//break;
	}

		//}	
		
		//repaint();
		//System.out.println(players.get(0).getHeldMarbles());
		
	//}
	@Override
	public void mousePressed(MouseEvent e) {

	}
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	@Override
	public void mouseEntered(MouseEvent e) {

	}
	@Override
	public void mouseExited(MouseEvent e) {

	}
}