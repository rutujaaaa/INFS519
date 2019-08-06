public	class	Node	implements	Comparable<Node>{
/*	Node	key	and value*/
private	String	key,	value;
/*	Child	Nodes	in	the	tree	*/
private	Node	left,	right;
/**	Creates	a	new	Node.
*	@param	key
*	@param	value
*/
private int size;
public Node(String key, String value) {
	super();
	this.key = key;
	this.value = value;
}
public void setSize(int size) {
	this.size = size;
}
/**
 * 
 * @return The	Node	key
 */
public String getKey() {
	return key;
}
/**
 * 
 * @param key The	Node	key
 */
public void setKey(String key) {
	this.key = key;
}
/**
 * 
 * @return The Node value
 */
public String getValue() {
	return value;
}
/**
 * 
 * @param value The	Node value
 */
public void setValue(String value) {
	this.value = value;
}
/**
 * 
 * @return The	left child Node
 */
public Node getLeft() {
	return left;
}
/**
 * 
 * @param left The left child Node
 */
public void setLeft(Node left) {
	this.left = left;
}
/**
 * 
 * @return The	right child	Node
 */
public Node getRight() {
	return right;
}
/**
 * 
 * @param right The	right child	Node
 */
public void setRight(Node right) {
	this.right = right;
}
/**
 * 
 */
@Override
public	String	toString()	{
return	String.format("%s%n%s%n",	this.key,	this.value);
}
/**
 * 
 */
@Override
public	int	compareTo(Node	that)	{
return	this.key.compareTo(that.key);
}
public int getSize() {
	return size;
}
}