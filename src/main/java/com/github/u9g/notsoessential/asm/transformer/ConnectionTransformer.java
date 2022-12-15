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
	 * Remove Essential's ability to make an external connection.
	 *
	 * <p> FOR REFERENCE:
	 * <p> "()V" will describe a method as void, meaning it's a procedure.
	 *
	 * @param classNode transformed class node
	 * @param name      transformed class name
	 */
	@Override
	public void transform(ClassNode classNode, String name)
	{
		for (MethodNode method : classNode.methods)
		{
			if (method.desc.equals("()V"))
			{
				this.clearInstructions(method);
				method.instructions.insert(new InsnNode(RETURN));
			}
		}
	}

}
