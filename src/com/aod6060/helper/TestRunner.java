package com.aod6060.helper;

import java.lang.reflect.InvocationTargetException;

public class TestRunner implements WindowListener {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		WindowManager man = new WindowManager();
		
		try {
			man.init("Test", 800, 600, new TestRunner());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		man.loop();
		
		man.release();
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
	}

	@Override
	public void release() {
	}
}
