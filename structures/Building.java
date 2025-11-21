package raf.draft.dsw.model.structures;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter@Setter
public class Building  extends DraftNodeComposite {
    @JsonIgnore
    private Random rand;
    private Color color;

    public Building(String ime, DraftNode parent) {
        super(ime,parent);
        rand=new Random();
        color=new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
    }
    @JsonCreator
    public Building(
            @JsonProperty("ime") String ime,
            @JsonProperty("color") Color color,
            @JsonProperty("children") List<DraftNode> children) {
        super(ime, null);
        this.color=color;
        this.setChildren(children);
        for(DraftNode child : children){
            child.setParent(this);
        }
    }
    @Override
    public void addChild(DraftNode node) {
        if (node != null && node instanceof Room room){
            if (!this.getChildren().contains(room)){
                this.getChildren().add(room);
                room.setParent(this);
            }
        }
    }

    @Override
    public void removeChild(DraftNode node) {
        //ApplicationFramework.getInstance().getDraftRoomRepository().getRoot().removeChild(node);
        /*DraftNode projekat =
                ApplicationFramework.getInstance().getDraftRoomRepository().getRoot().getChildren().get(ApplicationFramework.getInstance().getDraftRoomRepository().getRoot().getChildren().indexOf(node.getParent()));
        ((Project) projekat).getChildren().remove(node);*/
        this.getChildren().remove(node);
    }
}
