package com.github.u9g.notsoessential;

import com.github.u9g.notsoessential.asm.ClassTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@IFMLLoadingPlugin.Name("FMLPlugin")
@IFMLLoadingPlugin.TransformerExclusions({"com.github.u9g.notsoessential.asm.transformer"})
public class FMLPlugin implements IFMLLoadingPlugin
{

    /**
     * @return list of classes registered by {@link ClassTransformer}
     */
    @NotNull
    @Override
    public String[] getASMTransformerClass()
    {
        return (new String[] {ClassTransformer.class.getName()});
    }

    /**
     * Return a class name that implements {@link net.minecraftforge.fml.common.ModContainer} for injection into the mod list.
     *
     * @return null
     */
    @Nullable
    @Override
    public String getModContainerClass()
    {
        return (null);
    }

    /**
     * Return the class name of an implementor of {@link net.minecraftforge.fml.relauncher.IFMLCallHook} that will be run, in the main thread.
     *
     * @return null
     */
    @Nullable
    @Override
    public String getSetupClass()
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
    public String getAccessTransformerClass()
    {
        return (null);
    }

}