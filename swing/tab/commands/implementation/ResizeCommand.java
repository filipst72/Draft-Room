package raf.draft.dsw.gui.swing.tab.commands.implementation;

import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.painters.ElementPainter;

public class ResizeCommand extends AbstractCommand {

    RoomView rw;
    RoomElement re;
    ElementPainter elementPainter;
    int sirina,visina, novaVisina, novaSirina;

    public ResizeCommand(RoomView rw, RoomElement re, ElementPainter el,int sirina,int visina, int novaSirina, int novaVisina) {
        this.rw = rw;
        this.re = re;
        this.elementPainter = el;
        this.sirina=sirina;
        this.visina=visina;
        this.novaVisina=novaVisina;
        this.novaSirina=novaSirina;
    }

    @Override
    public void doCommand() {
        rw.getPainterList().remove(elementPainter);
        re.setVisina(novaVisina);
        re.setSirina(novaSirina);
        rw.getPainterList().add(elementPainter);
        rw.ofarbaj();
    }

    @Override
    public void undoCommand() {
        rw.getPainterList().remove(elementPainter);
        re.setVisina(visina);
        re.setSirina(sirina);
        rw.getPainterList().add(elementPainter);
        rw.ofarbaj();
    }
}
