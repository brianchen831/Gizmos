
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.*;
import java.util.*;

import javax.imageio.ImageIO;

enum GizmoType {
    CONVERT,
    PICK,
    BUILD,
	FILE,
    UPGRADE
}

enum GizmoTgr {
	none,
	GreyMarble,
	RedMarble,
	YellowMarble,
	BlueMarble,
	BlueAndBlueMarble,
	GreyAndGreyMarble,
	YellowAndYellowMarble,
	RedAndRedMarble,
	AnyMarble,

	PickGrey,
	PickRed,
	PickYellow,
	PickBlue,
	PickYellowOrRed,
	PickYellowOrBlue,
	PickBlueOrBlack,
	PickRedOrBlue,
	PickBlueAndBlue,
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

	File
}

enum GizmoEffect{
	AnyMarble,
	BlindMarble,
	AnyTwoMarble,
	TwoYellowMarble,
	TwoRedMarble,
	TwoGreyMarble,
	TwoBlueMarble,
	PickAny,
	PickAnyTwo,
	PickBlind,
	PickTwoBlue,
	PickTwoGrey,
	PickTwo,
	OneMoreMarbleAndResearch,
	OneMoreMarbleAndFile,
	OneMoreVictoryPoint,
	TwoMarbleOneFileTwoResearch

}
public class Gizmo {
	private BufferedImage gizmoImage;
	private int tier;
	private String gizmoColor;
	private int cost; 
	private GizmoType type;	 // file/convert/upgrade..., very left
	private GizmoTgr trigger; // specific trigger, left side, use enum
	private GizmoEffect effect; // right side
	private int victoryPoint;
	private static int IDNum;
	private int ID;

	public Gizmo(BufferedImage gizmoImage, int t) { //index will be used to uniquely distinguish each Gizmo card per sheet 1 and sheet 2

	 	IDNum++;
	    ID = IDNum;
		tier = t;
		this.gizmoImage = gizmoImage;
		//System.out.println(new Color(gizmoImage.getRGB(30, 255)));
		Color temp = new Color(gizmoImage.getRGB(4, 123));
        if (temp==new Color(55, 55, 55)) { gizmoColor="Grey"; }
		else if (temp==new Color(170, 28, 35)) { gizmoColor="Red"; }
		else if (temp==new Color(222, 175, 31)) { gizmoColor="Yellow"; }
		else if (temp==new Color(56, 95, 163)) { gizmoColor="Blue"; }
		victoryPoint = 1;  //default to 1 unless not
		cost = 1; //default to 1 unless not
		AssignProperties();
	} 

	public BufferedImage getImage() {

		return gizmoImage;

	}
	private void AssignProperties(){
		// switch (ID) {
        //     case 0:  System.out.println(ID + "  tier: " + tier);
        //              break; 
		// 	default: System.out.println(ID + " Strange ID unrecognized");
        //              break;
		// }
		System.out.println(ID + "  tier: " + tier);
		switch(ID)
	    {
			case 0:

				break;

			case 1:
			case 6:
				type = GizmoType.CONVERT;			
				trigger = GizmoTgr.YellowMarble;
				effect = GizmoEffect.AnyMarble;				
				break;
			case 2:
			case 5:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.RedMarble;
				effect = GizmoEffect.AnyMarble;
				break;
			case 3:
			case 8:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.GreyMarble;
				effect = GizmoEffect.AnyMarble;
				break;
			case 4:
			case 7:
				type = GizmoType.CONVERT;
				trigger = GizmoTgr.BlueMarble;
				effect = GizmoEffect.AnyMarble;
				break;								
	

			case 9:
			case 15:
				type = GizmoType.PICK;
				trigger = GizmoTgr.BlueMarble;
				effect = GizmoEffect.BlindMarble;
				break;
			case 10:
			case 16:
				type = GizmoType.PICK;
				trigger = GizmoTgr.RedMarble;
				effect = GizmoEffect.BlindMarble;
				break;
			case 11:
			case 13:
				type = GizmoType.PICK;
				trigger = GizmoTgr.YellowMarble;
				effect = GizmoEffect.BlindMarble;
				break;
			case 12:
			case 14:
				type = GizmoType.PICK;
				trigger = GizmoTgr.GreyMarble;
				effect = GizmoEffect.BlindMarble;
				break;




			case 17:
			case 18:
			case 19:
			case 20:
				type = GizmoType.FILE;
				trigger = GizmoTgr.File;
				effect = GizmoEffect.PickAny;
				break;
			case 21:
			case 22:
			case 23:
			case 24:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.OneMoreMarbleAndResearch;
				break;

			case 25:
			case 26:
			case 27:
			case 28:
				type = GizmoType.UPGRADE;
				trigger = GizmoTgr.none;
				effect = GizmoEffect.OneMoreMarbleAndFile;
				break;
			case 29:
				type = GizmoType.BUILD;
				trigger = GizmoTgr.BuildBlue;
				effect = GizmoEffect.PickAny;
				break;
		}
	}
//CONTINUE THIS STUFF DEBARSHI AND MARK
}
