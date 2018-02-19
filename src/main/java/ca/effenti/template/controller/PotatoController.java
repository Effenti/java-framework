package ca.effenti.template.controller;

import ca.effenti.template.model.Potato;
import ca.effenti.template.model.PotatoRepository;
import ca.effenti.template.rest.mappers.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PotatoController {

    @Autowired
    private PotatoRepository potatoRepository;

    public Potato getPotatoById(String id) {
        Potato potatoExists = potatoRepository.findOne(Long.parseLong(id));
        if (potatoExists == null) {
            throw new NotFoundException("potato not found");
        }

        return potatoExists;
    }

    public void createPotato(Potato potatoIn){
        potatoRepository.save(potatoIn);
    }

    public Potato updatePotato(Potato potato) {
        Potato potatoExists = potatoRepository.findOne(potato.getId());
        System.out.println(potato.getId());
        if (potatoExists == null) {
            throw new NotFoundException("potato not found");
        }
        potatoExists.setEmail(potato.getEmail());
        potatoRepository.save(potato);

        return potatoExists;
    }

    // If you want to open a session for the DB in case of error to make a rollback, use @Transactional on function and throw RuntimeException and there will be no commit in DB.
}