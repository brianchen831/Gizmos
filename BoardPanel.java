import java.awt.image.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.*;
import static java.lang.System.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import org.w3c.dom.css.Rect;


public class BoardPanel extends JPanel implements MouseListener {

	private BufferedImage marbleDispenser;
	private ArrayList<Gizmo> t1Gizmos, t2Gizmos, t3Gizmos;
	private ArrayList<Marble> visibleMarbles;
	private ArrayList<Marble> marbles;
	private ArrayList<Player> players;
	private boolean firstDraw;
	private BufferedImage victoryPointDisplay, turnText, background, playergui, player1gui, gizmoSheet1, gizmoSheet2, victoryPoint1, victoryPoint5, displayWhen1st, displayWhen2nd, howtopla;
	private int yellowCount, redCount, greyCount, blueCount;
	private Rectangle marbleBound1, marbleBound2, marbleBound3, marbleBound4, marbleBound5, marbleBound6; // temporary
																											// hardcode
																											// so we can
																											// start the
																											// more
																											// difficult
																											// part
	private int spentRed, spentBlue, spentGray, spentYellow;
	private Rectangle gizmoBound1_1, gizmoBound1_2, gizmoBound1_3, gizmoBound1_4;
	private Rectangle gizmoBound2_1, gizmoBound2_2, gizmoBound2_3;
	private Rectangle gizmoBound3_1, gizmoBound3_2;

	private Rectangle upgBound, convertBound, archiveBound;
	private Rectangle tier1bound, tier2bound, tier3bound;
	private Rectangle fileBound, pickBound, buildBound, researchBound;
	private Rectangle activeBound;
	private Rectangle privateGizmoBound;

	private ArrayList<Rectangle> upgradeBoundList, convertBoundList, fileBoundList, pickBoundList, buildBoundList,
			archiveBoundList;
	private ArrayList<Rectangle> fourMarbleBoundList;
	private Rectangle nextPlayerBound; // temporary rectangle for player to click to give up the turn to the next
										// player
	private int totalPlayers; // 4 players for the game
	private int currentPlayer;

	private boolean researched = false;
	private boolean FileBoundClicked = false;
	private ArrayList<Gizmo> firstCard;
	private ArrayList<String> usedPrivateGizmoThisRound; // used to record what private gizmos in player dashboard have
															// been used for the current round

	private Gizmo gizmoBeingBuilt;
	private int gizmoBeingBuiltPosition;
	private Gizmo gizmoPrivateSelected;
	private ArrayList<Marble> fourMarbleList;
	private ArrayList<Marble> tempConvertedMarbleList;
	private ArrayList<Rectangle> tempConvertedMarbleBoundList;
	private boolean showFourMarbleList;

	private boolean turnFinishedAlert = false;
	private String colorTempMarbleClicked = "";
	private boolean pickEffectActive = false;
	private boolean pickEffectActive2 = false;
	private boolean buildEffectActive = false;
	// private boolean fileEffectActive = false;
	JTextField redMarbleInput = new JTextField();
	JTextField grayMarbleInput = new JTextField();
	JTextField yellowMarbleInput = new JTextField();
	JTextField blueMarbleInput = new JTextField();
	private int researchMode = 0;
	private ArrayList<Rectangle> researchGizmoBoundList;
	private ArrayList<Gizmo> researchGizmoList;
	private int selectedResearchGizmoIndex;
	private boolean howtoplay = false;
	private boolean filing;
	private int currentRound = 0;
	private boolean tryConvertGizmos = false;
	private boolean bGameOver = false;
	private JComponent[] inputs = new JComponent[] {
						new JLabel("Red"),
						redMarbleInput,
						new JLabel("Gray"),
						grayMarbleInput,
						new JLabel("Blue"),
						blueMarbleInput,
						new JLabel("Yellow"),
						yellowMarbleInput,
						
		};
	
	private boolean researching = false;;

