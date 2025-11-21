package raf.draft.dsw.gui.swing.tab.commands.implementation;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.model.elements.*;
import raf.draft.dsw.model.painters.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CopyPasteCommand extends AbstractCommand {
    RoomView rw = MainFrame.getInstance().getProjectView().getRoom();
    RoomElement re;
    ElementPainter elementPainter;
    List<ElementPainter> noviEL = new ArrayList<>();
    List<RoomElement> reList = new ArrayList<>();
    public CopyPasteCommand() {
        noviEL.addAll(rw.getSelectedList());
    }

    @Override
    public void doCommand() {
        kopiranje(rw);
        preslikavanje(rw);
    }

    @Override
    public void undoCommand() {
        List<ElementPainter> zaBrisanje = new ArrayList<>();
        for(ElementPainter p : rw.getPainterList()) {
            if(reList.contains(p.getDraftNode())) {
                zaBrisanje.add(p);
                rw.getRoom().removeChild(p.getDraftNode());
            }
        }
        rw.getPainterList().removeAll(zaBrisanje);
    }
    static int brkop=1;
    public void kopiranje(RoomView roomView){
        roomView.setNewItems(new ArrayList<>());
        for(ElementPainter r: noviEL){
            Prototype novi=((RoomElement) r.getDraftNode()).clone();
            if(novi instanceof Kada){
                Kada k=(Kada)novi;
                Point p=new Point((int)((Kada) novi).getLokacija().getX()+20,(int)((Kada) novi).getLokacija().getY()+20);
                k.setLokacija(p);
                k.setIme("Kada Kopija"+brkop);
                roomView.getNewItems().add(k);
                MainFrame.getInstance().getDraftTree().addChildRoom(roomView.getDraftTreeItem(), k);
            }
            else if(novi instanceof Bojler){
                Bojler b=(Bojler)novi;
                Point p=new Point((int)((Bojler) novi).getLokacija().getX()+20,(int)((Bojler) novi).getLokacija().getY()+20);
                b.setLokacija(p);
                b.setIme("Bojler Kopija"+brkop);
                roomView.getNewItems().add(b);
                MainFrame.getInstance().getDraftTree().addChildRoom(roomView.getDraftTreeItem(), b);
            }else if(novi instanceof Krevet){
                Krevet k=(Krevet)novi;
                Point p=new Point((int)((Krevet) novi).getLokacija().getX()+20,(int)((Krevet) novi).getLokacija().getY()+20);
                k.setLokacija(p);
                k.setIme("Krevet Kopija"+brkop);
                roomView.getNewItems().add(k);
                MainFrame.getInstance().getDraftTree().addChildRoom(roomView.getDraftTreeItem(), k);
            }else if(novi instanceof LavaboK){
                LavaboK k=(LavaboK)novi;
                Point p=new Point((int)((LavaboK) novi).getLokacija().getX()+20,(int)((LavaboK) novi).getLokacija().getY()+20);
                k.setLokacija(p);
                k.setIme("Lavabo Kopija"+brkop);
                roomView.getNewItems().add(k);
                MainFrame.getInstance().getDraftTree().addChildRoom(roomView.getDraftTreeItem(), k);
            }else if(novi instanceof Ormar){
                Ormar k=(Ormar)novi;
                Point p=new Point((int)((Ormar) novi).getLokacija().getX()+20,(int)((Ormar) novi).getLokacija().getY()+20);
                k.setLokacija(p);
                k.setIme("Ormar Kopija"+brkop);
                roomView.getNewItems().add(k);
                MainFrame.getInstance().getDraftTree().addChildRoom(roomView.getDraftTreeItem(), k);
            }else if(novi instanceof Sto){
                Sto k=(Sto)novi;
                Point p=new Point((int)((Sto) novi).getLokacija().getX()+20,(int)((Sto) novi).getLokacija().getY()+20);
                k.setLokacija(p);
                k.setIme("Sto Kopija"+brkop);
                roomView.getNewItems().add(k);
                MainFrame.getInstance().getDraftTree().addChildRoom(roomView.getDraftTreeItem(), k);
            }else if(novi instanceof VesMasina){
                VesMasina k=(VesMasina)novi;
                Point p=new Point((int)((VesMasina) novi).getLokacija().getX()+20,(int)((VesMasina) novi).getLokacija().getY()+20);
                k.setLokacija(p);
                k.setIme("Ves Masina Kopija"+brkop);
                roomView.getNewItems().add(k);
                MainFrame.getInstance().getDraftTree().addChildRoom(roomView.getDraftTreeItem(), k);
            }else if(novi instanceof Vrata){
                Vrata k=(Vrata)novi;
                Point p=new Point((int)((Vrata) novi).getLokacija().getX()+20,(int)((Vrata) novi).getLokacija().getY()+20);
                k.setLokacija(p);
                k.setIme("Vrata Kopija"+brkop);
                roomView.getNewItems().add(k);
                MainFrame.getInstance().getDraftTree().addChildRoom(roomView.getDraftTreeItem(), k);
            }else if(novi instanceof WCSolja){
                WCSolja k=(WCSolja)novi;
                Point p=new Point((int)((WCSolja) novi).getLokacija().getX()+20,(int)((WCSolja) novi).getLokacija().getY()+20);
                k.setLokacija(p);
                k.setIme("WC Solja Kopija"+brkop);
                roomView.getNewItems().add(k);
                //noviEL.add(k);
                MainFrame.getInstance().getDraftTree().addChildRoom(roomView.getDraftTreeItem(), k);
            }
            System.out.println("Uslo u c");
            brkop++;

        }
    }

    public void preslikavanje(RoomView roomView){
        for(RoomElement klon:roomView.getNewItems()){
            if(klon instanceof Kada){
                KadaPainter kp=new KadaPainter((Kada) klon);
                roomView.getPainterList().add(kp);
            }
            else if(klon instanceof Bojler){
                BojlerPainter bp=new BojlerPainter((Bojler) klon);
                roomView.getPainterList().add(bp);
            }
            else if(klon instanceof Krevet){
                KrevetPainter bp=new KrevetPainter((Krevet) klon);
                roomView.getPainterList().add(bp);
            }
            else if(klon instanceof LavaboK){
                LavaboPainter bp=new LavaboPainter((LavaboK) klon);
                roomView.getPainterList().add(bp);
            }
            else if(klon instanceof Ormar){
                OrmarPainter bp=new OrmarPainter((Ormar) klon);
                roomView.getPainterList().add(bp);
            }
            else if(klon instanceof Sto){
                StoPainter bp=new StoPainter((Sto) klon);
                roomView.getPainterList().add(bp);
            }
            else if(klon instanceof VesMasina){
                VesMasinaPainter bp=new VesMasinaPainter((VesMasina) klon);
                roomView.getPainterList().add(bp);
            }
            else if(klon instanceof Vrata){
                VrataPainter bp=new VrataPainter((Vrata) klon);
                roomView.getPainterList().add(bp);
            }
            else if(klon instanceof WCSolja){
                WCSoljaPainter bp=new WCSoljaPainter((WCSolja) klon);
                roomView.getPainterList().add(bp);
            }
            roomView.getRoom().addChild(klon);
        }
        reList.addAll(roomView.getNewItems());
        roomView.getNewItems().clear();
    }
}
