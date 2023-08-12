package ro.mycode.autovitapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.mycode.autovitapi.dto.MasinaDTO;
import ro.mycode.autovitapi.exceptii.EmptyDatabaseMasiniException;
import ro.mycode.autovitapi.exceptii.MasinaAlreadyExistsException;
import ro.mycode.autovitapi.exceptii.MasinaDoesntExistException;
import ro.mycode.autovitapi.model.Masina;
import ro.mycode.autovitapi.service.MasinaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class CarResource {


    private MasinaService masinaService;

    public CarResource(MasinaService masinaService) {
        this.masinaService = masinaService;
    }


    @GetMapping("api/v1/masini/all")
    public ResponseEntity<List<Masina>> getAllMasini() {
        List<Masina> masinas = masinaService.getAllCars();
        return new ResponseEntity<>(masinas, HttpStatus.OK);
    }

    @GetMapping("api/v1/masini/culoare/{color}")
    public ResponseEntity<List<Masina>> getAllMasiniByColor(@PathVariable String color) {
            List<Masina> masinas = masinaService.getAllCarsByColor(color).get();
            return new ResponseEntity<>(masinas, HttpStatus.OK);
    }

    @PostMapping("api/v1/masini/add")
    public ResponseEntity<Masina> addCar(@Valid @RequestBody Masina m) {
            this.masinaService.add(m);
            return new ResponseEntity<>(m, HttpStatus.CREATED);
    }

    @DeleteMapping("api/v1/masini/removebymodel/{model}")
    public ResponseEntity<Masina> deleteCar(@PathVariable(value = "model") String model) {
        this.masinaService.removeByModel(model);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("api/v1/masini/removebyid/{id}")
    public ResponseEntity<Masina> deleteCar(@PathVariable(value="id")  Long id) {
        this.masinaService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("api/v1/masini/update")
    public ResponseEntity<MasinaDTO> updateCar(@Valid @RequestBody MasinaDTO masina) {
        this.masinaService.update(masina);
        return new ResponseEntity<>(masina,HttpStatus.OK);
    }

    @GetMapping("api/v1/masini/filter")
    public ResponseEntity<MasinaDTO> filterCar(@Valid @RequestBody MasinaDTO masina){
        this.masinaService.filter(masina);
        return new ResponseEntity<>(masina,HttpStatus.OK);
    }

    @GetMapping("api/v1/masini/findById/{id}")
    public ResponseEntity<Masina> getCarById(@Valid @PathVariable Long id){
        Masina carById = this.masinaService.getCarById(id);
        return new ResponseEntity<>(carById,HttpStatus.OK);
    }






}
