import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class BinarySearchTree<T extends Comparable<T>> {
	private static class Node<T> {
		private T item;
		private Node<T> leftChild;
		private Node<T> rightChild;
		
		Node(T item, Node<T> leftChild, Node<T> rightChild) {
			this.item = item;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
	}
	private Node<T> root;
	private int size = 0;
	
	public BinarySearchTree(T item) {
		root = new Node<T>(item, null, null);
	}
	
	@Test
	public int size() {
		return size;
	}
	
	@Test
	public boolean add(T item) {
		Node<T> node = new Node<T>(item, null, null);
		insertNode(root, node);
		size++;
		return true;
	}
	
	private Node<T> insertNode(Node<T> subTree, Node<T> newNode) {
		if (subTree == null) {
			subTree = newNode;
		} else if ((subTree.item).compareTo(newNode.item) < 0) {
			subTree.rightChild = insertNode(subTree.rightChild, newNode);
		} else if ((subTree.item).compareTo(newNode.item) > 0) {
			subTree.leftChild = insertNode(subTree.leftChild, newNode);
		} else {
			throw new IllegalArgumentException("Duplicate element: " + newNode.item);
		}
		
		return subTree;
	}
	
	@Test
	public boolean delete(T item) {
		deleteNode(root, item);
		size--;
		return true;
	}
	
	private Node<T> deleteNode(Node<T> subTree, T item) {
		if (subTree != null) {
			if ((subTree.item).compareTo(item) < 0) {
				subTree.rightChild = deleteNode(subTree.rightChild, item);
			} else if ((subTree.item).compareTo(item) > 0) {
				subTree.leftChild = deleteNode(subTree.leftChild, item);
			} else {
				if ((subTree.leftChild != null) && (subTree.rightChild != null)) {
					Node<T> temp = findLeftmostChild(subTree.rightChild);
					subTree.item = temp.item;
					subTree.rightChild = deleteNode(subTree.rightChild, temp.item);
				} else if (subTree.leftChild != null) {
					// Node only has 1 child (the left child)
					subTree = subTree.leftChild;
				} else {
					// Only right child remaining
					subTree = subTree.rightChild;
				}
			}
		} else {
			throw new IllegalArgumentException("No such element");
		}
		return subTree;
	}
	
	private Node<T> findLeftmostChild(Node<T> subTree) {
		while (subTree.leftChild != null) {
			subTree = subTree.leftChild;
		}
		return subTree;
	}
	
	@Test
	public Node<T> findNode(T item) {
		if (item == null) {
			return null;
		}
		
		Node<T> temp = root;
		
		while ((temp.item).compareTo(item) != 0) {
			if ((temp.item).compareTo(item) > 0) {
				temp = temp.leftChild;
			} else if ((temp.item).compareTo(item) < 0) {
				temp = temp.rightChild;
			}
			
			if (temp == null) {
				return null;
			}
		}
		return temp;
	}
	
	@Test
	public T findMinimum() {
		if (root == null) {
			return null;
		}
		Node<T> temp = root;
		
		while (temp.leftChild != null) {
			temp = temp.leftChild;
		}
		
		return temp.item;
	}
	
	@Test
	public T findMaximum() {
		if (root == null) {
			return null;
		}
		Node<T> temp = root;
		
		while (temp.rightChild != null) {
			temp = temp.rightChild;
		}
		
		return temp.item;
	}
	
	public List<T> breathFirstSearch() {
		List<T> list = new ArrayList<T>();
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		
		queue.add(root);
		
		while (!queue.isEmpty()) {
			Node<T> temp = queue.poll();
			list.add(temp.item);
			
			if (temp.leftChild != null) {
				queue.add(temp.leftChild);
			}
			if (temp.rightChild != null) {
				queue.add(temp.rightChild);
			}
		}
		return list;
	}
	
	public List<T> depthFirstSearch() {
		List<T> list = new ArrayList<T>();
		Stack<Node<T>> stack = new Stack<Node<T>>();
		
		stack.push(root);
		
		while (!stack.isEmpty()) {
			Node<T> temp = stack.pop();
			list.add(temp.item);
			
			if (temp.rightChild != null) {
				stack.push(temp.rightChild);
			}
			if (temp.leftChild != null) {
				stack.push(temp.leftChild);
			}
		}
		return list;
	}
	
	public void inOrderTraversal(Node<T> temp) {
		if (temp != null) {
			preorderTraversal(temp.leftChild);
			System.out.print(temp.item + " ");
			preorderTraversal(temp.rightChild);
		}
	}
	
	public void preorderTraversal(Node<T> temp) {
		if (temp != null) {
			System.out.print(temp.item + " ");
			preorderTraversal(temp.leftChild);
			preorderTraversal(temp.rightChild);
		}
	}
	
	public void postOrderTraversal(Node<T> temp) {
		if (temp != null) {
			preorderTraversal(temp.leftChild);
			preorderTraversal(temp.rightChild);
			System.out.print(temp.item + " ");
		}
	}
	
	public void printTree(List<T> list) {
		for (T item : list) {
			System.out.print(item + " ");
		}
	}
	
	public static void main(String [] args) {
		BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>(20);
		
		// Binary Search Tree size = 0
		assertEquals(binarySearchTree.size(), 0);
		
		// Binary Search Tree add items
		assertTrue(binarySearchTree.add(30));
		assertTrue(binarySearchTree.add(10));
		assertTrue(binarySearchTree.add(15));
		assertTrue(binarySearchTree.add(24));
		assertTrue(binarySearchTree.add(36));
		assertTrue(binarySearchTree.add(78));
		assertTrue(binarySearchTree.add(69));
		assertTrue(binarySearchTree.add(2));
		// Binary Search Tree delete 78
		assertTrue(binarySearchTree.delete(78));

		// Binary Search Tree get minimum element
		assertNotNull(binarySearchTree.findMinimum());

		// Binary Search Tree get maximum element
		assertNotNull(binarySearchTree.findMaximum());

		// Binary Search Tree find node 24
		assertNotNull(binarySearchTree.findNode(24));

		System.out.print("Preorder Traversal: ");
		binarySearchTree.preorderTraversal(binarySearchTree.root);

		System.out.print("\nInorder Traversal: ");
		binarySearchTree.inOrderTraversal(binarySearchTree.root);

		System.out.print("\nPostorder Traversal: ");
		binarySearchTree.postOrderTraversal(binarySearchTree.root);

		List<Integer> list = binarySearchTree.breathFirstSearch();
		System.out.print("\n\nBreath First Search: ");
		binarySearchTree.printTree(list);

		list = binarySearchTree.depthFirstSearch();
		System.out.print("\nDepth First Search: ");
		binarySearchTree.printTree(list);
		
		System.out.println();
	}
}