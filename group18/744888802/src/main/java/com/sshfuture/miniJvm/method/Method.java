package com.sshfuture.miniJvm.method;

import com.sshfuture.miniJvm.attr.AttributeInfo;
import com.sshfuture.miniJvm.attr.CodeAttr;
import com.sshfuture.miniJvm.clz.ClassFile;
import com.sshfuture.miniJvm.cmd.ByteCodeCommand;
import com.sshfuture.miniJvm.constant.ConstantPool;
import com.sshfuture.miniJvm.constant.UTF8Info;
import com.sshfuture.miniJvm.loader.ByteCodeIterator;


public class Method {
	
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	private CodeAttr codeAttr;
	
	private ClassFile clzFile;
	
	
	public ClassFile getClzFile() {
		return clzFile;
	}

	public int getNameIndex() {
		return nameIndex;
	}
	public int getDescriptorIndex() {
		return descriptorIndex;
	}
	
	public CodeAttr getCodeAttr() {
		return codeAttr;
	}

	public void setCodeAttr(CodeAttr code) {
		this.codeAttr = code;
	}
	
	

	public Method(ClassFile clzFile, int accessFlag, int nameIndex, int descriptorIndex) {
		this.clzFile = clzFile;
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	
	
	
	
public String toString() {
		
		ConstantPool pool = this.clzFile.getConstantPool();
		StringBuilder buffer = new StringBuilder();
		
		String name = ((UTF8Info)pool.getConstantInfo(this.nameIndex)).getValue();
		
		String desc = ((UTF8Info)pool.getConstantInfo(this.descriptorIndex)).getValue();
		
		buffer.append(name).append(":").append(desc).append("\n");
		
		buffer.append(this.codeAttr.toString(pool));
		
		return buffer.toString();
	}
	
	public static Method parse(ClassFile clzFile, ByteCodeIterator iter){
		int accessFlag = iter.nextU2ToInt();
		int nameIndex = iter.nextU2ToInt();
		int descIndex = iter.nextU2ToInt();
		int attribCount = iter.nextU2ToInt();
		
		
		Method m = new Method(clzFile, accessFlag, nameIndex, descIndex);
		
		for( int j=1; j<= attribCount; j++){
			System.out.println("attribCount:"+attribCount);

			int attrNameIndex = iter.nextU2ToInt();	
			String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
			iter.back(2);
			
			if(AttributeInfo.CODE.equalsIgnoreCase(attrName)){
				CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
				m.setCodeAttr(codeAttr);
			} else{
				throw new RuntimeException("only CODE attribute is implemented , please implement the "+ attrName);
			}
			
		}
		
		return m ;
		
	}

	public ByteCodeCommand[] getCmds() {
		return this.getCodeAttr().getCmds();
	}
}
