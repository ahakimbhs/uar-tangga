package com.amikomgamedev.ulartangga.multiplayer.bluetooth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.util.Debug;

import android.bluetooth.BluetoothSocket;
import android.widget.Toast;

import com.amikomgamedev.ulartangga.states.State_Gameplay_MP_BT;

public class BT_Send_Received extends State_Gameplay_MP_BT
{
	private OutputStream 	mOutputStream;
	private PrintWriter 	mPrintWriterOUT;
	private BufferedReader 	mBufferedReader; 
	
	private static BluetoothSocket mSocket;
	
	@Override
	public Scene onLoadScene() {
		initBT();
		return super.onLoadScene();
	}
	
	private void initBT()
	{
		try {
				mBufferedReader=new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
				mOutputStream=mSocket.getOutputStream();
				mPrintWriterOUT=new PrintWriter(mOutputStream);
			} catch (UnknownHostException e) {
				
				Toast.makeText(BT_Send_Received.this, "UNKNOWN SERVER ADDRESS", 100).show();
			} catch (IOException e) {
				
				Toast.makeText(BT_Send_Received.this, "ERROR CREATING SOCKET", 100).show();
			}
		
		
		 
	}
	
	@Override
	public String receiveMessage()
	{		
		String receivedMessage;
		try {
			receivedMessage = new String(mBufferedReader.readLine()+ "\n");
			receivedMessage.trim();
			return receivedMessage;
		} catch (IOException e) {
			Debug.d("error reading stream");
			isRunning=false;
		}

		return null;
		
	}
	
	@Override
	public void sendMessage(String str) {
		mPrintWriterOUT.println(str);
		mPrintWriterOUT.flush();
	}
	
	public static void setBluetoothSocket(BluetoothSocket _BTSocket)
	{
		mSocket=_BTSocket;
	}


	
	

}