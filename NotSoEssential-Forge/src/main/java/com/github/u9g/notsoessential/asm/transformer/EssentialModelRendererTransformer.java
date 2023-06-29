package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class EssentialModelRendererTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.cosmetics.EssentialModelRenderer");
    }

    /**
     * Attempting to transform the EssentialModelRenderer class. <br> Procedural methods in EssentialModelRenderer are
     * returned at head, most functions are returned false. The intended outcome is to cancel the rendering of
     * cosmetics.
     *
     * <ul>
     *     <li> {@link org.objectweb.asm.tree.VarInsnNode} is a node that represents a local variable instruction
     *     that loads and stores the value of a local variable. </li>
     *     <li> {@link org.objectweb.asm.Opcodes#DSTORE} stores a double into a local variable. </li>
     * </ul>
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
                            method.instructions.insertBefore(INSN.getNext(), this.createInsnList(
                                new InsnNode(ICONST_0),
                                new InsnNode(IRETURN)
                            ));
                            method.instructions.remove(INSN);
                        }
                    }
                    break;
//              case "render$39300608":
//              case "doRenderLayer$278b9f3a$250b0546":
//                  this.clearInstructions(method);
//                  method.instructions.insert(new InsnNode(RETURN));
//                  break;
//              case "func_177142_b":
//                  this.clearInstructions(method);
//                  method.instructions.insert(this.createInsnList(
//                      new InsnNode(ICONST_0),
//                      new InsnNode(IRETURN)
//                  ));
//                  break;
            }
        }
    }

}
