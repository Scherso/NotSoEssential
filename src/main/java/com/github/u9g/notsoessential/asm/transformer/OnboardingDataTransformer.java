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
     *     We're clearing these instructions, and replacing it with our own.
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
                    /* Clearing method instructions, all instructions being local variables in this case.*/
                    method.localVariables.clear();
                    /* Adding the following bytecode
                     * ICONST_0
                     * IRETURN
                     * this meaning return false. */
                    method.instructions.insert(this.functionReturnFalse());
                    break;
                case "hasDeniedTos":
                case "hasDeniedEssentialTOS":
                    /* Clearing method instructions, all instructions being local variables in this case.*/
                    method.localVariables.clear();
                    /* Adding the following bytecode
                     * ICONST_1
                     * IRETURN
                     * this meaning return true. */
                    method.instructions.insert(this.functionReturnTrue());
                    break;
            }
        }
    }

}
