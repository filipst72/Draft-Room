package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.messages.Notification;
import raf.draft.dsw.model.messages.NotificationType;

public class ConsoleLogger implements Logger{
    public ConsoleLogger(){
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this, NotificationType.PORUKE);
    }

    @Override
    public void update(Notification notification) {
        System.out.println(notification.getMessage().getPoruka());
    }
}
