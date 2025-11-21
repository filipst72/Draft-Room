package raf.draft.dsw.controller.messagegenerator;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.messages.Notification;
import raf.draft.dsw.model.messages.NotificationType;
import raf.draft.dsw.model.nodes.DraftNode;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Setter

public class MessageGenerator implements IPublisher {
    //List<ISubscriber> subscriberList=new ArrayList<>();
    Map<NotificationType, List<ISubscriber>> subscribers = new HashMap<>();
    private Notification notification=new Notification();

    public void kreirajPoruku(MessageType messageType)
    {
        LocalTime vreme = LocalTime.now();
        LocalDate datum = LocalDate.now();

        if (messageType.toString().equals("NijeSelektovano")) {
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Nista nije selektovano"));
        }
        else if(messageType.toString().equals("ProjectExplorerDelete")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Project Explorer se ne moze brisati"));
        }
        else if(messageType.toString().equals("ProjectExplorerEdit")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Project Explorer se ne moze editovati"));
        }
        else if(messageType.toString().equals("RoomDodavanje")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Room ne moze imati decu"));
        }
        else if(messageType.toString().equals("PraznoPolje")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Morate uneti vrednost u polje"));
        }
        else if(messageType.toString().equals("NijeSelektovanObjekat")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Morate izabrati tip objekta"));
        }
        else if(messageType.toString().equals("IstoIme")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Objekat sa istim imenom vec postoji"));
        }
        else if(messageType.toString().equals("PrazanProjekat")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Projekat nema sobe"));
        }
        else if(messageType.toString().equals( "VisinaISirinaIsto")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Visina i sirina moraju biti isti"));
        }else if(messageType.toString().equals( "NemaSobe")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Morate prvo zadati dimenzije sobe"));
        }else if(messageType.toString().equals( "VecImaSoba")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Vec su zadate dimenzije sobe"));
        }else if(messageType.toString().equals( "Preseca")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Element sobe se preklapa sa drugim"));
        }else if(messageType.toString().equals( "PreviseObjekata")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Resize se moze vrsiti za samo 1 element sobe"));
        }else if(messageType.toString().equals( "NemaDalje")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Nema vise akcija"));
        }else if(messageType.toString().equals( "VanSobe")){
            this.notification.getMessage().setPoruka(("[ERROR]" + "[" + datum + "]" + "[" + vreme + "] Ne mozete dodati element van sobe"));
        }
        this.notifySubs(notification, NotificationType.PORUKE);

    }
    public void desavanjaUModelu(MessageType messageType, DraftNode draftNode)
    {
        notification.getMessage().setPoruka(messageType.toString());
        notification.setDraftNode(draftNode);
        notifySubs(notification, NotificationType.DESAVANJA_U_MODELU);
    }
    public void crtanje(RoomView roomView){
        notification.getMessage().setPoruka(NotificationType.Repaint.toString());
        notification.setRoomView(roomView);
        notifySubs(notification,NotificationType.Repaint);
    }

    @Override
    public void addSubscriber(ISubscriber iSubscriber, NotificationType notificationType) {
        //subscriberList.add(iSubscriber);
        subscribers.computeIfAbsent(notificationType, k -> new ArrayList<>()).add(iSubscriber);
    }


    @Override
    public void removeSubsciber(ISubscriber iSubscriber) {
        for (List<ISubscriber> subscriber : subscribers.values()) {
            for (ISubscriber sub : subscriber) {
                if (sub == iSubscriber) {
                    subscriber.remove(iSubscriber);
                }
            }
        }
    }

    @Override
    public void notifySubs(Notification notification, NotificationType notificationType) {
        List<ISubscriber> subscriber = subscribers.get(notificationType);
        for (ISubscriber sub : subscriber) {
            sub.update(notification);
        }
    }

}


