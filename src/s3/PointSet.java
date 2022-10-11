package s3;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSet {

    public PointSet() {
    }

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return 1;
    }

    public void insert() {

    }

    public boolean contains() {
        return false;
    }

    public void draw() {

    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        return new Point2D(3, 3);
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        Point2D a = new Point2D(5, 4);
        return a;

    }

    public static void main(String[] args) {

    }
}
