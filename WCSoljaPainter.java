package raf.draft.dsw.model.painters;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.elements.WCSolja;

import java.awt.*;
import java.awt.geom.*;

@Getter@Setter
public class WCSoljaPainter implements ElementPainter {
    WCSolja draftNode;
    Rectangle hitbox;
    boolean zakacen;
    Color color= MainFrame.getInstance().getBackground();
    public WCSoljaPainter(WCSolja draftNode) {
        this.draftNode = draftNode;
    }

    @Override
    public void paint(Graphics2D graphics) {
        int x = (int) (draftNode.getLokacija().getX() - draftNode.getSirina()/2);
        int y = (int) (draftNode.getLokacija().getY() -draftNode.getVisina()/2);

        graphics.setColor(Color.BLACK);
        graphics.drawRect( x,y, (int) draftNode.getSirina(),
                (int) draftNode.getVisina()/4);

        graphics.drawLine(x, (int)(y+draftNode.getVisina()/4),x,
                (int)(y+draftNode.getVisina()/2));
        graphics.drawLine((int) (x+draftNode.getSirina()),
                (int)(y+draftNode.getVisina()/4), (int) (x+draftNode.getSirina()),
                (int)(y+draftNode.getVisina()/2));

        graphics.drawArc(x, (int) (y-draftNode.getVisina()/4),
                (int) draftNode.getSirina(),
                (int) ((int) draftNode.getVisina()+draftNode.getVisina()/4),180
                ,180);

        graphics.draw(new Ellipse2D.Double(x+ draftNode.getSirina()/3, y+ draftNode.getVisina()/3, draftNode.getSirina()/3, draftNode.getVisina()/3));
        AffineTransform affineTransformStart = graphics.getTransform();
        AffineTransform affineTransformRotate = affineTransformStart;
        hitbox =
                new Rectangle((int) ((int) draftNode.getLokacija().getX()-draftNode.getSirina()/2-4), (int) (draftNode.getLokacija().getY()-draftNode.getVisina()/2-4),
                        (int) (draftNode.getSirina()+8),
                        (int) (draftNode.getVisina()+8));
        if(draftNode.getRotacija()%2!=0){
            hitbox =
                    new Rectangle((int) ((int) draftNode.getLokacija().getX()- draftNode.getVisina()/2-4),
                            (int) ((int) draftNode.getLokacija().getY()-draftNode.getSirina()/2-4), (int) (draftNode.getVisina()+8), (int) (draftNode.getSirina()+8));
           /*affineTransformRotate.rotate(Math.PI/2,
                    draftNode.getLokacija().getX()+draftNode.getSirina()/2,
                    (int) draftNode.getLokacija().getY()+getDraftNode()
                    .getVisina()/2);*/

             affineTransformRotate.rotate(Math.PI/2,
                     getDraftNode().getLokacija().getX(),
                     getDraftNode().getLokacija().getY());
              //affineTransformRotate.createTransformedShape(hitbox);

           graphics.setTransform(affineTransformRotate);
        }
        else{
            hitbox =
                    new Rectangle((int) ((int) draftNode.getLokacija().getX()-draftNode.getSirina()/2-4), (int) (draftNode.getLokacija().getY()-draftNode.getVisina()/2-4),
                            (int) (draftNode.getSirina()+8),
                            (int) (draftNode.getVisina()+8));
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
