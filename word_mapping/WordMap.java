package word_mapping;

public class WordMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            ArvorePatricia arvore = new ArvorePatricia(128); // parametro e o num bits das chaves
            ExtraiPalavra palavras = new ExtraiPalavra ("delimitadores.txt", "teste.txt");
            String palavra = null; int i = 1;

            while ((palavra = palavras.proximaPalavra())!=null){
                /*
                enquanto palavras.proximaPalavra() nao retornar nulo, segue inserindo palavra na arvore
                palavras.linha foi adicionado a classe extrai funcao e e' atualizada pela propria proximaPalavra
                sempre que uma nova linha e' lida
                */
                String posicao;
                posicao = Integer.toString(palavras.linha) + " (" + Integer.toString(palavras.numPalavra) + ")";


                palavra = palavra.replaceAll("[^a-zA-Z0-9]", "");
                palavra = palavra.toLowerCase();

                arvore.insere(palavra, posicao);
                /*
                chama arvore.insere com duas string, a palavra em si e o numero inteiro de linhas convertido para string
                convertemos esse numero para string para ficar mais facil de ir concatenando linhas, sempre que outra
                ocorrencia da palavra ocorrer
                */
            }
            System.out.println("Find: Case");
            arvore.pesquisa("Case");
            System.out.println("Find: Matrix");
            arvore.pesquisa("Matrix");
            System.out.println("Find: Interstellar");
            arvore.pesquisa("Interstellar");
            System.out.println("Find: Neuromancer");
            arvore.pesquisa("Neuromancer");
            System.out.println("Find: zooboomafoo");
            arvore.pesquisa("zooboomafoo");
            palavras.fecharArquivos();
        } catch (Exception e) {System.out.println ("Erro! Msg: " + e.getMessage ());}    
    }
}
