package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.RETURN;

public class OnlineIndicatorTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.handlers.OnlineIndicator");
    }

    /**
     * Remove the Essential indicators next to player names on name-tags, and in the tab list.
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
                case "drawTabIndicator":
                case "drawNametagIndicator$5aa011b9":
                    this.clearInstructions(method);
                    method.instructions.insert(new InsnNode(RETURN));
                    break;
            }
        }
    }

}
