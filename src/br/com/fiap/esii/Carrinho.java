package br.com.fiap.esii;

import java.util.ArrayList;

public class Carrinho {
    
    private final ArrayList<Item> itens;

    public Carrinho() {
        this.itens = new ArrayList<>();  
    }

    public int getQuantidadeItens() {
        return itens.size();
    }

    public void add(Produto produto) {
        Item i = new Item(produto,1);
        
        if(itens.contains(i)) {
            int index = itens.indexOf(i);
            itens.get(index).addQtd();
        }
        else {   
            itens.add(i);    
       }
    }
    
    public void add(Produto produto, int quantidade) throws QuantidadeInsuficienteException{
        if(quantidade <= 0)
            throw new QuantidadeInsuficienteException();
        
        for(int i = 1; i <= quantidade; i++){
            add(produto);
        }
    }
    
    public ArrayList<Item> getItens() {
        return itens;
    }

    public void remove(Produto produto) throws ProdutoInexistenteException {
        Item i = new Item(produto);
        
        if(itens.contains(i) &&  itens.get(itens.indexOf(i)).getQtd()==1){
            itens.remove(i);
        }
        else {
            if(itens.contains(i)){
                itens.get(itens.indexOf(i)).removeQtd();
            }
            else{
                throw new ProdutoInexistenteException();
            }
        }
    }

    public int getQuantidadeProdutos(Produto produto) throws ProdutoInexistenteException{
        Item i = new Item(produto);
        
        if(!itens.contains(i)) {
            throw new ProdutoInexistenteException();
        }
        else {
            return itens.get(itens.indexOf(i)).getQtd();
        }
    }
    
    public int getQuantidadeProdutos(Class clazz) throws ProdutoInexistenteException{
        
        int qtde = 0;
        
        for (Item item : itens) {
            if (item.getProduto().getClass() == clazz) {
                qtde += item.getQtd();
            }
        }
             
        if(qtde == 0){
            throw new ProdutoInexistenteException();
        }
                 
        return qtde;
    }

    public boolean isEmpty() {
        return itens.isEmpty();
    }

    public Double getTotal() {
        
        Double total = 0.0;
        
        for (Item item : itens) {
            total += item.getQtd() * item.getProduto().getPreco();
        }
        
        return total;    
    }
    
    public void clear() {
        itens.clear();
    }
}
