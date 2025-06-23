package com.github.scherso.notsoessential.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.cosmetics.EssentialModelRenderer", remap = false)
public class EssentialModelRendererMixin
{

    @Inject(method = "cosmeticsShouldRender(Lnet/minecraft/class_1657;)Z", at = @At("TAIL"), cancellable = true, remap = false)
    private static void nse$cosmeticsShouldRender(final CallbackInfoReturnable<Boolean> clr)
    {
        clr.setReturnValue(false);
    }

//	@Inject(method = {
//			"render$ad45216(Lgg/essential/universal/UMatrixStack;Lgg/essential/model/backend/RenderBackend$VertexConsumerProvider;Ljava/util/Set;Lnet/minecraft/class_742;)V",
//			"render$195d0902(Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;ILnet/minecraft/class_742;)V"
//	}, at = @At("HEAD"), cancellable = true, remap = false)
//	private static void nse$render(final CallbackInfo ci)
//	{
//		ci.cancel();
//	}

}
