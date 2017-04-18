package com.sshfuture.miniJvm.cmd;

import com.sshfuture.miniJvm.clz.ClassFile;
import com.sshfuture.miniJvm.constant.ConstantInfo;
import com.sshfuture.miniJvm.constant.ConstantPool;
import com.sshfuture.miniJvm.constant.StringInfo;

public class LdcCmd extends OneOperandCmd {

	public LdcCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);		
	}
	
	@Override
	public String toString(ConstantPool pool) {
		
		ConstantInfo info = (ConstantInfo)pool.getConstantInfo(this.getOperand());
		
		String value = "TBD";
		if(info instanceof StringInfo){
			StringInfo strInfo = (StringInfo)info;
			value = strInfo.toString();
		}
		
		return this.getOffset()+":"+this.getOpCode()+" " + this.getReadableCodeText() + " "+  value;
		
	}
	
}
