package raf.draft.dsw.model.painters;

import raf.draft.dsw.model.nodes.DraftNode;

import java.awt.*;

public interface ElementPainter {
    void paint(Graphics2D graphics);
    boolean elementAt(Point location);
    boolean intersect(Rectangle rectangle);
    void setHitboxColor(Color color);
    void rotateRight(double angle,int centarx,int centary);
    void rotateLeft(double angle,int centarx,int centary);
    DraftNode getDraftNode();
    Rectangle getHitbox();
    boolean isZakacen();
    void setHitbox(int x, int y, int height, int width);
    void setZakacen(boolean zakacen);
}
