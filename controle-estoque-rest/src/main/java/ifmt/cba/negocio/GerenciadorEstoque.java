package ifmt.cba.negocio;

import java.util.ArrayList;

import ifmt.cba.vo.ProdutoVO;

public class GerenciadorEstoque {

    private ArrayList<ProdutoVO> listaProduto;

    public GerenciadorEstoque() {
        this.listaProduto = new ArrayList<ProdutoVO>();
    }

    public void adicionarProduto(ProdutoVO produtoVO) throws Exception {
        if (produtoVO != null) {
            if (this.buscarProdutoPorCodigo(produtoVO.getCodigo()) == null) {
                this.listaProduto.add(produtoVO);
            } else {
                throw new Exception("Produto já existe. ");
            }
        } else {
            throw new Exception("Produto não pode ser nulo");
        }
    }

    public void removerProduto(ProdutoVO produto) throws Exception {
        if (produto != null) {
            if (this.listaProduto.indexOf(produto) >= 0) {
                this.listaProduto.remove(produto);
            } else {
                throw new Exception("Produto não localizado");
            }
        } else {
            throw new Exception("Produto não pode ser nulo. ");
        }
    }

    public void adicionarEstoqueProduto(ProdutoVO produto, int quantidade) throws Exception {
        if (produto != null || quantidade > 0) {
            if (this.listaProduto.indexOf(produto) >= 0) {
                ProdutoVO produtoTemp = this.listaProduto.get(this.listaProduto.indexOf(produto));
                produtoTemp.adicionarEstoque(quantidade);
            } else {
                throw new Exception("Produto não localizado. ");
            }
        } else {
            throw new Exception("Produto ou quantidade inexistente. ");
        }
    }

    public void baixarEstoqueProduto(ProdutoVO produto, int quantidade) throws Exception {
        if (produto != null || quantidade > 0) {
            if (this.listaProduto.indexOf(produto) >= 0) {
                ProdutoVO produtoTemp = this.listaProduto.get(this.listaProduto.indexOf(produto));
                produtoTemp.baixarEstoque(quantidade);
            } else {
                throw new Exception("Produto não localizado. ");
            }
        } else {
            throw new Exception("Produto ou quantidade inexistente. ");
        }
    }

    public ProdutoVO buscarProdutoPorCodigo(int codigo) throws Exception {
        ProdutoVO produtoTemp = null;
        for (ProdutoVO produtoVO : this.listaProduto) {
            if (produtoVO.getCodigo() == codigo) {
                produtoTemp = produtoVO;
                break;
            }
        }
        return produtoTemp;
    }

    public int contadorProduto() {
        return this.listaProduto.size();
    }

    public ArrayList<ProdutoVO> listaProduto() {
        return this.listaProduto;
    }

    public int totalEstoqueFisico() {
        int total = 0;
        for (ProdutoVO produtoVO : this.listaProduto) {
            total += produtoVO.getEstoque();
        }
        return total;
    }

    public float totalizarValorEstoqueProduto(ProdutoVO produto) throws Exception {
        float total = 0;
        if (produto != null) {
            if (produto.getValorUnitario() != 0 || produto.getEstoque() != 0) {
                total = produto.getValorUnitario() * produto.getEstoque();
                return total;
            } else {
                throw new Exception("Produto não possui estoque ou valor unitário ");
            }
        } else {
            throw new Exception("Produto não pode ser nulo. ");
        }
    }

    public float totalizarValorEstoqueGeral() throws Exception {
        float total = 0;
        if (!this.listaProduto.isEmpty()) {
            for (ProdutoVO produto : this.listaProduto) {
                total += produto.getEstoque() * produto.getValorUnitario();
            }
        }else{
            throw new Exception("Lista de produtos está vazia. Insira um produto.");
        }
        return total;
    }
}
