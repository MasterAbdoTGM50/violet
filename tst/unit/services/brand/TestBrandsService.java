package unit.services.brand;

import jonamatoka.violet.data.repo.BrandRepository;
import jonamatoka.violet.web.services.BrandServices;

import org.springframework.beans.factory.annotation.Autowired;

import unit.TestVioletService;

public abstract class TestBrandsService extends TestVioletService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandServices brandServices;

    public BrandRepository getBrandRepository() { return brandRepository; }

    public BrandServices getBrandServices() { return brandServices; }

}
