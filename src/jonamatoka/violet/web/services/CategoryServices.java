package jonamatoka.violet.web.services;


import jonamatoka.violet.Lib;
import jonamatoka.violet.data.repo.CategoryRepository;
import jonamatoka.violet.product.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = Lib.Mappings.CATEGORY_SERVICES, method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllCategory() {

        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);

        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    @RequestMapping(value = Lib.Mappings.ADD_CATEGORY_SYSTEM, method = RequestMethod.POST)
    public ResponseEntity<Boolean> addCategoryToSystem(@RequestBody Category category) {

        categoryRepository.save(category);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

}
