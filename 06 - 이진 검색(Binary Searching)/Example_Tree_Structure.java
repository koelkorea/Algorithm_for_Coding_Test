package code06.Binary_Searching;

import java.util.Scanner;

// tree구조 구현
class Node{
	
	int data;
	int left_node;
	int right_node;
	
    public Node(int data, int left_node, int right_node) {
		super();
		this.data = data;
		this.left_node = left_node;
		this.right_node = right_node;
	}
    
}
     
public class Example_Tree_Structure {

	static int n;
	
	static Node tree[] = new Node[100];
	
	// 전위 순회(Preorder Traversal)
	static void pre_order(Node node) {
		
	    System.out.print(node.data + " ");
	    
	    if(node.left_node != 0) {
	    	
	    	 pre_order(tree[node.left_node]);
	    	 
	    }
	       
	    if(node.right_node != 0) {
	    	
	    	 pre_order(tree[node.right_node]);
	    	 
	    }
	       
	}   
	
	// 중위 순회(Inorder Traversal)
	static void in_order(Node node) {
	    
	    if(node.left_node != 0) {
	    	
	    	in_order(tree[node.left_node]);
	    	 
	    }
	    
	    System.out.print(node.data + " ");
	       
	    if(node.right_node != 0) {
	    	
	    	in_order(tree[node.right_node]);
	    	 
	    }
	       
	}   
	
	// 후위 순회(Postorder Traversal)
	static void post_order(Node node) {

	    if(node.left_node != 0) {
	    	
	    	post_order(tree[node.left_node]);
	    	 
	    }
	       
	    if(node.right_node != 0) {
	    	
	    	post_order(tree[node.right_node]);
	    	 
	    }
	    
	    System.out.print(node.data + " ");
	       
	}   
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		n = scan.nextInt();

		for(int i = 0; i < n; i++) {
			
			int data = scan.nextInt();
			int left_node = scan.nextInt();
			int right_node = scan.nextInt();
			
			
		    if(left_node == 0) {
		    	
		    	 left_node = 0;
		    	 
		    }
		       
		    
		    if(right_node == 0) {
		    	
		    	right_node = 0;
		    	 
		    }

		    tree[data] = new Node(data, left_node, right_node);
		}    

		pre_order(tree[1]);
		
		System.out.println();
		
		in_order(tree[1]);
		
		System.out.println();
		
		post_order(tree[1]);

		scan.close();

	}

}


//'''

//[예시 입력]

//7

//A B C
//B D E
//C F G
//D None None
//E None None
//F None None
//G None None

//[예시 출력]

//A B D E C F G 
//D B E A F C G 
//D E B F G C A 
//'''
