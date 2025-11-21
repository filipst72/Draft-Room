package raf.draft.dsw.gui.swing.tab.commands.implementation;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.painters.ElementPainter;

import javax.swing.*;

public class AddCommand extends AbstractCommand {
    RoomView rw;
    RoomElement re;
    Boolean izbrisan = false;
    ElementPainter elementPainter;

    public AddCommand(RoomView rw, RoomElement re, ElementPainter el) {
        this.rw = rw;
        this.re = re;
        this.elementPainter = el;
    }

    @Override
    public void doCommand() {
        if(!(rw.getPainterList().contains(elementPainter))) rw.getPainterList().add(elementPainter);
        rw.getRoom().addChild(re);
        if(izbrisan)
        {
            rw.getSelectedList().add(elementPainter);
            izbrisan = false;
        }
        MainFrame.getInstance().getDraftTree().addChildRoom(rw.getDraftTreeItem(), re);
    }

    @Override
    public void undoCommand() {
        rw.getPainterList().remove(elementPainter);
        if(rw.getSelectedList().remove(elementPainter))
            izbrisan = true;
        rw.getRoom().removeChild(re);
        for(int i = 0; i < rw.getDraftTreeItem().getChildCount(); i++)
        {
            if(((DraftTreeItem)rw.getDraftTreeItem().getChildAt(i)).getDraftNode().equals(elementPainter.getDraftNode()))
            {
                MainFrame.getInstance().getDraftTree().removeChild(((DraftTreeItem)rw.getDraftTreeItem().getChildAt(i)));
            }
        }

        rw.ofarbaj();
    }
}
