package directory_explorer;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

/**
 * The root of a tree representing a directory structure.
 */
public class FileNode {

	/** The name of the file or directory this node represents. */
	private String name;
	/** Whether this node represents a file or a directory. */
	private FileType type;
	/** This node's parent. */
	private FileNode parent;
	/**
	 * This node's children, mapped from the file names to the nodes. If type is
	 * FileType.FILE, this is null.
	 */
	private Map<String, FileNode> children;

	/**
	 * A node in this tree.
	 *
	 * @param name
	 *            the file
	 * @param parent
	 *            the parent node.
	 * @param type
	 *            file or directory
	 * @see buildFileTree
	 */
	public FileNode(String name, FileNode parent, FileType type) {
		this.name = name;
		this.type = type;
		this.parent = parent;
		this.children = new HashMap<String, FileNode>();
	}

	/**
	 * Find and return a child node named name in this directory tree, or null
	 * if there is no such child node.
	 *
	 * @param name
	 *            the file name to search for
	 * @return the node named name
	 */
	public FileNode findChild(String name) {
		FileNode result = null;
		//Base case.
		if (this.name == name) {
			return this;
		} 
		Collection<FileNode> children = this.children.values();
		FileNode[] childs = new FileNode[children.size()];
		//Recursively search to the tree's children.
		for (int i=0; result == null && i<children.size(); i++) {
			result = children.toArray(childs)[i].findChild(name);
		} return result;
		}
		//TODO	
		

	/**
	 * Return the name of the file or directory represented by this node.
	 *
	 * @return name of this Node
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name of the current node
	 *
	 * @param name
	 *            of the file/directory
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return the child nodes of this node.
	 *
	 * @return the child nodes directly underneath this node.
	 */
	public Collection<FileNode> getChildren() {
		return this.children.values();
	}

	/**
	 * Return this node's parent.
	 * 
	 * @return the parent
	 */
	public FileNode getParent() {
		return parent;
	}

	/**
	 * Set this node's parent to p.
	 * 
	 * @param p
	 *            the parent to set
	 */
	public void setParent(FileNode p) {
		this.parent = p;
	}

	/**
	 * Add childNode, representing a file or directory named name, as a child of
	 * this node.
	 * 
	 * @param name
	 *            the name of the file or directory
	 * @param childNode
	 *            the node to add as a child
	 */
	public void addChild(String name, FileNode childNode) {
		this.children.put(name, childNode);
	}

	/**
	 * Return whether this node represents a directory.
	 * 
	 * @return whether this node represents a directory.
	 */
	public boolean isDirectory() {
		return this.type == FileType.DIRECTORY;
	}

	/**
	 * This method is for code that tests this class.
	 * 
	 * @param args
	 *            the command line args.
	 */
	public static void main(String[] args) {
		System.out.println("Testing FileNode");
		FileNode f1 = new FileNode("top", null, FileType.DIRECTORY);
		if (!f1.getName().equals("top")) {
			System.out.println("Error: " + f1.getName() + " should be " + "top");
		}
		FileNode a = new FileNode("a", f1, FileType.DIRECTORY);
		FileNode b = new FileNode("b", a, FileType.DIRECTORY);
		FileNode c = new FileNode("c", a, FileType.DIRECTORY);
		FileNode d = new FileNode("d", c, FileType.DIRECTORY);
		FileNode e = new FileNode("e", c, FileType.DIRECTORY);
		f1.addChild("a", a);
		a.addChild("b", b);
		a.addChild("c", c);
		c.addChild("d", d);
		c.addChild("e", e);
		System.out.println(a.findChild("b").name);

	}

}
