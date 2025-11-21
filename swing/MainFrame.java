package raf.draft.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.actions.ActionManager;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.tab.ProjectView;
import raf.draft.dsw.gui.swing.tree.DraftTree;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeImplementation;
import raf.draft.dsw.model.messages.Notification;

import javax.swing.*;
import java.awt.*;

@Setter
@Getter
public class MainFrame extends JFrame implements ISubscriber {
    //buduca polja za sve komponente view-a na glavnom prozoru

    private static MainFrame instance;
    private ActionManager actionManager;
    private DraftTree draftTree ;
    private ProjectView projectView;
    private JTree projectExplorer;
    MyToolBar toolBar;MyMenuBar menu;
    private MainFrame(){
    }

    public static MainFrame getInstance() {
        if(instance==null){
            instance= new MainFrame();
        }
        return instance;
    }

    public void initialize(){
        actionManager=new ActionManager();
        draftTree = new DraftTreeImplementation();

       // this.setVisible(true);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/2 , screenHeight/2 );
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DraftRoom");

         menu = new MyMenuBar();
        setJMenuBar(menu);

         toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);

        projectExplorer = draftTree.generateTree(ApplicationFramework.getInstance().getDraftRoomRepository().getRoot());
        projectView=new ProjectView();


        JScrollPane scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,projectView.getTabbedPane());
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        MyToolBarState toolBarState = new MyToolBarState();
        add(toolBarState, BorderLayout.EAST);

        actionManager.getUndo().setEnabled(false);
        actionManager.getRedo().setEnabled(false);
        this.setVisible(true);
    }


    @Override
    public void update(Notification notification) {
        JOptionPane.showMessageDialog(null, notification.getMessage().getPoruka(), "Message", JOptionPane.INFORMATION_MESSAGE);
    }


}
