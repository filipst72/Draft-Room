package raf.draft.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@Getter@Setter
public class AddItem extends JFrame implements ActionListener {
    String ime;
    String autor;
    String putanja;

    JFrame f;
    Button dodaj;
    Button otkazi;
    Label imeLB;
    Label autorLB;
    Label putanjaLB;
    TextField imeTF;
    TextField autorTF;
    TextField putanjaTF;
    JFileChooser fileChooser;
    JRadioButton roomRB;
    JRadioButton buildingRB;

    public AddItem(DraftTreeItem node){
        init();
        if(node.getDraftNode() instanceof ProjectExplorer || node.getDraftNode() instanceof Building){
            roomRB.setVisible(false);
            buildingRB.setVisible(false);
        }
        if(node.getDraftNode() instanceof Project || node.getDraftNode() instanceof Building){
            autorTF.setEditable(false);
            putanjaTF.setEditable(false);
        }
        putanjaTF.addActionListener(e->
        {
            if (fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {

                File projectFile = fileChooser.getSelectedFile();
                node.getDraftNode().setPutanja(projectFile.getPath());
            } else {
                return;
            }
        });

        dodaj.addActionListener(e -> {
            if(imeTF.getText().isEmpty()) {
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.PraznoPolje);
            }
            else if(roomRB.isVisible() && !roomRB.isSelected() && !buildingRB.isSelected()){
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.NijeSelektovanObjekat);
            }
            else{
                for(DraftNode prolaz:  ApplicationFramework.getInstance().getDraftRoomRepository().getRoot().getChildren()){
                    if (prolaz.getIme().equals(imeTF.getText())) {
                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.IstoIme);
                        return;
                    }
                    if(prolaz instanceof Project){
                        for(DraftNode deca:((Project) prolaz).getChildren()){
                            if(deca.getIme().equals(imeTF.getText())){
                                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.IstoIme);
                                return;
                            }
                            if(deca instanceof Building){
                                for(DraftNode sobe:((Building) deca).getChildren()){
                                    if(imeTF.getText().equals(sobe.getIme())) {
                                        ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.IstoIme);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
                MainFrame.getInstance().getDraftTree().createChild2(node,imeTF.getText(),autorTF.getText(),putanjaTF.getText(),roomRB.isSelected(),buildingRB.isSelected());
                dispose();
            }
        });

        otkazi.addActionListener(e -> {
            dispose();
        });
    }

    public void init(){
        f=new JFrame();
        imeLB=new Label("Ime:");
        autorLB=new Label("Autor:");
        putanjaLB=new Label("Putanja:");
        dodaj=new Button("Dodaj");
        otkazi=new Button("Otkazi");
        imeTF=new TextField();
        autorTF=new TextField();
        putanjaTF=new TextField();
        roomRB=new JRadioButton("Room");
        buildingRB=new JRadioButton("Building");
        ButtonGroup grupa=new ButtonGroup();
        grupa.add(roomRB);
        grupa.add(buildingRB);
        fileChooser=new JFileChooser();
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));
        setLocationRelativeTo(null);
        imeTF.setPreferredSize(new Dimension(100, 30));

        add(imeLB);
        add(imeTF);
        add(autorLB);
        add(autorTF);
        add(putanjaLB);
        add(putanjaTF);
        add(roomRB);
        add(buildingRB);
        add(dodaj);
        add(otkazi);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
