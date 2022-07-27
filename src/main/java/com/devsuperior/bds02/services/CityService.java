package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> list = repository.findAll(Sort.by("name"));
		return list.stream().map(objCity -> new CityDTO(objCity)).collect(Collectors.toList());
	}
	
	@Transactional
	public CityDTO insert(CityDTO cityDto) {
		City objCity = new City();
		objCity.setName(cityDto.getName());
		objCity = repository.save(objCity);
		return new CityDTO(objCity);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

}
