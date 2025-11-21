package raf.draft.dsw.model.painters;

import raf.draft.dsw.model.elements.*;
import raf.draft.dsw.model.nodes.DraftNode;

public class PainterFactory {

    public  static ElementPainter kreirajPainter(RoomElement roomElement)
    {
        if(roomElement instanceof Bojler)
            return new BojlerPainter((Bojler) roomElement);
        if(roomElement instanceof Krevet)
            return new KrevetPainter((Krevet) roomElement);
        if(roomElement instanceof Kada)
            return new KadaPainter((Kada) roomElement);
        if(roomElement instanceof LavaboK)
            return new LavaboPainter((LavaboK) roomElement);
        if(roomElement instanceof Ormar)
            return new OrmarPainter((Ormar) roomElement);
        if(roomElement instanceof Sto)
            return new StoPainter((Sto) roomElement);
        if(roomElement instanceof VesMasina)
            return new VesMasinaPainter((VesMasina) roomElement);
        if(roomElement instanceof Vrata)
            return new VrataPainter((Vrata) roomElement);
        if (roomElement instanceof WCSolja)
            return new WCSoljaPainter((WCSolja) roomElement);
        return null;
    }
}
