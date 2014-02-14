package be.kdg.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import be.kdg.model.Piece;
import org.richfaces.event.DropEvent;
import org.richfaces.event.DropListener;

@ManagedBean
@RequestScoped
public class DragDropEventBean implements DropListener {

    @ManagedProperty(value = "#{dragDropBean}")
    private DragDropBean dragDropBean;

    public void setDragDropBean(DragDropBean dragDropBean) {
        this.dragDropBean = dragDropBean;
    }

    public void processDrop(DropEvent event) {
       // dragDropBean.movePiece((Piece) event.getDragValue());
        dragDropBean.movePiece(0,25);
    }
}
