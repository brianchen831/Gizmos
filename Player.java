import java.util.*;

//import Gizmo.GizmoEffect;



public class Player {
    
    //we have a fixed amount of gizmo types so lets just use multiple arraylists to keep it simple
    //private TreeMap<String, ArrayList<Gizmo>> heldGizmos;

    private ArrayList<Gizmo> upgradeGizmos;
    private ArrayList<Gizmo> converterGizmos;
    private ArrayList<Gizmo> fileGizmos;
    private ArrayList<Gizmo> pickGizmos;
    private ArrayList<Gizmo> buildGizmos;
    private Gizmo clickedFileGizmo;

    private ArrayList<Gizmo> archiveGizmos;

    private ArrayList<Marble> heldMarbles;
    private int victoryPoints;
    private boolean winning;
    private String name;
    
    private int marbleSpace;
    private int archiveSpace;
    private int researchPower;

    private int weightedPts;
    private int position;
    
    public Player(String name) {
        //heldGizmos = new TreeMap<>();
        upgradeGizmos = new ArrayList();
        converterGizmos = new ArrayList();
        fileGizmos = new ArrayList();
        pickGizmos = new ArrayList();
        buildGizmos = new ArrayList();
        archiveGizmos = new ArrayList<>();
        clickedFileGizmo = null;
        winning = false;
        this.name = name;
        heldMarbles = new ArrayList<>();
        //temporary changing marble space
        marbleSpace = 5;
        archiveSpace = 1;
        researchPower = 3;
        weightedPts = 0;
        position = 0;
    }
    public void setPosition(int pos){
        position = pos;
    }
    public void CalculateWeightedPts(){
        weightedPts = victoryPoints * 100 + heldMarbles.size() * 10 + position;
    }
    public int getWeightedPts(){
        return weightedPts;
    }
    public void addArchiveGizmo(Gizmo g){
        archiveGizmos.add(g);
    }
    public void removeArchiveGizmo(Gizmo g)
    {
        for(int i = 0; i < archiveGizmos.size(); i++){
            if(archiveGizmos.get(i) == g){
                archiveGizmos.remove(i);
                break;
            }
        }
    }
    public void addFileGizmo(Gizmo g) {
        fileGizmos.add(g);
        //heldGizmos.computeIfAbsent("File", k -> new ArrayList<>()).add(g); 
        // the lambda lets you add to an arraylist thats in a treemap with 1000x less effort, if u wanna use ts replace the g with what u need to add and the "File" with a key
    }
    public void addUpgradeGizmo(Gizmo g){
        upgradeGizmos.add(g);
        Gizmo.GizmoEffect effect = g.getEffect();
        if(effect == Gizmo.GizmoEffect.OneMarbleOneArchive){
            marbleSpace++;
            archiveSpace++;
        }
        else if(effect == Gizmo.GizmoEffect.OneMarbleOneResearch){
            marbleSpace++;
            researchPower++;
        }
        else if(effect == Gizmo.GizmoEffect.TwoMarbleOneFileTwoResearch)
        {
            marbleSpace += 2;
            archiveSpace++;
            researchPower += 2;
        }

    }
    public void addConvertGizmo(Gizmo g){
        converterGizmos.add(g);
    }
    public void addPickGizmo(Gizmo g){
        pickGizmos.add(g);
    }
    public void addBuildGizmo(Gizmo g){
        buildGizmos.add(g);
    }
    public boolean spaceForMoreMarbles()
    {
        if(marbleSpace > heldMarbles.size())
            return true;
        else
            return false;
    }
    public int getArchiveSpace(){
        return archiveSpace;
    }
    public int getArchiveSpaceUsed(){
        return archiveGizmos.size();
    }
    public boolean spaceForMoreArchive()
    {
        if(archiveSpace > archiveGizmos.size())
            return true;
        else    
            return false;
    }
    public boolean spaceForMoreMarble(){
        if(marbleSpace > heldMarbles.size())
            return true;
        else
            return false;
    }
    public int getResearchPower(){
        return researchPower;
    }
    public void noMoreResearch(){
        researchPower = 0;
    }
    public void noMoreArchive(){
        archiveSpace = 0;
    }
    public void addMarble(Marble m) {
        //System.out.println("Player " + name + " getting a " + m.getMarbleColor() + " marble");
        if(!spaceForMoreMarble())
            return;
        heldMarbles.add(m);
    }
    public void addVictoryPoint(int v){
        victoryPoints += v;
    }
 
    public void payMarble(int cost, String color){
        int removedMarbles = 0;
        for(int i = heldMarbles.size() - 1; i >= 0; i--){
            if(removedMarbles == cost)
                break;
            Marble m = heldMarbles.get(i);
            if(m.getMarbleColor() == color && removedMarbles < cost){
                heldMarbles.remove(i);
                removedMarbles++;
            }
        }
    }
    public ArrayList<Marble> getHeldMarbles() {
        return heldMarbles;
    }
    public void ClearHeldMarbles(){
        heldMarbles.clear();
    }
        //    upgradeGizmos = new ArrayList();
        // converterGizmos = new ArrayList();
        // fileGizmos = new ArrayList();
        // pickGizmos = new ArrayList();
        // buildGizmos = new ArrayList();
    public ArrayList<Gizmo> getUpgradeGizmos(){
        return upgradeGizmos;
    }
    public ArrayList<Gizmo> getConvertGizmos(){
        return converterGizmos;
    }
    public ArrayList<Gizmo> getFileGizmos(){
        return fileGizmos;
    }
    public ArrayList<Gizmo> getPickGizmos(){
        return pickGizmos;
    }
    public ArrayList<Gizmo> getBuildGizmos(){
        return buildGizmos;
    }
    public ArrayList<Gizmo> getArchivedGizmos(){
        return archiveGizmos;
    }
    public Gizmo getClickedFileGizmo () {
        return clickedFileGizmo;
    }
    public void setClickedFileGizmo (Gizmo g) {
        clickedFileGizmo = g;
    }
    public void removeClickedFileGizmo () {
        clickedFileGizmo = null;
    }
    public int getVictoryPoints() {
        return victoryPoints;
    }
    public String getName() {
        return name;
    }

    public int getMarbleCount() {
        return heldMarbles.size();
    }
    //public Gizmo getFiledGizmos() {
    //}
    public void researchMethod(int tear) {

    }


    public void addMarbleSpace(int num){
        marbleSpace += num;
    }
    public void addArchiveSpace(int num){
        archiveSpace += num;
    }
    public void addResearchPower(int num){
        researchPower += num;
    }
    

}
