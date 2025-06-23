package com.github.scherso.notsoessential.asm.transformer;

import com.github.scherso.notsoessential.asm.ITransformer;
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

    /**
     * Removes the user's ability to execute the '/essential' command. Instead, the command will output 'no' into the
     * chat.
     *
     * @param classNode transformed class node
     * @param name      transformed class name
     */
    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            if (method.name.equals("handle"))
            {
                this.clearInstructions(method);
                method.instructions.insert(this.createInsnList(
                    new LdcInsnNode("§7[§cNot §aSo §9Essential§7] §rno"),
                    new MethodInsnNode(INVOKESTATIC, "gg/essential/universal/UChat", "chat", "(Ljava/lang/Object;)V", false),
                    new InsnNode(RETURN)
                ));
            }
        }
    }

}
