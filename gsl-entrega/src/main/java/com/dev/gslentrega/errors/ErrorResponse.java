package com.dev.gslentrega.errors;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private String message;
    private int code;
    private String status;
    private String objectName;
    private List<ErrorModel> errors;
	
}
