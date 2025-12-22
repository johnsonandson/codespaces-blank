public class Main{
    public static void main (String[]args){
        BST bst=new BST();
        bst.insert(3);
        bst.insert(4);
        bst.insert(2);
        bst.insert(7);
        bst.insert(1);
        System.out.println(bst.toString());
        bst.printTree();
        bst.remove(1);
        bst.insert(9);
        bst.printTree();
        System.out.println(bst.toString());
    }
}