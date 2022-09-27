package com.example.project.Service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project.Model.Type;
import com.example.project.Repository.TypeRepository;

@Service
public class TypeService {

	private final TypeRepository typeRepository;

	@Autowired
	public TypeService(TypeRepository typeRepository) {
		this.typeRepository = typeRepository;
	}

	public List<Type> getTypes() {
		return typeRepository.findAll();
	}

	public void addNewType(Type type) {
		typeRepository.save(type);
	}

	public void deleteType(int typeId) {
		boolean exists = typeRepository.existsById(typeId);
		if (!exists) {
			throw new IllegalStateException("type with id " + typeId + " does not exist");
		}
		typeRepository.deleteById(typeId);
	}

	@Transactional
	public void updateType(int typeId, String typeName) {
		Type type = typeRepository.findById(typeId)
				.orElseThrow(() -> new IllegalStateException("type with id " + typeId + " does not exist"));

		if (typeName != null && typeName.length() > 0 && !Objects.equals(type.getTypeName(), typeName)) {
			type.setTypeName(typeName);
		}
	}
}
