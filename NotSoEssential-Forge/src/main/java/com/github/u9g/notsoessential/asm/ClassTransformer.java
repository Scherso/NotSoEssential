package com.github.u9g.notsoessential.asm;

import com.github.u9g.notsoessential.asm.transformer.*;
import net.minecraft.launchwrapper.IClassTransformer;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassTransformer implements IClassTransformer
{

	/**
	 * Create a JVM flag to dump transformed classes. Usable by adding -DNSE.debugBytecode=true to JVM arguments.
	 */
	public static final boolean DUMP_BYTECODE = Boolean.parseBoolean(System.getProperty("NSE.debugBytecode", "false"));

	/**
	 * List of transformers to be registered later,
	 *
	 * @associates com.github.u9g.notsoessential.asm.ITransformer
	 * @see HashMap
	 */
	private final HashMap<String, List<ITransformer>> TRANSFORMER_HASHMAP = new HashMap<>();
	
	/**
	 * Local mneumonic to mark the end of a list when iterating
	 * in a reverse order.
	 */
	static final int EOL = -1;

	public ClassTransformer()
	{
		registerTransformer(new CommandConfigTransformer());
		registerTransformer(new ConnectionManagerTransformer());
		registerTransformer(new ConnectionTransformer());
		registerTransformer(new EssentialConfigTransformer());
		registerTransformer(new EssentialModelRendererTransformer());
		registerTransformer(new EssentialMultiplayerGuiTransformer());
		registerTransformer(new GuiOptionsEditorTransformer());
		registerTransformer(new OnboardingDataTransformer());
		registerTransformer(new OnlineIndicatorTransformer());
		registerTransformer(new PauseMenuDisplayTransformer());
	}

	private void registerTransformer(@NotNull ITransformer transformer)
	{
		final List<ITransformer> transformer_list = TRANSFORMER_HASHMAP.get(transformer.getClassName());
		if (transformer_list == null)
		{
			final List<ITransformer> new_list = new ArrayList<>();
			new_list.add(transformer);
			TRANSFORMER_HASHMAP.put(transformer.getClassName(), new_list);
		} else
			transformer_list.add(transformer);
	}

	@Override
	public byte[] transform(final String name, final String transformedName, byte[] bytes)
	{
		if (bytes == null) return (null);
		final List<ITransformer> transformer_list = TRANSFORMER_HASHMAP.get(transformedName);
		if (transformer_list == null) return (bytes);

		for (int i = transformer_list.size() - 1; i > EOL; i--)             /* For statement here matches a for-each, ex:          */
		{                                                                   /* for (ITransformer transformer : TRANSFORMER_LIST) { */
			final ITransformer transformer = transformer_list.get(i);       /*     transformer.transform(node, transformedName);   */
			final ClassNode    cn          = new ClassNode();               /* }                                                   */
			final ClassReader  cr          = new ClassReader(bytes);        /* Statement is written differently for performance.   */
			cr.accept(cn, 0);
			transformer.transform(cn, transformedName);
			final ClassWriter cw = new ClassWriter(cr, 0);
			cn.accept(cw);

			if (DUMP_BYTECODE)
				this.dumpBytes(transformedName, cw);

			bytes = cw.toByteArray();
		}
		return (bytes);
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	private void dumpBytes(String name, ClassWriter writer)
	{
		try
		{
			name = (name.contains("$")) ? name.replace('$', '.') + ".class" : name + ".class";

			final File bytecode_dir = new File(".bytecode.out");
			if (!bytecode_dir.exists()) bytecode_dir.mkdirs();

			final File bytecode_out = new File(bytecode_dir, name);
			if (!bytecode_out.exists()) bytecode_out.createNewFile();

			FileOutputStream stream = new FileOutputStream(bytecode_out);
			stream.write(writer.toByteArray());
			stream.close();
		} catch (Exception ex)
		{
			System.out.println("Failed to dump bytecode for " + name);
			ex.printStackTrace();
		}
	}

}
