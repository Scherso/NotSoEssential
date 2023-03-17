package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.*;

public class EssentialConfigTransformer implements ITransformer
{

	@Override
	public final String getClassName()
	{
		return ("gg.essential.config.EssentialConfig");
	}

	/**
	 * Attempting to transform the EssentialConfig class. Any configuration options
	 * local to this class will be transformed according to their 'usefulness', or
	 * lack thereof. <br>
	 * <p> Configurations modified, </p>
	 * <ul>
	 *     <li> {@code getShowEssentialIndicatorOnTab} as false. </li>
	 *     <li> {@code getShowEssentialIndicatorOnNametag} as false. </li>
	 *     <li> {@code getEssentialEnabled} as false. </li>
	 *     <li> {@code getEssentialFull} as false. </li>
	 *     <li> {@code getDisableCosmetics} as true. </li>
	 *     <li> {@code getDisableAllNotifications} as true. </li>
	 * </ul>
	 * <p> Reference, </p>
	 * <ul>
	 *     <li> {@link org.objectweb.asm.Opcodes#ICONST_0} Pushes a constant 0 value
	 *     onto the stack, in a boolean context, the value is 'false'. </li>
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
				case "getShowEssentialIndicatorOnTab":
				case "getShowEssentialIndicatorOnNametag":
				case "getEssentialEnabled":
				case "getEssentialFull":
					this.clearInstructions(method);
					method.instructions.insert(this.createInsnList(
						/* Inserting the following
						 * ICONST_0 - false
						 * IRETURN  - return int value
						 * this meaning return false. */
						new InsnNode(ICONST_0),
						new InsnNode(IRETURN)
					));
					break;
				case "getDisableCosmetics":
				case "getDisableAllNotifications":
					this.clearInstructions(method);
					method.instructions.insert(this.createInsnList(
						/* Inserting the following:
						 * ICONST_1 - true
						 * IRETURN  - return int value
						 * this meaning return true. */
						new InsnNode(ICONST_1),
						new InsnNode(IRETURN)
					));
					break;
			}
		}
	}

}
