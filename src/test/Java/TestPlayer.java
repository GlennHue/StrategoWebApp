import be.kdg.model.Piece;
import be.kdg.model.Player;

import static junit.framework.Assert.assertEquals;

public class TestPlayer {

     private Player player = new Player("De Fons");

    @org.junit.Test
    public void testCountArmy() {


                assertEquals("Army size should be 40", 40, player.armyGetSize());

    }


    @org.junit.Test
     public void testCountPieces(){
          int flag=0,spy=0,scout=0,miner=0,sergeant=0,lieutenant=0,captain=0,major=0,
                  colonel=0, general=0, marshal=0, bomb =0 ;
        for(int i =0;i < 40;i++){

           Piece piece = player.getPiece(i);

            switch (piece.getRank()){
            case 0 : flag++;break;
            case 1 : spy++ ;break;
            case 2 : scout++;break;
            case 3 : miner++;break;
            case 4 : sergeant++;break;
            case 5 : lieutenant++;break;
            case 6 : captain++;break;
            case 7 : major++;break;
            case 8 : colonel++;break;
            case 9 : general++;break;
            case 10 : marshal++;break;
            case 11 : bomb++;break;   }

        }

        assertEquals("Count of Flag", 1, flag);
        assertEquals("Count of Spy", 1, spy);
        assertEquals("Count of Scout", 8, scout);
        assertEquals("Count of Miner", 5, miner);
        assertEquals("Count of Sergeant", 4, sergeant);
        assertEquals("Count of Lieutenant", 4, lieutenant);
        assertEquals("Count of Captain", 4, captain);
        assertEquals("Count of Major", 3, major);
        assertEquals("Count of Colonel", 2, colonel);
        assertEquals("Count of General", 1, general);
        assertEquals("Count of Marshal", 1, marshal);
        assertEquals("Count of Bomb", 6, bomb);


     }

}
