package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class EssentialConfigTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.config.EssentialConfig");
    }

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
                    method.instructions.insert(this.functionReturnFalse());
                    break;
                case "getDisableCosmetics":
                case "getDisableAllNotifications":
                    method.instructions.insert(this.functionReturnTrue());
                    break;
            }
        }
    }

}