package cn.davidma.mendinggems.common.misc;

public interface IProxy {
	
	public String localize(String text);
	
	public String format(String text, Object... args);
}
