package br.com.fiap.esii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CarrinhoTest {
    
    private Carrinho carrinho;
    
    @Before
    public void criandoCarrinho() {
        carrinho = new Carrinho();
    }
    
    /*
        CT01 -  Ao criar o carrinho, a quantidade de itens deve ser 0,
                ou seja, deve estar vazio.
    */
    @Test
    public void criandoCarrinhoVazio(){
        boolean isEmpty = carrinho.isEmpty();
        assertEquals(isEmpty, true);
    }
    
    /*
        CT02 -  Ao adicionar um produto inexistente no 
                carrinho deve ser criado um item
    */
    @Test
    public void adicionandoPrimeiroProduto() throws ProdutoInexistenteException{
        int qtdeItensAntiga = carrinho.getQuantidadeItens();
        Livro l1 = new Livro("Aprendendo Java", 50.00);
        carrinho.add(l1);
        int qtdeItensNova = carrinho.getQuantidadeItens();
        assertEquals((qtdeItensAntiga + 1), qtdeItensNova);
    }
    
    /*
        CT03 -  Ao adicionar um produto existente no carrinho a quantidade de
                itens não deve ser modificada.
    */
    @Test
    public void adicionandoProdutosAoMesmoItem() throws QuantidadeInsuficienteException{
        Eletronico celular = new Eletronico("Iphone 5", 1.500);
        carrinho.add(celular);
        int qtdeAntiga = carrinho.getQuantidadeItens();
        carrinho.add(celular, 2);
        int qtdeNova = carrinho.getQuantidadeItens();
        assertEquals(qtdeAntiga, qtdeNova);
    }
    
    /*
        CT04 -  Ao adicionar um produto existente no carrinho a nova quantidade
                do produto deve ser a antiga + 1
    */
    @Test
    public void adicionandoMaisProdutosExistentes() throws ProdutoInexistenteException{
        Eletronico tv = new Eletronico("TV LED 50' Samsumg", 5.500);
        carrinho.add(tv);
        int qtdeAntiga = carrinho.getQuantidadeProdutos(tv);
        carrinho.add(tv);
        int qtdeNova = carrinho.getQuantidadeProdutos(tv);
        assertEquals(qtdeNova, (qtdeAntiga + 1));
    }
    
    /*
        CT05 -  Não deve ser possível obter a quantidade de um produto
                inexistente no carrinho
    */
    @Test(expected = ProdutoInexistenteException.class)
    public void obtendoQuantidadeProdutoInexistente() throws ProdutoInexistenteException{
        Eletronico dvd = new Eletronico("Aparelho de DVD LG", 150.00);
        int qtdeProdutos = carrinho.getQuantidadeProdutos(dvd);
        assertEquals(qtdeProdutos, 0);
    }
    
    /*
        CT06 -  Ao remover um produto do carrinho a quantidade desse produto deve ser a 
                antiga menos 1
    */
    @Test
    public void removendoUmProdutoExistente() 
            throws ProdutoInexistenteException, QuantidadeInsuficienteException{
        Livro deitel = new Livro("Aprendendo a programar JAVA", 150.00);
        carrinho.add(deitel, 3);
        int qtdeAntiga = carrinho.getQuantidadeProdutos(deitel);
        carrinho.remove(deitel);
        int qtdeNova = carrinho.getQuantidadeProdutos(deitel);
        
        assertEquals((qtdeAntiga - 1), qtdeNova);
    }
    
    
    /*
        CT07 -  Ao remover um produto do carrinho, se só existir uma quantidade desse produto,
                o item deve ser removido.
    */
    @Test
    public void removendoUmProdutoComUmaQuantidade()throws ProdutoInexistenteException{
        int qtdeItensAntiga = carrinho.getQuantidadeItens();
        Livro cacador = new Livro("O caçador de pipas", 50.00);
        carrinho.add(cacador);
        carrinho.remove(cacador);
        int qtdeItensNova = carrinho.getQuantidadeItens();
        
        assertEquals(qtdeItensAntiga, qtdeItensNova);
    }
    
    /*
        CT08 -  Não é possivel remover um produto que não existe no carrinho
    */
    @Test(expected = ProdutoInexistenteException.class)
    public void removendoProdutoInexistente()throws ProdutoInexistenteException{
        Perfume perfume = new Perfume("Desodorante masculino", 14.90);
        carrinho.remove(perfume);
    }
    
    /*
        CT09 -  A lista de produtos do carrinho deve conter os mesmos produtos 
                que foram adicionas.
    */
    @Test
    public void obtendoListaDeProdutosDoCarrinho(){
        Eletronico celular = new Eletronico("Home Theater Sony", 2500.00);
        Livro biblia = new Livro("A biblia de c#", 250.00);
        Perfume perfume = new Perfume("perfume feminino", 150.00);
        
        ArrayList<Item> produtosAdicionar = new ArrayList<>(
                Arrays.asList(new Item(celular), new Item(biblia),new Item(perfume)));
        
        carrinho.add(celular);
        carrinho.add(biblia);
        carrinho.add(perfume);
        
        ArrayList<Item> produtosAdicionados = carrinho.getItens();
        
        assertArrayEquals(produtosAdicionar.toArray(), produtosAdicionados.toArray());
    }
    
    /*
        CT10 -  O total a ser pago deve ser a quantidade de cada 
                produto x o valor do mesmo
    */
    @Test
    public void calculandoValorAPagar(){
        Livro apostila = new Livro("Apostila de C", 30.00);
        Perfume perfume = new Perfume("Perfume feminino", 350.00);
        
        carrinho.add(perfume);
        carrinho.add(perfume);
        carrinho.add(apostila);
        carrinho.add(apostila);
        
        Double totalPagar = 2 * apostila.getPreco() + 2 * perfume.getPreco();
        
        assertEquals(totalPagar, carrinho.getTotal());
    }
            
    
    /*
        CT11 -  Deve ser possivel adicionar produtos de diferentes tipos
    */
    @Test
    public void contabilizandoProdutosDoMesmoTipo() 
            throws ProdutoInexistenteException, QuantidadeInsuficienteException{
        Perfume doce = new Perfume("Perfume doce", 50.00);
        Perfume amadeirado = new Perfume("PerfumeAmadeirado", 100.00);
        Eletronico tv = new Eletronico("TV 20' LG", 1500.00);
        Livro cabana = new Livro("A cabana", 49.90);
        
        carrinho.add(doce, 2);
        carrinho.add(amadeirado);
        carrinho.add(tv);
        carrinho.add(cabana);
        
        int qtdeDePerfumes = carrinho.getQuantidadeProdutos(Perfume.class);
        
        assertEquals(qtdeDePerfumes, 3);
    }
    
    /*
        CT12 -  Ao esvaziar o carrinho a quantidade de itens deve ser 0
    */
    @Test
    public void esvaziarCarrinho() throws QuantidadeInsuficienteException {
        Perfume doce = new Perfume("Perfume doce", 50.00);
        Perfume amadeirado = new Perfume("PerfumeAmadeirado", 100.00);
        Eletronico tv = new Eletronico("TV 20' LG", 1500.00);
        Livro cabana = new Livro("A cabana", 49.90);
        
        carrinho.add(doce, 2);
        carrinho.add(amadeirado);
        carrinho.add(tv);
        carrinho.add(cabana);
    
        carrinho.clear();
    
        assertEquals(carrinho.getQuantidadeItens(), 0);
    }
    
    
}

    
