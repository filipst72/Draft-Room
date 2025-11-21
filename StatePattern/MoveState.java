package raf.draft.dsw.controller.StatePattern;

import raf.draft.dsw.controller.State;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.tab.commands.implementation.MoveSelectedCommand;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.painters.ElementPainter;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MoveState implements State {
    private Point dragStartPoint;
    Point pomeran;
    Point Pocetni;
    Map<RoomElement, Point> stariPoint = new HashMap<RoomElement, Point>();
    Map<RoomElement, Point> noviPoint = new HashMap<>();
    @Override
    public void misKliknut(RoomView room, Point point) {

    }

    @Override
    public void misPritisnut(RoomView room, Point point) {
        dragStartPoint = point;
       int zumiranX =
               (int) ((dragStartPoint.getX() - room.getMousePoint().getX()) / room.getScalingFactor() + room.getMousePoint().getX());
        int zumiranY =
                (int) ((dragStartPoint.getY() - room.getMousePoint().getY()) / room.getScalingFactor() + room.getMousePoint().getY());
        dragStartPoint = new Point((int) zumiranX, (int) zumiranY);
    }

    @Override
    public void misPusten(RoomView room, Point point) {
        for(ElementPainter selected: room.getSelectedList())
        {
            Point stari = ((RoomElement) selected.getDraftNode()).getPocetnaLokacija();
            for (ElementPainter element: room.getPainterList()) {

                if(selected.getHitbox().intersects(element.getHitbox()) && !selected.equals(element))
                {
                    ((RoomElement)selected.getDraftNode()).setLokacija(stari.getLocation());
                    room.ofarbaj();
                }
            }


        }
        for(ElementPainter selected: room.getSelectedList())
        {
            noviPoint.put((RoomElement) selected.getDraftNode(), new Point(((RoomElement)selected.getDraftNode()).getLokacija().x, ((RoomElement)selected.getDraftNode()).getLokacija().y));
            stariPoint.put((RoomElement) selected.getDraftNode(), new Point(((RoomElement)selected.getDraftNode()).getPocetnaLokacija().x, ((RoomElement)selected.getDraftNode()).getPocetnaLokacija().y));
        }

        AbstractCommand moveCommand = new MoveSelectedCommand(room, stariPoint, noviPoint);
        room.getCommandManager().addCommand(moveCommand);

        for(ElementPainter selected: room.getSelectedList())
        {
            ((RoomElement)selected.getDraftNode()).getPocetnaLokacija().x = ((RoomElement)selected.getDraftNode()).getLokacija().x;
            ((RoomElement)selected.getDraftNode()).getPocetnaLokacija().y = ((RoomElement)selected.getDraftNode()).getLokacija().y;
        }

        room.ofarbaj();
        //room.move(point);
        dragStartPoint = null;
    }

    @Override
    public void misUsao(RoomView room, Point point) {

    }

    @Override
    public void misIzasao(RoomView room, Point point) {

    }

    @Override
    public void misVuce(RoomView room, Point point) {
        double zumiranX = (point.getX() - room.getMousePoint().getX()) / room.getScalingFactor() + room.getMousePoint().getX();
        double zumiranY = (point.getY() - room.getMousePoint().getY()) / room.getScalingFactor() + room.getMousePoint().getY();
        point = new Point((int) zumiranX, (int) zumiranY);

        pomeran = new Point((int) (point.getX() - dragStartPoint.getX()), (int) (point.getY() - dragStartPoint.getY()));
        if(room.getSelectedList().isEmpty())
        {
            room.setMousePoint(point);
        }
        move(pomeran,  room);
        dragStartPoint = point;
        room.ofarbaj();
    }

    public void move(Point point, RoomView roomView)
    {
       /* int x = roomView.getX() + point.x;
        int y = roomView.getY() + point.y;
        roomView.setBounds(x, y, roomView.getWidth(), roomView.getHeight());*/

        for(ElementPainter selected:roomView.getSelectedList()) {
            int roomX = 30;
            int roomY = 30;
            int roomWidth = roomView.getWidth() - 60;
            int roomHeight = roomView.getHeight() - 60;
            int snapMargin = 20;
            double selectedW = selected.getHitbox().getWidth();
            double selectedH = selected.getHitbox().getHeight();
            Point original = ((RoomElement) selected.getDraftNode()).getLokacija();

            original.x += (int) point.getX();
            original.y += (int) point.getY();



           /* original.x = (int) Math.max(roomX, Math.min(original.x, roomX + roomWidth - selected.getHitbox().getWidth()));
            original.y = (int) Math.max(roomY, Math.min(original.y,
                    roomY + roomHeight - selected.getHitbox().getHeight()));*/
            double leftEdge = original.x - selectedW / 2;
            double rightEdge = original.x + selectedW / 2;
            double topEdge = original.y - selectedH / 2;
            double bottomEdge = original.y + selectedH / 2;
            boolean zakacenY = false;
            boolean zakacenX = false;
            if (!selected.isZakacen()) {
                if (Math.abs(leftEdge - roomX) <= snapMargin) {
                    original.x = (int) (roomX + selectedW / 2);
                    zakacenX = true;
                } else if (Math.abs(rightEdge - (roomX + roomWidth)) <= snapMargin) {
                    original.x = (int) (roomX + roomWidth - selectedW / 2);
                    zakacenX = true;
                } else {
                    original.x = (int) Math.max(roomX + selectedW / 2, Math.min(original.x, roomX + roomWidth - selectedW / 2));
                }

                if (Math.abs(topEdge - roomY) <= snapMargin) {
                    original.y = (int) (roomY + selectedH / 2);
                    zakacenY = true;
                } else if (Math.abs(bottomEdge - (roomY + roomHeight)) <= snapMargin) {
                    original.y = (int) (roomY + roomHeight - selectedH / 2);
                    zakacenY = true;
                } else {
                    original.y = (int) Math.max(roomY + selectedH / 2, Math.min(original.y, roomY + roomHeight - selectedH / 2));
                }
                selected.setZakacen(zakacenY || zakacenX);
            } else {
                original.x = (int) Math.max(roomX + selectedW / 2, Math.min(original.x, roomX + roomWidth - selectedW / 2));
                original.y = (int) Math.max(roomY + selectedH / 2, Math.min(original.y, roomY + roomHeight - selectedH / 2));
                if (Math.abs(topEdge - roomY) >= snapMargin && Math.abs(bottomEdge - (roomY + roomHeight)) >= snapMargin)
                    zakacenY = false;
                else zakacenY = true;
                if (Math.abs(leftEdge - roomX) >= snapMargin && Math.abs(rightEdge - (roomX + roomWidth)) >= snapMargin)
                    zakacenX = false;
                else zakacenX = true;
                selected.setZakacen(zakacenY || zakacenX);
            }
        }
    }

    @Override
    public void misPomeren(RoomView room, Point point) {

    }

    @Override
    public void misTocakPomeren(RoomView room, Point point, int brojOkreta) {

    }
}
