package controller;

/**
 * Clase que maneja el objeto ThreadNotify.java
 * @author CRISTIAN DAVID PARADA MARTINEZ
 * @date 19/09/2021
 */
public abstract class ThreadInfo implements Runnable {

	private Thread thread;
	protected boolean isExecute;
	protected boolean isPaused;
	protected int sleepTime;

	/**
	 * Constructor de la clase ThreadNotify
	 * @param sleepTime tiempo de espera que esperara para hacer la comprobacion de notificaciones
	 */
	public ThreadInfo(int sleepTime) {
		this.sleepTime = sleepTime;
		this.isExecute = false;
		this.thread = new Thread(this);
	}

	/**
	 * Metodo que inicia el hilo
	 */
	public void start() {
		isExecute = true;
		thread.start();
	}

	/**
	 * Metodo que detiene el hilo
	 */
	public synchronized void stop() {
		isExecute = false;
		notifyAll();
	}

	/**
	 * Metodo que pausa el hilo
	 */
	public synchronized void pause() {
		isPaused = true;
		notifyAll();
	}

	/**
	 * Metodo que reaunuda el hilo
	 */
	public synchronized void resume() {
		isPaused = false;
		notifyAll();
	}

	/**
	 * Metodo que verifica si se esta ejecutando el hilo
	 * @return true o false
	 */
	public boolean isExecute() {
		return isExecute;
	}
	
	/**
	 * Metodo que verifica si esta pausado
	 * @return true o false
	 */
	public boolean isPaused() {
		return isPaused;
	}
	
	/**
	 * Metodo que implementa la accion del hilo
	 */
	protected abstract void executeTask();

	@Override
	public void run() {
		while (isExecute) {
			synchronized (this) {
				if (isPaused) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!isExecute) {
						break;
					}
				}else {
					executeTask();
				}
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
