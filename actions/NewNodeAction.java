package raf.draft.dsw.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.AddItem;
import raf.draft.dsw.gui.swing.EditWindow;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewNodeAction extends AbstractRoomAction {

    public NewNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected2 = (DraftTreeItem) MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected2==null){
            ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.NijeSelektovano);
            return;
        }
        if(selected2.getDraftNode() instanceof Room){
            ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.RoomDodavanje);
            return;
        }
        AddItem addItem=new AddItem(selected2);
        addItem.setVisible(true);
    }
}
