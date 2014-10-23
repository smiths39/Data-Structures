import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class LinkedList<T> {
	
	private static class Node<T> {
		private T item;
		private Node<T> next = null;
		
		Node(T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}
	
	private Node<T> head = null;
	private Node<T> tail = null;
	private int size = 0;

	@Test
	public int size() {
		return size;
	}

	@Test
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node <T> temp = head;
		int tempIndex = 0;
		
		while (tempIndex != index) {
			temp = temp.next;
			tempIndex++;
		}
		
		return temp.item;
	}

	@Test
	public T set(int index, T item) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<T> temp = head;
		int tempIndex = 0;
		
		while (tempIndex != index) {
			temp = temp.next;
			tempIndex++;
		}
		
		T tempItem = temp.item;
		temp.item = item;
		return tempItem;
	}

	@Test
	public boolean add(T item) {
		tail = new Node<T>(item, tail);
		add(size, tail.item);
		return true;
	}

	@Test
	public boolean addFirst(T item) {
		head = new Node<T>(item, head);
		add(0, head.item);
		return true;
	}

	@Test
	public void add(int index, T item) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			if (size != 0) {
				head = head.next;
			}
			head = new Node<T>(item, head);
	
			if (tail == null) {
				tail = head;
			}	
		} else {
			Node<T> temp = head;
			int tempIndex = 0;
			
			while (tempIndex != index - 1) {
				temp = temp.next;
				tempIndex++;
			}
			
			temp.next = new Node<T>(item, temp.next);
			
			if (tail == temp) {
				tail = tail.next;
			}
		}
		size++;
	}

	@Test
	public int indexOf(T item) {
		if (tail == null) {
			return -1;
		} else {
			Node<T> temp = head;
			int tempIndex = 0;
			
			while (temp.item != item) {
				temp = temp.next;
				tempIndex++;
			}
			
			return tempIndex;
		}
	}

	@Test
	public T remove(int index) {
		if (size == 0) {
			throw new NoSuchElementException();
		} 
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node<T> temp = head;
		int tempIndex = 0;
		
		if (index != 0) {
	
			while (tempIndex != index - 1) {
				temp = temp.next;
				tempIndex++;
			}
			T tempItem = temp.next.item;
			temp.next = temp.next.next; 
		} else {
			T tempItem = temp.item; 
		}
		size--;
		return tempItem;		
	}

	@Test
	public T removeLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		
		T tempItem = tail.item;

		Node<T> temp = head;
		int tempIndex = 0;
		
		while (tempIndex != size - 1) {
			temp = temp.next;
			tempIndex++;
		}
		
		tail = temp;
		size--;
		return tempItem;
	}

	@Test
	public static void main(String [] args) {
		
		LinkedList<String> linkedList = new LinkedList<String>();
			
		// LinkedList size = 0
		assertEquals(linkedList.size(), 0);
		
		// Add 'Dog' to end
		assertTrue(linkedList.add("Dog"));

		// Add 'Cat' to start
		assertTrue(linkedList.addFirst("Cat"));
		
		// Add 'Bat' to position 1
		linkedList.add(1, "Bat");

		// LinkedList size = 3
		assertEquals(linkedList.size(), 3);

		// LinkedList set 'Bat' to 'Rat'
		assertEquals(linkedList.set(1, "Rat"), "Bat");
		
		// LinkedList get 'Rat'	
		assertEquals(linkedList.get(1), "Rat");

		// Index of 'Dog'
		assertEquals(linkedList.indexOf("Dog"), 2);

		// LinkedList remove 'Dog'
		assertEquals(linkedList.removeLast(), "Dog");

		// LinkedList remove 'Rat' and 'Cat'
		assertEquals(linkedList.remove(linkedList.indexOf("Rat")), "Rat");
		assertEquals(linkedList.remove(linkedList.indexOf("Cat")), "Cat");
		
		// LinkedList size = 0
		assertEquals(linkedList.size(), 0);
	}
}