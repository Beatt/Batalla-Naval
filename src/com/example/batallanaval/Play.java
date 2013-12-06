package com.example.batallanaval;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Play extends View {

	// ATRIBUTOS
	private Bitmap barco, cambiar, bmpFallado, bmpAcierto;
	private boolean activo = false, comienza = false;
	private int eRen = 3, eCol = 5;
	private int ePosicionX = 30, ePosicionY = 10;
	private List<GraficoTablero> MiTablero; // TABLERO USUARIO
	private List<misBarcos> MisBarcos; // POSICIONAR MIS BARCOS
	private List<BarcoEnemigo> TableroEnemigo; // TABLERO MAQUINA
	private int eAncho;
	private int eAlto;
	private int ePosBarcoX = 100;
	private int ePosBarcoY = 300;
	private boolean bContinua = true;
	private misBarcos mover;
	private int x, y;
	private int eGanaste = 0;
	private boolean bTiro = false;
	private int ePerdiste = 0;
	private MediaPlayer disparo, disparoFallado;
	Thread miHilo;
	Toast mensaje;

	public Play(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		setBackgroundResource(R.drawable.fondo); // FONDO QUE SE UTILIZA EN LA
													// BATALLA DE LOS MEMES

		barco = BitmapFactory.decodeResource(getResources(),
				R.drawable.pordefecto); // IMAGEN POR DEFECTO

		cambiar = BitmapFactory
				.decodeResource(getResources(), R.drawable.barco); // IMAGEN DE
																	// BARCOS

		bmpFallado = BitmapFactory.decodeResource(getResources(),
				R.drawable.bmpfallaste); // IMAGEN TIRO FALLADO

		bmpAcierto = BitmapFactory.decodeResource(getResources(),
				R.drawable.bmpacierto); // IMAGEN TIRO ACIERTO

		disparo = MediaPlayer.create(getContext(), R.raw.disparo);
		disparoFallado = MediaPlayer.create(getContext(), R.raw.fallado);

		// Obtiene el ancho de la imagen
		eAncho = barco.getWidth();
		eAlto = barco.getHeight();

		// Crea un objeto de tipo ArrayList
		MiTablero = new ArrayList<GraficoTablero>();
		MisBarcos = new ArrayList<misBarcos>();
		TableroEnemigo = new ArrayList<BarcoEnemigo>();

		for (int i = 0; i < eRen; i++) {

			for (int j = 0; j < eCol; j++) {

				GraficoTablero miTablero = new GraficoTablero(this, barco);
				miTablero.setePosX(ePosicionX);
				miTablero.setePosY(ePosicionY);

				BarcoEnemigo barcoEnemigo = new BarcoEnemigo(this, barco);
				barcoEnemigo.setePosX(ePosicionX);
				barcoEnemigo.setePosY(ePosicionY + 250);

				MiTablero.add(miTablero);
				TableroEnemigo.add(barcoEnemigo);
				ePosicionX += eAncho;

			}

			ePosicionX = 30;
			ePosicionY += eAlto;
		}

		for (int i = 0; i < 3; i++) {

			misBarcos barcos = new misBarcos(this, cambiar);
			barcos.setePosX(ePosBarcoX);
			barcos.setePosY(ePosBarcoY);

			ePosBarcoX += eAncho;
			MisBarcos.add(barcos);

		}

		// Inicializa posición de barcos Enemigos
		randomBarcos();
		
	}// FIN CONSTRUCTOR

	// METODOS

	@Override
	protected void onDraw(Canvas canvas) {
		
		
		if (bContinua) {

			for (GraficoTablero miTablero : MiTablero) {

				miTablero.dibujarCasilla(canvas);
			}

			for (misBarcos barcos : MisBarcos) {

				barcos.ubicacionTusBarcos(canvas);
				barcos.elijeTusBarcos(canvas);
			}

		}

		if (comienza == true) {

			if (bTiro == true) {

				for (BarcoEnemigo tableroEnemigo : TableroEnemigo) {

					if (tableroEnemigo.imagenAcertada(x, y)) {

						if (tableroEnemigo.isbProbado() == false) {

							if (tableroEnemigo.isTieneBarco() == true) {
								Toast.makeText(getContext(),
										"Diste en el blanco",
										Toast.LENGTH_SHORT).show();
								eGanaste++;
								// bTiro = true;
								disparo.seekTo(500);
								disparo.start();
								tableroEnemigo.setBmp(bmpAcierto);

							} else {

								disparoFallado.start();
								bTiro = false;
								tableroEnemigo.setBmp(bmpFallado);					

							}

							tableroEnemigo.setbProbado(true);
						}

					}
				}
			} else {
				
				try {
					miHilo.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				int eNumero = randomNumero();

				// after 5 seconds draw the second line
				if (MiTablero.get(eNumero).isbProbado() == false) {
					
					if (MiTablero.get(eNumero).isTieneBarco() == true) {

						Toast.makeText(getContext(), "Tu barco Fue hundido",
								Toast.LENGTH_SHORT).show();
						MiTablero.get(eNumero).setDibujo(bmpAcierto);
						// bTiro = false;
						ePerdiste++;

					} else {
						
						mensaje = Toast.makeText(getContext(), "TE ATACARON...ES TU TURNO DE ATACAR", Toast.LENGTH_SHORT);
						mensaje.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
						mensaje.show();
						MiTablero.get(eNumero).setDibujo(bmpFallado);
						bTiro = true;

					}

					MiTablero.get(eNumero).setbProbado(true);
				}

			}

			if (ePerdiste == 3) {
				Toast.makeText(getContext(), "PERDISTE PERDEDOR !!!",
						Toast.LENGTH_SHORT).show();
				setBackgroundResource(R.drawable.perdiste);
				bContinua = false;
				comienza = false;
			}

			if (eGanaste == 3) {
				setBackgroundResource(R.drawable.ganaste);
				Toast.makeText(getContext(), "GANASTE ERES UN MEME !!!",
						Toast.LENGTH_SHORT).show();
				bContinua = false;
				comienza = false;
			}

			for (GraficoTablero miTablero : MiTablero) {

				miTablero.dibujarCasilla(canvas);
			}
			
			
			for (BarcoEnemigo barcoEnemigo : TableroEnemigo) {

				barcoEnemigo.ubicacionBarcos(canvas);
			}

		}
		
		invalidate();


	}// FIN onDraw

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		x = (int) event.getX();
		y = (int) event.getY();

		elegirBarcos(x, y);

		if (event.getAction() == MotionEvent.ACTION_UP && !comienza) {

			insertarBarco(x, y);
		}

		return true;
	}// FIN onTouchEvent

	public void elegirBarcos(int x, int y) {

		for (int i = MisBarcos.size() - 1; i >= 0; i--) {
			mover = MisBarcos.get(i);
			if (mover.imagenAcertada(x, y)) {
				activo = true;

			}
		}

		if (activo == true) {

			mover.setePosX(x);
			mover.setePosY(y);

		}

	}// FIN elegirBarcos

	public void insertarBarco(int x, int y) {

		for (GraficoTablero miTablero : MiTablero) {

			if (x >= miTablero.getePosX()
					&& x < miTablero.getePosX() + miTablero.geteAncho()
					&& y >= miTablero.getePosY()
					&& y < miTablero.getePosY() + miTablero.geteAlto()
					&& miTablero.isTieneBarco() == false) {

				miTablero.setDibujo(cambiar);
				miTablero.setTieneBarco(true);

				activo = false;
				MisBarcos.remove(mover);

				// Para comenzar la partida..
				if (MisBarcos.size() == 0) {

					comienza = true; //Comienza la partida
					bContinua = false; //Ya no permite la eleccion de la posición de tus barcos
					bTiro = true; //Comenzar a ejecutar instrucciones de los tiros
				}
			}
		}

	}// FIN insertarBarco

	public void randomBarcos() {

		Random r = new Random();
		int eCont = 0;

		do {

			int barco = r.nextInt(12);

			if (!TableroEnemigo.get(barco).isTieneBarco()) {

				// TableroEnemigo.get(barco).setBmp(cambiar);
				TableroEnemigo.get(barco).setTieneBarco(true);
				eCont++;
			} else {

				barco = r.nextInt(12);
			}

		} while (eCont != 3);
	}//FIN randomBarcos
	
	public int randomNumero() {
		
		int eNumero;
		Random r = new Random();
		
		
		do {
			
			eNumero = (int) r.nextInt(15);
			
		}while( MiTablero.get(eNumero).isbProbado() != false );
		return eNumero;
	}
	
}// FIN CLASE Play
