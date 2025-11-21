package raf.draft.dsw.controller.StatePattern;

import raf.draft.dsw.controller.State;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.tab.commands.implementation.ResizeCommand;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.painters.ElementPainter;

import java.awt.*;

public class ResizeState implements State {
    boolean cosakKliknut=false;
    int sirina,visina,NOVASIRINA,NOVAVISINA;
    @Override
    public void misKliknut(RoomView room, Point point) {

    }

    @Override
    public void misPritisnut(RoomView room, Point point) {
        int zumiranX = (int) ((point.x - room.getMousePoint().getX()) / room.getScalingFactor() + room.getMousePoint().getX());
        int zumiranY = (int) ((point.y - room.getMousePoint().getY()) / room.getScalingFactor() + room.getMousePoint().getY());
        point = new Point(zumiranX, zumiranY);
        Rectangle rectangle=new Rectangle((int)point.getX()-10,(int)point.getY()-10,20,20);
        if(room.getSelectedList().size()==1){
            for(ElementPainter ep:room.getSelectedList()){
                if(ep.intersect(rectangle)){
                    sirina=(int)((RoomElement)(ep.getDraftNode())).getSirina();
                    visina=(int)((RoomElement)(ep.getDraftNode())).getVisina();
                    cosakKliknut=true;
                    //ResizeCommand resizeCommand=new ResizeCommand(room,(RoomElement)ep.getDraftNode(),ep,sirina,visina);
                    //room.getCommandManager().addCommand(resizeCommand);
                }
            }
        }
        else{
            ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PreviseObjekata);
        }
    }

    @Override
    public void misPusten(RoomView room, Point point) {
        /*for(ElementPainter ep:room.getPainterList()){
            if(((RoomElement)ep.getDraftNode()).getVisina()==NOVAVISINA && ((RoomElement)ep.getDraftNode()).getSirina()==NOVASIRINA){
                ResizeCommand resizeCommand=new ResizeCommand(room,(RoomElement)ep.getDraftNode(),ep,NOVASIRINA,NOVAVISINA);
                room.getCommandManager().addCommand(resizeCommand);
            }
        }*/
        for(ElementPainter ep:room.getSelectedList()){
           AbstractCommand resizeCommand = new ResizeCommand(room,(RoomElement) ep.getDraftNode(), ep, sirina, visina, NOVASIRINA,NOVAVISINA);
            room.getCommandManager().addCommand(resizeCommand);
        }
        cosakKliknut=false;
    }

    @Override
    public void misUsao(RoomView room, Point point) {

    }

    @Override
    public void misTocakPomeren(RoomView room, Point point, int brojOkreta) {

    }
    @Override
    public void misIzasao(RoomView room, Point point) {

    }

    @Override
    public void misVuce(RoomView room, Point point) {
        if(cosakKliknut){
            uvelicava(room,point);
        }
        room.ofarbaj();
    }

    @Override
    public void misPomeren(RoomView room, Point point) {

    }
    public void uvelicava(RoomView roomView,Point point)
    {
        for(ElementPainter selected:roomView.getSelectedList()) {
            double selectedW = ((RoomElement)(selected.getDraftNode())).getSirina();
            double selectedH = ((RoomElement)(selected.getDraftNode())).getVisina();
            double originalX = ((RoomElement) selected.getDraftNode()).getLokacija().getX();
            int dodavanje=(int)(point.getX()-(originalX+selectedW));
            NOVASIRINA=(int)selectedW+dodavanje;
            NOVAVISINA=(int)selectedH+dodavanje;
            ((RoomElement)(selected.getDraftNode())).setSirina((int)(selectedW+dodavanje));
            ((RoomElement)(selected.getDraftNode())).setVisina((int)(selectedH+dodavanje));
        }
    }
}
