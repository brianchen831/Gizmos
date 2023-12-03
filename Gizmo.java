
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.util.*;

import javax.imageio.ImageIO;

public class Gizmo {

	public enum GizmoType {
		CONVERT,
		PICK,
		BUILD,
		FILE,
		UPGRADE
	}

	public enum GizmoTgr {
		none,
		GreyMarble,
		RedMarble,
		YellowMarble,
		BlueMarble,
		BlueAndBlueMarble,
		GreyAndGreyMarble,
		YellowAndYellowMarble,
		RedAndRedMarble,
		RedOrGrey,
		BlueOrYellow,
		AnyMarble,

		PickGrey,
		PickRed,
		PickYellow,
		PickBlue,
		PickYellowOrRed,
		PickYellowOrGrey,
		PickYellowOrBlue,
		PickRedOrGrey,
		PickRedOrBlue,
		PickGreyOrBlue,
		PickAny,

		BuildGrey,
		BuildRed,
		BuildYellow,
		BuildBlue,
		BuildYellowOrGrey,
		BuildYellowOrRed,
		BuildBlueOrGrey,
		BuildBlueOrRed,
		BuildBlueOrYellow,
		BuildGreyOrRed,
		BuildGreyOrYellow,
		BuildFromFile,
		BuildFromFileII,
		BuildFromResearch,
		BuildT1,
		BuildT2,
		BuildT3,

		File,
	}

	public enum GizmoEffect{
		AnyMarble,
		DrawOne,
		AnyTwoMarble,
		TwoYellowMarble,
		TwoRedMarble,
		TwoGreyMarble,
		TwoBlueMarble,
		TwoYellowOrRed,
		TwoYellowOrGrey,
		TwoYellowOrBlue,
		TwoRedOrGrey,
		TwoRedOrBlue,
		TwoGreyOrBlue,
		
		PickAny,
		PickAnyTwo,
		DrawThree,
		FourMarble,
		OneMarbleOneResearch,
		OneMarbleOneArchive,
		OneVictoryPoint,
		TwoVictoryPoints,
		TwoMarbleOneFileTwoResearch,

		DiscountMarbleWhenResearchBuilt,
		DiscountMarbleWhenFileBuilt,
		DiscountMarbleWhenT2Built,
		DiscountMarbleWhenT1Built,
		FreeT1Build,

	

		Research,
		File,

		RestrictFile,
		RestrictBuild,
		RestrictResearch,

	}



	private BufferedImage gizmoImage;
	private int tier;
	private String color;
	private int cost; 
	private GizmoType type;	 // file/convert/upgrade..., very left
	private GizmoTgr trigger; // specific trigger, left side, use enum
	private GizmoEffect effect; // right side
	private int victoryPoint;
	private static int IDNum;
	private int ID;

	private int x;
	private int y;

	private boolean triggered = false;

