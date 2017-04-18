package com.sshfuture.miniJvm.cmd;

import com.sshfuture.miniJvm.clz.ClassFile;
import com.sshfuture.miniJvm.constant.ConstantPool;


public class BiPushCmd extends OneOperandCmd {

	public BiPushCmd(ClassFile clzFile, String opCode) {
		super(clzFile,opCode);
		
	}

	@Override
	public String toString(ConstantPool pool) {
	
		return this.getOffset()+": "+ this.getOpCode()+" " + this.getReadableCodeText() + " " + this.getOperand();
	}
	
	

}
