package com.dev.gslsfc.service;

import javax.validation.Valid;

import com.dev.gslsfc.request.CteRequest;
import com.dev.gslsfc.response.CteResponse;

public interface SfcService {

	CteResponse cadastrarCte(@Valid CteRequest cteRequest);

}
