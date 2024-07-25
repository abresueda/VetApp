package dev.patika.demo.api;

import dev.patika.demo.business.abstracts.IVaccineService;
import dev.patika.demo.core.config.modelMapper.IModelMapperService;
import dev.patika.demo.core.result.Result;
import dev.patika.demo.core.result.ResultData;
import dev.patika.demo.core.ulties.ResultHelper;
import dev.patika.demo.dto.request.Vaccine.VaccineRequest;
import dev.patika.demo.dto.response.Vaccine.VaccineResponse;
import dev.patika.demo.dto.response.Vaccine.VaccineWithAnimalsDTO;
import dev.patika.demo.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v2/vaccines")
public class VaccineController {

    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;

    public VaccineController(IVaccineService vaccineService, IModelMapperService modelMapper) {
        this.vaccineService = vaccineService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineRequest vaccineRequest) {
        Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineRequest, Vaccine.class);
        Vaccine savedVaccine = this.vaccineService.save(saveVaccine, vaccineRequest.getAnimalId());
        return ResultHelper.created(this.modelMapper.forResponse().map(savedVaccine, VaccineResponse.class));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineRequest vaccineRequest) {
        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineRequest, Vaccine.class);
        this.vaccineService.update(updateVaccine);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateVaccine, VaccineResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get(@PathVariable("id") Long id) {
        Vaccine vaccine = this.vaccineService.get(id);
        VaccineResponse vaccineResponse = this.modelMapper.forResponse().map(vaccine, VaccineResponse.class);
        return ResultHelper.success(vaccineResponse);
    }

    //AnimalId'ye göre vaccineleri getirtmek için.
    @GetMapping("/animal/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccinesByAnimalId(@PathVariable("animalId") Long animalId) {
        List<Vaccine> vaccines = this.vaccineService.getVaccinesByAnimalId(animalId);
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
        return new ResultData<>(true, "Aşı bulundu.", "200", vaccineResponses);
    }

    @GetMapping("/protection-finish-date")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineWithAnimalsDTO>> getVaccinesByProtectionFinishDate(@RequestParam LocalDate protectionStartDate, @RequestParam LocalDate protectionFinishDate) {
        List<VaccineWithAnimalsDTO> vaccines = this.vaccineService.getVaccinesByProtectionFinishDateBetween(protectionStartDate, protectionFinishDate);

        return new ResultData<>(true, "Aşı koruyuculuk bitiş tarihi yaklaştı.", "200", vaccines);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }
}
