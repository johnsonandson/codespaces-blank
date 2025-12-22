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
        while(true){
            path.add(curr);
            if(key<curr.key){
                if(curr.left==null){
                    curr.left=new Node(key);
                    return;
                }
                curr=curr.left;
            }else if(key>curr.key){
                    if(curr.right==null){
                        curr.right=new Node(key);
                        return;
                    }
                    curr=curr.right;
            }
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
    //post condition: returns true if the key was removed, and removes the key from the BST while attaching it's personal root to one of its children
    boolean remove(int key){
        if (search(key)==false){
            return false;
        }

        Node curr=root;
        Node parent=null;
        while(true){
            if (curr.key==key){
                if(curr.left!=null&&curr.right!=null){
                    Node replaceUp = curr;
                    Node replace = curr.right;
                    while (replace.left != null) {
                        replaceUp = replace;
                        replace = replace.left;
                    }
                    curr.key = replace.key;
                    replaceUp.left = null;

                    return true;
                }
                else if(curr.left!=null){
                    if(curr.equals(parent.left)){
                        parent.left=curr.left;
                    }
                    else if(curr.equals(parent.right)){
                        parent.right=curr.left;
                    }
                    return true;
                }
                else if(curr.right!=null){
                    if(curr.equals(parent.left)){
                        parent.left=curr.right;
                    }
                    else if(curr.equals(parent.right)){
                        parent.right=curr.right;
                    }
                    return true;
                }
                else{
                    if(curr.equals(parent.left)){
                        parent.left=null;
                    }
                    else if(curr.equals(parent.right)){
                        parent.right=null;
                    }
                    return true;
                }
            }
            else if(key<curr.key){
                parent=curr;
                curr=curr.left;
            }else if(key>curr.key){
                parent=curr;
                curr=curr.right;
            }
        }
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
    public boolean isBSTOrNot() {
        return isBSTOrNot(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

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
    public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }
 
    public void printTree(){
        printTree(root, null, false);
    }

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


    // rotates the tree such that the subRoot is replaced with it's right child with subRoot becoming the left child of the new subRoot. prev now points to the new subRoot.

    private void rotateLeft(Node subRoot, Node prev){

    }

 

    // rotates the tree such that the subRoot is replaced with it's left child with subRoot becoming the right child of the new subRoot. prev now points to the new subRoot.

    private void rotateRight(Node subRoot, Node prev){

    }
    
    // returns the height of the node 
    //precondition: the node is in the current BST and there is at least one node in the BST
    //post condition: returns the height of the node, which is the amount of edges from the 
    private int height(Node node){
        if(node.left==null&&node.right==null){
            return 0;
        }
        if(node.left!=null&&node.right!=null){
            return 1+Math.max(height(node.right),height(node.left));
        }
        if (node.left!=null){
            return 1+height(node.left);
        }
        else{
            return 1+height(node.right);
        }
       
    }

    //returns the balance at the specified node

    private int balance(Node node){
        
        if(node.left==null&&node.right==null){
            return 0;
        }
        if(node.left!=null&&node.right!=null){
            return 
        }
        if (node.left!=null){
        }
        else{
        }
    }
}
