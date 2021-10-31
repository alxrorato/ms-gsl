package com.dev.gslparceira.service;

import com.dev.gslparceira.response.ParceiraResponse;

public interface ParceiraService {

	ParceiraResponse solicitarParceria(Long cnpjSolicitante);
}
