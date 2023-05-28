
public class Semaforo {
	private int recursos;

	public Semaforo(int recursos) {
		this.recursos = recursos;
	}

	public synchronized void acquire() {
		if (recursos < 1) {
			try {
				wait();
			} catch (Exception e) {

			}
		}
		recursos--;
	}

	public synchronized void release() {
		recursos++;
		notify();
	}

}
