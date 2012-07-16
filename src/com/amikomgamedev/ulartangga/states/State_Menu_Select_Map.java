package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.input.touch.detector.ScrollDetector;
import org.anddev.andengine.input.touch.detector.SurfaceScrollDetector;
import org.anddev.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;

import com.amikomgamedev.ulartangga.Data;
import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Utils;

public class State_Menu_Select_Map implements 	IScrollDetectorListener,
												IOnSceneTouchListener
{	
	private Scene scene;
	private ScrollDetector scrollDetector;
	
	public State_Menu_Select_Map(Scene scene) 
	{
		this.scene = scene;
	}
	
	public void stateMenuSelectMap()
	{		
		try
		{
			scene.attachChild(Game.spr_Img_Select_Map_Bg);
			for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
			{
				scene.getLastChild().attachChild(Game.spr_Img_Select_Map_Icon_Map[i]);
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
		}
		
		scene.setOnSceneTouchListener(this);
		
		scrollDetector = new SurfaceScrollDetector(this);
		scrollDetector.setEnabled(true);
	}

	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		scrollDetector.onTouchEvent(pSceneTouchEvent);
		Utils.TRACE("Scene TOuch " +pSceneTouchEvent.getPointerID());
		return false;
	}

	public void onScroll(ScrollDetector pScollDetector, TouchEvent pTouchEvent,
			float pDistanceX, float pDistanceY) {
		Utils.TRACE(""+pDistanceX);
		Utils.TRACE("Scroll");
	}

}
