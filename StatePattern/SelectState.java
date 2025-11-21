package raf.draft.dsw.controller.StatePattern;

import raf.draft.dsw.controller.State;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.painters.ElementPainter;

import java.awt.*;

public class SelectState implements State {
    boolean selected = false;
    @Override
    public void misKliknut(RoomView room, Point point) {
        room.getSelectedList().clear();
        int zumiranX = (int) ((point.x - room.getMousePoint().getX()) / room.getScalingFactor() + room.getMousePoint().getX());
        int zumiranY = (int) ((point.y - room.getMousePoint().getY()) / room.getScalingFactor() + room.getMousePoint().getY());
        point = new Point(zumiranX, zumiranY);
        for(ElementPainter ep: room.getPainterList())
        {


            if(ep.elementAt(point) )
            {
                room.getSelectedList().clear();
                room.getSelectedList().add(ep);
                ep.setHitboxColor(Color.BLUE);
            }
            else {
                room.getSelectedList().clear();
                ep.setHitboxColor(room.getBackground());

            }
        }
        room.ofarbaj();
    }

    @Override
    public void misPritisnut(RoomView room, Point point) {
        int zumiranX = (int) ((point.x - room.getMousePoint().getX()) / room.getScalingFactor() + room.getMousePoint().getX());
        int zumiranY = (int) ((point.y - room.getMousePoint().getY()) / room.getScalingFactor() + room.getMousePoint().getY());
        point = new Point(zumiranX, zumiranY);
        room.setGornjiLevi(point);
        if (room.getSelectedList() != null && !room.getSelectedList().isEmpty()) {
            for(ElementPainter ep: room.getSelectedList())
            {
                ep.setHitboxColor(room.getBackground());
            }
            room.getSelectedList().clear();
        }

    }

    @Override
    public void misPusten(RoomView room, Point point) {
        room.setColor(room.getBackground());
        int zumiranX = (int) ((point.x - room.getMousePoint().getX()) / room.getScalingFactor() + room.getMousePoint().getX());
        int zumiranY = (int) ((point.y - room.getMousePoint().getY()) / room.getScalingFactor() + room.getMousePoint().getY());

        point = new Point(zumiranX, zumiranY);
        room.setDonjiDesni(point);
        if(!room.getGornjiLevi().equals(room.getDonjiDesni())){
            for(ElementPainter ep: room.getPainterList())
            {
                if(ep.intersect(room.getSelectionRectangle())) {
                    room.getSelectedList().add(ep);
                }
            }
            for(ElementPainter ep: room.getSelectedList()){
                ep.setHitboxColor(Color.blue);
            }
        }
        room.ofarbaj();
    }

    @Override
    public void misTocakPomeren(RoomView room, Point point, int brojOkreta) {

    }

    @Override
    public void misUsao(RoomView room, Point point) {

    }

    @Override
    public void misIzasao(RoomView room, Point point) {

    }

    @Override
    public void misVuce(RoomView room, Point point) {
        int zumiranX = (int) ((point.x - room.getMousePoint().getX()) / room.getScalingFactor() + room.getMousePoint().getX());
        int zumiranY = (int) ((point.y - room.getMousePoint().getY()) / room.getScalingFactor() + room.getMousePoint().getY());
        point = new Point(zumiranX, zumiranY);
        room.setDonjiDesni(point);
        room.setColor(Color.BLUE);
        room.ofarbaj();

    }

    @Override
    public void misPomeren(RoomView room, Point point) {

    }
}
