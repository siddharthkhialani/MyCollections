import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class represents a HashSet.
 * 
 * @author siddharthkhialani
 *
 * @param <T>
 */
public class MyHashSet<T> extends AbstractSet<T> implements Set<T>, Cloneable,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Node[] elements;
	private int size;
	private double loadFactor;

	public MyHashSet() {
		this.size = 0;
		this.elements = new Node[16];
		this.loadFactor = 0.75;
	}

	public MyHashSet(int initialCapacity) {
		this.size = 0;
		this.elements = new Node[initialCapacity];
		this.loadFactor = 0.75;
	}

	public MyHashSet(int initialCapacity, int loadfactor) {
		this.size = 0;
		this.elements = new Node[initialCapacity];
		this.loadFactor = loadfactor;
	}

	/**
	 * This method adds the element to the HashSet
	 */
	public boolean add(T obj) {
		if(this.contains(obj)) {
			return false;
		}
		else {
			int hashcode;
			
			if(obj == null) {
				hashcode = 1;
			}
			else {
				hashcode = obj.hashCode() % elements.length;
			}
			
			Node head = elements[hashcode];
			Node temp = new Node(obj);
			
			if(head == null) {
				head = temp;
				head.setNext(head);
				head.setPrevious(head);
				elements[hashcode] = head; 
			}
			else {
				Node last = head.getPrevious();
				head.setPrevious(temp);
				last.setNext(temp);
				temp.setNext(head);
				temp.setPrevious(last);
			}
			
			return true;
		}
	}

	/**
	 * Clears the HashSet. Removes all the elements from the data structure.
	 */
	public void clear() {
		this.elements = null;
		this.size = 0;
	}

	/**
	 * This method adds the element to the HashSet
	 */
	public boolean contains(Object obj) {
		int hashcode;
		if (obj == null) {
			hashcode = 1;
		} else {
			hashcode = obj.hashCode() % elements.length;
		}

		Node head = elements[hashcode];

		if (head != null) {
			Node ptr1 = head.getNext();
			Node ptr2 = head.getPrevious();

			boolean flag = false;
			while (true) {
				if (ptr1.getElement() != obj) {
					ptr1 = ptr1.getNext();
					if(ptr1 == ptr2) {
						flag = true;
					}
				} else {
					return true;
				}

				if (ptr2.getElement() != obj) {
					ptr2 = ptr2.getPrevious();
					if(ptr1 == ptr2) {
						flag = true;
					}
				} else {
					return true;
				}
				
				
				if(flag) {
					return false;
				}
			}
		}

		return false;
	}

	public boolean remove(Object o) {
		int hashcode;
		if (o == null) {
			hashcode = 1;
		} else {
			hashcode = o.hashCode() % elements.length;
		}

		Node head = elements[hashcode];

		if (head != null) {
			Node ptr1 = head.getNext();
			Node ptr2 = head.getPrevious();

			boolean flag = false;
			while (true) {
				if (ptr1.getElement() != o) {
					ptr1 = ptr1.getNext();
					if(ptr1 == ptr2) {
						flag = true;
					}
				} else {
					removeNode(hashcode, ptr1);
					return true;
				}

				if (ptr2.getElement() != o) {
					ptr2 = ptr2.getPrevious();
					if(ptr1 == ptr2) {
						flag = true;
					}
				} else {
					removeNode(hashcode, ptr1);
					return true;
				}
				
				
				if(flag) {
					return false;
				}
			}
		}

		return false;
	}
	
	private void removeNode(int hashcode, Node current) {
		if(current.getNext() == current) {
			elements[hashcode] = null;
			return;
		}
		else if(current == elements[hashcode]) {
			elements[hashcode] = current.getNext();
		}
		Node previous = current.getPrevious();
		Node next = current.getNext();
		previous.setNext(next);
		next.setPrevious(previous);
	}
	
	
	public String toString() {
		String s = "";
		
		for(int index = 0; index < elements.length; index++) {
			if(elements[index] != null) {
				s += elements[index].getElement().toString() + " ";
			}
			
		}
		return s;
		
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
