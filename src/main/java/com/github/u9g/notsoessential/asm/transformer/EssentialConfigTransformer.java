package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.*;

public class EssentialConfigTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.config.EssentialConfig");
    }

    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            switch (method.name)
            {
                case "getShowEssentialIndicatorOnTab":
                case "getShowEssentialIndicatorOnNametag":
                case "getEssentialEnabled":
                case "getEssentialFull":
                    method.instructions.insert(this.createInsnList(
                            new InsnNode(ICONST_0),
                            new InsnNode(IRETURN)
                    ));
                    break;
                case "getDisableCosmetics":
                case "getDisableAllNotifications":
                    method.instructions.insert(this.createInsnList(
                            new InsnNode(ICONST_1),
                            new InsnNode(IRETURN)
                    ));
                    break;
            }
        }
    }

}