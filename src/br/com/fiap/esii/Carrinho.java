/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.fiap.esii;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EOS
 */
public class Carrinho {
    
    private ArrayList<Item> itens;
    private Double total;

    public Carrinho() {
        
        this.itens = new ArrayList<Item>();
        this.total=0.0;
        
    }
    
    

    
    public int getQuantidadeItens() {
        return itens.size();
    }

    void add(Produto produto) {
        
        Item i = new Item(produto,1);
        
       if(itens.contains(i)){
           int index = itens.indexOf(i);
           itens.get(index).addQtd();
    
       }else{
           
            itens.add(i);
       }
        
       
    }

    public ArrayList<Item> getItens() {
      
        return itens;
    }

    void remove(Produto produto)throws ProdutoInexistenteExpected {
        
        Item i = new Item(produto);
        
       if(itens.contains(i) &&  itens.get(itens.indexOf(i)).getQtd()==1)
          itens.remove(i);
       else
           if(itens.contains(i))
              itens.get(itens.indexOf(i)).removeQtd();
           else
               throw new ProdutoInexistenteExpected();
        
    }

    public int getQuantidadeProdutos(Produto produto) throws ProdutoInexistenteExpected{
        
         Item i = new Item(produto);
        
       if(itens.contains(i))
           return itens.get(itens.indexOf(i)).getQtd();
       else
           throw new ProdutoInexistenteExpected();
        
    }
    
    int getQuantidadeProdutos(Class clazz) throws ProdutoInexistenteExpected{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isEmpty() {
        
        return itens.isEmpty();
    }

    Object getTotal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
