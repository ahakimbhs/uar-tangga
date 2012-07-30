package com.amikomgamedev.ulartangga.entity;

import org.anddev.andengine.engine.camera.Camera;

import com.amikomgamedev.ulartangga.Define;
import com.amikomgamedev.ulartangga.states.State_Gameplay;

public class Entity_Camera implements Define
{
	private Camera camera;

	private float moveX = 0;
	private float moveY = 0;
	
	private float	minX, 
					maxX,
					minY,
					maxY;

	private boolean visX = false;
	private boolean visY = false;
	
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
	
	public void reset()
	{
		
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
			float disX)
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
			if(mcX1 - disX < minX)
				camera.setCenter(
						minX + camera.getWidth() / 2, 
						camera.getCenterY());
			else if(mcX1 - disX + camera.getWidth() > maxX)
				camera.setCenter(
						maxX - camera.getWidth() / 2, 
						camera.getCenterY());
			else
				camera.setCenter(
						mcX1 - disX + camera.getWidth() / 2,
						camera.getCenterY());
		
		if(velX < 0)
			if(mcX2 + disX > maxX)
				camera.setCenter(
						maxX - camera.getWidth() / 2, 
						camera.getCenterY());
			else if(mcX2 + disX - camera.getWidth() < minX)
				camera.setCenter(
						minX + camera.getWidth() / 2, 
						camera.getCenterY());
			else
				camera.setCenter(
						mcX2 + disX - camera.getWidth() / 2,
						camera.getCenterY());
		
		if(velX != 0 && velY == 0)
			if(mcY2 + disX > maxY)
				camera.setCenter(
						camera.getCenterX(), 
						maxY - camera.getHeight() / 2);
			else if(mcY2 + disX - camera.getHeight() < minY)
				camera.setCenter(
						camera.getCenterX(),
						minY + camera.getHeight() / 2);
			else
				camera.setCenter(
						camera.getCenterX(),
						mcY2 + disX - camera.getHeight() / 2);
		
		if(velY > 0)
			if(mcY1 - disX < minY)
				camera.setCenter(
						camera.getCenterX(),
						minY + camera.getHeight() / 2);
			else if(mcY1 - disX + camera.getHeight() > maxY)
				camera.setCenter(
						camera.getCenterX(),
						maxY - camera.getHeight() / 2);
			else
				camera.setCenter(
						camera.getCenterX(),
						mcY1 - disX + camera.getHeight() / 2);
		
		if(velY < 0)
			if(mcY2 + disX > maxY)
				camera.setCenter(
						camera.getCenterX(),
						maxY - camera.getHeight() / 2);
			else if(mcY2 + disX - camera.getHeight() < minY)
				camera.setCenter(
						camera.getCenterX(),
						minY + camera.getHeight() / 2);
			else
				camera.setCenter(
						camera.getCenterX(),
						mcY2 + disX - camera.getHeight() / 2);
		
		if(velX == 0 && velY != 0)
			if(mcX2 + disX > maxX)
				camera.setCenter(
						maxX - camera.getWidth() / 2,
						camera.getCenterY());
			else if(mcX2 + disX - camera.getWidth() < minX)
				camera.setCenter(
						minX + camera.getWidth() / 2,
						camera.getCenterY());
			else
				camera.setCenter(
						mcX2 + disX - camera.getWidth() / 2,
						camera.getCenterY());
		
		State_Gameplay.moveCamera = false;
	}

	public void autoMoveCameraToNextPlayer(
			Entity_Mc mcNext,
			int dis)
	{		
		float mcNextX = mcNext.getAnimatedSprite().getX();
		float mcNextY = mcNext.getAnimatedSprite().getY() + mcNext.getAnimatedSprite().getHeight();
		
		int speed = 8;
		float distance = 0;
		
		moveX++;
		
		if((mcNext.Posisi_Mc_Current / COLUMN_COUNT) % 2 == 0)
			distance = dis;
		else
			distance = camera.getWidth() - dis - mcNext.getAnimatedSprite().getWidth();
		
		if(mcNextX - distance + camera.getWidth()> camera.getMaxX() + moveX / 3 * 2
				&& camera.getMaxX() < maxX)
		{
			visX = false;
			
			if(moveX >= speed)
				moveX = speed;
			
			camera.offsetCenter(moveX, 0);
		}
		else if(mcNextX - distance < camera.getMinX() - moveX / 3 * 2
				&& camera.getMinX() > minX)
		{
			visX = false;
			
			if(moveX >= speed)
				moveX = speed;
			
			camera.offsetCenter(-moveX, 0);
		}
		else
		{			
			visX = true;
			
			moveX = 0;
		}
		
		moveY++;

		if(mcNextY + dis > camera.getMaxY() + moveY / 3 * 2
				&& camera.getMaxY() < maxY)
		{
			visY = false;
			
			if(moveY >= speed)
				moveY = speed;
			
			camera.offsetCenter(0, moveY);
		}
		else if(mcNextY + dis - camera.getHeight() < camera.getMinY() - moveY / 3 * 2
				&& camera.getMinY() > minY)
		{
			visY = false;
			
			if(moveY >= speed)
				moveY = speed;

			camera.offsetCenter(0, -moveY);
		}
		else
		{
			visY = true;
			
			moveY = 0;
		}
		
		if(visX && visY)
		{
			State_Gameplay.spr_Img_Botton_Dice.setVisible(true);
			State_Gameplay.diceEnable = true;
		}
		else
		{
			State_Gameplay.spr_Img_Botton_Dice.setVisible(false);
			State_Gameplay.diceEnable = false;
		}
	}
}
