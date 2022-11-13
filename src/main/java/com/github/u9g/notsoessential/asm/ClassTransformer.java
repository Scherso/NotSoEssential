package com.github.u9g.notsoessential.asm;

import com.github.u9g.notsoessential.asm.transformer.GuiOptionsEditorTransformer;
import com.github.u9g.notsoessential.asm.transformer.PauseMenuDisplayTransformer;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

public class ClassTransformer implements IClassTransformer {

    /**
     * Create a JVM flag to dump transformed classes.
     * Usable by adding `-DdebugBytecode=true` to JVM arguments.
     */
    public static final boolean dumpBytecode = Boolean.parseBoolean(System.getProperty("debugBytecode", "false"));

    /**
     * Create a multimap of transformers with Google's multimap library.
     */
    private final Multimap<String, ITransformer> transformerMultimap = ArrayListMultimap.create();

    public ClassTransformer() {
        registerTransformer(new GuiOptionsEditorTransformer());
        registerTransformer(new PauseMenuDisplayTransformer());
    }

    private void registerTransformer(ITransformer transformer) {
        /* Iterating through class names.
         * Mapping the class names in `transformerMultimap`. */
        for (String className : transformer.getClassName())
            transformerMultimap.put(className, transformer);
    }

    /**
     * Transform according to Minecraft's launchwrapper.
     *
     * @param name            non-transformed class name
     * @param transformedName transformed class name
     * @param bytes           bytecode to be returned
     * @return result of ClassWriter
     */
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null) return null;

        /* Collecting a possible list of transformers. */
        Collection<ITransformer> transformers = transformerMultimap.get(transformedName);
        /* If the list is empty,
         * The JVM will not attempt to run through the transformation process. */
        if (transformers.isEmpty())
            return bytes;

        /* Creating instances of classes. */
        ClassReader reader = new ClassReader(bytes);
        ClassNode node = new ClassNode();
        reader.accept(node, ClassReader.EXPAND_FRAMES);

        /* For every transformer, perform a transformation. */
        for (ITransformer transformer : transformers)
            transformer.transform(node, transformedName);

        /* Calling an instance of ClassWriter with the intention of flagging to imply computeMax.
         * Flag to automatically compute the stack map frames of methods from scratch.
         * If this flag is set, then the calls to the MethodVisitor.visitFrame method are ignored,
         * and the stack map frames are recomputed from the methods bytecode.
         *
         * see ClassWriter(int) */
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        try {
            node.accept(writer);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        dumpBytes(transformedName, writer);

        /* Return the newly written bytes and finalize any transformation process. */
        return (writer.toByteArray());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void dumpBytes(String name, ClassWriter writer) {
        if (!dumpBytecode) return;

        try {
            name = (name.contains("$")) ? name.replace('$', '.') + ".class" : name + ".class";

            File bytecodeDirectory = new File(".bytecode.out");
            if (!bytecodeDirectory.exists()) bytecodeDirectory.mkdirs();

            File bytecodeOutput = new File(bytecodeDirectory, name);
            if (!bytecodeOutput.exists()) bytecodeOutput.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(bytecodeOutput);
            outputStream.write(writer.toByteArray());
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
