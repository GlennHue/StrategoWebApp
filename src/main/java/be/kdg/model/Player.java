package be.kdg.model;


import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Piece> army;
    private List<Piece> graveyard;
   /* private String color;       */

    public Player(String name) {
        this.name = name;
        createArmy();

        graveyard = new ArrayList<Piece>();
    }

    public int armyGetSize() {
        return army.size();
    }

  /*  public String getName() {
        return name;
    }        */

    public void createArmy(){
        army = new ArrayList<Piece>();
        int[] countPieces = {1,1,8,5,4,4,4,3,2,1,1,6};

        for(int i = 0;i < countPieces.length;i++) {
            createPiece(i,countPieces[i]);
        }
    }

    private void createPiece(int rank,int count){
        for(int i = 0;i<count;i++){
            Piece piece = new Piece(rank);
            army.add(piece);
        }

    }

    public Piece getPiece(int i) {
        return army.get(i);
    }

    public int graveyardGetSize() {
        return graveyard.size();
    }

   /* public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }     */

}