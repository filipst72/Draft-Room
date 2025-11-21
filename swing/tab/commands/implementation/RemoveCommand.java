package raf.draft.dsw.gui.swing.tab.commands.implementation;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.painters.ElementPainter;

import java.util.ArrayList;
import java.util.List;

public class RemoveCommand extends AbstractCommand {
    RoomView roomView;
    List<ElementPainter> elements = new ArrayList<>();
    ElementPainter selectedElement;

    public RemoveCommand(RoomView roomView, List<ElementPainter> elements) {
        this.roomView = roomView;
        for (ElementPainter element : elements) {
            this.elements.add(element);
        }
    }

    public RemoveCommand(RoomView roomView, ElementPainter selectedElement) {
        this.roomView = roomView;
        this.selectedElement = selectedElement;
    }

    @Override
    public void doCommand() {

        if(!roomView.getSelectedList().isEmpty())
        {
            for(ElementPainter ep:roomView.getSelectedList())
            {
                for(ElementPainter e: roomView.getPainterList()){
                    if(e.equals(ep)){
                        for(int i = 0;i < roomView.getDraftTreeItem().getChildCount(); i++)
                        {
                            if(e.getDraftNode().equals(((DraftTreeItem)roomView.getDraftTreeItem().getChildAt(i)).getDraftNode())) {
                                MainFrame.getInstance().getDraftTree().removeChild(((DraftTreeItem) roomView.getDraftTreeItem().getChildAt(i)));

                                roomView.getRoom().removeChild(ep.getDraftNode());
                            }
                        }
                    }
                }
            }
            roomView.getPainterList().removeAll(roomView.getSelectedList());
            roomView.getSelectedList().clear();
        }
        else{
            if(selectedElement!=null){
                for(int i = 0;i < roomView.getDraftTreeItem().getChildCount(); i++)
                {
                    if(selectedElement.getDraftNode().equals(((DraftTreeItem)roomView.getDraftTreeItem().getChildAt(i)).getDraftNode()))
                        MainFrame.getInstance().getDraftTree().removeChild(((DraftTreeItem)roomView.getDraftTreeItem().getChildAt(i)));

                }
                roomView.getPainterList().remove(selectedElement);
                roomView.getRoom().removeChild(selectedElement.getDraftNode());
            }
        }

        roomView.ofarbaj();
    }

    @Override
    public void undoCommand() {
        if(!elements.isEmpty()){
           for(ElementPainter ep:elements)
            {
                roomView.getPainterList().add(ep);
                roomView.getSelectedList().add(ep);
                roomView.getRoom().addChild(ep.getDraftNode());
                MainFrame.getInstance().getDraftTree().addChildRoom(roomView.getDraftTreeItem(),(RoomElement) ep.getDraftNode());

            }
        }
        else
        {
            roomView.getPainterList().add(selectedElement);
            roomView.getRoom().addChild(selectedElement.getDraftNode());
            MainFrame.getInstance().getDraftTree().addChildRoom(roomView.getDraftTreeItem(),(RoomElement) selectedElement.getDraftNode());
        }
        roomView.ofarbaj();
    }
}
