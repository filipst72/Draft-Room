package raf.draft.dsw.gui.swing.tree.controller;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class DraftTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object clickedOn =null;
    private JTextField edit=null;
    public DraftTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        clickedOn =arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3 && !(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof ProjectExplorer)){
                ApplicationFramework.getInstance().getMessageGenerator().desavanjaUModelu(MessageType.IzmenjenItem,MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode());
                return true;
            }
        if (((MouseEvent)arg0).getClickCount()==2 && MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project){
            if(((Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode()).getChildren().isEmpty()){
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PrazanProjekat);
                return false;
            }
            //MainFrame.getInstance().getProjectView().kreirajTabove
            // (MainFrame.getInstance().getDraftTree().getSelectedNode());
            DraftTreeItem dtf = MainFrame.getInstance().getDraftTree().getSelectedNode();
            MainFrame.getInstance().getProjectView().kreirajTabove(dtf);
            MainFrame.getInstance().getProjectView().getTabbedPane().updateUI();
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!(clickedOn instanceof DraftTreeItem))
            return;

        DraftTreeItem clicked = (DraftTreeItem) clickedOn;
        clicked.setName(e.getActionCommand());

    }
}
