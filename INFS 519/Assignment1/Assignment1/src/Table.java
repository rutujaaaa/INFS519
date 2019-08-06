import java.util.Scanner;

/**
 * 
 * @author rutujajadhav
 *
 */
public class Table {
	private Node mark;

	/**
	 * @return Node
	 */
	public Node getMark() {
		return mark;
	}

	/**
	 * @param Node
	 */
	public void setMark(Node mark) {
		this.mark = mark;
	}

	/**
	 * add a name (and address)
	 * 
	 * @param key
	 * @param value
	 * @return boolean
	 */
	public boolean insert(String key, String value) {
		Node n= mark;
		if(!markToStart()) {
			setMark(new Node(key,value));
			return true;
		}
		else if(keyAtMark().equals(key))
			return false;
		else {
			while(advanceMark()) {
				if(keyAtMark().equals(key)) {
					mark=n;
					return false;
				}
			}
			if(keyAtMark().equals(key))
				return false;
		}
		mark.setNext(new Node(key,value));
		mark=n;
		return true;
	}

	/**
	 * look	up a name (displaying the associated address)
	 * 
	 * @param key
	 * @return string
	 */
	public String lookUp(String key) {
		Node n = mark;
		if(!markToStart()) {
			return null;
		}
		else if(keyAtMark().equals(key)) {
			return valueAtMark();
		}
		while(advanceMark()) {
			if(keyAtMark().equals(key)) {
				String valAtMark= valueAtMark();
				mark=n;
				return valAtMark;
			}
		}
		return null;
	}

	/**
	 * delete an entry
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean delete(String key) {
		Node n = mark;
		Node prevMark=mark;
		boolean foundIt = false;
		if(markToStart()) {
			if(keyAtMark().equals(key)) {
				prevMark=n.getNext();
				setMark(prevMark);
				return true;
			}
			while(advanceMark()) {
				if(keyAtMark().equals(key)) {
					prevMark.setNext(mark.getNext());
					foundIt = true;
				}
			}
		}
		mark = n;
		return foundIt;
	}
	//return null if value not found or else traverse the table
	//1.do while do(this.key at mark .equals the key then return the value at the mark
	//while this.advanceMark 
	//2.DO while keep going long as long as node in the list return false at  the end of the list
	/**
	 * 
	 * update the address for a	name
	 * 
	 * @param key
	 * @param newValue
	 * @return boolean
	 */
	public boolean update(String key, String newValue) {
		Node n = mark;
		boolean foundIt = false;

		if(!markToStart()) {
			return false;
		}
		else if(keyAtMark().equals(key)) {
			mark.setValue(newValue);
			return true;
		}

		else {
			while(advanceMark()) {
				if(keyAtMark().equals(key)) {
					mark.setValue(newValue);
					foundIt=true;
				}
			}
				mark=n;
				return foundIt;
			}
	}
			/**
			 * Sets the mark to the first item in the table
			 * 
			 * @return boolean
			 */
			public boolean markToStart() {
				if (mark == null) {
					return false;
				}
				return true;
			}

			/**
			 * Moves the mark to the next item in the table
			 * 
			 * @return boolean
			 */
			public boolean advanceMark() {
				if (mark != null && null != mark.getNext()) {
					mark = mark.getNext();
					return true;
				}
				return false;
			}

			/**
			 * Returns the key stored in the item at the current mark.
			 * 
			 * @return string
			 */
			public String keyAtMark() {
				return mark.getKey();
			}
			/**
			 * Returns the value stored in the item at the current mark.
			 * 
			 * @return string
			 */
			public String valueAtMark() {
				return mark.getValue();
			}
			/**
			 * A method to display all the entries in the Address Book includin the	total #	of  entries
			 * @return integer
			 */
			public int displayAll() {
				// TODO Auto-generated method stub
				Node n = mark;
				int count = 0;
				while (n != null) {
					System.out.println("Name: " + n.getKey() + "\n" + "Address: " + n.getValue() + "\n");
					n = n.getNext();
					count++;
				}
				System.out.println("Total Enteries in the book are:"+count +"\n");
				return count;
			}



		}
