package s3;
/*************************************************************************
 *************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {
    private Node root;
    private int size;
    private RectHV initial_rect;

    // construct an empty set of points
    public class Node {
        private Point2D key;
        private RectHV rect;
        private Node left;
        private Node right;

        public Node(Point2D key, RectHV rect) {
            this.key = key;
            this.rect = rect;
            Node left;
            Node right;
        }
    }

    public KdTree() {
        this.size = 0;
        this.initial_rect = new RectHV(0, 0, 1, 1);
    }

    // is the set empty?
    public boolean isEmpty() {
        return this.size == 0;
    }

    // number of points in the set
    public int size() {
        return this.size;
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (this.contains(p))
            return;

        this.root = insert_recursive(p, this.root, true, this.initial_rect);
        this.size++;

    }

    public Node insert_recursive(Point2D p, Node node, boolean vertical, RectHV rect) {
        if (node == null)
            return new Node(p, rect);
        if (vertical) {
            if (p.x() < node.key.x()) {
                rect = new RectHV(rect.xmin(), rect.ymin(), node.key.x(), rect.ymax());
                node.left = insert_recursive(p, node.left, false, rect);
            } else {
                rect = new RectHV(node.key.x(), rect.ymin(), rect.xmax(), rect.ymax());
                node.right = insert_recursive(p, node.right, false, rect);
            }
        }
        if (!vertical) {
            if (p.y() < node.key.y()) {
                rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.key.y());
                node.left = insert_recursive(p, node.left, true, rect);
            } else {
                rect = new RectHV(rect.xmin(), node.key.y(), rect.xmax(), rect.ymax());
                node.right = insert_recursive(p, node.right, true, rect);
            }
        }
        return node;
    }


    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return contains_recur(p, this.root, true);
    }

    public boolean contains_recur(Point2D p, Node node, boolean vertical) {
        if (node == null)
            return false;
        if (node.key.x() == p.x() && node.key.y() == p.y()) {
            return true;
        }
        if (vertical) {
            if (p.x() < node.key.x()) {
                return this.contains_recur(p, node.left, false);
            } else
                return this.contains_recur(p, node.right, false);
        } else if (!vertical) {
            if (p.y() < node.key.y())
                return this.contains_recur(p, node.left, true);
            else
                return this.contains_recur(p, node.right, true);

        }
        return false;

    }


    // draw all of the points to standard draw
    public void draw() {
        this.draw_recur(this.root, true);

    }

    public void draw_recur(Node node, boolean vertical) {
        if (node != null) {
            if (vertical) {
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(node.key.x(), node.rect.ymin(), node.key.x(), node.rect.ymax());

            } else {
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(node.rect.xmax(), node.key.y(), node.rect.xmin(), node.key.y());
            }

            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLACK);
            node.key.draw();

            draw_recur(node.left, false);
            draw_recur(node.right, false);
        }
    }


    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> pointlis = new ArrayList<Point2D>();
        return range_recur(rect, this.root, pointlis);
    }

    public List<Point2D> range_recur(RectHV rect, Node node, List<Point2D> lis) {
        if (node == null)
            return lis;
        if (rect.intersects(node.rect)) {
            if (rect.contains(node.key)) lis.add(node.key);
            lis = range_recur(rect, node.left, lis);
            lis = range_recur(rect, node.right, lis);
        }
        return lis;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        Point2D a = new Point2D(Double.MAX_VALUE, Double.MAX_VALUE);
        return nearest_recur(p, this.root, a);
    }

    public Point2D nearest_recur(Point2D p, Node node, Point2D closest_p) {
        if (node == null)
            return closest_p;
        if (node.rect.distanceTo(p) >= p.distanceTo(closest_p))
            return closest_p;
        if (node.key.distanceTo(p) < node.key.distanceTo(closest_p))
            closest_p = node.key;
        closest_p = nearest_recur(p, node.left, closest_p);
        closest_p = nearest_recur(p, node.right, closest_p);
        return closest_p;
    }

    /*******************************************************************************
     * Test client
     ******************************************************************************/
    public static void main(String[] args) {


    }
}

