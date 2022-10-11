package th.obi.rest.controller;

import org.springframework.web.bind.annotation.*;
import th.obi.rest.repository.DeviceRepository;
import th.obi.rest.entity.Device;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {
    private DeviceRepository deviceRepository;

    public DeviceController(DeviceRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }

    // get all devices as a List<Device>
    @GetMapping("")
    public List<Device> getDevice(){
        return deviceRepository.findAll();
    }

    // get device by id
    @GetMapping(path = "/{id}")
    public Device getDevice(@PathVariable long id){
        if(deviceRepository.findById(id).isPresent())
            return deviceRepository.findById(id).get();
        return null;
    }

    // create a new device
    @PostMapping(path = "")
    public void saveDevice(@RequestBody Device device){
        deviceRepository.save(device);
    }

    // update a device
    // currently redundant to updateDeviceName. Might change by adding more attributes to the device entity
    @PatchMapping(path = "/{id}/all/{name}/{mail}")
    public void updateDeviceAll(@PathVariable long id, @PathVariable String name){
        if(deviceRepository.findById(id).isPresent()) {
            Device device = deviceRepository.findById(id).get();
            device.setName(name);
            deviceRepository.save(device);
        }
    }

    // currently redundant to updateDeviceAll. Might change by adding more attributes to the device entity
    @PatchMapping(path = "/{id}/name/{name}")
    public void updateDeviceName(@PathVariable long id, @PathVariable String name){
        if(deviceRepository.findById(id).isPresent()) {
            Device device = deviceRepository.findById(id).get();
            device.setName(name);
            deviceRepository.save(device);
        }
    }

    // delete device by id
    @DeleteMapping(path = "/{id}")
    public void deleteDevice(@PathVariable long id){
        deviceRepository.deleteById(id);
    }

}
