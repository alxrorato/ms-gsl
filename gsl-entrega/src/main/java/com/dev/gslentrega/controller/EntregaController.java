package com.dev.gslentrega.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.gslentrega.entities.Entrega;
import com.dev.gslentrega.errors.ServicoIndisponivelException;
import com.dev.gslentrega.request.CalculoFreteRequest;
import com.dev.gslentrega.request.EntregaRequest;
import com.dev.gslentrega.request.SolicitacaoRequest;
import com.dev.gslentrega.response.AndamentoEntregaResponse;
import com.dev.gslentrega.response.CalculoFreteResponse;
import com.dev.gslentrega.response.CancelamentoResponse;
import com.dev.gslentrega.response.Cliente;
import com.dev.gslentrega.response.ConfirmacaoEntregaResponse;
import com.dev.gslentrega.response.EmissaoCteResponse;
import com.dev.gslentrega.response.OperacaoEtapaResponse;
import com.dev.gslentrega.response.PagamentoResponse;
import com.dev.gslentrega.service.EntregaService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/entregas")
@Slf4j
@Api(value = "Endpoints do módulo de Entregas")
public class EntregaController {

	@Autowired
	private EntregaService entregaService;
	
	@GetMapping
	@ApiOperation(value = "Retorna todas as solicitacoes de entregas cadastradas no sistema", response = List.class)
	@ApiResponse(code = 200, message = "Lista de todas as entregas retornada com sucesso")
	public ResponseEntity<List<Entrega>> findAll() {
		List<Entrega> list = entregaService.buscarEntregas();
		return ResponseEntity.ok(list);
	}	

