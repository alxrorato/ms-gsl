package com.dev.gslentrega.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {

	private String message;
    private String field;
    private Object parameter;

}
