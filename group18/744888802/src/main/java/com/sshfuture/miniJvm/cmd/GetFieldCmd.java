package com.sshfuture.miniJvm.cmd;

import com.sshfuture.miniJvm.clz.ClassFile;
import com.sshfuture.miniJvm.constant.ConstantPool;


public class GetFieldCmd extends TwoOperandCmd {

	public GetFieldCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);		
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsField(pool);
	}

	
	

}
