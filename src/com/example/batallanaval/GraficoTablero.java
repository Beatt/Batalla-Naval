package com.example.batallanaval;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class GraficoTablero {
	
	//ATributos
	private View vista;
	private Bitmap dibujo, cambiar;
	private boolean cambio = false;
	private int eAncho , eAlto, ePosX, ePosY;
	private boolean tieneBarco = false, bProbado = false;
	
	public GraficoTablero(View vista,Bitmap dibujo) {
		
		this.vista = vista;
		this.dibujo = dibujo;
		
		eAncho = dibujo.getWidth();
		eAlto = dibujo.getHeight();
	}
	
	public void dibujarCasilla(Canvas canvas) {
		
		Paint miPincel = new Paint();
		canvas.drawBitmap(dibujo, ePosX, ePosY, miPincel);	
	}
	
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

	public void setDibujo(Bitmap dibujo) {
		this.dibujo = dibujo;
	}

	public void setCambio(boolean cambio) {
		this.cambio = cambio;
	}

	public int geteAncho() {
		return eAncho;
	}

	public void seteAncho(int eAncho) {
		this.eAncho = eAncho;
	}

	public int geteAlto() {
		return eAlto;
	}

	public void seteAlto(int eAlto) {
		this.eAlto = eAlto;
	}
	
	
	
}//FIN CLASE GraficoTablero
