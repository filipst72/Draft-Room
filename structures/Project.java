package raf.draft.dsw.model.structures;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.util.List;

@Getter@Setter
@JsonSerialize
public class Project extends DraftNodeComposite {
    private String  autor;
    private String putanja;
    @JsonIgnore
    boolean otvoren;


    @JsonCreator
    public Project(
            @JsonProperty("ime") String ime,
            @JsonProperty("autor") String autor,
            @JsonProperty("putanja") String putanja,
            @JsonProperty("children") List<DraftNode> children) {
        super(ime, null);
        this.autor = autor;
        this.putanja = putanja;
        this.otvoren = false;
        this.setChildren(children);
        for(DraftNode child : children)
            child.setParent(this);

    }

    public Project(String ime, String autor, String putanja, DraftNode parent) {
        super(ime, parent);
        this.autor=autor;
        this.putanja=putanja;
        otvoren=false;
    }

    @Override
    public void addChild(DraftNode node) {
        if (node != null && node instanceof Room room){
            if (!this.getChildren().contains(room)){
                this.getChildren().add(room);
                room.setParent(this);
            }
        }
        else if (node != null && node instanceof Building building){
            if (!this.getChildren().contains(building)){
                this.getChildren().add(building);
                building.setParent(this);
            }
        }
    }

    @Override
    public void removeChild(DraftNode node) {
        this.getChildren().remove(node);
    }
}
