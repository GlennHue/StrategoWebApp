/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "T_PLAYER")
public class Player {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Color color;
    private boolean ready;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private Game game;

   @Transient
    private List<Piece> graveyard = new ArrayList<Piece>();

    @Transient
    private List<Piece> army = new ArrayList<Piece>();

    public Player(){

    }

    public Player(User user) {
        ready = false;
        this.user = user;
    }

    public Player(User user, Game game) {
        ready = false;
        this.user = user;
        this.game = game;
    }

    public Player(User user, Game game, Color color) {
        ready = false;
        this.user = user;
        this.game = game;
        this.color = color;
    }

    public int armyGetSize() {
        return army.size();
    }

   /* public Piece getPieceByName(String name) {
        Piece piece = null;
        for (Piece p : army) {
            if (!p.isPlaced()) {
                if (p.getName().equalsIgnoreCase(name)) {
                    piece = p;
                    break;
                }
            }
        }
        return piece;
    }*/

    public Piece getPiece(int i) {
        return army.get(i);
    }

    public int graveyardGetSize() {
        return graveyard.size();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Piece> getArmy() {
        return army;
    }

    public void setArmy(List<Piece> army) {
        this.army = army;
    }

    public List<Piece> getGraveyard() {
        return graveyard;
    }

    public void setGraveyard(List<Piece> graveyard) {
        this.graveyard = graveyard;
    }

    public boolean isReady() {
        return ready;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean getReady() { return ready; }
    public void setReady(boolean ready) { this.ready = ready;}

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
