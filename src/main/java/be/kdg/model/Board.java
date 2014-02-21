/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.model;


public class Board {

    private Tile[] tiles;

    public Board(){
        this.tiles = new Tile[100];
        for(int i = 0;i<100;i++){
                tiles[i]= new Tile();
        }
        createObstacles();
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public Piece getPieceByCoordinate(int x) {
        return tiles[x].getPiece();
    }

    public Tile getTile(int x) {
        return tiles[x];
    }

    public void createObstacles(){
        tiles[42].setObstacle(true);
        tiles[43].setObstacle(true);
        tiles[46].setObstacle(true);
        tiles[47].setObstacle(true);
        tiles[52].setObstacle(true);
        tiles[53].setObstacle(true);
        tiles[56].setObstacle(true);
        tiles[57].setObstacle(true);
    }
}
