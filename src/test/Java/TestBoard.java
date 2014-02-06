/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

import be.kdg.model.Board;

import static junit.framework.Assert.assertEquals;

public class TestBoard {

    @org.junit.Test
    public void testGrootteBord() {
        Board board = new Board();

        assertEquals("aantal vakjes moet 100 zijn", 100, (board.tiles.length * board.tiles[0].length));
    }

    @org.junit.Test
    public void testObstacles() {
        Board board = new Board();
        int countObstacles = 0;
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                if (board.tiles[i][j].obstacle) {
                    countObstacles++;
                }
            }
        }

        assertEquals("aantal vakjes met obstakels moet 8 zijn", 8, countObstacles);
    }

}
