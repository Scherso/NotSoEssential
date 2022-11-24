package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.DSTORE;
import static org.objectweb.asm.Opcodes.RETURN;

public class EssentialModelRendererTransformer implements ITransformer
{

    @Override
    public String getClassName()
    {
        return ("gg.essential.cosmetics.EssentialModelRenderer");
    }

    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            switch (method.name)
            {
                case "cosmeticsShouldRender":
                    for (final AbstractInsnNode INSN : method.instructions.toArray())
                    {
                        if (INSN instanceof VarInsnNode && INSN.getOpcode() == DSTORE)
                        {
                            method.instructions.insertBefore(INSN, this.functionReturnFalse());
                            method.instructions.remove(INSN);
                        }
                    }
                    break;
                case "render":
                case "doRenderLayer":
                    this.clearInstructions(method);
                    method.instructions.insert(new InsnNode(RETURN));
                    break;
            }
        }
    }

}