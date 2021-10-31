package com.dev.gslsfc.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.dev.gslsfc.request.CteRequest;
import com.dev.gslsfc.response.CteResponse;
import com.dev.gslsfc.utils.RandomUtils;

@Service
public class SfcServiceImpl implements SfcService {

	private static final long START_RANDOM_NUMBER_PROTOCOLO_RECEBIMENTO_CTE = 100000000000000L;
	private static final long END_RANDOM_NUMBER_PROTOCOLO_RECEBIMENTO_CTE = 999999999999999L;	
	
	@Override
	public CteResponse cadastrarCte(@Valid CteRequest cteRequest) {
		
		CteResponse cteResponse = new CteResponse();
		
		if (cteRequest != null) {
			cteResponse.setCteRecebido(true);
			cteResponse.setMensagem("Ct-e recebido com sucesso. Apuração financeira iniciada.");
			cteResponse.setProtocoloRecebimentoSfc(RandomUtils.getRandomNumber(START_RANDOM_NUMBER_PROTOCOLO_RECEBIMENTO_CTE, 
						END_RANDOM_NUMBER_PROTOCOLO_RECEBIMENTO_CTE));
		} else {
			cteResponse.setCteRecebido(false);
			cteResponse.setMensagem("Ct-e não recebido.");
			cteResponse.setProtocoloRecebimentoSfc(null);
		}
		
		return cteResponse;
	}

}
