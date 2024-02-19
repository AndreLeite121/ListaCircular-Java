package Dio.ListaCircular;

import java.lang.annotation.Target;

public class ListaCircular<T> {
    private No<T> cabeca;
    private No<T> cauda;
    private int TamanhoLista;

    public ListaCircular() {
        this.cabeca = null;
        this.cauda = null;
        this.TamanhoLista = 0;
    }


    public void add(T conteudo){
        No<T> novoNo = new No<>(conteudo);
        if(this.TamanhoLista == 0){
            this.cabeca = novoNo;
            this.cauda = this.cabeca;
            this.cabeca.setNoProximo(cauda);
        }else{
            novoNo.setNoProximo(this.cauda);
            this.cabeca.setNoProximo(novoNo);
            this.cauda = novoNo;
        }
        this.TamanhoLista++;
    }

    public void remove(int index){
        if(index>this.TamanhoLista)
            throw new IndexOutOfBoundsException("O indice é maior que o tamanho da lista.");
        No<T> noAuxiliar = this.cauda;
        if (index == 0){
            this.cauda = this.cauda.getNoProximo();
            this.cabeca.setNoProximo(this.cauda);
        }else if(index == 1){
            this.cauda.setNoProximo(this.cauda.getNoProximo());
        }else {//Com o nó 4 escolhido, o for ira parar no 3, com isso ele ira ligar no 5 pelos dois GetNoProximo().
            for (int i=0; i<index-1; i++){
                noAuxiliar = noAuxiliar.getNoProximo();
            }
            noAuxiliar.setNoProximo(noAuxiliar.getNoProximo().getNoProximo());
        }
        this.TamanhoLista --;
    }

    public T get(int index){
        return this.getNo(index).getConteudo();
    }

    private No<T> getNo(int index){
        if(this.isEmpty())
            throw new IndexOutOfBoundsException("A lista esta vazia!");
        if (index == 0){
            return this.cauda;
        }
        No<T> noAuxiliar = this.cauda;
            for (int i=0; (i<= index) && (noAuxiliar != null); i++){
                noAuxiliar = noAuxiliar.getNoProximo();
            }
        return noAuxiliar;
    }

    public boolean isEmpty(){
        return  this.TamanhoLista == 0 ? true: false;
    }

    public int size(){
        return TamanhoLista;
    }

    @Override
    public String toString() {
        String strRetorno = "";
        No<T> noAuxiliar = this.cauda;
        for(int i = 0; i < size(); i++){
            strRetorno += "[No{conteudo=" + noAuxiliar.getConteudo() +"}]--->";
            noAuxiliar = noAuxiliar.getNoProximo();
        }
        strRetorno += size() != 0 ? "(Retorna ao início)" : "[]";
        return strRetorno;
    }
}
