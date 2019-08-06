import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Table {

	private Node root;

	/**
	 * Stores the key/value pair in the address book.
	 * @param key
	 * @param value
	 * @return boolean
	 */
	public boolean insert(String key, String value) {
		Node itrTree = root;
		if (root == null) {
			root = new Node(key, value);
		} else {
			while (itrTree != null) {
				if (itrTree.getKey().compareToIgnoreCase(key) > 0) {
					if (itrTree.getLeft() != null) {
						itrTree.setSize(itrTree.getSize() + 1);
						itrTree = itrTree.getLeft();
					} else {
						itrTree.setLeft(new Node(key, value));
						break;
					}

				} else if (itrTree.getKey().compareToIgnoreCase(key) < 0) {

					if (itrTree.getRight() != null) {
					itrTree.setSize(itrTree.getSize() + 1);
						itrTree = itrTree.getRight();
					} else {
						itrTree.setRight(new Node(key, value));
						break;
					}

				} else if (itrTree.getKey().compareToIgnoreCase(key) == 0) {
					break;
				}
			}
			return true;
		}
		return false;
	}


	/**
	 * This will return the size of the node recursively.
	 * @param node
	 * @return integer
	 */
	private int calculateSize(Node root) {
		if (root == null) {
			return 0;
		} else {
			return calculateSize(root.getLeft()) + 1 + calculateSize(root.getRight());
		}
	}
	/**
	 * Looks up the entry with the given key and returns the associated value. If no entry is found null is returned.
	 * @param key
	 * @return String
	 */
	public String lookUp(String key) {
		return lookUpRecurvive(key, root);
	}

	/**
	 * This will recursively look for the value, if not found it will return the empty string.
	 * @param key
	 * @param root
	 * @return String
	 */
	private String lookUpRecurvive(String key, Node root) {
		if (root == null) {
			return "";
		} else if (0 == root.getKey().compareToIgnoreCase(key)) {
			return root.getValue();
		} else if (0 < root.getKey().compareToIgnoreCase(key)) {
			return lookUpRecurvive(key, root.getLeft());
		} else {
			return lookUpRecurvive(key, root.getRight());
		}
	}

	/**
	 * Deletes the entry with the given key.
	 * @param key
	 * @return boolean
	 */
	public boolean delete(String key) {
		if ( "" != lookUp( key ) ) {
			this.root = deleteContact(key, root);
			return true;
		}
		return false;
	}
	/**
	 * This will delete the contact using recursion.
	 * @param key
	 * @param root
	 * @return Node
	 */
	private Node deleteContact(String key, Node root) {
		Node temp = root;
		if (temp == null) {
			return null;
		} else if (0 < temp.getKey().compareToIgnoreCase(key)) {
			temp.setLeft(deleteContact(key, temp.getLeft()));
		} else if (0 > temp.getKey().compareToIgnoreCase(key)) {
			temp.setRight(deleteContact(key, temp.getRight()));
		} else {

			if (temp.getLeft() == null) {

				return root.getRight();

			} else if (root.getRight() == null) {

				return root.getLeft();
			}

			Node tempNode = temp.getRight();

			while (tempNode.getLeft() != null) {
				tempNode = tempNode.getLeft();
			}

			temp.setKey(tempNode.getKey());
			temp.setRight(deleteContact(temp.getKey(), temp.getRight()));
		}
		return temp;
	}

	/**
	 * 
	 * Replaces the old value associated with with the given key with the newValue string.
	 * @param key
	 * @param newValue
	 * @return boolean
	 */
	public boolean update(String key, String newValue) {
		Node itrTree = root;
		while (itrTree != null) {
			if (itrTree.getKey().compareToIgnoreCase(key) > 0) {
				itrTree = itrTree.getLeft();
			} else if (itrTree.getKey().compareToIgnoreCase(key) < 0) {
				itrTree = itrTree.getRight();
			} else if (itrTree.getKey().compareToIgnoreCase(key) == 0) {
				itrTree.setValue(newValue);
				return true;
			}
		}
		return false;
	}

	/**
	 * Displays Name/Address for each table entry, the list of entries is sorted by the keys.
	 * @return integer
	 */
	public int displayAll() {
		displayNode(root);
		System.out.println("Tree size = " + root.getSize());
		System.out.println("Number of contacts in addressbook = " + root.getSize());
		return root.getSize();
	}

	/**
	 * This is a recursive in-order traversal.
	 * @param root
	 */
	private void displayNode(Node root) {
		if (root == null)
			return;
		displayNode(root.getLeft());
		System.out.println(root.getKey());
		System.out.println(root.getValue());
		System.out.println();
		displayNode(root.getRight());
	}

	/**
	 *  reads the name of a text output file, and will write a list of the table entries to an the output file.
	 * @throws IOException
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
	 * This will save the file recursively.
	 * @param writer
	 * @param root
	 * @throws IOException
	 */
	private void writeNode(BufferedWriter writer, Node root) throws IOException {
		if (root == null) {
			return;
		}
		writer.write(root.getKey() + "\n");
		writer.write(root.getValue() + "\n");
		writeNode(writer, root.getLeft());
		writeNode(writer, root.getRight());
	}
}
