package com.github.scherso.notsoessential.asm.transformer;

import com.github.scherso.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.RETURN;

public class MenuButtonCompanionTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.gui.common.MenuButton$Companion");
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
            if (method.name.equals("drawButton"))
            {
                method.instructions.clear();
                method.instructions.add(new InsnNode(RETURN));
            }
        }
    }

}
