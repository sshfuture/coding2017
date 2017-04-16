package com.coderising.jvm.loader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.*;
import com.coderising.jvm.util.Util;

public class ClassFileParser {

    public ClassFile parse(byte[] codes) {
        ByteCodeIterator byteCodeIterator = new ByteCodeIterator(codes);
        String megic = byteCodeIterator.nextU4ToString();
        if (!"cafebabe".equals(megic)) {
            throw new RuntimeException("  this is not a java class");
        }
        ClassFile classFile = new ClassFile();
        classFile.setMinorVersion(byteCodeIterator.nextU2ToInt());
        classFile.setMajorVersion(byteCodeIterator.nextU2ToInt());
        classFile.setConstPool(parseConstantPool(byteCodeIterator));
        classFile.setAccessFlag(parseAccessFlag(byteCodeIterator));
        classFile.setClassIndex(parseClassInfex(byteCodeIterator));
        parseInterfaces(byteCodeIterator);
        parseFidld(classFile,byteCodeIterator);
        parseMethod(classFile,byteCodeIterator);

        return classFile;
    }


    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {

        return new AccessFlag(iter.nextU2ToInt());
    }

    private ClassIndex parseClassInfex(ByteCodeIterator iter) {
        int thisClassIndex = iter.nextU2ToInt();
        int superClassIndex = iter.nextU2ToInt();
        ClassIndex classIndex = new ClassIndex();
        classIndex.setThisClassIndex(thisClassIndex);
        classIndex.setSuperClassIndex(superClassIndex);
        return classIndex;

    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
        int poolCount = iter.nextU2ToInt();
        ConstantPool constantPool = new ConstantPool();
        for (int i = 0; i < poolCount; i++) {
            if(i == 0){
                constantPool.addConstantInfo(new NullConstantInfo());

                continue;
            }

            int typeInt = iter.nextU1ToInt();
            if (typeInt == ConstantInfo.CLASS_INFO) {
                ClassInfo constantInfo = new ClassInfo(constantPool);
                constantInfo.setUtf8Index(iter.nextU2ToInt());
                constantPool.addConstantInfo(constantInfo);

            } else if (typeInt == ConstantInfo.UTF8_INFO) {
                UTF8Info utf8Info = new UTF8Info(constantPool);
                int length = iter.nextU2ToInt();
                utf8Info.setLength(length);
                try {
                    utf8Info.setValue(new String(iter.nextBytesByLength(length), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                constantPool.addConstantInfo(utf8Info);

            } else if (typeInt == ConstantInfo.METHOD_INFO) {

                MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
                methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
                methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
                constantPool.addConstantInfo(methodRefInfo);

            } else if (typeInt == ConstantInfo.FIELD_INFO) {

                FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
                fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
                fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
                constantPool.addConstantInfo(fieldRefInfo);

            } else if (typeInt == ConstantInfo.STRING_INFO) {

                StringInfo stringInfo = new StringInfo(constantPool);
                stringInfo.setIndex(iter.nextU2ToInt());
                constantPool.addConstantInfo(stringInfo);

            } else if (typeInt == ConstantInfo.NAME_AND_TYPE_INFO) {

                NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
                nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
                nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
                constantPool.addConstantInfo(nameAndTypeInfo);

            } else {
                System.out.println("typeInt:" + typeInt);

                throw new RuntimeException("cannot analysis type");
            }
        }


        return constantPool;
    }

    private void parseInterfaces(ByteCodeIterator iter) {
        int interfaceCount = iter.nextU2ToInt();

        System.out.println("interfaceCount:" + interfaceCount);

        // TODO : 如果实现了interface, 这里需要解析
    }

    private void parseFidld(ClassFile clz,ByteCodeIterator iterator){



    }

    private void parseMethod(ClassFile clz,ByteCodeIterator iterator){

    }
}
