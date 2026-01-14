//12/25
//John Speer
//Creates Binary Search Tree and methods for it
import java.util.ArrayList;

public class BST {
    Node root;
    public BST()
    {
         root = null;
    }

   
    //pre condition: key is not an integer already in the BST
    //post condition: puts the key into the BST in a correct position
    void insert(int key){
        ArrayList<Node> path=new ArrayList<>();
		if (root==null){
            root=new Node(key);
            return;
        }
        Node curr=root;
        boolean ye=true;
        while(ye){
            path.add(curr);
            if(key<curr.key){
                if(curr.left==null){
                    curr.left=new Node(key);
                    ye=false;
                }
                curr=curr.left;
            }else if(key>curr.key){
                    if(curr.right==null){
                        curr.right=new Node(key);
                        ye=false;
                    }
                    curr=curr.right;
            }
        }
        Node prev=null;
        for(int i=0;i<path.size();i++){
            Node look=path.get(i);
            if (balance(look)==-2&&balance(look.right)==1){
                fixCrookedRight(look,prev);
            }
            else if (balance(look)==2&&balance(look.left)==-1){
                fixCrookedLeft(look,prev);
            }
            else if(1<balance(path.get(i))){
                rotateRight(look,prev);
            }
            else if(balance(path.get(i))<-1){
                rotateLeft(look,prev);
            }
            prev=look;
        }
    }
    //pre condition: key is an integer
    //post condition: returns true if the key is in one of the nodes in the BST
    public boolean search(int key){
        return search(key, root);
    }
    private boolean search(int key, Node n){
        
        if (n==null){
            return false;
        }
        if (n.key==key){
            return true;
        }
        if (key<n.key){
            return search(key, n.left);
        }
        else{
            return search(key,n.right);
        }
        
    }
    //pre condition: key is an integer
    //post condition: returns the key if the key was removed, and removes the key from the BST while attaching it's personal root to one of its children. otherwise, returns -1
    int remove(int key){
        if (search(key)==false){
            return -1;
        }
        ArrayList<Node> path=new ArrayList<Node>();
        path.add(root);
        Node curr=root;
        Node parent=null;
        boolean work=true;
        while(work){
            if (curr.key==key){
                if(curr.left!=null&&curr.right!=null){
                    Node replaceUp = curr;
                    Node replace = curr.right;
                    while (replace.left != null) {
                        replaceUp = replace;
                        replace = replace.left;
                    }
                    curr.key = replace.key;
                    if (replaceUp.right.equals(replace)){
                        replaceUp.right=null;
                    }
                    
                    work=false;
                }
                else if(curr.left!=null){
                    if(curr.equals(parent.left)){
                        parent.left=curr.left;
                    }
                    else if(curr.equals(parent.right)){
                        parent.right=curr.left;
                    }
                    work=false;
                }
                else if(curr.right!=null){
                    if(curr.equals(parent.left)){
                        parent.left=curr.right;
                    }
                    else if(curr.equals(parent.right)){
                        parent.right=curr.right;
                    }
                    work=false;
                }
                else{
                    if(curr.equals(parent.left)){
                        parent.left=null;
                    }
                    else if(curr.equals(parent.right)){
                        parent.right=null;
                    }
                    work=false;
                }
            }
            else if(key<curr.key){
                path.add(parent);
                parent=curr;
                curr=curr.left;
            }else if(key>curr.key){
                path.add(parent);
                parent=curr;
                curr=curr.right;
            }
        }
        for(int i=path.size()-1;i>=0;i--){
            Node prev=null;
            Node look=path.get(i);
            if(i>0){
                prev=path.get(i-1);
            }
            if (balance(look)==-2&&balance(look.right)==1){
                fixCrookedRight(look,prev);
            }
            else if (balance(look)==2&&balance(look.left)==-1){
                fixCrookedLeft(look,prev);
            }
            else if(1<balance(path.get(i))){
                rotateRight(look,prev);
            }
            else if(balance(path.get(i))<-1){
                rotateLeft(look,prev);
            }
        }
        
        if(balance(root)<-1){
            rotateLeft(root,null);

        }
        else if(balance(root)>1){
            rotateRight(root,null);
        }
        
        return key;
    }

