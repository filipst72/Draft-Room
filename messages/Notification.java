package raf.draft.dsw.model.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.painters.ElementPainter;

@Getter @Setter
public class Notification {
    Message message;
    DraftNode draftNode;
    @JsonIgnore
    RoomView roomView;
    @JsonIgnore
    ElementPainter ep;
    public Notification(){
        message=new Message();
    }

}
