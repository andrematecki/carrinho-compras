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

   public void add(Produto produto) {
        
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

    public void remove(Produto produto)throws ProdutoInexistenteExpected {
        
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
    
    public int getQuantidadeProdutos(Class Clazz) throws ProdutoInexistenteExpected{
        
        int qtde=0;
        
         for(int k=0;k<itens.size();k++)
             if(itens.get(k).getProduto().getClass() == Clazz)
             {
                 qtde+=itens.get(k).getQtd();
             }
             
             if(qtde==0)
                throw new ProdutoInexistenteExpected();
                 
             return qtde;
       
    }

    public boolean isEmpty() {
        
        return itens.isEmpty();
    }

    public Double getTotal() {
        
        total=0.0;
        
        for(int k=0;k<itens.size();k++)
        {
            total+= itens.get(k).getQtd() * itens.get(k).getProduto().getPreco();
        }
        
        return total;
        
        
    }
    
}
