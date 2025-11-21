package raf.draft.dsw.model.painters;

import java.awt.*;
import java.awt.geom.AffineTransform;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.elements.LavaboK;

@Getter@Setter
public class LavaboPainter implements ElementPainter{
    LavaboK draftNode;
    Rectangle hitbox;
    boolean zakacen;

    Color color= MainFrame.getInstance().getBackground();
    public LavaboPainter(LavaboK draftNode) {
        this.draftNode = draftNode;
    }

    @Override
    public void paint(Graphics2D graphics) {
        int x1 = (int) draftNode.getLokacija().getX()- (int) draftNode.getSirina()/2, y1 = (int) draftNode.getLokacija().getY()- (int) draftNode.getVisina()/2;
        int x2 = x1+(int) draftNode.getSirina(), y2 = y1;
        int x3 = (int) draftNode.getLokacija().getX() , y3 = (int) draftNode.getLokacija().getY()+ (int) draftNode.getVisina()/2;

        graphics.setColor(Color.BLACK);
        int[] xPoints = {x1, x2, x3};
        int[] yPoints = {y1, y2, y3};

        graphics.drawPolygon(xPoints, yPoints, 3);
        int centerX = (x1 + x2 + x3) / 3;
        int centerY = (y1 + y2 + y3) / 3;

        int dotSize = 6;
        graphics.fillOval(centerX - dotSize / 2, centerY - dotSize / 2, dotSize, dotSize);
        AffineTransform affineTransformStart = graphics.getTransform();
        AffineTransform affineTransformRotate = affineTransformStart;
        if(draftNode.getRotacija()%2!=0){
            hitbox = new Rectangle((int) ((int) draftNode.getLokacija().getX()- draftNode.getVisina()/2-2),
                    (int) ((int) draftNode.getLokacija().getY()-draftNode.getSirina()/2-2), (int) (draftNode.getVisina()+4), (int) (draftNode.getSirina()+4));
            affineTransformRotate.rotate(Math.PI/2,draftNode.getLokacija().getX(), (int) draftNode.getLokacija().getY());
            graphics.setTransform(affineTransformRotate);
        }
        else{
            hitbox = new Rectangle((int) ((int) draftNode.getLokacija().getX()-draftNode.getSirina()/2-2),
                    (int) ((int) draftNode.getLokacija().getY()- draftNode.getVisina()/2-2), (int) (draftNode.getSirina()+4),
                    (int) (draftNode.getVisina()+4));

        }
        graphics.setColor(color);
        graphics.draw(hitbox);
    }
    public Rectangle getHitbox() {
        return hitbox;
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
}
