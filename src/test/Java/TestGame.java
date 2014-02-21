import be.kdg.model.Game;
import be.kdg.model.Piece;
import be.kdg.model.Player;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Marousi on 2/11/14.
 */
public class TestGame {

    //Verwisselt naar 1 dimensionale array, deze test is dus veroudert.

    /*

    private final Player playerTwo = new Player("Wouter");
    private final Player playerOne = new Player("Glenn");
    private final Game game = new Game(playerOne, playerTwo);
    private final String[] names = new String[]{"scout", "scout", "bomb", "bomb", "scout", "scout", "miner", "miner", "scout", "scout", "scout", "scout", "bomb", "bomb", "bomb", "bomb", "miner", "miner", "miner", "sergeant", "sergeant", "sergeant", "sergeant", "lieutenant", "lieutenant", "lieutenant", "lieutenant", "captain", "captain", "captain", "captain", "major", "major", "major", "colonel", "colonel", "general", "marshal", "spy", "flag"};
    private Piece movingPiece = game.getBoard().getPieceByCoordinate(1,3);

    @org.junit.Test
    public void testSetPiecePlayerOne() {

        assertTrue("Player must be able to put a piece on his side of the field", setPiece(playerOne, 0, 0, 0));
        assertFalse("Player mustn't be able to put a piece on other side of the field",setPiece(playerOne, 1, 9, 9));
        assertEquals("Flag piece must've been placed on the board", playerOne.getPiece(0), getPieceOnBoard(0, 0));
    }

    @Test
    public void testSetPiecePlayerTwo() {

        assertTrue("Player must be able to put a piece on his side of the field", setPiece(playerTwo, 0, 9, 9));
        assertFalse("Player mustn't be able to put a piece on other side of the field", setPiece(playerTwo, 1, 0, 0));
        assertEquals("Flag piece must've been placed on the board", playerTwo.getPiece(0), getPieceOnBoard(9, 9));
    }

    @Test
    public void testSetAllPiecesRed() {
        game.setArmy(playerOne, names);
        for(int i = 0; i < names.length; i++) {
            assertTrue("All pieces should be placed", playerOne.getPiece(i).isPlaced());
        }
    }

    @Test
    public void testValidMove() {
        game.setArmy(playerOne, names);
        movingPiece = game.getBoard().getPieceByCoordinate(1, 3);
        game.move(playerOne, movingPiece, 1, 4);
        assertEquals("Piece should be moved to new position", movingPiece, game.getBoard().getPieceByCoordinate(1, 4));
    }

    @Test
    public void testMoveToSameColor() {
        game.setArmy(playerOne, names);
        assertEquals("Piece should not be able to move to a tile where one of your pieces is", "You can't move here", game.move(playerOne, movingPiece, 0, 3));
    }

    @Test
    public void testSetAllPieceBlue() {
        game.setArmy(playerTwo, names);
        for(int i = 0; i < names.length; i++) {
            assertTrue("All pieces should be placed", playerTwo.getPiece(i).isPlaced());
        }
    }

    @Test
    public void testMoveMultipleSquares() {
        game.setArmy(playerOne, names);
    }

    private boolean setPiece(Player player, int piece, int x, int y) {
        return game.setPiece(player, player.getPiece(piece), x, y);
    }

    private Piece getPieceOnBoard(int x, int y) {
        return game.getBoard().getTile(x, y).getPiece();
    }

    */
}
