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

    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            switch (method.name)
            {
                case "drawTabIndicator":
                case "drawNametagIndicator$5aa011b9":
                    method.instructions.insert(new InsnNode(RETURN));
                    break;
            }
        }
    }

}
