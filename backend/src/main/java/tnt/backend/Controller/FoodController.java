package tnt.backend.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tnt.backend.DTO.request.FoodDTO;
import tnt.backend.Entity.Food;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Service.FoodService;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping()
    public ResponseEntity<?> getAllFood(@RequestParam (defaultValue = "0")int page,
                                        @RequestParam (defaultValue = "5")int size) throws BadRequestException{

        return ResponseEntity.ok(foodService.getAllFoods(PageRequest.of(page,size)));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodById(@PathVariable int id) throws BadRequestException{
        return ResponseEntity.ok(foodService.getFoodById(id));
    }

    @PostMapping()
    public ResponseEntity<?> saveFood(@Valid @RequestBody FoodDTO food) throws BadRequestException{
        return ResponseEntity.ok(foodService.saveFood(food));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFood(@PathVariable int id, @Valid @RequestBody FoodDTO food) throws BadRequestException{
        return ResponseEntity.ok(foodService.updateFood(food,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable int id) throws BadRequestException{
        return ResponseEntity.ok(foodService.deleteFood(id));
    }

}
