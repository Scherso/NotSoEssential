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
     * Create a JVM flag to dump transformed classes.
     * Usable by adding `-DNSE.debugBytecode=true` to JVM arguments.
     */
    public static final boolean dumpBytecode = Boolean.parseBoolean(System.getProperty("NSE.debugBytecode", "false"));

    /**
     * Create a hashmap of transformers.
     */
    private final HashMap<String, List<ITransformer>> TRANSFORMER_HASHMAP = new HashMap<>();

    public ClassTransformer()
    {
        registerTransformer(new GuiOptionsEditorTransformer());
        registerTransformer(new PauseMenuDisplayTransformer());
        registerTransformer(new EssentialModelRendererTransformer());
        registerTransformer(new EssentialConfigTransformer());
    }

    private void registerTransformer(ITransformer transformer) {
        final List<ITransformer> LIST = this.TRANSFORMER_HASHMAP.get(transformer.getClassName());
        if (LIST == null)
        {
            final List<ITransformer> NEW_LIST = new ArrayList<>();
            NEW_LIST.add(transformer);
            this.TRANSFORMER_HASHMAP.put(transformer.getClassName(), NEW_LIST);
        }
        else
        {
            LIST.add(transformer);
        }
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        if (bytes == null) return null;
        final List<ITransformer> TRANSFORMER_LIST = this.TRANSFORMER_HASHMAP.get(transformedName); if (TRANSFORMER_LIST == null) return (bytes);
        for (final ITransformer TRANSFORMER : TRANSFORMER_LIST)
        {
            final ClassNode   NODE   = new ClassNode();
            final ClassReader READER = new ClassReader(bytes);
            READER.accept(NODE, ClassReader.EXPAND_FRAMES);
            TRANSFORMER.transform(NODE, transformedName);
            final ClassWriter WRITER = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

            try
            {
                NODE.accept(WRITER);
            }
            catch (Throwable throwable)
            {
                throwable.printStackTrace();
            }

            if (dumpBytecode) this.dumpBytes(transformedName, WRITER);
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

            File bytecodeDirectory = new File(".bytecode.out");
            if (!bytecodeDirectory.exists()) bytecodeDirectory.mkdirs();

            File bytecodeOutput = new File(bytecodeDirectory, name);
            if (!bytecodeOutput.exists()) bytecodeOutput.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(bytecodeOutput);
            outputStream.write(writer.toByteArray());
            outputStream.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}