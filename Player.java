import java.util.*;



public class Player {
    
    private TreeMap<String, ArrayList<Gizmo>> heldGizmos;
    private ArrayList<Marble> heldMarbles;
    private int victoryPoints;
    private boolean winning;
    private String name;
    public Player(String name) {
        heldGizmos = new TreeMap<>();
        winning = false;
        this.name = name;
    }
    
    public void fileGizmo(Gizmo g) {
        heldGizmos.computeIfAbsent("File", k -> new ArrayList<>()).add(g); // the lambda lets you add to an arraylist thats in a treemap with 1000x less effort, if u wanna use ts replace the g with what u need to add and the "File" with a key
    }
    public void addMarble(Marble m) {
        
        heldMarbles.add(m);

    }

    public int getVictoryPoints() {

        return victoryPoints;


    }
    public String getName() {

        return name;

    }
    //public Gizmo getFiledGizmos() {
    //}
}
