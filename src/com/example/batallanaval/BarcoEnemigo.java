package com.example.batallanaval;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class BarcoEnemigo {
	
	private View vista;
	private Bitmap bmp;
	private int ePosX, ePosY;
	private boolean tieneBarco = false, bProbado = false;
	private int eAncho;
	private int eAlto;
	
	public BarcoEnemigo (View v, Bitmap bmp){
		
		this.vista = v;
		this.bmp = bmp;
		
		eAncho = bmp.getWidth();
		eAlto = bmp.getHeight();
	}//FIN CONSTRUCTOR
	
	public void ubicacionBarcos(Canvas c){
		
		Paint miPincel = new Paint();
		c.drawBitmap(bmp, ePosX, ePosY, miPincel);
	}//FIN ubicacionBarcos
	
public boolean imagenAcertada(int x, int y) {
		
		return (x >= ePosX && x < (ePosX + eAncho) && y >= ePosY
				&& y < (ePosY + eAlto));
	}
	
	

	public boolean isbProbado() {
	return bProbado;
}

public void setbProbado(boolean bProbado) {
	this.bProbado = bProbado;
}

	public boolean isTieneBarco() {
		return tieneBarco;
	}

	public void setTieneBarco(boolean tieneBarco) {
		this.tieneBarco = tieneBarco;
	}

	public Bitmap getBmp() {
		return bmp;
	}

	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
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
	
	
	
}//FIN BarcoEnemigo
