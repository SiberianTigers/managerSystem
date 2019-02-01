package com.jubao.cart.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化和反序列化工具类
 * @author dell
 *
 */
public class SerializeUtil {

	/***
	 * 序列化
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object){
		ObjectOutputStream oos=null;
		ByteArrayOutputStream baos=null;	
		try {
			baos=new ByteArrayOutputStream();
			oos=new ObjectOutputStream(baos);
			oos.writeObject(object);
			
			byte [] bytes=baos.toByteArray();
			
			return bytes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	 /**
	  * 反序列化
	  * @param bytes
	  * @return
	  */
	public static Object unserialize(byte[] bytes){
		ByteArrayInputStream  bais=null;
		try {
			
			bais=new ByteArrayInputStream(bytes);
			
			ObjectInputStream ois=new ObjectInputStream(bais);
			
			return ois.readObject();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
