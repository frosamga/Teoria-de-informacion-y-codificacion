/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author monte
 */
public class CompararImagenes {

      // to change the value of the offset bits.
    final static int RED    = 0;
    final static int GREEN  = 1;
    final static int BLUE   = 2;
    final static int OFFSET = 3;  // ignore offset; use only red, green, blue
    final static int ERRORFILELOAD =-2;
    final static int ERRORFILESIZE =-1;

        int[][][] ImageOriginal;
        final int MAXROWSOriginal;
        final int MAXCOLSOriginal;

        int[][][] ImageModificada;
        final int MAXROWSModificada;
        final int MAXCOLSModificada;

/******
 * 
 * @param Original
 * @param Modificada
 */
 public    CompararImagenes (String Original, String Modificada){

        ImageOriginal = BMPHandler.loadBMP(Original);
        MAXROWSOriginal = ImageOriginal.length;
        MAXCOLSOriginal = ImageOriginal[0].length;

        ImageModificada = BMPHandler.loadBMP(Modificada);
        MAXROWSModificada = ImageOriginal.length;
        MAXCOLSModificada = ImageOriginal[0].length;

     }

  /**********
   * 
   * @return
   */
   public int diferencias2Imagenes3canales (){

        int dif=0;

        if ((ImageOriginal==null) || (ImageModificada ==null)) return ERRORFILELOAD;
        
        if ((MAXROWSOriginal!=MAXROWSModificada) || (MAXCOLSOriginal !=MAXCOLSModificada)) return ERRORFILESIZE;
        // No tienen mismo tamanho

        for (int row=0; row<MAXROWSOriginal; row++) {
                    for (int col=0; col<MAXCOLSOriginal; col++) {
                       if (ImageOriginal[row][col][RED] != ImageModificada[row][col][RED]) dif++;
                       if (ImageOriginal[row][col][GREEN] != ImageModificada[row][col][GREEN])  dif++;
                       if (ImageOriginal[row][col][BLUE] != ImageModificada[row][col][BLUE])      dif++;
                    }  // for col
           }  // for row

        return dif;
        
    }

   /************
    * 
    * @return
    */
public int diferencias2Imagenes4canales (){

        int dif=0;

        if ((ImageOriginal==null) || (ImageModificada ==null)) return ERRORFILELOAD;

        if ((MAXROWSOriginal!=MAXROWSModificada) || (MAXCOLSOriginal !=MAXCOLSModificada)) return ERRORFILESIZE;
        // No tienen mismo tamanho

        for (int row=0; row<MAXROWSOriginal; row++) {
                    for (int col=0; col<MAXCOLSOriginal; col++) {
                       if (ImageOriginal[row][col][RED] != ImageModificada[row][col][RED]) dif++;
                       if (ImageOriginal[row][col][GREEN] != ImageModificada[row][col][GREEN])  dif++;
                       if (ImageOriginal[row][col][BLUE] != ImageModificada[row][col][BLUE])      dif++;
                       if (ImageOriginal[row][col][OFFSET] != ImageModificada[row][col][OFFSET])      dif++;
                    }  // for col
           }  // for row

        return dif;
}

/*********
 * 
 * @return
 */
public int diferenciasBit2Imagenes3canales (){

        int dif=0;

        if ((ImageOriginal==null) || (ImageModificada ==null)) return ERRORFILELOAD;

        if ((MAXROWSOriginal!=MAXROWSModificada) || (MAXCOLSOriginal !=MAXCOLSModificada)) return ERRORFILESIZE;
        // No tienen mismo tamanho

        for (int row=0; row<MAXROWSOriginal; row++) {
           for (int col=0; col<MAXCOLSOriginal; col++) {
                dif+=  Integer.bitCount(ImageOriginal[row][col][RED] ^ ImageModificada[row][col][RED]);
                dif+=  Integer.bitCount(ImageOriginal[row][col][GREEN] ^ ImageModificada[row][col][GREEN]);
                dif+=  Integer.bitCount(ImageOriginal[row][col][BLUE] ^ ImageModificada[row][col][BLUE]);
               }  // for col
           }  // for row

        return dif;
}

/*********
 * 
 * @return
 */
public int diferenciasBit2Imagenes4canales (){

        int dif=0;

        if ((ImageOriginal==null) || (ImageModificada ==null)) return ERRORFILELOAD;

        if ((MAXROWSOriginal!=MAXROWSModificada) || (MAXCOLSOriginal !=MAXCOLSModificada)) return ERRORFILESIZE;
        // No tienen mismo tamanho

        for (int row=0; row<MAXROWSOriginal; row++) {
           for (int col=0; col<MAXCOLSOriginal; col++) {
                dif+=  Integer.bitCount(ImageOriginal[row][col][RED] ^ ImageModificada[row][col][RED]);
                dif+=  Integer.bitCount(ImageOriginal[row][col][GREEN] ^ ImageModificada[row][col][GREEN]);
                dif+=  Integer.bitCount(ImageOriginal[row][col][BLUE] ^ ImageModificada[row][col][BLUE]);
                dif+=  Integer.bitCount(ImageOriginal[row][col][OFFSET] ^ ImageModificada[row][col][OFFSET]);
               }  // for col
           }  // for row

        return dif;
}

/**************
 * 
 * @return
 */

    public int diferencias2Imagenes (){

        int dif=0;
        if ((ImageOriginal==null) || (ImageModificada ==null)) return ERRORFILELOAD;
    
        if ((MAXROWSOriginal!=MAXROWSModificada) || (MAXCOLSOriginal !=MAXCOLSModificada)) return ERRORFILESIZE;
        // No tienen mismo tamanho

        for (int row=0; row<MAXROWSOriginal; row++) {
                    for (int col=0; col<MAXCOLSOriginal; col++) {
                       if (ImageOriginal[row][col][RED] != ImageModificada[row][col][RED]) dif++;
                       else{
                           if(ImageOriginal[row][col][GREEN] != ImageModificada[row][col][GREEN])  dif++;
                           else
                             if (ImageOriginal[row][col][BLUE] != ImageModificada[row][col][BLUE])  dif++;
                       }
                    }  // for col
                }  // for row


        return dif;

    }

}
