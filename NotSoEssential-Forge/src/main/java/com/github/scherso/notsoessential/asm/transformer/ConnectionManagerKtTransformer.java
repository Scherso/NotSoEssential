package com.github.scherso.notsoessential.asm.transformer;

import com.github.scherso.notsoessential.asm.ITransformer;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import static org.objectweb.asm.Opcodes.RETURN;

public class ConnectionManagerKtTransformer implements ITransformer
{

    @Override
    public final String getClassName()
    {
        return ("gg.essential.network.connectionmanager.ConnectionManagerKt");
    }

    /**
     * Prevents Essential's connection loop from ever being launched. <br> {@code ConnectionManagerKt} is the abstract
     * parent of {@link ConnectionManagerTransformer the ConnectionManager}, and owns the coroutine which repeatedly
     * attempts to reach Essential's infrastructure.
     *
     * <p> The loop is built in the constructor as a <i>lazy</i> job, </p>
     * <pre>{@code connectLoopJob = scope.launch(context, CoroutineStart.LAZY) { connectLoop() }}</pre>
     * <p> and {@code start} is its only trigger, being called once from {@code Essential.start}. Returning {@code start}
     * at the head therefore leaves the job forever unstarted, and {@code connectLoop} never runs. </p>
     *
     * <p> This supersedes relying on {@link OnboardingDataTransformer} to stall the loop. Up until Essential 1.4.1.1,
     * {@code connectLoop} awaited a one-shot {@code TosAcceptedEvent} which, with the terms of service denied, would
     * never fire, parking the coroutine indefinitely. It now awaits the {@code OnboardingData.acceptedTos} state
     * instead, and that state is read from Essential's <i>global</i> onboarding file rather than from the patched
     * {@code hasAcceptedTos}. Where the two disagree the await returns immediately, spinning the loop on the client
     * thread and hanging the game before it can finish starting. </p>
     *
     * <ul>
     *     <li> {@link org.objectweb.asm.Opcodes#RETURN} Returns from a method,
     *     yielding no value. </li>
     *     <li> "()V" Describes a method as void, making it a procedure. </li>
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
            /* method.desc.equals("()V") condition ensures
             * that only the no-argument procedure is taken,
             * leaving any future overload of the same name
             * untouched. */
            if (method.name.equals("start") && method.desc.equals("()V"))
            {
                this.clearInstructions(method);
                method.instructions.insert(new InsnNode(RETURN));
            }
        }
    }

}
