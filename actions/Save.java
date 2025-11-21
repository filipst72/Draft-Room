package raf.draft.dsw.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class Save extends AbstractRoomAction{
    public Save(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.ALT_MASK));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*DraftTreeItem selected = (DraftTreeItem) MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected.getDraftNode() instanceof Project){
            ApplicationFramework.getInstance().getJacksonSerializer().saveProject((Project) selected.getDraftNode());
        }
        else{

        }*/
        JFileChooser jfc = new JFileChooser();
        String resourceFolderPath = new File("src/main/resources").getAbsolutePath();
        if (MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project project) {

            File projectFile = null;

            if (project.getPutanja() == null || project.getPutanja().isEmpty()) {
                if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                    projectFile = jfc.getSelectedFile();
                    project.setPutanja(projectFile.getPath());
                } else {

                    project.setPutanja(resourceFolderPath + File.separator+ project.getIme());
                }

            }

            ApplicationFramework.getInstance().getJacksonSerializer().saveProject(project);
        }
        if(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Room room)
        {
            room.setPutanja(resourceFolderPath+File.separator+ "JSonFiles" + File.separator+ room.getIme());
            ApplicationFramework.getInstance().getJacksonSerializer().saveRoom(room);
        }
        else return;
    }
}
