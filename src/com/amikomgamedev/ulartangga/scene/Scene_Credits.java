package com.amikomgamedev.ulartangga.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.KeyEvent;


import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Loading;
import com.amikomgamedev.ulartangga.states.State_MainMenu;

public class Scene_Credits extends State_MainMenu{
	private Scene mScene;
	private final static int STATE_OPEN = 0;
	private final static int STATE_LOAD = 1;
	private final static int STATE_END = 2;
	private static int State_Game_Current			= 0;
	public static float second;
	private Text mText;
	
	public Scene credits(BaseGameActivity activity, Engine mEngine) {
		mEngine.registerUpdateHandler(new FPSLogger());
		mEngine.registerUpdateHandler(this);
		mScene = new Scene();
		switchStatte(STATE_OPEN);			
		return mScene;
	}


	private void switchStatte(int state) {
		State_Game_Current = state;
		switch (State_Game_Current) {
		case STATE_OPEN:
			Loading.setLoading(Loading.LOADING_TYPE_MAIN_MENU);
			break;
		case STATE_LOAD:
			mScene.attachChild(Game.spr_Img_Back_Menu);
			Loading.setLoading(Loading.LOADING_TYPE_MAIN_MENU);
			break;
		case STATE_END:
			float x = (320 - Game.font[1].getTexture().getWidth()) / 2; 
			mText = new Text(x, 10, Game.font[1], "Credit");
			mScene.attachChild(mText);
			break;
		}	
	}


	public void onUpdate(float pSecondsElapsed) {
		second = pSecondsElapsed;
		switch (State_Game_Current) {
		case STATE_OPEN:
			if(Loading.isLoading()){
				Loading.updateLoading();
			}
			else {
				switchStatte(STATE_LOAD);
			}
			break;

		case STATE_LOAD:
			if(Loading.isLoading()){
				Loading.updateLoading();
			}
			else {
				switchStatte(STATE_END);
			}
			break;
		}
		
	}


	public void reset() {
		
	}
	public boolean onKeyDown(int keykode, KeyEvent event) {
		if ((keykode == KeyEvent.KEYCODE_BACK)) {
	
			mEngine.setScene(sceneMenu());
	   }
		  return super.onKeyDown(keykode, event);
	}
	
}
