import org.junit.*;
import static org.junit.Assert.*;

public class BoundedStack<T> {
	
	private T[] sequence = (T[])(new Object[10000]);
	private int size = 0;
	
	@Test
	public int size() {
		return size;
	}
	
	@Test
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Test
	public boolean push(T item) {
		if (size >= sequence.length) {
			T[] sequenceCopy = (T[])(new Object[sequence.length * 2]);
			System.arraycopy(sequence, 0, sequenceCopy, 0, sequence.length);
			sequence = sequenceCopy;
		}
		sequence[size] = item;
		size++;
		return true;
	} 
	
	@Test
	public T pop() {
		if (isEmpty()) {
			return null;
		} 
		T tempItem = sequence[size - 1];
		size--;
		return tempItem;
	}
	
	@Test(timeout = 100)
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		return sequence[size - 1];		
	}
	
	public static void main(String [] args) {
		
		BoundedStack<String> stack = new BoundedStack<String>();

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
	