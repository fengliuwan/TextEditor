package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E> (null);
		tail = new LLNode<E> (null);
		head.next = tail;
		tail.prev = head;	
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException("NullPointerException");
		} else {
			LLNode<E> newNode = new LLNode<E>(element, tail.prev);
			size++;
			return true;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
		}
		LLNode<E> currLLNode = head;
		for(int i = 0; i < index + 1; i++) {
			currLLNode = currLLNode.next;
		}
		return currLLNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(element == null) {
			throw new NullPointerException("NullPointerException");
		}
		if(size == 0) {
			if(index == 0) {
				LLNode<E> newNode = new LLNode<E> (element, head);
				size++;
				return;
			} else {
				throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
			}
		}
		if(index < 0 || index > size - 1 || (size == 0 && index !=0) ) {
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
		}

		LLNode<E> prevLLNode = head;
		for(int i = 0; i < index; i++) {
			prevLLNode = prevLLNode.next;
		}
		LLNode<E> newNode = new LLNode<E> (element, prevLLNode);
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
		}
		LLNode<E> currNode = head;
		for(int i = 0; i < index +1; i++) {
			currNode = currNode.next;
		}
		currNode.prev.next = currNode.next;
		currNode.next.prev = currNode.prev;
		currNode.prev = null;
		currNode.next = null;
		size--;
		return currNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
		}
		if(element == null) {
			throw new NullPointerException("NullPointerException");
		}
		LLNode<E> currLLNode = head;
		for(int i = 0; i < index + 1; i++) {
			currLLNode = currLLNode.next;
		}
		E temp = currLLNode.data;
		currLLNode.data = element;
		return temp;
	}   
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("size: " + size);
		for(int i = 0; i < size; i ++) {
			output.append(" index: " + i + " data: " + get(i));
		}
		return output.toString();
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e, LLNode prevNode) {
		this(e);
		this.prev = prevNode;
		this.next = prevNode.next;
		prevNode.next.prev = this;
		prevNode.next = this;
	}
	
	public String toString() {
		return data.toString();
	}

}
