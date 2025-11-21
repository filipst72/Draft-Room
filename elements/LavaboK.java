package raf.draft.dsw.model.elements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;

public class LavaboK extends RoomElement{
    public LavaboK(String ime, Room parent, int sirina, int visina, Point lokacija, int rotacija) {
        super(ime, parent, sirina, visina, lokacija, rotacija);
    }
    @JsonCreator
    public LavaboK(
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
        return new LavaboK(this.getIme(),(Room)this.getParent(),
                (int)(this.getSirina()/ ((Room) this.getParent()).getRatioS()),(int)(this.getVisina()/ ((Room) this.getParent()).getRatioV()),this.getLokacija(),this.getRotacija());
    }
}
