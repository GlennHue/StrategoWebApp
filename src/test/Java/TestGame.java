/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

import be.kdg.beans.LobbyBean;
import be.kdg.beans.RegisterBean;
import be.kdg.model.*;
import be.kdg.persistence.api.GameDAOApi;
import be.kdg.persistence.api.PlayerDAOApi;
import be.kdg.persistence.api.UserDAOApi;
import be.kdg.persistence.impl.GameDAOImpl;
import be.kdg.persistence.impl.PlayerDAOImpl;
import be.kdg.persistence.impl.UserDAOImpl;
import be.kdg.service.api.GameServiceApi;
import be.kdg.service.api.PlayerServiceApi;
import be.kdg.service.api.UserServiceApi;
import be.kdg.service.impl.GameServiceImpl;
import be.kdg.service.impl.PlayerServiceImpl;
import be.kdg.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class TestGame {

    WebDriver driver;


    private UserDAOApi userOperations = new UserDAOImpl();
    private GameDAOApi gameOperations = new GameDAOImpl();
    private PlayerDAOApi playerOperations = new PlayerDAOImpl();
    private final GameServiceApi gameService = new GameServiceImpl();
    private final PlayerServiceApi playerService = new PlayerServiceImpl();
    private User user1;
    private User user2;
    private Game game;
    private Player player1;
    private Player player2;
    private LobbyBean lobby = new LobbyBean();
    private int gameId;
    private int playerId1;
    private int playerId2;

    @Before
    public void beforeTest(){

        setDriver();
        insertUsers();
        makeGame();

    }

    @After
    public void afterTest(){
        driver.close();
        driver.quit();
    }

    @Test
    public void testSetStartPosition(){

       /* new WebDriverWait(driver, 10);

        driver.get("http://localhost:8080/api/game/setStartPosition?pieces=r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1,r1&playerId="+playerId1+"&gameId="+gameId);
        String result = driver.findElement(By.cssSelector("body")).getText();
        assertEquals("expect false, as only one player is ready", "false", result);
    */
    }

    /*
    @org.junit.Test
    public void testSetPiecePlayerOne() {

        assertTrue("Player must be able to put a piece on his side of the field", movePiece(playerOne, 0, 0, 0));
        assertFalse("Player mustn't be able to put a piece on other side of the field",movePiece(playerOne, 1, 9, 9));
        assertEquals("Flag piece must've been placed on the board", playerOne.getPiece(0), getPieceOnBoard(0, 0));
    }

    @Test
    public void testSetPiecePlayerTwo() {

        assertTrue("Player must be able to put a piece on his side of the field", movePiece(playerTwo, 0, 9, 9));
        assertFalse("Player mustn't be able to put a piece on other side of the field", movePiece(playerTwo, 1, 0, 0));
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

    private boolean movePiece(Player player, int piece, int x, int y) {
        return game.movePiece(player, player.getPiece(piece), x, y);
    }

    private Piece getPieceOnBoard(int x, int y) {
        return game.getBoard().getTile(x, y).getPiece();
    }
    */

    public void setDriver(){

        gameOperations.refresh();

        File file = new File("C:\\Users\\Stijn\\Downloads\\Selenium-Google\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        driver = new ChromeDriver();

    }

    public void insertUsers(){

        user1 = new User("user1", "password", "email");
        userOperations.insertNewUser(user1);
        user2 = new User("user2", "password", "email");
        userOperations.insertNewUser(user2);
        user1 = userOperations.getUserByUsername("user1");
        user2 = userOperations.getUserByUsername("user2");
        userOperations.setUserAuthenticationCode("user1", "testUuid1");
        userOperations.setUserAuthenticationCode("user2", "testUuid2");
        userOperations.uuidIsVerified("testUuid1");
        userOperations.uuidIsVerified("testUuid2");

    }

    public void makeGame(){

        game = new Game();
        gameOperations.saveGame(game);
        player1 = new Player(user1,game,Color.RED);
        player2 = new Player(user2,game,Color.BLUE);
        playerOperations.savePlayer(player1);
        playerOperations.savePlayer(player2);
        game.setPlayers(new ArrayList<Player>(Arrays.asList(player1, player2)));
        gameId = player1.getGame().getId();
        playerId1 = player1.getId();
        playerId2 = player2.getId();

    }

}
