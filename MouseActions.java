package raf.draft.dsw.controller;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.ProjectView;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.structures.Room;

import java.awt.event.*;
import java.sql.SQLOutput;

public class MouseActions extends MouseAdapter implements MouseWheelListener {

    RoomView roomView;

    public MouseActions(RoomView roomView) {
        this.roomView = roomView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MainFrame.getInstance().getProjectView().misKliknut(roomView, e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getProjectView().misPritisnut(roomView, e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getProjectView().misPusten(roomView, e.getPoint());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        MainFrame.getInstance().getProjectView().misUsao(roomView,
                e.getPoint());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MainFrame.getInstance().getProjectView().misIzasao(roomView, e.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getProjectView().misVuce(roomView,
                e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MainFrame.getInstance().getProjectView().misPomeren(roomView, e.getPoint());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        MainFrame.getInstance().getProjectView().misTocakPomeren(roomView, e.getPoint(), e.getWheelRotation());
    }
}
