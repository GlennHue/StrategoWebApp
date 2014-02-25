package be.kdg.model;

/**
 * Created by Marousi on 2/11/14.
 */
public class Game {
    private Board board;
    private Player[] players;
    private int time;

    public Game(Player playerOne, Player playerTwo) {
        board = new Board();
        players = new Player[2];
        playerOne.setColor(Player.Color.r);
        players[0] = playerOne;
        playerTwo.setColor(Player.Color.b);
        players[1] = playerTwo;
    }

    public boolean setPiece(Player player, Piece piece, int x, int y) {
        if(player.getColor() == Player.Color.r) {
            if(y>3) {
                return false;
            }
        } else {
            if(y<7) {
                return false;
            }
        }
        board.getTile(x).setPiece(piece);
        piece.setxCoordinate(x);
        piece.setyCoordinate(y);
        return true;
    }

    public void setPlayerReady(int i) {
        players[i].setReady(true);
    }

    public void setArmy(Player player, String[] names) {
        int x = 0;
        int y = 0;
        if (player.getColor() == Player.Color.b) {
            y = 9;
            for (int i = names.length-1; i != -1; i--) {
                Piece currentPiece = player.getPieceByName(names[i]);
                setPiece(player, currentPiece, x, y);
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
                setPiece(player, currentPiece, x, y);
                x++;
                if (x == 10) {
                    x = 0;
                    y++;
                }
                currentPiece.setPlaced(true);
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setTime(int time){
        this.time = time;
    }

    public String move(Player player, Piece piece, int x, int y) {
        if(board.getTile(x).getPiece() == null) {
            int currentX = piece.getxCoordinate();
            int currentY = piece.getyCoordinate();
            board.getTile(x).setPiece(piece);
            board.getTile(currentX).setPiece(null);
            return "";
        } else {
            return "You can't move here";
        }
    }
}
