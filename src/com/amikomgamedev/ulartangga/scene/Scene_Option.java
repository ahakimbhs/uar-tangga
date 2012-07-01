package com.amikomgamedev.ulartangga.scene;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.os.Debug;
import android.view.KeyEvent;

import com.amikomgamedev.ulartangga.Game;
import com.amikomgamedev.ulartangga.Loading;
import com.amikomgamedev.ulartangga.states.State_MainMenu;

public class Scene_Option extends State_MainMenu {
	private Scene mScene;
	private BaseGameActivity activity;
	private final static int STATE_OPEN = 0;
	private final static int STATE_LOAD = 1;
	private final static int STATE_END = 2;
	private static int State_Game_Current			= 0;
	public static float second;
	private Text mText;
	
	public Scene option(BaseGameActivity activity, Engine mEngine) {
		mEngine.registerUpdateHandler(new FPSLogger());
		mEngine.registerUpdateHandler(this);
		mScene = new Scene();
		switchStatte(STATE_OPEN);			
		this.activity = activity;
		return mScene;
	}


	private void switchStatte(int state) {
		State_Game_Current = state;
		switch (State_Game_Current) {
		case STATE_OPEN:
			mScene.setBackground(new ColorBackground(0, 0, 0));
			Loading.setLoading(Loading.LOADING_TYPE_MAIN_MENU);
		
			break;
		case STATE_LOAD:
			mScene.attachChild(Game.spr_Img_Back_Menu);
			Loading.setLoading(Loading.LOADING_TYPE_MAIN_MENU);
			break;
		case STATE_END:
			mText = new Text(120, 10, Game.font[1], "Option");
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
