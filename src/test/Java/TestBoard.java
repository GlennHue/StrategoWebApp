/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

import be.kdg.model.Board;

import static junit.framework.Assert.assertEquals;

public class TestBoard {
    Board board = new Board();
    @org.junit.Test
    public void testGrootteBord() {

        assertEquals("aantal vakjes moet 100 zijn", 100, board.getTiles().length);
    }

    @org.junit.Test
    public void testObstacles() {

        int countObstacles = 0;
        for(int i = 0; i<100; i++){
                if (board.getTiles()[i].getObstacle()) {
                    countObstacles++;
                }
            }

        assertEquals("aantal vakjes met obstakels moet 8 zijn", 8, countObstacles);
    }

}
