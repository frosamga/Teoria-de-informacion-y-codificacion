
import java.util.Random;

/**
 * 
 * @author monte
 */
public class Canal {

	double probabError = 0.01;
	Random rnd = new Random();

	/********
	 * 
	 * @param probabErrorP
	 */

	Canal(double probabErrorP) {
		probabError = probabErrorP;
	}

	/*********************
	 * img es el nombre del fichero bmp
	 * 
	 * @param img
	 */
	public String canal(String img) {
		return img;

	}

	/********************
	 * Es utilizado por el metodo canal para interpretar bit a bit el canal
	 * 
	 * @param b4
	 * @return
	 */

	public int canal(int b4) {
		int out = 0;

		return out;
	}

}
