package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
// import org.objectweb.asm.tree.InsnNode;
// import org.objectweb.asm.tree.MethodNode;

// import static org.objectweb.asm.Opcodes.RETURN;

public class EssentialMultiplayerGuiTransformer implements ITransformer
{

    @Override
    public String getClassName()
    {
        return ("gg.essential.gui.multiplayer.EssentialMultiplayerGui");
    }


    /**
     * Transforming Essential's Multiplayer GUI, cancelling the method.
     *
     * @param classNode transformed class node
     * @param name      transformed class name
     */
    @Override
    public void transform(ClassNode classNode, String name)
    {
//      for (MethodNode method : classNode.methods)
//      {
//          if (method.name.equals("initGui"))
//              method.instructions.insert(new InsnNode(RETURN));
//      }
    }

}
