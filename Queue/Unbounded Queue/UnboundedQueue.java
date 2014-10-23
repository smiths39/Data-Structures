import org.junit.*;
import static org.junit.Assert.*;

public class UnboundedQueue<T> {
	private static class Node<T> {
		private T item;
		private Node<T> next = null;
		
		Node (T item, Node<T> next) {
			this.item = item;
			this.next = next;
		}
	}
	
	private Node<T> head = null;
	private Node<T> tail = null;
	private int size = 0;
	
	@Test
	public boolean isEmpty() {
		return head == null;
	}
	
	@Test
	public int size() {
		return size;
	}
	
	@Test
	public boolean enqueue(T item) {
		Node<T> temp = new Node<T>(item, null);
		
		if (tail != null) {
			tail.next = temp;
		} else {
			head = temp;
		}
		tail = temp;
		size++;
		return true;
	}
	
	@Test
	public T dequeue() {
		if (isEmpty()) {
			return null;
		}
		T temp = head.item;
		head = head.next;
		size--;
		return temp;
	}
	
	@Test(timeout = 100)
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		return head.item;
	}
	
	public static void main(String [] args) {
		
		UnboundedQueue<String> queue = new UnboundedQueue<String>();
		
		// Queue size = 0
		assertEquals(queue.size(), 0);
		
		// Head of queue = null
		assertNull(queue.peek());
		
		// Enqueue 'Dog'
		assertTrue(queue.enqueue("Dog"));

		// Queue empty = false
		assertFalse(queue.isEmpty());

		// Enqueue 'Cat'
		assertTrue(queue.enqueue("Cat"));
		
		// Queue size = 2
		assertEquals(queue.size(), 2);

		// Dequeue 'Dog'
		assertEquals(queue.dequeue(), "Dog");

		// Head of Queue = Cat
		assertEquals(queue.peek(), "Cat");
		assertNotNull(queue.peek());
		
		// Dequeue 'Cat'
		assertEquals(queue.dequeue(), "Cat");
		
		// Queue empty = true
		assertTrue(queue.isEmpty());
	}
}