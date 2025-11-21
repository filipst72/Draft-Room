package raf.draft.dsw.gui.swing;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAcion());
        fileMenu.add(MainFrame.getInstance().getActionManager().getAboutUs());
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewNodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getEditAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getCopyPasteAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getPasteAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getZoomStateAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getUndo());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRedo());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSave());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAs());
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpen());
        add(fileMenu);
    }
}
