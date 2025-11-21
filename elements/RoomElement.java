package raf.draft.dsw.model.elements;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.Leaf;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;

@Getter@Setter
public abstract class RoomElement extends Leaf implements Prototype{
    private float sirina;
    private float visina;
    private Point lokacija;
    private Point pocetnaLokacija = new Point();
    private int rotacija;

    public RoomElement(String ime, Room parent, float sirina, float visina, Point lokacija, int rotacija) {
        super(ime, parent);
        this.sirina = sirina * parent.getRatioS();
        this.visina = visina * parent.getRatioV();
        this.pocetnaLokacija=lokacija.getLocation();
        this.lokacija = lokacija;
        this.rotacija=rotacija;

    }
    public RoomElement(String ime, DraftNode parent) {
        super(ime, parent);

    }

    @Override
    public abstract Prototype clone();
}
