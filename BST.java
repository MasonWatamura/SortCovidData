package p1cs232;

import java.util.NoSuchElementException;

/**
 * Creates and implements a binary search tree that will be used to take in a 
 * key and value and sorts the data by the key being new cases of covid
 * Sorts the data in the put method and then it has a method that will be 
 * called to print the outputs to an output file
 * 
 * @author Water
 * 
 * @throws NA
 * @param NA
 * @param <Key> the key for the binary search tree
 * @param <Value> the value for the binary search tree
 */

public class BST<Key extends Comparable<Key>, Value> {	
	private Node root;
	private class Node{   //setting up getting ready to use nodes
		private Key key;
		private Value val;
		private Node left, right;  //creates the nodes that will branch either to the left or right of the root node
		private int size;
		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}
	
	/**
	 * checks is the binary search tree is empty
	 * 
	 * @throws NA
	 * @param NA
	 * @return true if the binary search tree is empty, false otherwise
	 */
	public boolean isEmpty() {  
		return size() == 0;
	}
	
	/**
	 * returns the number of key/value pairs in the bst
	 * 
	 * @return the number of key/value pairs in the bst
	 * @throws NA
	 * @param NA (passes a Node to return the pairs rooted at x Node)
	 */
	public int size() {
		return size(root);
	}
	private int size(Node x) {     //gives the size of the binary search tree
		if(x == null) {
			return 0;
		}
		else {
			return x.size;
		}
	}
	
	/**
	 * returns the value of a given key in the bst
	 * 
	 * @param key the key
	 * @return the value of the given key if the key is in the bst and null if its not
	 * @throws IllegalArgumentException if the key is null
	 */
	public Value get(Key key) {  //all of the methods until the printing methods that don't have a node in the parenthesis
		//will then call on the methods that use the nodes using root as the node and key for the key
		return get(root, key);
	}
	private Value get(Node x, Key key) {
		if(key == null) {
			throw new IllegalArgumentException("calls get with a null key");
		}
		if(x == null) {
			return null;  //if there is nothing in the tree
		}
		int cmp = key.compareTo(x.key);  //uses recursion to find the key that is being looked for to return the value
		if(cmp<0) {
			return get(x.left,key);  //the left sides of the nodes
		}
		else if(cmp>0) {
			return get(x.right,key);  //the right sides of the nodes
		}
		else {
			return x.val;
		}
	}
	
	/**
	 * puts a specific key/value into the bst
	 * if the key is already there it would rewrite the value with the new value
	 * 
	 * @param key the key
	 * @param val the value
	 * @throws IllegalArgumentException is the key is null
	 * @return NA
	 */
	public void put(Key key, Value val) {
		if(val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);   //calls put() to put the key and value in as its node
	}
	private Node put(Node x, Key key, Value val) {
		if(key == null) {
			throw new IllegalArgumentException("calls put with a null key");
		}
		if(x == null) {
			return new Node(key, val, 1);  //inserts the first node in the tree
		}
		int cmp = key.compareTo(x.key);  //compares to method being used
		if(cmp<0){
			x.left = put(x.left, key, val);  //if the compare to is -1 puts the key and val to the left of the branch needed until it reaches a null branch
		}
		else if(cmp>0) {    
			x.right = put(x.right, key, val);    //if the compare to is 1 puts the key and val to the right of the branch needed until it reaches a null branch
		}
		x.size = 1+size(x.left) + size(x.right);//setting the new size of the tree
		return x;
	}
	
	/**
	 * deletes a specifid key and value in the bst
	 * 
	 * @param key the key
	 * @throws IllegalArgumentException if the key is null
	 * @return NA
	 */
	public void delete(Key key) {
		if(key == null) {
			throw new IllegalArgumentException("calls delete with a null key");
		}
		root = delete(root, key);
	}
	private Node delete(Node x, Key key) {
		if(x == null) {
			return null; //if nothing is in the tree
		}
		int cmp = key.compareTo(x.key);   //this will be much like both the get method in the sense that
		//it uses recursion to find the node much like the get method
		if(cmp<0) {
			x.left = delete(x.left, key);
		}
		else if(cmp>0) {
			x.right = delete(x.right,key);
		}
		else {    //this is primarily for if one side of the node does not have a node to go to the other side
			if(x.right == null) {
				return x.left;
			}
			if(x.left == null) {
				return x.right;
			}
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.size = size(x.left)+size(x.right)+1;
		return x;
	}
	
	/**
	 * removes the smallest key and value from the bst
	 * 
	 * @param NA
	 * @return NA
	 * @throws NoSuchElementException if the bst is empty
	 */
	public void deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException("Underflow");
		}
        root = deleteMin(root);
    }
    private Node deleteMin(Node x) {   //deletes the node with the minimum most key
        if (x.left == null) {  //uses recursion until there is no more left node
        	return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    /**
     * returns the smallest key in the bst
     * 
     * @return the smallest key in the bst
     * @throws NoSuchElementException if the bst is empty
     * @param NA
     */
    public Key min() {
    	if (isEmpty()) {
    		throw new NoSuchElementException("calls min with empty symbol table");
    	}
        return min(root).key;
    }
    private Node min(Node x) {  //finds the node with the minimum key
        if (x.left == null) {
        	return x;
        }
        else {
        	return min(x.left);
        }
    }
    
    /**
     * returns the height of the bst
     * 
     * @return the height of the bst
     * @param NA
     * @throws NA
     */
    public int height() {
        return height(root);
    }
    private int height(Node x) {  //returns how many rows that we have in the tree
        if (x == null) { 
        	return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }
    
    /**
     * prints from smallest to largest in an output file
     * 
     * @param x the root
     * @throws NA
     * @return NA
     */
    public void InOrder(FileOut fo) {
    	InOrder(fo, root);
    } 
    private void InOrder(FileOut fo, Node x) {  //uses recursion
    	if (x == null) {            //until this if statement is null when it stops
    	   return;
  	   }
   	   InOrder(fo, x.left);             //goes all the way to the left
   	   fo.writer("New cases: " + x.key + "" + x.val);  //prints the key and value to output file
   	   InOrder(fo, x.right);            //goes to the right once and repeats this process until it goes all the way to the right most node
    }
}
