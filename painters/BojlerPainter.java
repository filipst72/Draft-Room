package raf.draft.dsw.model.painters;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.elements.Bojler;

import java.awt.*;
@Getter@Setter
public class BojlerPainter implements ElementPainter{

    Bojler draftNode;
    Rectangle hitbox;
    boolean zakacen;
    Color color= MainFrame.getInstance().getBackground();
    public BojlerPainter(Bojler draftNode) {
        this.draftNode = draftNode;

    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);

        int poluprecnik =(int) draftNode.getSirina();
        if(draftNode.getSirina()>draftNode.getVisina())
        {poluprecnik = (int) draftNode.getVisina();}
        int centarX = (int) draftNode.getLokacija().getX()-poluprecnik/2;
        int centarY = (int) draftNode.getLokacija().getY()-poluprecnik/2;
        hitbox = new Rectangle(centarX-2, centarY-2,
                poluprecnik+4,
                poluprecnik+4);
        graphics.drawOval(centarX , centarY ,  poluprecnik,  poluprecnik);
        graphics.drawLine(centarX+poluprecnik/4, centarY+poluprecnik/4,
                centarX + poluprecnik- poluprecnik/4,
                centarY + poluprecnik-poluprecnik/4 );
        graphics.drawLine(centarX+poluprecnik-poluprecnik/4,
                centarY+poluprecnik/4,
                centarX + poluprecnik/4,
                centarY + poluprecnik-poluprecnik/4 );
        graphics.setColor(color);
        graphics.draw(hitbox);
    }

    @Override
    public boolean elementAt(Point location) {
        return hitbox.contains(location);
    }

    @Override
    public boolean intersect(Rectangle rectangle) {
        return hitbox.intersects(rectangle);
    }

    @Override
    public void setHitboxColor(Color color) {
        this.color=color;
    }

    @Override
    public void rotateRight(double angle, int centarx, int centary) {

    }

    @Override
    public void rotateLeft(double angle, int centarx, int centary) {

    }

    @Override
    public void setHitbox(int x, int y, int height, int width) {
        this.hitbox = new Rectangle(x, y, height, width);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
