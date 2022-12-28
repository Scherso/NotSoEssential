package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.RETURN;

public class ConnectionTransformer implements ITransformer
{

	@Override
	public String getClassName()
	{
		return ("gg.essential.network.connectionmanager.Connection");
	}

	/**
	 * Attempting to remove Essential's ability to make an external connection.
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
				case "send":
				case "attemptConnect":
				case "attemptConnect$1385ff":
				case "doAttemptConnect":
				case "retryConnectWithBackoff":
					this.clearInstructions(method);
					method.instructions.insert(new InsnNode(RETURN));
					break;
			}
		}
	}

}
