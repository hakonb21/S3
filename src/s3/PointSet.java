package s3;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class PointSET {
    private int size;
    private RedBlackBST<Point2D, Integer> pointset;
    RectHV rect;

    public PointSET() {
        this.pointset = new RedBlackBST<Point2D, Integer>();
        this.size = 0;
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

    public Point2D minPoint() {
        return this.pointset.min();
    }

    public void draw() {
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();
        for (Point2D p : this.pointset.keys()) {
            p.draw();
        }
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> insideRect = new ArrayList<Point2D>();
        for (Point2D p : this.pointset.keys()) {
            if (rect.contains(p))
                insideRect.add(p);
        }
        return insideRect;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        Double closest = Double.POSITIVE_INFINITY;
        Point2D returnPoint = new Point2D(0, 0);
        for (Point2D point : this.pointset.keys()) {
            if (p.distanceTo(point) <= closest) {
                closest = p.distanceTo(point);
                returnPoint = point;
            }
        }
        return returnPoint;
    }

    public static void main(String[] args) {

    }
}
