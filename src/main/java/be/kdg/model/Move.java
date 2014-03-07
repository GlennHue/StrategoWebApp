package be.kdg.model;

import javax.persistence.*;

/**
 * Created by Glenn on 6-3-14.
 */
@Entity
@Table(name = "T_MOVE")
public class Move {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int oldIndex;
    private int newIndex;
    private String pieceCode;

    public Move() {
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

    public String getPieceCode() {
        return pieceCode;
    }

    public void setPieceCode(String pieceCode) {
        this.pieceCode = pieceCode;
    }
}
