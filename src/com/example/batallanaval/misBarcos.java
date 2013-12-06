package com.example.batallanaval;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class misBarcos {

	Bitmap m;
	View v;
	private int ePosX, ePosY;
	private int eAlto;
	private int eAncho;
	private int x = 20 , y = 250;
	
	public misBarcos(View v, Bitmap m) {
		this.v = v;
		this.m = m;
		
		eAncho = m.getWidth();
		eAlto = m.getHeight();
	}
	
	public void ubicacionTusBarcos(Canvas canvas) {
		
		Paint miPincel = new Paint();
		canvas.drawBitmap(m, ePosX, ePosY, miPincel);
	}
	
	public boolean imagenAcertada(int x, int y) {
		
		return (x >= ePosX && x < (ePosX + eAncho) && y >= ePosY
				&& y < (ePosY + eAlto));
	}
	
	public void elijeTusBarcos(Canvas canvas) {
		Paint miPincel = new Paint();
		miPincel .setARGB(240, 120, 3, 222);
		miPincel.setTextSize(25);
		canvas.drawText("ELIJE LA POSICIÓN", x, y, miPincel);
		canvas.drawText("DE TUS BARCOS", x + 80, y + 20, miPincel);
	}
	
	public int getePosX() {
		return ePosX;
	}

	public void setePosX(int ePosX) {
		this.ePosX = ePosX;
	}

	public int getePosY() {
		return ePosY;
	}

	public void setePosY(int ePosY) {
		this.ePosY = ePosY;
	}

	public int geteAlto() {
		return eAlto;
	}

	public void seteAlto(int eAlto) {
		this.eAlto = eAlto;
	}

	public int geteAncho() {
		return eAncho;
	}

	public void seteAncho(int eAncho) {
		this.eAncho = eAncho;
	}
	
	
	
}
