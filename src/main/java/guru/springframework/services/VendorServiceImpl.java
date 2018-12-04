package guru.springframework.services;

import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import guru.springframework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kunal Indoliya.
 */
@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id).map(vendorMapper::vendorTOVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorURL(id));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO returnDto=vendorMapper.vendorTOVendorDTO(vendor);
                    returnDto.setVendorUrl(getVendorURL(vendor.getId()));
                    return returnDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOtoVendor(vendorDTO));
    }
    private VendorDTO saveAndReturnDTO(Vendor vendor){
       Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDto = vendorMapper.vendorTOVendorDTO(savedVendor);

        returnDto.setVendorUrl(getVendorURL(savedVendor.getId()));

        return returnDto;
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id,VendorDTO vendorDTO) {
        Vendor vendor=vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendor.setId(id);
        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id,VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if(vendorDTO.getName()!=null)
                vendor.setName(vendorDTO.getName());
            VendorDTO returnDTO=vendorMapper.vendorTOVendorDTO(vendorRepository.save(vendor));
            returnDTO.setVendorUrl(getVendorURL(id));
            return returnDTO;
        }).orElseThrow(ResourceNotFoundException::new);
    }
    private String getVendorURL(Long id){
        return "api/v1/vendor/"+id;
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}