	public Gizmo(BufferedImage gizmoImage, int t){ //index will be used to uniquely distinguish each Gizmo card per sheet 1 and sheet 2

	 	IDNum++;
	    ID = IDNum;
		tier = t;
		this.gizmoImage = RoundCorners.makeRoundedCorner(gizmoImage, 70);
		trigger = null;
		color = ClosestColorFinder.getColorName(new Color(gizmoImage.getRGB(4, 275)));
        // if (temp=="Gray") { color="Grey"; System.out.println("Grey");}
		// else if (temp=="Red") { color="Red"; System.out.println("Red");}
		// else if (temp=="Yellow") { color="Yellow"; System.out.println("Yellow");}
		// else if (temp=="Blue") { color="Blue"; System.out.println("Blue");}
		victoryPoint = 1;  //default to 1 unless not
		cost = 1; //default to 1 unless not
		AssignProperties();
	} 

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int posX){
		x = posX;
	}
	public void setY(int posY){
		y = posY;
	}
	public void triggered(){
		triggered = true;
	}
	public void untriggered(){
		triggered = false;
	}
	public boolean isTriggered(){
		return triggered;
	}

	public BufferedImage getImage() {

		return gizmoImage;
	}

	public String getColor(){
		return color;
	}
	public GizmoType getType(){
		return type;
	}
	public GizmoTgr getTrigger(){
		return trigger;
	}
	public GizmoEffect getEffect(){
		return effect;
	}
	public int getCost(){
		return cost;
	}
	public int getVictoryPoints(){
		return victoryPoint;
	}

	public int getID(){
		return ID;
	}
	
	public int getTier(){
		return tier;
	}
	private void AssignProperties(){
		
		// switch (ID) {
        //     case 0:  System.out.println(ID + "  tier: " + tier);
        //              break; 
		// 	default: System.out.println(ID + " Strange ID unrecognized");
        //              break;
		// }
		// System.out.println(ID + "  tier: " + tier);

		switch(ID)
	    {
			
			case 110:
			case 111:
			case 112:
				//starting gizmo 
				type = GizmoType.FILE;
				effect = GizmoEffect.DrawOne;
				break;
			case 1:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.YellowMarble;
				effect = GizmoEffect.AnyMarble;
				break;
			case 2:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.RedMarble;
				effect = GizmoEffect.AnyMarble;
				break;
			case 3:
				type = GizmoType.CONVERT;
				effect = GizmoEffect.AnyMarble;
				trigger = GizmoTgr.GreyMarble;
				break;
			case 4:
				type = GizmoType.CONVERT;
				effect = GizmoEffect.AnyMarble;
				trigger = GizmoTgr.BlueMarble;
				break;
			case 5:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.RedMarble;
				effect = GizmoEffect.AnyMarble;
				break;
			case 6:
				type = GizmoType.CONVERT;			
				trigger = GizmoTgr.YellowMarble;
				effect = GizmoEffect.AnyMarble;				
				break;
			case 7:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.BlueMarble;
				effect = GizmoEffect.AnyMarble;
				break;
			case 8:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.GreyMarble;
				effect = GizmoEffect.AnyMarble;
				break;								
			case 9:
				type = GizmoType.PICK;
				effect = GizmoEffect.DrawOne;
				trigger = GizmoTgr.PickBlue;
				break;
			case 10:
				trigger = GizmoTgr.PickRed;
				type = GizmoType.PICK;
				effect = GizmoEffect.DrawOne;
				break;
			case 11:
				trigger = GizmoTgr.PickYellow;
				type = GizmoType.PICK;
				effect = GizmoEffect.DrawOne;
				break;
			case 12:
				trigger = GizmoTgr.PickGrey;
				type = GizmoType.PICK;
				effect = GizmoEffect.DrawOne;
				break;
			case 13:
				type = GizmoType.PICK;
				trigger = GizmoTgr.PickYellow;
				effect = GizmoEffect.DrawOne;
				break;
			case 14:
				type = GizmoType.PICK;
				trigger = GizmoTgr.PickGrey;
				effect = GizmoEffect.DrawOne;
				break;
			case 15:
				type = GizmoType.PICK;
				trigger = GizmoTgr.PickBlue;
				effect = GizmoEffect.DrawOne;
				break;
			case 16:
				type = GizmoType.PICK;
				trigger = GizmoTgr.PickRed;
				effect = GizmoEffect.DrawOne;
				break;
			case 17:
				type = GizmoType.FILE;
				trigger = GizmoTgr.File;
				effect = GizmoEffect.PickAny;
				break;
			case 18:
				type = GizmoType.FILE;
				trigger = GizmoTgr.File;
				effect = GizmoEffect.PickAny;
				break;
			case 19:
				type = GizmoType.FILE;
				trigger = GizmoTgr.File;
				effect = GizmoEffect.PickAny;
				break;
			case 20:
				type = GizmoType.FILE;
				trigger = GizmoTgr.File;
				effect = GizmoEffect.PickAny;
				break;
			case 21:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.OneMarbleOneResearch;
				break;
			case 22:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.OneMarbleOneResearch;
				break;
			case 23:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.OneMarbleOneResearch;
				break;
			case 24:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.OneMarbleOneResearch;
				break;
			case 25:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.OneMarbleOneArchive;
				break;
			case 26:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.OneMarbleOneArchive;
				break;
			case 27:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.OneMarbleOneArchive;
				break;
			case 28:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.OneMarbleOneArchive;
				break;
			case 29:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlue;
				effect = GizmoEffect.PickAny;
				break;
			case 30:
				trigger = GizmoTgr.BuildYellow;
				type = GizmoType.BUILD;
				effect = GizmoEffect.PickAny;
				break;
			case 31:
				trigger = GizmoTgr.BuildGrey;
				type = GizmoType.BUILD;
				effect = GizmoEffect.PickAny;
				break;
			case 32:
				trigger = GizmoTgr.BuildRed;
				type = GizmoType.BUILD;
				effect = GizmoEffect.PickAny;
				break;
			case 33:
				trigger = GizmoTgr.BuildRed;
				type = GizmoType.BUILD;
				effect = GizmoEffect.OneVictoryPoint;
				break;
			case 34:
				trigger = GizmoTgr.BuildGrey;
				type = GizmoType.BUILD;
				effect = GizmoEffect.OneVictoryPoint;
				break;
			case 35:
				trigger = GizmoTgr.BuildYellow;
				type = GizmoType.BUILD;
				effect = GizmoEffect.OneVictoryPoint;
				break;
			case 36:
				trigger = GizmoTgr.BuildBlue;
				type = GizmoType.BUILD;
				effect = GizmoEffect.OneVictoryPoint;
				break;
			case 37:
				//TIER TWOS NOW
				trigger = GizmoTgr.none;
				type = GizmoType.UPGRADE;
				effect = GizmoEffect.TwoMarbleOneFileTwoResearch;
				cost = 3;
				victoryPoint = 3;
				break;
			case 38:
				trigger = GizmoTgr.none;
				type = GizmoType.UPGRADE;
				effect = GizmoEffect.TwoMarbleOneFileTwoResearch;
				cost = 3;
				victoryPoint = 3;
				break;
			case 39:
				trigger = GizmoTgr.none;
				type = GizmoType.UPGRADE;
				effect = GizmoEffect.TwoMarbleOneFileTwoResearch;
				cost = 3;
				victoryPoint = 3;
				break;
			case 40:
				trigger = GizmoTgr.none;
				type = GizmoType.UPGRADE;
				effect = GizmoEffect.TwoMarbleOneFileTwoResearch;
				cost = 3;
				victoryPoint = 3;
				break;
			case 41:
				trigger = GizmoTgr.BuildFromFile;
				type = GizmoType.BUILD;
				effect = GizmoEffect.PickAnyTwo;
				cost = 3;
				victoryPoint = 3;
				break;
			case 42:
				trigger = GizmoTgr.BuildFromFile;
				type = GizmoType.BUILD;
				effect = GizmoEffect.PickAnyTwo;
				cost = 3;
				victoryPoint = 3;
				break;
			case 43:
				trigger = GizmoTgr.BuildFromFile;
				type = GizmoType.BUILD;
				effect = GizmoEffect.PickAnyTwo;
				cost = 3;
				victoryPoint = 3;
				break;
			case 44:
				trigger = GizmoTgr.BuildFromFile;
				type = GizmoType.BUILD;
				effect = GizmoEffect.PickAnyTwo;
				cost = 3;
				victoryPoint = 3;
				break;
			case 45:
				trigger = GizmoTgr.PickYellowOrRed;
				type = GizmoType.PICK;
				effect = GizmoEffect.DrawOne;
				cost = 2;
				victoryPoint = 2;
				break;
			case 46:
				trigger = GizmoTgr.PickYellowOrGrey;
				type = GizmoType.PICK;
				effect = GizmoEffect.DrawOne;
				cost = 2;
				victoryPoint = 2;
				break;
			case 47:
				trigger = GizmoTgr.PickGreyOrBlue;
				type = GizmoType.PICK;
				effect = GizmoEffect.DrawOne;
				cost = 2;
				victoryPoint = 2;
				break;
			case 48:
				trigger = GizmoTgr.PickRedOrBlue;
				type = GizmoType.PICK;
				effect = GizmoEffect.DrawOne;
				cost = 2;
				victoryPoint = 2;
				break;
			case 49:
				trigger = GizmoTgr.BlueAndBlueMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.AnyTwoMarble;
				cost = 2;
				victoryPoint = 2;
				break;
			case 50:
				trigger = GizmoTgr.GreyAndGreyMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.AnyTwoMarble;
				cost = 2;
				victoryPoint = 2;
				break;
			case 51:
				trigger = GizmoTgr.YellowAndYellowMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.AnyTwoMarble;
				cost = 2;
				victoryPoint = 2;
				break;
			case 52:
				trigger = GizmoTgr.RedAndRedMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.AnyTwoMarble;
				cost = 2;
				victoryPoint = 2;
				break;
			case 53:
				trigger = GizmoTgr.YellowMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.TwoYellowMarble;
				cost = 3;
				victoryPoint = 3;
				break;
			case 54:
				trigger = GizmoTgr.RedMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.TwoRedMarble;
				cost = 3;
				victoryPoint = 3;
				break;
			case 55:
				trigger = GizmoTgr.BlueMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.TwoBlueMarble;
				cost = 3;
				victoryPoint = 3;
				break;
			case 56:
				trigger = GizmoTgr.GreyMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.TwoGreyMarble;
				cost = 3;
				victoryPoint = 3;
				break;
			case 57:
				trigger = GizmoTgr.RedMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.TwoRedMarble;
				cost = 3;
				victoryPoint = 3;
				break;
			case 58:
				trigger = GizmoTgr.YellowMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.TwoYellowMarble;
				cost = 3;
				victoryPoint = 3;
				break;
			case 59:
				trigger = GizmoTgr.GreyMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.TwoGreyMarble;
				cost = 3;
				victoryPoint = 3;
				break;
			case 60:
				trigger = GizmoTgr.BlueMarble;
				type = GizmoType.CONVERT;
				effect = GizmoEffect.TwoBlueMarble;
				cost = 3;
				victoryPoint = 3;
				break;
			case 61:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlueOrRed;
				effect = GizmoEffect.AnyMarble;
				cost = 2;
				victoryPoint = 2;
				break;
			case 62:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildGreyOrYellow;
				effect = GizmoEffect.AnyMarble;
				cost = 2;
				victoryPoint = 2;
				break;
			case 63:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlueOrYellow;
				effect = GizmoEffect.AnyMarble;
				cost = 2;
				victoryPoint = 2;
				break;
			case 64:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildGreyOrRed;
				effect = GizmoEffect.AnyMarble;
				cost = 2;
				victoryPoint = 2;
				break;
			case 65:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildYellowOrRed;
				effect = GizmoEffect.AnyMarble;
				cost = 2;
				victoryPoint = 2;
				break;
			case 66:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildYellowOrRed;
				effect = GizmoEffect.AnyMarble;
				cost = 2;
				victoryPoint = 2;
			case 67:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlueOrGrey;
				effect = GizmoEffect.AnyMarble;
				cost = 2;
				victoryPoint = 2;
				break;
			case 68:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlueOrGrey;
				effect = GizmoEffect.AnyMarble;
				cost = 2;
				victoryPoint = 2;
				break;
			case 69:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlueOrYellow;
				effect = GizmoEffect.OneVictoryPoint;
				cost = 3;
				victoryPoint = 3;
				break;
			case 70:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildGreyOrRed;
				effect = GizmoEffect.OneVictoryPoint;
				cost = 3;
				victoryPoint = 3;
				break;
			case 71:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildYellowOrGrey;
				effect = GizmoEffect.OneVictoryPoint;
				cost = 3;
				victoryPoint = 3;
				break;
			case 72:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlueOrRed;
				effect = GizmoEffect.OneVictoryPoint;
				cost = 3;
				victoryPoint = 3;
				break;
			case 73:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.FourMarble;
				cost = 4;
				victoryPoint = 4;
				break;
			case 74:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.FourMarble;
				cost = 4;
				victoryPoint = 4;
				break;
			case 75:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.AnyMarble;
				effect = GizmoEffect.AnyMarble;
				cost = 4;
				victoryPoint = 4;
				break;
			case 76:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.AnyMarble;
				effect = GizmoEffect.AnyMarble;
				cost = 4;
				victoryPoint = 4;
				break;
			case 77:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.BlueOrYellow;
				effect = GizmoEffect.TwoYellowOrBlue;
				cost = 5;
				victoryPoint = 5;
				break;
			case 78:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.RedOrGrey;
				effect = GizmoEffect.TwoRedOrGrey;
				cost = 5;
				victoryPoint = 5;
				break;
			case 79:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildFromFile;
				effect = GizmoEffect.TwoVictoryPoints;
				cost = 5;
				victoryPoint = 5;
				break;
			case 80:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildFromFile;
				effect = GizmoEffect.TwoVictoryPoints;
				cost = 5;
				victoryPoint = 5;
				break;
			case 81:
				type = GizmoType.FILE;
				trigger = GizmoTgr.File;
				effect = GizmoEffect.OneVictoryPoint;
				cost = 4;
				victoryPoint = 4;
				break;
			case 82:
				type = GizmoType.FILE;
				trigger = GizmoTgr.File;
				effect = GizmoEffect.OneVictoryPoint;
				cost = 4;
				victoryPoint = 4;
				break;
			case 83:
				type = GizmoType.FILE;
				trigger = GizmoTgr.File;
				effect = GizmoEffect.DrawThree;
				cost = 4;
				victoryPoint = 4;
				break;
			case 84:
				type = GizmoType.FILE;
				trigger = GizmoTgr.File;
				effect = GizmoEffect.DrawThree;
				cost = 4;
				victoryPoint = 4;
				break;
			case 85:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildT2;
				effect = GizmoEffect.PickAnyTwo;
				cost = 6;
				victoryPoint = 6;
				break;
			case 86:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildT2;
				effect = GizmoEffect.PickAnyTwo;
				cost = 6;
				victoryPoint = 6;
				break;
			case 87:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.DiscountMarbleWhenT2Built;
				cost = 5;
				victoryPoint = 5;
				break;
			case 88:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.DiscountMarbleWhenT2Built;
				cost = 5;
				victoryPoint = 5;
				break;
			case 89:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlueOrRed;
				effect = GizmoEffect.TwoVictoryPoints;
				cost = 5;
				victoryPoint = 5;
				break;
			case 90:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildGreyOrYellow;
				effect = GizmoEffect.TwoVictoryPoints;
				cost = 5;
				victoryPoint = 5;
				break;
			case 91:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildYellowOrRed;
				effect = GizmoEffect.FreeT1Build;
				cost = 6;
				victoryPoint = 6;
				break;
			case 92:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlueOrGrey;
				effect = GizmoEffect.FreeT1Build;
				cost = 6;
				victoryPoint = 6;
				break;
			case 93:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.RestrictResearch;
				cost = 4;
				victoryPoint = 8;
				break;
			case 94:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.RestrictResearch;
				cost = 4;
				victoryPoint = 8;
				break;
			case 95:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.RestrictFile;
				cost = 4;
				victoryPoint = 7;
				break;
			case 96:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.RestrictFile;
				cost = 4;
				victoryPoint = 7;
				break;
			case 97:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlueOrYellow;
				effect = GizmoEffect.File;
				cost = 5;
				victoryPoint = 5;
				break;
			case 98:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildGreyOrRed;
				effect = GizmoEffect.File;
				cost = 5;
				victoryPoint = 5;
				break;
			case 99:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.DiscountMarbleWhenFileBuilt;
				cost = 5;
				victoryPoint = 5;
				break;
			case 100:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.DiscountMarbleWhenFileBuilt;
				cost = 5;
				victoryPoint = 5;
				break;
			case 101:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.DiscountMarbleWhenResearchBuilt;
				cost = 6;
				victoryPoint = 6;
				break;
			case 102:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.DiscountMarbleWhenResearchBuilt;
				cost = 6;
				victoryPoint = 6;
				break;
			case 103:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildYellowOrRed;
				effect = GizmoEffect.Research;
				cost = 7;
				victoryPoint = 7;
				break;
			case 104:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlueOrGrey;
				effect = GizmoEffect.Research;
				cost = 7;
				victoryPoint = 7;
				break;
			case 105:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				//effect = GizmoEffect.
			case 106:
			case 107:
			case 108:
			case 109:
				break;
			default:
				//System.out.println("Missed Something"); //for debugging
				break;
		}
	}
//CONTINUE THIS STUFF DEBAR I AND MARK
}
