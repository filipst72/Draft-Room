package raf.draft.dsw.controller.observer;

import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.messages.Notification;
import raf.draft.dsw.model.messages.NotificationType;

public interface IPublisher {
    void addSubscriber(ISubscriber iSubscriber, NotificationType notificationType);
    void removeSubsciber(ISubscriber iSubscriber);
    void notifySubs(Notification notification, NotificationType notificationType);
}
