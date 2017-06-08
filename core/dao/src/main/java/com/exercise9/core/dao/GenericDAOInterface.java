package com.exercise9.core.dao;

public interface GenericDAO {
	
	public Type add(Type added);
	public Type update(Type updated);
	public Type delete(Type deleted);
	public Type get(Class<Type> entity, Long id);
}

