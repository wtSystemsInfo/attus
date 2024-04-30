
package com.attusteste.attusteste.services;

import com.attusteste.attusteste.DTO.PersonRequestDTO;
import com.attusteste.attusteste.DTO.PersonResponseDTO;
import com.attusteste.attusteste.entity.Person;
import com.attusteste.attusteste.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 *
 * @author William Toloto
 */
@Service
public class PersonService {
    
    @Autowired
    private PersonRepository repository;
    
    
    public Person findPersonById(Long id) throws Exception{
        return repository.findPersonById(id).orElseThrow(() -> new Exception("Pessoa não encontrada!"));
    }
    
    
    public List<PersonResponseDTO> savePerson(PersonRequestDTO data){
        Person personData = new Person(data);
        repository.save(personData);
        return listAll();
    }
    
    
    public List<PersonResponseDTO> listAll(){
        List<PersonResponseDTO> personList = repository.findAll().stream().map(PersonResponseDTO::new).toList();    
        return personList;
    }
    
    
    public List<PersonResponseDTO> updatePerson(PersonRequestDTO data){
        Person personData = new Person(data);
        repository.save(personData);
        return listAll();
    }
 
}
