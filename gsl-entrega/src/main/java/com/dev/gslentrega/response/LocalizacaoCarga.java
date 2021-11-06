package com.dev.gslentrega.response;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(description = "Localização da carga")
public class LocalizacaoCarga implements Serializable {

	private static final long serialVersionUID = 6823676838248465565L;
	
	@ApiModelProperty(name = "latitude", value = "Latitude referente a localização atual da carga", example = "47.8302")
	private BigDecimal latitude;
	
	@ApiModelProperty(name = "longitude", value = "Longitude referente a localização atual da carga", example = "80,8044")
	private BigDecimal longitude;
}
