package ifmt.cba.servico;

import ifmt.cba.negocio.GerenciadorEstoque;
import ifmt.cba.vo.ProdutoVO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/produtos")
public class ServicoGerenciadorEstoque {

    private static GerenciadorEstoque gerenciadorEstoque;

    static {
        gerenciadorEstoque = new GerenciadorEstoque();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarProduto(ProdutoVO produtoVO) {
        String retorno;
        try {
            gerenciadorEstoque.adicionarProduto(produtoVO);
            retorno = "Produto adicionado com sucesso.";
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        ResponseBuilder resposta = Response.ok();
        resposta.entity(retorno);
        return resposta.build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerProduto(ProdutoVO produtoVO) {
        String retorno;
        try {
            gerenciadorEstoque.removerProduto(produtoVO);
            retorno = "Produto removido com sucesso.";
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        ResponseBuilder resposta = Response.ok();
        resposta.entity(retorno);
        return resposta.build();
    }

    @PUT
    @Path("/adicionar/{qtde}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarEstoqueProduto(@PathParam("qtde") int quantidade, ProdutoVO produtoVO) throws Exception {
        gerenciadorEstoque.adicionarEstoqueProduto(produtoVO, quantidade);
    }

    @PUT
    @Path("/baixar/{qtde}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void baixarEstoqueProduto(@PathParam("qtde") int quantidade, ProdutoVO produtoVO) throws Exception {
        gerenciadorEstoque.baixarEstoqueProduto(produtoVO, quantidade);
    }

    @GET
    @Path("/contar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response contadorProduto() {
        int total = gerenciadorEstoque.contadorProduto();
        ResponseBuilder resposta = Response.ok();
        resposta.entity(total);
        return resposta.build();
    }

    @GET
    @Path("/buscar/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarProdutoPorCodigo(@PathParam("codigo") String codigo) throws Exception {
        ProdutoVO retorno = gerenciadorEstoque.buscarProdutoPorCodigo(Integer.parseInt(codigo));
        ResponseBuilder resposta = Response.ok();
        resposta.entity(retorno);
        return resposta.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaProduto() {
        ResponseBuilder resposta = Response.ok();
        resposta.entity(gerenciadorEstoque.listaProduto());
        return resposta.build();
    }

    @GET
    @Path("/estoque")
    public Response totalEstoqueFisico() {
        ResponseBuilder resposta = Response.ok();
        resposta.entity(gerenciadorEstoque.totalEstoqueFisico());
        return resposta.build();
    }

}
