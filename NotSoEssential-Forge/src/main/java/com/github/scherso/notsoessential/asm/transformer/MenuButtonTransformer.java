package com.github.scherso.notsoessential.asm.transformer;

import com.github.scherso.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

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
     *     <li> {@link org.objectweb.asm.Opcodes#RETURN} same as {@code return}.
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
            if (method.name.equals("draw") && method.desc.equals("(Lgg/essential/universal/UMatrixStack;)V"))
            {
                // Find and remove the invokespecial UIComponent.draw call
                AbstractInsnNode[] instructions = method.instructions.toArray();

                for (int i = 0; i < instructions.length; i++)
                {
                    AbstractInsnNode insn = instructions[i];

                    if (insn.getOpcode() == INVOKESPECIAL)
                    {
                        MethodInsnNode methodInsn = (MethodInsnNode) insn;

                        // Check if this is the UIComponent.draw call
                        if (methodInsn.owner.equals("gg/essential/elementa/UIComponent") &&
                            methodInsn.name.equals("draw") &&
                            methodInsn.desc.equals("(Lgg/essential/universal/UMatrixStack;)V"))
                        {
                            // Remove the method call instruction
                            method.instructions.remove(insn);

                            // Also need to remove the two ALOAD instructions that load 'this' and 'matrixStack'
                            // They should be right before the INVOKESPECIAL
                            if (i >= 2)
                            {
                                AbstractInsnNode prev1 = instructions[i-1]; // Should be ALOAD matrixStack
                                AbstractInsnNode prev2 = instructions[i-2]; // Should be ALOAD this

                                if (prev1.getOpcode() == ALOAD && prev2.getOpcode() == ALOAD)
                                {
                                    method.instructions.remove(prev1);
                                    method.instructions.remove(prev2);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

}
