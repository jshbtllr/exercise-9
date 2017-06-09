package com.exercise9.core.service;

public interface CrudServiceInterface <T> {
	public T create(T created);
	public List <T> read(Integer sort, Integer order);
	public T delete(Long id);
	public T update(T updated);
}