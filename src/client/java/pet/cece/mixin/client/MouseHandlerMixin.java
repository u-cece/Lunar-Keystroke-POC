package pet.cece.mixin.client;

import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pet.cece.LunarKeystrokePOCClient;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @Inject(at = @At("HEAD"), method = "onPress", cancellable = true)
    public void onPress(long window, int button, int action, int mods, CallbackInfo ci) {
        if (LunarKeystrokePOCClient.isMaskingInputEvents()) {
            ci.cancel();
            return;
        }
    }
}
