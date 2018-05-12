package unit.services.brand;

import jonamatoka.violet.data.repo.BrandRepository;
import jonamatoka.violet.web.services.BrandServices;

import org.springframework.beans.factory.annotation.Autowired;

import unit.AbstractTestVioletService;

public abstract class AbstractTestBrandService extends AbstractTestVioletService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandServices brandServices;

    public BrandRepository getBrandRepository() { return brandRepository; }

    public BrandServices getBrandServices() { return brandServices; }

}
