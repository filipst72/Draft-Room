package raf.draft.dsw.controller.StatePattern;

import raf.draft.dsw.controller.State;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.RoomEditWindow;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.messages.MessageType;

import java.awt.*;

public class EditRoomState implements State {
    @Override
    public void misKliknut(RoomView room, Point point) {
        if(room.isNacrtatiSobu()) {
            ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.VecImaSoba);

        }
        else{
            RoomEditWindow roomEditWindow =new RoomEditWindow(room);
            roomEditWindow.setVisible(true);
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
