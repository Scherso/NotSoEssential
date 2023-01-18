package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.*;

public class ConnectionManagerTransformer implements ITransformer
{

	@Override
	public String getClassName()
	{
		return ("gg.essential.network.connectionmanager.ConnectionManager");
	}

	/**
	 * <pre>
	 *     This method is used to cancel any connection attempts to an external
	 *     server, ie. Telemetry. {@link OnboardingDataTransformer} is used to deny
	 *     the user from accepting the TOS, which should stop any telemetry by
	 *     Essential. This procedure is used to confirm that the client does not
	 *     make any attempts at an external connection as a 'just incase' thing.
	 *
	 *     FOR REFERENCE:
	 *     {@link org.objectweb.asm.Opcodes#ICONST_0} in this use; false.
	 *     {@link org.objectweb.asm.Opcodes#ICONST_1} in this use; true.
	 *     {@link org.objectweb.asm.Opcodes#IRETURN} return an int from a method.
	 *     "()V" will describe a method as void, meaning it's a procedure.
	 *     "()Z" will describe a method as boolean, meaning it's a function that
	 *     returns a boolean.
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
			/* !method.name.equals("<init>") condition
			 * ensures that the constructor's instructions
			 * are not cleared, as it will cause a crash. */
			if (method.desc.endsWith("V") && !method.name.equals("<init>"))
			{
				/* Taking every procedure,
				 * clearing its instruction and
				 * returning it at the head of
				 * the method. */
				this.clearInstructions(method);
				method.instructions.insert(new InsnNode(RETURN));
			}

			if (method.desc.endsWith("Z"))
			{
				/* Taking every single function
				 * and, clearing its instructions,
				 * and returning 'false' at the head. */
				this.clearInstructions(method);
				method.instructions.insert(this.createInsnList(
					new InsnNode(ICONST_0),
					new InsnNode(IRETURN)
				));
			}
		}
	}

}
