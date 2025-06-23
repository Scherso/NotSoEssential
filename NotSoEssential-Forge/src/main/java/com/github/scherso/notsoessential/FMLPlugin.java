package com.github.scherso.notsoessential;

import com.github.scherso.notsoessential.asm.ClassTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@IFMLLoadingPlugin.Name("FMLPlugin")
@IFMLLoadingPlugin.TransformerExclusions({"com.github.scherso.notsoessential.asm.transformer"})
public class FMLPlugin implements IFMLLoadingPlugin
{

    /**
     * @return {@link String[]} of classes registered by {@link ClassTransformer}
     */
    @NotNull
    @Override
    public final String[] getASMTransformerClass()
    {
        return (new String[] {ClassTransformer.class.getName()});
    }

    /**
     * Return a class name that implements {@link net.minecraftforge.fml.common.ModContainer}
     * for injection into the mod list.
     *
     * @return null
     */
    @Nullable
    @Override
    public final String getModContainerClass()
    {
        return (null);
    }

    /**
     * Return the class name of an implementor of {@link net.minecraftforge.fml.relauncher.IFMLCallHook}
     * that will be run, in the main thread.
     *
     * @return null
     */
    @Nullable
    @Override
    public final String getSetupClass()
    {
        return (null);
    }

    /**
     * @param map list of string and object
     */
    @Override
    public void injectData(Map<String, Object> map)
    {
        /* Hello! */
    }

    /**
     * Return an optional access transformer class for this coremod.
     *
     * @return null
     */
    @Nullable
    @Override
    public final String getAccessTransformerClass()
    {
        return (null);
    }

}
