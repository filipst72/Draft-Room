package raf.draft.dsw.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class Open extends AbstractRoomAction{
    public Open(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc=new JFileChooser();
        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                if(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof ProjectExplorer){
                    Project p = ApplicationFramework.getInstance().getJacksonSerializer().loadProject(file);
                    p.setParent(ApplicationFramework.getInstance().getDraftRoomRepository().getRoot());
                    //TODO
                    MainFrame.getInstance().getDraftTree().loadProject(p);
                }
                if(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project project ){
                    Room r = ApplicationFramework.getInstance().getJacksonSerializer().loadRoom(file);
                    project.addChild(r);
                    r.setParent(project);
                    MainFrame.getInstance().getDraftTree().getSelectedNode().add(new DraftTreeItem(r));
                    ApplicationFramework.getInstance().getMessageGenerator().desavanjaUModelu(MessageType.DodatItem,r);
                }
                if(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Building building ){
                    Room r = ApplicationFramework.getInstance().getJacksonSerializer().loadRoom(file);
                    r.setParent(building);
                    building.addChild(r);
                    ApplicationFramework.getInstance().getMessageGenerator().desavanjaUModelu(MessageType.DodatItem,r);
                    MainFrame.getInstance().getDraftTree().getSelectedNode().add(new DraftTreeItem(r));
                }
                MainFrame.getInstance().getDraftTree().osvezi();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
