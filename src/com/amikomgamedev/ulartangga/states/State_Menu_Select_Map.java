package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.entity.scene.Scene;

import com.amikomgamedev.ulartangga.Data;
import com.amikomgamedev.ulartangga.Game;

public class State_Menu_Select_Map
{
	private Scene scene;

	public State_Menu_Select_Map(Scene scene)
	{
		this.scene = scene;
	}
	
	public void stateMenuSelectMap()
	{
		scene.attachChild(Game.spr_Img_Select_Map_Bg);
		for(int i = 0; i < Data.IMG_INGAME_BACKGROUND_MAP.length; i++)
		{
			Game.spr_Img_Select_Map_Bg.attachChild(Game.spr_Img_Select_Map_Icon_Map[i]);
		}
	}
}
