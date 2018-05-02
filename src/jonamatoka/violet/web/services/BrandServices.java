package jonamatoka.violet.web.services;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.Brand;
import jonamatoka.violet.data.repo.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(Lib.Mappings.API_V1_BRAND)
public class BrandServices {

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping
    public ResponseEntity<?> all() {

        List<Brand> brands = new ArrayList<>();
        brandRepository.findAll().forEach(brands::add);

        return new ResponseEntity<>(brands, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Brand brand) {

        brandRepository.save(brand);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

}
