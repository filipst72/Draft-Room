package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;

public interface DraftTree {
    void obrisanCvor(DraftTreeItem parent, RoomElement roomElement);
    DraftNode addChildRoom(DraftTreeItem parent, RoomElement roomElement);
    DraftTreeView generateTree(ProjectExplorer projectExplorer);
    void removeChild(DraftTreeItem node);
    void createChild2(DraftTreeItem parent,String ime,String autor,String putanja,Boolean room,Boolean building);
    DraftTreeItem getSelectedNode();
    void loadProject(Project node);
    void osvezi();
}
