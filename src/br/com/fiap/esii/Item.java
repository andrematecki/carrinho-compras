/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fiap.esii;

public class Item {
    
    private Produto produto;
    private int qtd;

    public Item(Produto produto, int qtd) {
        this.produto = produto;
        this.qtd = qtd;
    }
    
     public Item(Produto produto) {
        this.produto = produto;
        this.qtd = 0;
    }
    

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
        Item aux = (Item)obj;
        
       if(produto.getNome().equals(aux.getProduto().getNome())) 
           return true;
       else
           return false;
       
    }
    
    
    
    
}
