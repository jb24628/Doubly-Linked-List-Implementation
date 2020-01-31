package edu.gsu.cs1302.list;
/**
 * 
 * @author Jireh Bethely
 *
 * @param <T> The Generic data type for the node
 */
public class Node<T> {
	private Node<T> prev = null; //the node before this node
	private Node<T> next = null; //the node after this node
	private T element; //what is stored in this node
	/**
	 * Creates a new node.
	 */
	public Node() {
		element = null;
		next = null;
		
	}
	/**
	 * Creates a new Node given an element
	 * @param element The Data to add to the Node
	 */
	public Node(T element) {
		this.element = element;
	}
	/**
	 * Gives the next Node
	 * @return The next Node
	 */
	public Node<T> getNext() {
		return next; 
	}
	/**
	 * Gets the previous Node
	 * @return The previous node
	 */
	public Node<T> getPrev() {
		return prev;
	}
	/**
	 * Sets the next Node
	 * @param n The node to set its next node to
	 */
	public void setNext(Node<T> n) {
		next = n;
	}
	/**
	 * Sets the previous node
	 * @param n The node to set its previous node to
	 */
	public void setPrev(Node<T> n) {
		prev = n;
	}
	/**
	 * Sets the element
	 * @param elem The element to set to the node
	 */
	public void setElem(T elem) {
		element = elem;
	}
	/**
	 * Gets the element of the node
	 * @return The element of the node
	 */
	public T getElem() {
		return element;
	}
}