package raf.draft.dsw.controller.StateActions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.tab.commands.implementation.CopyPasteCommand;
import raf.draft.dsw.model.elements.*;
import raf.draft.dsw.model.painters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CopyPasteAction extends AbstractRoomAction {
    public CopyPasteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractCommand copyPasteCommand = new CopyPasteCommand();
        MainFrame.getInstance().getProjectView().getRoom().getCommandManager().addCommand(copyPasteCommand);
    }

}
