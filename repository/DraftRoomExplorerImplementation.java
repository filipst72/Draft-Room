package raf.draft.dsw.model.repository;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

@Setter
@Getter
public class DraftRoomExplorerImplementation implements DraftRoomRepository{
    private ProjectExplorer projectExplorer;

    public DraftRoomExplorerImplementation(){
        this.projectExplorer = new ProjectExplorer("My Project Explorer");
    }

    @Override
    public ProjectExplorer getRoot() {
        return this.projectExplorer;
    }

    @Override
    public void addChild(DraftNodeComposite parent, DraftNode child) {
        parent.addChild(child);
    }

    @Override
    public void removeChild(DraftNode node) {
        if(node instanceof DraftNodeComposite){
            ((DraftNodeComposite) node).getChildren().removeAll(((DraftNodeComposite) node).getChildren());
            DraftNodeComposite root= (DraftNodeComposite) node.getParent();
            root.removeChild(node);
        }else{
            DraftNodeComposite parent= (DraftNodeComposite) node.getParent();
            parent.removeChild(node);
        }
    }

}
