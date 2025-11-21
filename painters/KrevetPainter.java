package raf.draft.dsw.model.painters;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.elements.Krevet;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

@Getter@Setter
public class KrevetPainter implements ElementPainter{
    Krevet draftNode;
    Shape shape;
    Rectangle hitbox;
    boolean zakacen;
    Color color= MainFrame.getInstance().getBackground();
    AffineTransform affineTransformStart;
    AffineTransform affineTransformRotate;
    public KrevetPainter(Krevet draftNode) {
        this.draftNode = draftNode;
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.setPaint(Color.BLACK);
        affineTransformStart = graphics.getTransform();
        affineTransformRotate = affineTransformStart;
        int sirina= (int) draftNode.getSirina();
        int visina=(int) draftNode.getVisina();
        Rectangle2D.Double krevetOblik = new Rectangle2D.Double((int) draftNode.getLokacija().getX()-sirina/2, (int) draftNode.getLokacija().getY()-visina/2,sirina,visina);
        this.setShape(krevetOblik);
        graphics.draw(this.getShape());
        double jastukX = krevetOblik.getX() + (krevetOblik.getWidth() * 0.1);
        double jastukY = krevetOblik.getY() + (krevetOblik.getHeight() * 0.1);
        double jastukSirina = krevetOblik.getWidth() * 0.8;
        double jastukVIsina = krevetOblik.getHeight() * 0.3;
        graphics.fill(new Rectangle2D.Double(jastukX, jastukY, jastukSirina, jastukVIsina));
        graphics.draw(new Rectangle2D.Double(jastukX, jastukY, jastukSirina, jastukVIsina));
        affineTransformStart = graphics.getTransform();
        affineTransformRotate = affineTransformStart;
        if(draftNode.getRotacija()%2!=0){
            hitbox =
                    new Rectangle((int) draftNode.getLokacija().getX()-visina/2-4,
                    (int) draftNode.getLokacija().getY()-sirina/2-4,visina+8,
                            sirina+8);
            affineTransformRotate.rotate(Math.PI/2,draftNode.getLokacija().getX(), (int) draftNode.getLokacija().getY());
            graphics.setTransform(affineTransformRotate);
        }
        else{
            hitbox =
                    new Rectangle((int) draftNode.getLokacija().getX()-sirina/2-4,
                    (int) draftNode.getLokacija().getY()-visina/2-4,sirina+8,
                    visina+8);

        }
        graphics.setColor(color);
        graphics.draw(hitbox);
        graphics.setTransform(affineTransformStart);
    }

    @Override
    public boolean elementAt(Point location) {
        return getHitbox().contains(location);
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
