package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
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
	 * <pre>
	 *     This method is used to transform the OnboardingData class.
	 *     Main function: Disable the ability to accept Essential's terms
	 *     of service, and in doing so, deny the terms of service as-well.
	 *
	 *     FOR REFERENCE:
	 *     {@link org.objectweb.asm.Opcodes#IRETURN} return an int from a method,
	 *     in this case, functions in transformed classes returning the following:
	 *     {@link org.objectweb.asm.Opcodes#ICONST_0} false
	 *     {@link org.objectweb.asm.Opcodes#ICONST_1} true
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
