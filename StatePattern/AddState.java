package raf.draft.dsw.controller.StatePattern;

import raf.draft.dsw.controller.State;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.AddRoomItemWindow;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.painters.ElementPainter;

import java.awt.*;

public class AddState implements State {
    @Override
    public void misKliknut(RoomView room, Point point) {
        for(ElementPainter ep:room.getSelectedList()) ep.setHitboxColor(room.getBackground());
        room.getSelectedList().clear();
        double zumiranX = (point.getX() - room.getMousePoint().getX()) / room.getScalingFactor() + room.getMousePoint().getX();
        double zumiranY = (point.getY() - room.getMousePoint().getY()) / room.getScalingFactor() + room.getMousePoint().getY();
        point = new Point((int) zumiranX, (int) zumiranY);
        if(!room.getRoomHitbox().contains(point)) ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.VanSobe);
        else{
            if(!room.isNacrtatiSobu()) ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.NemaSobe);
            else {
                for(ElementPainter e:room.getPainterList()){
                    if(e.elementAt(point)){
                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.Preseca);
                        return;
                    }
                }


                AddRoomItemWindow addRoomItemWindow=new AddRoomItemWindow(room, point);
                addRoomItemWindow.setVisible(true);
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
