package com.github.scherso.notsoessential.asm.transformer;

import com.github.scherso.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.PUTFIELD;

public class MenuButtonTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.gui.common.MenuButton");
    }

    /**
     * Attempts to transform the MenuButton class. <br> Removes the Essential Setting icon to the right of the 'Snooper
     * Settings...' button in the options menu.
     *
     * <ul>
     *     <li> {@link org.objectweb.asm.Opcodes#PUTFIELD} Sets an instance field defined by
     *     the owner class and the field’s name and descriptor. </li>
     *     <li> {@link FieldInsnNode} A node that represents a field instruction. </li>
     * </ul>
     *
     * @param classNode transformed class node
     * @param name      transformed class name
     */
    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            if (method.name.equals("<init>"))
            {
                for (AbstractInsnNode insn : method.instructions.toArray())
                {
                    if (insn instanceof FieldInsnNode && insn.getOpcode() == PUTFIELD)
                        method.instructions.remove(insn);
                }
            }
        }
    }

}
