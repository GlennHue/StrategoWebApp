/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.model;


public class Tile {

    private boolean occupied;
    private boolean obstacle;
    private Piece piece;

    /*  public Tile( boolean obstacle){
        this.occupied = false;
        this.obstacle = obstacle;
    }       */


    public Tile(){
        this.occupied = false;
        this.obstacle = false;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }


    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean getObstacle() {
        return obstacle;
    }
}


