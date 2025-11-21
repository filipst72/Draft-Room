package raf.draft.dsw.model.painters;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.elements.Kada;

import java.awt.*;
import java.awt.geom.AffineTransform;
@Getter@Setter
public class KadaPainter implements ElementPainter{

    Kada draftNode;
    Rectangle hitbox;
    Color color= MainFrame.getInstance().getBackground();
    AffineTransform affineTransformStart;
    AffineTransform affineTransformRotate;
    boolean zakacen;
    public KadaPainter(Kada kada) {
        this.draftNode = kada;
    }

    @Override
    public void paint(Graphics2D graphics) {



        int x = (int) draftNode.getLokacija().getX();
        int y = (int) draftNode.getLokacija().getY();
        int width = (int) draftNode.getSirina();
        int height = (int) draftNode.getVisina();
        int padding = 10;

        graphics.setColor(Color.BLACK);
        graphics.drawRect(x-width/2, y-height/2, width, height);

        int ovalX = x + padding;
        int ovalY = y + padding;
        int ovalWidth = width - 2 * padding;
        int ovalHeight = height - 2 * padding;
        graphics.setColor(Color.BLACK);
        graphics.drawOval(ovalX-width/2, ovalY-height/2, ovalWidth, ovalHeight);
        affineTransformStart = graphics.getTransform();
        affineTransformRotate = affineTransformStart;
        if(draftNode.getRotacija()%2!=0){
            hitbox =
                    new Rectangle((int) draftNode.getLokacija().getX()-height/2-2,
                    (int) draftNode.getLokacija().getY()-width/2-2,height+4,
                    width+4);
            affineTransformRotate.rotate(Math.PI/2,draftNode.getLokacija().getX(), (int) draftNode.getLokacija().getY());
            graphics.setTransform(affineTransformRotate);
        }
        else{
            hitbox =
                    new Rectangle((int) draftNode.getLokacija().getX()-width/2-4,
                    (int) draftNode.getLokacija().getY()-height/2-4,width+8,
                    height+8);

        }
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

    public Rectangle getHitbox() {
        return hitbox;
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
}
