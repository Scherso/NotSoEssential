package com.github.u9g.notsoessential.asm;

import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public interface ITransformer {

    /**
     * Class name that will undergo transformation.
     *
     * @return class name
     */
    String[] getClassName();

    /**
     * Perform objectweb assembly in order to transform Java bytecode.
     *
     * @param classNode transformed class node
     * @param name      transformed class name
     */
    void transform(ClassNode classNode, String name);

    /**
     * Map method name from de-obfuscated MCP.
     *
     * @param classNode  transformed class node
     * @param methodNode transformed classes method node
     * @return mapped method name
     */
    default String mapMethodName(ClassNode classNode, MethodNode methodNode) {
        return (FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, methodNode.name, methodNode.desc));
    }

    /**
     * Map the field name from de-obfuscated MCP.
     *
     * @param classNode transformed class node
     * @param fieldNode transformed class field node
     * @return mapped field name
     */
    default String mapFieldName(ClassNode classNode, FieldNode fieldNode) {
        return (FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(classNode.name, fieldNode.name, fieldNode.desc));
    }

    /**
     * Map the method description from de-obfuscated MCP.
     *
     * @param methodNode transformed method node
     * @return mapped method description
     */
    default String mapMethodDesc(MethodNode methodNode) {
        return (FMLDeobfuscatingRemapper.INSTANCE.mapMethodDesc(methodNode.desc));
    }

    /**
     * Map the field name from de-obfuscated MCP.
     *
     * @param methodInsnNode transformed field insn node
     * @return mapped insn field
     */
    default String mapMethodNameFromNode(MethodInsnNode methodInsnNode) {
        return (FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(methodInsnNode.owner, methodInsnNode.name, methodInsnNode.desc));
    }

    /**
     * Acquire a mapped class name from FML.
     *
     * @param name class name
     * @return internal type name
     */
    default String mapClassName(String name) {
        return (FMLDeobfuscatingRemapper.INSTANCE.mapType(name));
    }

    /**
     * Clear method instructions
     *
     * @param methodNode instance variable in parameters to clear lists
     */
    default void clearInstructions(MethodNode methodNode) {
        /* Remove all method instructions from a node list. */
        methodNode.instructions.clear();

        /* Prevent procedure from clearing an already empty local variable. */
        if (!methodNode.localVariables.isEmpty()) methodNode.localVariables.clear();

        /* Prevent procedure from clearing an already empty catch block. */
        if (!methodNode.tryCatchBlocks.isEmpty()) methodNode.tryCatchBlocks.clear();
    }

}
