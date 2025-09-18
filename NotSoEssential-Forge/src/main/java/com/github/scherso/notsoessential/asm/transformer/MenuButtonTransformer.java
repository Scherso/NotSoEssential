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
            if (method.name.equals("draw"))
            {
                method.instructions.clear();
                method.localVariables.clear();
                method.tryCatchBlocks.clear();

                // Adds a parameter null check similar to the original method.
                // This was a hacky solution to a crazy VerifyError,
                // and hopefully this will ensure that the instruction
                // type matches the stack map.
                method.instructions.add(new VarInsnNode(ALOAD, 1)); // matrixStack
                method.instructions.add(new LdcInsnNode("matrixStack"));
                method.instructions.add(new MethodInsnNode(INVOKESTATIC,
                    "kotlin/jvm/internal/Intrinsics",
                    "checkNotNullParameter",
                    "(Ljava/lang/Object;Ljava/lang/String;)V",
                    false));

                method.instructions.add(new InsnNode(RETURN));

                method.maxStack  = 2;
                method.maxLocals = 2;

                break;
            }
        }
    }

}
