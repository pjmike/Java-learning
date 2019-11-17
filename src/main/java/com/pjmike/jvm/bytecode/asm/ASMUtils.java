package com.pjmike.jvm.bytecode.asm;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.*;

import java.io.*;

/**
 * @description:
 * @author: 13572
 * @create: 2019/05/01 19:12
 */
public class ASMUtils {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("F:\\IDEAproject\\Java-Learning\\src\\main\\java\\com\\pjmike\\jvm\\bytecode\\asm\\ASMTest.class");
        ClassReader classReader = new ClassReader(fileInputStream);
        ClassWriter cw = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        //Java8选择ASM5，
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM5, cw) {
            @Override
            public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
                System.out.println("field:" + name);
                return super.visitField(access, name, desc, signature, value);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                System.out.println("方法" + name);
                return super.visitMethod(access, name, desc, signature, exceptions);
            }
        };
        //忽略调试信息
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
    }
}
