package raf.draft.dsw.controller.StateActions;

import com.sun.tools.javac.Main;
import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.elements.Krevet;
import raf.draft.dsw.model.messages.Notification;
import raf.draft.dsw.model.messages.NotificationType;
import raf.draft.dsw.model.painters.ElementPainter;
import raf.draft.dsw.model.painters.KrevetPainter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteStateAction extends AbstractRoomAction {
    public DeleteStateAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete State");
        putValue(SHORT_DESCRIPTION, "DeleteStateAction");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            MainFrame.getInstance().getProjectView().startDeleteState();
    }
}
