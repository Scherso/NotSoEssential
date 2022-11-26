package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.BIPUSH;

public class GuiOptionsEditorTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.gui.GuiOptionsEditor");
    }

    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            if (method.name.equals("guiOptionsInit"))
            {
                for (final AbstractInsnNode INSN : method.instructions.toArray())
                {
                    if (INSN.getOpcode() == BIPUSH && ((IntInsnNode) INSN).operand == 104)
                    {
                        method.instructions.set(INSN, new IntInsnNode(BIPUSH, 0));
                    }
                }
            }
        }
    }

}