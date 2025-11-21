package raf.draft.dsw.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteNodeAction extends AbstractRoomAction{
    public DeleteNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = (DraftTreeItem) MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected==null){
            ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.NijeSelektovano);
            return;
        }
        else if(selected.getDraftNode() instanceof ProjectExplorer){
            ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.ProjectExplorerDelete);
            return;
        }
        MainFrame.getInstance().getDraftTree().removeChild(selected);
    }
}
