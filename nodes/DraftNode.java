package raf.draft.dsw.model.nodes;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import raf.draft.dsw.model.elements.*;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import java.util.Objects;

@Setter
@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Room.class, name = "Room"),
        @JsonSubTypes.Type(value = Building.class, name = "Building"),
        @JsonSubTypes.Type(value = Project.class, name = "Project"),
        @JsonSubTypes.Type(value = Bojler.class, name = "Bojler"),
        @JsonSubTypes.Type(value = Kada.class, name = "Kada"),
        @JsonSubTypes.Type(value = Krevet.class, name = "Krevet"),
        @JsonSubTypes.Type(value = LavaboK.class, name = "LavaboK"),
        @JsonSubTypes.Type(value = Ormar.class, name = "Ormar"),
        @JsonSubTypes.Type(value = Sto.class, name = "Sto"),
        @JsonSubTypes.Type(value = VesMasina.class, name = "VesMasina"),
        @JsonSubTypes.Type(value = Vrata.class, name = "Vrata"),
        @JsonSubTypes.Type(value = WCSolja.class, name = "WCSolja"),
})
public abstract class DraftNode {
    private String ime;
    private String  autor;
    private String putanja;
    @ToString.Exclude
    @JsonIgnore
    private  DraftNode parent;

    public DraftNode(String ime, String autor,String putanja,DraftNode parent) {
        this.ime = ime;
        this.autor = autor;
        this.putanja = putanja;
        this.parent = parent;
    }
    public DraftNode(String ime,DraftNode parent){
        this.ime = ime;
        this.parent = parent;
    }
    @JsonCreator
    public DraftNode(
            @JsonProperty("ime") String ime,
            @JsonProperty("autor") String autor,
            @JsonProperty("putanja") String putanja) {
        this.ime = ime;
        this.autor = autor;
        this.putanja = putanja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DraftNode draftNode = (DraftNode) o;
        return Objects.equals(ime, draftNode.ime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime);
    }

}
