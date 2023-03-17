package com.github.u9g.notsoessential.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@SuppressWarnings("UnresolvedMixinReference")
@Mixin(targets = "gg.essential.commands.impl.CommandConfig", remap = false)
public class CommandConfigMixin
{

	/**
	 * Removes the users ability to execute the '/essential' command.
	 * Instead, the command will output 'no' into the chat.
	 *
	 * @param ci {@link CallbackInfo}
	 */
	@Inject(method = "handle()V", at = @At("HEAD"), cancellable = true, remap = false)
	private void nse$handle(final CallbackInfo ci)
	{
		if (MinecraftClient.getInstance().player != null)
			MinecraftClient.getInstance().player.sendMessage(Text.of("§7[§cNot §aSo §9Essential§7] §rno"));
		ci.cancel();
	}

}
