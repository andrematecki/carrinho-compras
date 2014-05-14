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
    @Test(expected = ProdutoInexistenteExpected.class)
    public void criandoCarrinhoVazio(){
        boolean isEmpty = carrinho.isEmpty();
        assertEquals(isEmpty, true);
    }
    
    /*
        CT02 -  Ao adicionar um produto inexistente no 
                carrinho deve ser criado um item
    */
    @Test
    public void adicionandoPrimeiroProduto() throws ProdutoInexistenteExpected{
        int qtdeItensAntiga = carrinho.getQuantidadeItens();
        Livro java = new Livro("Aprendendo Java", 50.00);
        carrinho.add(java);
        int qtdeItensNova = carrinho.getQuantidadeItens();
        assertEquals((qtdeItensAntiga + 1), qtdeItensNova);
    }
    
    /*
        CT03 -  Ao adicionar um produto existente no carrinho a quantidade de
                itens não deve ser modificada.
    */
    @Test
    public void adicionandoProdutosAoMesmoItem(){
        Eletronico celular = new Eletronico("Iphone 5", 1.500);
        carrinho.add(celular);
        int qtdeAntiga = carrinho.getQuantidadeItens();
        carrinho.add(celular);
        carrinho.add(celular);
        int qtdeNova = carrinho.getQuantidadeItens();
        assertEquals(qtdeAntiga, qtdeNova);
    }
    
    /*
        CT04 -  Ao adicionar um produto existente no carrinho a nova quantidade
                do produto deve ser a antiga + 1
    */
    @Test
    public void adicionandoMaisProdutosExistentes() throws ProdutoInexistenteExpected{
        Eletronico tv = new Eletronico("TV LED 50' Samsumg", 5.500);
        carrinho.add(tv);
        int qtdeAntiga = carrinho.getQuantidadeProdutos(tv);
        carrinho.add(tv);
        assertEquals(qtdeAntiga, (qtdeAntiga + 1));
    }
    
    /*
        CT05 -  Não deve ser possível obter a quantidade de um produto
                inexistente no carrinho
    */
    @Test(expected = ProdutoInexistenteExpected.class)
    public void obtendoQuantidadeProdutoInexistente() throws ProdutoInexistenteExpected{
        Eletronico dvd = new Eletronico("Aparelho de DVD LG", 150.00);
        Integer qtdeProdutos = carrinho.getQuantidadeProdutos(dvd);
        assertEquals(qtdeProdutos, null);
    }
    
    /*
        CT06 -  Ao remover um produto do carrinho a quantidade desse produto deve ser a 
                antiga menos 1
    */
    @Test
    public void removendoUmProdutoExistente() throws ProdutoInexistenteExpected{
        Livro deitel = new Livro("Aprendendo a programar JAVA", 150.00);
        carrinho.add(deitel);
        carrinho.add(deitel);
        carrinho.add(deitel);
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
    public void removendoUmProdutoComUmaQuantidade(){
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
    @Test(expected = ProdutoInexistenteExpected.class)
    public void removendoProdutoInexistente(){
        Perfumaria perfume = new Perfumaria("Desodorante masculino", 14.90);
        carrinho.remove(perfume);
    }
    
    /*
        CT09 -  A lista de produtos do carrinho deve conter os mesmos produtos 
                que foram adicionas.
    */
    @Test
    public void obtendoListaDeProdutosDoCarrinho(){
        Livro biblia = new Livro("A biblia de c#", 250.00);
        Eletronico celular = new Eletronico("Home Theater Sony", 2500.00);
        Perfumaria perfume = new Perfumaria("perfume feminino", 150.00);
        
        List<Produto> produtosAdicionar = new ArrayList<>(
                Arrays.asList(biblia, celular, perfume));
        
        carrinho.add(celular);
        carrinho.add(biblia);
        carrinho.add(perfume);
        
        List<Produto> produtosAdicionados = carrinho.getProdutos();
        
        assertArrayEquals(produtosAdicionar.toArray(), produtosAdicionados.toArray());
    }
    
    /*
        CT10 -  O total a ser pago deve ser a quantidade de cada 
                produto x o valor do mesmo
    */
    
    /*
        CT11 -  Deve ser possivel adicionar produtos de diferentes tipos
    */
}
