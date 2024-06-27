package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.RETURN;

public class MixinLogExceptionsTransformer implements ITransformer
{

    @Override
    public String getClassName()
    {
        return ("gg.essential.mixins.transformers.client.network");
    }

    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            if (method.name.equals("essential$logException"))
            {
                this.clearInstructions(method);
                method.instructions.insert(new InsnNode(RETURN));
            }
        }
    }

}
