package com.github.u9g.notsoessential.asm;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.*;

public interface ITransformer
{

    /**
     * Class name that will undergo transformation.
     *
     * @return class name
     */
    String getClassName();

    /**
     * Perform transformation of Java bytecode.
     *
     * @param classNode transformed class node
     * @param name      transformed class name
     */
    void transform(ClassNode classNode, String name);

    /**
     * Return instruction list of false booleans with Instruction Node Opcodes.
     * Opcode ICONST_0 = false
     * Opcode IRETURN = return
     *
     * @return list of false booleans
     */
    default InsnList functionReturnFalse()
    {
        final InsnList list = new InsnList();
        list.add(new InsnNode(ICONST_0));
        list.add(new InsnNode(IRETURN));
        return (list);
    }

    /**
     * Return instruction list of true booleans with Instruction Node Opcodes.
     * Opcode ICONST_1 = true
     * Opcode IRETURN = return
     *
     * @return list of true booleans
     */
    default InsnList functionReturnTrue()
    {
        final InsnList list = new InsnList();
        list.add(new InsnNode(ICONST_1));
        list.add(new InsnNode(IRETURN));
        return (list);
    }

    /**
     * Clear method instructions
     *
     * @param methodNode instance variable of MethodNode
     */
    default void clearInstructions(MethodNode methodNode)
    {
        /* Remove all method instructions from a node list. */
        methodNode.instructions.clear();

        /* Prevent procedure from clearing an already empty local variable. */
        if (!methodNode.localVariables.isEmpty()) methodNode.localVariables.clear();
        if (!methodNode.tryCatchBlocks.isEmpty()) methodNode.tryCatchBlocks.clear();
        if (!methodNode.exceptions.isEmpty()) methodNode.exceptions.clear();
        if (!methodNode.visibleAnnotations.isEmpty()) methodNode.visibleAnnotations.clear();
    }

}
