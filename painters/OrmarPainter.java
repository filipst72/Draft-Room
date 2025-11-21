package raf.draft.dsw.model.painters;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.elements.Ormar;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Getter@Setter
public class OrmarPainter implements ElementPainter{
    Ormar draftNode;
    Rectangle hitbox;
    boolean zakacen;
    Color color= MainFrame.getInstance().getBackground();
    public OrmarPainter(Ormar draftNode) {
        this.draftNode = draftNode;
    }

    @Override
    public void paint(Graphics2D graphics) {
        int x = (int) draftNode.getLokacija().getX();
        int y = (int) draftNode.getLokacija().getY();
        int width = (int) draftNode.getSirina();
        int height =(int) draftNode.getVisina();

        graphics.setColor(Color.BLACK);
        graphics.drawRect(x-width/2, y-height/2, width, height);

        int midX = x + width / 2;
        graphics.drawLine(midX-width/2, y-height/2, midX-width/2, y + height/2);

        int offset = 10;
        int dotSize = 6;

        int[][] points = {
                {midX-width/2 - offset, y },
                {midX-width/2 + offset, y }
        };
        for (int[] point : points) {
            graphics.fillOval(point[0] - dotSize / 2, point[1] - dotSize / 2, dotSize, dotSize);
        }
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
