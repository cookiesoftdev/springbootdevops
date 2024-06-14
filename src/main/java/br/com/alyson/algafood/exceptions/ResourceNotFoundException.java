package br.com.alyson.algafood.exceptions;

public class ResourceNotFoundException extends RuntimeException{

        public ResourceNotFoundException(String message) {
            super(message);
        }
}
