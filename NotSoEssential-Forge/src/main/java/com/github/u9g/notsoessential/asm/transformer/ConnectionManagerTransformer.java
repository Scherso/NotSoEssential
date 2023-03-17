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
	 * Attempts to cancel any external connections made via Essential itself,
	 * i.e. telemetry. <br>
	 * Although {@link com.github.u9g.notsoessential.asm.transformer.OnboardingDataTransformer}
	 * already attempts to deny the terms of service, which in turn would
	 * *hopefully* prevent any external connections, this is a failsafe, and
	 * confirms that there is absolutely no external connections made.
	 *
	 * <ul>
	 *     <li> {@link org.objectweb.asm.Opcodes#ICONST_0} Pushes a constant 0 value
	 *     onto the stack, in a boolean context, the value is 'false'. </li>
	 *     <li> {@link org.objectweb.asm.Opcodes#ICONST_1} Pushes a constant 1 value
	 *     onto the stack, in a boolean context, the value if 'true'. </li>
	 *     <li> {@link org.objectweb.asm.Opcodes#IRETURN} Returns an integer value
	 *     from a method. </li>
	 *     <li> "()V" Describes a method as void, making it a procedure. </li>
	 *     <li> "()Z" Describes a method as function which returns a boolean. </li>
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
