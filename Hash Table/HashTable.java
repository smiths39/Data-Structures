import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class HashTable {
	private static class Node {
		private Object key;
		private Object value;
		private Node next;
		
		Node(Object key, Object value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	private static Node[] table;
	private int size = 0;
	
	public HashTable(int initialSize) {
		table = new Node[initialSize];
	}
	
	@Test
	public int size() {
		return size;
	}
	
	@Test
	public void put(Object key, Object value) {
		int bucket = hash(key);
		Node temp = table[bucket];
		
		while (temp != null) {
			if (temp.key.equals(key)) {
				break;
			}
			temp = temp.next;
		}
		
		if (temp != null) {
			temp.value = value;
		} else {
			if (size >= 0.75 * table.length) {
				resize();
			}
			
			Node newNode = new Node(key, value, table[bucket]);
			table[bucket] = newNode;
			size++;			
		}
	}
	
	@Test
	public Object get(Object key) {
		int bucket = hash(key);
		Node temp = table[bucket];
		
		while (temp != null) {
			if (temp.key.equals(key)) {
				return temp.value;
			}
			temp = temp.next;
		}
		return temp;
	}
	
	@Test
	public void remove(Object key) {
		int bucket = hash(key);
		if (table[bucket] == null) {
			return;
		}
		if (table[bucket].key.equals(key)) {
			table[bucket] = table[bucket].next;
			size--;
			return;
		}
		
		Node previousNode = table[bucket];
		Node currentNode = previousNode.next;
		
		while (currentNode != null && !currentNode.key.equals(key)) {
			currentNode = currentNode.next;
			previousNode = currentNode;
		}
		
		if (currentNode != null) {
			previousNode.next = currentNode.next;
			size--;
		}
	}
	
	@Test
	public boolean containsKey(Object key) {
		int bucket = hash(key);
		Node temp = table[bucket];
		
		while (temp != null) {
			if (temp.key.equals(key)) {
				return true;
			}
			temp = temp.next;
		}
		return false;
	}
	
	private int hash(Object key) {
		return (Math.abs(key.hashCode())) % table.length;
	}
	
	private void resize() {
		Node [] newTable = new Node[table.length * 2];
		
		for (int index = 0; index < table.length; index++) {
			Node temp = table[index];
			
			while (temp != null) {
				Node nextTemp = temp.next;
				
				int hash = hash(temp.key);
				temp.next = newTable[hash];
				newTable[hash] = temp;
				temp = nextTemp;
			}
		}
		table = newTable;
	}
	
	public static void printHashTable() {
		System.out.println("============");
		System.out.println(" HASH TABLE ");
		System.out.println("============");
		
		for (int index = 0; index < table.length; index++) {
			System.out.print(index + ":");
			Node temp = table[index];
			
			while (temp != null) {
				System.out.print("(" + temp.key + ", " + temp.value + ")");
				temp = temp.next;
			}
			System.out.println();
		}
	}
	
	public static void main(String [] args) {
		
		HashTable hashTable = new HashTable(10);
		
		// HashTable put names 
		hashTable.put("John Doe", 7);
		hashTable.put("Jack Crews", 2);
		hashTable.put("Carl Lincoln", 5);
		
		// HashTable size = 3
		assertEquals(hashTable.size(), 3);
		 
		// HashTable get "Carl Lincoln"
		assertEquals(hashTable.get("Carl Lincoln"), 5); 
		
		// HashTable remove "Jack Crews"
		hashTable.remove("Jack Crews");
		
		// HashTable size = 2
		assertEquals(hashTable.size(), 2);
		
		// HashTable contains keys
		assertFalse(hashTable.containsKey("Jack Crews"));
		assertTrue(hashTable.containsKey("John Doe"));
		assertTrue(hashTable.containsKey("Carl Lincoln"));
		
		printHashTable();	
	}
}