package raf.draft.dsw.gui.swing;

import com.sun.tools.javac.Main;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.tab.commands.implementation.AddCommand;
import raf.draft.dsw.model.elements.*;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.painters.*;

import javax.swing.*;
import java.awt.*;
//import java.lang.classfile.attribute.RecordAttribute;

public class AddRoomItemWindow extends JFrame{
    JFrame f;
    Button bojler;
    Button kada;
    Button krevet;
    Button lavabo;
    Button ormar;
    Button sto;
    Button vesmasina;
    Button vrata;
    Button wcsolja;
    Label odabir;
    Label visinaLB;
    Label sirinaLB;
    TextField visina;
    TextField sirina;
    static int brElementa=0;
    RoomElement re;
    public AddRoomItemWindow(RoomView room, Point  point){
        init();

        krevet.addActionListener(e -> {
            String vis=visina.getText();
            String sir=sirina.getText();

            if(vis.isEmpty() || sir.isEmpty()){
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PraznoPolje);
            }
            else {
                int VISINA = Integer.parseInt(vis);
                int SIRINA = Integer.parseInt(sir);
                int zumiranX = point.x;
                int zumiranY = point.y;
                int scaledWidth = (int) (SIRINA * room.getRoom().getRatioS()*room.getScalingFactor());
                int scaledHeight = (int) (VISINA * room.getRoom().getRatioV()*room.getScalingFactor());
                zumiranX -= scaledHeight/2;
                zumiranY -= scaledWidth/2;
                Point pointZum = new Point(zumiranX, zumiranY);
                Rectangle rectZum = new Rectangle(pointZum.x-4,pointZum.y-4,
                        scaledWidth+8,
                        scaledHeight+8);
                for(ElementPainter ep: room.getPainterList())
                {
                    if(ep.getHitbox().intersects(rectZum))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.Preseca);
                        dispose();
                        return;
                    }
                }

                Krevet k = new Krevet("Krevet" + brElementa, room.getRoom(), SIRINA,
                        VISINA, point, 0);
                KrevetPainter kp = new KrevetPainter(k);
                AbstractCommand command = new AddCommand(room, k, kp);
                room.getCommandManager().addCommand(command);

                dispose();
            }
        });

        vrata.addActionListener(e->{
            String vis=visina.getText();
            String sir=sirina.getText();
            if(vis.isEmpty() || sir.isEmpty()){
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PraznoPolje);
            }
            else {
                int VISINA = Integer.parseInt(vis);
                int SIRINA = Integer.parseInt(sir);
                if(VISINA!=SIRINA){
                    ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.VisinaISirinaIsto);
                    return;
                }
                int zumiranX = point.x;
                int zumiranY = point.y;
                int scaledWidth =
                        (int) (SIRINA * room.getRoom().getRatioS()*room.getScalingFactor());
                int scaledHeight =
                        (int) (VISINA * room.getRoom().getRatioV()*room.getScalingFactor());
                zumiranX -= scaledHeight/2;
                zumiranY -= scaledWidth/2;
                Point pointZum = new Point(zumiranX, zumiranY);
                Rectangle rectZum = new Rectangle(pointZum.x-4,pointZum.y-4,
                        scaledWidth+8,
                        scaledHeight+8);
                for(ElementPainter ep: room.getPainterList())
                {
                    if(ep.getHitbox().intersects(rectZum))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.Preseca);
                        dispose();
                        return;
                    }
                }
                Vrata v = new Vrata("Vrata" + brElementa, room.getRoom(), SIRINA, VISINA, point, 0);
                VrataPainter vp = new VrataPainter(v);
                AbstractCommand command = new AddCommand(room, v, vp);
                room.getCommandManager().addCommand(command);
                dispose();
            }
        });

        bojler.addActionListener(e -> {
            String vis=visina.getText();
            String sir=sirina.getText();
            if(vis.isEmpty() || sir.isEmpty()){
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PraznoPolje);
            }
            else {
                int VISINA = Integer.parseInt(vis);
                int SIRINA = Integer.parseInt(sir);
                int zumiranX = point.x;
                int zumiranY = point.y;
                int scaledWidth = (int) (SIRINA * room.getRoom().getRatioS()*room.getScalingFactor());
                int scaledHeight = (int) (VISINA * room.getRoom().getRatioV()*room.getScalingFactor());
                if(scaledWidth<scaledHeight){
                    scaledHeight = scaledWidth;
                }
                else scaledWidth = scaledHeight;
                zumiranX -= scaledHeight/2;
                zumiranY -= scaledWidth/2;
                Point pointZum = new Point(zumiranX, zumiranY);
                Rectangle rectZum = new Rectangle(pointZum.x-4,pointZum.y-4,
                        scaledWidth+8,
                        scaledHeight+8);
                for(ElementPainter ep: room.getPainterList())
                {
                    if(ep.getHitbox().intersects(rectZum))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.Preseca);
                        dispose();
                        return;
                    }
                }
                if(VISINA!=SIRINA){
                    ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.VisinaISirinaIsto);
                    return;
                }

                Bojler b = new Bojler("Bojler" + brElementa, room.getRoom(), SIRINA, VISINA, point, 0);
                BojlerPainter bp = new BojlerPainter(b);
                AbstractCommand command = new AddCommand(room, b, bp);
                room.getCommandManager().addCommand(command);
                dispose();
            }
        });

        sto.addActionListener(e -> {
            String vis=visina.getText();
            String sir=sirina.getText();
            if(vis.isEmpty() || sir.isEmpty()){
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PraznoPolje);
            }
            else {
                int VISINA = Integer.parseInt(vis);
                int SIRINA = Integer.parseInt(sir);
                int zumiranX = point.x;
                int zumiranY = point.y;
                int scaledWidth = (int) (SIRINA * room.getRoom().getRatioS()*room.getScalingFactor());
                int scaledHeight = (int) (VISINA * room.getRoom().getRatioV()*room.getScalingFactor());
                zumiranX -= scaledHeight/2;
                zumiranY -= scaledWidth/2;
                Point pointZum = new Point(zumiranX, zumiranY);
                Rectangle rectZum = new Rectangle(pointZum.x-4,pointZum.y-4,
                        scaledWidth+8,
                        scaledHeight+8);
                for(ElementPainter ep: room.getPainterList())
                {
                    if(ep.getHitbox().intersects(rectZum))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.Preseca);
                        dispose();
                        return;
                    }
                }
                Sto s = new Sto("Sto" + brElementa, room.getRoom(), SIRINA, VISINA, point, 0);
                StoPainter sp = new StoPainter(s);
                AbstractCommand command = new AddCommand(room, s, sp);
                room.getCommandManager().addCommand(command);
                dispose();
            }
        });

        ormar.addActionListener(e ->{
            String vis=visina.getText();
            String sir=sirina.getText();
            if(vis.isEmpty() || sir.isEmpty()){
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PraznoPolje);
            }
            else {
                int VISINA = Integer.parseInt(vis);
                int SIRINA = Integer.parseInt(sir);
                int zumiranX = point.x;
                int zumiranY = point.y;
                int scaledWidth = (int) (SIRINA * room.getRoom().getRatioS()*room.getScalingFactor());
                int scaledHeight = (int) (VISINA * room.getRoom().getRatioV()*room.getScalingFactor());
                zumiranX -= scaledHeight/2;
                zumiranY -= scaledWidth/2;
                Point pointZum = new Point(zumiranX, zumiranY);
                Rectangle rectZum = new Rectangle(pointZum.x-4,pointZum.y-4,
                        scaledWidth+8,
                        scaledHeight+8);
                for(ElementPainter ep: room.getPainterList())
                {
                    if(ep.getHitbox().intersects(rectZum))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.Preseca);
                        dispose();
                        return;
                    }
                }
                Ormar o = new Ormar("Ormar" + brElementa, room.getRoom(), SIRINA, VISINA, point, 0);
                OrmarPainter op = new OrmarPainter(o);
                AbstractCommand command = new AddCommand(room, o, op);
                room.getCommandManager().addCommand(command);
                dispose();
            }
        });

        kada.addActionListener(e -> {
            String vis=visina.getText();
            String sir=sirina.getText();
            if(vis.isEmpty() || sir.isEmpty()){
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PraznoPolje);
            }
            else {
                int VISINA = Integer.parseInt(vis);
                int SIRINA = Integer.parseInt(sir);
                int zumiranX = point.x;
                int zumiranY = point.y;
                int scaledWidth = (int) (SIRINA * room.getRoom().getRatioS()*room.getScalingFactor());
                int scaledHeight = (int) (VISINA * room.getRoom().getRatioV()*room.getScalingFactor());
                zumiranX -= scaledHeight/2;
                zumiranY -= scaledWidth/2;
                Point pointZum = new Point(zumiranX, zumiranY);
                Rectangle rectZum = new Rectangle(pointZum.x-4,pointZum.y-4,
                        scaledWidth+8,
                        scaledHeight+8);
                for(ElementPainter ep: room.getPainterList())
                {
                    if(ep.getHitbox().intersects(rectZum))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.Preseca);
                        dispose();
                        return;
                    }
                }
                Kada k = new Kada("Kada" + brElementa, room.getRoom(), SIRINA, VISINA, point, 0);
                KadaPainter kp = new KadaPainter(k);
                AbstractCommand command = new AddCommand(room, k, kp);
                room.getCommandManager().addCommand(command);
                dispose();
            }
        });

        lavabo.addActionListener(e -> {
            String vis=visina.getText();
            String sir=sirina.getText();
            if(vis.isEmpty() || sir.isEmpty()){
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PraznoPolje);
            }
            else {
                int VISINA = Integer.parseInt(vis);
                int SIRINA = Integer.parseInt(sir);
                int zumiranX = point.x;
                int zumiranY = point.y;
                int scaledWidth = (int) (SIRINA * room.getRoom().getRatioS()*room.getScalingFactor());
                int scaledHeight = (int) (VISINA * room.getRoom().getRatioV()*room.getScalingFactor());
                zumiranX -= scaledHeight/2;
                zumiranY -= scaledWidth/2;
                Point pointZum = new Point(zumiranX, zumiranY);
                Rectangle rectZum = new Rectangle(pointZum.x-4,pointZum.y-4,
                        scaledWidth+8,
                        scaledHeight+8);
                for(ElementPainter ep: room.getPainterList())
                {
                    if(ep.getHitbox().intersects(rectZum))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.Preseca);
                        dispose();
                        return;
                    }
                }
                LavaboK l = new LavaboK("Lavabo" + brElementa, room.getRoom(), SIRINA, VISINA, point, 0);
                LavaboPainter lp = new LavaboPainter(l);
                AbstractCommand command = new AddCommand(room, l, lp);
                room.getCommandManager().addCommand(command);
                dispose();
            }
        });

        vesmasina.addActionListener(e -> {
            String vis=visina.getText();
            String sir=sirina.getText();
            if(vis.isEmpty() || sir.isEmpty()){
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PraznoPolje);
            }
            else {
                int VISINA = Integer.parseInt(vis);
                int SIRINA = Integer.parseInt(sir);
                if(VISINA!=SIRINA){
                    ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.VisinaISirinaIsto);
                    return;
                }
                int zumiranX = point.x;
                int zumiranY = point.y;
                int scaledWidth = (int) (SIRINA * room.getRoom().getRatioS()*room.getScalingFactor());
                int scaledHeight = (int) (VISINA * room.getRoom().getRatioV()*room.getScalingFactor());
                zumiranX -= scaledHeight/2;
                zumiranY -= scaledWidth/2;
                Point pointZum = new Point(zumiranX, zumiranY);
                Rectangle rectZum = new Rectangle(pointZum.x-4,pointZum.y-4,
                        scaledWidth+8,
                        scaledHeight+8);
                for(ElementPainter ep: room.getPainterList())
                {
                    if(ep.getHitbox().intersects(rectZum))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.Preseca);
                        dispose();
                        return;
                    }
                }
                VesMasina v = new VesMasina("Ves Masina" + brElementa, room.getRoom(), SIRINA, VISINA, point, 0);
                VesMasinaPainter vp = new VesMasinaPainter(v);
                AbstractCommand command = new AddCommand(room, v, vp);
                room.getCommandManager().addCommand(command);
                dispose();
            }
        });

        wcsolja.addActionListener(e -> {
            String vis=visina.getText();
            String sir=sirina.getText();
            if(vis.isEmpty() || sir.isEmpty()){
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PraznoPolje);
            }
            else {
                int VISINA = Integer.parseInt(vis);
                int SIRINA = Integer.parseInt(sir);
                int zumiranX = point.x;
                int zumiranY = point.y;
                int scaledWidth = (int) (SIRINA * room.getRoom().getRatioS()*room.getScalingFactor());
                int scaledHeight = (int) (VISINA * room.getRoom().getRatioV()*room.getScalingFactor());
                zumiranX -= scaledHeight/2;
                zumiranY -= scaledWidth/2;
                Point pointZum = new Point(zumiranX, zumiranY);
                Rectangle rectZum = new Rectangle(pointZum.x-4,pointZum.y-4,
                        scaledWidth+8,
                        scaledHeight+8);
                for(ElementPainter ep: room.getPainterList())
                {
                    if(ep.getHitbox().intersects(rectZum))
                    {
                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.Preseca);
                        dispose();
                        return;
                    }
                }
                WCSolja w = new WCSolja("WC solja" + brElementa, room.getRoom(), SIRINA, VISINA, point, 0);
                WCSoljaPainter wp = new WCSoljaPainter(w);
                AbstractCommand command = new AddCommand(room, w, wp);
                room.getCommandManager().addCommand(command);
                dispose();
            }
        });
        brElementa++;
    }

    public void init(){
        f=new JFrame();
        bojler=new Button("BOJLER");
        kada=new Button("KADA");
        krevet=new Button("KREVET");
        lavabo=new Button("LAVABO");
        ormar=new Button("ORMAR");
        sto=new Button("STO");
        vesmasina=new Button("VES MASINA");
        vrata=new Button("VRATA");
        wcsolja=new Button("WC SOLJA");
        odabir=new Label("Izaberite element:");

        visinaLB=new Label("Unesite visinu:");
        sirinaLB=new Label("Unesite sirinu:");
        visina=new TextField();
        sirina=new TextField();

        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
        setLocationRelativeTo(null);
        add(visinaLB);
        add(visina);
        add(sirinaLB);
        add(sirina);
        add(odabir);
        add(bojler);
        add(kada);
        add(krevet);
        add(lavabo);
        add(ormar);
        add(sto);
        add(vesmasina);
        add(vrata);
        add(wcsolja);
    }
}
