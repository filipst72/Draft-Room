package raf.draft.dsw.controller.actions;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.StateActions.*;
import raf.draft.dsw.gui.swing.AddItem;

@Getter
@Setter
public class ActionManager {

    AboutUs aboutUs;
    ExitAction exitAcion;
    NewNodeAction newNodeAction;
    DeleteNodeAction deleteNodeAction;
    EditAction editAction;

    AddStateAction addStateAction;
    EditRoomStateAction editRoomStateAction;
    SelectStateAction selectStateAction;
    ResizeStateAction resizeStateAction;
    RotateStateAction rotateStateAction;
    RotateLeftStateAction rotateLeftStateAction;
    MoveStateAction moveStateAction;
    ZoomStateAction zoomStateAction;
    DeleteStateAction deleteStateAction;
    EditStateAction editStateAction;
    CopyPasteAction copyPasteAction;
    PasteAction pasteAction;
    Undo undo;
    Redo redo;
    Save save;
    SaveAs saveAs;
    Open open;

    public ActionManager(){
        aboutUs = new AboutUs();
        exitAcion = new ExitAction();
        newNodeAction = new NewNodeAction();
        deleteNodeAction = new DeleteNodeAction();
        editAction = new EditAction();

        addStateAction = new AddStateAction();
        editRoomStateAction=new EditRoomStateAction();
        selectStateAction=new SelectStateAction();
        resizeStateAction=new ResizeStateAction();
        rotateStateAction=new RotateStateAction();
        rotateLeftStateAction=new RotateLeftStateAction();
        moveStateAction=new MoveStateAction();
        zoomStateAction=new ZoomStateAction();
        deleteStateAction=new DeleteStateAction();
        editStateAction=new EditStateAction();
        copyPasteAction=new CopyPasteAction();
        pasteAction=new PasteAction();
        undo = new Undo();
        redo=new Redo();
        save=new Save();
        saveAs=new SaveAs();
        open=new Open();
    }
}
