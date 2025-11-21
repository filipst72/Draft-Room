package raf.draft.dsw.model.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
public abstract class DraftNodeComposite extends DraftNode{

    List<DraftNode> children;

    public DraftNodeComposite(String ime, String autor, String putanja, DraftNode parent, List<DraftNode> children) {
        super(ime, autor, putanja, parent);
        this.children = children;
    }

    public DraftNodeComposite(String ime, String autor, String putanja,  DraftNode parent) {
        super(ime, "", null, parent);
        this.children = new ArrayList<>();
    }
    public DraftNodeComposite(String ime, DraftNode parent) {
        super(ime, parent);
        this.children = new ArrayList<>();
    }

    public abstract void addChild(DraftNode node);

    public abstract void removeChild(DraftNode node);

}
