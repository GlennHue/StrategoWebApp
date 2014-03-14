/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.model;

import javax.persistence.*;

@Entity
@Table(name = "T_MOVE")
public class Move {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int oldIndex;
    private int newIndex;
    private int number;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

    public Move() {
        number = 0;
    }

    public Move(int oldIndex, int newIndex) {
        this.oldIndex = oldIndex;
        this.newIndex = newIndex;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOldIndex() {
        return oldIndex;
    }

    public void setOldIndex(int oldIndex) {
        this.oldIndex = oldIndex;
    }

    public int getNewIndex() {
        return newIndex;
    }

    public void setNewIndex(int newIndex) {
        this.newIndex = newIndex;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int pieceCode) {
        this.number = pieceCode;
    }
}
