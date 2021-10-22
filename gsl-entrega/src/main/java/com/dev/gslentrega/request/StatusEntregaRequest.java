package com.dev.gslentrega.request;

import java.time.LocalDateTime;

import com.dev.gslentrega.enums.StatusEntrega;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusEntregaRequest {
	private StatusEntrega statusEntrega;
}
