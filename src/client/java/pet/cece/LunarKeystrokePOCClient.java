package pet.cece;

import net.fabricmc.api.ClientModInitializer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.windows.INPUT;
import org.lwjgl.system.windows.User32;

public class LunarKeystrokePOCClient implements ClientModInitializer {

	// this key essentially acts as a switch for whether we should ignore key events
	public static final int MASKING_SWITCH_GLFW_KEY = GLFW.GLFW_KEY_M;
	public static final short MASKING_SWITCH_NATIVE_KEY = 'M';

	private static boolean maskingInputEvents = false;

	public static void setMaskingInputEvents(boolean maskingKeyEvents) {
		LunarKeystrokePOCClient.maskingInputEvents = maskingKeyEvents;
	}

	public static boolean isMaskingInputEvents() {
		return maskingInputEvents;
	}

	public static void sendNativeInputs(INPUT.Buffer inputs) {
		User32.SendInput(inputs, INPUT.SIZEOF);
	}

	@Override
	public void onInitializeClient() {
	}
}