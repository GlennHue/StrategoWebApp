package be.kdg.beans;


import be.kdg.model.*;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


@Component
@SessionScoped
public class DragDropBean implements Serializable {
  //  private List<Piece> source;
  //  private List<Piece> target;
    private Piece[] pieces;
    private Game game;
    private String  source = "Source";
    private String target = "target";

    public DragDropBean() {
        /*initList();*/
        maakLijst();
    }

    private void maakLijst(){
        game = new Game(new Player("player a", 1), new Player("player b",2));

    /*    for(int i = 0;i < 100; i++){
      /*      tiles[i] = new Tile();            */
           /*if(i == 1){
               //    tiles[i].setPiece(new Piece(i));
           }
            if(i == 2){
                tiles[i].setPiece(new Piece(i));
            }    */
       for(int i = 60;i < 70; i++){

            game.getBoard().getTile(i).setPiece(new Piece(1,"b"));
           game.getBoard().getTile(i + 10).setPiece(new Piece(1,"b"));
           game.getBoard().getTile(i + 20).setPiece(new Piece(1,"b"));
           game.getBoard().getTile(i + 30).setPiece(new Piece(1,"b"));
        }

          /*  if(i<12) {
                if(i % 2 == 0) {
                    tiles[i].setPiece(new Piece(i, "b"));
                } else {
                    tiles[i].setPiece(new Piece(i, "r"));
                }
            }
           /*
            if(i == 42 || i == 43 || i == 46 || i == 47 || i == 52 || i == 53 || i == 56 || i == 57){
                tiles[i].setObstacle(true);
            }  */
      //  }
    }

    private void initList() {
       // source = Lists.newArrayList();
       // target = Lists.newArrayList();
        pieces = new Piece[100];
        //source.add(new Piece(0));


        for(int i = 0;i < 10; i++){
             pieces[i] = new Piece(i,i, "r");
             pieces[i*10] = new Piece(i,i*10, "b") ;
        }
    }


    public void movePiece(int newIndex, int oldIndex) {
      //  source.remove(p);
      //  target.add(p);

        game.setPiece(newIndex, oldIndex);
}

    public void putStartPieces(String pieces){
        char firstChar = pieces.charAt(0);
        if (firstChar == ('b')){
            putStartPiecesBlue(pieces);
        }
        else{
            putStartPiecesRed(pieces);
        }

        game.setPlayerReady(0);
    }

    public void setReady() {
        game.setPlayerReady(1);
    }

    public boolean getReady() {
        if(game.getPlayerReady(0) && game.getPlayerReady(1)) {
            return true;
        } else {
            return false;
        }
    }

    private void putStartPiecesRed(String pieces) {
        String[] pieces2 = pieces.split(",");
        Tile[] tiles = game.getBoard().getTiles();
        int j = 0;
        for (int i = 39; i>=0;i--){
            tiles[i].setPiece(new Piece(Integer.parseInt(pieces2[j].substring(1)), pieces2[j].substring(0, 1)));
            j++;
        }
    }

    private void putStartPiecesBlue(String pieces) {
        String[] pieces2 = pieces.split(",");
        Tile[] tiles = game.getBoard().getTiles();
        for (int i = 60; i<100;i++){
            tiles[i].setPiece(new Piece(Integer.parseInt(pieces2[i-60].substring(1)), pieces2[i-60].substring(0, 1)));
        }
    }


    public Piece[] getPieces() {
        return pieces;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Tile[] getTiles() {
        return game.getBoard().getTiles();
    }

    public String getColor(int userId,int rank){


        return "img/piece/" + game.getPlayerColor(userId) + rank + ".png";
    }
}
