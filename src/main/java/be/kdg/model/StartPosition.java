/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.model;

import javax.persistence.*;

@Entity
@Table(name = "T_STARTPOSITIONS")
public class StartPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "gameId", nullable = false)
    private Game game;

    private String piece;
    private String color;

    public StartPosition(){
    }

    public StartPosition(String color, String piece) {
        this.color = color;
        this.piece = piece;
    }

    public StartPosition(Game game, String piece, String color) {
        this.game = game;
        this.piece = piece;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getPiece() {
        return piece;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
