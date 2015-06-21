public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String img = "C:/Users/alan/Desktop/TIC/Practica4/practica4/android.bmp";
		Visual v = new Visual();
		BMPHandler hand = new BMPHandler();
		hand.saveImage("android1", hand.loadBMP(img));
		Canal c = new Canal(1);
		v.load(c.canal(img));

	}

}
