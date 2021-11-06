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
import com.dev.gslentrega.response.Cliente;
import com.dev.gslentrega.response.EmissaoCteResponse;
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
	
	@HystrixCommand(fallbackMethod = "getClienteAlternativo")
	@GetMapping(value = "/getCliente/{cnpj}")
	@ApiOperation(value = "Retorna um cliente ao ser informado um CNPJ", response = Cliente.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente retornado com sucesso"),
			@ApiResponse(code = 404, message = "Cliente não encontrado")
    })
	public ResponseEntity<Cliente> getCliente(
			@ApiParam(name = "cnpj", value = "CNPJ do cliente") 
			@PathVariable Long cnpj) {
		log.info("Dentro do endpoint getCliente no gsl-entrega buscando pelo cnpj [{}]", cnpj);
		Cliente cliente = entregaService.getCliente(cnpj);
		return ResponseEntity.ok(cliente);
	}

	public ResponseEntity<Cliente> getClienteAlternativo(Long cnpj) {
		log.info("Dentro do metodo endpoint getCliente getClienteAlternativo");
		throw new ServicoIndisponivelException("Serviço de clientes indisponível. Tente mais tarde.");
	}
	
	@PutMapping("atualizarEntrega")
	@ApiOperation(value = "Atualiza dados do andamento de uma entrega")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Entrega atualizada"),
			@ApiResponse(code = 404, message = "Entrega não encontrada")
    })
	public ResponseEntity<?> atualizarEntrega(@RequestBody SolicitacaoRequest solicitacaoRequest) {
		entregaService.atualizarEntrega(solicitacaoRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/findProgressByRequestCode/{codigoSolicitacao}")
	@ApiOperation(value = "Consulta o andamento de uma entrega ao ser informado seu código de solicitação", 
		response = Entrega.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Entrega retornada com sucesso"),
			@ApiResponse(code = 404, message = "Entrega não encontrada")
    })
	public ResponseEntity<AndamentoEntregaResponse> findProgressByRequestCode(
			@ApiParam(name = "codigoSolicitacao", value = "Código de solicitação da entrega")
			@PathVariable Long codigoSolicitacao) {
		AndamentoEntregaResponse andamentoEntregaResponse = entregaService.findProgressByRequestCode(codigoSolicitacao);
		return ResponseEntity.ok(andamentoEntregaResponse);
	}

	@GetMapping(value = "/estimarCalculoFrete")
	public ResponseEntity<CalculoFreteResponse> estimarCalculoFrete(@Valid @RequestBody CalculoFreteRequest calculoFreteRequest) {
		CalculoFreteResponse calculoFreteResponse = entregaService.estimarCalculoFrete(calculoFreteRequest);
		return ResponseEntity.ok(calculoFreteResponse);
	}
	
	@PostMapping("solicitar")
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "Solicitar entrega", response = Entrega.class)
	@ApiResponse(code = 201, message = "Solicitação de entrega criada", response = Entrega.class)
	public ResponseEntity<?> solicitarEntrega(@Valid @RequestBody EntregaRequest entregaRequest) {
		return new ResponseEntity<>(entregaService.cadastrarEntrega(entregaRequest), HttpStatus.CREATED);
	}

	@PatchMapping("efetuarPagamento/{codigoSolicitacao}")
	public ResponseEntity<?> efetuarPagamento(@PathVariable Long codigoSolicitacao) {
		return new ResponseEntity<>(entregaService.efetuarPagamento(codigoSolicitacao), HttpStatus.OK);
	}
	
	@PostMapping("emitirCte/{codigoSolicitacao}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> emitirCte(@PathVariable Long codigoSolicitacao) {
		EmissaoCteResponse emissaoCteResponse = entregaService.emitirCte(codigoSolicitacao);
		return new ResponseEntity<>(emissaoCteResponse, emissaoCteResponse.isEmissaoOk() ? HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@PatchMapping("coletarCarga/{codigoSolicitacao}")
	public ResponseEntity<?> coletarCarga(@PathVariable Long codigoSolicitacao) {
		return new ResponseEntity<>(entregaService.coletarCarga(codigoSolicitacao), HttpStatus.OK);
	}
	
	@PatchMapping("efetuarRoteirizacao/{codigoSolicitacao}")
	public ResponseEntity<?> efetuarRoteirizacao(@PathVariable Long codigoSolicitacao) {
		return new ResponseEntity<>(entregaService.efetuarRoteirizacao(codigoSolicitacao), HttpStatus.OK);
	}

	@PatchMapping("iniciarTransporte/{codigoSolicitacao}")
	public ResponseEntity<?> iniciarTransporte(@PathVariable Long codigoSolicitacao) {
		return new ResponseEntity<>(entregaService.iniciarTransporte(codigoSolicitacao), HttpStatus.OK);
	}
	
	@PatchMapping("distribuirNosCds/{codigoSolicitacao}")
	public ResponseEntity<?> distribuirNosCds(@PathVariable Long codigoSolicitacao) {
		return new ResponseEntity<>(entregaService.distribuirNosCds(codigoSolicitacao), HttpStatus.OK);
	}
	
	@PatchMapping("iniciarLastMile/{codigoSolicitacao}")
	public ResponseEntity<?> iniciarLastMile(@PathVariable Long codigoSolicitacao) {
		return new ResponseEntity<>(entregaService.iniciarLastMile(codigoSolicitacao), HttpStatus.OK);
	}
	
	@PatchMapping("finalizarEntrega/{codigoSolicitacao}")
	public ResponseEntity<?> finalizarEntrega(@PathVariable Long codigoSolicitacao) {
		return new ResponseEntity<>(entregaService.finalizarEntrega(codigoSolicitacao), HttpStatus.OK);
	}
	
	@DeleteMapping("cancelarEntrega/{codigoSolicitacao}")
	public ResponseEntity<?> cancelarEntrega(@PathVariable Long codigoSolicitacao) {
		return new ResponseEntity<>(entregaService.cancelarEntrega(codigoSolicitacao), HttpStatus.OK);
	}
}
