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
     * To return a list of classes that implement ITransformer.
     *
     * @return list of classes that implement a transformer interface
     */
    @NotNull
    @Override
    public String[] getASMTransformerClass()
    {
        return (new String[]{ClassTransformer.class.getName()});
    }

    /**
     * Return a class name that implements "ModContainer" for injection into the mod list.
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
     * Return the class name of an implementor of "IFMLCallHook", that will be run, in the main thread.
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