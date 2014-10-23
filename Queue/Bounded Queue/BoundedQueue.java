import org.junit.*;
import static org.junit.Assert.*;

public class BoundedQueue<T> {
	
	private T[] sequence = (T[])(new Object[10000]);
	private int size = 0;
	private int head = 0;
	private int tail = 0;

	@Test
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Test
	public int size() {
		return size;
	}
	
	@Test
	public boolean enqueue(T item) {
		if (size >= sequence.length) {
			T[] sequenceCopy = (T[])(new Object[sequence.length * 2]);
			System.arraycopy(sequence, 0, sequenceCopy, 0, sequence.length);
			sequence = sequenceCopy;
		}
		sequence[tail] = item;
		tail = (tail + 1) % sequence.length;
		size++;
		return true;
	}
	
	@Test
	public T dequeue() {
		if (isEmpty()) {
			return null;
		}
		T temp = sequence[head];
		head = (head + 1) % sequence.length;
		size--;
		return temp;
	}
	
	@Test(timeout = 100)
	public T peek() {
		if (isEmpty()) {
			return null;
		}
		return sequence[head];
	}
	
	public static void main(String [] args) {
		
		BoundedQueue<String> queue = new BoundedQueue<String>();
		
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