package s3;
/*************************************************************************
 *************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

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

    }

    public Node insert_recursive(Point2D p, Node node, boolean vertical, RectHV rect) {
        if (node == null)
            return new Node(p, rect);
        if (vertical) {
            if (p.x() < node.key.x()) {
                rect = new RectHV(rect.xmin(), rect.ymin(), p.x(), rect.ymax());
                node.left = insert_recursive(p, node.left, false, rect);
            } else {
                rect = new RectHV(p.x(), rect.ymin(), rect.xmax(), rect.ymax());
                node.right = insert_recursive(p, node.right, false, rect);
            }
        }
        if (!vertical) {
            if (p.y() < node.key.y()) {
                rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p.y());
                node.left = insert_recursive(p, node.left, true, rect);
            } else {
                rect = new RectHV(rect.xmin(), p.y(), rect.xmax(), rect.ymax());
                node.right = insert_recursive(p, node.right, true, rect);
            }
        }
        return node;
    }


    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return false;
    }

    public boolean contains_recur_left() {
        return true;
    }

    public boolean contains_recur_right() {
        return false;
    }

    // draw all of the points to standard draw
    public void draw() {

    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        return p;
    }

    /*******************************************************************************
     * Test client
     ******************************************************************************/
    public static void main(String[] args) {
        KdTree tree = new KdTree();
        Point2D a = new Point2D(0.3, 0.2);
        Point2D b = new Point2D(0.2, 0.3);
        tree.insert(a);
        StdOut.println(tree.root.key.x());
        tree.insert(b);
        StdOut.println(tree.root.left.key.x());
    }
}

