package raf.draft.dsw.gui.swing;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.messages.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomEditWindow extends JFrame implements ActionListener {
    String duzina;
    String sirina;
    String putanja;

    JFrame f;
    Button izmeni;
    Button otkazi;
    Label duzinaLB;
    Label sirinaLB;
    TextField duzinaTF;
    TextField sirinaTF;

    public RoomEditWindow(RoomView roomView){
            init();
         //   duzina = Float.toString((float) roomView.getRoom().getVisina());
        //    sirina=Float.toString((float) roomView.getRoom().getSirina());


          //  duzinaTF.setText(duzina);
      //      sirinaTF.setText(sirina);


            izmeni.addActionListener(e -> {
                if(duzinaTF.getText().isEmpty() || sirinaTF.getText().isEmpty()){
                    ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PraznoPolje);
                }
                else {
                    roomView.getRoom().setVisina(Float.parseFloat(duzinaTF.getText()));
                    roomView.getRoom().setSirina(Float.parseFloat(sirinaTF.getText()));
                    roomView.setNacrtatiSobu(true);
                    float ratio;
                    float visinaPanela = roomView.getSize().height - 60;
                    float sirinaPanela = roomView.getSize().width - 60;
                    float odnosVisina = visinaPanela / roomView.getRoom().getVisina();
                    float odnosSirina = sirinaPanela / roomView.getRoom().getSirina();
                    if (odnosVisina < odnosSirina) ratio = odnosVisina;
                    else ratio = odnosSirina;

                    roomView.getRoom().setRatioV(odnosVisina);
                    roomView.getRoom().setRatioS(odnosSirina);
                    ApplicationFramework.getInstance().getMessageGenerator().crtanje(roomView);
                    dispose();
                }
            });

            otkazi.addActionListener(e->{dispose();});

    }


    public void init(){
        f=new JFrame();
        duzinaLB=new Label("visina u cm:");
        sirinaLB =new Label("sirina u cm:");

        izmeni =new Button("Izmeni");
        otkazi=new Button("Otkazi");
        duzinaTF=new TextField("");
        sirinaTF =new TextField("");

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));
        setLocationRelativeTo(null);
        duzinaTF.setPreferredSize(new Dimension(100, 30));

        add(duzinaLB);
        add(duzinaTF);
        add(sirinaLB);
        add(sirinaTF);
        add(izmeni);
        add(otkazi);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
