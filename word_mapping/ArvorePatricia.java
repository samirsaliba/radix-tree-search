package word_mapping;

import java.util.Arrays;

public class ArvorePatricia {
       
    private static abstract class PatNo { }
    private static class PatNoInt extends PatNo {
        int index; PatNo esq, dir;
    }  
    private static class PatNoExt extends PatNo {
        byte[] chave; // @{\it O tipo da chave depende da aplica\c{c}\~ao}@
        String linha;
    }
  
    private PatNo raiz;
    private int nbitsChave;
    
    public ArvorePatricia (int nbitsChave) {
        this.raiz = null; this.nbitsChave = nbitsChave; 
    }
 
  // @{\it Verifica se p \'e n\'o externo}@
    private boolean eExterno (PatNo p) {    
        Class classe = p.getClass ();
        return classe.getName().equals(PatNoExt.class.getName());
    }

    private PatNo criaNoInt (int i, PatNo esq, PatNo dir) {
        PatNoInt p = new PatNoInt ();
        p.index = i; p.esq = esq; p.dir = dir;
        return p;
    }

    private PatNo criaNoExt (byte[] b, String l) {
        PatNoExt p = new PatNoExt ();
        p.chave = b;
        p.linha=l;
        return p;
    }
  
    private PatNo insereEntre (byte[] b, String l, PatNo t, int i) {
        PatNoInt aux = null; 
        if (!this.eExterno (t)) aux = (PatNoInt)t;
        if (this.eExterno (t) || (i < aux.index)) { // @{\it Cria um novo n\'o externo}@
        PatNo p = this.criaNoExt (b, l);
        if (b[i] == 1) return this.criaNoInt (i, t, p);
        else return this.criaNoInt (i, p, t);
        }
        else {
        if (b[aux.index] == 1) 
            aux.dir = this.insereEntre (b, l, aux.dir, i);
            else aux.esq = this.insereEntre (b, l, aux.esq, i);
        return aux;
        }
    }
  
    private PatNo insere (byte[] b, String l, PatNo t) {
        if (t == null) return this.criaNoExt (b, l);
        else {
            PatNo p = t;
            while (!this.eExterno (p)) {
                PatNoInt aux = (PatNoInt)p;
                if (b[aux.index] == 1) p = aux.dir; else p = aux.esq;
            }
            PatNoExt aux = (PatNoExt)p;
            int i = 0; // @{\it acha o primeiro bit diferente}@
            while ((i < this.nbitsChave)&& 
                (b[i] == aux.chave[i])) i++;
            if (i >= this.nbitsChave) {
                //System.out.println ("OBS: Word already in Tree");
                /*
                Chave ja esta na arvore, ou seja, deve-se atualizar as ocorrencias
                Na linha abaixo concatena-se 'a string de linhas a nova ocorrencia da palavra
                */
                aux.linha = aux.linha +  ", " + l;
                return t;
            }
            
            else {
                return this.insereEntre (b, l, t, i);
            }
        }
    }
  
    private void central (PatNo pai, PatNo filho, String msg) {
        if (filho != null) {
            if (!this.eExterno (filho)) {
            PatNoInt aux = (PatNoInt)filho; 
            central (filho, aux.esq, "ESQ");
            if (pai != null)
                System.out.println ("Pai: "+ ((PatNoInt)pai).index + " " + msg+ " Int: " + aux.index);
            else System.out.println ("Pai: "+ pai + " " + msg+ " Int: " + aux.index);
            central (filho, aux.dir, "DIR");
            } else {
            PatNoExt aux = (PatNoExt)filho;
            if (pai != null)
                System.out.println ("Pai: "+ ((PatNoInt)pai).index + " " + msg+ " Ext: " + Arrays.toString(aux.chave));
            else System.out.println ("Pai: "+ pai + " " + msg+ " Ext: " + Arrays.toString(aux.chave));
            }
        }
    }

    public void imprime () {
        this.central (null, this.raiz, "RAIZ");
    }
 
    private void pesquisa (byte[] b, PatNo t) {
        if (this.eExterno (t)) {
            PatNoExt aux = (PatNoExt)t;
            if (Arrays.equals(aux.chave, b)){
                System.out.print("Word found: ");
                System.out.println("Line(Count): " + aux.linha);
            }
            else System.out.println ("Word not found");
        }
        else { 
            PatNoInt aux = (PatNoInt)t;
            if (b[aux.index]== 0) pesquisa (b, aux.esq);
            else pesquisa (b, aux.dir);
        }
    }
    
    public void pesquisa (String palavra){
        /*
        metodo para pesquisar com string, o metodo chama a transformacao em bits
        e chama a pesquisa com os bits em um array de bytes
        */

        palavra = palavra.toLowerCase();

        Bits b = new Bits();
        byte[] aux;
        aux = b.transforma(palavra);
        this.pesquisa(aux);
    }
    
    public void pesquisa (byte[] b) { this.pesquisa (b, this.raiz); }
    
    public void insere (byte[] b, String linha) {
        this.raiz = this.insere (b, linha, this.raiz); 
    } 

    public void insere (String palavra, String linha){
        /*
        metodo para inserir com string, o metodo chama a transformacao em bits
        e chama o metodo insere com os bits em um array de bytes
        */
        palavra = palavra.toLowerCase();

        Bits stringToBits = new Bits();
        byte[] aux;
        //System.out.println ("Palavra: " + palavra);
        aux = stringToBits.transforma(palavra);
        this.insere(aux, linha);
    }
          
}

