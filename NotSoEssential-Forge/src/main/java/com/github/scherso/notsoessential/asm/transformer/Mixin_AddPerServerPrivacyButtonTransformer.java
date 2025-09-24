package com.github.scherso.notsoessential.asm.transformer;

import com.github.scherso.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class Mixin_AddPerServerPrivacyButtonTransformer implements ITransformer
{

    @Override
    public String getClassName()
    {
        return ("gg.essential.mixins.transformers.feature.per_server_privacy.Mixin_AddPerServerPrivacyButton");
    }

    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            if (method.desc.endsWith("V"))
            {
                this.clearInstructions(method);
                method.instructions.add(new InsnNode(RETURN));
            }
        }
    }

}
