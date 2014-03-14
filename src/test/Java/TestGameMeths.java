/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

import be.kdg.beans.GameBean;
import be.kdg.model.*;
import be.kdg.persistence.api.GameDAOApi;
import be.kdg.persistence.impl.GameDAOImpl;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


public class TestGameMeths {

    WebDriver driver;
    GameBean gb = new GameBean();
    Player p = new Player(new User());
    Player p2 = new Player(new User());
    Game game = new Game(p, p2);
    String setup = "b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,b1,";

    private GameDAOApi operations = new GameDAOImpl();


    @org.junit.Test
    public void testSetpiece() throws InterruptedException {
        Tile tile =game.getBoard().getTile(1);
        Piece p = new Piece(1,"b");
        tile.setPiece(p);

        assertEquals("This should place a piece on the board.", tile.getPiece(), p);
    }

    @org.junit.Test
    public void testReadyFalse() throws InterruptedException {
        gb.putStartPieces(setup);
        assertFalse("This test sets all pieces but the second player is not yet ready.", gb.getReady());
    }

    @org.junit.Test
    public void testReady() throws InterruptedException {
        p.setReady(true);
        p2.setReady(true);
        gb.setReady();
        gb.putStartPieces(setup);
       assertTrue("This test sets all pieces in place and sets status to ready.", gb.getReady());
    }



}
