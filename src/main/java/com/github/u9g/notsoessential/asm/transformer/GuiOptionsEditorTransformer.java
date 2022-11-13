package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.RETURN;

public class GuiOptionsEditorTransformer implements ITransformer {

    @Override
    public String[] getClassName() {
        return (new String[]{"gg.essential.gui.GuiOptionsEditor"});
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods)
            if (methodNode.name.equals("guiOptionsInit"))
                methodNode.instructions.insertBefore(methodNode.instructions.getFirst(), new InsnNode(RETURN));
    }

}
