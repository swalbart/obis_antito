package th.obi.rest.controller;

import org.springframework.web.bind.annotation.*;
import th.obi.rest.entity.EmergencyContact;
import th.obi.rest.repository.EmergencyContactRepository;
import java.util.List;

@RestController
@RequestMapping("/emergencyContact")
public class EmergencyContactController {
    private EmergencyContactRepository emergencyContactRepository;

    public EmergencyContactController(EmergencyContactRepository emergencyContactRepository){
        this.emergencyContactRepository = emergencyContactRepository;
    }

    // get all emergencyContact as a List<EmergencyContact>
    @GetMapping("")
    public List<EmergencyContact> getEmergencyContact(){
        return emergencyContactRepository.findAll();
    }

    // get emergencyContact by id
    @GetMapping(path = "/{id}")
    public EmergencyContact getEmergencyContact(@PathVariable long id){
        if(emergencyContactRepository.findById(id).isPresent())
            return emergencyContactRepository.findById(id).get();
        return null;
    }

    // create a new emergencyContact
    @PostMapping(path = "")
    public void saveEmergencyContact(@RequestBody EmergencyContact emergencyContact){
        emergencyContactRepository.save(emergencyContact);
    }

    // update a emergencyContact
    @PatchMapping(path = "/{id}/all/{name}/{mail}")
    public void updateEmergencyContactAll(@PathVariable long id, @PathVariable String name, @PathVariable String mail){
        if(emergencyContactRepository.findById(id).isPresent()) {
            EmergencyContact emergencyContact = emergencyContactRepository.findById(id).get();
            emergencyContact.setName(name);
            emergencyContact.setMail(mail);
            emergencyContactRepository.save(emergencyContact);
        }
    }

    @PatchMapping(path = "/{id}/name/{name}")
    public void updateEmergencyContactName(@PathVariable long id, @PathVariable String name){
        if(emergencyContactRepository.findById(id).isPresent()){
            EmergencyContact ec = emergencyContactRepository.findById(id).get();
            ec.setName(name);
            emergencyContactRepository.save(ec);
        }
    }

    @PatchMapping(path = "/{id}/mail/{mail}")
    public void updateEmergencyContactMail(@PathVariable long id, @PathVariable String mail){
        if(emergencyContactRepository.findById(id).isPresent()) {
            EmergencyContact ec = emergencyContactRepository.findById(id).get();
            ec.setName(mail);
            emergencyContactRepository.save(ec);
        }
    }

    // delete emergencyContact by id
    @DeleteMapping(path = "/{id}")
    public void deleteEmergencyContact(@PathVariable long id){
        emergencyContactRepository.deleteById(id);
    }

}