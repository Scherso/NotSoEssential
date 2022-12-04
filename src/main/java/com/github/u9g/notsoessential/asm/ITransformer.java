package com.github.u9g.notsoessential.asm;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

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
     * Clear method instructions
     *
     * @param methodNode instance variable of MethodNode
     */
    default void clearInstructions(MethodNode methodNode)
    {
        /* Remove all method instructions from a node list. */
        methodNode.instructions.clear();

        /* Prevent procedure from clearing an already empty local variable. */
        if (!methodNode.localVariables.isEmpty())     methodNode.localVariables.clear();
        if (!methodNode.tryCatchBlocks.isEmpty())     methodNode.tryCatchBlocks.clear();
        if (!methodNode.exceptions.isEmpty())         methodNode.exceptions.clear();
        if (!methodNode.visibleAnnotations.isEmpty()) methodNode.visibleAnnotations.clear();
    }

    /**
     * Creates a new {@link InsnList} to return in an instruction list function.
     *
     * @param instructions {@link AbstractInsnNode} instructions to add to the list
     * @return {@link InsnList} with the instructions added
     * @author Nilsen84 (nils) 2022
     */
    default InsnList createInsnList(AbstractInsnNode... instructions)
    {
        InsnList instructionList = new InsnList();
        for (AbstractInsnNode instruction : instructions)
            instructionList.add(instruction);

        return (instructionList);
    }

}
