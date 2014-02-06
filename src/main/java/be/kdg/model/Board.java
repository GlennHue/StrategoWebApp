/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.model;


public class Board {

    public Tile[][] tiles;

    public Board(){
        this.tiles = new Tile[10][10];
        for(int i = 0;i<10;i++){
            for(int j = 0;j<10; j++){
                tiles[i][j] = new Tile();
            }
        }
        createObstacles();
    }

    public void createObstacles(){
        tiles[2][4].setObstacle(true);
        tiles[2][5].setObstacle(true);
        tiles[3][4].setObstacle(true);
        tiles[3][5].setObstacle(true);
        tiles[6][4].setObstacle(true);
        tiles[6][5].setObstacle(true);
        tiles[7][4].setObstacle(true);
        tiles[7][5].setObstacle(true);
    }
}
