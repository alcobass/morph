package org.alcobass.morphdict.dictionary;

public class SingleWordNode extends Node
{
	String text;
	boolean inDictionary = false;
	public SingleWordNode(String text)
	{
		this.text = text;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	public boolean isInDictionary()
	{
		return inDictionary;
	}
	public void setInDictionary(boolean inDictionary)
	{
		this.inDictionary = inDictionary;
	}
	@Override
    public String toString()
    {
	    return "SingleWordNode [text=" + text + ", inDictionary="
	            + inDictionary + "]";
    }
	
}
