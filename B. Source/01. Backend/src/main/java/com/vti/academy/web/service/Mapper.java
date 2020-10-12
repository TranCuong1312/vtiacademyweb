package com.vti.academy.web.service;

/**
 * Mapper interface - Mapping between DTO and entity
 * 
 * @param T : DTO
 * @param X : Entity
 */

public interface Mapper<T, X> {

	public T mapToDTO(X entity);

	public X mapToEntity(T dto);
}