	@GetMapping(value = "/buscarPorId/{id}")
	@ApiOperation(value = "Retorna uma entrega ao ser informado um id", response = Entrega.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Entrega retornada com sucesso"),
			@ApiResponse(code = 404, message = "Entrega não encontrada")
    })
	public ResponseEntity<Entrega> findById(
			@ApiParam(name = "id", value = "Id do registro no banco de dados") @PathVariable Long id) {
		
		Entrega entrega = entregaService.buscarEntregaById(id);
		return ResponseEntity.ok(entrega);
	}

	@GetMapping(value = "/buscarPorCodigoSolicitacao/{codigoSolicitacao}")
	@ApiOperation(value = "Retorna uma entrega ao ser informado um código de solicitação", response = Entrega.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Entrega retornada com sucesso"),
			@ApiResponse(code = 404, message = "Entrega não encontrada")
    })
	public ResponseEntity<Entrega> findByCodigoSolicitacao(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega") 
			@PathVariable Long codigoSolicitacao) {
		
		Entrega entrega = entregaService.buscarEntregaByCodigoSolicitacao(codigoSolicitacao);
		return ResponseEntity.ok(entrega);
	}
	
	@HystrixCommand(fallbackMethod = "buscarClienteAlternativo")
	@GetMapping(value = "/buscarCliente/{cnpj}")
	@ApiOperation(value = "Retorna um cliente ao ser informado um CNPJ", response = Cliente.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente retornado com sucesso"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")
    })
	public ResponseEntity<Cliente> buscarCliente(
			@ApiParam(name = "cnpj", value = "CNPJ do cliente") 
			@PathVariable Long cnpj) {
		log.info("Dentro do endpoint buscarCliente no gsl-entrega buscando pelo cnpj [{}]", cnpj);
		Cliente cliente = entregaService.buscarCliente(cnpj);
		return ResponseEntity.ok(cliente);
	}

	public ResponseEntity<Cliente> buscarClienteAlternativo(Long cnpj) {
		log.info("Dentro do método buscarClienteAlternativo");
		throw new ServicoIndisponivelException("Serviço de clientes indisponível. Tente mais tarde.");
	}
	
	/* No momento não é necessário 
	@PutMapping("atualizarEntrega")
	//@ApiOperation(value = "Atualiza dados do andamento de uma entrega")
	public ResponseEntity<?> atualizarEntrega(@RequestBody SolicitacaoRequest solicitacaoRequest) {
		entregaService.atualizarEntrega(solicitacaoRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	*/
	
	@GetMapping(value = "/consultarAndamentoEntrega/{codigoSolicitacao}")
	@ApiOperation(value = "Consulta o andamento de uma entrega ao ser informado seu código de solicitação", 
		response = AndamentoEntregaResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Andamento da entrega retornado com sucesso"),
			@ApiResponse(code = 404, message = "Entrega não encontrada")
    })
	public ResponseEntity<AndamentoEntregaResponse> consultarAndamentoEntrega(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega")
			@PathVariable Long codigoSolicitacao) {
		AndamentoEntregaResponse andamentoEntregaResponse = entregaService.consultarAndamentoEntrega(codigoSolicitacao);
		return ResponseEntity.ok(andamentoEntregaResponse);
	}

	@GetMapping(value = "/estimarCalculoFrete")
	@ApiOperation(value = "Estimar cálculo do frete", response = CalculoFreteResponse.class)
	@ApiResponse(code = 200, message = "Solicitação de entrega criada", response = CalculoFreteResponse.class)
	public ResponseEntity<CalculoFreteResponse> estimarCalculoFrete(@Valid @RequestBody CalculoFreteRequest calculoFreteRequest) {
		CalculoFreteResponse calculoFreteResponse = entregaService.estimarCalculoFrete(calculoFreteRequest);
		return ResponseEntity.ok(calculoFreteResponse);
	}
	
	@PostMapping("solicitar")
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "Solicitar entrega", response = Entrega.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Solicitação de entrega cadastrada"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")
		})
	public ResponseEntity<Entrega> solicitarEntrega(@Valid @RequestBody EntregaRequest entregaRequest) {
		return new ResponseEntity<>(entregaService.cadastrarEntrega(entregaRequest), HttpStatus.CREATED);
	}

	@PatchMapping("efetuarPagamento/{codigoSolicitacao}")
	@ApiOperation(value = "Efetuar o pagamento do serviço de transporte solicitado", response = PagamentoResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Pagamento efetuado com sucesso"),
		@ApiResponse(code = 404, message = "Entrega não encontrada"),
		@ApiResponse(code = 422, message = "Pagamento já efetuado")
	})
	public ResponseEntity<PagamentoResponse> efetuarPagamento(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega") 
			@PathVariable Long codigoSolicitacao) {
		
		PagamentoResponse pagamentoResponse = entregaService.efetuarPagamento(codigoSolicitacao);
		return ResponseEntity.ok(pagamentoResponse);
	}
	
	@PostMapping("emitirCte/{codigoSolicitacao}")
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "Emitir o CT-e do transporte a ser realizado ao sistema legado SFC para que ele "
			+ "realize os devidos controles.", response = EmissaoCteResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "CT-e emitido ao SFC"),
		@ApiResponse(code = 404, message = "Entrega não encontrada"),
		@ApiResponse(code = 422, message = "Operação não efetuada")
	})
	public ResponseEntity<EmissaoCteResponse> emitirCte(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega")
			@PathVariable Long codigoSolicitacao) {
		
		EmissaoCteResponse emissaoCteResponse = entregaService.emitirCte(codigoSolicitacao);
		return new ResponseEntity<>(emissaoCteResponse, emissaoCteResponse.isEmissaoOk() ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@PatchMapping("coletarCarga/{codigoSolicitacao}")
	@ApiOperation(value = "Efetuar a etapa de coleta da carga", response = OperacaoEtapaResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operação efetuada"),
		@ApiResponse(code = 404, message = "Entrega não encontrada"),
		@ApiResponse(code = 422, message = "Operação não efetuada")
	})
	public ResponseEntity<OperacaoEtapaResponse> coletarCarga(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega") 
			@PathVariable Long codigoSolicitacao) {
		
		return new ResponseEntity<>(entregaService.coletarCarga(codigoSolicitacao), HttpStatus.OK);
	}
	
	@PatchMapping("efetuarRoteirizacao/{codigoSolicitacao}")
	@ApiOperation(value = "Efetuar a etapa de roteirização do transporte da carga", response = OperacaoEtapaResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operação efetuada"),
		@ApiResponse(code = 404, message = "Entrega não encontrada"),
		@ApiResponse(code = 422, message = "Operação não efetuada")
	})
	public ResponseEntity<OperacaoEtapaResponse> efetuarRoteirizacao(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega")
			@PathVariable Long codigoSolicitacao) {
		return new ResponseEntity<>(entregaService.efetuarRoteirizacao(codigoSolicitacao), HttpStatus.OK);
	}

	@PatchMapping("iniciarTransporte/{codigoSolicitacao}")
	@ApiOperation(value = "Efetuar a etapa de iniciar o transporte até o CD (centro de distribuição) "
			+ "mais próximo do cliente", response = OperacaoEtapaResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operação efetuada"),
		@ApiResponse(code = 404, message = "Entrega não encontrada"),
		@ApiResponse(code = 422, message = "Operação não efetuada")
	})
	public ResponseEntity<OperacaoEtapaResponse> iniciarTransporte(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega")
			@PathVariable Long codigoSolicitacao) {
		
		return new ResponseEntity<>(entregaService.iniciarTransporte(codigoSolicitacao), HttpStatus.OK);
	}
	
	@PatchMapping("distribuirNosCds/{codigoSolicitacao}")
	@ApiOperation(value = "Efetuar a etapa de distribuição da carga no CD mais próximo do cliente final", 
		response = OperacaoEtapaResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operação efetuada"),
		@ApiResponse(code = 404, message = "Entrega não encontrada"),
		@ApiResponse(code = 422, message = "Operação não efetuada")
	})
	public ResponseEntity<OperacaoEtapaResponse> distribuirNosCds(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega")
			@PathVariable Long codigoSolicitacao) {
		return new ResponseEntity<>(entregaService.distribuirNosCds(codigoSolicitacao), HttpStatus.OK);
	}
	
	@PatchMapping("iniciarLastMile/{codigoSolicitacao}")
	@ApiOperation(value = "Efetuar a etapa de iniciar o Last-mile (última milha de transporte até o cliente final)", 
		response = OperacaoEtapaResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operação efetuada"),
		@ApiResponse(code = 404, message = "Entrega não encontrada"),
		@ApiResponse(code = 422, message = "Operação não efetuada")
	})
	public ResponseEntity<OperacaoEtapaResponse> iniciarLastMile(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega")
			@PathVariable Long codigoSolicitacao) {
		
		return new ResponseEntity<>(entregaService.iniciarLastMile(codigoSolicitacao), HttpStatus.OK);
	}
	
	@PatchMapping("finalizarEntrega/{codigoSolicitacao}")
	@ApiOperation(value = "Efetuar a etapa de entrega da mercadoria ao cliente final", response = ConfirmacaoEntregaResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operação efetuada"),
		@ApiResponse(code = 404, message = "Entrega não encontrada"),
		@ApiResponse(code = 422, message = "Operação não efetuada")
	})
	public ResponseEntity<ConfirmacaoEntregaResponse> finalizarEntrega(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega")
			@PathVariable Long codigoSolicitacao) {
		
		return new ResponseEntity<>(entregaService.finalizarEntrega(codigoSolicitacao), HttpStatus.OK);
	}
	
	@DeleteMapping("cancelarEntrega/{codigoSolicitacao}")
	@ApiOperation(value = "Cancelar o processo de entrega ao cliente final", response = OperacaoEtapaResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Operação efetuada"),
		@ApiResponse(code = 404, message = "Entrega não encontrada"),
		@ApiResponse(code = 422, message = "Operação não efetuada")
	})
	public ResponseEntity<CancelamentoResponse> cancelarEntrega(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega")
			@PathVariable Long codigoSolicitacao) {
		
		return new ResponseEntity<>(entregaService.cancelarEntrega(codigoSolicitacao), HttpStatus.OK);
	}
}
