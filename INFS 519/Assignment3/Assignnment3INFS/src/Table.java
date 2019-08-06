import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** This class implements an AVL tree of key/value pairs **/
public class Table {
	/** Root node in the tree */
	private Node root;

	/**
	 * Inserts a new Node into the table. If the provided key already
	 * exists, no entry will be created. Otherwise, the new entry is
	 * added to the table.
	 * @param key
	 * @param value
	 * @return True if the new node was inserted successfully.
	 * False if the provided key already exists in the table.
	 */
	public boolean insert(String key, String value) {
		Node newRoot = insertNode(root, key, value);
		if (newRoot.equals(root)) {
			return false;
		}
		this.root = newRoot;
		return true;
	}

	/**
	 *  Inserts a new node into an AVL tree.
	 * Note that if the new node key is not unique, the
	 * new node will not be added.
	 * @param node
	 * @param key
	 * @param value
	 * @return node
	 */
	private Node insertNode(Node node, String key, String value) {

		if (node == null) {
			return new Node(key, value, 0, 1);
		}
		if (0 < node.getKey().compareToIgnoreCase(key)) {
			node.setLchild(insertNode(node.getLchild(), key, value));
			if (findHeight(node.getLchild()) - findHeight(node.getRchild()) == 2)
				if (0 < node.getLchild().getKey().compareToIgnoreCase(key))
					node = rotateRight(node);
				else
					node = rotateLeftThenRight(node);
		} else if (0 > node.getKey().compareToIgnoreCase(key)) {
			node.setRchild(insertNode(node.getRchild(), key, value));

			if (findHeight(node.getLchild()) - findHeight(node.getRchild()) == -2)
				if (0 > node.getRchild().getKey().compareToIgnoreCase(key))
					node = rotateLeft(node);
				else
					node = rotateRightThenLeft(node);
		} else {
			return node;
		}
		node.setHeight(1 + Math.max(findHeight(node.getLchild()), findHeight(node.getRchild())));
		node.setSize(findSize(node.getLchild()) + findSize(node.getRchild()) + 1);
		node.setBalanceFactor(findHeight(node.getLchild()) - findHeight(node.getRchild()));
		return node;
	}

	/**
	 * This will return the size of the node and if the node is null it will return its size as 0.
	 * @param node
	 * @return integer
	 */
	private int findSize(Node node) {
		if (node == null) {
			return 0;
		}
		return node.getSize();
	}

	/**
	 * Re-balances an AVL tree at the provided node.
	 * Note that the height and balance factor of the node
	 * will also be updated.
	 * @param n - Root of the (sub)tree to balance
	 * @return The root of the newly balanced (sub)tree
	 */
	private Node rebalance(Node node) {
		if (node == null) {
			return node;
		}
		rebalance(node.getLchild());
		if (findHeight(node.getLchild()) - findHeight(node.getRchild()) == 2) {
			if (node.getLchild() != null) {
				if (node.getLchild().getBalanceFactor() == -1) {
					node = rotateLeftThenRight(node);
				} else {
					node = rotateRight(node);
				}
			}
		}
		rebalance(node.getRchild());
		if (findHeight(node.getLchild()) - findHeight(node.getRchild()) == -2) {
			if (node.getRchild() != null) {
				if (node.getRchild().getBalanceFactor() == 1) {
					node = rotateRightThenLeft(node);
				} else {
					node = rotateLeft(node);
				}
			}
		}
		node.setHeight(1 + Math.max(findHeight(node.getLchild()), findHeight(node.getRchild())));
		node.setSize(findSize(node.getLchild()) + findSize(node.getRchild()) + 1);
		node.setBalanceFactor(findHeight(node.getLchild()) - findHeight(node.getRchild()));
		return node;
	}
	/**
	* Performs a left-right double rotation for an
	* unbalanced AVL tree.
	* @param root The root of the unbalanced tree
	* @return The root of the newly balanced tree
	*/
	private Node rotateLeftThenRight(Node node) {
		node.setLchild(rotateLeft(node.getLchild()));
		return rotateRight(node);
	}
	/**
	* Performs a right-left double rotation for an
	* unbalanced AVL tree.
	* @param root - The root of the unbalanced tree
	* @return The root of the newly balanced tree
	*/
	private Node rotateRightThenLeft(Node node) {
		node.setRchild(rotateRight(node.getRchild()));
		return rotateLeft(node);
	}

