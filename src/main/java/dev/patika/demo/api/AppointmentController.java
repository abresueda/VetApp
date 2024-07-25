package dev.patika.demo.api;

import dev.patika.demo.business.abstracts.IAppointmentService;
import dev.patika.demo.core.config.modelMapper.IModelMapperService;
import dev.patika.demo.core.result.Result;
import dev.patika.demo.core.result.ResultData;
import dev.patika.demo.core.ulties.ResultHelper;
import dev.patika.demo.dto.request.Appointment.AppointmentRequest;
import dev.patika.demo.dto.response.Appointment.AppointmentResponse;
import dev.patika.demo.entities.Appointment;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v2/appointments")
public class AppointmentController {
    private final IAppointmentService appointmentService;
    private final IModelMapperService modelMapper;


    public AppointmentController(IAppointmentService appointmentService, IModelMapperService modelMapper) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentRequest, Appointment.class);
        this.appointmentService.save(saveAppointment);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment, AppointmentResponse.class));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentRequest, Appointment.class);
        this.appointmentService.update(updateAppointment);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAppointment, AppointmentResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") Long id) {
        Appointment appointment = this.appointmentService.get(id);
        AppointmentResponse appointmentResponse = this.modelMapper.forResponse().map(appointment, AppointmentResponse.class);
        return ResultHelper.success(appointmentResponse);
    }

    //Randevular, doctorId'si ve tarih aralığına göre filtrelenir.
    @GetMapping("/doctor/{doctorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getByDoctorIdAndDateRange(
            @PathVariable("doctorId") Long doctorId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Appointment> appointments = this.appointmentService.getByDoctorIdAndDateRange(doctorId, startDate, endDate);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .toList();
        return ResultHelper.success(appointmentResponses);
    }

    //Randevular, animalId'si ve tarih aralığına göre filtrelenir.
    @GetMapping("/animal/{animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getByAnimalIdAndDateRange(
            @PathVariable("animalId") Long animalId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Appointment> appointments = this.appointmentService.getByAnimalIdAndDateRange(animalId, startDate, endDate);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .toList();
        return ResultHelper.success(appointmentResponses);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }
}
