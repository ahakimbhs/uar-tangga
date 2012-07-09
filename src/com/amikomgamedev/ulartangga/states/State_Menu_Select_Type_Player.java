package com.amikomgamedev.ulartangga.states;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.input.touch.TouchEvent;

import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.sax.StartElementListener;
import android.text.style.BackgroundColorSpan;

import com.amikomgamedev.ulartangga.Game;

public class State_Menu_Select_Type_Player{
	private Text mText2, mText3, mText4;

	public Scene select_Player_Type(){
		Scene mScene = new Scene();
		mScene.setBackground(new ColorBackground(1, 1, 1));
		mText2 = new Text(100, 10,Game.font[1], "Chose Player");
		mText3 =new Text(50, 200,Game.font[2], "Sinngle Player"){
		@Override
		public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
				float pTouchAreaLocalX, float pTouchAreaLocalY) {
//			startActivity(new Intent(State_Menu_Select_Type_Player.this, State_Gameplay.class));
			return true;
							}	
		};
		
		mScene.attachChild(mText2);
		mScene.attachChild(mText3);
		mScene.registerTouchArea(mText3);
//		mScene.attachChild(Game.spr_Img_Back_Menu);
		return mScene;
				
	}

}
