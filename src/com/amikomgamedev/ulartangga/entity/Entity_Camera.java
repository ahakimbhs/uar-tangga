package com.amikomgamedev.ulartangga.entity;

import org.anddev.andengine.engine.camera.Camera;

import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.states.State_Gameplay;

public class Entity_Camera implements Define
{
	private Camera camera;
	
	private float	minX, 
					maxX,
					minY,
					maxY;
	
	public Entity_Camera(
			Camera camera,
			float minX, 
			float maxX,
			float minY,
			float maxY) 
	{
		this.camera	= camera;
		this.minX	= minX;
		this.maxX	= maxX;
		this.minY	= minY;
		this.maxY	= maxY;
		
		camera.setCenter(
				minX - camera.getWidth() / 2,
				maxY + camera.getHeight() / 2);
	}
	
	public void backCameraToMap()
	{
		if(camera.getMinX() < minX)
			camera.setCenter(
					minX + camera.getWidth() / 2, 
					camera.getCenterY());
		
		if(camera.getMaxX() > maxX)
			camera.setCenter(
					maxX - camera.getWidth() / 2, 
					camera.getCenterY());

		if(camera.getMinY() < minY)
			camera.setCenter(
					camera.getCenterX(),
					minY + camera.getHeight() / 2);

		if(camera.getMaxY() > maxY)
			camera.setCenter(
					camera.getCenterX(), 
					maxY - camera.getHeight() / 2);
	}
	
	public void moveCamera(
			float disX, 
			float disY)
	{
		if(camera.getMinX() > minX
				&& disX < 0)
			camera.offsetCenter(disX, 0);
		
		if(camera.getMaxX() < maxX
				&& disX > 0)
			camera.offsetCenter(disX, 0);
		
		if(camera.getMinY() > minY
				&& disY < 0)
			camera.offsetCenter(0, disY);
		
		if(camera.getMaxY() < maxY
				&& disY > 0)
			camera.offsetCenter(0, disY);
	}
	
	public void autoMoveCamera(
			Entity_Mc mc,
			float dis)
	{
		float mcX1 	= mc.getAnimatedSprite().getX();
		float mcX2	= mc.getAnimatedSprite().getX() + mc.getAnimatedSprite().getWidth();
		float velX	= mc.getVelocityX();
		
		float mcY1 	= mc.getAnimatedSprite().getY();
		float mcY2	= mc.getAnimatedSprite().getY() + mc.getAnimatedSprite().getHeight();
		float velY	= mc.getVelocityY();
		
		// codingan masih jelek
		// fungsi pada method backCameraToMap()
		// muncul lagi disini
		
		if(velX > 0)
			if(mcX1 - dis < minX)
				camera.setCenter(
						minX + camera.getWidth() / 2, 
						camera.getCenterY());
//			else if(camera.getMaxX() >= maxX && mcX1 > camera.getMinX())
			else if(mcX1 - dis + camera.getWidth() > maxX)
				camera.setCenter(
						maxX - camera.getWidth() / 2, 
						camera.getCenterY());
			else
				camera.setCenter(
						mcX1 - dis + camera.getWidth() / 2,
						camera.getCenterY());
		
		if(velX < 0)
			if(mcX2 + dis > maxX)
				camera.setCenter(
						maxX - camera.getWidth() / 2, 
						camera.getCenterY());
//			else if(camera.getMinX() <= minX && mcX2 < camera.getMaxX())
			else if(mcX2 + dis - camera.getWidth() < minX)
				camera.setCenter(
						minX + camera.getWidth() / 2, 
						camera.getCenterY());
			else
				camera.setCenter(
						mcX2 + dis - camera.getWidth() / 2,
						camera.getCenterY());
		
		if(velX != 0 && velY == 0)
			if(mcY2 + dis > maxY)
				camera.setCenter(
						camera.getCenterX(), 
						maxY - camera.getHeight() / 2);
			else if(mcY2 + dis - camera.getHeight() < minY)
				camera.setCenter(
						camera.getCenterX(),
						minY + camera.getHeight() / 2);
			else
				camera.setCenter(
						camera.getCenterX(),
						mcY2 + dis - camera.getHeight() / 2);
		
		
		// PR copas pengecekan atas ke pengecekan bawah
		if(velY > 0)
			if(mcY1 - dis < minY)
				camera.setCenter(
						camera.getCenterX(),
						minY + camera.getHeight() / 2);
			else if(mcY1 - dis + camera.getHeight() > maxY)
				camera.setCenter(
						camera.getCenterX(),
						maxY - camera.getHeight() / 2);
			else
				camera.setCenter(
						camera.getCenterX(),
						mcY1 - dis + camera.getHeight() / 2);
		
		if(velY < 0)
			if(mcY2 + dis > maxY)
				camera.setCenter(
						camera.getCenterX(),
						maxY - camera.getHeight() / 2);
			else if(mcY2 + dis - camera.getHeight() < minY)
				camera.setCenter(
						camera.getCenterX(),
						minY + camera.getHeight() / 2);
			else
				camera.setCenter(
						camera.getCenterX(),
						mcY2 + dis - camera.getHeight() / 2);
		
		if(velX == 0 && velY != 0)
			if(mcX2 + dis > maxX)
				camera.setCenter( 
						maxX - camera.getWidth() / 2,
						camera.getCenterY());
			else if(mcX2 + dis - camera.getWidth() < minX)
				camera.setCenter(
						minX + camera.getWidth() / 2,
						camera.getCenterY());
			else
				camera.setCenter(
						mcX2 + dis - camera.getWidth() / 2,
						camera.getCenterY());
		
		State_Gameplay.moveCamera = false;
		
	}
}
