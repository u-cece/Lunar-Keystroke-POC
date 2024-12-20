package pet.cece;

import net.fabricmc.api.ClientModInitializer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.windows.INPUT;
import org.lwjgl.system.windows.User32;

public class LunarKeystrokePOCClient implements ClientModInitializer {

	// this key essentially acts as a switch for whether we should ignore key events
	public static final int MASKING_SWITCH_GLFW_KEY = GLFW.GLFW_KEY_M;
	public static final short MASKING_SWITCH_NATIVE_KEY = 'M';

	private static boolean maskingKeyEvents = false;

	public static void setMaskingKeyEvents(boolean maskingKeyEvents) {
		LunarKeystrokePOCClient.maskingKeyEvents = maskingKeyEvents;
	}

	public static boolean isMaskingKeyEvents() {
		return maskingKeyEvents;
	}

	public static void sendNativeInputs(INPUT.Buffer inputs) {
		User32.SendInput(inputs, INPUT.SIZEOF);
	}

	@Override
	public void onInitializeClient() {
	}
}