package be.kdg.beans;

import be.kdg.model.Piece;
import org.richfaces.event.DropEvent;
import org.richfaces.event.DropListener;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class DragDropEventBean implements DropListener {



    public void processDrop(DropEvent event) {

        // dragDropBean.movePiece((Piece) event.getDragValue());
        // dragDropBean.movePiece(7,25);
        Piece piece = (Piece) event.getDragValue();
        //  String[] parts = event.getDropValue().toString().split("=");
        //  dragDropBean.setSource(Integer.toString(piece.getxCoordinate()));
        // dragDropBean.setTarget(event.getDropValue().toString());

        //  dragDropBean.setTarget(parts[1].split(",")[0]);

        piece.setxCoordinate(Integer.parseInt(event.getDropValue().toString()));
        //    dragDropBean.setSource(event.getDragSource().toString());
        // dragDropBean.setTarget(event.getDropTarget().toString());


    }
}
