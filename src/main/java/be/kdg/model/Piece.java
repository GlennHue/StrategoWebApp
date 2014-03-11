package be.kdg.model;


public class Piece implements Comparable<Piece> {

    public enum NameEnum {
        FLAG, SPY, SCOUT, MINER, SERGEANT, LIEUTENANT,
        CAPTAIN, MAJOR, COLONEL, GENERAL, MARSHAL, BOMB
    }

    private int rank;
    private NameEnum name;
    private boolean isPlaced;
    private int xCoordinate;
    private int yCoordinate;
    private String color;
    private String url;

    public Piece(int rank, String color) {
        url = "img/piece/" + color + rank + ".png";
        this.rank = rank;
        this.color = color;
        assignName();
    }

    public Piece(int rank, int place, String color) {
        url = "img/piece/" + color + rank + ".png";
        this.rank = rank;
        xCoordinate = place;
        this.color = color;
        assignName();
    }


    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean isPlaced) {
        this.isPlaced = isPlaced;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    private void assignName() {
        name = NameEnum.values()[rank];
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public String getName() {
        return name.toString();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /**
     * public List getCoordinatesOfViableTiles(Board board) {
     * List result = new ArrayList<String>();
     * Tile left = board.getTile(xCoordinate - 1, yCoordinate);
     * Tile front = board.getTile(xCoordinate, yCoordinate + 1);
     * Tile right = board.getTile(xCoordinate + 1, yCoordinate);
     * Tile back = board.getTile(xCoordinate, yCoordinate - 1);
     * if(left.getPiece())
     * return result;
     * }  *
     */

    public int getRank() {
        return rank;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int compareTo(Piece p) {
        if (name == NameEnum.MINER && p.name == NameEnum.BOMB) {
            return 1;
        } else if (name == NameEnum.SPY && p.name == NameEnum.MARSHAL) {
            return 1;
        } else if (p.name == NameEnum.FLAG) {
            return 2;
        } else if (rank > p.rank) {
            return 1;
        } else if (rank < p.rank) {
            return -1;
        }

        return 0;
    }
}