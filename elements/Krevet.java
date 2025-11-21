package raf.draft.dsw.model.elements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;

@Getter@Setter
public class Krevet extends RoomElement{
    Color boja;
    Stroke stroke;
    public Krevet(String ime, Room parent, int sirina, int visina, Point lokacija, int rotacija) {
        super(ime,parent, sirina, visina, lokacija,rotacija);
    }
    @JsonCreator
    public Krevet(
            @JsonProperty("ime") String ime,
            @JsonProperty("sirina") int sirina,
            @JsonProperty("visina") int visina,
            @JsonProperty("lokacija") Point lokacija,
            @JsonProperty("rotacija") int rotacija
    ) {
        //super(ime, null, sirina, visina, lokacija, rotacija);
        super(ime, null);
        this.setVisina(visina);
        this.setSirina(sirina);
        this.setLokacija(lokacija);
        this.setRotacija(rotacija);
    }

    @Override
    public Prototype clone() {
        return new Krevet(this.getIme(),(Room)this.getParent(),
                (int)(this.getSirina()/ ((Room) this.getParent()).getRatioS()),(int)(this.getVisina()/ ((Room) this.getParent()).getRatioV()),this.getLokacija(),this.getRotacija());
    }
}
