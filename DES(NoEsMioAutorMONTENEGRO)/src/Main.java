/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author monte
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */

	private static boolean validateSubkeys(byte[][] subKeys) {
		boolean ok = true;
		String[] strKeys = {
				" 00011011 00000010 11101111 11111100 01110000 01110010",// 1
				" 01111001 10101110 11011001 11011011 11001001 11100101",// 2
				" 01010101 11111100 10001010 01000010 11001111 10011001",// 3
				" 01110010 10101101 11010110 11011011 00110101 00011101",// 4
				" 01111100 11101100 00000111 11101011 01010011 10101000",// 5
				" 01100011 10100101 00111110 01010000 01111011 00101111",// 6
				" 11101100 10000100 10110111 11110110 00011000 10111100",// 7
				" 11110111 10001010 00111010 11000001 00111011 11111011",// 8
				" 11100000 11011011 11101011 11101101 11100111 10000001",// 9
				" 10110001 11110011 01000111 10111010 01000110 01001111",// 0
				" 00100001 01011111 11010011 11011110 11010011 10000110",// 1
				" 01110101 01110001 11110101 10010100 01100111 11101001",// 2
				" 10010111 11000101 11010001 11111010 10111010 01000001",// 3
				" 01011111 01000011 10110111 11110010 11100111 00111010",// 4
				" 10111111 10010001 10001101 00111101 00111111 00001010",// 5
				" 11001011 00111101 10001011 00001110 00010111 11110101" };
		for (int k = 0; k < 16; k++) {
			for (int i = 0; i < 6; i++) {
				String strByte = strKeys[k].substring(9 * i + 1, 9 * i + 1 + 8);
				byte keyByte = (byte) Integer.parseInt(strByte, 2);
				if (keyByte != subKeys[k][i])
					ok = false;
			}
		}
		return ok;
	}

	public static boolean validateCipher(byte[] cipher) {
		String strCipher = " 10000101 11101000 00010011 01010100"
				+ " 00001111 00001010 10110100 00000101";
		boolean ok = true;
		for (int i = 0; i < 8; i++) {
			String strByte = strCipher.substring(9 * i + 1, 9 * i + 1 + 8);
			byte cipherByte = (byte) Integer.parseInt(strByte, 2);
			if (cipherByte != cipher[i])
				ok = false;
		}
		return ok;
	}

	public static void Cifrado(String Key, String Msg) {

		DESCipher DesCipher;
		try {
			DesCipher = new DESCipher(Key);
			byte[] theCph = DesCipher.cipherBlock(Msg);

			// boolean ok = validateCipher(theCph);
			// System.out.println("DES cipher test result: "+ok);

		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public static void SubKeys(String Key) {
		try {
			DESSubKeys DESSubk = new DESSubKeys(Key);
			byte[][] subKeys = DESSubk.getSubkeys();
			// boolean ok = validateSubkeys(subKeys);
			// System.out.println("DES subkeys test result: " + ok);
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] a) {

		String Key = " 00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001";

		// weakKey
		String weaKey1 = " 00000001 00000001 00000001 00000001 00000001 00000001 00000001 00000001"; // 0x0101010101010101
		String weaKey2 = " 11111110 11111110 11111110 11111110 11111110 11111110 11111110 11111110";// 0xFEFEFEFEFEFEFEFE
		String weaKey3 = " 11100000 11100000 11100000 11100000 11110001 11110001 11110001 11110001";// 0xE0E0E0E0F1F1F1F1
		String weaKey4 = " 00011111 00011111 00011111 00011111 00001110 00001110 00001110 00001110";// 0x1F1F1F1F0E0E0E0E

		String Msg = " 00000001 00100011 01000101 01100111 10001001 10101011 11001101 11101111";
		String E_Msg = " 01100001 01111011 00111010 00001100 11101000 11110000 01110001 00000000";

		/*****************************************/
		// / Cifrado con claves débiles. Todos los subClaves son iguales.

		Cifrado(weaKey1, Msg); // E_Msg = E_weaKey1 (Msg).
		Cifrado(weaKey1, E_Msg); // Msg = E_weaKey1 (E_Msg). --> E_weaKey1
									// (E_weaKey1 (Msg)) = Msg.

		String KeyCero = " 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000";
		String MsgCero = " 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000";

		/**********************************************************************/
		// / Probar mensajes Cero y Uno

		Cifrado(KeyCero, MsgCero);

		// 00000000 00000000 00000000 00000000 L1 11011000 11011000 11011011
		// 10111100 R1

		String KeyUno = " 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111";
		String MsgUno = " 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111";

		Cifrado(KeyUno, MsgUno);

		// 11111111 11111111 11111111 11111111 L1 00100111 00100111 00100100
		// 01000011 R1

		/**********************************************************************/
		// Efecto avalancha, cambio un bit en la clave

		// String Key =
		// " 00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001";
		String Key1 = " 10010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001";
		// String Msg =
		// " 00000001 00100011 01000101 01100111 10001001 10101011 11001101 11101111";

		Cifrado(Key, Msg);
		// E_key(Msg) = 10000101 11101000 00010011 01010100 00001111 00001010
		// 10110100 00000101
		Cifrado(Key1, Msg);
		// E_key1(Msg)= 10000011 11101011 11001000 10000001 11111101 10101101
		// 00101101 01011000
	}

}
