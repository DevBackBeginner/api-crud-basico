package com.spring.buenas_practicas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class MessageResponse<T> {
	private String message;
	private T data;
}
