package com.example.explorer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;

public class MainActivity extends Activity {
	TextView tv;
	private Socket socket;
	LocationManager lm;
	String text = "adsf";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.banner);
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0,
				//locationListener);
		//lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,
				//locationListener);
		tv.setText(text);
		
	}
	
	protected void onStart(){
		super.onStart();
		run();
	}
	public void run() {
		try {
			InetAddress serverAddr = InetAddress.getByName("192.168.1.195");
			text= text +"bb";
			tv.setText(text);
			socket = new Socket(serverAddr, 10000);
			text= text +"aa";
			tv.setText(text);
			Gson packet = new Gson();

			PrintWriter out;
			BufferedReader input;
			try {
				text= text +"aa";
				tv.setText(text);
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())),
						true);
				input = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				while (true) {
					Thread.sleep(400);
					Location location = lm
							.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					double lat = location.getLatitude();
					double lng = location.getLongitude();
					Coordinate c = new Coordinate(lat, lng);
					String json = packet.toJson(c);
					out.print(json);
					String read = input.readLine();

					// analysis
					if (read.startsWith("Die")) {
						tv.setText(read);
						finish();
					} else if (read.startsWith("Find")) {
						tv.setText(read);
						finish();
					} else {
						tv.setText(read);
					}

				}

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private final LocationListener locationListener = new LocationListener() {
		// To Do: override the four methods.
		public void onLocationChanged(Location location) {
/*
			if (location != null) {
				try {
					Thread.sleep(400);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
*/
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}
		
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}
	};
 /*
	class ClientThread implements Runnable {
		// private static final int SERVERPORT = 10000;
		// private static final String SERVER_IP = "192.168.1.195";
		@Override
		
	}
*/

}
