package raf.draft.dsw.gui.swing.tab.commands.implementation;

import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MoveSelectedCommand extends AbstractCommand {

    Point stari = new Point(), novi = new Point();
    RoomElement element;
    RoomView roomView;
    Map<RoomElement, Point> noviMapa = new HashMap<>();
    Map<RoomElement, Point> stariMapa = new HashMap<>();

    //ArrayList<Point> tacke = new ArrayList<>();


    public MoveSelectedCommand(RoomView roomView, Map<RoomElement, Point> stari, Map<RoomElement, Point> novi) {
        this.roomView = roomView;
        this.stariMapa.putAll(stari);
        this.noviMapa.putAll(novi);
    }

    public MoveSelectedCommand(Point stari, Point novi, RoomElement element, RoomView roomView) {
        this.stari.x = stari.x;
        this.stari.y = stari.y;
        this.novi.x = novi.x;
        this.novi.y = novi.y;
        this.element = element;
        this.roomView = roomView;
    }

    @Override
    public void doCommand() {
        for (Map.Entry<RoomElement, Point> entry : noviMapa.entrySet()) {
            RoomElement element = entry.getKey();
            Point novi = entry.getValue();
            element.getLokacija().x = novi.x;
            element.getLokacija().y = novi.y;
        }
        /*element.getLokacija().x = novi.x;
        element.getLokacija().y = novi.y;*/
        roomView.ofarbaj();
    }

    @Override
    public void undoCommand() {
        for (Map.Entry<RoomElement, Point> entry : stariMapa.entrySet()) {
            RoomElement element = entry.getKey();
            Point stari = entry.getValue();
            element.getLokacija().x = stari.x;
            element.getLokacija().y = stari.y;
        }

        /*element.getLokacija().x = stari.x;
        element.getLokacija().y = stari.y;*/
        roomView.ofarbaj();
    }
}
