package com.github.scherso.notsoessential.asm.transformer;

import com.github.scherso.notsoessential.asm.ITransformer;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import static org.objectweb.asm.Opcodes.*;

public class ChatComponentSerializerTransformer implements ITransformer
{

    /* Used to find the compiled serialize method, its name and first argument are obfuscated. */
    private static final String SERIALIZE_TAIL =
        ";Ljava/lang/reflect/Type;Lcom/google/gson/JsonSerializationContext;)Lcom/google/gson/JsonElement;";

    @Override
    public String getClassName()
    {
        return ("net.minecraft.util.IChatComponent$Serializer");
    }

    /**
     * Runs the component through {@link com.github.scherso.notsoessential.ChatComponentSanitizer}
     * at the head of {@code serialize}, fixing the crash with LabyMod on Essential's UTextComponent.
     *
     * <p> The class is still obfuscated when this runs, so {@code serialize} is found by its
     * descriptor instead of its name. </p>
     *
     * @param classNode transformed class node
     * @param name      transformed class name
     */
    @Override
    public void transform(ClassNode classNode, String name)
    {
        for (MethodNode method : classNode.methods)
        {
            if (!method.desc.startsWith("(L") || !method.desc.endsWith(SERIALIZE_TAIL)) continue;

            /* Skip the extra (Object, ...) overload the compiler adds, it just calls this one. */
            final String component = Type.getArgumentTypes(method.desc)[0].getInternalName();
            if (component.equals("java/lang/Object")) continue;

            method.instructions.insert(this.createInsnList(
                new VarInsnNode(ALOAD, 1),
                new MethodInsnNode(
                    INVOKESTATIC,
                    "com/github/scherso/notsoessential/ChatComponentSanitizer",
                    "sanitize",
                    "(L" + component + ";)L" + component + ";",
                    false
                ),
                new VarInsnNode(ASTORE, 1)
            ));
        }
    }

}
