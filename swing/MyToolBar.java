package raf.draft.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getExitAcion());
        add(MainFrame.getInstance().getActionManager().getAboutUs());
        add(MainFrame.getInstance().getActionManager().getNewNodeAction());
        add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        add(MainFrame.getInstance().getActionManager().getEditAction());
        add(MainFrame.getInstance().getActionManager().getUndo());
        add(MainFrame.getInstance().getActionManager().getRedo());
        add(MainFrame.getInstance().getActionManager().getSave());
        add(MainFrame.getInstance().getActionManager().getSaveAs());
        add(MainFrame.getInstance().getActionManager().getOpen());
    }
    public void disable(String type)
    {
        if(type.equals("undo"))
        {
            for(int i = 0; i < getComponentCount(); i++)
            {
                if(this.getComponent(i).equals ((MainFrame.getInstance().getActionManager()).getUndo()))
                    this.getComponent(i).setBackground(Color.blue);
            }
        }
        if(type.equals("redo"))
        {
            for(int i = 0; i < getComponentCount(); i++)
            {
                if(this.getComponent(i).equals ((MainFrame.getInstance().getActionManager()).getRedo()))
                this.getComponent(i).setEnabled(false);
            }
        }
    }
}
