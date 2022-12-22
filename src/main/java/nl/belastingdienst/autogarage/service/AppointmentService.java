package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.AppointmentDto;
import nl.belastingdienst.autogarage.exception.AppointmentNotFoundException;
import nl.belastingdienst.autogarage.exception.CarNotFoundException;
import nl.belastingdienst.autogarage.exception.CustomerNotFoundException;
import nl.belastingdienst.autogarage.exception.RepairNotFoundException;
import nl.belastingdienst.autogarage.model.Appointment;
import nl.belastingdienst.autogarage.model.Car;
import nl.belastingdienst.autogarage.model.Customer;
import nl.belastingdienst.autogarage.model.Repair;
import nl.belastingdienst.autogarage.repository.AppointmentRepository;
import nl.belastingdienst.autogarage.repository.CarRepository;
import nl.belastingdienst.autogarage.repository.CustomerRepository;
import nl.belastingdienst.autogarage.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<AppointmentDto> allAppointments(){
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<AppointmentDto> appointmentDtoList = new ArrayList<>();
        for(Appointment appointment : appointmentList){
            appointmentDtoList.add(fromAppointmentToDto(appointment));
        }
        return appointmentDtoList;
    }

    public AppointmentDto appointmentById(Long id){
        return fromAppointmentToDto(appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id)));
    }

    public AppointmentDto newAppointment(AppointmentDto appointmentInputDto){
        Appointment appointment = fromDtoToAppointment(appointmentInputDto);
        appointmentRepository.save(appointment);
        return fromAppointmentToDto(appointment);
    }

    public void updateAppointment(Long id, AppointmentDto appointmentInputDto){
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException(id));
        appointment.setStartAppointment(appointmentInputDto.getStartAppointment());
        appointment.setEndAppointment(appointmentInputDto.getEndAppointment());
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id){
        appointmentRepository.deleteById(id);
    }

    private AppointmentDto fromAppointmentToDto(Appointment appointment){
        AppointmentDto appointmentDto = new AppointmentDto(appointment.getStartAppointment(), appointment.getEndAppointment());
        appointmentDto.setId(appointment.getId());
        return appointmentDto;
    }

    private Appointment fromDtoToAppointment(AppointmentDto appointmentDto){
        Appointment appointment = new Appointment(appointmentDto.getStartAppointment(), appointmentDto.getEndAppointment());
        appointment.setId(appointmentDto.getId());
        return appointment;
    }

    public void addRepair(Long appointmentId, Long repairId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
        Repair repair = repairRepository.findById(repairId).orElseThrow(() -> new RepairNotFoundException(repairId));
        appointment.addToRepairList(repair);
        repair.addToAppointmentList(appointment);
        appointmentRepository.save(appointment);
        repairRepository.save(repair);
    }

    public void removeRepair(Long appointmentId, Long repairId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
        Repair repair = repairRepository.findById(repairId).orElseThrow(() -> new RepairNotFoundException(repairId));
        if(appointment.getRepairList().contains(repair)){
            appointment.removeFromRepairList(repair);
            repair.removeFromAppointmentList(appointment);
            appointmentRepository.save(appointment);
            repairRepository.save(repair);
        }
        else{
            throw new RepairNotFoundException("Repair was not linked to appointment");
        }
    }

    public void addCar(Long appointmentId, Long carId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        appointment.setCar(car);
        car.addToAppointmentList(appointment);
        appointmentRepository.save(appointment);
        carRepository.save(car);
    }

    public void removeCar(Long appointmentId, Long carId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        if (appointment.getCar() == car){
            appointment.setCar(null);
            car.removeFromAppointmentList(appointment);
            appointmentRepository.save(appointment);
            carRepository.save(car);
        }
        else {
            throw new CarNotFoundException("Car was not linked to this appointment");
        }
    }

    public void addCustomer(Long appointmentId, Long customerId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        appointment.setCustomer(customer);
        customer.addToAppointmentList(appointment);
        appointmentRepository.save(appointment);
        customerRepository.save(customer);
    }

    public void removeCustomer(Long appointmentId, Long customerId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
        if(appointment.getCustomer() == customer){
            appointment.setCustomer(null);
            customer.removeFromAppointmentList(appointment);
            appointmentRepository.save(appointment);
            customerRepository.save(customer);
        }
        else {
            throw new CustomerNotFoundException("Customer was not linked to this appointment");
        }
    }
}
