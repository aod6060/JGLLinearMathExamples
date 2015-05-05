package com.aod6060.math.examples;

import org.lwjgl.opengl.GL11;

import com.aod6060.helper.WindowListener;
import com.aod6060.helper.WindowManager;
import com.aod6060.math.LinearMath;
import com.aod6060.math.Mat4f;
import com.aod6060.math.Transforms;
import com.aod6060.math.Util;
import com.aod6060.math.Vec3f;



/**
 * This is a basic example to test my library with 
 * lwjgl 3
 * @author Fred
 *
 */
public class BasicExample implements WindowListener {
	
	// Projection Matrix
	private Mat4f proj;
	
	// This is for rotations
	private float rot = 0.0f;
	
	public static void main(String[] args)  {
		
		WindowManager man = new WindowManager();
		
		
		try {
			man.init("JGLLinearMath: Basic Example", 800, 600, new BasicExample());
			
			man.loop();
			
			man.release();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
		proj = Transforms.perspective(Util.toRadian(45.0f), 800.0f/600.0f, 1.0f, 1024.0f);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		rot += 0.1f;
		
		if(rot > 360.0f) {
			rot -= 360.0f;
		}
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glMultMatrixf(Util.toFloatBuffer(proj));
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		GL11.glPushMatrix();
		Mat4f t = Transforms.translate(0.0f, 0.0f, -5.0f);
		Mat4f r = Transforms.rotate(Util.toRadian(rot), 1.0f, 1.0f, 1.0f);
		Mat4f s = Transforms.scale(0.5f, 0.5f, 0.5f);
		
		Mat4f world = LinearMath.mul(s, LinearMath.mul(r, t));
		
		GL11.glMultMatrixf(Util.toFloatBuffer(world));
		
		GL11.glBegin(GL11.GL_TRIANGLES);
		
		GL11.glColor3f(1.0f, 0.0f, 0.0f);
		GL11.glVertex3f(-1.0f, 1.0f, 0.0f);
		GL11.glColor3f(0.0f, 1.0f, 0.0f);
		GL11.glVertex3f(1.0f, 1.0f, 0.0f);
		GL11.glColor3f(0.0f, 0.0f, 1.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 0.0f);
		
		GL11.glColor3f(0.0f, 0.0f, 1.0f);
		GL11.glVertex3f(-1.0f, -1.0f, 0.0f);
		GL11.glColor3f(0.0f, 1.0f, 0.0f);
		GL11.glVertex3f(1.0f, 1.0f, 0.0f);
		GL11.glColor3f(1.0f, 1.0f, 0.0f);
		GL11.glVertex3f(1.0f, -1.0f, 0.0f);
		GL11.glEnd();
		GL11.glPopMatrix();
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

}
