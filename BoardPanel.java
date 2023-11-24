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
	private ArrayList<Marble> marbles;
	private ArrayList<Player> players;
	private boolean firstDraw;
	private BufferedImage background, playergui, gizmoSheet1, gizmoSheet2;
	private int yellowCount, redCount, greyCount, blueCount;
	private Rectangle marbleBound1, marbleBound2, marbleBound3, marbleBound4, marbleBound5, marbleBound6; //temporary hardcode so we can start the more difficult part
	private Rectangle gizmoBound1_1, gizmoBound1_2, gizmoBound1_3, gizmoBound1_4;
	private Rectangle gizmoBound2_1, gizmoBound2_2, gizmoBound2_3;
	private Rectangle gizmoBound3_1, gizmoBound3_2;

	private Rectangle upgBound, convertBound;
	private Rectangle tier1bound, tier2bound, tier3bound;
	private Rectangle fileBound, pickBound, buildBound, researchBound;
	private ArrayList<Rectangle> upgradeBoundList, convertBoundList, fileBoundList, pickBoundList, buildBoundList, archiveBoundList;
	private Rectangle nextPlayerBound; //temporary rectangle for player to click to give up the turn to the next player
	private int totalPlayers; // 4 players for the game
	private int currentPlayer;

	private boolean researched = false;
	private Gizmo firstCard;

	public BoardPanel() {
		players = new ArrayList<>();
		totalPlayers = 4;
		currentPlayer = 0;
		visibleMarbles = new ArrayList<>();
		marbles = new ArrayList<>();
		t1Gizmos = new ArrayList<>();
		t2Gizmos = new ArrayList<>();
		t3Gizmos = new ArrayList<>();

		upgradeBoundList = new ArrayList<>();
		convertBoundList = new ArrayList<>();
		fileBoundList = new ArrayList<>();
		pickBoundList = new ArrayList<>();
		buildBoundList = new ArrayList<>();
		archiveBoundList = new ArrayList<>();

		int temp = 0;
		String marbleColor = "";
		for (int i = 0 ; i < 4 ; i++) {
			if(i == 0)
				marbleColor = "Red";
			else if(i == 1)
				marbleColor = "Yellow";
			else if(i == 2)
				marbleColor = "Blue";
			else
				marbleColor = "Grey";
			for(int j = 0; j < 13; j++){
				Marble m = new Marble(marbleColor);
				marbles.add(m);	
			}
								
		}
		Collections.shuffle(marbles);
		for (int i = 0 ; i < 6 ; i++) {
			Marble marble = marbles.remove(0);			
			visibleMarbles.add(marble);
			temp+=25;
		}
		nextPlayerBound = new Rectangle(1800, 900, 100, 100);
		tier3bound = new Rectangle(35, 80, 137, 124);
		tier2bound = new Rectangle(35, 240, 137, 124);
		tier1bound = new Rectangle(35, 400, 137, 124);
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

		upgBound = new Rectangle(240, 580, 165, 75);
		convertBound = new Rectangle(405, 580, 180, 75);
		fileBound = new Rectangle(585, 580, 180, 75);
		pickBound = new Rectangle(765, 580, 180, 75);
		buildBound = new Rectangle(930, 580, 180, 75);
		researchBound = new Rectangle(1095, 580, 120, 75);

		upgradeBoundList.add(new Rectangle(upgBound.x + 10, upgBound.y + upgBound.height, 143, 130));
		convertBoundList.add(new Rectangle(convertBound.x + 20, convertBound.y + convertBound.height, 143, 130));
		fileBoundList.add(new Rectangle(fileBound.x + 20, fileBound.y + fileBound.height, 143, 130));
		pickBoundList.add(new Rectangle(pickBound.x + 20, pickBound.y + pickBound.height, 143, 130));
		buildBoundList.add(new Rectangle(buildBound.x + 20, buildBound.y + buildBound.height, 143, 130));
		archiveBoundList.add(new Rectangle(1215, buildBound.y + buildBound.height, 143, 130));

		try {
            // Load images
			marbleDispenser = ImageIO.read(BoardFrame.class.getResource("/images/Dispenser.png"));
			background = ImageIO.read(BoardFrame.class.getResource("/images/gameback.png"));
			playergui = ImageIO.read(BoardFrame.class.getResource("/images/playergui.png"));
			gizmoSheet1 = ImageIO.read(BoardFrame.class.getResource("/images/sheet1.jpg"));
			gizmoSheet2 = ImageIO.read(BoardFrame.class.getResource("/images/sheet2.jpg"));	
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


		for (int i = 0 ; i <= 3 ; i++) {
			//subimage makes it so we dont need 900000 diff images, basically a bitmap, each gizmo is 490x490, if u want to get the image of the 2nd gizmo on the top for example it would be getSubimage(x: 490, y: 0, width: 490, height: 490)
			for (int j = 0 ; j <= 7 /*why only 3 */ ; j++) {
				t1Gizmos.add(new Gizmo(gizmoSheet1.getSubimage(j*490, i*490, 490, 490), 1));
			}
			
		}
		for(int j = 0; j <= 3; j++){
			t1Gizmos.add(new Gizmo(gizmoSheet1.getSubimage(j*490, 4*490, 490, 490), 1));
		}


		for(int j = 4; j <= 7; j++){
			t2Gizmos.add(new Gizmo(gizmoSheet1.getSubimage(j*490, 4*490, 490, 490), 2));
		}
		for (int i = 5 ; i <= 7; i++) {
			//subimage makes it so we dont need 900000 diff images, basically a bitmap, each gizmo is 490x490, if u want to get the image of the 2nd gizmo on the top for example it would be getSubimage(x: 490, y: 0, width: 490, height: 490)
			for (int j = 0 ; j <= 7 ; j++) {
				t2Gizmos.add(new Gizmo(gizmoSheet1.getSubimage(j*490, i*490, 490, 490), 2));
			}
			
		}
		for(int j = 0; j <= 6; j++){
			t2Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(j*490, 0*490, 490, 490), 2));
		}
		t2Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(0*490, 1*490, 490, 490), 2));

		
		for(int j = 1; j <= 6; j++){
			t3Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(j*490, 1*490, 490, 490), 3));
		}
		for (int i = 2 ; i <= 5; i++) {
			//subimage makes it so we dont need 900000 diff images, basically a bitmap, each gizmo is 490x490, if u want to get the image of the 2nd gizmo on the top for example it would be getSubimage(x: 490, y: 0, width: 490, height: 490)
			for (int j = 0 ; j <= 6 ; j++) {
				t3Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(j*490, i*490, 490, 490), 3)); //fix this later
			}
		}
		t3Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(6*490, 0*490, 490, 490), 3));
		t3Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(6*490, 1*490, 490, 490), 3));

		firstCard = new Gizmo(gizmoSheet2.getSubimage(2*490, 6*490, 490, 490), 0);
		Collections.shuffle(t1Gizmos);
		Collections.shuffle(t2Gizmos);
		Collections.shuffle(t3Gizmos);
		players.add(new Player("A"));
		players.add(new Player("B"));
		players.add(new Player("C"));
		players.add(new Player("D"));
		addMouseListener(this);
		firstDraw = true;
	}
	public void pickMarble(int index, String color) {
		// Marble newMarble = new Marble(color);

		if (visibleMarbles.get(index).toString().equals("Red")) { redCount++; }
		else if (visibleMarbles.get(index).toString().equals("Yellow")) { yellowCount++; }
		else if (visibleMarbles.get(index).toString().equals("Grey")) { greyCount++; }
		else { blueCount++; }
		// players.get(playerNum).addMarble(visibleMarbles.remove(index));
		// visibleMarbles.add(0, newMarble); 
    	players.get(currentPlayer).addMarble(visibleMarbles.remove(index));
		visibleMarbles.add(marbles.remove(0));
		
		System.out.println("Picking a " + color + " marble with " + marbles.size() + " marbles left in the dispenser");
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
		for (int i = 0; i < 4; i++) {
			g.drawImage(t1Gizmos.get(i).getImage(), 200 + temp, 390, 143, 130, null);
			temp+=170;
		}
		temp = 0;

		for (int i = 0; i < 3; i++) {
			g.drawImage(t2Gizmos.get(i).getImage(), 200 + temp, 235, 143, 130, null);
			temp+=170;
			//if (temp > 340) { break; }  
		}
		temp = 0;
		for (int i = 0; i < 2; i++) {
			g.drawImage(t3Gizmos.get(i).getImage(), 200 + temp, 80, 143, 130, null);
			temp+=170;
		}
		int i = 0;
		for (Marble m : visibleMarbles) {
			m.setMarbleX(0);
			g.drawImage(m.getMarbleImage(), m.getMarbleX(), m.getMarbleY() + i, null);
			i+=25;
		}

		g.drawImage(firstCard.getImage(), fileBound.x + 20, fileBound.y + fileBound.height, 143, 130, null);
		if(researched == true){
			g.fillRect(35, 80, 140, 2);
			g.fillRect(35, 210, 140, 2);
			g.fillRect(35, 80, 2, 132);
			g.fillRect(175, 80, 2, 132);
			g.fillRect(35, 237, 140, 2);
			g.fillRect(35, 367, 140, 2);
			g.fillRect(35, 237, 2, 132);
			g.fillRect(175, 237, 2, 132);
			g.fillRect(35, 395, 140, 2);
			g.fillRect(35, 525, 140, 2);
			g.fillRect(35, 395, 2, 132);
			g.fillRect(175, 395, 2, 132);
		}

		g.drawRect(nextPlayerBound.x, nextPlayerBound.y, nextPlayerBound.width, nextPlayerBound.height);
		Player p = players.get(currentPlayer);
		g.drawString(p.getName(), nextPlayerBound.x + 25, nextPlayerBound.y + 30);
		g.drawString("Next", nextPlayerBound.x + 25, nextPlayerBound.y + 60);

	}

		
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println(x + ", " + y);

		
 
		if(marbleBound1.contains(e.getPoint())) {	  
			pickMarble(0, visibleMarbles.get(0).getMarbleColor());
		} 
		else if(marbleBound2.contains(e.getPoint())){
			pickMarble(1, visibleMarbles.get(1).getMarbleColor());
		}
		else if(marbleBound3.contains(e.getPoint())){
			pickMarble(2, visibleMarbles.get(2).getMarbleColor());
		}
		else if(marbleBound4.contains(e.getPoint())){
			pickMarble(3, visibleMarbles.get(3).getMarbleColor());
		}
		else if(marbleBound5.contains(e.getPoint())){
			pickMarble(4, visibleMarbles.get(4).getMarbleColor());
		}
		else if(marbleBound6.contains(e.getPoint())){
			pickMarble(5, visibleMarbles.get(5).getMarbleColor());
		}
		else if(gizmoBound1_1.contains(e.getPoint())){
			System.out.println(e.getX() + " , " + e.getY() + " in bound of tier 1 first card");
			System.out.println(t1Gizmos.get(0).getType());
		}
					
		else if(gizmoBound1_2.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of tier 1 second card");
		else if(gizmoBound1_3.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of tier 1 third card");
		else if(gizmoBound1_4.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of tier 1 4th card");
		else if(gizmoBound2_1.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of tier 2 first card");
		else if(gizmoBound2_2.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of tier 2 second card");
		else if(gizmoBound2_3.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of tier 2 third card");			
		else if(gizmoBound3_1.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of tier 3 first card");
		else if(gizmoBound3_2.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Level 3 second card");
		else if(fileBound.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of File");
		else if(pickBound.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Pick");
		else if(buildBound.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Build");
		else if(researchBound.contains(e.getPoint()))
			System.out.println(e.getX() + " , " + e.getY() + " in bound of Research");
		else if(nextPlayerBound.contains(e.getPoint())) {	  
			Player p = players.get(currentPlayer);
			
			currentPlayer++;
			currentPlayer = currentPlayer % 4;
			p = players.get(currentPlayer);
			System.out.println("Next player: " + p.getName());
			redCount = yellowCount = blueCount = greyCount = 0;
			for(int i = 0; i < p.getHeldMarbles().size(); i++)
			{
				Marble m = p.getHeldMarbles().get(i);
				System.out.println("Player " + p.getName() + " marble " + m.getMarbleColor());
				if(m.getMarbleColor() == "Red")
					redCount++;
				else if(m.getMarbleColor() == "Yellow")
					yellowCount++;
				else if(m.getMarbleColor() == "Blue")
					blueCount++;
				else
					greyCount++;
			}
			System.out.println("Current player: " + p.getName() + " has " + p.getHeldMarbles().size() + " marbles");
			System.out.println("Red: " + redCount + " Yellow: " + yellowCount + " Blue: " + blueCount + " Grey: " + greyCount);
		}
		else
			System.out.println(e.getX() + " , " + e.getY() + " out of bound of any card in display area");
		if(researchBound.contains(e.getPoint())){
			researched = true;
		}
		repaint();		
	}

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