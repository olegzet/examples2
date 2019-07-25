package com.olegzet.algorithm;

public class hareTortoise {
    public static void main(String[] args) {
        Node root = generateListWithLoop();

        System.out.println(hasLoop(root));
    }

    private static Node generateListWithLoop() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(n6);
        n6.setNext(n7);
        n7.setNext(n8);
        n8.setNext(n3);

        return n1;
    }

    private static boolean hasLoop(Node root) {
        if (root == null)
            return false;

        Node tortoise = root;
        Node hare = root;

        while (true) {
            tortoise = tortoise.getNext();

            System.out.println("tortoise: " + tortoise.getData());

            if (hare.getNext() != null)
                hare = hare.getNext().getNext();
            else
                return false;

            System.out.println("hare: " + hare.getData());
            
            if ((tortoise == null) || (hare == null))
                return false;

            if (tortoise == hare)
                return true;
        }
    }
}
