

import be.kdg.model.Piece;

import static junit.framework.Assert.assertEquals;

public class TestPiece {

    Piece miner = new Piece(3, "b");
    Piece colonel = new Piece(8, "b");
    Piece bomb = new Piece(11, "b");
    Piece spy = new Piece(1, "b");
    Piece marshal = new Piece(10, "b");

    @org.junit.Test
    public void testName() {
        String[] arrayNames = {"FLAG", "SPY", "SCOUT", "MINER","SERGEANT", "LIEUTENANT",
                "CAPTAIN", "MAJOR", "COLONEL","GENERAL", "MARSHAL", "BOMB"};

        for(int i = 0; i<12;i++) {
            Piece piece = new Piece(i, "b");
            assertEquals("Names should be linked to the correct rank", arrayNames[i], piece.getName());
        }
    }

    @org.junit.Test
    public void testLowerVsHigherRank(){

        assertEquals("De waarde moet -1 zijn", -1, miner.compareTo(colonel));
    }


    @org.junit.Test
    public void testHigherVsLowerRank(){
        assertEquals("De waarde moet 1 zijn", 1, colonel.compareTo(miner));
    }


    @org.junit.Test
    public void testSameRank(){
        assertEquals("De waarde moet 0 zijn", 0, colonel.compareTo(colonel));
    }

    @org.junit.Test
    public void testHigherVsBomb(){
        assertEquals("De waarde moet -1 zijn", -1, colonel.compareTo(bomb));
    }

    @org.junit.Test
    public void testMinerVsBomb(){
        assertEquals("De waarde moet 1 zijn",1,miner.compareTo(bomb));
    }

    @org.junit.Test
    public void testSpyVsMarshal(){
        assertEquals("De waarde moet 1 zijn", 1, spy.compareTo(marshal));
    }

    @org.junit.Test
    public void testMarshalVsSpy(){
        assertEquals("De waarde moet 1 zijn",1,marshal.compareTo(spy));
    }

    @org.junit.Test
    public void testCaptureTheFlag(){
        Piece flag = new Piece(0, "b");
        assertEquals("De waarde moet 2 zijn",2,marshal.compareTo(flag));
    }
}
