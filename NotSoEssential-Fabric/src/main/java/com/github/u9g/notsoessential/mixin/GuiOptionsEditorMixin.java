package com.github.u9g.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.gui.GuiOptionsEditor", remap = false)
public class GuiOptionsEditorMixin
{

	@Inject(method = {
			"guiOptionsInit(Lgg/essential/event/gui/InitGuiEvent;)V"
	}, at = @At("HEAD"), cancellable = true, remap = false)
	private void nse$guiOptionsInit(final CallbackInfo ci)
	{
		ci.cancel();
	}

}
