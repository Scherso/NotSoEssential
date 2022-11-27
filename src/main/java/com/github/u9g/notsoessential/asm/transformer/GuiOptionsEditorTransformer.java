package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.BIPUSH;

public class GuiOptionsEditorTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.gui.GuiOptionsEditor");
    }

    /**
     * <pre>
     *     This method is used to transform the GuiOptionsEditor class.
     *
     *     FOR REFERENCE:
     *     {@link org.objectweb.asm.Opcodes#BIPUSH} pushes a byte onto the stack as an
     *     integer value.
     *     {@link IntInsnNode#IntInsnNode(int, int)} creates a new {@link IntInsnNode}
     *     with the specified opcode and operand.
     * </pre>
     *
     * @param classNode transformed class node
     * @param name      transformed class name
     */
    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            if (method.name.equals("guiOptionsInit"))
            {
                for (final AbstractInsnNode INSN : method.instructions.toArray())
                {
                    if (INSN instanceof IntInsnNode && INSN.getOpcode() == BIPUSH
                            && ((IntInsnNode) INSN).operand == 104)
                    {
                        method.instructions.set(INSN, new IntInsnNode(BIPUSH, 0));
                    }
                }
            }
        }
    }

}