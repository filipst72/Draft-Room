package raf.draft.dsw.controller.node_factory;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.AddItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

public class NodeFactory {
    public NodeFactory(){
    }

    public static DraftNode fabrika(DraftNode node,String ime,String autor,String putanja,Boolean room,Boolean building){
        if (node instanceof ProjectExplorer) {
           return new Project(ime,autor,putanja,node);
        }
        else if(node instanceof Project){
            if(room==true){
                return new Room(ime,node);
            }
            else if(building==true){
                return new Building(ime,node);
            }
        }
        else if(node instanceof Building){
            return new Room(ime,node);
        }
        else if(node instanceof Room){
            return null;
        }
        return null;
    }

    public static void dodajUModel(DraftNode parent, DraftNode child) {
        if (parent instanceof ProjectExplorer) {
            ApplicationFramework.getInstance().getDraftRoomRepository().getRoot().getChildren().add(child);
        } else if (parent instanceof Project) {
            DraftNode projekat = ApplicationFramework.getInstance().getDraftRoomRepository().getRoot().getChildren().get(ApplicationFramework.getInstance().getDraftRoomRepository().getRoot().getChildren().indexOf(parent));
            ((Project) projekat).getChildren().add(child);
        } else if (parent instanceof Building) {
            DraftNode projekat =
                    ApplicationFramework.getInstance().getDraftRoomRepository().getRoot().getChildren().get(ApplicationFramework.getInstance().getDraftRoomRepository().getRoot().getChildren().indexOf(parent.getParent()));
            DraftNode building = ((Project) projekat).getChildren().get(ApplicationFramework.getInstance().getDraftRoomRepository().getRoot().getChildren().indexOf(parent));
            ((Building) building).getChildren().add(child);
        }
    }
}
