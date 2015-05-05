package com.aod6060.helper;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class WindowManager {
	
	private GLFWErrorCallback errorCallback;
	
	private long windowID;
	
	private String caption;
	
	private int width;
	
	private int height;
	
	private WindowListener windowListener;
	
	public void init(String caption, int width, int height, WindowListener windowListener) throws Exception {
		this.caption = caption;
		this.width = width;
		this.height = height;
		this.windowListener = windowListener;
		
		// Initializing GLFW for the window
		this.errorCallback = Callbacks.errorCallbackPrint(System.err);
		GLFW.glfwSetErrorCallback(this.errorCallback);
		
		if(GLFW.glfwInit() != GL11.GL_TRUE) {
			throw new Exception("GLFW didn't create for some reason.");
		}
		
		GLFW.glfwDefaultWindowHints();
		
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_FALSE);
		
		this.windowID = GLFW.glfwCreateWindow(this.width, this.height, this.caption, 0, 0);
		
		GLFW.glfwSetWindowPos(this.windowID, 256, 256);
		
		GLFW.glfwMakeContextCurrent(this.windowID);
		
		GLFW.glfwShowWindow(this.windowID);
		
		GLContext.createFromCurrent();
		
		if(this.windowListener != null) {
			windowListener.init();
		}
	}
	


	public void loop() {
		
		while(GLFW.glfwWindowShouldClose(this.windowID) == GL11.GL_FALSE) {
			if(this.windowListener != null) {
				windowListener.update();
			}
			
			
			GLFW.glfwSwapBuffers(this.windowID);
			GLFW.glfwPollEvents();
		}
	}
	
	public void release() {
		
		if(this.windowListener != null) {
			windowListener.release();
		}
		
		windowListener = null;
		
		// TODO Auto-generated method stub
		GLFW.glfwDestroyWindow(this.windowID);
		GLFW.glfwTerminate();
		errorCallback.release();
	}	
	
}
