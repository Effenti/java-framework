# Technologies

JPA
H2
Hibernate
Tomcat
Spring-boot

from https://start.spring.io/   and selected "JPA", "H2" and "Web -> Tomcat & Spring MVC"

## How to start application from command line

mvn clean install && mvn spring-boot:run


# Project structure

## Controller

This is where the big of the application will take place.

## Interfaces REST

#### Assemblers

An assembler take an object and convert it to a DTO and vice versa. That way you hide all the object construction.

#### DTO (data transfer object)

This object is only used to transfer data between external application to API. That way, you can control which data you received and
which data goes out because you don't always want to send the entire object.

#### Mappers

Mappers are used to catch application errors and then react based on what type of error was thrown.
e.g. We ask DB for an object but the object doesn't exist, we throw an exception and a mapper catch it. It then return the good response (404 entity not found).

#### Resources

This is where every you will find every path for the application.

You can put an annotation like this @RequestMapping("/aPath") on a class or a function. When you put it on the class, every function will have the parent path before their own path.

@RestController
@RequestMapping(value="/potato")
public class PotatoResource {
    @Autowired
    private PotatoController potatoController;

    private PotatoAssembler potatoAssembler = new PotatoAssembler();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getPotatoById(@PathVariable(value = "id") String id) {
        Potato potatoExists = potatoController.getPotatoById(id);
        if (potatoExists == null) {
            throw new PotatoNotFoundException("potato not found");
        }

        return new ResponseEntity<>(potatoExists.getEmail(), HttpStatus.OK);
    }
}

As you can see here, to access of getPotatoById, the path will be : /potato/id

## Model

This is where you find the application models and annotation for hibernate. Here a link for some annotations.

https://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html

@Entity = class
@Column = class propriety

In this version, our repository is extending JpaRepository to get its function to add object in DB.

# Test

As you can see, there is folder in src. This is where you put your tests (here we use junit and mockito). The structure of test files should be the same as the application. That way, you will not get lost.
