package raf.draft.dsw.gui.swing.tab;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.MouseActions;
import raf.draft.dsw.controller.StateManager;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.tab.commands.implementation.RemoveCommand;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.messages.Notification;
import raf.draft.dsw.model.messages.NotificationType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.painters.ElementPainter;
import raf.draft.dsw.model.painters.PainterFactory;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class ProjectView extends JPanel implements ISubscriber {
    JTabbedPane tabbedPane;
    JLabel ime;
    RoomView room;
    List<RoomView> taboviSoba;
    private StateManager stateManager;
    MouseActions mouseActions;

    public ProjectView(){
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this, NotificationType.DESAVANJA_U_MODELU);
        room=new RoomView();
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        taboviSoba=new ArrayList<>();
        tabbedPane.setSize(getWidth(),getHeight());
        add(tabbedPane);
        stateManager=new StateManager();

    }

    public void startAddState(){this.stateManager.setAddState();}
    public void startCopyPasteState(){this.stateManager.setCopyPasteState();}
    public void startDeleteState(){
        AbstractCommand remove = new RemoveCommand(this.getRoom(),
                this.getRoom().getSelectedList());
        this.getRoom().getCommandManager().addCommand(remove);

        this.stateManager.setDeleteState();
    }
    public void startEditRoomState(){this.stateManager.setEditRoomState();}
    public void startEditState(){this.stateManager.setEditState();}
    public void startMoveState(){this.stateManager.setMoveState();}
    public void startResizeState(){this.stateManager.setResizeState();}
    public void startRotateState(){this.stateManager.setRotateState();}
    public void startRotateLeftState(){this.stateManager.setRotateLeftState();}
    public void startSelectState(){this.stateManager.setSelectState();}
    public void startZoomState(){this.stateManager.setZoomState();}
    public void stopZoomState(){this.stateManager.setSelectState();}
    public void misKliknut(RoomView room, Point point){this.stateManager.getCurrentState().misKliknut(room, point);}
    public void misPritisnut(RoomView room, Point point){this.stateManager.getCurrentState().misPritisnut(room, point);}
    public void misPusten(RoomView room, Point point){this.stateManager.getCurrentState().misPusten(room, point);}
    public void misUsao(RoomView room, Point point){this.stateManager.getCurrentState().misUsao(room, point);}
    public void misIzasao(RoomView room, Point point){this.stateManager.getCurrentState().misIzasao(room, point);}
    public void misVuce(RoomView room, Point point){this.stateManager.getCurrentState().misVuce(room, point);}
    public void misPomeren(RoomView room, Point point){this.stateManager.getCurrentState().misPomeren(room, point);}
    public void misTocakPomeren(RoomView room, Point point, int wheelRotation){this.stateManager.getCurrentState().misTocakPomeren(room, point, wheelRotation);}
    @Override
    public void update(Notification notification) {
        if (notification.getMessage().getPoruka().equals(MessageType.IzbrisanItem.toString()))
            brisanjeTabova(notification.getDraftNode());
        if(notification.getMessage().getPoruka().equals(MessageType.DodatItem.toString()))
            dodajTab(notification.getDraftNode());
        if(notification.getMessage().getPoruka().equals(MessageType.IzmenjenItem.toString()))
            editovanNode(notification.getDraftNode());
    }

    public void kreirajTabove(DraftTreeItem projekat){
        if(((Project)projekat.getDraftNode()).isOtvoren()==false && projekat.getDraftNode() instanceof Project){
            for(DraftNode deca:((Project) projekat.getDraftNode()).getChildren()){
                if(deca instanceof Building) {
                    for (DraftNode sobe : ((Building) deca).getChildren()) {
                        room=new RoomView();
                        String proj=sobe.getParent().getParent().getIme()+" "+sobe.getParent().getIme();
                        String aut=sobe.getParent().getParent().getAutor();
                        room.getProjbuilding().setText(proj);
                        room.getAutor().setText(aut);
                        room.setIme(sobe.getIme());
                        room.setColor(((Building) deca).getColor());
                        room.setRoom((Room) sobe);
                        taboviSoba.add(room);
                        for(RoomElement re: room.getRoom().getElementiSobe())
                        {
                            room.getPainterList().add(PainterFactory.kreirajPainter(re));
                        }
                        tabbedPane.add(sobe.getIme(), room);
                        tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1,ime = new JLabel(sobe.getIme()));
                        tabbedPane.setBackgroundAt(tabbedPane.getTabCount()-1,((Building) deca).getColor());
                        dodajDraftTreeItemURoomView(room);
                        room.getRoom().addSubscriber(room, NotificationType.Repaint);
                    }
                }
                if(deca instanceof Room){
                    room=new RoomView();
                    String proj=deca.getParent().getIme()+"/ ";
                    String aut=deca.getParent().getAutor();
                    room.getProjbuilding().setText(proj);
                    room.getAutor().setText(aut);
                    room.setIme(deca.getIme());
                    room.setColor(Color.WHITE);
                    room.setRoom((Room) deca);
                    for(RoomElement re: room.getRoom().getElementiSobe())
                    {
                        room.getPainterList().add(PainterFactory.kreirajPainter(re));
                    }
                    taboviSoba.add(room);
                    room.getRoom().addSubscriber(room, NotificationType.Repaint);
                    tabbedPane.add(deca.getIme(), room);
                    tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1,ime = new JLabel(deca.getIme()));
                    tabbedPane.setBackgroundAt(tabbedPane.getTabCount()-1,Color.WHITE);
                    dodajDraftTreeItemURoomView(room);
                }
            }

            tabbedPane.setSelectedIndex(0);
            //velicinaSobe();
            ((Project)projekat.getDraftNode()).setOtvoren(true);
        }
    }

    public void brisanjeTabova(DraftNode node) {
        if (node instanceof Room) {
                for (int i = tabbedPane.getTabCount() - 1; i >= 0; i--) {
                    String title = tabbedPane.getTitleAt(i);
                       if (node.getIme().equals(title)) {
                            tabbedPane.remove(i);

                       }

            }
        }
        if (node instanceof Building) {
            for (RoomView rm : taboviSoba) {
                for (DraftNode sobe : ((Building) node).getChildren()) {
                    for (int i = 0; i <= tabbedPane.getTabCount() - 1; i++) {
                        if (rm.getRoom().equals(sobe) && rm.getIme().equals(tabbedPane.getTitleAt(i))) {
                           tabbedPane.remove(i);
                        }
                    }
                }
            }
        }

        if (node instanceof Project) {
            for (RoomView rm : taboviSoba) {
                for (DraftNode sobe : ((Project) node).getChildren()) {
                    if (sobe instanceof Room) {
                        for (int i = 0; i <= tabbedPane.getTabCount() - 1; i++) {
                            if (rm.getRoom().equals(sobe) && rm.getIme().equals(tabbedPane.getTitleAt(i))) {
                                tabbedPane.remove(i);
                            }
                        }
                    }
                    if (sobe instanceof Building) {
                        for (DraftNode sobeUzgradi : ((Building) sobe).getChildren()) {
                            for (int i = 0; i <= tabbedPane.getTabCount() - 1; i++) {
                                if (rm.getRoom().equals(sobeUzgradi) && rm.getIme().equals(tabbedPane.getTitleAt(i))) {
                                    tabbedPane.remove(i);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

        /*
        RoomView izbrisanaSoba;
        List<DraftNode> decaIzbrisanog=new ArrayList<>();
        if(node instanceof Room) {
            izbrisanaSoba=new RoomView();
            izbrisanaSoba.setIme(node.getIme());
            taboviSoba.remove(izbrisanaSoba);
        }
        else if(node instanceof Building){
            decaIzbrisanog.addAll(((DraftNodeComposite)node).getChildren());
            for(DraftNode df:decaIzbrisanog){
                izbrisanaSoba=new RoomView();
                izbrisanaSoba.setIme(df.getIme());
                taboviSoba.remove(izbrisanaSoba);
            }
        }
        else if(node instanceof Project){
            decaIzbrisanog.addAll(((DraftNodeComposite)node).getChildren());
            List<DraftNode> dd=new ArrayList<>();
            for(DraftNode tip:decaIzbrisanog){
                if(tip instanceof Building){
                    dd.addAll(((Building) tip).getChildren());
                }
            }
            decaIzbrisanog.addAll(dd);
            for(DraftNode df:decaIzbrisanog){
                izbrisanaSoba=new RoomView();
                izbrisanaSoba.setIme(df.getIme());
                taboviSoba.remove(izbrisanaSoba);
            }
        }
        tabbedPane.removeAll();
        editovanNode(node);
    }*/

    public void dodajTab(DraftNode node) {
        boolean otvoren = false;
        if (node.getParent() instanceof Project) {
            if (((Project) node.getParent()).isOtvoren()) {
                otvoren = true;
            }
        } else if (node.getParent() instanceof Building) {
            if ((node.getParent()).getParent() instanceof Project) {
                if (((Project) node.getParent().getParent()).isOtvoren()) {
                    otvoren = true;
                }
            }
        }
        if (node instanceof Room && otvoren == true) {
            if (node.getParent() instanceof Project) {
                room = new RoomView();
                String proj = node.getParent().getIme() + "/ ";
                String aut = node.getParent().getAutor();
                room.getProjbuilding().setText(proj);
                room.getAutor().setText(aut);
                room.setIme(node.getIme());
                room.setColor(Color.WHITE);
                room.setRoom((Room) node);
                for(RoomElement re: room.getRoom().getElementiSobe())
                {
                    room.getPainterList().add(PainterFactory.kreirajPainter(re));
                }
                taboviSoba.add(room);
                tabbedPane.add(node.getIme(), room);
                tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, ime = new JLabel(node.getIme()));
                tabbedPane.setBackgroundAt(tabbedPane.getTabCount() - 1, Color.WHITE);
                dodajDraftTreeItemURoomView(room);
                room.getRoom().addSubscriber(room, NotificationType.Repaint);
            } else if (node.getParent() instanceof Building) {
                room = new RoomView();
                String proj = node.getParent().getParent().getIme() + " " + node.getParent().getIme();
                String aut = node.getParent().getParent().getAutor();
                room.getProjbuilding().setText(proj);
                room.getAutor().setText(aut);
                room.setIme(node.getIme());
                room.setColor(((Building) node.getParent()).getColor());
                room.setRoom((Room) node);
                for(RoomElement re: room.getRoom().getElementiSobe())
                {
                    room.getPainterList().add(PainterFactory.kreirajPainter(re));
                }
                taboviSoba.add(room);
                tabbedPane.add(node.getIme(), room);
                tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, ime = new JLabel(node.getIme()));
                tabbedPane.setBackgroundAt(tabbedPane.getTabCount() - 1, ((Building) node.getParent()).getColor());
                dodajDraftTreeItemURoomView(room);
                room.getRoom().addSubscriber(room, NotificationType.Repaint);
            }
        }
    }


    public void editovanNode(DraftNode node){
        if(node instanceof Room){
            for(int i=tabbedPane.getTabCount()-1;i>=0;i--){
                for(RoomView rm:taboviSoba){
                    if(rm.getRoom().equals(node)){
                        if(tabbedPane.getTitleAt(i).equals(rm.getIme())) {

                            tabbedPane.setTabComponentAt(i, new JLabel( rm.getRoom().getIme()));
                            rm.setIme(rm.getRoom().getIme());
                            tabbedPane.setComponentAt(i, rm);
                            tabbedPane.setTitleAt(i, rm.getIme());
                        }
                    }
                }
            }
        }
        if(node instanceof Building){
            for(RoomView rm:taboviSoba){
                for(DraftNode sobe: ((Building) node).getChildren()){
                    for(int i=0;i<=tabbedPane.getTabCount()-1;i++){
                        if(rm.getRoom().equals(sobe) && rm.getIme().equals(tabbedPane.getTitleAt(i))){
                            rm.getProjbuilding().setText(node.getParent().getIme() + " "+rm.getRoom().getParent().getIme());
                            rm.getAutor().setText(node.getParent().getAutor());
                             //tabbedPane.setComponentAt(i, rm.getPanel());

                        }
                    }
                }
            }
        }

        if(node instanceof Project){
            for(RoomView rm:taboviSoba){
                for(DraftNode sobe: ((Project) node).getChildren()){
                    if(sobe instanceof Room) {
                        for (int i = 0; i <= tabbedPane.getTabCount() - 1; i++) {
                            if (rm.getRoom().equals(sobe) && rm.getIme().equals(tabbedPane.getTitleAt(i))) {
                                rm.getProjbuilding().setText(node.getIme() + "/");
                                rm.getAutor().setText(node.getAutor());
                                //tabbedPane.setComponentAt(i, rm.getPanel());

                            }
                        }
                    }
                    if(sobe instanceof Building){
                            for(DraftNode sobeUzgradi: ((Building) sobe).getChildren()){
                                for(int i=0;i<=tabbedPane.getTabCount()-1;i++){
                                    if(rm.getRoom().equals(sobeUzgradi) && rm.getIme().equals(tabbedPane.getTitleAt(i))){
                                        rm.getProjbuilding().setText(node.getIme() + " "+rm.getRoom().getParent().getIme());
                                        rm.getAutor().setText(node.getAutor());
                                        //tabbedPane.setComponentAt(i,rm.getPanel());
                                    }
                                }
                            }
                        }
                    }
                }
            }

        tabbedPane.repaint();
        tabbedPane.revalidate();
        /*
        tabbedPane.removeAll();
        for(RoomView sobe:taboviSoba) {
            tabbedPane.add(sobe.getIme(), sobe.getPanel());
            tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, ime = new JLabel(sobe.getIme()));
            tabbedPane.setBackgroundAt(tabbedPane.getTabCount() - 1,sobe
            .getColor());
        }*/
        }
/*
        public void velicinaSobe()
        {
            tabbedPane.addChangeListener(new ChangeListener() {
                private boolean[] shownDialog = new boolean[tabbedPane.getTabCount()];

                @Override
                public void stateChanged(ChangeEvent e) {
                    int selectedIndex;

                    if (tabbedPane.getTabCount() > 0) {

                        selectedIndex = tabbedPane.getSelectedIndex();
                        if (!shownDialog[selectedIndex]) {
                            UnosVelicineSobe unos = new UnosVelicineSobe();

                            for (RoomView rm : taboviSoba) {
                                if (rm.getIme().equals(tabbedPane.getTitleAt(selectedIndex))) {
                                    rm.getRoom().setDuzina(unos.getX());
                                    rm.getRoom().setSirina(unos.getY());
                                }
                            }
                            shownDialog[selectedIndex] = true;
                        }
                    }


                }
            });
        }*/

    public RoomView getRoom() {
        for(RoomView rw: taboviSoba)
        {
            if(rw.getRoom().getIme().equals(tabbedPane.getTitleAt(tabbedPane.getSelectedIndex())))
                return rw;
        }
        return null;
    }
    public void dodajDraftTreeItemURoomView(RoomView room)
    {
        for(int i = 0; i < MainFrame.getInstance().getDraftTree().getSelectedNode().getChildCount();i++)
        {
            DraftTreeItem draftTreeItem = (DraftTreeItem)MainFrame.getInstance().getDraftTree().getSelectedNode().getChildAt(i);
            if(room.getIme().equals(draftTreeItem.getDraftNode().getIme()))
                room.setDraftTreeItem(draftTreeItem);
        }
    }

}



