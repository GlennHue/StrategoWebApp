package be.kdg.model;


import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Piece> army;
    private List<Piece> graveyard;
    private Color color;
    private int userId;
    boolean ready;

    public enum Color {
        b, r
    }



    public Player(String name,int userId) {
        this.name = name;
        ready = false;
        createArmy();
        this.userId = userId;
        graveyard = new ArrayList<Piece>();
    }

    public int armyGetSize() {
        return army.size();
    }

    public boolean getReady() { return ready; }
    public void setReady(boolean ready) { this.ready = ready;}

    public String getName() {
        return name;
    }

    private void createArmy(){
        army = new ArrayList<Piece>();
        int[] countPieces = {1,1,8,5,4,4,4,3,2,1,1,6};

        for(int i = 0;i < countPieces.length;i++) {
            createPiece(i,countPieces[i]);
        }
    }

    private void createPiece(int rank,int count){
        for(int i = 0;i<count;i++){
            Piece piece = new Piece(rank, "b");
            army.add(piece);
        }

    }

    public Piece getPieceByName(String name) {
        Piece piece = null;
        for (Piece p : army) {
            if (!p.isPlaced()) {
                if (p.getName().equalsIgnoreCase(name)) {
                    piece = p;
                    break;
                }
            }
        }
        return piece;
    }

    public Piece getPiece(int i) {
        return army.get(i);
    }

    public int graveyardGetSize() {
        return graveyard.size();
    }

   public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public int getUserId() {
        return userId;
    }
}
