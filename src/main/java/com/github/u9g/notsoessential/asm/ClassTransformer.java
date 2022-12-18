package com.github.u9g.notsoessential.asm;

import com.github.u9g.notsoessential.asm.transformer.*;
import net.minecraft.launchwrapper.IClassTransformer;
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
	 * @see HashMap
	 */
	private final HashMap<String, List<ITransformer>> TRANSFORMER_HASHMAP = new HashMap<>();

	public ClassTransformer()
	{
		registerTransformer(new CommandConfigTransformer());
		registerTransformer(new ConnectionManagerTransformer());
		registerTransformer(new ConnectionTransformer());
		registerTransformer(new EssentialConfigTransformer());
		registerTransformer(new EssentialModelRendererTransformer());
		registerTransformer(new GuiOptionsEditorTransformer());
		registerTransformer(new OnboardingDataTransformer());
		registerTransformer(new OnlineIndicatorTransformer());
		registerTransformer(new PauseMenuDisplayTransformer());
	}

	private void registerTransformer(ITransformer transformer)
	{
		final List<ITransformer> LIST = this.TRANSFORMER_HASHMAP.get(transformer.getClassName());
		if (LIST == null)
		{
			final List<ITransformer> NEW_LIST = new ArrayList<>();
			NEW_LIST.add(transformer);
			this.TRANSFORMER_HASHMAP.put(transformer.getClassName(), NEW_LIST);
		} else
			LIST.add(transformer);
	}

	@Override
	public byte[] transform(final String name, final String transformedName, byte[] bytes)
	{
		if (bytes == null) return (null);
		final List<ITransformer> TRANSFORMER_LIST = this.TRANSFORMER_HASHMAP.get(transformedName);
		if (TRANSFORMER_LIST == null) return (bytes);

		for (int i = TRANSFORMER_LIST.size() - 1; i > -1; i--)
		{
			final ITransformer TRANSFORMER = TRANSFORMER_LIST.get(i);
			final ClassNode    NODE        = new ClassNode();
			final ClassReader  READER      = new ClassReader(bytes);
			READER.accept(NODE, 0);
			TRANSFORMER.transform(NODE, transformedName);
			final ClassWriter WRITER = new ClassWriter(READER, 0);
			NODE.accept(WRITER);

			if (DUMP_BYTECODE)
				this.dumpBytes(transformedName, WRITER);

			bytes = WRITER.toByteArray();
		}
		return (bytes);
	}

	@SuppressWarnings("ResultOfMethodCallIgnored")
	private void dumpBytes(String name, ClassWriter writer)
	{
		try
		{
			name = (name.contains("$")) ? name.replace('$', '.') + ".class" : name + ".class";

			final File BYTECODE_DIR = new File(".bytecode.out");
			if (!BYTECODE_DIR.exists()) BYTECODE_DIR.mkdirs();

			final File BYTECODE_OUT = new File(BYTECODE_DIR, name);
			if (!BYTECODE_OUT.exists()) BYTECODE_OUT.createNewFile();

			FileOutputStream stream = new FileOutputStream(BYTECODE_OUT);
			stream.write(writer.toByteArray());
			stream.close();
		} catch (Exception ex)
		{
			System.out.println("Failed to dump bytecode for " + name);
			ex.printStackTrace();
		}
	}

}
