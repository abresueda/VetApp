package dev.patika.demo.api;

import dev.patika.demo.business.abstracts.IAnimalService;
import dev.patika.demo.core.config.modelMapper.IModelMapperService;
import dev.patika.demo.core.result.Result;
import dev.patika.demo.core.result.ResultData;
import dev.patika.demo.core.ulties.ResultHelper;
import dev.patika.demo.dto.request.Animal.AnimalRequest;
import dev.patika.demo.dto.response.Animal.AnimalResponse;
import dev.patika.demo.entities.Animal;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v2/animals")
public class AnimalController {

    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;

    public AnimalController(IAnimalService animalService, IModelMapperService modelMapper) {
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalRequest animalRequest) {
        Animal saveAnimal = this.modelMapper.forRequest().map(animalRequest, Animal.class);
        this.animalService.save(saveAnimal);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class));
    }

    //Animalları isme göre filtreleyerek aramayı sağlar.
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> get(@PathVariable("name") String name) {
        List<Animal> animals = this.animalService.get(name);
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return new ResultData<>(true, "Hayvan bulundu.", "200", animalResponses);
    }

    //Animalları, customerlara göre filtreleyerek getirtmeyi sağlar.
    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getByCustomerId(@PathVariable("customerId") Long customerId) {
        List<Animal> animals = this.animalService.getByCustomerId(customerId);
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return new ResultData<>(true, "Müşteri bulundu.", "200", animalResponses);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalRequest animalRequest) {
        Animal updateAnimal = this.modelMapper.forRequest().map(animalRequest, Animal.class);
        this.animalService.update(updateAnimal);

        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultHelper.ok();
    }


}
