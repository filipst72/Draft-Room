package raf.draft.dsw.gui.swing;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditWindow extends JFrame implements ActionListener {
    String ime;
    String autor;
    String putanja;

    JFrame f;
    Button izmeni;
    Button otkazi;
    Label imeLB;
    Label autorLB;
    Label putanjaLB;
    TextField imeTF;
    TextField autorTF;
    TextField putanjaTF;
    JFileChooser putanjaJFC;
    public EditWindow(DraftTreeItem node){
        init();
        ime=node.getDraftNode().getIme();
        autor=node.getDraftNode().getAutor();
        putanja=node.getDraftNode().getPutanja();

        imeTF.setText(ime);
        autorTF.setText(autor);
        putanjaTF.setText(putanja);

        if(node.getDraftNode() instanceof Building || node.getDraftNode() instanceof Room){
            autorTF.setEditable(false);
            putanjaTF.setEditable(false);
        }

        izmeni.addActionListener(e -> {
                for(DraftNode subjekat :((DraftNodeComposite)node.getDraftNode().getParent()).getChildren()){
                    if(subjekat.equals(node.getDraftNode()) && !istoIme(node.getDraftNode())){
                       subjekat.setIme(imeTF.getText());
                       subjekat.setAutor(autorTF.getText());
                       subjekat.setPutanja(putanjaTF.getText());
                    }
                }
            ApplicationFramework.getInstance().getMessageGenerator().desavanjaUModelu(MessageType.IzmenjenItem, node.getDraftNode());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getProjectExplorer());
            dispose();

        });

        otkazi.addActionListener(e->{dispose();});
    }


    public void init(){
        f=new JFrame();
        imeLB=new Label("Ime:");
        autorLB=new Label("Autor:");
        putanjaLB=new Label("Putanja:");
        izmeni=new Button("Izmeni");
        otkazi=new Button("Otkazi");
        imeTF=new TextField();
        autorTF=new TextField();
        putanjaTF=new TextField();


        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null);
        imeTF.setPreferredSize(new Dimension(100, 30));

        add(imeLB);
        add(imeTF);
        add(autorLB);
        add(autorTF);
        add(putanjaLB);
        add(putanjaTF);
        add(izmeni);
        add(otkazi);
    }
    public boolean istoIme(DraftNode node)
    {
        for(DraftNode provera: ((DraftNodeComposite) node.getParent()).getChildren())
        {
            if(imeTF.getText().equals(provera.getIme()) && !(provera == node))
            {
                ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.IstoIme);
                return true;
            }
        }
        if(imeTF.getText().equals(node.getParent().getIme()))
            return true;
        return false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
