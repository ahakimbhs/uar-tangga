package com.amikomgamedev.ulartangga;

import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.content.Context;

import com.chartboost.sdk.ChartBoost;


public class Chart_Boost
{
	private BaseGameActivity 	m_Activity;
    private ChartBoost 			cb;
//
//    private String	m_App_Id = "505ede3d17ba47a95e00001b";
//    private String	m_App_Signature	= "01cdff332dd8fbcb2c67f3dbae3f73fee60dd5da";
    
    private String	m_App_Id = "50518e8416ba47a511000044";
    private String	m_App_Signature	= "87b3b0790447117b94402e5e691787de052891a0";

    public Chart_Boost(Context p_Context)
    {
    	m_Activity = (BaseGameActivity) p_Context;
	}
    
    public void createChartBoost()
    {
        cb = ChartBoost.getSharedChartBoost(m_Activity);
        cb.setAppId(m_App_Id);
    	cb.setAppSignature(m_App_Signature);
        cb.install();
	}
    
    public void showChartBoost()
    {
    	cb.showInterstitial();
	}
}
