package guru.springframework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Kunal Indoliya.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorListDTO {
    List<VendorDTO> vendors;
}