package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.RETURN;

public class PauseMenuDisplayTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.handlers.PauseMenuDisplay");
    }

    /**
     * Remove the pause menu display, i.e. The floating buttons 
     * and widgets that interact with Essential in the pause menu.
     *
     * @param classNode transformed class node
     * @param name      transformed class name
     */
    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            if (method.name.equals("init"))
            {
                this.clearInstructions(method);
                method.instructions.insert(new InsnNode(RETURN));
            }
        }
    }

}
