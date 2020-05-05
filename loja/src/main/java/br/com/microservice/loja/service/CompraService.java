package br.com.microservice.loja.service;

import br.com.microservice.loja.client.FornecedorClient;
import br.com.microservice.loja.dto.InfoPedidoDTO;
import br.com.microservice.loja.model.Compra;
import br.com.microservice.loja.dto.CompraDTO;
import br.com.microservice.loja.dto.InfoFornecedorDTO;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

	//MODO FAZENDO USO DO FeignClient
	@Autowired
	private FornecedorClient fornecedorClient;

	public Compra realizaCompra(CompraDTO compra) {
		final String estado = compra.getEndereco().getEstado();

		LOG.info("Buscando informações do fornecdor de {}", estado);
		InfoFornecedorDTO info = fornecedorClient.getInforPorEstado(compra.getEndereco().getEstado());

		LOG.info("Realizando um pedido");
		InfoPedidoDTO pedido = fornecedorClient.realizaPedidos(compra.getItens());

		System.out.println(info.getEndereco());

		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
		compraSalva.setEnderecoDeDestino(compra.getEndereco().toString());

		return compraSalva;
	}

	//MANEIRA DE REALIZAR REQUEST PARA OUTRO SERVICE USANDO RESTTEMPLATE
	/*@Autowired
	private RestTemplate client;

	@Autowired
	private DiscoveryClient eurekaClient;

	public void realizaCompra(CompraDTO compra) {
		
		ResponseEntity<InfoFornecedorDTO> exchange = 
			client.exchange("http://fornecedor/info/"+compra.getEndereco().getEstado(),
			HttpMethod.GET, null, InfoFornecedorDTO.class);

		eurekaClient.getInstances("fornecedor").stream()
				.forEach(fornecedor ->
				{
					System.out.println("localhost: " + fornecedor.getPort());
				});
		
		System.out.println(exchange.getBody().getEndereco());
	}*/
}
