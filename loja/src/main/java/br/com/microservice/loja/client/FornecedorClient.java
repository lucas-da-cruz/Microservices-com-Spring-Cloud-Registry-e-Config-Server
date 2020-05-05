package br.com.microservice.loja.client;

import br.com.microservice.loja.dto.InfoFornecedorDTO;
import br.com.microservice.loja.dto.InfoPedidoDTO;
import br.com.microservice.loja.dto.ItemDaCompraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("fornecedor")
public interface FornecedorClient {

    @RequestMapping("/info/{estado}")
    InfoFornecedorDTO getInforPorEstado(@PathVariable String estado);

    @RequestMapping(method = RequestMethod.POST, value = "/pedido")
    InfoPedidoDTO realizaPedidos(List<ItemDaCompraDTO> itens);
    
}
