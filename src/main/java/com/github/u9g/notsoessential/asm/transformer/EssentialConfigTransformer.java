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

    /**
     * <pre>
     *     This method is used to transform the EssentialConfig class.
     *     Any configuration options local to this class will be transformed
     *     according to their 'usefulness', or lack thereof.
     *
     *     FOR REFERENCE:
     *     {@link org.objectweb.asm.Opcodes#ICONST_0} in this use; false.
     *     {@link org.objectweb.asm.Opcodes#ICONST_1} in this use; true.
     *     {@link org.objectweb.asm.Opcodes#IRETURN} return an int from a method,
     * </pre>
     *
     * @param classNode transformed class node
     * @param name      transformed class name
     */
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
                            /* Inserting the following
                             * ICONST_0 - false
                             * IRETURN  - return int value
                             * this meaning return false. */
                            new InsnNode(ICONST_0),
                            new InsnNode(IRETURN)
                    ));
                    break;
                case "getDisableCosmetics":
                case "getDisableAllNotifications":
                    method.instructions.insert(this.createInsnList(
                            /* Inserting the following:
                             * ICONST_1 - true
                             * IRETURN  - return int value
                             * this meaning return true. */
                            new InsnNode(ICONST_1),
                            new InsnNode(IRETURN)
                    ));
                    break;
            }
        }
    }

}