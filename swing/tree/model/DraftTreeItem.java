package raf.draft.dsw.gui.swing.tree.model;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;

import javax.swing.tree.DefaultMutableTreeNode;

@Setter
@Getter
public class DraftTreeItem extends DefaultMutableTreeNode {

    private DraftNode draftNode;

    public DraftTreeItem(DraftNode nodeModel) {
        this.draftNode = nodeModel;
    }

    @Override
    public String toString() {
        return draftNode.getIme();
    }

    public void setName(String name) {
        this.draftNode.setIme(name);
    }

}
