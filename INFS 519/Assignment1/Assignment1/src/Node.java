/**
 * 
 * @author rutujajadhav
 *
 */
public class Node {
	/*Node Key*/
	private String key;
	/*Node value*/
	private String value;

	/*Next Node in the list*/
	private Node next;

	Node() {
		this.key = null;
		this.value = null;
	}

	/**Creates a new Node
	 * @param key
	 * @param value
	 */
	Node(String key, String value) {
		this.setKey(key);
		this.setValue(value);
	}

	/**
	 * @return The Node key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key The Node key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return String
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return The next Node in the list
	 */
	public Node getNext() {
		return next;
	}
	/**
	 * @param next The next Node in the list
	 */
	public void setNext(Node next) {
		this.next = next;
	}
}
