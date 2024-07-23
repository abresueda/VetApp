package dev.patika.demo.api;

import dev.patika.demo.business.abstracts.IAvailableService;
import dev.patika.demo.core.config.modelMapper.IModelMapperService;
import dev.patika.demo.core.result.Result;
import dev.patika.demo.core.result.ResultData;
import dev.patika.demo.core.ulties.ResultHelper;
import dev.patika.demo.dto.request.AvailableDate.AvailableDateRequest;
import dev.patika.demo.dto.response.AvailableDate.AvailableDateResponse;
import dev.patika.demo.entities.AvailableDate;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/dates")
public class AvailableDateController {
    private final IAvailableService availableService;
    private final IModelMapperService modelMapper;

    public AvailableDateController(IAvailableService availableService, IModelMapperService modelMapper) {
        this.availableService = availableService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateRequest availableDateRequest) {
        AvailableDate saveAvailableDate = this.modelMapper.forRequest().map(availableDateRequest, AvailableDate.class);
        this.availableService.save(saveAvailableDate);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAvailableDate, AvailableDateResponse.class));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateRequest availableDateRequest) {
        AvailableDate updateAvailableDate = this.modelMapper.forRequest().map(availableDateRequest, AvailableDate.class);
        this.availableService.update(updateAvailableDate);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAvailableDate, AvailableDateResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id") Long id) {
        AvailableDate availableDate = this.availableService.get(id);
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
        return ResultHelper.success(availableDateResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.availableService.delete(id);
        return ResultHelper.ok();
    }
}
