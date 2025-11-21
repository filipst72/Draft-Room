package raf.draft.dsw.controller.StateActions;

import raf.draft.dsw.controller.MouseActions;
import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ZoomStateAction extends AbstractRoomAction {
    public ZoomStateAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke((char) KeyEvent.MOUSE_WHEEL_EVENT_MASK,
                ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/zoom.png"));
        putValue(NAME, "Zoom State");
        putValue(SHORT_DESCRIPTION, "ZoomStateAction");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startZoomState();
    }


}