	public BoardPanel() {
		
		players = new ArrayList<>();
		totalPlayers = 4;
		currentPlayer = 0;
		visibleMarbles = new ArrayList<>();
		marbles = new ArrayList<>();
		t1Gizmos = new ArrayList<>();
		t2Gizmos = new ArrayList<>();
		t3Gizmos = new ArrayList<>();
		firstCard = new ArrayList<>();
		usedPrivateGizmoThisRound = new ArrayList<>();
		tempConvertedMarbleList = new ArrayList<>();
		fourMarbleList = new ArrayList<>();
		upgradeBoundList = new ArrayList<>();
		convertBoundList = new ArrayList<>();
		fileBoundList = new ArrayList<>();
		pickBoundList = new ArrayList<>();
		buildBoundList = new ArrayList<>();
		archiveBoundList = new ArrayList<>();
		fourMarbleBoundList = new ArrayList<>();
		tempConvertedMarbleBoundList = new ArrayList<>();
		gizmoBeingBuilt = null;
		gizmoBeingBuiltPosition = -1;
		gizmoPrivateSelected = null;
		showFourMarbleList = false;

		String marbleColor = "";
		researchGizmoBoundList = new ArrayList<>();
		researchGizmoList = new ArrayList<>();
		selectedResearchGizmoIndex = -1;

		for (int i = 0; i < 4; i++) {
			if (i == 0)
				marbleColor = "Red";
			else if (i == 1)
				marbleColor = "Yellow";
			else if (i == 2)
				marbleColor = "Blue";
			else
				marbleColor = "Grey";
			for (int j = 0; j < 13; j++) {
				Marble m = new Marble(marbleColor);
				marbles.add(m);
			}

		}
		Collections.shuffle(marbles);
		for (int i = 0; i < 6; i++) {
			Marble marble = marbles.remove(0);
			visibleMarbles.add(marble);
			//temp += 25;
		}
		Marble m = new Marble("Red");
		fourMarbleList.add(m);
		m = new Marble("Blue");
		fourMarbleList.add(m);
		m = new Marble("Grey");
		fourMarbleList.add(m);
		m = new Marble("Yellow");
		fourMarbleList.add(m);
		//nextPlayerBound = new Rectangle(1800, 900, 100, 100);
		nextPlayerBound = new Rectangle(1400, 600, 100, 100);
		activeBound = new Rectangle(0, 0, 0, 0);
		privateGizmoBound = new Rectangle(0, 0, 0, 0);

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
		archiveBound = new Rectangle(1215, 580, 180, 75);
		fileBound = new Rectangle(585, 580, 180, 75); 
		pickBound = new Rectangle(765, 580, 163, 75);
		buildBound = new Rectangle(930, 580, 163, 75);
		researchBound = new Rectangle(1095, 580, 120, 75);
		
		upgradeBoundList.add(new Rectangle(upgBound.x + 10, upgBound.y + upgBound.height, 143, 130));
		convertBoundList.add(new Rectangle(convertBound.x + 20, convertBound.y + convertBound.height, 143, 130));
		fileBoundList.add(new Rectangle(fileBound.x + 20, fileBound.y + fileBound.height, 143, 130));
		pickBoundList.add(new Rectangle(pickBound.x + 20, pickBound.y + pickBound.height, 143, 130));
		buildBoundList.add(new Rectangle(buildBound.x + 20, buildBound.y + buildBound.height, 143, 130));
		archiveBoundList.add(new Rectangle(archiveBound.x, archiveBound.y + buildBound.height, 143, 130));
		for(int i = 0; i < 4; i++)
			fourMarbleBoundList.add(new Rectangle(-800 + i * 25, 640, 25,25));
				
		try {
			// Load images
			victoryPointDisplay = ImageIO.read(BoardFrame.class.getResource("/images/victoryPointDisplay.png"));
			marbleDispenser = ImageIO.read(BoardFrame.class.getResource("/images/Dispenser.png"));
			background = ImageIO.read(BoardFrame.class.getResource("/images/gameback.png"));
			playergui = ImageIO.read(BoardFrame.class.getResource("/images/playergui.png"));
			turnText = ImageIO.read(BoardFrame.class.getResource("/images/turnPlayedAlert.png"));
			gizmoSheet1 = ImageIO.read(BoardFrame.class.getResource("/images/sheet1.jpg"));
			gizmoSheet2 = ImageIO.read(BoardFrame.class.getResource("/images/sheet2.jpg"));
			player1gui = ImageIO.read(BoardFrame.class.getResource("/images/player1gui.png"));
			victoryPoint1 = ImageIO.read(BoardFrame.class.getResource("/images/victoryPoint1.png"));
			victoryPoint5 = ImageIO.read(BoardFrame.class.getResource("/images/victoryPoint5.png"));
			displayWhen2nd = ImageIO.read(BoardFrame.class.getResource("/images/case1display.png"));
			displayWhen1st = ImageIO.read(BoardFrame.class.getResource("/images/case2display.png"));
			howtopla = ImageIO.read(BoardFrame.class.getResource("/images/DIRECTIONDIRECTIONSDIRECTIONS.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		for (int i = 0; i <= 3; i++) {
			// subimage makes it so we dont need 900000 diff images, basically a bitmap,
			// each gizmo is 490x490, if u want to get the image of the 2nd gizmo on the top
			// for example it would be getSubimage(x: 490, y: 0, width: 490, height: 490)
			for (int j = 0; j <= 7 /* why only 3 */ ; j++) {
				t1Gizmos.add(new Gizmo(gizmoSheet1.getSubimage(j * 490, i * 490, 490, 490), 1));
			}

		}
		for (int j = 0; j <= 3; j++) {
			t1Gizmos.add(new Gizmo(gizmoSheet1.getSubimage(j * 490, 4 * 490, 490, 490), 1));
		}

		for (int j = 4; j <= 7; j++) {
			t2Gizmos.add(new Gizmo(gizmoSheet1.getSubimage(j * 490, 4 * 490, 490, 490), 2));
		}
		for (int i = 5; i <= 7; i++) {
			// subimage makes it so we dont need 900000 diff images, basically a bitmap,
			// each gizmo is 490x490, if u want to get the image of the 2nd gizmo on the top
			// for example it would be getSubimage(x: 490, y: 0, width: 490, height: 490)
			for (int j = 0; j <= 7; j++) {
				t2Gizmos.add(new Gizmo(gizmoSheet1.getSubimage(j * 490, i * 490, 490, 490), 2));
			}

		}
		for (int j = 0; j <= 6; j++) {
			t2Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(j * 490, 0 * 490, 490, 490), 2));
		}
		t2Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(0 * 490, 1 * 490, 490, 490), 2));




		for (int j = 1; j <= 6; j++) { // 
			t3Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(j * 490, 1 * 490, 490, 490), 3));
		}
		for (int i = 2; i <= 5; i++) {
			// subimage makes it so we dont need 900000 diff images, basically a bitmap,
			// each gizmo is 490x490, if u want to get the image of the 2nd gizmo on the top
			// for example it would be getSubimage(x: 490, y: 0, width: 490, height: 490)
			for (int j = 0; j <= 6; j++) {
				t3Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(j * 490, i * 490, 490, 490), 3)); // fix this later
			}
		}
		t3Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(0 * 490, 6 * 490, 490, 490), 3));
		t3Gizmos.add(new Gizmo(gizmoSheet2.getSubimage(1 * 490, 6 * 490, 490, 490), 3));
		for (int i = 0; i < 4; i++){
			firstCard.add(new Gizmo(gizmoSheet2.getSubimage(2 * 490, 6 * 490, 490, 490), 0));
		}
		
		
		Collections.shuffle(t1Gizmos);
		Collections.shuffle(t2Gizmos);
		Collections.shuffle(t3Gizmos);

		out.println(t3Gizmos.size());
		Collections.rotate(t3Gizmos, 4);
		Collections.rotate(t2Gizmos, -4);
		Collections.rotate(t1Gizmos, -16);

		Player p1 = new Player("A");
		p1.setPosition(0);
		p1.addFileGizmo(firstCard.get(0));
		players.add(p1);
		Player p2 = new Player("B");
		p2.setPosition(1);
		p2.addFileGizmo(firstCard.get(1));
		players.add(p2);
		Player p3 = new Player("C");
		p3.setPosition(2);
		p3.addFileGizmo(firstCard.get(2));
		players.add(p3);
		Player p4 = new Player("D");
		p4.setPosition(3);
		p4.addFileGizmo(firstCard.get(3));
		p1.getFileGizmos().get(0).setX(fileBound.x + 20);
		p1.getFileGizmos().get(0).setY(fileBound.y + fileBound.height + 30 * (p1.getFileGizmos().size() - 1));
		p2.getFileGizmos().get(0).setX(fileBound.x + 20);
		p2.getFileGizmos().get(0).setY(fileBound.y + fileBound.height + 30 * (p1.getFileGizmos().size() - 1));
		p3.getFileGizmos().get(0).setX(fileBound.x + 20);
		p3.getFileGizmos().get(0).setY(fileBound.y + fileBound.height + 30 * (p1.getFileGizmos().size() - 1));
		p4.getFileGizmos().get(0).setX(fileBound.x + 20);
		p4.getFileGizmos().get(0).setY(fileBound.y + fileBound.height + 30 * (p1.getFileGizmos().size() - 1));
		players.add(p4);
		addMouseListener(this);
		firstDraw = true;
		
		System.out.println(p1.getFileGizmos().size());

		//  players.get(currentPlayer).addMarbleSpace(100);
		//  for(int i = 0; i < 6; i++){
		//  	Marble em = new Marble("Red");
		//  	players.get(currentPlayer).addMarble(em);
		//  }
		//  for(int i = 0; i < 6; i++){
		//  	Marble em = new Marble("Yellow");
		//  	players.get(currentPlayer).addMarble(em);
		//  }
		//  for(int i = 0; i < 6; i++){
		//  	Marble em = new Marble("Blue");
		//  	players.get(currentPlayer).addMarble(em);
		//  }
		//  for(int i = 0; i < 6; i++){
		//  	Marble em = new Marble("Grey");
		//  	players.get(currentPlayer).addMarble(em);
		//  }
	}

	public void pickMarble(int index, String color) {
		if (players.get(currentPlayer).spaceForMoreMarble()) {
			if (!turnFinishedAlert || pickEffectActive || pickEffectActive2) {
				if (visibleMarbles.get(index).toString().equals("Red")) {
					redCount++;
					if(players.get(currentPlayer).spaceForMoreMarble())
						pickGizmoTriggered("Red");
				} else if (visibleMarbles.get(index).toString().equals("Yellow")) {
					yellowCount++;
					if(players.get(currentPlayer).spaceForMoreMarble())
						pickGizmoTriggered("Yellow");
				} else if (visibleMarbles.get(index).toString().equals("Grey")) {
					greyCount++;
					if(players.get(currentPlayer).spaceForMoreMarble())
						pickGizmoTriggered("Grey");
				} else {
					blueCount++;
					if(players.get(currentPlayer).spaceForMoreMarble())
						pickGizmoTriggered("Blue");
				}
				// players.get(playerNum).addMarble(visibleMarbles.remove(index));
				// visibleMarbles.add(0, newMarble);

				players.get(currentPlayer).addMarble(visibleMarbles.remove(index));
				visibleMarbles.add(0, marbles.remove(0));
				
				System.out.println(
						"Picking a " + color + " marble with " + marbles.size() + " marbles left in the dispenser");
			} 
			
			// NextPlayer();
			
			turnFinishedAlert = true;
			if(pickEffectActive && pickEffectActive2){
				pickEffectActive = false;
			}
			else if(pickEffectActive2){
				pickEffectActive2 = false;
			}
			else if(pickEffectActive){
				pickEffectActive = false;
			}
			repaint();

		}
		// else{
		// 	if(pickEffectActive && ){
		// 		pickEffectActive = false;
		// 		pickEffectActive2 = false;
		// 	}
		// }
	}

	public void resetMarbleDisp() {

	}

	public void resetGizmos() {

	}

	public void refreshBoard() {

	}

	public void paint(Graphics g) {
		// g.drawString(": " + yellowCount)
		// g.drawString(": " + blueCount);
		// g.drawString(": " + greyCount);

		// delete this if it doesnt work or breaks stuff
		
		if(bGameOver){
			g.drawImage(background, 0, 0, null);
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Proxima Nova", Font.PLAIN, 20));
			ArrayList<Integer> rankedPlayerList = new ArrayList<>(); 
			int left = 580;
			int top = 150;
			int height = 75;
			int width = 600;
			for(int i = 0; i < players.size(); i++){
				players.get(i).CalculateWeightedPts();
				rankedPlayerList.add(players.get(i).getWeightedPts());
				System.out.println("**************************" + players.get(i).getName() + " " + players.get(i).getWeightedPts());
				g.drawRect(left, top + (i + 1) * height, width, height);
			}
			String sWinner = "";
			Collections.sort(rankedPlayerList);
			for(int i = rankedPlayerList.size() - 1; i >= 0; i--){
				for(int j = 0; j < players.size(); j++){
					if(rankedPlayerList.get(i) == players.get(j).getWeightedPts()){
						if(i == rankedPlayerList.size() - 1){
							sWinner = players.get(j).getName();
						}
						String sInfo = "Rank " + (players.size() - i) + ". Player " + players.get(j).getName() + "   Victory Points: " + players.get(j).getVictoryPoints();
						sInfo += "    Marbles: " + players.get(j).getHeldMarbles().size();
						
						g.drawString(sInfo, left, top + 52 + (players.size() - i) * height);
						break;
					}
				}
			}
			g.setColor(Color.RED);
			g.setFont(new Font("Proxima Nova", Font.PLAIN, 25));
			g.drawString("Player " + sWinner + "wins!!!!", left, top + height * (players.size() + 2));
			return;
		}
		redCount = 0;
		blueCount = 0;
		yellowCount = 0;
		greyCount = 0;
		
		for (Marble m : players.get(currentPlayer).getHeldMarbles()) {
			
			if (m.getMarbleColor().equals("Red")) {
				redCount++;
			} else if (m.getMarbleColor().equals("Blue")) {
				blueCount++;
			} else if (m.getMarbleColor().equals("Yellow")) {
				yellowCount++;
			} else {
				greyCount++;
			}
			
		}
		// redCount-=spentRed;
		// blueCount-=spentBlue;
		// yellowCount-=spentYellow;
		// greyCount-=spentGray;
		
		
		g.drawImage(background, 0, 0, null);
		switch (currentPlayer) {
			case 0:
				out.println("--------------------drake--------------------");
				g.drawImage(displayWhen1st, 0, 0, null);
				for (Player p : players) {
					switch(p.getName()) {
						case "B":
							g.setColor(Color.white);
							g.drawString("" + p.getVictoryPoints(), 1195, 87);
						    int u = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getArchivedGizmos()) {
								g.drawImage(c.getImage(), 1840, 100 + u, 75, 75, null);

								u+=10;
							}
							g.setColor(Color.black);
							g.drawString("" + p.getRedMarbles(), 1245, 83);
							g.drawString("" + p.getBlueMarbles(), 1278, 83);
							g.drawString("" + p.getGrayMarbles(), 1245, 108);
							g.drawString("" + p.getYellowMarbles(), 1278, 108);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player B", 1360, 15);
							break;
						case "C":
							g.setColor(Color.white);
							g.drawString("" + p.getVictoryPoints(), 1195, 237);
							int u1 = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getArchivedGizmos()) {
								g.drawImage(c.getImage(), 1840, 250 + u1, 75, 75, null);
								u1+=10;
							}
							g.setColor(Color.black);
							g.drawString("" + p.getRedMarbles(), 1245, 233);
							g.drawString("" + p.getBlueMarbles(), 1278, 233);
							g.drawString("" + p.getGrayMarbles(), 1245, 258);
							g.drawString("" + p.getYellowMarbles(), 1278, 258);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player C", 1360, 172);
							break;
							
						case "D":
							g.setColor(Color.white);
                            g.drawString("" + p.getVictoryPoints(), 1195, 405);
							int u2 = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getArchivedGizmos()) {
								g.drawImage(c.getImage(), 1840, 400 + u2, 75, 75, null);
								u2+=10;
							}
							g.setColor(Color.black);
							g.drawString("" + p.getRedMarbles(), 1245, 397);
							g.drawString("" + p.getBlueMarbles(), 1278, 397);
							g.drawString("" + p.getGrayMarbles(), 1245, 423);
							g.drawString("" + p.getYellowMarbles(), 1278, 423);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player D", 1360, 336);
							break;
						default:
							out.println("DEFAULT TRIGGERED");
							break;
					}
					
				}
				break;
			case 1:
				g.drawImage(displayWhen2nd, 0, 0, null);
				for (Player p : players) {
					switch(p.getName()) {
						case "A":
							g.setColor(Color.white);
							g.drawString("" + p.getVictoryPoints(), 1195, 87);
						    int u = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getArchivedGizmos()) {
								g.drawImage(c.getImage(), 1840, 100 + u, 75, 75, null);
								u+=10;
							}
							g.setColor(Color.black);

							g.drawString("" + p.getRedMarbles(), 1245, 83);
							g.drawString("" + p.getBlueMarbles(), 1278, 83);
							g.drawString("" + p.getGrayMarbles(), 1245, 108);
							g.drawString("" + p.getYellowMarbles(), 1278, 108);

							//g.drawString("A",);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player A", 1360, 15);
							break;
						case "C":
							g.setColor(Color.white);
							g.drawString("" + p.getVictoryPoints(), 1195, 237);
							int u1 = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getArchivedGizmos()) {
								g.drawImage(c.getImage(), 1840, 250 + u1, 75, 75, null);
								u1+=10;
							}
							g.setColor(Color.black);
							g.drawString("" + p.getRedMarbles(), 1245, 233);
							g.drawString("" + p.getBlueMarbles(), 1278, 233);
							g.drawString("" + p.getGrayMarbles(), 1245, 258);
							g.drawString("" + p.getYellowMarbles(), 1278, 258);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player C", 1360, 172);
							break;
							
						case "D":
							g.setColor(Color.white);
							g.drawString("" + p.getVictoryPoints(), 1195, 405);
							int u2 = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getArchivedGizmos()) {
								g.drawImage(c.getImage(), 1840, 400 + u2, 75, 75, null);
								u2+=10;
							}
							g.setColor(Color.black);
							g.drawString("" + p.getRedMarbles(), 1245, 397);
							g.drawString("" + p.getBlueMarbles(), 1278, 397);
							g.drawString("" + p.getGrayMarbles(), 1245, 423);
							g.drawString("" + p.getYellowMarbles(), 1278, 423);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player D", 1360, 336);
							break;
							

						default:
							out.println("DEFAULT TRIGGERED");
							break;
					}
					
				}
				break;
			case 2:
				g.drawImage(displayWhen2nd, 0, 0, null);
				for (Player p : players) {
					switch(p.getName()) {
						case "A":
							g.setColor(Color.white);
							g.drawString("" + p.getVictoryPoints(), 1195, 87);
						    int u = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 100 + u, 75, 75, null);
								u+=10;
							}
							g.setColor(Color.black);
							g.drawString("" + p.getRedMarbles(), 1245, 83);
							g.drawString("" + p.getBlueMarbles(), 1278, 83);
							g.drawString("" + p.getGrayMarbles(), 1245, 108);
							g.drawString("" + p.getYellowMarbles(), 1278, 108);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player A", 1360, 15);
							break;
						case "D":
							g.setColor(Color.white);
							g.drawString("" + p.getVictoryPoints(), 1195, 237);	
							int u1 = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 250 + u1, 75, 75, null);
								u1+=10;
							}
							
							g.setColor(Color.black);
							g.drawString("" + p.getRedMarbles(), 1245, 233);
							g.drawString("" + p.getBlueMarbles(), 1278, 233);
							g.drawString("" + p.getGrayMarbles(), 1245, 258);
							g.drawString("" + p.getYellowMarbles(), 1278, 258);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player D", 1360, 172);
							break;
							
						case "B":
							g.setColor(Color.white);
							g.drawString("" + p.getVictoryPoints(), 1195, 405);
							int u2 = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 400 + u2, 75, 75, null);
								u2+=10;
							}
							g.setColor(Color.black);
							g.drawString("" + p.getRedMarbles(), 1245, 397);
							g.drawString("" + p.getBlueMarbles(), 1278, 397);
							g.drawString("" + p.getGrayMarbles(), 1245, 423);
							g.drawString("" + p.getYellowMarbles(), 1278, 423);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player B", 1360, 336);
							break;
						default:
							out.println("DEFAULT TRIGGERED");
							break;
					}
					
				}
				break;
			case 3:
				g.drawImage(displayWhen2nd, 0, 0, null);
				for (Player p : players) {
					switch(p.getName()) {
						case "A":
							g.setColor(Color.white);
							g.drawString("" + p.getVictoryPoints(), 1195, 87);
							
						    int u = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 100 + u, 75, 75, null);
								u+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 100 + u, 75, 75, null);
								u+=10;
							}
							g.setColor(Color.black);

							g.drawString("" + p.getRedMarbles(), 1245, 83);
							g.drawString("" + p.getBlueMarbles(), 1278, 83);
							g.drawString("" + p.getGrayMarbles(), 1245, 108);
							g.drawString("" + p.getYellowMarbles(), 1278, 108);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player A", 1360, 15);
							break;
						case "B":
							g.setColor(Color.white);
							g.drawString("" + p.getVictoryPoints(), 1195, 237);
							
							int u1 = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 250 + u1, 75, 75, null);
								u1+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 250 + u1, 75, 75, null);
								u1+=10;
							}
							g.setColor(Color.black);
							g.drawString("" + p.getRedMarbles(), 1245, 233);
							g.drawString("" + p.getBlueMarbles(), 1278, 233);
							g.drawString("" + p.getGrayMarbles(), 1245, 258);
							g.drawString("" + p.getYellowMarbles(), 1278, 258);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player B", 1360, 172);
							break;
							
							
						case "C":

							g.setColor(Color.white);
							g.drawString("" + p.getVictoryPoints(), 1195, 405);
							int u2 = 0;
							for (Gizmo c : p.getUpgradeGizmos()) {
								g.drawImage(c.getImage(), 1350, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getConvertGizmos()) {
								g.drawImage(c.getImage(), 1450, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getFileGizmos()) {
								g.drawImage(c.getImage(), 1550, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getPickGizmos()) {
								g.drawImage(c.getImage(), 1650, 400 + u2, 75, 75, null);
								u2+=10;
							}
							for (Gizmo c : p.getBuildGizmos()) {
								g.drawImage(c.getImage(), 1750, 400 + u2, 75, 75, null);
								u2+=10;
							}
							g.setColor(Color.black);
							g.drawString("" + p.getRedMarbles(), 1245, 397);
							g.drawString("" + p.getBlueMarbles(), 1278, 397);
							g.drawString("" + p.getGrayMarbles(), 1245, 423);
							g.drawString("" + p.getYellowMarbles(), 1278, 423);
							g.setColor(Color.white);
							g.setFont(g.getFont().deriveFont(15f));
							g.drawString("Player C", 1360, 336);
							break;
						default:
							out.println("DEFAULT TRIGGERED");
							
							break;
					}
					
				}
				break;
			default:
				System.out.println("--------------------default");


		}
		
		if (currentPlayer == 0) { g.drawImage(player1gui, 0, -180, null); }
		else { g.drawImage(playergui, 0, -180, null); }
		int incrementPos = 0;
		//int increment5Pos = 0;
		// while (v < players.get(currentPlayer).getVictoryPoints()) {
		// 	if (players.get(currentPlayer).getVictoryPoints() == 5) { 
		// 		g.drawImage(victoryPoint5, 88 + increment5Pos, 891, 63, 91, null); increment5Pos+=40;
		// 	 }
		// 	else { 
		// 		g.drawImage(victoryPoint1, 88 + increment1Pos, 891, 63, 91, null); increment1Pos+=40; 
		// 	}
		// 	v++;
		// }
		int vp = players.get(currentPlayer).getVictoryPoints();
		System.out.println(players.get(currentPlayer).getName() + " has " + vp + " victory points");
		int fiveVp = vp / 5;
		int oneVp = vp % 5;
		for(int k = 0; k < fiveVp; k++){
			g.drawImage(victoryPoint5, 108 + incrementPos, 891, 63, 91, null); incrementPos+=40;
		}
		for(int k = 0; k < oneVp; k++){
			g.drawImage(victoryPoint1, 108 + incrementPos, 891, 63, 91, null); incrementPos+=40; 
		}
		g.drawImage(victoryPointDisplay, 0, 0, null);
		g.drawImage(marbleDispenser, 0, 0, null);
		g.setFont(new Font("Proxima Nova", Font.PLAIN, 25));
		g.setColor(Color.BLACK);
		g.drawString("" + redCount, 68, 651);
		g.drawString("" + greyCount, 68, 693);
		g.drawString("" + blueCount, 123, 649);
		g.drawString("" + yellowCount, 123, 693);
		g.setColor(Color.YELLOW);
		// int temp = 0;
		// if(t1Gizmos.size() < 4){
		// 	for (int i = 0; i < 4; i++) {
		// 		g.drawImage(t1Gizmos.get(i).getImage(), 200 + temp, 390, 143, 130, null);
		// 		temp += 170;
		// 	}		
		// }
		// else{
		// 	JOptionPane.showMessageDialog(null, "Almost out of t1 gizmos");
		// 	for(int i = 0; i < t1Gizmos.size(); i++){
		// 		g.drawImage(t1Gizmos.get(i).getImage(), 200 + temp, 390, 143, 130, null);
		// 		temp += 170;
		// 	}
			
		// }

		// temp = 0;
		// if(t2Gizmos.size() < 3){
		// 	for (int i = 0; i < 3; i++) {
		// 		g.drawImage(t2Gizmos.get(i).getImage(), 200 + temp, 235, 143, 130, null);
		// 		temp += 170;
		// 	}	
		// }
		// else{
		// 	for(int i = 0; i < t2Gizmos.size(); i++){
		// 		JOptionPane.showMessageDialog(null, "Almost out of t2 gizmos");
		// 		g.drawImage(t2Gizmos.get(i).getImage(), 200 + temp, 235, 143, 130, null);
		// 		temp += 170;
		// 	}
		// }


		// temp = 0;
		// if(t3Gizmos.size() < 2){
		// 	for (int i = 0; i < 2; i++) {
		// 		g.drawImage(t3Gizmos.get(i).getImage(), 200 + temp, 80, 143, 130, null);
		// 		temp += 170;
		// 	}
		// }
		// else{
		// 	for (int i = 0; i < t3Gizmos.size(); i++) {
		// 		g.drawImage(t3Gizmos.get(i).getImage(), 200 + temp, 80, 143, 130, null);
		// 		temp += 170;
		// 	}
		// }
		int temp = 0;
		for (int i = 0; i < 4; i++) {
			g.drawImage(t1Gizmos.get(i).getImage(), 200 + temp, 390, 143, 130, null);
			temp += 170;
		}
		temp = 0;

		for (int i = 0; i < 3; i++) {
			g.drawImage(t2Gizmos.get(i).getImage(), 200 + temp, 235, 143, 130, null);
			temp += 170;
			// if (temp > 340) { break; }
		}
		temp = 0;
		for (int i = 0; i < 2; i++) {
			g.drawImage(t3Gizmos.get(i).getImage(), 200 + temp, 80, 143, 130, null);
			temp += 170;
		}
		int i = 0;
		for (Marble m : visibleMarbles) {
			//m.setMarbleX(0);
			g.drawImage(m.getMarbleImage(), m.getMarbleX(), m.getMarbleY() + i, null);
			i += 25;
		}
		
		
		
		g.drawImage(firstCard.get(currentPlayer).getImage(), fileBound.x + 20, fileBound.y + fileBound.height, 143, 130,
				null);
		if (researched == true) {
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

		if(researchMode == 1)
			g.drawRect(tier1bound.x, tier1bound.y, tier1bound.width, tier1bound.height);
		if(researchMode == 2)
			g.drawRect(tier2bound.x, tier2bound.y, tier2bound.width, tier2bound.height);
		if(researchMode == 3)
			g.drawRect(tier3bound.x, tier3bound.y, tier3bound.width, tier3bound.height);

		g.drawRect(nextPlayerBound.x, nextPlayerBound.y, nextPlayerBound.width, nextPlayerBound.height);

		Player p = players.get(currentPlayer);
		g.drawString("  " + p.getName(), nextPlayerBound.x + 25, nextPlayerBound.y + 30);
		g.drawString("Next", nextPlayerBound.x + 25, nextPlayerBound.y + 60);

		// draw player dashboard area
		for (i = 0; i < p.getBuildGizmos().size(); i++) {
			Gizmo gm = p.getBuildGizmos().get(i);
			g.drawImage(gm.getImage(), buildBoundList.get(i).x, buildBoundList.get(i).y, 143, 130, null);
		}
		for (i = 0; i < p.getConvertGizmos().size(); i++) {
			Gizmo gm = p.getConvertGizmos().get(i);
			g.drawImage(gm.getImage(), convertBoundList.get(i).x, convertBoundList.get(i).y, 143, 130, null);
		}
		for (i = 1; i < p.getFileGizmos().size(); i++) {
			Gizmo gm = p.getFileGizmos().get(i);
			g.drawImage(gm.getImage(), fileBoundList.get(i).x, fileBoundList.get(i).y, 143, 130, null);
		}
		for (i = 0; i < p.getPickGizmos().size(); i++) {
			Gizmo gm = p.getPickGizmos().get(i);
			g.drawImage(gm.getImage(), pickBoundList.get(i).x, pickBoundList.get(i).y, 143, 130, null);
		}
		for (i = 0; i < p.getUpgradeGizmos().size(); i++) {
			Gizmo gm = p.getUpgradeGizmos().get(i);
			g.drawImage(gm.getImage(), upgradeBoundList.get(i).x, upgradeBoundList.get(i).y, 143, 130, null);
		}
		//System.out.println("*****archived gizmo number: " + p.getArchivedGizmos().size());
		for (i = 0; i < p.getArchivedGizmos().size(); i++) {
			Gizmo gm = p.getArchivedGizmos().get(i);
			g.drawImage(gm.getImage(), archiveBoundList.get(i).x, archiveBoundList.get(i).y, 143, 130, null);
		}

		if(researchMode > 0){
            for(i = 0; i < researchGizmoList.size(); i++){
                Gizmo gm = researchGizmoList.get(i);
                g.drawImage(gm.getImage(), researchGizmoBoundList.get(i).x, researchGizmoBoundList.get(i).y, 143, 130, null);
            }
            if(selectedResearchGizmoIndex >= 0)
                g.drawRect(researchGizmoBoundList.get(selectedResearchGizmoIndex).x, researchGizmoBoundList.get(selectedResearchGizmoIndex).y, 
                researchGizmoBoundList.get(selectedResearchGizmoIndex).width, researchGizmoBoundList.get(selectedResearchGizmoIndex).height);
            g.drawRect(fileBound.x, fileBound.y, fileBound.width, fileBound.height);
            g.drawRect(buildBound.x, buildBound.y, buildBound.width, buildBound.height);
			g.drawRect(1550, 600, 100, 100);
			g.drawString("Dismiss", 1550, 650);
			g.drawString("Research", 1550, 670);
        }
		
		if (tempConvertedMarbleList.size() > 0) {
			g.setFont(new Font("Proxima Nova", Font.PLAIN, 16));
			g.drawString("Convert From:", 30, 860);
			for (i = 0; i < tempConvertedMarbleList.size(); i++) {
				g.drawImage(tempConvertedMarbleList.get(i).getMarbleImage(), tempConvertedMarbleBoundList.get(i).x, 
				tempConvertedMarbleBoundList.get(i).y, null);
			}
		}
		if(gizmoPrivateSelected != null){
			System.out.println("private selected not null");
			if(showFourMarbleList){
				System.out.println("draw 4 marbles for players to pick");
				g.drawString("Select From:", 30, 905);
				for(i = 0; i < 4; i++){
					g.drawImage(fourMarbleList.get(i).getMarbleImage(), 
					fourMarbleBoundList.get(i).x, fourMarbleBoundList.get(i).y, null);
				}
			}
		}

		g.setColor(Color.GREEN);
		out.println("what is active bound" + " " + activeBound.x);

		if (activeBound.x > 0)
			g.drawRect(activeBound.x, activeBound.y, activeBound.width, activeBound.height);

		if (privateGizmoBound.x > 0) {
			g.setColor(Color.YELLOW);
			g.drawRect(privateGizmoBound.x, privateGizmoBound.y, privateGizmoBound.width, privateGizmoBound.height);
		}
		
		g.setColor(Color.white);
		if(FileBoundClicked && !turnFinishedAlert){
			g.drawString("Filing...", (int)fileBound.getX(), (int)fileBound.getY() - 10);
		}

		g.setColor(Color.red);
		if(pickEffectActive || pickEffectActive2){
			g.drawRect((int)pickBound.x, (int)pickBound.y, (int)pickBound.getWidth(), (int)pickBound.getHeight());
		}
		if(buildEffectActive){
			g.drawRect((int)buildBound.x, (int)buildBound.y, (int)buildBound.getWidth(), (int)buildBound.getHeight());
		}
		ArrayList<Gizmo> fileGizmos = players.get(currentPlayer).getFileGizmos();
		ArrayList<Gizmo> buildGizmos = players.get(currentPlayer).getBuildGizmos();
		ArrayList<Gizmo> pickGizmos = players.get(currentPlayer).getPickGizmos();
		boolean reactionAvailable = false;;
		for(Gizmo gizmo : fileGizmos){
			if(gizmo.isTriggered() && !gizmo.getJustBuilt() && !gizmo.isTriggeredThisRound()){
				reactionAvailable = true;
				if(gizmo.getY() < fileBoundList.get(fileBoundList.size() - 1).getY()){
					g.drawRect(gizmo.getX(), gizmo.getY(), 143, 30);
				}
				else if(gizmo.getY() == fileBoundList.get(fileBoundList.size() - 1).getY()){
					g.drawRect(gizmo.getX(), gizmo.getY(), 143, 130);
				}
			}
		}
		for(Gizmo gizmo : buildGizmos){
			if(gizmo.isTriggered() && !gizmo.getJustBuilt() && !gizmo.isTriggeredThisRound()){
				reactionAvailable = true;
				if(gizmo.getY() < buildBoundList.get(buildBoundList.size() - 1).getY()){
					g.drawRect(gizmo.getX(), gizmo.getY(), 143, 30);
				}
				else if(gizmo.getY() == buildBoundList.get(buildBoundList.size() - 1).getY()){
					g.drawRect(gizmo.getX(), gizmo.getY(), 143, 130);
				}
			}
		}
		for(Gizmo gizmo : pickGizmos){
			if(gizmo.isTriggered() && !gizmo.getJustBuilt() && !gizmo.isTriggeredThisRound()){
				reactionAvailable = true;
				System.out.println("highlighting triggered pick gizmos");		
				if(gizmo.getY() < pickBoundList.get(pickBoundList.size() - 1).getY()){
					g.drawRect(gizmo.getX(), gizmo.getY(), 143, 30);
				}
				else if(gizmo.getY() == pickBoundList.get(pickBoundList.size() - 1).getY()){
					g.drawRect(gizmo.getX(), gizmo.getY(), 143, 130);
				}
			}
		}
		//g.setColor(Color.blue);
		if (turnFinishedAlert && !reactionAvailable && !pickEffectActive && !pickEffectActive2 && !buildEffectActive) {
			//g.drawImage(turnText,A1800, 900, null);
			g.drawImage(turnText, 870, 500, null);
		}
		else if(tryConvertGizmos){
			g.setFont(new Font("Proxima Nova", Font.PLAIN, 16));
			g.drawString("Player may click converter gizmos to build new gizmo", 420, 540);			
		}
		g.setColor(Color.white);
		g.drawRect(1700, 850,150, 150);
		g.drawString("How To Play",1703, 925 );
		if(howtoplay){
			g.drawImage(howtopla, 0, 0, 1920, 1080, null);
			g.drawRect(1700, 850, 150, 150);
            g.drawString("BACK", 1740, 925);
		}
	}
	private void DisplayTempConvertedMarbles(){
		System.out.println("Temporary Convert Marble size: " + tempConvertedMarbleList.size());
		for(int i = 0; i < tempConvertedMarbleList.size(); i++)
			System.out.println(tempConvertedMarbleList.get(i).getMarbleColor());
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		JTabbedPane.setDefaultLocale(getLocale());
		System.out.println("clicked his mouse");
		int x = e.getX();
		int y = e.getY();
		out.println(x + ", " + y);
		
		if (nextPlayerBound.contains(e.getPoint())) {
			if (turnFinishedAlert) {

				NextPlayer();

			} else {
				setBorder(new LineBorder(Color.black, 2, true));
				JOptionPane.showMessageDialog(null, "Please complete an action before completing your turn.");

			}
        }
        // if(turnFinishedAlert)
        //     return;

		if (marbleBound1.contains(e.getPoint())) {
			pickMarble(0, visibleMarbles.get(0).getMarbleColor());
		} else if (marbleBound2.contains(e.getPoint())) {
			pickMarble(1, visibleMarbles.get(1).getMarbleColor());
		} else if (marbleBound3.contains(e.getPoint())) {
			pickMarble(2, visibleMarbles.get(2).getMarbleColor());
		} else if (marbleBound4.contains(e.getPoint())) {
			pickMarble(3, visibleMarbles.get(3).getMarbleColor());
		} else if (marbleBound5.contains(e.getPoint())) {
			pickMarble(4, visibleMarbles.get(4).getMarbleColor());
		} else if (marbleBound6.contains(e.getPoint())) {
			pickMarble(5, visibleMarbles.get(5).getMarbleColor());
		} else if (gizmoBound1_1.contains(e.getPoint())) {
			System.out.println(e.getX() + " , " + e.getY() + " in bound of tier 1 first card");
			System.out.println(t1Gizmos.get(0).getType());
			activeBound.setBounds(gizmoBound1_1);
			Gizmo g = t1Gizmos.get(0);
			gizmoBeingBuilt = g;
			gizmoBeingBuiltPosition = 0;
			ActOnGizmoClick(g, 0);
		}

		else if (gizmoBound1_2.contains(e.getPoint())) {

			System.out.println(t1Gizmos.get(1).getType());
			activeBound.setBounds(gizmoBound1_2);
			Gizmo g = t1Gizmos.get(1);
			gizmoBeingBuiltPosition = 1;
			gizmoBeingBuilt = g;
			ActOnGizmoClick(g, 1);
		} else if (gizmoBound1_3.contains(e.getPoint())) {
			System.out.println(t1Gizmos.get(2).getType());
			activeBound.setBounds(gizmoBound1_3);
			Gizmo g = t1Gizmos.get(2);
			gizmoBeingBuilt = g;
			gizmoBeingBuiltPosition = 2;
			ActOnGizmoClick(g, 2);
		} else if (gizmoBound1_4.contains(e.getPoint())) {
			System.out.println(t1Gizmos.get(3).getType());
			activeBound.setBounds(gizmoBound1_4);
			gizmoBeingBuiltPosition = 3;
			Gizmo g = t1Gizmos.get(3);
			gizmoBeingBuilt = g;
			ActOnGizmoClick(g, 3);
		} else if (gizmoBound2_1.contains(e.getPoint())) {
			activeBound.setBounds(gizmoBound2_1);
			Gizmo g = t2Gizmos.get(0);
			gizmoBeingBuilt = g;
			gizmoBeingBuiltPosition = 4;
			ActOnGizmoClick(g, 4);
		} else if (gizmoBound2_2.contains(e.getPoint())) {
			activeBound.setBounds(gizmoBound2_2);
			Gizmo g = t2Gizmos.get(1);
			gizmoBeingBuilt = g;
			gizmoBeingBuiltPosition = 5;
			ActOnGizmoClick(g, 5);
		} else if (gizmoBound2_3.contains(e.getPoint())) {
			activeBound.setBounds(gizmoBound2_3);
			Gizmo g = t2Gizmos.get(2);
			gizmoBeingBuiltPosition = 6;
			gizmoBeingBuilt = g;
			ActOnGizmoClick(g, 6);
		} else if (gizmoBound3_1.contains(e.getPoint())) {
			activeBound.setBounds(gizmoBound3_1);
			Gizmo g = t3Gizmos.get(0);
			gizmoBeingBuilt = g;
			gizmoBeingBuiltPosition = 7;
			ActOnGizmoClick(g, 7);
		} else if (gizmoBound3_2.contains(e.getPoint())) {
			activeBound.setBounds(gizmoBound3_2);
			Gizmo g = t3Gizmos.get(1);
			gizmoBeingBuiltPosition = 8;
			gizmoBeingBuilt = g;
			ActOnGizmoClick(g, 8);
		} else if (fileBound.contains(e.getPoint())) {
			privateGizmoBound.setBounds(fileBound);
			//activeBound.setBounds(fileBound);
		} else if (pickBound.contains(e.getPoint()))
			privateGizmoBound.setBounds(pickBound);
		else if (buildBound.contains(e.getPoint()))
			privateGizmoBound.setBounds(buildBound);
		// else if (researchBound.contains(e.getPoint()))
		// 	privateGizmoBound.setBounds(researchBound);
		// else if (nextPlayerBound.contains(e.getPoint())) {
		// 	NextPlayer();
		// }
		Player p = players.get(currentPlayer);
		for (int i = 0; i < fileBoundList.size(); i++) {
			//if (fileBoundList.get(i).contains(e.getPoint())) { //add this back if something breaks 
			if (fileBound.contains(e.getPoint())) {
				System.out.println("file Gizmo clicked%%%%%%%%%%%%%%%%");
				FileBoundClicked = true;
				privateGizmoBound.setBounds(fileBound);
				
				System.out.println("fileBoundList " + i + " clicked " + privateGizmoBound.y);
			}
			else{
				FileBoundClicked = false;
			}
		}
		// for(int i = 0; i < upgradeBoundList.size(); i++){
		// if(upgradeBoundList.get(i).contains(e.getPoint())){
		// //activeBound.setBounds(upgradeBoundList.get(i));
		// System.out.println("upgradeBoundList " + i + " clicked " + activeBound.y);
		// }
		// }
		// for(int i = 0; i < tempConvertedMarbleBoundList.size(); i++){
		// 	if(tempConvertedMarbleBoundList.get(i).contains(e.getPoint()))
		// 		System.out.println(tempConvertedMarbleList.get(i).getMarbleColor() + " in temp convert list clickerd");
		// }
		boolean tempMarbleClicked = false;
		for(int i = 0; i < tempConvertedMarbleBoundList.size(); i++){
			Point pt = new Point(e.getPoint().x - 940, e.getPoint().y - 250);
			if(tempConvertedMarbleBoundList.get(i).contains(pt)){
				tempMarbleClicked = true;
				colorTempMarbleClicked = tempConvertedMarbleList.get(i).getMarbleColor();
				System.out.println(tempConvertedMarbleList.get(i).getMarbleColor() + " in temporary converted marble list clickerd");
			}
		}
		if(gizmoPrivateSelected != null && tempMarbleClicked == true){
			if(gizmoPrivateSelected.getTrigger() == Gizmo.GizmoTgr.BlueOrYellow){
				System.out.println("BlueOrYellow case, should add " + colorTempMarbleClicked + " marble");
				for(int k = 0; k < tempConvertedMarbleList.size(); k++){
					if(tempConvertedMarbleList.get(k).getMarbleColor() == colorTempMarbleClicked){
						tempConvertedMarbleList.add(new Marble(colorTempMarbleClicked));
						System.out.println("Blindly adding " + colorTempMarbleClicked + " marble!!!");
						break;
					}
					else if(tempConvertedMarbleList.get(k).getMarbleColor() == "Blue"){
						tempConvertedMarbleList.add(new Marble("Blue"));
						System.out.println("Blindly adding BLUE marble!!!");
						break;
					}
					else if(tempConvertedMarbleList.get(k).getMarbleColor() == "Yellow"){
						tempConvertedMarbleList.add(new Marble("Yellow"));		
						System.out.println("Blindly adding yellow marble!!!");
						break;
					}	
				}
			}
			if(gizmoPrivateSelected.getTrigger() == Gizmo.GizmoTgr.RedOrGrey){
				for(int k = 0; k < tempConvertedMarbleList.size(); k++){
					if(tempConvertedMarbleList.get(k).getMarbleColor() == colorTempMarbleClicked){
						tempConvertedMarbleList.add(new Marble(colorTempMarbleClicked));
						System.out.println("Blindly adding " + colorTempMarbleClicked +  " marble!!!");
						break;
					}
					else if(tempConvertedMarbleList.get(k).getMarbleColor() == "Red"){
						tempConvertedMarbleList.add(new Marble("Red"));
						System.out.println("Blindly adding red marble!!!");
						break;
					}
					else if(tempConvertedMarbleList.get(k).getMarbleColor() == "Grey"){
						tempConvertedMarbleList.add(new Marble("Grey"));	
						System.out.println("Blindly adding grey marble!!!");	
						break;
					}	
				}
			}
			setExpandedConvertMarbleBoundList();
			repaint();
			DisplayTempConvertedMarbles();
			int matchedMarble = 0;
			for(int j = 0; j < tempConvertedMarbleBoundList.size(); j++){
				if(tempConvertedMarbleList.get(j).getMarbleColor() == gizmoBeingBuilt.getColor())
					matchedMarble++;
				if(matchedMarble == gizmoBeingBuilt.getCost()){
					showFourMarbleList = false;
					System.out.println("dispenser has total of " + marbles.size() + " before switching player's marbles");
					SwitchPlayerMarbles();
					tempConvertedMarbleList.clear();
					tempConvertedMarbleBoundList.clear();
					System.out.println("dispenser has total of " + marbles.size() + " after switching player's marbles");
					recountCurrentPlayerMarbles(p);
					System.out.println("red: " + redCount + " yellow: " + yellowCount + " blue: " + blueCount + " grey: " + greyCount);
					ActOnGizmoClick(gizmoBeingBuilt, gizmoBeingBuiltPosition);
				}	
			}
		}
		for(int i = 0; i < 4; i++){
			
			Point pt = new Point(e.getPoint().x - 940, e.getPoint().y - 250);
			String color = "";
			if(i == 0)
				color = "Red";
			else if(i == 1)
				color = "Blue";
			else if(i == 2)
				color = "Grey";
			else
				color = "Yellow";
			if(fourMarbleBoundList.get(i).contains(pt)){
				System.out.println(fourMarbleList.get(i).getMarbleColor() + " in 4 free to pick marble list clickerd");
				System.out.println("trying to build gizmo cost " + gizmoBeingBuilt.getCost() + " " + gizmoBeingBuilt.getColor() + " marbles");
				for(int j = 0; j < tempConvertedMarbleList.size(); j++){
					System.out.println("convert gizmo being used has this trigger: " + gizmoPrivateSelected.getTrigger());
					System.out.println("temp Convert Marble List item: " + tempConvertedMarbleList.get(j).getMarbleColor());
					if(gizmoPrivateSelected != null)
					{
						if(gizmoPrivateSelected.getTrigger() == Gizmo.GizmoTgr.BlueMarble && tempConvertedMarbleList.get(j).getMarbleColor() == "Blue"){
							tempConvertedMarbleList.remove(j);
							tempConvertedMarbleList.add(new Marble(color));
							System.out.println("temp Convert Marble replaced with Blue marble");
							break;
						}
						if(gizmoPrivateSelected.getTrigger() == Gizmo.GizmoTgr.GreyMarble && tempConvertedMarbleList.get(j).getMarbleColor() == "Grey"){
							tempConvertedMarbleList.remove(j);
							tempConvertedMarbleList.add(new Marble(color));
							System.out.println("temp Convert Marble replaced with Grey marble");
							break;
						}		
						if(gizmoPrivateSelected.getTrigger() == Gizmo.GizmoTgr.RedMarble && tempConvertedMarbleList.get(j).getMarbleColor() == "Red"){
							tempConvertedMarbleList.remove(j);
							tempConvertedMarbleList.add(new Marble(color));
							System.out.println("temp Convert Marble replaced Red Blue marble");
							break;
						}	
						if(gizmoPrivateSelected.getTrigger() == Gizmo.GizmoTgr.YellowMarble && tempConvertedMarbleList.get(j).getMarbleColor() == "Yellow"){
							tempConvertedMarbleList.remove(j);
							tempConvertedMarbleList.add(new Marble(color));
							System.out.println("temp Convert Marble replaced with Yellow marble");
							break;
						}
						//handle any marble to any marble conversion case
						if((gizmoPrivateSelected.getTrigger() == Gizmo.GizmoTgr.AnyMarble)){
							if(colorTempMarbleClicked != "")
							{
								for(int k = 0; k < tempConvertedMarbleList.size(); k++){
									if(tempConvertedMarbleList.get(k).getMarbleColor() == colorTempMarbleClicked){
										tempConvertedMarbleList.remove(k);
										tempConvertedMarbleList.add(new Marble(fourMarbleList.get(i).getMarbleColor()));
										System.out.println("temp Convert Marble replaced with " + fourMarbleList.get(i).getMarbleColor() + " marble");
										break;
									}
								}
							}
						}
					}	
				}

				repaint();
				DisplayTempConvertedMarbles();
				int matchedMarble = 0;
				for(int j = 0; j < tempConvertedMarbleBoundList.size(); j++){
					if(tempConvertedMarbleList.get(j).getMarbleColor() == gizmoBeingBuilt.getColor())
						matchedMarble++;
					if(matchedMarble == gizmoBeingBuilt.getCost()){
						showFourMarbleList = false;
						System.out.println("dispenser has total of " + marbles.size() + " before switching player's marbles");
						SwitchPlayerMarbles();
						tempConvertedMarbleList.clear();
						tempConvertedMarbleBoundList.clear();
						System.out.println("dispenser has total of " + marbles.size() + " after switching player's marbles");
						recountCurrentPlayerMarbles(p);
						System.out.println("red: " + redCount + " yellow: " + yellowCount + " blue: " + blueCount + " grey: " + greyCount);
						ActOnGizmoClick(gizmoBeingBuilt, gizmoBeingBuiltPosition);
					}	
				}
			}
			//System.out.println(fourMarbleList.get(i).getMarbleColor() + " in 4 free to pick marble list clickerd");
		}
		if(!pickEffectActive && !pickEffectActive2 && !buildEffectActive){
			for (int i = 0; i < convertBoundList.size(); i++) {
			if (convertBoundList.get(i).contains(e.getPoint())) {
				privateGizmoBound.setBounds(convertBoundList.get(i));
				System.out.println("convertBoundList " + i + " clicked " + privateGizmoBound.y);
				gizmoPrivateSelected = p.getConvertGizmos().get(i);
				if (gizmoBeingBuilt != null) {
					System.out.println("I think I clicked on a converted gizmo, what it is then? " + gizmoPrivateSelected.getEffect() 
					+ "  " + gizmoPrivateSelected.getID());
					if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.AnyMarble 
					|| gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.AnyTwoMarble)
						showFourMarbleList = true;
					tryConvertGizmosForQualification();
				}
				break;
			}
			}
			for (int i = 0; i < pickBoundList.size(); i++) {
				if (pickBoundList.get(i).contains(e.getPoint())) {
					privateGizmoBound.setBounds(pickBoundList.get(i));
					gizmoPrivateSelected = p.getPickGizmos().get(i);
					gizmoReaction();
					System.out.println("pickBoundList " + i + " clicked " + privateGizmoBound.y);
				}
			}
			for (int i = 0; i < buildBoundList.size(); i++) {
				if (buildBoundList.get(i).contains(e.getPoint())) {
					privateGizmoBound.setBounds(buildBoundList.get(i));
					gizmoPrivateSelected = p.getBuildGizmos().get(i);
					out.println(gizmoPrivateSelected.getColor() + gizmoPrivateSelected.getCost() + gizmoPrivateSelected.getTrigger());
					gizmoReaction();
					System.out.println("buildBoundList " + i + " clicked " + privateGizmoBound.y);
				}
			}
			for (int i = 0; i < fileBoundList.size(); i++) {
				if (fileBoundList.get(i).contains(e.getPoint())) {
					privateGizmoBound.setBounds(fileBoundList.get(i));
					gizmoPrivateSelected = p.getFileGizmos().get(i);
					out.println(gizmoPrivateSelected.getColor() + gizmoPrivateSelected.getCost() + gizmoPrivateSelected.getTrigger());
					gizmoReaction();
					System.out.println("fileBoundList " + i + " clicked " + privateGizmoBound.y);
				}
			}
		}
		for (int i = 0; i < archiveBoundList.size(); i++) {
			if (archiveBoundList.get(i).contains(e.getPoint())) {
				privateGizmoBound.setBounds(archiveBoundList.get(i));
				Gizmo g = p.getArchivedGizmos().get(i);
				gizmoBeingBuilt = g;
				System.out.println("archiveBoundList " + i + " clicked " + privateGizmoBound.y);
				ActOnGizmoClick(g, 9);
			}
		}
		for(int i = 0; i < 4; i++){
			if(fourMarbleBoundList.get(i).contains(e.getPoint())){
				System.out.println("Going to pick a desired marble");
			}
		}

		// else
		// System.out.println(e.getX() + " , " + e.getY() + " out of bound of any card
		// in display area");

		if(researchMode == 0){
			researching = true;
			if(tier1bound.contains(e.getPoint()))
				researchMode = 1;
			if(tier2bound.contains(e.getPoint()))
				researchMode = 2;
			if(tier3bound.contains(e.getPoint()))
				researchMode = 3;
			if(researchMode > 0)
				AddResearchCards();
		}
		else{
			boolean clearResearchList = false;
			for(int i = 0; i < researchGizmoBoundList.size(); i++){
				if(researchGizmoBoundList.get(i).contains(e.getPoint())){
					gizmoBeingBuilt = researchGizmoList.get(i);
					selectedResearchGizmoIndex = i;
					clearResearchList = false;
					System.out.println("Gizmo to be build selected from research list " + gizmoBeingBuilt.getColor() + " - " + gizmoBeingBuilt.getCost());
				}
			}
			if(fileBound.contains(e.getPoint())){
				System.out.println("might want to file this selected gizmo");
				clearResearchList = false;
				//try to file this gizmo from research list
				ActOnGizmoClick(gizmoBeingBuilt, -1);
			}
			if(buildBound.contains(e.getPoint())){
				System.out.println("might want to build this selected gizmo");
				clearResearchList = false;
				//try to build this gizmo from research list but don't want the function to put the gizmo back to deck
				ActOnGizmoClick(gizmoBeingBuilt, 100);
			}
			
			
			if(x > 1550 && x < 1650 && y > 600 && y < 700){
				clearResearchList = true;
				turnFinishedAlert = true;
			}
			if(turnFinishedAlert)
				clearResearchList = true;
			if(clearResearchList == true){
				System.out.println("Clicked on unrelevant place, clear research gizmos");
				for(int i = researchGizmoList.size() - 1; i >= 0; i--){
					if(researchMode == 1)
						t1Gizmos.add(researchGizmoList.remove(i));
					if(researchMode == 2)
						t2Gizmos.add(researchGizmoList.remove(i));
					if(researchMode == 3)
						t3Gizmos.add(researchGizmoList.remove(i));				
				}
				researchGizmoBoundList.clear();
				researchMode = 0;
				researching = false;
				repaint();
			}
		}		
		
		System.out.println(x + ", " + y);
		if(x > 1700 && x < 1840 && y > 850 && y < 1000 && howtoplay == false){
			howtoplay = true;
		}
		else 
			howtoplay = false;
		repaint();
	}
    private void AddResearchCards(){
		researchGizmoList.clear();
		researchGizmoBoundList.clear();
		Player p = players.get(currentPlayer);
		int power = p.getResearchPower();
		for(int i = 0; i < power; i++){
			if(researchMode == 1)
				researchGizmoList.add(t1Gizmos.remove(t1Gizmos.size() - 1));
			if(researchMode == 2)
				researchGizmoList.add(t2Gizmos.remove(t2Gizmos.size() - 1));
			if(researchMode == 3)
				researchGizmoList.add(t3Gizmos.remove(t3Gizmos.size() - 1));	
			researchGizmoBoundList.add(new Rectangle(1400 + i * 170, 715, 140, 130));			
		}
		System.out.println("adding " + power + " tier " + researchMode + " gizmos to research list for player " + currentPlayer);
	}
	private void setExpandedConvertMarbleBoundList(){
		tempConvertedMarbleBoundList.clear();
		for(int i = 0; i < tempConvertedMarbleList.size(); i++){
			tempConvertedMarbleBoundList.add(new Rectangle(-800 + i * 25, 600, 25,25));
		}
	}

	private void tryConvertGizmosForQualification() {
		if (gizmoBeingBuilt == null)
			return;
		System.out.println("Player picked a gizmo that needs " + gizmoBeingBuilt.getCost() + "  "
				+ gizmoBeingBuilt.getColor() + " marbles");
		System.out.println("selected Convert Gizmo has convert power: " + gizmoPrivateSelected.getTrigger() + " - "
				+ gizmoPrivateSelected.getEffect());
		if (tempConvertedMarbleList.size() == 0) {
			for (int i = 0; i < players.get(currentPlayer).getHeldMarbles().size(); i++){
				tempConvertedMarbleList.add(players.get(currentPlayer).getHeldMarbles().get(i));
				tempConvertedMarbleBoundList.add(new Rectangle(-800 + i * 25, 600, 25,25));
			}
		}
		System.out.println("Now temp marble list has marble number of " + tempConvertedMarbleList.size());
		repaint();
	}

	private void putbackMarble(int num, String color) {
		for (int i = 0; i < num; i++) {
			Marble m = new Marble(color);
			marbles.add(m);
		}
		System.out.println("There are " + marbles.size() + " in the dispenser and energy row");
	}

	private void gizmoReaction(){
		System.out.println("reached gizmo reaction");
		Player p = players.get(currentPlayer);
		if(gizmoPrivateSelected.isTriggered() && gizmoPrivateSelected.getJustBuilt() == false){
			System.out.println("gizmo clicked is ready to be activated");
			if(gizmoPrivateSelected.getType() == Gizmo.GizmoType.PICK && !gizmoPrivateSelected.isTriggeredThisRound()){
				//if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.DrawOne){} it only got 1 effect lol
				Marble m = marbles.remove(marbles.size() - 1);

				gizmoPrivateSelected.untriggered();
				gizmoPrivateSelected.triggeredThisRound(true);
				if(p.spaceForMoreMarble()){
					p.addMarble(m);
					if (m.getMarbleColor() == "Red"){
						redCount++;
					}
					else if (m.getMarbleColor() == "Yellow"){
						yellowCount++;
					}
					else if (m.getMarbleColor() == "Grey"){
						greyCount++;
					}
					else if (m.getMarbleColor() == "Blue"){
						blueCount++;
					}
				}
			}
			else if(gizmoPrivateSelected.getType() == Gizmo.GizmoType.BUILD){
				if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.PickAny
				 && !gizmoPrivateSelected.isTriggeredThisRound()){
					out.println("reached pick any");
					pickEffectActive = true;
					gizmoPrivateSelected.untriggered();
					gizmoPrivateSelected.triggeredThisRound(true);
				}
				else if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.OneVictoryPoint
				 && !gizmoPrivateSelected.isTriggeredThisRound()){
					out.println(p.getVictoryPoints());
					p.addVictoryPoint(1);
					out.println(p.getVictoryPoints());
					gizmoPrivateSelected.untriggered();
					gizmoPrivateSelected.triggeredThisRound(true);
				}
				else if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.TwoVictoryPoints
				 && !gizmoPrivateSelected.isTriggeredThisRound()){
					out.println(p.getVictoryPoints());
					p.addVictoryPoint(2);
					out.println(p.getVictoryPoints());
					gizmoPrivateSelected.untriggered();
					gizmoPrivateSelected.triggeredThisRound(true);
				}
				else if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.PickAnyTwo
				 && !gizmoPrivateSelected.isTriggeredThisRound()){
					out.println("pick any two reached");
					pickEffectActive = true;
					pickEffectActive2 = true;
					gizmoPrivateSelected.untriggered();
					gizmoPrivateSelected.triggeredThisRound(true);
				}
				else if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.FreeT1Build
				 && !gizmoPrivateSelected.isTriggeredThisRound()){
					out.println("building free t1");
					buildEffectActive = true;
					gizmoPrivateSelected.untriggered();
					gizmoPrivateSelected.triggeredThisRound(true);
				}
				// else if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.File
				//  && !gizmoPrivateSelected.isTriggeredThisRound()){
				// 	out.println("building free t1");
				// 	fileEffectActive = true;
				// 	FileBoundClicked = true;
				// 	gizmoPrivateSelected.untriggered();
				// 	gizmoPrivateSelected.triggeredThisRound(true);
				// } i give up on this one

			}
			else if(gizmoPrivateSelected.getType() == Gizmo.GizmoType.FILE){

				if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.DrawOne
				 && !gizmoPrivateSelected.isTriggeredThisRound()){
					Marble m = marbles.remove(marbles.size() - 1);
					
					gizmoPrivateSelected.untriggered();
					gizmoPrivateSelected.triggeredThisRound(true);
					if(p.spaceForMoreMarble()){
						p.addMarble(m);
						if (m.getMarbleColor() == "Red"){
							redCount++;
						}
						else if (m.getMarbleColor() == "Yellow"){
							yellowCount++;
						}
						else if (m.getMarbleColor() == "Grey"){
							greyCount++;
						}
						else if (m.getMarbleColor() == "Blue"){
							blueCount++;
						}
					}
					
				}
				else if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.DrawThree
				 && !gizmoPrivateSelected.isTriggeredThisRound()){
					Marble m = marbles.remove(marbles.size() - 1);
					
					gizmoPrivateSelected.untriggered();
					gizmoPrivateSelected.triggeredThisRound(true);
					if(p.spaceForMoreMarble()){
						p.addMarble(m);
						if (m.getMarbleColor() == "Red"){
							redCount++;
						}
						else if (m.getMarbleColor() == "Yellow"){
							yellowCount++;
						}
						else if (m.getMarbleColor() == "Grey"){
							greyCount++;
						}
						else if (m.getMarbleColor() == "Blue"){
							blueCount++;
						}
					}
					m = marbles.remove(marbles.size() - 1);
					if(p.spaceForMoreMarble()){
						p.addMarble(m);
						if (m.getMarbleColor() == "Red"){
							redCount++;
						}
						else if (m.getMarbleColor() == "Yellow"){
							yellowCount++;
						}
						else if (m.getMarbleColor() == "Grey"){
							greyCount++;
						}
						else if (m.getMarbleColor() == "Blue"){
							blueCount++;
						}
					}
					m = marbles.remove(marbles.size() - 1);
					if(p.spaceForMoreMarble()){
						p.addMarble(m);
						if (m.getMarbleColor() == "Red"){
							redCount++;
						}
						else if (m.getMarbleColor() == "Yellow"){
							yellowCount++;
						}
						else if (m.getMarbleColor() == "Grey"){
							greyCount++;
						}
						else if (m.getMarbleColor() == "Blue"){
							blueCount++;
						}
					} //this way is kind of dumb but it works
					
				}
				else if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.PickAny
				 && !gizmoPrivateSelected.isTriggeredThisRound()){
					pickEffectActive = true;
					gizmoPrivateSelected.untriggered();
					gizmoPrivateSelected.triggeredThisRound(true);
				}
				else if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.OneVictoryPoint
				 && !gizmoPrivateSelected.isTriggeredThisRound()){
					out.println(p.getVictoryPoints());
					p.addVictoryPoint(1);
					out.println(p.getVictoryPoints());
					gizmoPrivateSelected.untriggered();
					gizmoPrivateSelected.triggeredThisRound(true);
				}
				else if(gizmoPrivateSelected.getEffect() == Gizmo.GizmoEffect.TwoVictoryPoints
				 && !gizmoPrivateSelected.isTriggeredThisRound()){
					out.println(p.getVictoryPoints());
					p.addVictoryPoint(2);
					out.println(p.getVictoryPoints());
					gizmoPrivateSelected.untriggered();
					gizmoPrivateSelected.triggeredThisRound(true);
				}
				
			}
			turnFinishedAlert = true;
			repaint();
			return;
		}

	}

	//change this to be like gizmo triggered pick
	private void pickGizmoTriggered(String color){
		System.out.println("reached gizmo triggered");
		Player p = players.get(currentPlayer);
		ArrayList<Gizmo> pickGizmos = p.getPickGizmos();
		//doing pick first cause its the easiest i think
		for(Gizmo g : pickGizmos){
			if(g.getTrigger() == Gizmo.GizmoTgr.PickRed){
				if(color.equals("Red")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil baby"); repaint();
				} 
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.PickBlue){
				if(color.equals("Blue")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil baby2"); repaint();
				} 
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.PickYellow){
				if(color.equals("Yellow")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil baby3"); repaint();
				} 
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.PickGrey){
				if(color.equals("Grey")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil baby4"); repaint();
				} 
			}
			//////////////////////////////////////////////////////////
			else if(g.getTrigger() == Gizmo.GizmoTgr.PickYellowOrRed){
				if(color.equals("Yellow")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil baby5.1"); repaint();
				} 
				else if(color.equals("Red")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil baby5.2"); repaint();
				}
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.PickYellowOrGrey){
				if(color.equals("Yellow")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil baby6.1"); repaint();
				} 
				else if(color.equals("Grey")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil baby6.2"); repaint();
				}
			}	
			else if(g.getTrigger() == Gizmo.GizmoTgr.PickGreyOrBlue){
				if(color.equals("Grey")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil baby7.1"); repaint();
				} 
				else if(color.equals("Blue")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					} //why didnt i make a method for this
					System.out.println("lil baby7.2"); repaint();
				}
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.PickRedOrBlue){
				if(color.equals("Red")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil baby8.1"); repaint();
				} 
				else if(color.equals("Blue")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil baby8.2"); repaint();
				}
			}

			
		}
		repaint();
	}
	
	private void buildGizmoTriggered(String color){
		Player p = players.get(currentPlayer);
		ArrayList<Gizmo> buildGizmos = p.getBuildGizmos();
		for(Gizmo g : buildGizmos){
			if(g.getTrigger() == Gizmo.GizmoTgr.BuildRed){
				
				if(color.equals("Red")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("nle 1"); repaint();
				}
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.BuildBlue){
				if(color.equals("Blue")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("nle2"); repaint();
				} 
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.BuildYellow){
				if(color.equals("Yellow")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("nle3"); repaint();
				} 
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.BuildGrey){
				if(color.equals("Grey")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("nle4"); repaint();
				} 
			}
			//////////////////////////////////////////////////////////
			else if(g.getTrigger() == Gizmo.GizmoTgr.BuildBlueOrRed){
				if(color.equals("Blue")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump5.1"); repaint();
				} 
				else if(color.equals("Red")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump5.2"); repaint();
				}
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.BuildGreyOrYellow){
				if(color.equals("Yellow")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump6.1"); repaint();
				} 
				else if(color.equals("Grey")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump6.2"); repaint();
				}
			}	
			else if(g.getTrigger() == Gizmo.GizmoTgr.BuildBlueOrYellow){
				if(color.equals("Blue")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump7.1"); repaint();
				} 
				else if(color.equals("Yellow")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump7.2"); repaint();
				}
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.BuildGreyOrRed){
				if(color.equals("Red")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump8.1"); repaint();
				} 
				else if(color.equals("Grey")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump8.2"); repaint();
				}
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.BuildYellowOrRed){
				if(color.equals("Yellow")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump9.1"); repaint();
				} 
				else if(color.equals("Red")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump9.2"); repaint();
				}
			}
			else if(g.getTrigger() == Gizmo.GizmoTgr.BuildBlueOrGrey){
				if(color.equals("Blue")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump10.1"); repaint();
				} 
				else if(color.equals("Grey")){
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					System.out.println("lil pump10.2"); repaint();
				}
			}
			//from file
			
			else if(g.getTrigger() == Gizmo.GizmoTgr.BuildFromFile) {
				if(color.equals("from file")) {
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					if(g.getJustBuilt()){
						out.println("just got built");
						g.untriggered();
					}
					System.out.println("pop smoke 1"); repaint();
				}
			}
			//build t2
			else if(g.getTrigger() == Gizmo.GizmoTgr.BuildT2) {
				if(color.equals("build t2")) {
					if(!g.getJustBuilt()); g.triggered();
					if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
					g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
						if(!p.spaceForMoreMarble()){
							g.untriggered();
							turnFinishedAlert = true;
						}
					}
					if(g.getJustBuilt()){
						out.println("just got built");
						g.untriggered();
					}
					System.out.println("pop smoke 1"); repaint();
				}
			}
		}
	}

	private void fileGizmoTriggered(){
		Player p = players.get(currentPlayer);
		ArrayList<Gizmo> fileGizmos = p.getFileGizmos(); 
		System.out.println(p.getFileGizmos().size() +"WHAT THE BOMBORASCLAT");
		for(Gizmo g : fileGizmos){
			out.println("erm what the flip");
			if(g.getTrigger() == Gizmo.GizmoTgr.File){
				if(!g.getJustBuilt()); g.triggered();
				if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
				g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
					if(!p.spaceForMoreMarble()){
						turnFinishedAlert = true;
						g.untriggered();
					}
				}
				out.println("yea uh yeah uh");
			}
		}
	}
	// position possible value: 0 - 8. 0-3 are for tier 1 gizmos, 4-6 for tier 2
	// gizmos, 7-8 for tier 3 gizmos
	// -1 for File gizmo, 9 for archived gizmo being clicked
	private void ActOnGizmoClick(Gizmo g, int position) {
		out.println("hia");
		if (!turnFinishedAlert || buildEffectActive) {
			out.println("bruh monkey");
			tempConvertedMarbleList.clear();
			tempConvertedMarbleBoundList.clear();

			
			Player p = players.get(currentPlayer);
			System.out.println("###ActOnGizmoClick gizmo type " + g.getType());

			int takeThisGizmo = 0;
			// if File is clicked and then another gizmo from level 1/2/3 is clicked, add
			// that gizmo to archive section of player dashboard area

			if(!buildEffectActive){
				if (FileBoundClicked) {
					if (p.spaceForMoreArchive()) {
						System.out.println(
								"Should move this gizmo positioned " + position + " to the archive area of the player");
						p.addArchiveGizmo(g);
						
						FillDisplayDeck(g, position);
						if (p.getArchivedGizmos().size() > 1) {
							int prevTopCard = p.getArchivedGizmos().size() - 2;
							archiveBoundList.get(prevTopCard).setBounds(archiveBoundList.get(prevTopCard).x,
									archiveBoundList.get(prevTopCard).y, 143, 30);
							archiveBoundList.add(new Rectangle(archiveBound.x + 20,
									archiveBound.y + archiveBound.height + 30 * (p.getArchivedGizmos().size() - 1), 143,
									130));
						}
						
						fileGizmoTriggered();
					}
					else{
						repaint();
						return;
					}
					
					turnFinishedAlert = true;
					repaint();
					return;
				}

			}

			switch (g.getColor()) {
				case "Red":
					if (redCount >= g.getCost())
						takeThisGizmo = 1;
					break;
				case "Blue":
					if (blueCount >= g.getCost())
						takeThisGizmo = 1;
					break;
				case "Yellow":
					if (yellowCount >= g.getCost())
						takeThisGizmo = 1;
					break;
				case "Grey":
					if (greyCount >= g.getCost())
						takeThisGizmo = 1;
					break;
				case "Generic":
					if (redCount + blueCount + greyCount + yellowCount >= 7) {
						//UIManager.put("OptionPane.minimumSize",new Dimension(500,500)); 
						int result = JOptionPane.showConfirmDialog(null, inputs, "Enter the amount of each marble you would like to use to build the generic gizmo.", JOptionPane.PLAIN_MESSAGE);
						spentRed = Integer.parseInt(redMarbleInput.getText());
						spentBlue = Integer.parseInt(blueMarbleInput.getText());
						spentGray = Integer.parseInt(grayMarbleInput.getText());
						spentYellow = Integer.parseInt(yellowMarbleInput.getText());
						takeThisGizmo=1;
					}
					
					break;
				
				// case "Generic":
				// 	String genericBuildAmounts;
				// 	JTextField xField = new JTextField(5);
      			// 	JTextField yField = new JTextField(5);
      			// 	add(new JLabel("Red:"));
      			// 	add(xField);
      			// 	add(Box.createHorizontalStrut(15));
      			// 	add(new JLabel("Gray:"));
      			// 	add(yField);
				// 	add(new JLabel("Yellow:"));
					// if ((redCount + blueCount + yellowCount + greyCount) >= g.getCost()) {
					// 	genericBuildAmounts = JOptionPane.showInputDialog(null, "Please enter the amount of each marble you would like to utilize when building the generic");
					// 	int genericRedAmount = Integer.parseInt(genericBuildAmounts.split(" ")[0]);
					// 	int genericBlueAmount = Integer.parseInt(genericBuildAmounts.split(" ")[1]);
					// 	int genericGrayAmount = Integer.parseInt(genericBuildAmounts.split(" ")[2]);
					// 	int genericYellowAmount = Integer.parseInt(genericBuildAmounts.split(" ")[3]);
					// }
			}
			if (takeThisGizmo == 1) {
				g.setJustBuilt(true);
				System.out.println("Player " + p.getName()
						+ " can take this Gizmo card since he has enough to pay for it which cost " + g.getCost() + " "
						+ g.getColor() + " marbles");
				switch (g.getType()) {

					case CONVERT:
					 	out.println("Built Gizmo Turn Finished True");
						turnFinishedAlert = true; // when we implement triggering gizmos, we can just set this to false
													// immediately so there wont be any problems with the turn
						// finishing prematurely
						System.out.println("convert add to convert list for player " + p.getName());
						p.addConvertGizmo(g);
						p.payMarble(g.getCost(), g.getColor());
						putbackMarble(g.getCost(), g.getColor());
						// since you get more than 1 such gizmo, the top one is covered by a new one
						// thus it has a smaller bound
						// this applies to other gizmos as well
						if (p.getConvertGizmos().size() > 1) {
							int prevTopCard = p.getConvertGizmos().size() - 2;
							convertBoundList.get(prevTopCard).setBounds(convertBoundList.get(prevTopCard).x,
									convertBoundList.get(prevTopCard).y, 143, 30);
							convertBoundList.add(new Rectangle(convertBound.x + 20,
									convertBound.y + convertBound.height + 30 * (p.getConvertGizmos().size() - 1), 143,
									130));
						}
						
						players.get(currentPlayer).addVictoryPoint(g.getVictoryPoints());
						System.out.println(g.getEffect());
						repaint();
						break;
					case BUILD:
						turnFinishedAlert = true; out.println("Built Gizmo Turn Finished True");
						System.out.println("convert add to build list for player " + p.getName());
						p.addBuildGizmo(g);
						p.payMarble(g.getCost(), g.getColor());
						putbackMarble(g.getCost(), g.getColor());
						if (p.getBuildGizmos().size() > 1) {
							int prevTopCard = p.getBuildGizmos().size() - 2;
							buildBoundList.get(prevTopCard).setBounds(buildBoundList.get(prevTopCard).x,
									buildBoundList.get(prevTopCard).y, 143, 30);
							buildBoundList.add(new Rectangle(buildBound.x + 20,
									buildBound.y + buildBound.height + 30 * (p.getBuildGizmos().size() - 1), 143, 130));
						}
						
						players.get(currentPlayer).addVictoryPoint(g.getVictoryPoints());
						
						g.setX(buildBound.x + 20);
						g.setY(buildBound.y + buildBound.height + 30 * (p.getBuildGizmos().size() - 1));
						
						repaint();
						break;
					case UPGRADE:
						turnFinishedAlert = true; out.println("Built Gizmo Turn Finished True");
						System.out.println("convert add to upgrade list for player " + p.getName());
						System.out.println(g.getColor());
						p.addUpgradeGizmo(g);
						
						if (g.getColor().equals("Generic")) {
							out.println("Red" + spentRed + " Blue" + spentBlue + " Yellow" + spentYellow + " Gray" + spentGray);
							p.payMarble(spentRed, spentBlue, spentYellow, spentGray);
							putbackMarble(spentRed, "Red");
							putbackMarble(spentYellow, "Yellow");
							putbackMarble(spentGray, "Grey");
							putbackMarble(spentBlue, "Blue");
						} else {
							
							p.payMarble(g.getCost(), g.getColor());
							putbackMarble(g.getCost(), g.getColor());
							
						}
						if (p.getUpgradeGizmos().size() > 1) {
							int prevTopCard = p.getUpgradeGizmos().size() - 2;
							upgradeBoundList.get(prevTopCard).setBounds(upgradeBoundList.get(prevTopCard).x,
									upgradeBoundList.get(prevTopCard).y, 143, 30);
							upgradeBoundList.add(new Rectangle(upgBound.x + 20,
									upgBound.y + upgBound.height + 30 * (p.getUpgradeGizmos().size() - 1), 143, 130));
						}
						
						players.get(currentPlayer).addVictoryPoint(g.getVictoryPoints());

						repaint();
						break;
					case FILE:
						turnFinishedAlert = true; out.println("Built Gizmo Turn Finished True");
						System.out.println("convert add to file list for player " + p.getName());
						p.addFileGizmo(g);
						p.payMarble(g.getCost(), g.getColor());
						putbackMarble(g.getCost(), g.getColor());
						if (p.getFileGizmos().size() > 1) {
							int prevTopCard = p.getFileGizmos().size() - 2;
							fileBoundList.get(prevTopCard).setBounds(fileBoundList.get(prevTopCard).x,
									fileBoundList.get(prevTopCard).y, 143, 30);
							fileBoundList.add(new Rectangle(fileBound.x + 20,
									fileBound.y + fileBound.height + 30 * (p.getFileGizmos().size() - 1), 143, 130));
						}
						
						players.get(currentPlayer).addVictoryPoint(g.getVictoryPoints());

						//if(g.getID() != 112){
							g.setX(fileBound.x + 20);
							g.setY(fileBound.y + fileBound.height + 30 * (p.getFileGizmos().size() - 1));
						//}

						repaint();
						break;
					case PICK:
						turnFinishedAlert = true; out.println("Built Gizmo Turn Finished True");
						System.out.println("convert add to pick list for player " + p.getName());
						p.addPickGizmo(g);
						p.payMarble(g.getCost(), g.getColor());
						putbackMarble(g.getCost(), g.getColor());
						if (p.getPickGizmos().size() > 1) {
							int prevTopCard = p.getPickGizmos().size() - 2;
							pickBoundList.get(prevTopCard).setBounds(pickBoundList.get(prevTopCard).x,
									pickBoundList.get(prevTopCard).y, 143, 30);
							pickBoundList.add(new Rectangle(pickBound.x + 20,
									pickBound.y + pickBound.height + 30 * (p.getPickGizmos().size() - 1), 143, 130));
						}
						
						players.get(currentPlayer).addVictoryPoint(g.getVictoryPoints());
						
						g.setX(pickBound.x + 20);
						g.setY(pickBound.y + pickBound.height + 30 * (p.getPickGizmos().size() - 1));

						repaint();
						break;

				}
				if (position == 9) {
					// should remove it from archive list of player dashboard area
					for (int i = 0; i < p.getArchivedGizmos().size(); i++) {
						if (p.getArchivedGizmos().get(i) == g) {
							System.out.println("$$$$$$$$$$$$$$$$$$$ found matching gizmo from archive list to remove");
							g.setJustBuilt(true);
							p.removeArchiveGizmo(g);
							archiveBoundList.remove(i);
							buildGizmoTriggered("from file");
							if(g.getTier() == 2){
								buildGizmoTriggered("build t2");
							}
							buildEffectActive = false;
							break;
						}
					}
				} else {
					FillDisplayDeck(g, position);
				}

				activeBound.setBounds(0, 0, 0, 0);

				if(g.getEffect() == Gizmo.GizmoEffect.PickAny || g.getEffect() == Gizmo.GizmoEffect.DrawOne ||
				g.getEffect() == Gizmo.GizmoEffect.DrawThree || g.getEffect() == Gizmo.GizmoEffect.PickAnyTwo){
					if(!p.spaceForMoreMarble()){
						return;
					}
				}
				buildGizmoTriggered(g.getColor());
				if(g.getTier() == 2){
					buildGizmoTriggered("build t2");
				}
				buildEffectActive = false;
			} else {
				System.out.println("Player " + p.getName()
						+ " can NOT take this Gizmo card since he can NOT pay for it which cost " + g.getCost() + " "
						+ g.getColor() + " marbles");
				if(p.getConvertGizmos().size() > 0)
					tryConvertGizmos = true;
			}
			if(turnFinishedAlert){
				activeBound.setBounds(0, 0, 0, 0);
				privateGizmoBound.setBounds(0, 0, 0, 0);
			}


			repaint();
		}

	}

	private void FillDisplayDeck(Gizmo g, int position) {
		switch (position) {
			case 0:
			case 1:
			case 2:
			case 3:
				t1Gizmos.remove(position);
				Gizmo lastGizmo = t1Gizmos.get(t1Gizmos.size() - 1);
				t1Gizmos.add(position, lastGizmo);
				t1Gizmos.remove(t1Gizmos.size() - 1);

				break;
			case 4:
			case 5:
			case 6:
				t2Gizmos.remove(position - 4);
				lastGizmo = t2Gizmos.get(t2Gizmos.size() - 1);
				t2Gizmos.add(position - 4, lastGizmo);
				t2Gizmos.remove(t2Gizmos.size() - 1);
				break;
			case 7:
			case 8:
				t3Gizmos.remove(position - 7);
				lastGizmo = t3Gizmos.get(t3Gizmos.size() - 1);
				t3Gizmos.add(position - 7, lastGizmo);
				t3Gizmos.remove(t3Gizmos.size() - 1);
				break;
			case 100:
				researchGizmoList.remove(selectedResearchGizmoIndex);
				break;
		}
	}

	private boolean GameOver(){
		for(int i = 0; i < players.size(); i++){
			Player p = players.get(i);
			int totalGizmos = 0;
			totalGizmos = p.getBuildGizmos().size() + p.getConvertGizmos().size() + p.getFileGizmos().size() + p.getPickGizmos().size() + p.getUpgradeGizmos().size();
			System.out.println(p.getName() + " has gizmos of " + totalGizmos);// + " and " + nLevel3Gizmos + " level III gizmos");
			if(totalGizmos >= 16)
				return true;
			int nLevel3Gizmos = 0;
			for(int j = 0; j < p.getUpgradeGizmos().size(); j++){
				if(p.getUpgradeGizmos().get(j).getCost() > 3)
					nLevel3Gizmos++;
				if(nLevel3Gizmos >= 4)
					return true;
			}
			for(int j = 0; j < p.getConvertGizmos().size(); j++){
				if(p.getConvertGizmos().get(j).getCost() > 3)
					nLevel3Gizmos++;
				if(nLevel3Gizmos >= 4)
					return true;
			}
			for(int j = 0; j < p.getFileGizmos().size(); j++){
				if(p.getFileGizmos().get(j).getCost() > 3)
					nLevel3Gizmos++;
				if(nLevel3Gizmos >= 4)
					return true;
			}
			for(int j = 0; j < p.getBuildGizmos().size(); j++){
				if(p.getBuildGizmos().get(j).getCost() > 3)
					nLevel3Gizmos++;
				if(nLevel3Gizmos >= 4)
					return true;
			}
			
		}
		return false;
	}
	private void recountCurrentPlayerMarbles(Player p){

		redCount = yellowCount = blueCount = greyCount = 0;
		for (int i = 0; i < p.getHeldMarbles().size(); i++) {
			Marble m = p.getHeldMarbles().get(i);
			System.out.println("Player " + p.getName() + " marble " + m.getMarbleColor());
			if (m.getMarbleColor() == "Red")
				redCount++;
			else if (m.getMarbleColor() == "Yellow")
				yellowCount++;
			else if (m.getMarbleColor() == "Blue")
				blueCount++;
			else
				greyCount++;
		}
		
	}
	private void NextPlayer() {
		ArrayList<Gizmo> fileGizmos = players.get(currentPlayer).getFileGizmos();
		ArrayList<Gizmo> buildGizmos = players.get(currentPlayer).getBuildGizmos();
		ArrayList<Gizmo> pickGizmos = players.get(currentPlayer).getPickGizmos();
		if(!turnFinishedAlert || pickEffectActive || pickEffectActive2 || buildEffectActive){
			JOptionPane.showMessageDialog(null, "Please complete an action before completing your turn."); 
			return;
		}
		for(Gizmo gizmo : fileGizmos){
			if(gizmo.isTriggered()){
				out.println(gizmo.getID());
				JOptionPane.showMessageDialog(null, "Please trigger your gizmos outlined with a red box before proceeding.");
				return;
			}
			gizmo.untriggered();
			gizmo.setJustBuilt(false);
			gizmo.triggeredThisRound(false);
		}
		for(Gizmo gizmo : buildGizmos){
			if(gizmo.isTriggered()){
				out.println(gizmo.getID());
				out.println(gizmo.getJustBuilt());

				JOptionPane.showMessageDialog(null, "Please trigger your gizmos outlined with a red box before proceeding.");
				return;
			}
			gizmo.untriggered();
			gizmo.setJustBuilt(false);
			gizmo.triggeredThisRound(false);
		}
		for(Gizmo gizmo : pickGizmos){
			if(gizmo.isTriggered()){
				out.println(gizmo.getID());
				JOptionPane.showMessageDialog(null, "Please trigger your gizmos outlined with a red box before proceeding.");
				return;
			}
			gizmo.untriggered();
			gizmo.setJustBuilt(false);
			gizmo.triggeredThisRound(false);
		}
		for(Gizmo gizmo : pickGizmos){
			if(gizmo.isTriggered()){
				out.println(gizmo.getID());
				JOptionPane.showMessageDialog(null, "Please complete an action before completing your turn.");
				return;
			}
			gizmo.untriggered();
			gizmo.setJustBuilt(false);
			gizmo.triggeredThisRound(false);
		}
		
		turnFinishedAlert = false;
		pickEffectActive = false;
		pickEffectActive2 = false;
		buildEffectActive = false;
		showFourMarbleList = false;

		tryConvertGizmos = false;
		selectedResearchGizmoIndex = -1;
		Player p = players.get(currentPlayer);
		gizmoBeingBuilt = null;
		gizmoPrivateSelected = null;
		usedPrivateGizmoThisRound.clear();
		tempConvertedMarbleList.clear();
		currentPlayer++;
		currentPlayer = currentPlayer % 4;
		if(currentPlayer == 0){
			currentRound++;
			System.out.println("Round " + currentRound + " we can check if there is a winner");
			if(GameOver()){
				System.out.println("Game Over");
				bGameOver = true;
				repaint();
			}
		}
		p = players.get(currentPlayer);
		System.out.println("Next player: " + p.getName());
		recountCurrentPlayerMarbles(p);


		// System.out.println("Current player: " + p.getName() + " has " +
		// p.getHeldMarbles().size() + " marbles");
		// System.out.println("Red: " + redCount + " Yellow: " + yellowCount + " Blue: "
		// + blueCount + " Grey: " + greyCount);

		activeBound.setBounds(0, 0, 0, 0);
		privateGizmoBound.setBounds(0, 0, 0, 0);
		buildBoundList.clear();
		if (p.getBuildGizmos().size() > 1) {
			for (int i = 0; i < p.getBuildGizmos().size(); i++) {
				if (i < p.getBuildGizmos().size() - 1)
					buildBoundList
							.add(new Rectangle(buildBound.x + 20, buildBound.y + buildBound.height + i * 30, 143, 30));
				else
					buildBoundList
							.add(new Rectangle(buildBound.x + 20, buildBound.y + buildBound.height + i * 30, 143, 130));
			}
		} else
			buildBoundList.add(new Rectangle(buildBound.x + 20, buildBound.y + buildBound.height, 143, 130));

		pickBoundList.clear();
		if (p.getPickGizmos().size() > 1) {
			for (int i = 0; i < p.getPickGizmos().size(); i++) {
				if (i < p.getPickGizmos().size() - 1)
					pickBoundList
							.add(new Rectangle(pickBound.x + 20, pickBound.y + pickBound.height + i * 30, 143, 30));
				else
					pickBoundList
							.add(new Rectangle(pickBound.x + 20, pickBound.y + pickBound.height + i * 30, 143, 130));
			}
		} else
			pickBoundList.add(new Rectangle(pickBound.x + 20, pickBound.y + pickBound.height, 143, 130));
		fileBoundList.clear();
		if (p.getFileGizmos().size() > 1) {
			for (int i = 0; i < p.getFileGizmos().size(); i++) {
				if (i < p.getFileGizmos().size() - 1)
					fileBoundList
							.add(new Rectangle(fileBound.x + 20, fileBound.y + fileBound.height + i * 30, 143, 30));
				else
					fileBoundList
							.add(new Rectangle(fileBound.x + 20, fileBound.y + fileBound.height + i * 30, 143, 130));
			}
		} else
			fileBoundList.add(new Rectangle(fileBound.x + 20, fileBound.y + fileBound.height, 143, 130));

		upgradeBoundList.clear();
		if (p.getUpgradeGizmos().size() > 1) {
			for (int i = 0; i < p.getUpgradeGizmos().size(); i++) {
				if (i < p.getUpgradeGizmos().size() - 1)
					upgradeBoundList
							.add(new Rectangle(upgBound.x + 20, upgBound.y + upgBound.height + i * 30, 143, 30));
				else
					upgradeBoundList
							.add(new Rectangle(upgBound.x + 20, upgBound.y + upgBound.height + i * 30, 143, 130));
			}
		} else
			upgradeBoundList.add(new Rectangle(upgBound.x + 20, upgBound.y + upgBound.height, 143, 130));
		convertBoundList.clear();
		if (p.getConvertGizmos().size() > 1) {
			for (int i = 0; i < p.getConvertGizmos().size(); i++) {
				if (i < p.getConvertGizmos().size() - 1)
					convertBoundList.add(
							new Rectangle(convertBound.x + 20, convertBound.y + convertBound.height + i * 30, 143, 30));
				else
					convertBoundList.add(new Rectangle(convertBound.x + 20,
							convertBound.y + convertBound.height + i * 30, 143, 130));
			}
		} else
			convertBoundList.add(new Rectangle(convertBound.x + 20, convertBound.y + convertBound.height, 143, 130));

		archiveBoundList.clear();
		if (p.getArchivedGizmos().size() > 1) {
			for (int i = 0; i < p.getArchivedGizmos().size(); i++) {
				if (i < p.getArchivedGizmos().size() - 1)
					archiveBoundList.add(
							new Rectangle(archiveBound.x + 20, archiveBound.y + archiveBound.height + i * 30, 143, 30));
				else
					archiveBoundList.add(new Rectangle(archiveBound.x + 20,
							archiveBound.y + archiveBound.height + i * 30, 143, 130));
			}
		} else
			archiveBoundList.add(new Rectangle(archiveBound.x + 20, archiveBound.y + archiveBound.height, 143, 130));
		// System.out.println("Coverted list: " + convertBoundList.size());
		// System.out.println("Build list: " + buildBoundList.size());
		// System.out.println("File list: " + fileBoundList.size());
		// System.out.println("Upgrade list: " + upgradeBoundList.size());
		// System.out.println("Pick list: " + pickBoundList.size());
		// System.out.println("Archive list: " + archiveBoundList.size());
		FileBoundClicked = false;

		ArrayList<Gizmo> upgradeGizmos = p.getUpgradeGizmos();
		for(Gizmo g : upgradeGizmos){
			if(g.getColor().equals("Generic") && g.getEffect() == Gizmo.GizmoEffect.VictoryPointsFromTokenCount){
				int quotient = p.getVictoryPoints() / 5;
				int remainder = p.getVictoryPoints() % 5;
				int genericVictoryPoints = quotient + remainder;
				p.addVictoryPoint(genericVictoryPoints);
				
				JOptionPane.showMessageDialog(null, p.getName() + " will receive  " 
				+ genericVictoryPoints + " victory points from a victory point type wild gizmo!");
				//p.addVictoryPoint(p.getVictoryPoints() / 5 + p.getVictoryPoints % 5);
			}
		}
		for(Gizmo g : upgradeGizmos){
			if(g.getColor().equals("Generic") && g.getEffect() == Gizmo.GizmoEffect.VictoryPointsFromTokenCount){
				int redVP = p.getRedMarbles();
				int blueVP = p.getBlueMarbles();
				int yellowVP = p.getYellowMarbles();
				int greyVP = p.getGrayMarbles();
				p.addVictoryPoint(redVP + blueVP + yellowVP + greyVP);
				
				JOptionPane.showMessageDialog(null, p.getName() + " will receive  " 
				+ (redVP + blueVP + yellowVP + greyVP) + " victory points from a marble type wild gizmo!");
				//p.addVictoryPoint(p.getVictoryPoints() / 5 + p.getVictoryPoints % 5);
			}
		}
	}

	//so that when they look at the board after the game ends they can freely switch between players
	private void GameOverNextPlayer() {
		
		turnFinishedAlert = false;
		pickEffectActive = false;
		tryConvertGizmos = false;
		selectedResearchGizmoIndex = -1;
		Player p = players.get(currentPlayer);
		gizmoBeingBuilt = null;
		gizmoPrivateSelected = null;
		usedPrivateGizmoThisRound.clear();
		tempConvertedMarbleList.clear();
		currentPlayer++;
		currentPlayer = currentPlayer % 4;
		if(currentPlayer == 0){
			currentRound++;
			System.out.println("Round " + currentRound + " we can check if there is a winner");
			if(GameOver()){
				System.out.println("Game Over");
				bGameOver = true;
				repaint();
			}
		}
		p = players.get(currentPlayer);
		System.out.println("Next player: " + p.getName());
		recountCurrentPlayerMarbles(p);


		// System.out.println("Current player: " + p.getName() + " has " +
		// p.getHeldMarbles().size() + " marbles");
		// System.out.println("Red: " + redCount + " Yellow: " + yellowCount + " Blue: "
		// + blueCount + " Grey: " + greyCount);

		activeBound.setBounds(0, 0, 0, 0);
		privateGizmoBound.setBounds(0, 0, 0, 0);
		buildBoundList.clear();
		if (p.getBuildGizmos().size() > 1) {
			for (int i = 0; i < p.getBuildGizmos().size(); i++) {
				if (i < p.getBuildGizmos().size() - 1)
					buildBoundList
							.add(new Rectangle(buildBound.x + 20, buildBound.y + buildBound.height + i * 30, 143, 30));
				else
					buildBoundList
							.add(new Rectangle(buildBound.x + 20, buildBound.y + buildBound.height + i * 30, 143, 130));
			}
		} else
			buildBoundList.add(new Rectangle(buildBound.x + 20, buildBound.y + buildBound.height, 143, 130));

		pickBoundList.clear();
		if (p.getPickGizmos().size() > 1) {
			for (int i = 0; i < p.getPickGizmos().size(); i++) {
				if (i < p.getPickGizmos().size() - 1)
					pickBoundList
							.add(new Rectangle(pickBound.x + 20, pickBound.y + pickBound.height + i * 30, 143, 30));
				else
					pickBoundList
							.add(new Rectangle(pickBound.x + 20, pickBound.y + pickBound.height + i * 30, 143, 130));
			}
		} else
			pickBoundList.add(new Rectangle(pickBound.x + 20, pickBound.y + pickBound.height, 143, 130));
		fileBoundList.clear();
		if (p.getFileGizmos().size() > 1) {
			for (int i = 0; i < p.getFileGizmos().size(); i++) {
				if (i < p.getFileGizmos().size() - 1)
					fileBoundList
							.add(new Rectangle(fileBound.x + 20, fileBound.y + fileBound.height + i * 30, 143, 30));
				else
					fileBoundList
							.add(new Rectangle(fileBound.x + 20, fileBound.y + fileBound.height + i * 30, 143, 130));
			}
		} else
			fileBoundList.add(new Rectangle(fileBound.x + 20, fileBound.y + fileBound.height, 143, 130));

		upgradeBoundList.clear();
		if (p.getUpgradeGizmos().size() > 1) {
			for (int i = 0; i < p.getUpgradeGizmos().size(); i++) {
				if (i < p.getUpgradeGizmos().size() - 1)
					upgradeBoundList
							.add(new Rectangle(upgBound.x + 20, upgBound.y + upgBound.height + i * 30, 143, 30));
				else
					upgradeBoundList
							.add(new Rectangle(upgBound.x + 20, upgBound.y + upgBound.height + i * 30, 143, 130));
			}
		} else
			upgradeBoundList.add(new Rectangle(upgBound.x + 20, upgBound.y + upgBound.height, 143, 130));
		convertBoundList.clear();
		if (p.getConvertGizmos().size() > 1) {
			for (int i = 0; i < p.getConvertGizmos().size(); i++) {
				if (i < p.getConvertGizmos().size() - 1)
					convertBoundList.add(
							new Rectangle(convertBound.x + 20, convertBound.y + convertBound.height + i * 30, 143, 30));
				else
					convertBoundList.add(new Rectangle(convertBound.x + 20,
							convertBound.y + convertBound.height + i * 30, 143, 130));
			}
		} else
			convertBoundList.add(new Rectangle(convertBound.x + 20, convertBound.y + convertBound.height, 143, 130));

		archiveBoundList.clear();
		if (p.getArchivedGizmos().size() > 1) {
			for (int i = 0; i < p.getArchivedGizmos().size(); i++) {
				if (i < p.getArchivedGizmos().size() - 1)
					archiveBoundList.add(
							new Rectangle(archiveBound.x + 20, archiveBound.y + archiveBound.height + i * 30, 143, 30));
				else
					archiveBoundList.add(new Rectangle(archiveBound.x + 20,
							archiveBound.y + archiveBound.height + i * 30, 143, 130));
			}
		} else
			archiveBoundList.add(new Rectangle(archiveBound.x + 20, archiveBound.y + archiveBound.height, 143, 130));
		// System.out.println("Coverted list: " + convertBoundList.size());
		// System.out.println("Build list: " + buildBoundList.size());
		// System.out.println("File list: " + fileBoundList.size());
		// System.out.println("Upgrade list: " + upgradeBoundList.size());
		// System.out.println("Pick list: " + pickBoundList.size());
		// System.out.println("Archive list: " + archiveBoundList.size());
		FileBoundClicked = false;

	}
	

	private void SwitchPlayerMarbles(){
		Player p = players.get(currentPlayer);
		for(int i = 0; i < p.getHeldMarbles().size(); i++){
			putbackMarble(1, p.getHeldMarbles().get(i).getMarbleColor());
		}
		p.ClearHeldMarbles();
		for(int i = 0; i < tempConvertedMarbleList.size(); i++){
			p.addMarble(tempConvertedMarbleList.get(i));
			for(int j = marbles.size() - 1; j >= 0; j--){
				if(marbles.get(j).getMarbleColor() == tempConvertedMarbleList.get(i).getMarbleColor())
				{
					marbles.remove(j);
					break;
				}
			}
		}


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

	@Override
	public void mousePressed(MouseEvent e) {

	}
}