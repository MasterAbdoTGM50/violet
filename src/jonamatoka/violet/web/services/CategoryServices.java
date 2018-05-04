package jonamatoka.violet.web.services;


import jonamatoka.violet.Lib;
import jonamatoka.violet.data.model.Category;
import jonamatoka.violet.data.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(Lib.Mappings.API_V1_CATEGORY)
public class CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<?> all() {

        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);

        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Category category) {

        categoryRepository.save(category);

        return new ResponseEntity<>(true, HttpStatus.OK);

    }

}
