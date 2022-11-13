package com.github.u9g.notsoessential.plugin;

import com.github.u9g.notsoessential.asm.ClassTransformer;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.TransformerExclusions({"com.github.u9g.notsoessential.asm.transformer"})
public class FMLPlugin implements IFMLLoadingPlugin {

    /**
     * Mutable array list that is assigned in values in `injectData`
     */
    static List<Object> coremodList;

    /**
     * Check for runtime deobfuscation.
     */
    static Boolean isObfuscated;

    /**
     * To return a list of classes that implement ITransformer.
     *
     * @return list of classes that implement a transformer interface
     */
    @NotNull
    @Override
    public String[] getASMTransformerClass() {
        return (new String[]{ClassTransformer.class.getName()});
    }

    /**
     * Return a class name that implements "ModContainer" for injection into the mod list.
     *
     * @return null
     */
    @Nullable
    @Override
    public String getModContainerClass() {
        return (null);
    }

    /**
     * Return the class name of an implementor of "IFMLCallHook", that will be run, in the main thread.
     *
     * @return null
     */
    @Nullable
    @Override
    public String getSetupClass() {
        return (null);
    }

    /**
     * Inject coremod data into this coremod This data includes:
     * "coremodList" : the list of coremods
     * "isObfuscatedEnvironment" : whether the environment is obfuscated or not
     *
     * @param map list of string and object
     */
    @SuppressWarnings("unchecked")
    @Override
    public void injectData(Map<String, Object> map) {
        coremodList = (List<Object>) map.get("coremodList");
        isObfuscated = (Boolean) map.get("runtimeDeobfuscationEnabled");
    }

    /**
     * Return an optional access transformer class for this coremod.
     *
     * @return null
     */
    @Nullable
    @Override
    public String getAccessTransformerClass() {
        return (null);
    }

}
