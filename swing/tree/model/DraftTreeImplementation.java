package raf.draft.dsw.gui.swing.tree.model;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.node_factory.NodeFactory;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.DraftTree;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.model.elements.Krevet;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;
@Setter
@Getter
public class DraftTreeImplementation implements DraftTree {
    private DraftTreeView treeView;
    private DefaultTreeModel treeModel;
    DraftTreeItem root;
    @Override
    public DraftTreeView generateTree(ProjectExplorer projectExplorer) {
        root = new DraftTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new DraftTreeView(treeModel);

        return treeView;
    }

    @Override
    public void removeChild(DraftTreeItem node) {
        if (node.getDraftNode() instanceof ProjectExplorer)
            return;
        node.removeFromParent();
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        ((DraftNodeComposite)node.getDraftNode().getParent()).getChildren().remove(node.getDraftNode());

        ApplicationFramework.getInstance().getMessageGenerator().desavanjaUModelu(MessageType.IzbrisanItem, node.getDraftNode());

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        treeView.setSelectionRow(0);
    }
    @Override
    public void obrisanCvor(DraftTreeItem parent, RoomElement roomElement){

      // TODO

        ((DraftNodeComposite)parent.getDraftNode()).getChildren().remove(roomElement);
        ApplicationFramework.getInstance().getMessageGenerator().desavanjaUModelu(MessageType.IzbrisanItem, roomElement);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public DraftTreeItem getSelectedNode() {
        return (DraftTreeItem) treeView.getLastSelectedPathComponent();
    }

    public void dodaj(DraftTreeItem parent,DraftTreeItem node){
        if (!(parent.getDraftNode() instanceof DraftNodeComposite))
            return;

        parent.add(new DraftTreeItem(node.getDraftNode()));
        ((DraftNodeComposite) parent.getDraftNode()).addChild(node.getDraftNode());
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        //NodeFactory.dodajUModel(parent.getDraftNode(), node.getDraftNode());
        ((DraftNodeComposite) parent.getDraftNode()).addChild(node.getDraftNode());
    }

    @Override
    public void createChild2(DraftTreeItem parent,String ime,String autor,String putanja,Boolean room,Boolean building) {
        DraftNode node= NodeFactory.fabrika(parent.getDraftNode(),ime,autor,putanja,room,building);
        dodaj(parent,new DraftTreeItem(node));
        ApplicationFramework.getInstance().getMessageGenerator().desavanjaUModelu(MessageType.DodatItem,node);
    }

    @Override
    public DraftNode addChildRoom(DraftTreeItem parent, RoomElement roomElement) {
        if (!(parent.getDraftNode() instanceof DraftNodeComposite))
            return null;

        parent.add(new DraftTreeItem(roomElement));
        ((DraftNodeComposite) parent.getDraftNode()).addChild(roomElement);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        return roomElement;
    }

    @Override
    public void loadProject(Project node){
        DraftTreeItem proj = new DraftTreeItem(node);
        root.add(proj);
        for(DraftNode draftNode: node.getChildren())
        {
            //draftNode.setParent(node);
            DraftTreeItem building = new DraftTreeItem(draftNode);
            proj.add(building);
            if(draftNode instanceof Building)
            {

                for(DraftNode draftNode1: ((Building) draftNode).getChildren())
                {
                    DraftTreeItem room = new DraftTreeItem(draftNode1);
                    building.add(room);

                        for (DraftNode draftNode2: ((Room) draftNode1).getChildren())
                        {
                            room.add(new DraftTreeItem(draftNode2));

                        }
                }
            }
            if(draftNode instanceof Room)
            {

                for (DraftNode draftNode2: ((Room) draftNode).getChildren())
                {
                    building.add(new DraftTreeItem(draftNode2));

                }
            }
        }
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }
    public void osvezi()
    {
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }
}
