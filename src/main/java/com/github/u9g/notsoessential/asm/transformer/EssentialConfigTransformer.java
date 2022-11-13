package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

public class EssentialConfigTransformer implements ITransformer {

    @Override
    public String[] getClassName() {
        return (new String[]{"gg.essential.config.EssentialConfig"});
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods) {
            switch (methodNode.name) {
                case "getShowEssentialIndicatorOnTab":
                case "getShowEssentialIndicatorOnNametag":
                case "getEssentialEnabled":
                case "getEssentialFull":
                    methodNode.instructions.insertBefore(methodNode.instructions.getFirst(), functionReturnFalse());
                    break;
                case "getDisableCosmetics":
                case "getDisableAllNotifications":
                    methodNode.instructions.insertBefore(methodNode.instructions.getFirst(), functionReturnTrue());
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
        InsnList list = new InsnList();
        list.add(new InsnNode(Opcodes.ICONST_0));
        list.add(new InsnNode(Opcodes.IRETURN));
        return (list);
    }

    /**
     * Return instruction list of true booleans with Instruction Node Opcodes.
     * Opcode ICONST_1 = true
     * Opcode IRETURN = return
     *
     * @return list of true booleans
     */
    public InsnList functionReturnTrue() {
        InsnList list = new InsnList();
        list.add(new InsnNode(Opcodes.ICONST_1));
        list.add(new InsnNode(Opcodes.IRETURN));
        return (list);
    }

}
