import java.util.NoSuchElementException;
import java.util.Scanner;

public class BinaryTree {

    private static boolean empty = true;

    public static void main(String[] args) {
        //System.setIn(BinaryTree.class.getResourceAsStream("teste"));
        Scanner sc = new Scanner(System.in);
        String line;
        Tree newTree = null;
        do {
            try {
                line = sc.nextLine();
            } catch (NoSuchElementException e) {
                break;
            }
            
            newTree = getFunction(line, newTree);
        } while (!line.isEmpty());

    }

    private static Tree getFunction(String line, Tree iTree) {
        String[] newArray;

        if (line.contains("PASS")) {
            
            empty = false;
            newArray = line.split(" ");
            if (iTree == null) {
                iTree = new Tree(new Node(new Car(newArray[2], newArray[1]), null, null));
            } else {
                iTree.addNode(new Node(new Car(newArray[2], newArray[1]), null, null), iTree.getRoot());
            }

        } else if (line.contains("IMPRIME")) {
            if (!empty) {
                //iTree.list(iTree.getRoot());
            }
        } else if (line.contains("STATUS")) {
            newArray=line.split(" ");
            if(!empty){
                boolean ver = iTree.getNode(newArray[1], iTree.getRoot(), "STATUS");
                if(!ver)
                    System.out.println(newArray[1] +" NO RECORD");
            }
            else
                System.out.println(newArray[1] +" NO RECORD");
            
        }else if(line.contains("UNFLAG")){
            if(!empty){
                newArray=line.split(" ");
                iTree.getNode(newArray[1], iTree.getRoot(), "UNFLAG");
            }
        }
        return iTree;
    }
}

class Tree {

    private Node root;
    private int numNodes = 0;

    public Tree(Node root) {
        this.root = root;
    }

    public Node addNode(Node x, Node iNode) {
        numNodes++;
        if (iNode == null) {
            iNode = x;
        } else if (x.compareTo(iNode) < 0) {
            iNode.setLeft(addNode(x, iNode.getLeft()));
        } else if (x.compareTo(iNode) > 0) {
            iNode.setRight(addNode(x, iNode.getRight()));
        } else if (x.compareTo(iNode) == 0) {
            
            iNode.getElement().setCount(iNode.getElement().getCount()+1);
            iNode.getElement().setStatus(x.getElement().getStatus());
        }
        return iNode;

    }

//    public void list(Node iNode) {
//
//        // System.out.println("cicle "+iNode.getElement().getNif());
//        numNodes++;
//        if (iNode.getLeft() != null) {
//            list(iNode.getLeft());
//        }
//        System.out.println((iNode.getElement().getNif()) + " TEM ACUMULADO " + iNode.getElement().getValue());
//        if (iNode.getRight() != null) {
//            list(iNode.getRight());
//        }
//    }

    public boolean getNode(String id, Node iNode, String opType) {
        numNodes++;
        if (iNode == null) {
            return false;
        } else if (id.compareTo(iNode.getElement().getId())<0) {
            return getNode(id,iNode.getLeft(),opType);
        } else if (id.compareTo(iNode.getElement().getId())>0) {
            return getNode(id,iNode.getRight(),opType);
        } else{
            if(opType.equals("STATUS"))
                System.out.println(iNode.getElement().getId()+" "+iNode.getElement().getCount()+" "+iNode.getElement().getStatus());
            else
                iNode.getElement().setStatus("R");
            return true;
        }
        
    }

    public Node getRoot() {
        return root;
    }

    public int getNumNodes() {
        return numNodes;
    }
    
}

class Node {

    private Car element;
    private Node right, left;

    public Node(Car element, Node right, Node left) {
        this.element = element;
        this.right = right;
        this.left = left;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Car getElement() {
        return element;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setElement(Car element) {
        this.element = element;
    }

    public int compareTo(Object t) {
        Node aux = (Node) t;
        if (this.getElement().getId().compareTo(aux.getElement().getId())<0) {
            return -1;
        } else if (this.getElement().getId().compareTo(aux.getElement().getId())>0) {
            return 1;
        }
        return 0;

    }
}

class Car {

    private int count;
    private String id,status;

    public Car(String status, String id) {
        this.status = status;
        this.id = id;
        this.count = 1;
    }

    public Car() {
    }

    public String getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
