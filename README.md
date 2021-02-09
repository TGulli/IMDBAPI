# **IMDb API**
IMDb API is a backend system for storing and manipulate movie characters.
The application is constructed in Spring using Hibernate. A PostgreSQL database is used
to store information about characters, the movies they appear in, and the franchises
they belong to. The REST API is used to interact and manipulate the data.

### Participants
Hiba Benhaida, Thomas Gulli, Bjørnar Pedersen and Amalie Hunsbedt

### Heroku page
http://pretendmdb.herokuapp.com/

For swagger:
http://pretendmdb.herokuapp.com/index.html


### Motivation
This project was made as an assignment during the Experis Academy Java Fullstack course.

### Technologies
- IntelliJ IDEA
- Java
- Spring
- Maven
- Hibernate
- Rest API
- PostgreSQL
- Postman
- Docker
- Heroku

### Code example
```java
// Post method from the CharacterService class
@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    /* Post new character */
    public ResponseEntity<ActorCharacter> createNewCharacter(ActorCharacter character){
        ActorCharacter c = characterRepository.save(character);
        return ResponseEntity.ok().body(c);
    }
    


// REST controller endpoint from the CharacterController class
@RestController
@RequestMapping("api/v1/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    /* A method to create a new ActorCharacter object and add it to the database. */
    @PostMapping("/add")
    public ResponseEntity<ActorCharacter> addCharacter(@RequestBody ActorCharacter character){
        return characterService.createNewCharacter(character);
    }
```

### Project tree
```bash
+---.idea
|   \---libraries
+---.mvn
|   \---wrapper
+---src
|   +---main
|   |   +---java
|   |   |   \---com
|   |   |       \---noroff
|   |   |           \---MovieCharactersAPI
|   |   |               +---controllers
|   |   |               +---exceptions
|   |   |               +---models
|   |   |               +---repositories
|   |   |               \---service
|   |   \---resources
|   |       +---static
|   |       \---templates
|   \---test
|       \---java
|           \---com
|               \---noroff
|                   \---MovieCharactersAPI
\---target
    +---classes
    |   \---com
    |       \---noroff
    |           \---MovieCharactersAPI
    |               +---controllers
    |               +---exceptions
    |               +---models
    |               +---repositories
    |               \---service
    +---generated-sources
    |   \---annotations
    +---generated-test-sources
    |   \---test-annotations
    \---test-classes
        \---com
            \---noroff
                \---MovieCharactersAPI
```

### Credits
Thanks to Nicholas Lennox for great guidance!
