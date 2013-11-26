package org.alcobass.morphdict.dictionary;

import java.util.ArrayList;
import java.util.List;

public abstract class Node
{
	Node parent = null;
	List<Node> children = new ArrayList<Node>();
	public Node getParent()
	{
		return parent;
	}
	public void setParent(Node parent)
	{
		this.parent = parent;
	}
	public List<Node> getChildren()
	{
		return children;
	}
	public void addChildren(Node newChild)
	{
		children.add(newChild);
	}
}
