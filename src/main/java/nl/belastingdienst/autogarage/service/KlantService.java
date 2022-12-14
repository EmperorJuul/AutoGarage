package nl.belastingdienst.autogarage.service;

import nl.belastingdienst.autogarage.dto.KlantDto;
import nl.belastingdienst.autogarage.exception.KlantNotFoundException;
import nl.belastingdienst.autogarage.model.Customer;
import nl.belastingdienst.autogarage.repository.KlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KlantService {

    @Autowired
    private KlantRepository klantRepository;

    public List<KlantDto> alleKlanten(){
        List<Customer> customerList = klantRepository.findAll();
        List<KlantDto> klantDtoList = new ArrayList<>();
        for(Customer customer : customerList){
            klantDtoList.add(vanKlantNaarKlantDto(customer));
        }
        return klantDtoList;
    }

    public KlantDto klantOpId(Long id){
        return vanKlantNaarKlantDto(klantRepository.findById(id).orElseThrow(() -> new KlantNotFoundException(id)));
    }

    public KlantDto nieuweKlant(Customer customer){
        klantRepository.save(customer);
        return vanKlantNaarKlantDto(customer);
    }

    public void updateKlant(Long id, Customer nieuweCustomer){
        Customer customer = klantRepository.findById(id).orElseThrow(() -> new KlantNotFoundException(id));
        nieuweCustomer.setId(customer.getId());
        klantRepository.save(nieuweCustomer);
    }

    public void verwijderKlant(Long id){
        klantRepository.deleteById(id);
    }

    private KlantDto vanKlantNaarKlantDto(Customer customer){
        KlantDto klantDto = new KlantDto(customer.getFirstname(), customer.getSurname(), customer.getPhoneNumber(), customer.getEmailAdress());
        klantDto.setId(customer.getId());
        return klantDto;
    }
}
