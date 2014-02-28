import be.kdg.model.Piece;
import be.kdg.model.Player;

import static junit.framework.Assert.assertEquals;

public class TestPlayer {

     private Player player = new Player("De Fons",1);
    int flag=0,spy=0,scout=0,miner=0,sergeant=0,lieutenant=0,captain=0,major=0,
            colonel=0, general=0, marshal=0, bomb =0 ;

    @org.junit.Test
    public void testCountArmyandGraveyard() {


                assertEquals("Army size should be 40", 40, player.armyGetSize() + player.graveyardGetSize());

    }


    @org.junit.Test
     public void testCountPieces(){



        for(int i =0;i < player.armyGetSize();i++){

            Piece piece = player.getPiece(i);

            swutch(piece.getRank());

        }

        for(int i = 0;i < player.graveyardGetSize();i++){
            Piece piece = player.getPiece(i);
            swutch(piece.getRank());
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



    private void swutch(int rank){

            switch (rank){
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

}
