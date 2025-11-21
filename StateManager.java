package raf.draft.dsw.controller;

import lombok.Getter;
import raf.draft.dsw.controller.StatePattern.*;

public class StateManager {
    private AddState addState;
    private CopyPasteState copyPasteState;
    private DeleteState deleteState;
    private EditRoomState editRoomState;
    private EditState editState;
    private MoveState moveState;
    private ResizeState resizeState;
    private RotateState rotateState;
    private RotateLeftState rotateLeftState;
    private SelectState selectState;
    private ZoomState zoomState;
    @Getter
    private State currentState;

    public StateManager(){
        addState=new AddState();
        copyPasteState = new CopyPasteState();
        deleteState=new DeleteState();
        editRoomState=new EditRoomState();
        editState=new EditState();
        moveState=new MoveState();
        resizeState=new ResizeState();
        rotateState=new RotateState();
        rotateLeftState=new RotateLeftState();
        selectState=new SelectState();
        zoomState=new ZoomState();
        currentState=editRoomState;
    }

    public void setAddState(){
        currentState=addState;
    }
    public void setCopyPasteState(){
        currentState=copyPasteState;
    }
    public void setDeleteState(){
        currentState=deleteState;
    }
    public void setEditRoomState(){
        currentState=editRoomState;
    }
    public void setEditState(){
        currentState=editState;
    }
    public void setMoveState(){
        currentState=moveState;
    }
    public void setResizeState(){
        currentState=resizeState;
    }
    public void setRotateState(){
        currentState=rotateState;
    }
    public void setRotateLeftState(){currentState=rotateLeftState;}
    public void setSelectState(){
        currentState=selectState;
    }
    public void setZoomState(){
        currentState=zoomState;
    }
}
