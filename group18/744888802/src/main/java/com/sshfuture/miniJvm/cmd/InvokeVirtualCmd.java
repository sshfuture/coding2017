package com.sshfuture.miniJvm.cmd;

import com.sshfuture.miniJvm.clz.ClassFile;
import com.sshfuture.miniJvm.constant.ConstantPool;


public class InvokeVirtualCmd extends TwoOperandCmd {

	public InvokeVirtualCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
	}

	@Override
	public String toString(ConstantPool pool) {
		
		return super.getOperandAsMethod(pool);
	}

	
	

}
