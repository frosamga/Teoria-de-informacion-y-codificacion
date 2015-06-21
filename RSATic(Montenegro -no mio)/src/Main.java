/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.math.BigInteger;

/**
 *
 * @author monte
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       int tamPrimo = 1024;
        RSA rsaAlice = new RSA(tamPrimo);


        System.out.println("Tam Clave: ["+ tamPrimo + "]\n");

        System.out.println("p: [" + rsaAlice.get_p().toString(16).toUpperCase() + "]");
        System.out.println("q: [" + rsaAlice.get_q().toString(16).toUpperCase() + "]\n");

        System.out.println("Clave publica (n,e)");
        System.out.println("n: [" + rsaAlice.get_n().toString(16).toUpperCase() + "]");
        System.out.println("e: [" + rsaAlice.get_e().toString(16).toUpperCase() + "]\n");

        System.out.println("Clave publica (n,d)");
        System.out.println("n: [" + rsaAlice.get_n().toString(16).toUpperCase() + "]");
        System.out.println("d: [" + rsaAlice.get_d().toString(16).toUpperCase() + "]\n");


        String textoPlano = ("Que clase mas buena!");
        System.out.println("Texto a cifrar: ["+ textoPlano +"]\n");

        BigInteger[] textoCifrado = rsaAlice.cifrar(textoPlano);

        System.out.println("Texto cifrado: [");

        for(int i=0; i<textoCifrado.length; i++) {
            System.out.print(textoCifrado[i].toString(16).toUpperCase());
            if(i != textoCifrado.length-1)
                System.out.println("");
        }
        System.out.println("]\n");

        String recuperarTextoPlano = rsaAlice.descifrar(textoCifrado);
        System.out.println("Texto descifrado: ["+ recuperarTextoPlano +"]");

    }

}
