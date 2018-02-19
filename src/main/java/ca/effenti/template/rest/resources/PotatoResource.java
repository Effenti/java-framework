package ca.effenti.template.rest.resources;

import ca.effenti.template.controller.PotatoController;
import ca.effenti.template.model.Potato;
import ca.effenti.template.rest.assemblers.PotatoAssembler;
import ca.effenti.template.rest.dto.PotatoDTO;
import ca.effenti.template.rest.mappers.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/potato")
public class PotatoResource {
    @Autowired
    private PotatoController potatoController;

    private PotatoAssembler potatoAssembler = new PotatoAssembler();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PotatoDTO> getPotatoById(@PathVariable(value = "id") String id) {
        Potato potatoExists = potatoController.getPotatoById(id);
        if (potatoExists == null) {
            throw new NotFoundException("potato not found");
        }

        return new ResponseEntity<>(potatoAssembler.toDTO(potatoExists), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createPotato(@RequestBody PotatoDTO potatoIn){
        Potato potato = this.potatoAssembler.fromDTO(potatoIn);
        potatoController.createPotato(potato);

        return new ResponseEntity<>("CREATED", HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<PotatoDTO> updatePotato(@RequestBody PotatoDTO potatoIn) {
        Potato potato = this.potatoAssembler.fromDTO(potatoIn);
        potato = potatoController.updatePotato(potato);

        return new ResponseEntity<>(potatoAssembler.toDTO(potato), HttpStatus.OK);
    }
}
