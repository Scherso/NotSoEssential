package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.RETURN;

public class CommandConfigTransformer implements ITransformer
{

    @Override
    public String getClassName()
    {
        return ("gg.essential.commands.impl.CommandConfig");
    }

    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            if (method.name.equals("handle"))
            {
                method.instructions.clear();
                method.instructions.insert(this.createInsnList(
                        new LdcInsnNode("§7[§cNot §9So Essential§7] §rno"),
                        new MethodInsnNode(INVOKESTATIC, "gg/essential/universal/UChat", "chat", "(Ljava/lang/Object;)V", false),
                        new InsnNode(RETURN)
                ));
            }
        }
    }

}
