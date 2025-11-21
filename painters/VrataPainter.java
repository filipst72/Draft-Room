package raf.draft.dsw.model.painters;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.elements.Vrata;

import java.awt.*;
import java.awt.geom.*;

@Getter@Setter
public class VrataPainter implements ElementPainter{
    Vrata draftNode;
    Shape shape;
    Rectangle hitbox;
    boolean zakacen;
    Color color= MainFrame.getInstance().getBackground();

    public VrataPainter(Vrata draftNode) {
        this.draftNode = draftNode;
    }

    @Override
    public void paint(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);

        int width =(int) draftNode.getSirina();
        int height =(int) draftNode.getVisina();
        if(width > height)width=height;
        else height=width;
        int x = (int) draftNode.getLokacija().getX()-width/2;
        int y = (int) draftNode.getLokacija().getY()-height/2;
        AffineTransform affineTransformStart = graphics.getTransform();
        AffineTransform affineTransformRotate = affineTransformStart;
        if(draftNode.getRotacija()%2!=0){

            hitbox = new Rectangle(x-4,
                   y-4, (int) (width+8),
                    (int) (width+8));
            affineTransformRotate.rotate(Math.PI/2,draftNode.getLokacija().getX(), (int) draftNode.getLokacija().getY());
            graphics.setTransform(affineTransformRotate);
        }
        else{
            hitbox = new Rectangle(x-4, y-4, (int) (width+8),
                    (int) (width+8));

        }
        graphics.drawArc(x-width, y, width*2, height*2, 90, -90);
        graphics.drawLine(x,y,x,y+height);



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
