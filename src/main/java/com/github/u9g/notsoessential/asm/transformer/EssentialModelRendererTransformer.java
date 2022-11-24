package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.DSTORE;
import static org.objectweb.asm.Opcodes.RETURN;

public class EssentialModelRendererTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.cosmetics.EssentialModelRenderer");
    }

     /**
      * <pre>
      * This method is used to transform the Essential Model renderer class,
      * finds and modifies methods that render cosmetics.
      * Procedural methods are returned, most functions are returned false.
      *
      * FOR REFERENCE:
      * {@link org.objectweb.asm.tree.VarInsnNode} is a node that represents a local variable instruction
      * that loads or stores the value of a local variable.
      * {@link DSTORE} stores a double into local variable
      * </pre>
      *
      * @param classNode transformed class node
      * @param name      transformed class name
      * @see org.objectweb.asm.tree.VarInsnNode
      */
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
                        /* See 'FOR REFERENCE' comment above for clarification. */
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