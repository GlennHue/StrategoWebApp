package be.kdg.beans;


import be.kdg.model.Board;
import be.kdg.model.Piece;
import be.kdg.model.Tile;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class DragDropBean implements Serializable {
  //  private List<Piece> source;
  //  private List<Piece> target;
    private Piece[] pieces;
    private Board board;
    private String  source = "Source";
    private String target = "target";

    public DragDropBean() {
        /*initList();*/
        maakLijst();
    }

    private void maakLijst(){
        board = new Board();

    /*    for(int i = 0;i < 100; i++){
      /*      tiles[i] = new Tile();            */
           /*if(i == 1){
               //    tiles[i].setPiece(new Piece(i));
           }
            if(i == 2){
                tiles[i].setPiece(new Piece(i));
            }
            if(i<12) {
                if(i % 2 == 0) {
                    tiles[i].setPiece(new Piece(i, "b"));
                } else {
                    tiles[i].setPiece(new Piece(i, "r"));
                }
            }

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


    public void movePiece(int oldTile, int newTile) {
      //  source.remove(p);
      //  target.add(p);

       pieces[newTile] = pieces[oldTile];
        pieces[oldTile] = null;
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
        return board.getTiles();
    }
}
