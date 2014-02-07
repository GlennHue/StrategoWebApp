/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.model;


public class Tile {

    private boolean occupied;
    public boolean obstacle;

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
}


