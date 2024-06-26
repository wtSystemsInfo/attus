package com.attusteste.attusteste.controller;

import com.attusteste.attusteste.DTO.PersonRequestDTO;
import com.attusteste.attusteste.DTO.PersonResponseDTO;
import com.attusteste.attusteste.entity.Person;
import com.attusteste.attusteste.services.PersonService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Wiliam Toloto
 */
@RestController()
@RequestMapping("/persons")
public class PersonController {
    
    
    @Autowired
    private PersonService service;
    
    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody  PersonRequestDTO person) throws Exception{

        Person newPerson = service.savePerson(person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);

    } 
    
    
    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> getAllPersons(){
        List<PersonResponseDTO> listPersons =  service.listAll();
        return new ResponseEntity<>(listPersons, HttpStatus.OK);
    } 
    
    @GetMapping("/id")
    public ResponseEntity<Long> getPersonId (@RequestParam("nome") String nome, @RequestParam("nascimento") LocalDate nascimento) throws Exception {
        Long personId = service.findPersonIdByNameAndBirthday(nome, nascimento);
        return ResponseEntity.ok(personId);
    }
    
    
    @GetMapping("/personsById")
    public ResponseEntity<Person> getPersonById (@RequestParam("id") Long idPerson) throws Exception {
        Person person = service.findPersonById(idPerson);
        return new ResponseEntity<>(person, HttpStatus.OK);
    
    }
    
    
    @PutMapping("{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody PersonRequestDTO data) throws Exception{
        Person updatedPerson = service.updatePerson(id, data);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }
    
}
        

