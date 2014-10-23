import org.junit.*;
import static org.junit.Assert.*;

public class UnboundedStack<T> {
	
	private static class Node<T> {
		
		private T item;
		private Node<T> nextNode = null;
		
		Node(T item, Node<T> nextNode) {
			this.item = item;
			this.nextNode = nextNode;
		}
	}
	
	private Node<T> head = null;
	
	@Test
	public boolean isEmpty() {
		return head == null;
	}
	
	@Test
	public int size() {
		Node<T> tempItem = head;
		int count = 0;
		
		while (tempItem != null) {
			count++;
			tempItem = tempItem.nextNode;
		}
		return count;
	}
	
	@Test
	public boolean push(T item) {
		head = new Node<T>(item, head);
		return true;
	}
	
	@Test
	public T pop() {
		if (isEmpty()) {
			return null;
		}
		T tempItem = head.item;
		head = head.nextNode;
		return tempItem;
	}
	
	@Test(timeout = 100)
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		return head.item;
	}
	
	public static void main(String [] args) {
		
		UnboundedStack<String> stack = new UnboundedStack<String>();

		// Stack size = 0
		assertEquals(stack.size(), 0);
		
		// Top of stack = null
		assertNull(stack.peek());
		
		// Push 'Dog'
		assertTrue(stack.push("Dog"));

		// Stack empty = false
		assertFalse(stack.isEmpty());

		// Push 'Cat'
		assertTrue(stack.push("Cat"));
		
		// Stack size = 2
		assertEquals(stack.size(), 2);

		// Pop 'Cat'
		assertEquals(stack.pop(), "Cat");

		// Top of Stack = Dog
		assertEquals(stack.peek(), "Dog");
		assertNotNull(stack.peek());
		
		// Pop 'Dog'
		assertEquals(stack.pop(), "Dog");
		
		// Stack empty = true
		assertTrue(stack.isEmpty());
	}
}