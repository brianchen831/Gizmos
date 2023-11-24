import java.util.*;



public class Player {
    
    //we have a fixed amount of gizmo types so lets just use multiple arraylists to keep it simple
    //private TreeMap<String, ArrayList<Gizmo>> heldGizmos;

    private ArrayList<Gizmo> upgradeGizmos;
    private ArrayList<Gizmo> converterGizmos;
    private ArrayList<Gizmo> fileGizmos;
    private ArrayList<Gizmo> pickGizmos;
    private ArrayList<Gizmo> buildGizmos;

    private ArrayList<Gizmo> archive;

    private ArrayList<Marble> heldMarbles;
    private int victoryPoints;
    private boolean winning;
    private String name;
    private int marbleCount; //i think this is kinda redundant and adds an unnecessary step
    
    private int marbleSpace;
    private int archiveSpace;
    private int researchPower;
    
    public Player(String name) {
        //heldGizmos = new TreeMap<>();
        upgradeGizmos = new ArrayList();
        converterGizmos = new ArrayList();
        fileGizmos = new ArrayList();
        pickGizmos = new ArrayList();
        buildGizmos = new ArrayList();
        winning = false;
        this.name = name;
        heldMarbles = new ArrayList<>();
    }
 
    
    public void fileGizmo(Gizmo g) {
        //heldGizmos.computeIfAbsent("File", k -> new ArrayList<>()).add(g); 
        // the lambda lets you add to an arraylist thats in a treemap with 1000x less effort, if u wanna use ts replace the g with what u need to add and the "File" with a key
    }
    public void addMarble(Marble m) {
        
        heldMarbles.add(m);


    }

    public ArrayList<Marble> getHeldMarbles() {

        return heldMarbles;

    }

    public int getVictoryPoints() {

        return victoryPoints;


    }
    public String getName() {

        return name;

    }

    public int getMarbleCount() {

        return marbleCount;

    }
    //public Gizmo getFiledGizmos() {
    //}
    public void researchMethod(int tear) {

    }
}
