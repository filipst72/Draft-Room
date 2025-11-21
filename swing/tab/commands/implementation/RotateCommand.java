package raf.draft.dsw.gui.swing.tab.commands.implementation;

import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.painters.ElementPainter;

import java.util.ArrayList;
import java.util.List;

public class RotateCommand extends AbstractCommand {
    RoomView roomView;
    List<ElementPainter> rotirani = new ArrayList<ElementPainter>();
    boolean rotiranjeDesno;
    public RotateCommand(RoomView roomView, boolean rotiranjeDesno) {
        this.rotirani = roomView.getSelectedList();
        this.roomView = roomView;
        this.rotiranjeDesno = rotiranjeDesno;
    }

    @Override
    public void doCommand() {
        if(rotiranjeDesno) {
            for(ElementPainter elementPainter:roomView.getSelectedList()){
                ((RoomElement)elementPainter.getDraftNode()).setRotacija(((RoomElement)elementPainter.getDraftNode()).getRotacija()+1);
            }
        }
        else
        {
            for(ElementPainter elementPainter:roomView.getSelectedList()){
                ((RoomElement)elementPainter.getDraftNode()).setRotacija(((RoomElement)elementPainter.getDraftNode()).getRotacija()-1);
            }
        }
        roomView.ofarbaj();
    }

    @Override
    public void undoCommand() {
        if(!rotiranjeDesno) {
            for(ElementPainter elementPainter:roomView.getSelectedList()){
                ((RoomElement)elementPainter.getDraftNode()).setRotacija(((RoomElement)elementPainter.getDraftNode()).getRotacija()+1);
            }
        }
        else
        {
            for(ElementPainter elementPainter:roomView.getSelectedList()){
                ((RoomElement)elementPainter.getDraftNode()).setRotacija(((RoomElement)elementPainter.getDraftNode()).getRotacija()-1);
            }
        }
        roomView.ofarbaj();
    }
}
