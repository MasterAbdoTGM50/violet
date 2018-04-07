package jonamatoka.violet.web.services;

import jonamatoka.violet.Lib;
import jonamatoka.violet.data.repo.BrandRepository;
import jonamatoka.violet.product.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BrandServices {

    @Autowired
    private BrandRepository brandRepository;

    @RequestMapping(value = Lib.Mappings.BRAND_SERVICES, method = RequestMethod.GET)
    public ResponseEntity<List<Brand>> getAllBrands() {

        List<Brand> brands = new ArrayList<>();
        brandRepository.findAll().forEach(brands::add);

        return new ResponseEntity<>(brands, HttpStatus.OK);

    }

    @RequestMapping(value = Lib.Mappings.ADD_BRAND_SYSTEM, method = RequestMethod.POST)
    public ResponseEntity<Boolean> addBrandToSystem(@RequestBody Brand brand) {

        brandRepository.save(brand);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

}
