package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class ExtensionsKtTransformer implements ITransformer
{

    @Override
    public String getClassName()
    {
        return ("gg.essential.gui.common.ExtensionsKt");
    }

    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            for (AbstractInsnNode insn : method.instructions.toArray())
            {
                if (insn instanceof MethodInsnNode && insn.getOpcode() == INVOKESTATIC)
                    if (((MethodInsnNode) insn).owner.contains("Intrinsics"))
                        method.instructions.remove(insn);
            }
        }
    }

}
