package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO eventDto) {
		try {
			Event objEvent = repository.getOne(id);
			objEvent.setName(eventDto.getName());
			objEvent.setDate(eventDto.getDate());
			objEvent.setUrl(eventDto.getUrl());
			objEvent.setCity(new City(eventDto.getCityId(), null));
			
			objEvent = repository.save(objEvent);
			
			return new EventDTO(objEvent);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso Não Encontrado Id: " + id);
		}
	}

}
