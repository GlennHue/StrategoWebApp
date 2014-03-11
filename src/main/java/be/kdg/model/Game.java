package be.kdg.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marousi on 2/11/14.
 */
@Entity
@Table(name = "T_GAME")
public class Game {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Transient
    private Board board;

    @OneToMany(mappedBy = "game")
    private List<StartPosition> startPositions;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private List<Player> players;

    private int time;
    private int playerCount;

    public Game() {
        setBoard(new Board());
    }

    public int getId() {
        return id;
    }

    public void addStartPosition(StartPosition sp) {
        if(startPositions.size() < 2){
            startPositions.add(sp);
        } else {
            throw new IllegalArgumentException();
        }
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<StartPosition> getStartPositions() {
        return startPositions;
    }

    public void setStartPositions(List<StartPosition> startPositions) {
        this.startPositions = startPositions;
    }

    public int getTime() {
        return time;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public List<Player> getPlayers() {

        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Game(Player playerOne, Player playerTwo) {

        board = new Board();
        players = new ArrayList<Player>();
        playerOne.setColor(Color.RED);
        players.add(playerOne);
        playerTwo.setColor(Color.BLUE);
        players.add(playerTwo);
        playerCount = 0;
    }

    /**public boolean movePiece(int newIndex, int oldIndex) {
        List<Tile> tiles = board.getTiles();
        tiles.get(newIndex).movePiece(tiles.get(oldIndex).getPiece());
        tiles.get(oldIndex).movePiece(null);
        return true;
    }**/

    public void setPlayerReady(int i) {
        players.get(i).setReady(true);
    }

    public boolean getPlayerReady(int i) {
        return players.get(i).getReady();
    }

    /**public void setArmy(Player player, String[] names) {
        int x = 0;
        int y = 0;
        if (player.getColor() == Color.BLUE) {
            y = 9;
            for (int i = names.length-1; i != -1; i--) {
                Piece currentPiece = player.getPieceByName(names[i]);
                //movePiece(player, currentPiece, x, y);
                x++;
                if (x == 10) {
                    x = 0;
                    y--;
                }
                currentPiece.setPlaced(true);
            }
        } else {
            for (int i = 0; i < names.length; i++) {
                Piece currentPiece = player.getPieceByName(names[i]);
                //movePiece(player, currentPiece, x, y);
                x++;
                if (x == 10) {
                    x = 0;
                    y++;
                }
                currentPiece.setPlaced(true);
            }
        }
    }**/

    public Board getBoard() {
        return board;
    }

    public void setTime(int time){
        this.time = time;
    }

    /**public String move(Player player, Piece piece, int x, int y) {
        if(board.getTile(x).getPiece() == null) {
            int currentX = piece.getxCoordinate();
            int currentY = piece.getyCoordinate();
            board.getTile(x).movePiece(piece);
            board.getTile(currentX).movePiece(null);
            return "";
        } else {
            return "You can't move here";
        }
    }*/

     /**public String getPlayerColor(int userId){
         if(players[0].getUser().getId() == userId){
             return players[0].getColor().toString();
         }
         else if(players[1].getUser().getId() == userId){
            return players[1].getColor().toString();
         }    else{
             return "NO, JUST NO";
         }
     }**/
}
