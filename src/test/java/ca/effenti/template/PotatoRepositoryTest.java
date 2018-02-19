package ca.effenti.template;

import ca.effenti.template.model.Potato;
import ca.effenti.template.model.PotatoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PotatoRepositoryTest {

    @Autowired
    private PotatoRepository potatoRepository;

    @Test
    public void givenPotato_whenAddingToRep_thenRepoIsNotEmpty() {
        Potato potato = new Potato();
        assertEquals(0,this.potatoRepository.count());

        this.potatoRepository.save(potato);

        assertEquals(1,this.potatoRepository.count());
    }

}