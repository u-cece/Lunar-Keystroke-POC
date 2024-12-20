package pet.cece.mixin.client;

import net.minecraft.client.KeyboardHandler;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.windows.INPUT;
import org.lwjgl.system.windows.User32;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pet.cece.LunarKeystrokePOCClient;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {
    @Inject(at = @At("HEAD"), method = "keyPress", cancellable = true)
    public void keyPress(long glfwHandle, int key, int scancode, int action, int mods, CallbackInfo ci) {
        if (key == LunarKeystrokePOCClient.MASKING_SWITCH_GLFW_KEY) {
            if (action == GLFW.GLFW_PRESS)
                LunarKeystrokePOCClient.setMaskingKeyEvents(true);
            if (action == GLFW.GLFW_RELEASE)
                LunarKeystrokePOCClient.setMaskingKeyEvents(false);
        }
        if (LunarKeystrokePOCClient.isMaskingKeyEvents()) {
            ci.cancel();
            return;
        }

        // below is for testing
        if (key == GLFW.GLFW_KEY_APOSTROPHE) {
            INPUT.Buffer buffer = INPUT.create(3);

            INPUT switchDown = buffer.get(0);
            switchDown.type(User32.INPUT_KEYBOARD);
            switchDown.DUMMYUNIONNAME_ki()
                    .wVk(LunarKeystrokePOCClient.MASKING_SWITCH_NATIVE_KEY);

            INPUT switchUp = buffer.get(2);
            switchUp.type(User32.INPUT_KEYBOARD);
            switchUp.DUMMYUNIONNAME_ki()
                    .wVk(LunarKeystrokePOCClient.MASKING_SWITCH_NATIVE_KEY)
                    .dwFlags(User32.KEYEVENTF_KEYUP);

            INPUT wDown = buffer.get(1);
            wDown.type(User32.INPUT_KEYBOARD);

            if (action == GLFW.GLFW_PRESS) {
                wDown.DUMMYUNIONNAME_ki()
                        .wVk((short) 'W');
            }
            if (action == GLFW.GLFW_RELEASE) {
                wDown.DUMMYUNIONNAME_ki()
                        .wVk((short) 'W')
                        .dwFlags(User32.KEYEVENTF_KEYUP);

            }

            LunarKeystrokePOCClient.sendNativeInputs(buffer);
        }
    }
}
