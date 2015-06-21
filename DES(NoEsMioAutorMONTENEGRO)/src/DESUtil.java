/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author monte
 */
public class DESUtil {
    /******************************************
     *
     * @param a
     * @param aLen
     * @param b
     * @param bLen
     * @return
     */
   public static byte[] concatenateBits(byte[] a, int aLen, byte[] b,
      int bLen) {
      int numOfBytes = (aLen+bLen-1)/8 + 1;
      byte[] out = new byte[numOfBytes];
      int j = 0;
      for (int i=0; i<aLen; i++) {
         int val = getBit(a,i);
         setBit(out,j,val);
         j++;
      }
      for (int i=0; i<bLen; i++) {
         int val = getBit(b,i);
         setBit(out,j,val);
         j++;
      }
      return out;
   }



    /******************************************
  *
  * @param data
  * @param pos
  * @return
  */
   public static int getBit(byte[] data, int pos) {
      int posByte = pos/8;
      int posBit = pos%8;
      byte valByte = data[posByte];
      int valInt = valByte>>(8-(posBit+1)) & 0x0001;
      return valInt;
   }



    /******************************************
     *
     * @param data
     * @param pos
     * @param val
     */
   public static void setBit(byte[] data, int pos, int val) {
      int posByte = pos/8;
      int posBit = pos%8;
      byte oldByte = data[posByte];
      oldByte = (byte) (((0xFF7F>>posBit) & oldByte) & 0x00FF);
      byte newByte = (byte) ((val<<(8-(posBit+1))) | oldByte);
      data[posByte] = newByte;
   }


     /******************************************
    *
    * @param in
    * @param map
    * @return
    */
   public static byte[] selectBits(byte[] in, int[] map) {
      int numOfBytes = (map.length-1)/8 + 1;
      byte[] out = new byte[numOfBytes];
      for (int i=0; i<map.length; i++) {
         int val = getBit(in,map[i]-1);
         setBit(out,i,val);
//       System.out.println("i="+i+", pos="+(map[i]-1)+", val="+val);
      }
      return out;
   }


     /******************************************
     *
     * @param in
     * @param pos
     * @param len
     * @return
     */
   public static byte[] selectBits(byte[] in, int pos, int len) {
      int numOfBytes = (len-1)/8 + 1;
      byte[] out = new byte[numOfBytes];
      for (int i=0; i<len; i++) {
         int val = getBit(in,pos+i);
         setBit(out,i,val);
      }
      return out;
   }

    /******************************************
  *
  * @param in
  * @param len
  * @param step
  * @return
  */
   public static byte[] rotateLeft(byte[] in, int len, int step) {
      int numOfBytes = (len-1)/8 + 1;
      byte[] out = new byte[numOfBytes];

      for (int i=0; i<len; i++) {
         int val = getBit(in,(i+step)%len);
         setBit(out,i,val);
      }
      return out;
   }

   /***********
    * 
    * 
    * @param in
    * @param len
    * @param step
    * @return
    */
     private static byte[] rotateRight(byte[] in, int len, int step) {

       return null;
   }


     /***************
   *
   * @param in
   * @param len
   * @return
   */
   public static byte[] splitBytes(byte[] in, int len) {
      int numOfBytes = (8*in.length-1)/len + 1;
      byte[] out = new byte[numOfBytes];
      for (int i=0; i<numOfBytes; i++) {
         for (int j=0; j<len; j++) {
            int val = DESUtil.getBit(in, len*i+j);
            DESUtil.setBit(out,8*i+j,val);
         }
      }
      return out;
   }


   private static String byteToBits(byte b) {
      StringBuffer buf = new StringBuffer();
      for (int i=0; i<8; i++)
         buf.append((int)(b>>(8-(i+1)) & 0x0001));
      return buf.toString();
   }


      /******************************************
     *
     * @param data
     * @param name
     */
   public static void printBytes(byte[] data, String name) {
      System.out.println("");
      System.out.println(name+":");
      for (int i=0; i<data.length; i++) {
         System.out.print(byteToBits(data[i])+" ");
      }
      System.out.println();
   }


    /*******************
  *
  * @param a
  * @param b
  * @return
  */
 public static byte[] doXORBytes(byte[] a, byte[] b) {
      byte[] out = new byte[a.length];
      for (int i=0; i<a.length; i++) {
         out[i] = (byte) (a[i] ^ b[i]);
      }
      return out;
   }


 
}


