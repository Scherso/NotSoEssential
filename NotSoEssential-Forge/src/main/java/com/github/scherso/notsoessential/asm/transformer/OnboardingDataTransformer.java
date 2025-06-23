package com.github.scherso.notsoessential.asm.transformer;

import com.github.scherso.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.*;

public class OnboardingDataTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.data.OnboardingData");
    }

    /**
     * Attempts to transform the OnboardingData class. <br> Disables the ability to accept Essential's terms of service,
     * and in doing so, will theoretically prevent any telemetry data from being harvested by Essential.
     *
     * <ul>
     *     <li> {@link org.objectweb.asm.Opcodes#ICONST_0} Pushes a constant 0 value
     *     onto the stack; in a boolean context, the value is 'false'. </li>
     *     <li> {@link org.objectweb.asm.Opcodes#ICONST_1} Pushes a constant 1 value
     *     onto the stack, in a boolean context, the value if 'true'. </li>
     *     <li> {@link org.objectweb.asm.Opcodes#IRETURN} Returns an integer value
     *     from a method. </li>
     * </ul>
     *
     * @param classNode transformed class node
     * @param name      transformed class name
     */
    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            switch (method.name)
            {
                case "hasAcceptedTos":
                case "hasAcceptedEssentialTOS":
                    this.clearInstructions(method);
                    /* Inserting the following
                     * ICONST_0 - false
                     * IRETURN  - return int value
                     * this meaning return false. */
                    method.instructions.insert(this.createInsnList(
                        new InsnNode(ICONST_0),
                        new InsnNode(IRETURN)
                    ));
                    break;
                case "hasDeniedTos":
                case "hasDeniedEssentialTOS":
                    this.clearInstructions(method);
                    /* Inserting the following:
                     * ICONST_1 - true
                     * IRETURN  - return int value
                     * this meaning return true. */
                    method.instructions.insert(this.createInsnList(
                        new InsnNode(ICONST_1),
                        new InsnNode(IRETURN)
                    ));
                    break;
            }
        }
    }

}
