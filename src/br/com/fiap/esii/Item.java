package br.com.fiap.esii;

import java.util.Objects;

public class Item {
    
    private final Produto produto;
    private int qtd;

    public Item(Produto produto) {
        this.produto = produto;
        this.qtd = 1;
    }
    
    public Produto getProduto() {
        return produto;
    }
    
    public int getQtd() {
        return qtd;
    }

    public void addQtd() {
        this.qtd++;
    }
    
    public void removeQtd() {
        this.qtd--;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Item) {
            Item aux = (Item)obj;
            return produto.getNome().equals(aux.getProduto().getNome());  
        } 
        else {
            return false;
        }
    }
}
