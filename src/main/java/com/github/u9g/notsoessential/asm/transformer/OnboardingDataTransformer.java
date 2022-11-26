package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.IRETURN;

public class OnboardingDataTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.data.OnboardingData");
    }

    /**
     * <pre>
     *     This method is used to transform the OnboardingData class.
     *
     *     FOR REFERENCE:
     *     {@link org.objectweb.asm.Opcodes#IRETURN} return an int from a method,
     *     in this case, functions in transformed classes returning the following:
     *     {@link org.objectweb.asm.Opcodes#ICONST_0} false
     *     {@link org.objectweb.asm.Opcodes#ICONST_1} true
     *
     *     Exert from hasDeniedTos()Z:
     *     LINE A 44 ...
     *     INVOKESTATIC java/lang/Boolean.valueOf(Z)Ljava/lang/Boolean;
     *     INVOKESTATIC kotlin/jvm/internal/Intrinsics.areEqual(Ljava/lang/Object;Ljava/lang/Object;)Z
     *     IRETURN
     *     We're grabbing the instruction IRETURN, and replacing it with our own
     *     instruction, marked below.
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
            switch (method.name)
            {
                case "hasAcceptedTos":
                case "hasAcceptedEssentialTOS":
                    for (final AbstractInsnNode INSN : method.instructions.toArray())
                        if (INSN.getOpcode() == IRETURN)
                        {
                            /* Replacing IRETURN instruction with our own. */
                            method.instructions.insertBefore(INSN, this.functionReturnFalse());
                            method.instructions.remove(INSN);
                        }
                    break;
                case "hasDeniedTos":
                case "hasDeniedEssentialTOS":
                    for (final AbstractInsnNode INSN : method.instructions.toArray())
                        if (INSN.getOpcode() == IRETURN)
                        {
                            /* Replacing IRETURN instruction with our own. */
                            method.instructions.insertBefore(INSN, this.functionReturnTrue());
                            method.instructions.remove(INSN);
                        }
                    break;
            }
        }
    }

}
