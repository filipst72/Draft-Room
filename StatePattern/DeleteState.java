package raf.draft.dsw.controller.StatePattern;

import raf.draft.dsw.controller.State;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.tab.commands.implementation.RemoveCommand;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.painters.*;

import java.awt.*;

public class DeleteState implements State {
    public DeleteState()
    {}
    @Override
    public void misKliknut(RoomView room, Point point) {

        ElementPainter ep = null;
        for(ElementPainter e: room.getPainterList()){
            if(e.elementAt(point)){
                ep=e;

            }
        }
        AbstractCommand remove = new RemoveCommand(room, ep);
        room.getCommandManager().addCommand(remove);



        /* if(ep instanceof KrevetPainter) room.getRoom().removeChild((
       (KrevetPainter)ep).getDraftNode());
        if(ep instanceof BojlerPainter) room.getRoom().removeChild(((BojlerPainter)ep).getDraftNode());
        if(ep instanceof KadaPainter) room.getRoom().removeChild(((KadaPainter)ep).getDraftNode());
        if(ep instanceof LavaboPainter) room.getRoom().removeChild(((LavaboPainter)ep).getDraftNode());
        if(ep instanceof OrmarPainter) room.getRoom().removeChild(((OrmarPainter)ep).getDraftNode());
        if(ep instanceof StoPainter) room.getRoom().removeChild(((StoPainter)ep).getDraftNode());
        if(ep instanceof VesMasinaPainter) room.getRoom().removeChild(((VesMasinaPainter)ep).getDraftNode());
        if(ep instanceof VrataPainter) room.getRoom().removeChild(((VrataPainter)ep).getDraftNode());
        if(ep instanceof WCSoljaPainter) room.getRoom().removeChild(((WCSoljaPainter)ep).getDraftNode());*/

    }

    @Override
    public void misPritisnut(RoomView room, Point point) {

    }

    @Override
    public void misPusten(RoomView room, Point point) {

    }

    @Override
    public void misUsao(RoomView room, Point point) {

    }

    @Override
    public void misIzasao(RoomView room, Point point) {

    }

    @Override
    public void misVuce(RoomView room, Point point) {

    }

    @Override
    public void misPomeren(RoomView room, Point point) {

    }

    @Override
    public void misTocakPomeren(RoomView room, Point point, int brojOkreta) {

    }
}
