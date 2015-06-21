/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author monte
 */


public class DESSubKeys {

    byte[] theKey = new byte[8];
    byte[][] subKeys = new byte[16][];

 /******************************************
  * Primera Permutacion
  */
   static final int[] PC1 = {
      57, 49, 41, 33, 25, 17,  9,
       1, 58, 50, 42, 34, 26, 18,
      10,  2, 59, 51, 43, 35, 27,
      19, 11,  3, 60, 52, 44, 36,
      63, 55, 47, 39, 31, 23, 15,
       7, 62, 54, 46, 38, 30, 22,
      14,  6, 61, 53, 45, 37, 29,
      21, 13,  5, 28, 20, 12,  4
   };

 /******************************************
  * Segunda Permutacion
  */
   static final int[] PC2 = {
      14, 17, 11, 24,  1,  5,
       3, 28, 15,  6, 21, 10,
      23, 19, 12,  4, 26,  8,
      16,  7, 27, 20, 13,  2,
      41, 52, 31, 37, 47, 55,
      30, 40, 51, 45, 33, 48,
      44, 49, 39, 56, 34, 53,
      46, 42, 50, 36, 29, 32
   };

 /******************************************
  * Desplazamientos uno o dos
  */
   static final int[] SHIFTS = {
      1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
   };
   
 /******************************************
  * Constructor pasa Str y obtiene 8 bytes
  * @param strKey
  */
   public DESSubKeys(String strKey) {

        for (int i=0; i<8; i++) {
         String strByte = strKey.substring(9*i+1,9*i+1+8);
         theKey[i] = (byte) Integer.parseInt(strByte,2);
        }
    }



 /******************************************
  * 
  * @return
  * @throws Exception
  */
   public byte[][] getSubkeys()
      throws Exception {
      DESUtil.printBytes(theKey,"Clave Entrada");

      int activeKeySize = PC1.length;
      int numOfSubKeys = SHIFTS.length;
      byte[] activeKey = DESUtil.selectBits(theKey,PC1);

      DESUtil.printBytes(activeKey,"    C_0 D_0: Después de PC-1:");

      int halfKeySize = activeKeySize/2;
      byte[] c = DESUtil.selectBits(activeKey,0,halfKeySize);
      byte[] d = DESUtil.selectBits(activeKey,halfKeySize,halfKeySize);
    
      for (int k=0; k<numOfSubKeys; k++) {
         c = DESUtil.rotateLeft(c,halfKeySize,SHIFTS[k]);
         d = DESUtil.rotateLeft(d,halfKeySize,SHIFTS[k]);

         byte[] cd = DESUtil.concatenateBits(c,halfKeySize,d,halfKeySize);
         
        DESUtil.printBytes(cd,"    C_"+(k+1)+" D_"+(k+1)+" 56 bits 28 + 28 ");
         subKeys[k] = DESUtil.selectBits(cd,PC2);

        DESUtil.printBytes(subKeys[k],"SubClave #"+(k+1)+" Aplicamos PC-2 (48 bits).");
      }
      return subKeys;
   }

}
