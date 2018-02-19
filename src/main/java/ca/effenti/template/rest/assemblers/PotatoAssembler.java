package ca.effenti.template.rest.assemblers;


import ca.effenti.template.model.Potato;
import ca.effenti.template.rest.dto.PotatoDTO;
import ca.effenti.template.rest.mappers.exception.BadRequestException;

public class PotatoAssembler {
    public Potato fromDTO(PotatoDTO potatoDTO) {
        this.validateEntry(potatoDTO);
        Potato potato = new Potato();
        potato.setEmail(potatoDTO.email);
        potato.setId(potatoDTO.id);
        return potato;
    }

    public PotatoDTO toDTO(Potato potato) {
        PotatoDTO potatoDTO = new PotatoDTO();
        potatoDTO.email = potato.getEmail();
        potatoDTO.id = potato.getId();
        return  potatoDTO;
    }

    private void validateEntry(PotatoDTO potatoDTO) {
        if (potatoDTO.email == null) {
            throw new BadRequestException("There is no data in email");
        }
    }
}
