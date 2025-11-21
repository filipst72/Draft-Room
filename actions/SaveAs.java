package raf.draft.dsw.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.structures.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAs extends AbstractRoomAction{
    public SaveAs(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,
                ActionEvent.ALT_MASK));
        putValue(NAME, "SaveAs");
        putValue(SHORT_DESCRIPTION, "SaveAs");
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

        if (!(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project)) return;

        Project project = (Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode();
        File projectFile = null;

        if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = jfc.getSelectedFile();
                project.setPutanja(projectFile.getPath());
        } else {
                return;
        }

        ApplicationFramework.getInstance().getJacksonSerializer().saveProject(project);

    }
}
