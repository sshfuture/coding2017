package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

import java.util.Arrays;

public class ByteCodeIterator {
    int index = 0;
    byte [] codes = null;

    public ByteCodeIterator(byte[] codes){
        this.codes = codes;
    }
    public int nextU2ToInt(){
        return Util.byteToInt(new byte[]{codes[index++],codes[index++]});
    }

    public String  nextU4ToString(){
        return Util.byteToHexString(new byte[]{codes[index++],codes[index++],codes[index++],codes[index++]});
    }
    public int nextU1ToInt(){
        return Util.byteToInt(new byte[]{codes[index++]});
    }
    public String nextU1ToString(){
        return Util.byteToHexString(new byte[]{codes[index++]});
    }
    public byte[] nextBytesByLength(int length){
        byte[] res = Arrays.copyOfRange(codes,index,(index+length));

        index = index+length;
        return  res;

    }



}