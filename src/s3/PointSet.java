package s3;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;

public class PointSet {
    private RedBlackBST pointset;
    private int size;
    RectHV rect;

    public PointSet() {
        this.pointset = new RedBlackBST();
        this.size = 0;
        this.rect = new RectHV(0, 0, 0, 0); //Þetta á örugglega ekki að vera herna, bara nota þetta í ákveðnu falli

    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        this.pointset.put(p, 0);
        this.size++;

    }

    public boolean contains(Point2D p) {
        return this.pointset.contains(p);
    }

    public void draw() {

    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        return;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        Point2D a = new Point2D(5, 4);
        return a;

    }

    public static void main(String[] args) {

    }
}
