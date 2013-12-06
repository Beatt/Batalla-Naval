package com.example.batallanaval;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class BatallaNaval extends Activity implements OnClickListener {
	// Atributos
	
	TextView iniciar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		setContentView(R.layout.activity_main);
		
		iniciar = (TextView) findViewById(R.id.textView2);
		iniciar.setOnClickListener(this);
		
		
	}//FIN onCreate

	// Metodos

	@Override
	protected void onDestroy() {
		finish();
		super.onDestroy();
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, Start.class));
	}
	
}//FIN MainActivity
