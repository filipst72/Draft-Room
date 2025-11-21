package raf.draft.dsw.controller.StatePattern;

import raf.draft.dsw.controller.State;
import raf.draft.dsw.gui.swing.EditRoomElement;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.painters.ElementPainter;

import java.awt.*;

public class EditState implements State {
    @Override
    public void misKliknut(RoomView room, Point point) {
        int zumiranX = (int) ((point.x - room.getMousePoint().getX()) / room.getScalingFactor() + room.getMousePoint().getX());
        int zumiranY = (int) ((point.y - room.getMousePoint().getY()) / room.getScalingFactor() + room.getMousePoint().getY());
        point = new Point(zumiranX, zumiranY);
        for(ElementPainter elementPainter: room.getPainterList()){

            if(elementPainter.elementAt(point)){
                EditRoomElement editRoomElement=new EditRoomElement(room,(RoomElement) elementPainter.getDraftNode());
                editRoomElement.setVisible(true);
            }
        }
    }

    @Override
    public void misPritisnut(RoomView room, Point point) {

    }

    @Override
    public void misPusten(RoomView room, Point point) {

    }

    @Override
    public void misUsao(RoomView room, Point point) {

    }

    @Override
    public void misIzasao(RoomView room, Point point) {

    }

    @Override
    public void misVuce(RoomView room, Point point) {

    }

    @Override
    public void misPomeren(RoomView room, Point point) {

    }

    @Override
    public void misTocakPomeren(RoomView room, Point point, int brojOkreta) {

    }
}