	/**
	* Performs a left-left single rotation for an
	* unbalanced AVL tree.
	* @param root - The root of the unbalanced tree
	* @return The root of the newly balanced tree
	*/
	private Node rotateLeft(Node node) {
		Node rotate = node.getRchild();
		node.setRchild(rotate.getLchild());
		rotate.setLchild(node);
		node.setHeight(Math.max(findHeight(node.getLchild()), findHeight(node.getRchild())) + 1);
		rotate.setHeight(Math.max(findHeight(rotate.getLchild()), findHeight(rotate.getRchild())) + 1);
		node.setSize(findSize(node.getLchild()) + findSize(node.getRchild()) + 1);
		rotate.setSize(findSize(rotate.getLchild()) + findSize(rotate.getRchild()) + 1);
		node.setBalanceFactor(findHeight(node.getLchild()) - findHeight(node.getRchild()));
		rotate.setBalanceFactor(findHeight(rotate.getLchild()) - findHeight(rotate.getRchild()));
		return rotate;
	}

	/**
	* Performs a right-right single rotation for an
	* unbalanced AVL tree.
	* @param root - The root of the unbalanced tree
	* @return The root of the newly balanced tree
	*/
	private Node rotateRight(Node node) {
		Node rotate = node.getLchild();
		node.setLchild(rotate.getRchild());
		rotate.setRchild(node);
		node.setHeight(Math.max(findHeight(node.getLchild()), findHeight(node.getRchild())) + 1);
		rotate.setHeight(Math.max(findHeight(rotate.getLchild()), findHeight(rotate.getRchild())) + 1);
		node.setSize(findSize(node.getLchild()) + findSize(node.getRchild()) + 1);
		rotate.setSize(findSize(rotate.getLchild()) + findSize(rotate.getRchild()) + 1);
		rotate.setBalanceFactor(findHeight(rotate.getLchild()) - findHeight(rotate.getRchild()));
		node.setBalanceFactor(findHeight(node.getLchild()) - findHeight(node.getRchild()));
		return rotate;
	}

	/**
	 * This will return the height of the node and if the height is null it will return -1.
	 * @param node
	 * @return Integer
	 */
	private int findHeight(Node node) {
		if (node == null) {
			return -1;
		}
		return node.getHeight();
	}
	/**
	 * Looks up the table entry with the provided key.
	 * @param key
	 * @return The value of the entry with the provided key. Null if
	 * no entry with the key can be found.
	 */
	public String lookUp(String key) {
		return lookUpNode(key, root);
	}

	/**
	 * This will recursively look for the value, if not found it will return the
	 * empty string.
	 * 
	 * @param key
	 * @param root
	 * @return String
	 */
	private String lookUpNode(String key, Node root) {
		if (root == null) {
			return "";
		} else if (0 == root.getKey().compareToIgnoreCase(key)) {
			return root.getValue();
		} else if (0 < root.getKey().compareToIgnoreCase(key)) {
			return lookUpNode(key, root.getLchild());
		} else {
			return lookUpNode(key, root.getRchild());
		}
	}

	/**
	 * Deletes the table entry with the given key.
	 * 
	 * @param key
	 * @return boolean  True if the entry was successfully deleted. False if
	 * no entry with the given key was found
	 */
	public boolean delete(String key) {
		if ("" != lookUp(key)) {
			this.root = deleteNode(key, root);
			this.root = rebalance(root);
			return true;
		}

		return false;
	}

