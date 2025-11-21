package raf.draft.dsw.gui.swing.tab;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.MouseActions;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.commands.CommandManager;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;

import raf.draft.dsw.model.elements.*;

import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.messages.MessageType;

import raf.draft.dsw.model.messages.Notification;
import raf.draft.dsw.model.messages.NotificationType;
import raf.draft.dsw.model.painters.*;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter@Setter
public class RoomView extends JPanel implements ISubscriber {
    List<ElementPainter> painterList=new ArrayList<>();
    List<ElementPainter> selectedList=new ArrayList<>();
    List<RoomElement> newItems;

    JLabel projbuilding;
    JLabel autor;
    String ime;
    Color color;
    Room room;
    DraftTreeItem draftTreeItem;
    MouseActions mouseActions;
    Graphics graphics;
    private boolean nacrtatiSobu;
    float ratio;
    Point gornjiLevi;
    Point donjiDesni;
    Rectangle selectionRectangle;
    AffineTransform affineTransformStart;
    Graphics2D g2;
    AffineTransform affineTransformRotate;
    Rectangle roomHitbox;
    double scalingFactor = 1;
    Point2D mousePoint = new Point2D.Double(0,0);
    CommandManager commandManager = new CommandManager();

    public RoomView() {
        init();
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this,NotificationType.Repaint);

    }

    public void init(){
        projbuilding=new JLabel();
        autor=new JLabel();
        nacrtatiSobu=false;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(autor,SwingConstants.CENTER);
        add(projbuilding,SwingConstants.CENTER);
        mouseActions = new MouseActions(this);
        addMouseListener(mouseActions);
        addMouseMotionListener(mouseActions);
        addMouseWheelListener(mouseActions);
    }




    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //TODO
        g2=(Graphics2D) g;
        g2.translate(mousePoint.getX(),mousePoint.getY());
        g2.scale(scalingFactor,scalingFactor);
        g2.translate(-mousePoint.getX(),-mousePoint.getY());
        affineTransformStart = g2.getTransform();
        affineTransformRotate = g2.getTransform();
        if(nacrtatiSobu || room.isNacrtatiSobu()) {
            g2.setColor(Color.BLACK);
            g2.drawRect(30, 30, getWidth() - 60, getHeight() - 60);
            roomHitbox = new Rectangle(30, 30, getWidth() - 60,
                    getHeight() - 60);
        }
        for(ElementPainter p1:painterList){
            int centarX = (int)((RoomElement)p1.getDraftNode()).getLokacija().getX();
            int centarY = (int)((RoomElement)p1.getDraftNode()).getLokacija().getY();
            g2.setTransform(affineTransformStart);
            if(((RoomElement) p1.getDraftNode()).getRotacija() < 0)
            {
                for(int i = ((RoomElement) p1.getDraftNode()).getRotacija(); i < 0; i++)
                {
                    g2.rotate(-Math.PI/2, centarX, centarY);
                }
            }
            else{
                for(int i = 0; i < ((RoomElement) p1.getDraftNode()).getRotacija(); i++)
                {
                    g2.rotate(Math.PI/2, centarX, centarY);
                }
            }

            if(!selectedList.contains(p1)){
                p1.paint(g2);
            }
        }
        for(ElementPainter p1: selectedList) {
            //g2.setTransform(affineTransformRotate);
            int centarX = (int)((RoomElement)p1.getDraftNode()).getLokacija().getX();
            int centarY = (int)((RoomElement)p1.getDraftNode()).getLokacija().getY();
            g2.setTransform(affineTransformStart);
            if(((RoomElement) p1.getDraftNode()).getRotacija() < 0)
            {
                for(int i = ((RoomElement) p1.getDraftNode()).getRotacija(); i < 0; i++)
                {
                    g2.rotate(-Math.PI/2, centarX, centarY);
                }
            }
            else{
                for(int i = 0; i < ((RoomElement) p1.getDraftNode()).getRotacija(); i++)
                {
                    g2.rotate(Math.PI/2, centarX, centarY);
                }
            }
            g2.setColor(Color.blue);
            p1.paint(g2);
        }
        g2.setTransform(affineTransformStart);
        g2.setColor(Color.BLACK);
        if(gornjiLevi != null && donjiDesni != null)
        {
            int x = Math.min(gornjiLevi.x, donjiDesni.x);
            int y = Math.min(gornjiLevi.y, donjiDesni.y);
            int width = Math.abs(gornjiLevi.x - donjiDesni.x);
            int height = Math.abs(gornjiLevi.y - donjiDesni.y);
            g2.setColor(color);
            selectionRectangle = new Rectangle(x, y, width, height);
            g2.draw(selectionRectangle);
        }
    }

    @Override
    public void update(Notification notification) {

        if(notification.getMessage().getPoruka().equals(NotificationType.Repaint.toString()))
        {
            this.repaint();
            MainFrame.getInstance().getProjectView().getTabbedPane().repaint();
        }
        if(notification.getMessage().getPoruka().equals(NotificationType.Nacrtaj.toString()))
        {
            this.nacrtatiSobu = true;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomView roomView = (RoomView) o;
        return Objects.equals(ime, roomView.ime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime);
    }
    public void ofarbaj()
    {
        this.repaint();
        MainFrame.getInstance().getProjectView().getTabbedPane().repaint();
    }
    public void rotateRight(double angle, int centarx, int centary) {
        affineTransformRotate.rotate(angle, centarx, centary);

    }
}
