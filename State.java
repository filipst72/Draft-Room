package raf.draft.dsw.controller;

import raf.draft.dsw.gui.swing.tab.RoomView;

import java.awt.*;

public interface State {
    void misKliknut(RoomView room, Point point);
    void misPritisnut(RoomView room, Point point);
    void misPusten(RoomView room, Point point);
    void misUsao(RoomView room, Point point);
    void misIzasao(RoomView room, Point point);
    void misVuce(RoomView room, Point point);
    void misPomeren(RoomView room, Point point);
    void misTocakPomeren(RoomView room,Point point, int brojOkreta);

}
