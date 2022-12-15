package com.github.u9g.notsoessential.asm.transformer;

import com.github.u9g.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.*;

import static org.objectweb.asm.Opcodes.*;

public class EssentialModelRendererTransformer implements ITransformer
{

	@Override
	public final String getClassName()
	{
		return ("gg.essential.cosmetics.EssentialModelRenderer");
	}

	/**
	 * <pre>
	 *     This method is used to transform the EssentialModelRenderer class.
	 *     Method finds and modifies methods that render cosmetics.
	 *     Procedural methods are returned at head,
	 *     Most functions are returned false.
	 *     The main function of this procedure is to cancel the rendering of
	 *     cosmetics.
	 *
	 *     FOR REFERENCE:
	 *     {@link org.objectweb.asm.tree.VarInsnNode} is a node that represents a local variable instruction
	 *     that loads and stores the value of a local variable.
	 *     {@link org.objectweb.asm.Opcodes#DSTORE} stores a double into a local variable.
	 * </pre>
	 *
	 * @param classNode transformed class node
	 * @param name      transformed class name
	 * @see org.objectweb.asm.tree.VarInsnNode
	 */
	@Override
	public void transform(ClassNode classNode, String name)
	{
		for (MethodNode method : classNode.methods)
		{
			switch (method.name)
			{
				case "cosmeticsShouldRender":
					for (final AbstractInsnNode INSN : method.instructions.toArray())
					{
						/* See 'FOR REFERENCE' comment above for clarification. */
						if (INSN instanceof VarInsnNode && INSN.getOpcode() == DSTORE)
						{
							method.instructions.insertBefore(INSN.getNext(), this.createInsnList(
									new InsnNode(ICONST_0),
									new InsnNode(IRETURN)
							));
							method.instructions.remove(INSN);
						}
					}
					break;
				case "render":
				case "doRenderLayer":
					this.clearInstructions(method);
					method.instructions.insert(new InsnNode(RETURN));
					break;
			}
		}
	}

}
