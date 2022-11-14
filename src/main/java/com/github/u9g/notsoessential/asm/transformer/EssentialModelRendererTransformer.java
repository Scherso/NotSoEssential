package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.ListIterator;

public class EssentialModelRendererTransformer implements ITransformer {

    @Override
    public String[] getClassName() {
        return (new String[]{"gg.essential.cosmetics.EssentialModelRenderer"});
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods) {
            switch (methodNode.name) {
                case "cosmeticsShouldRender":
                    final ListIterator<AbstractInsnNode> iterator = methodNode.instructions.iterator();
                    while (iterator.hasNext()) {
                        final AbstractInsnNode next = iterator.next();
                        if (next instanceof VarInsnNode && next.getOpcode() == Opcodes.DSTORE)
                            methodNode.instructions.insertBefore(next.getNext(), functionReturnFalse());
                    }
                    break;
                case "render":
                case "doRenderLayer":
                    clearInstructions(methodNode);
                    methodNode.instructions.insertBefore(methodNode.instructions.getFirst(), new InsnNode(Opcodes.RETURN));
                    break;
            }
        }
    }

    /**
     * Return instruction list of false booleans with Instruction Node Opcodes.
     * Opcode ICONST_0 = false
     * Opcode IRETURN = return
     *
     * @return list of false booleans
     */
    public InsnList functionReturnFalse() {
        final InsnList list = new InsnList();
        list.add(new InsnNode(Opcodes.ICONST_0));
        list.add(new InsnNode(Opcodes.IRETURN));
        return (list);
    }

}