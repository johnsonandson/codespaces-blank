//12/25
//John Speer
//Tests BST functions
public class Main{
    public static void main (String[]args){
        BST bst=new BST();
        bst.insert(3);
        bst.insert(4);
        bst.insert(2);
        bst.insert(7);
        bst.insert(8);
        bst.insert(9);
        bst.printTree();
        bst.remove(2);
        bst.printTree();
        bst.remove(3);
        bst.printTree();
        bst.remove(8);
        bst.printTree();
        if(bst.search(3)) System.out.println("true"); else {System.out.println("false");}
        bst.insert(1);
        System.out.println(bst.toString());
        bst.printTree();
        bst.remove(1);
        bst.printTree();
        System.out.println(bst.toString());
        bst.remove(4);
        bst.printTree();
        System.out.println(bst.toString());
        bst.remove(3);
        bst.printTree();
        System.out.println(bst.toString());
        if (bst.isBSTOrNot()){
            System.out.println("Is a bst!");
        }        
    }
}