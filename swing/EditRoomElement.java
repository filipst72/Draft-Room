package raf.draft.dsw.gui.swing;

import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.painters.ElementPainter;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;

public class EditRoomElement extends JFrame {
    JFrame f;
    Button izmeni;
    Button otkazi;
    Label visinaLB;
    Label sirinaLB;
    Label rotacijaLB;
    TextField visina;
    TextField sirina;
    TextField rotacija;

    public  EditRoomElement(RoomView roomView, RoomElement roomElement){
        init();
        visina.setText(""+roomElement.getVisina()/((Room)roomElement.getParent()).getRatioV());
        sirina.setText(""+roomElement.getSirina()/((Room)roomElement.getParent()).getRatioS());
        rotacija.setText(""+roomElement.getRotacija());
        izmeni.addActionListener(e -> {
            String vis=visina.getText();
            String sir=sirina.getText();
            String rot=rotacija.getText();
            float VISINA = Float.parseFloat(vis);
            float SIRINA = Float.parseFloat(sir);
            int ROTACIJA = Integer.parseInt(rot);
            roomElement.setVisina(VISINA*((Room)roomElement.getParent()).getRatioV());
            roomElement.setSirina(SIRINA*((Room)roomElement.getParent()).getRatioS());
            roomElement.setRotacija(ROTACIJA);
            System.out.println(roomElement);
            roomView.ofarbaj();
            dispose();
        });
        otkazi.addActionListener(e -> {
            dispose();
        });
    }

    public void init(){
        f=new JFrame();
        izmeni=new Button("Izmeni");
        otkazi=new Button("Otkazi");

        visinaLB=new Label("visina(cm)");
        sirinaLB=new Label("sirina(cm)");
        rotacijaLB=new Label("rotacija");
        visina=new TextField();
        sirina=new TextField();
        rotacija=new TextField();

        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null);
        add(visinaLB);
        add(visina);
        add(sirinaLB);
        add(sirina);
        add(rotacijaLB);
        add(rotacija);
        add(izmeni);
        add(otkazi);
    }
}
