package com.amikomgamedev.ulartangga.multiplayer.bluetooth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.UUID;

import org.anddev.andengine.util.Debug;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.widget.Toast;

import com.amikomgamedev.ulartangga.states.State_Menu_Main;

public class BT_Server_Client 	extends State_Menu_Main
{
	private BluetoothAdapter mBluetoothAdapter;
	
	private static final int REQUEST_CONNECT_DEVICE_SECURE 		= 1;
	private static final int REQUEST_CONNECT_DEVICE_INSECURE 	= 2;
	private static final int REQUEST_ENABLE_BT 					= 3;
	
	private static final UUID MY_UUID_INSECURE =
	    UUID.fromString("0044e310-a6f5-4e22-af6d-3ce38eed3d63");
	
	//Name for the SDP record when creating server socket
	
	private static final String NAME_INSECURE = "UlarTanggaBluetoothInsecure";
	
	private static AcceptThread mAcceptThread;
	private static ConnectThread mConnectThread;

	
	public void bluetoothMode() {
		// TODO Auto-generated method stub
		super.bluetoothMode();
		
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		
		if (mBluetoothAdapter == null)
		{			
			toast("Your device does not support bluetooth");
		}
		else
		{
	        makeModeDialog();
		        
	        if (!mBluetoothAdapter.isEnabled())
	        {
	            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
			}
		}
	}

	private void makeModeDialog()
	{
		AlertDialog.Builder modeDialog = new AlertDialog.Builder(this);

		modeDialog.setTitle("Bluetooth");
		
		modeDialog.setPositiveButton("Server", new OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
            	
            	toast("Waiting Client...");
				mAcceptThread=new AcceptThread();
				mAcceptThread.start();
				
			}
		});

		modeDialog.setNegativeButton("Client", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				Intent serverIntent = new Intent(BT_Server_Client.this, BluetoothListDevicesActivity.class);
		         startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_INSECURE);
		      
			}
		});

		modeDialog.show();
	}


	
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(resultCode == Activity.RESULT_OK && data != null)
		{	
			String address = data.getExtras()
		        .getString(BluetoothListDevicesActivity.EXTRA_DEVICE_ADDRESS);
			
			BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
			
			mConnectThread = new ConnectThread(device);
			mConnectThread.start();
		}
	}
	
	
	protected void onDestroy() {
//		stopBTThread();
		
		super.onDestroy();
	}
	
	public void stopBTThread()
	{
		if(mConnectThread!=null)
		{
			mConnectThread.cancel();
			mConnectThread.destroy();
		}
		if(mAcceptThread!=null)
		{
			mAcceptThread.cancel();
		}
		
	}
	
	private OutputStream 	mOutputStream;
	private PrintWriter 	mPrintWriterOUT;
	private BufferedReader 	mBufferedReader; 
	
	private static BluetoothSocket mSocket;
	
	private void initBT()
	{
		try {
				mBufferedReader=new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
				mOutputStream=mSocket.getOutputStream();
				mPrintWriterOUT=new PrintWriter(mOutputStream);
			} catch (UnknownHostException e) {
				
				Toast.makeText(this, "UNKNOWN SERVER ADDRESS", 100).show();
			} catch (IOException e) {
				
				Toast.makeText(this, "ERROR CREATING SOCKET", 100).show();
			}
		
		
		 
	}
	
	
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
	
	
	public void sendMessage(String str) {

		mPrintWriterOUT.println(str);
		mPrintWriterOUT.flush();
	}
	
	public static void setBluetoothSocket(BluetoothSocket _BTSocket)
	{
		mSocket=_BTSocket;
	}
	
	
	private class AcceptThread extends Thread
	{
	    private final BluetoothServerSocket mmServerSocket;
	 
	    public AcceptThread()
	    {
	        BluetoothServerSocket tmp = null;
	        
	        try
	        {
	            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME_INSECURE, MY_UUID_INSECURE);
	        }
	        catch (IOException e) { }
	        
	        mmServerSocket = tmp;
	    }
	 
	    public void run()
	    {
	        BluetoothSocket socket = null;
	        
	        while (true)
	        {
	            try
	            {
	                socket = mmServerSocket.accept();

	                setBluetoothSocket(socket);
		        	BT_Send_Received.setBluetoothSocket(socket);
		        	initBT();
		    		startReceiverThread();
		    		isServer = true;
		        	switchState(STATE_MENU_SELECT_MC_MULTI_SERVER);
//	                Intent i=new Intent(BT_Server_Client.this,BT_Send_Received.class);
//	                finish();
//	                startActivity(i);
	            }
	            catch (Exception e)
	            {
	            	e.printStackTrace();
	                break;
	            }
	            // If a connection was accepted
	            if (socket != null)
	            {
	                // Do work to manage the connection (in a separate thread)
	                //manageConnectedSocket(socket);
	                try {
						mmServerSocket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                break;
	            }
	        }
	    }
	    
	    public void cancel()
	    {
	        try
	        {
	            mmServerSocket.close();
	        } 
	        catch (IOException e) { }
	    }
	}

	private class ConnectThread extends Thread
	{
	    private final BluetoothSocket mmSocket;
	    private final BluetoothDevice mmDevice;
	 
	    public ConnectThread(BluetoothDevice device) {
	        // Use a temporary object that is later assigned to mmSocket,
	        // because mmSocket is final
	        BluetoothSocket tmp = null;
	        mmDevice = device;
	 
	        // Get a BluetoothSocket to connect with the given BluetoothDevice
	        try {
	            // MY_UUID is the app's UUID string, also used by the server code
	            tmp = device.createRfcommSocketToServiceRecord(MY_UUID_INSECURE);
	        } catch (IOException e) { }
	        mmSocket = tmp;
	    }
	 
	    public void run() {
	        // Cancel discovery because it will slow down the connection
	        mBluetoothAdapter.cancelDiscovery();
	 
	        try {
	            // Connect the device through the socket. This will block
	            // until it succeeds or throws an exception
	            Debug.d("connecting socket");
	            
	        	mmSocket.connect();
	        
	        	setBluetoothSocket(mmSocket);
	        	BT_Send_Received.setBluetoothSocket(mmSocket);
	        	initBT();
	    		startReceiverThread();
	    		isServer = false;
//	        	Intent i=new Intent(BT_Server_Client.this,BT_Send_Received.class);
//	        	finish();
//	        	startActivity(i);
	        	
	        	switchState(STATE_MENU_SELECT_MC_MULTI_SERVER);
	            
	            
	        } catch (IOException connectException) {
	            // Unable to connect; close the socket and get out
	            try {
	                mmSocket.close();
	            } catch (IOException closeException) { }
	            return;
	        }
	 
	        // Do work to manage the connection (in a separate thread)
	       // manageConnectedSocket(mmSocket);
	    }
	 
	    /** Will cancel an in-progress connection, and close the socket */
	    public void cancel() {
	        try {
	            mmSocket.close();
	        } catch (IOException e) { }
	    }
	}
}
