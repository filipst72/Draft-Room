package raf.draft.dsw.controller.StateActions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditRoomStateAction extends AbstractRoomAction {
    public EditRoomStateAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/editstate.png"));
        putValue(NAME, "Edit Room State");
        putValue(SHORT_DESCRIPTION, "EditRoomStateAction");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startEditRoomState();
    }
}
