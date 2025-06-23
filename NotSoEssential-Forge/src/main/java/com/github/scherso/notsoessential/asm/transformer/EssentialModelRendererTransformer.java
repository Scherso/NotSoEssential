package com.github.scherso.notsoessential.asm.transformer;

import com.github.scherso.notsoessential.asm.ITransformer;
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
     * @param classNode transformed class node
     * @param name      transformed class name
     * @see org.objectweb.asm.tree.VarInsnNode
     */
    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            if (method.name.equals("cosmeticsShouldRender"))
            {
                this.clearInstructions(method);
                method.instructions.insert(this.createInsnList(
                    new InsnNode(ICONST_0),
                    new InsnNode(IRETURN)
                ));
            }
        }
    }

}
