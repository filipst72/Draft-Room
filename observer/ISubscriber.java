package raf.draft.dsw.controller.observer;

import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.messages.Notification;

public interface ISubscriber {
     void update(Notification notification);

}
