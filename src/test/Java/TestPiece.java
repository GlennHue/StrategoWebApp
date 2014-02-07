

import be.kdg.model.Piece;

import static junit.framework.Assert.assertEquals;

public class TestPiece {

    @org.junit.Test
    public void test() {
        String[] arrayNames = {"FLAG", "SPY", "SCOUT", "MINER","SERGEANT", "LIEUTENANT",
                "CAPTAIN", "MAJOR", "COLONEL","GENERAL", "MARSHAL", "BOMB"};

        for(int i = 0; i<12;i++) {
            Piece piece = new Piece(i);
            assertEquals("", arrayNames[i], piece.getName());
        }
    }

}
