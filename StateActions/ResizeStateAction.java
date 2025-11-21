package raf.draft.dsw.controller.StateActions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ResizeStateAction extends AbstractRoomAction {

    public ResizeStateAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/resize.png"));
        putValue(NAME, "Resize State");
        putValue(SHORT_DESCRIPTION, "ResizeStateAction");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startResizeState();
    }
}
