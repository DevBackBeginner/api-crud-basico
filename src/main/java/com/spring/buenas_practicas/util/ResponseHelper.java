package com.spring.buenas_practicas.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spring.buenas_practicas.dto.MessageResponse;

public class ResponseHelper {
	
	private ResponseHelper() { }
	
	public static <T> MessageResponse<T> of(String message, T data){
        return MessageResponse.<T>builder()
        		.message(message)
        		.data(data)
    		.build();
	}
	
	public static <T> ResponseEntity<MessageResponse<T>> ok(String message, T data){
		return ResponseEntity.status(HttpStatus.OK).body(of(message, data));
	}
	
	public static <T> ResponseEntity<MessageResponse<T>> create(String message, T data){
		return ResponseEntity.status(HttpStatus.CREATED).body(of(message, data));
	}
	
	public static <T> ResponseEntity<MessageResponse<T>> notFound(String message, T data){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(of(message, data));
	}
	
	public static <T> ResponseEntity<MessageResponse<T>> badRequest(String message, T data){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(of(message, data));
	}
	
	public static <T> ResponseEntity<MessageResponse<T>> internalError(String message, T data){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(of(message, data));
	}
	
	public static <T> ResponseEntity<MessageResponse<T>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