	/**
	 *Deletes the node with the provided key from the given tree
	 * 
	 * @param key The key of the node to delete
	 * @param root The root of the tree containing the node to delete
	 * @return Node The root node of the altered tree.
	 */
	private Node deleteNode(String key, Node root) {
		Node temp = root;
		if (temp == null) {
			return null;
		} else if (0 < temp.getKey().compareToIgnoreCase(key)) {
			temp.setLchild(deleteNode(key, temp.getLchild()));
		} else if (0 > temp.getKey().compareToIgnoreCase(key)) {
			temp.setRchild(deleteNode(key, temp.getRchild()));
		} else {

			if (temp.getLchild() == null) {

				return root.getRchild();

			} else if (root.getRchild() == null) {

				return root.getLchild();
			}

			Node tempNode = temp.getRchild();

			while (tempNode.getLchild() != null) {
				tempNode = tempNode.getLchild();
			}

			temp.setKey(tempNode.getKey());
			temp.setRchild(deleteNode(temp.getKey(), temp.getRchild()));
		}

		temp.setHeight(1 + Math.max(findHeight(temp.getLchild()), findHeight(temp.getRchild())));
		temp.setSize(temp.getSize() - 1);
		temp.setBalanceFactor(findHeight(temp.getLchild()) - findHeight(temp.getRchild()));
		return temp;
	}

	/**
	 * Replaces the old value associated with the given key
	 * with the newValue string.
	 * @param key
	 * @param newValue
	 * @return True if the node value was updated successfully.
	 * False if the provided key was not found.
	 */
	public boolean update(String key, String newValue) {
		Node itrTree = root;
		while (itrTree != null) {
			if (itrTree.getKey().compareToIgnoreCase(key) > 0) {
				itrTree = itrTree.getLchild();
			} else if (itrTree.getKey().compareToIgnoreCase(key) < 0) {
				itrTree = itrTree.getRchild();
			} else if (itrTree.getKey().compareToIgnoreCase(key) == 0) {
				itrTree.setValue(newValue);
				return true;
			}
		}
		return false;
	}

	/**
	 * Displays all nodes in the table.
	 * @return The number of nodes in the table.
	 */
	public int displayAll() {
		displayNode(root);
		//System.out.println("Tree size = " + root.getSize());
		//System.out.println("Number of contacts in addressbook = " + root.getHeight());
		return root.getSize();
	}

	/**
	 * Displays all nodes in a (sub)tree using in-order traversal
	 * (left, parent, right)
	 * @param node - The root node of the tree to display
	 * @return The number of nodes in the tree
	 */
	private int displayNode(Node root) {
		if (root == null)
			return 0;
		displayNode(root.getLchild());
		System.out.println(root.getKey());
		System.out.println(root.getValue());
		System.out.println("     --- Node height = " + root.getBalanceFactor());
		System.out.println();
		displayNode(root.getRchild());
		return root.getSize();
	}

	/**
	 * Saves the table to a text file
	 * @param filename Name of the file to contain the table
	 */
	public void save() throws IOException {
		System.out.print("Please enter the file name: ");
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		String key = scn.nextLine();
		FileWriter fWriter = new FileWriter(key + ".txt");
		BufferedWriter writer = new BufferedWriter(fWriter);
		writeNode(writer, root);
		writer.newLine();
		writer.close();
	}

	/**
	 * Writes a tree to a file using pre-order traversal
	 * (parent, left, right)
	 * @param writer Writer to the file
	 * @param node - Root node of the tree to write
	 * @throws IOException
	 */
	private void writeNode(BufferedWriter writer, Node node) throws IOException {
		if (root == null) {
			return;
		}
		writer.write(root.getKey() + "\n");
		writer.write(root.getValue() + "\n");
		writeNode(writer, root.getLchild());
		writeNode(writer, root.getRchild());
	}
	/**
	 * Finds the largest node of the provided tree
	 * @param parent - The root of the tree
	 * @return The largest node in the provided tree
	 */
	private Node findLargestNode(Node parent) {
		System.out.println(root);
		return root;
	}
	/**
	* Updates the height and balance factor of a node.
	* Note that this method assumes all child nodes
	* have up-to-date height and balance factors.
	*/
	private void updateAVL(Node n) {
		
	}
}
