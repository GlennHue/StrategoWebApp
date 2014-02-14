package be.kdg.beans;


import be.kdg.model.Piece;
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

    public DragDropBean() {
        initList();
    }

    private void initList() {
       // source = Lists.newArrayList();
       // target = Lists.newArrayList();
        pieces = new Piece[100];
        //source.add(new Piece(0));


        for(int i = 0;i < 10; i++){
             pieces[i] = new Piece(i);
             pieces[i*10] = new Piece(i) ;
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
}
