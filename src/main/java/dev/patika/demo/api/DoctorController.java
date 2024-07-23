package dev.patika.demo.api;

import dev.patika.demo.business.abstracts.IDoctorService;
import dev.patika.demo.core.config.modelMapper.IModelMapperService;
import dev.patika.demo.core.result.Result;
import dev.patika.demo.core.result.ResultData;
import dev.patika.demo.core.ulties.ResultHelper;
import dev.patika.demo.dto.request.Doctor.DoctorRequest;
import dev.patika.demo.dto.request.Doctor.DoctorUpdateRequest;
import dev.patika.demo.dto.response.Doctor.DoctorResponse;
import dev.patika.demo.dto.response.Doctor.DoctorUpdateResponse;
import dev.patika.demo.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/doctors")
public class DoctorController {

    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public DoctorController(IDoctorService doctorService, IModelMapperService modelMapper) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    //Doktor kayıt işlemini yapmak için.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    //Valid ile null olmamasını sağlıyoruz.
    public ResultData<DoctorResponse> save (@Valid @RequestBody DoctorRequest doctorRequest) {
        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorRequest, Doctor.class);
        this.doctorService.save(saveDoctor);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveDoctor, DoctorResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") Long id) {
        Doctor doctor = this.doctorService.get(id);
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(doctor, DoctorResponse.class);
        return ResultHelper.success(doctorResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorUpdateResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        Doctor updateDoctor = this.modelMapper.forRequest().map(doctorUpdateRequest, Doctor.class);
        this.doctorService.update(updateDoctor);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateDoctor, DoctorUpdateResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }
}