    //pre condition: BST has at least one node with a integer key
    //post condition: returns a String of the BST with the keys in levels
    public String toString(){
        ArrayList<ArrayList<Node>> nums=new ArrayList<>();
        nums=toString(root,0,nums);
        String str="";
        //traverse depth top to bottom left to right and print each arraylist on a separate line.
        for (int i=0;i<nums.size();i++){
            for(int x=0;x<nums.get(i).size();x++){
                str=str+nums.get(i).get(x).key+", ";
            }
            str+="\n";
        }
        return str;
    }
    //pre condition: the tree is initialized, nums is initialized
    //post condition: returns an arraylist of arraylists of the tree nodes
    private ArrayList<ArrayList<Node>> toString(Node n,int depth, ArrayList<ArrayList<Node>> nums){
        if (n==null){
            return nums;
        }
        toString(n.left,depth+1,nums);

        while (nums.size() <= depth) {
            nums.add(new ArrayList<Node>());
        }

        nums.get(depth).add(n);

        //right subtree
        toString(n.right, depth + 1, nums);

        return nums;
    }
    //pre condition: bst is initialized
    //post condition: returns true if the bst is balanced on every node, false if not
    public boolean isBSTOrNot() {
        return isBSTOrNot(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    //pre condition: bst is initialized
    //post condition: returns true if the bst is balanced, false if not.
    private boolean isBSTOrNot(Node root, int minValue, int maxValue) {
        // check for root is not null or not
        if (root == null) {
            return true;
        }
        // check for current node value with left node value and right node value and recursively check for left sub tree and right sub tree
        if(root.key >= minValue && root.key <= maxValue && isBSTOrNot(root.left, minValue, root.key) && isBSTOrNot(root.right, root.key, maxValue)){
            return true;
        }
        return false;
    }

 

   // please use the following pieces of code to display your tree in a more easy to follow style (Note* you'll need to place the Trunk class in it's own file)
    //pre condition: bst is initialized
    //post condition: prints
    public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }
    //pre condition: bst is initialized
    //post condition: calls the printTree function to print a tree of the bst
    public void printTree(){
        printTree(root, null, false);
    }
    //pre condition: bst is initialized
    //post condition: prints a tree with all of the bst nodes connecting to each child
    private void printTree(Node root, Trunk prev, boolean isLeft)
    {
        if (root == null) {
            return;
        }
 
        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);
 
        printTree(root.right, trunk, true);
 
        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }
 
        showTrunks(trunk);
        System.out.println(" " + root.key);
 
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";
 
        printTree(root.left, trunk, false);

    }
    //pre condition: node is initialized
    //post condition: rotates left child left, then rotates node right.
    private void fixCrookedLeft(Node node, Node prev){
        rotateLeft(node.left,node);
        rotateRight(node,prev);
    }
    //pre condition: node is initialized
    //post condition: rotates right child right, then rotates node left.
    private void fixCrookedRight(Node node, Node prev){
        rotateRight(node.right,node);
        rotateLeft(node,prev);
    }
    // rotates the tree such that the subRoot is replaced with it's right child with subRoot becoming the left child of the new subRoot. prev now points to the new subRoot.
    //pre condition: subRoot is a non null node
    //post condition: rotates subRoot to the left, where it is replaced by its right child and becomes that child's left child, and that child's left child becomes subRoot's right child
    private void rotateLeft(Node subRoot, Node prev){
        if(prev==null){
            root = subRoot.right;
        }
        else if (prev.right==subRoot){
            prev.right=subRoot.right;
        }
        else{
            prev.left=subRoot.right;
        }
        Node temp=subRoot;
        subRoot=subRoot.right;
        temp.right=subRoot.left;
        subRoot.left=temp;
        
    }

 

    // rotates the tree such that the subRoot is replaced with it's left child with subRoot becoming the right child of the new subRoot. prev now points to the new subRoot.
    //pre condition: subRoot is a non null node
    //post condition: rotates subRoot to the right, where it is replaced by its left child and becomes that child's right child, and child's right child becomes subRoot's right child
    private void rotateRight(Node subRoot, Node prev){
        if(prev==null){
            root = subRoot.left;
        }
        else if (prev.right.equals(subRoot)){
            prev.right=subRoot.left;
        }
        else{
            prev.left=subRoot.left;
        }
        Node temp=subRoot;
        subRoot=subRoot.left;
        temp.left=subRoot.right;
        subRoot.right=temp;
    }
    
    // returns the height of the node 
    //precondition: the node is in the current BST and there is at least one node in the BST
    //post condition: returns the height of the node, which is the amount of edges from the node to the farthest leaf
    private int height(Node node){
        if(node==null){
            return -1;
        }
            return 1+Math.max(height(node.right),height(node.left));
       
    }

    //returns the balance at the specified node
    //pre condition: node is a node in the BST
    //post condition: returns the height of the left child minus the height of the right child
    private int balance(Node node){
        if(node==null){
            return 0;
        }
        return height(node.left)-height(node.right);
    }
}
