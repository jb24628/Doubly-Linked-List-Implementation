package edu.gsu.cs1302.list;

import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
/**
 * @author Jireh Bethely
 * @param <T> The generic type that the list will be
 */
public class SortedDblList<T> implements Comparable<T> {

	private int size; //stores the size of the list
	private Node<T> head = null; //the front Node in the list
	private Node<T> tail = null; //the last Node in the list

	/**
	 * This saves the elements of the Nodes of a SortedDblList to a file
	 * @param fileName the file name to save the elements as
	 */
	public void saveListObjects(String fileName) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));

			output.write(getSize()); //saves the number of elements that will be added to the file
			for (int i = 0; i < getSize(); i++) { //Loops through for each Node<T>
				output.writeObject((Person)nextNode(head,i).getElem()); //writes the element of the ith Node<T> to the file
			}
			output.close(); 
			System.out.println(getSize() + " objects were successfully writen to the file " + fileName + "."); //Shows the user that the file was successfuly created
		}
		catch (Exception e) {
			System.out.println("There was a problem creating the file.");
		}

	}
	/**
	 * Loads the elements from a given file
	 * @param fileName The name of the fiel to be read
	 */
	public void loadListObjects(String fileName) {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName)); //creates the file
			int tempSize = input.read(); //gets the number of elements in the file
			for (int i = 0; i<tempSize; i++) {
				add((T) input.readObject()); //adds the elements from the ile by casting the objects to the data type of the list
			}
			input.close();
			System.out.println("Succesfully read from the file.");
		}
		catch (EOFException f) {
			System.out.println("End of file reached.");
		}
		catch (Exception e) {
			System.out.println("There was a problem reading from the file.");
		}
	}
	/**
	 * Compares the element of the Node to an Object
	 * @param o A given Object
	 */
	public int compareTo(Object o) {
		T element = (T) o; //Casts the object to the generic's data type
		return compareTo(element); //returns the result of the compareTo() method defined by another class
	}
	/**
	 * Adds an element to the List of nodes
	 * @param element The element to be added
	 * @return returns true if the element was added. Returns false if the element could not be added.
	 */
	public boolean add(T element) {
		Node<T> n = new Node<T>(element);
		boolean result = true; //defines what is going to be returned

		if (head != null) {
			if (size==1) { // for if the size of the list is 1
				if (((Comparable) element).compareTo(head.getElem()) == 1) { //for if it's a lower id
					//The code below sets a new head and changes the next element to the old head
					Node<T> old = head;
					head = n;
					head.setNext(old); 
					head.setPrev(null);
					nextNode(head,1).setNext(null);
					nextNode(head,1).setPrev(head);
					tail = old;
					tail.setPrev(nextNode(head,1));
				}
				else if (((Comparable) element).compareTo(head.getElem()) == -1) { //for if it's a higher id
					//The code below sets the element after the head to what the user wants to add
					head.setNext(n);
					nextNode(head,1).setPrev(head);
					nextNode(head,1).setNext(null);
					tail = n;
					tail.setPrev(nextNode(head,1));
					tail.setNext(null);
				}
				else {
					result = false; //returns false because no node was added
					size--; //this corrects the size in the event that what is trying to be added is already in the list
				}
			}
			else { //for if the size is greater than 1			
				if (((Comparable) element).compareTo(tail.getElem()) == -1) { //adds the element to a node at the end of the list
					tail.setNext(n);
					tail = n;
					tail.setNext(null);
					tail.setPrev(nextNode(head,size-1));
				}

				else if ((((Comparable) element).compareTo(head.getElem()) == 1)) { //adds the element to a node before the end of the list
					head.setPrev(n);
					n.setNext(head);
					head = n;
					head.setPrev(null);

				}

				else if (((Comparable) element).compareTo(tail.getElem()) == 1){
					int x = 0;
					for (int i = 0; i < size - 1; i++) { //loops through the elements of the node
						if (((Comparable) element).compareTo(nextNode(head,i).getElem()) == -1 && ((Comparable) element).compareTo(nextNode(head,i+1).getElem()) == 1) { //tests if the element goes after the ith element and before the ith+1 element
							//inserts the element between the two proper nodes
							n.setNext(nextNode(head,i+1));
							nextNode(head,i).setNext(n);
							nextNode(head, i+1).setPrev(nextNode(head,i));
							nextNode(head,i+2).setPrev(nextNode(head,i+1));
							x++;
						}
					}
					if (x == 0) { //Checks if the if statement was ran through. If that if statement was not run through, it corrects the size
						size--;
					}
				}
				else {
					result = false; //returns false because no node was added
					size--; //corrects for the size
				}
			}
		}
		else { //ads a Node if there are none in the list
			head = n;
			head.setNext(null);
			head.setPrev(null);
			tail = n;
			tail.setNext(null);
			tail.setPrev(null);
		}
		size++; //changes the size of the list
		return true;
	}
	/**
	 * Returns the node that is a certain number of nodes after another node.
	 * @param n The referenced node which is typically the head.
	 * @param left The index to get. While the method is running recursively, it represents the indexes left.
	 * @return Returns the desired Node
	 */
	public Node<T> nextNode(Node<T> n, int left) {
		if (left == 0) { //the base case for this recursive method
			return n;
		}
		return nextNode(n.getNext(), left -1);
	}
	/**
	 * @param o The object to test the equality to
	 */
	public boolean equals(Object o) {
		boolean result = false;

		if (compareTo((T) o) == 0) { //tests if the objects are equal 
			result = true;
		}

		return result;
	}
	/**
	 * Removes a node given an assuming that it's there.
	 * @param o The object to be removed.
	 * @return Returns true if the object was removed. Returns false if the object was not therefore and could not be removed.
	 */
	public boolean remove(Object o) {

		int index = indexOf(o); //Uses indexOf to call compareTo to decide which Node should be removed
		if (index < 0) { //If the index is -1, it is not in the list. So it returns false.
			return false;
		}
		else if (index == 0) { //changes the head if the head is going to me removed
			head = nextNode(head, index+1);
			head.setPrev(null);
		}
		else if (index >= 1 && index < size-1) { //changes the indexth node of the list
			nextNode(head, index-1).setNext(nextNode(head,index+1));
			nextNode(head, index).setPrev(nextNode(head,index-1));
		}
		else { //changes the tail if the tail is going to be removed
			tail = nextNode(head,index-1);
			tail.setNext(null);
		}
		return true;
	}
	/**
	 * Returns the element in the index-th node
	 * @param index index of the list to get
	 * @return The element of the list of nodes
	 */
	public T get(int index) {
		if (index < 0 || index >= size) {
			return null;
		}
		
		Node<T> temp = head;
		for (int i = 0; i < index; i++) { //loops until it gets to the desired index
			temp = temp.getNext();
		}
		return temp.getElem();
	}
	/**
	 * Returns if the list is empty or not.
	 * @return True if the list is empty. False if the list is not empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * Prints the entire list of nodes.
	 */
	public void printList() {
		Node<T> temp = head;
		while (temp != null) { //loops until a null element is reahed. AKA the end of the list
			T t = temp.getElem();
			System.out.println(t.toString());
			temp = temp.getNext();
		}
	}
	/**
	 * Returns the index of an Object
	 * @param o The object to find the idnex of.
	 * @return Returns -1 if the Object is not in the list. It returns the index of where an Object is in a list.
	 */
	public int indexOf(Object o) {
		T t = (T) o;
		Node<T> temp = head;
		int index = -1;

		while (temp != null) { //loops until the end of the list
			T t1 = temp.getElem();
			temp = temp.getNext();

			if (((Comparable) t1).compareTo(t) == 0) {
				temp = null; //ends the loop if the index is found
			}
			index++;
		}
		return index;
	}
	/**
	 * Returns a list of all the unique nodes in the two lists.
	 * @param otherList The second list to union with the list this method is called on.
	 * @return The combined list.
	 */
	public SortedDblList<T> union(SortedDblList<? extends T> otherList) {
		SortedDblList<T> s = new SortedDblList<T>();

		for (int i = 0; i<getSize();i++) { //adds all of the elements of the first list to the temporary list
			s.add(nextNode(head,i).getElem());
		}
		for (int i = 0; i<((SortedDblList<T>) otherList).getSize();i++) { //adds all of the elements of the second list to temporary list
			s.add(nextNode(((Node<T>) otherList.head),i).getElem());
		}
		return s; //returns the temporary. There are no dupilicate nodes because the add method does not add duplicate items.
	}
	/**
	 * Returns a list of the nodes that are in the list that the method is called on that are not in the second list.
	 * @param otherList The list to compare the first list to.
	 * @return A list of nodes that are in the first list and not in the seocnd list.
	 */
	public SortedDblList<T> intersection(SortedDblList<? extends T> otherList) {
		SortedDblList<T> combined = new SortedDblList<T>();
		for (int i = 0; i < getSize(); i++) { //loops through the first list
			for (int j = 0; j<otherList.getSize(); j++) { //loops through the second list
				if (((Comparable) nextNode(head,i).getElem()).compareTo(((Comparable) nextNode((Node<T>)otherList.head,j).getElem())) == 0) { //compares the ith node of the 1st list and the jth node of the 2nd to see if they are equal
					combined.add(nextNode(head,i).getElem());
				}
			}
		}
		return combined;
	}
	/**
	 * Returns the size of the list.
	 * @return Returns the size of the list.
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Returns the head of the list
	 * @return The head of the list
	 */
	public Node<T> getHead() {
		return head;
	}
	/**
	 * Returns the tail of the list
	 * @return The tail of the list
	 */ 
	public Node<T> getTail() {
		return tail;
	}
	/**
	 * Sets the head of the list
	 * @param n The node to set as the head
	 */
	public void setHead(Node<T> n) {
		head = n;
	}
	/**
	 * Sets the tail of the list
	 * @param n The node to set the tail as
	 */
	public void setTail(Node<T> n) {
		tail = n;
	}
}