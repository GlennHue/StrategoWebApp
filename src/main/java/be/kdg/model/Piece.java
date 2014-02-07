package be.kdg.model;


public class Piece{

    public enum nameEnum{FLAG,SPY,SCOUT,MINER,SERGEANT,LIEUTENANT,
        CAPTAIN,MAJOR,COLONEL,GENERAL,MARSHAL,BOMB}

    private int rank;
    private nameEnum name;

    public Piece(int rank) {
        this.rank = rank;
        assignName();
    }

    private void assignName(){
        name = nameEnum.values()[rank];
    }

    public String getName() {
        return name.toString();
    }

    public int getRank() {
        return rank;
    }

}