package raf.draft.dsw.gui.swing.tab.commands;

import com.sun.tools.javac.Main;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.messages.MessageType;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<AbstractCommand> commands = new ArrayList<AbstractCommand>();
    int currentCommand = 0;
    public void doCommand()
    {
        if(currentCommand == commands.size()-1)
        {
            MainFrame.getInstance().getActionManager().getUndo().setEnabled(true);
            MainFrame.getInstance().getActionManager().getRedo().setEnabled(false);
            MainFrame.getInstance().getToolBar().disable("redo");
        }
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            MainFrame.getInstance().getActionManager().getUndo().setEnabled(true);
        }
       /* if(currentCommand==commands.size()){
            ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.NemaDalje);
        }*/
    }
    public void redoCommand()
    {
        if(currentCommand == 0)
        {
            MainFrame.getInstance().getActionManager().getUndo().setEnabled(false);
            MainFrame.getInstance().getActionManager().getRedo().setEnabled(true);
            MainFrame.getInstance().getToolBar().disable("undo");
        }
        if(currentCommand > 0){
            commands.get(--currentCommand).undoCommand();
            MainFrame.getInstance().getActionManager().getRedo().setEnabled(true);
        }
        /*if(currentCommand==0){
            ApplicationFramework.getInstance().getMessageGenerator().kreirajPoruku(MessageType.NemaDalje);
        }*/

    }
    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
    }
}
