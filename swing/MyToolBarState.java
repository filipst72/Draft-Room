package raf.draft.dsw.gui.swing;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyToolBarState extends JToolBar {
    public MyToolBarState(){
        super(VERTICAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getEditRoomStateAction());
        add(MainFrame.getInstance().getActionManager().getAddStateAction());
        add(MainFrame.getInstance().getActionManager().getSelectStateAction());
        add(MainFrame.getInstance().getActionManager().getResizeStateAction());
        add(MainFrame.getInstance().getActionManager().getRotateStateAction());
        add(MainFrame.getInstance().getActionManager().getRotateLeftStateAction());
        add(MainFrame.getInstance().getActionManager().getMoveStateAction());
        add(MainFrame.getInstance().getActionManager().getZoomStateAction());
        add(MainFrame.getInstance().getActionManager().getEditStateAction());
        add(MainFrame.getInstance().getActionManager().getDeleteStateAction());
        add(MainFrame.getInstance().getActionManager().getCopyPasteAction());
    }
}
