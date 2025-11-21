package raf.draft.dsw.core;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.messagegenerator.*;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.Serializer.JacksonSerializer;
import raf.draft.dsw.model.messages.NotificationType;
import raf.draft.dsw.model.repository.DraftRoomExplorerImplementation;
import raf.draft.dsw.model.repository.DraftRoomRepository;


@Setter
@Getter
public class ApplicationFramework {
    //buduca polja za model celog projekta

    private static ApplicationFramework instance;
    private LoggerFactory loggerFactory;
    private MessageGenerator messageGenerator;
    private DraftRoomRepository draftRoomRepository;
    private JacksonSerializer jacksonSerializer;

    private ApplicationFramework(){

    }

    public static ApplicationFramework getInstance() {
        if(instance==null){
            instance= new ApplicationFramework();
        }
        return instance;
    }

    public void initialize(){
        jacksonSerializer = new JacksonSerializer();
        draftRoomRepository = new DraftRoomExplorerImplementation();
        messageGenerator  = new MessageGenerator();
        MainFrame.getInstance().initialize();
        messageGenerator.addSubscriber(MainFrame.getInstance(), NotificationType.PORUKE);
        loggerFactory = new LoggerFactory();
        Logger consoleLogger = loggerFactory.createLogger("console");
        Logger fileLogger = loggerFactory.createLogger("file");

    }


}
