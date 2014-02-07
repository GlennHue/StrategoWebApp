

import be.kdg.model.Piece;

import static junit.framework.Assert.assertEquals;

public class TestPiece {

    @org.junit.Test
    public void testName() {
        String[] arrayNames = {"FLAG", "SPY", "SCOUT", "MINER","SERGEANT", "LIEUTENANT",
                "CAPTAIN", "MAJOR", "COLONEL","GENERAL", "MARSHAL", "BOMB"};

        for(int i = 0; i<12;i++) {
            Piece piece = new Piece(i);
            assertEquals("Names should be linked to the correct rank", arrayNames[i], piece.getName());
        }
    }

}
