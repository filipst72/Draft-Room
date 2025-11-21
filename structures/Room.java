package raf.draft.dsw.model.structures;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.model.elements.RoomElement;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.messages.Notification;
import raf.draft.dsw.model.messages.NotificationType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@JsonSerialize
public class Room extends DraftNodeComposite implements IPublisher {

    float visina;
    float sirina;
    float ratioV;
    @JsonIgnore
    boolean nacrtatiSobu = false;
    float ratioS;
    List<RoomElement> elementiSobe=new ArrayList<>();
    @JsonIgnore
    transient Notification notification;

    @JsonIgnore
    transient List<ISubscriber> subscribers=new ArrayList<>();

    public Room(String ime, DraftNode parent) {
        super(ime, parent);
        notification = new Notification();
    }
    @JsonCreator
    public Room(
            @JsonProperty("ime") String ime,
            @JsonProperty("visina") float visina,
            @JsonProperty("sirina") float sirina,
            @JsonProperty("ratioV") float ratioV,
            @JsonProperty("ratioS") float ratioS,
            @JsonProperty("children") List<DraftNode> children,
            @JsonProperty("elementiSobe") List<RoomElement> elementiSobe) {
        super(ime, null);
        this.visina = visina;
        this.sirina = sirina;
        this.ratioV = ratioV;
        this.ratioS = ratioS;
        this.setChildren(children);
        this.elementiSobe = elementiSobe != null ? elementiSobe : new ArrayList<>();

        for (RoomElement elem : this.elementiSobe) {elem.setParent(this);}
        for(DraftNode n : this.getChildren()){n.setParent(this);}
        notification = new Notification();
        this.nacrtatiSobu = true;
        /*notification.getMessage().setPoruka(NotificationType.Nacrtaj
        .toString());
        notifySubs(notification,null);*/
    }
    @Override
    public void addChild(DraftNode node) {
        if (node != null && node instanceof RoomElement roomElement){
            if (!elementiSobe.contains(roomElement)){
                this.elementiSobe.add(roomElement);
                this.getChildren().add(roomElement);
                roomElement.setParent(this);
            }
        }
        notification.getMessage().setPoruka(NotificationType.Repaint.toString());
        notifySubs(notification,null);
    }

    @Override
    public void removeChild(DraftNode node) {
        this.getElementiSobe().remove(node);
        this.getChildren().remove(node);
        notification.getMessage().setPoruka(NotificationType.Repaint.toString());
        notifySubs(notification,null);
    }


    @Override
    public void addSubscriber(ISubscriber iSubscriber, NotificationType notificationType) {
        subscribers.add(iSubscriber);
    }

    @Override
    public void removeSubsciber(ISubscriber iSubscriber) {
        subscribers.remove(iSubscriber);
    }

    @Override
    public void notifySubs(Notification notification, NotificationType notificationType) {
            for(ISubscriber subscriber : subscribers){
                subscriber.update(notification);
            }
    }
}
