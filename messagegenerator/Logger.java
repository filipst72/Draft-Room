package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.messages.Notification;

import java.util.concurrent.Flow;

public interface Logger extends ISubscriber{
    @Override
    void update(Notification notification);

}
