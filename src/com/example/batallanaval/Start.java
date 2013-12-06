package com.example.batallanaval;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;

public class Start extends Activity {

	Play miVista;
	MediaPlayer songComienzo;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		setContentView(new Play (this) ); //comieza el juego
		musicaComienzo(); //Comienza la musica
		
	}//FIN onCreate

	@Override
	protected void onPause() {
		super.onPause();
		if(songComienzo != null)
				songComienzo.release();
	}//FIN onPause
	
	public void musicaComienzo() {
		if(songComienzo != null) 
			songComienzo.release();
		songComienzo = MediaPlayer.create(getApplicationContext(), R.raw.comienzo);
		songComienzo.seekTo(0);
		songComienzo.start();
	}//FIN musicaComienzo
	
}//FIN CLASE Start
