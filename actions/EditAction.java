package raf.draft.dsw.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.EditWindow;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditAction extends AbstractRoomAction{
    public EditAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(NAME, "Edit");
        putValue(SHORT_DESCRIPTION, "Edit");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = (DraftTreeItem) MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected==null){
            ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.NijeSelektovano);
            return;
        }
        else if(selected.getDraftNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.ProjectExplorerEdit);
            return;
        }
        else{
            EditWindow editWindow=new EditWindow(selected);
            editWindow.setVisible(true);
        }
    }
}
