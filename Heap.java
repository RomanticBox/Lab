/*
 * Name: 이성현
 * Student ID #: 2020114010
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public final class Heap<T extends Comparable> implements IHeap<T> {



    private Node<T> head;
    private Node<T> last;
    private int size;

    private final class Node<T extends Comparable> {
        private T value;
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;

        private Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        private Node<T> getSmaller(Node<T> other) {
            if (other == null) return this;
            return this.value.compareTo(other.value) <= 0 ? this : other;
        }

        private boolean childIsSmaller() {
            if (this.left == null && this.right == null) {
                return false;
            }
            if (this.left == null) {
                return this.right.value.compareTo(this.value) < 0;
            }
            if (this.right == null) {
                return this.left.value.compareTo(this.value) < 0;
            }
            return this.right.value.compareTo(this.value) < 0
                    || this.left.value.compareTo(this.value) < 0;
        }

        private boolean parentIsLarger() {
            if (this.isRoot()) {
                return false;
            }
            return this.parent.value.compareTo(this.value) > 0;

        }

        private void swapUp() {
            System.out.printf("now running %s here\n", "swapUp");

            boolean isLeft = this.isLeftChild();
            Node<T> parent = this.parent;
            if (parent.isLeftChild()) {
                this.asLeftChild(parent.parent);
            } else if (parent.isRightChild()) {
                this.asRightChild(parent.parent);
            } else {
                this.parent = null;
            }
            if (isLeft) {
                swapUpAsLeft(parent);
                //write(parent.value + " swap", writer);
            } else {
                swapUpAsRight(parent);
            }
        }

        private void swapUpAsLeft(Node<T> parent) {
            System.out.printf("now running %s here\n", "swapUpAsLeft");
            Node<T> temp = parent.right;
            if (this.left != null)
                this.left.asLeftChild(parent);
            else parent.left = null;
            if (this.right != null)
                this.right.asRightChild(parent);
            else parent.right = null;
            if (temp != null)
                temp.asRightChild(this);
            else this.right = null;
            parent.asLeftChild(this);
        }

        private void swapUpAsRight(Node<T> parent) {
            System.out.printf("now running %s here\n", "swapUpAsRight");
            Node<T> temp = parent.left;
            if (this.left != null)
                this.left.asLeftChild(parent);
            else parent.left = null;
            if (this.right != null)
                this.right.asRightChild(parent);
            else parent.right = null;
            if (temp != null)
                temp.asLeftChild(this);
            else this.left = null;
            parent.asRightChild(this);
        }

        private void asLeftChild(Node<T> parent) {
            this.parent = parent;
            parent.left = this;
        }

        private void asRightChild(Node<T> parent) {
            this.parent = parent;
            parent.right = this;
        }

        private Node<T> leftMostLeaf() {
            Node<T> iter = this;
            while (iter.left != null) {
                iter = iter.left;
            }
            return iter;
        }

        private Node<T> rightMostLeaf() {
            Node<T> iter = this;
            while (iter.right != null) {
                iter = iter.right;
            }
            return iter;
        }

        private boolean isRightMostLeaf() {
            if (this.right != null)
                return false;
            Node<T> iter = this;
            while (!iter.isRoot()) {
                if (iter.isLeftChild())
                    return false;
                iter = iter.parent;
            }
            return true;
        }

        private boolean isLeftMostLeaf() {
            if (this.left != null)
                return false;
            Node<T> iter = this;
            while (!iter.isRoot()) {
                if (iter.isRightChild())
                    return false;
                iter = iter.parent;
            }
            return true;
        }

        private boolean isRightChild() {
            if (this.isRoot())
                return false;
            return !this.isLeftChild();
        }

        private boolean isLeftChild() {
            if (this.isRoot())
                return false;
            return this.parent.left == this;
        }

        private boolean isRoot() {
            return this.parent == null;
        }
    }

    Heap() {
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    private void upHeap() {
        System.out.printf("now running %s here\n", "upHeap");
        if (this.last.parentIsLarger()) {
            Node<T> temp = this.last.parent;
            Node<T> iter = this.last;
            System.out.printf("-->parentisLarger\n");;
            while (iter.parentIsLarger()) {
                iter.swapUp();
                if (iter.isRoot()) {
                    this.head = iter;
                }
            }
            this.last = temp;
        }
    }

    private void downHeap() {
        System.out.printf("now running %s here\n", "downHeap");
        if (this.head.childIsSmaller()) {
            Node<T> lC = this.head.left;
            Node<T> rC = this.head.right;
            Node<T> least = lC.getSmaller(rC);
            Node<T> iter = this.head;
            while (iter.childIsSmaller()) {
                if (iter.left == null) {
                    iter.right.swapUp();
                    continue;
                }
                Node<T> dir = iter.left.getSmaller(iter.right);
                dir.swapUp();
            }
            this.head = least;
        }
    }

    @Override
    public T min() {
        return this.head.value;
    }

    @Override
    public T removeMin() {
        if (this.head == null) return null;
        T rm = this.head.value;
        Node<T> tmp_last = this.last;
        if (this.last.isRoot()) {
            clear();
            return rm;
        } else if (this.last.isRightChild()) {
            this.last = this.last.parent.left;
            tmp_last.parent.right = null;
        } else if (this.last.isLeftMostLeaf()) {
            this.last = this.head.rightMostLeaf();
            tmp_last.parent.left = null;
        } else {
            Node<T> iter = this.last.parent;
            while (iter.isLeftChild()) {
                iter = iter.parent;
            }
            this.last = iter.parent.left.rightMostLeaf();
            tmp_last.parent.left = null;
        }
        tmp_last.parent = null;
        if (this.head.left != null)
            this.head.left.asLeftChild(tmp_last);
        if (this.head.right != null)
            this.head.right.asRightChild(tmp_last);
        this.head = tmp_last;
        downHeap();
        size--;
        return rm;
    }

    @Override
    public void insert(T entry) {
        System.out.printf("now running %s here\n", "downHeap");
        Node<T> newNode = new Node(entry);
        if (this.head == null) {
            this.head = newNode;
        } else if (this.last.isRightMostLeaf()) {
            newNode.asLeftChild(this.head.leftMostLeaf());
        } else if (this.last.isLeftChild()) {
            newNode.asRightChild(this.last.parent);
        } else {
            Node<T> iter = this.last;
            while (iter.isRightChild()) {
                iter = iter.parent;
            }
            newNode.asLeftChild(iter.parent.right.leftMostLeaf());
        }
        this.last = newNode;
        upHeap();
        this.size++;
    }

    @Override
    public void clear() {
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public void merge(Heap<T> otherHeap) {
        while (!otherHeap.isEmpty()) {
            insert(otherHeap.removeMin());
        }
    }
}
