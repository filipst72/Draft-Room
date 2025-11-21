package raf.draft.dsw.controller.StatePattern;

import raf.draft.dsw.controller.State;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.painters.ElementPainter;

import java.awt.*;
import java.awt.event.KeyAdapter;

public class ZoomState implements State {
    @Override
    public void misKliknut(RoomView room, Point point) {
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
    public void misTocakPomeren(RoomView room, Point point, int brojOkretaja) {
        double scalingFactor = room.getScalingFactor();
        if(brojOkretaja < 0 )
        {
            if(scalingFactor <2)
                room.setScalingFactor( scalingFactor*1.01);
        }
        else
        {
            if(scalingFactor > 0.5)
                room.setScalingFactor( scalingFactor*0.9);
        }
            room.setMousePoint(point);
            for (ElementPainter ep : room.getPainterList()) {
                Rectangle stariHitbox = ep.getHitbox();
                int scaledX = (int) ((stariHitbox.x - room.getMousePoint().getX()) * room.getScalingFactor() + room.getMousePoint().getX());
                int scaledY = (int) ((stariHitbox.y - room.getMousePoint().getY()) * room.getScalingFactor() + room.getMousePoint().getY());
                int scaledWidth = (int) (stariHitbox.width * room.getScalingFactor());
                int scaledHeight = (int) (stariHitbox.height * room.getScalingFactor());
                ///TODO proveriti sa original visinom i sirinom
                //ep.setHitbox(scaledX, scaledY,scaledWidth, scaledHeight );
                ep.setHitbox(scaledX, scaledY,stariHitbox.width, stariHitbox.height );
            }
            room.ofarbaj();

    }
}
