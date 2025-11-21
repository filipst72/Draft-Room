package raf.draft.dsw.model.structures;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.util.List;

public class ProjectExplorer extends DraftNodeComposite {

    public ProjectExplorer(String ime) {
        super(ime, null, null, null);
    }

    @Override
    public void addChild(DraftNode node) {
        if (node != null && node instanceof Project ){
            Project project = (Project) node;
            if (!this.getChildren().contains(project)){
                this.getChildren().add(project);
                project.setParent(this);
            }
        }
    }

    @Override
    public void removeChild(DraftNode node) {
       // ApplicationFramework.getInstance().getDraftRoomRepository().removeChild();
        this.getChildren().remove(node);
    }
}
