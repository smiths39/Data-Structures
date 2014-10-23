import org.junit.*;
import static org.junit.Assert.*;

public class ArrayList<T> {
	
	private T[] sequence = (T[])(new Object[10000]);
	private int size = 0;
	
	@Test
	public int size() {
		return size;
	}
	
	@Test
	public int indexOf(T item) {
		for (int index = 0; index < size; index++) {
			if (sequence[index].equals(item)) {
				return index;
			}
		}
		return -1;
	}
	
	@Test
	public boolean add(T item) {
		add(size, item);
		return true;
	}
	
	@Test
	public void addFirst(T item) {
		add(0, item);
	}
	
	@Test
	public void add(int index, T item) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == sequence.length) {
			T[] sequenceCopy = (T[])(new Object[sequence.length * 2]);
			System.arraycopy(sequence, 0, sequenceCopy, 0, sequence.length);
			sequence = sequenceCopy;
		}
		
		for (int i = size; i > index; i--) {
			sequence[i] = sequence[i - 1];
		}
		sequence[index] = item;
		size++;
	}
	
	@Test
	public T remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		T temp = sequence[index];
		
		for (int i = index; i <= size; i++) {
			sequence[i] = sequence[i + 1];
		}
		
		size--;
		return temp;
	}
	
	@Test
	public T get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		return sequence[index];
	}
	
	@Test
	public T set(int index, T item) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		T temp = sequence[index];
		sequence[index] = item;
		return temp;
	}
	
	public static void main(String [] args) {
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		// ArrayList size = 0
		assertEquals(arrayList.size(), 0);
		
		// Add 'Dog' to end
		assertTrue(arrayList.add("Dog"));

		// Add 'Cat' to start
		arrayList.addFirst("Cat");

		// ArrayList size = 2
		assertEquals(arrayList.size(), 2);

		// ArrayList set 'Dog' to 'Rat'
		assertEquals(arrayList.set(1, "Rat"), "Dog");
		
		// ArrayList get 'Rat	
		assertEquals(arrayList.get(1), "Rat");

		// Index of 'Cat'
		assertEquals(arrayList.indexOf("Cat"), 0);
		
		// ArrayList remove 'Cat'
		assertEquals(arrayList.remove(arrayList.indexOf("Cat")), "Cat");
		
		// ArrayList remove 'Rat'
		assertEquals(arrayList.remove(arrayList.indexOf("Rat")), "Rat");
		
		// ArrayList size = 0
		assertEquals(arrayList.size(), 0);
	}
}