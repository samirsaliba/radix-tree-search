package word_mapping;

import java.util.StringTokenizer;
import java.io.*;
public class ExtraiPalavra {
  private BufferedReader arqDelim, arqTxt;
  private StringTokenizer palavras;
  private String delimitadores;
  public int linha=0;
  public int numPalavra=1;

  public ExtraiPalavra (String nomeArqDelim, String nomeArqTxt) 
  throws Exception {
    this.arqDelim = new BufferedReader (new FileReader (nomeArqDelim));
    this.arqTxt = new BufferedReader (new FileReader (nomeArqTxt));
    // @{\it Os delimitadores devem estar juntos em uma \'unica linha do arquivo}@ 
    this.delimitadores = arqDelim.readLine() + "\r\n"; 
    this.palavras = null;
  }  
  public String proximaPalavra () throws Exception{
    if (palavras == null || !palavras.hasMoreTokens()) {
      String l = arqTxt.readLine();
      this.linha++;
      this.numPalavra=0;
      if (l == null) return null; 
      this.palavras = new StringTokenizer (l, this.delimitadores);
      if (!palavras.hasMoreTokens()) return ""; // @{\it ignora delimitadores}@
    }
    this.numPalavra++;
    return this.palavras.nextToken ();
  }  
  public void fecharArquivos () throws Exception {
    this.arqDelim.close(); 
    this.arqTxt.close();
  }
}
