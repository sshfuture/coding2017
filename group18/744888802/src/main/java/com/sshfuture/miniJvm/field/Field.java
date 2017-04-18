package com.sshfuture.miniJvm.field;

import com.sshfuture.miniJvm.constant.ConstantPool;
import com.sshfuture.miniJvm.constant.UTF8Info;
import com.sshfuture.miniJvm.loader.ByteCodeIterator;


public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	
	
	private ConstantPool pool;
	
	public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {
		
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}

	public String toString() {
		String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
		
		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		return name +":"+ desc;
	}
	
	
	public static Field parse(ConstantPool pool, ByteCodeIterator iter){
		
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descIndex = iter.nextU2ToInt();
		int attribCount = iter.nextU2ToInt();
		//System.out.println("field attribute count:"+ attribCount);
		
		Field f = new Field(accessFlag, nameIndex, descIndex,pool);
		
		if(attribCount > 0){
			throw new RuntimeException("Field Attribute has not been implemented");
		}
		
		return f;
	}

}
