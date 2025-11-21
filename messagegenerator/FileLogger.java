package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.messages.Notification;
import raf.draft.dsw.model.messages.NotificationType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger{
    File file=new File("src/main/resources/log.txt");
    public FileLogger(){
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this, NotificationType.PORUKE);
    }

    @Override
    public void update(Notification notification) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
            bw.write(notification.getMessage().getPoruka());
            bw.newLine();
            bw.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
