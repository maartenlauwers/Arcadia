package engine;

import java.io.InputStream;

import org.newdawn.slick.Font;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Config {

	// SINGLETON? 
	
	private static int screenWidth;
	private static int screenHeight;
	private static TrueTypeFont font1;
	private static TrueTypeFont font2;
	
	//private Core instance;
	
	private static TextureManager textureManager;	
	
	public Config(int screenWidth, int screenHeight, TextureManager textureManager) {
		Config.screenWidth = screenWidth;
		Config.screenHeight = screenHeight; 
		Config.textureManager = textureManager;
		
		try {
            InputStream oi = ResourceLoader
                             .getResourceAsStream("black.ttf");
            java.awt.Font f = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, oi);
            font1 = new TrueTypeFont(f.deriveFont(16f), true);
            
            f = new java.awt.Font("Arial", java.awt.Font.BOLD, 12);
            font2 = new TrueTypeFont(f.deriveFont(12f), true);
        } catch (Exception e) {
            System.out.println("Failed to load font");
        } 
		
	}
	
	public static int getScreenWidth() {
		return screenWidth;
	}
	
	public static int getScreenHeight() {
		return screenHeight;
	}
	
	public static TextureManager getTextureManager() {
		return textureManager;
	}
	
	public static TrueTypeFont getFont1() {
		return font1;
	}
	
	public static TrueTypeFont getFont2() {
		return font2;
	}
}
