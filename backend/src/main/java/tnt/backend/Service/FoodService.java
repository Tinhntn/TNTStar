package tnt.backend.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.FoodDTO;
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Food;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.EmployeeRepository;
import tnt.backend.Repository.FoodRepository;

import java.time.LocalDate;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<FoodDTO> getAllFoods(Pageable pageable) throws BadRequestException {
        try{
            Page<FoodDTO> lst = foodRepository.findAll(pageable).map(food -> new FoodDTO(food));
            return lst;
        }catch (Exception ex){
            throw new BadRequestException("Food is not found"+ ex.getMessage());
        }
    }

    public FoodDTO getFoodById(Integer id) throws BadRequestException {

        try{
            Food food = foodRepository.findById(id).orElseThrow(()->new NotFoundException(""));
            FoodDTO foodDTO = new FoodDTO(food);
            return foodDTO;
        }catch (Exception ex){
            throw new BadRequestException("Food is not found"+ ex.getMessage());
        }

    }

    public boolean saveFood(FoodDTO foodDTO) throws BadRequestException {
        try{
            modelMapper.typeMap(FoodDTO.class,Food.class).addMappings(mapper -> {
                mapper.skip(Food::setId);
                mapper.skip(Food::setCreator);
                mapper.skip(Food::setEditor);
            });

            //Generate food code
            String foodCode;
            do {
                foodCode = CommonUtils.generateCode(foodDTO.getFoodName());
            }while(foodRepository.existsByFoodCode(foodCode));
            Food food = modelMapper.map(foodDTO,Food.class);
            Employee employee = employeeRepository.findById(foodDTO.getIdCreator()).orElseThrow(()->new NotFoundException("Creator is not found"));
            food.setCreator(employee);
            food.setCreatedDate(LocalDate.now());
            food.setFoodCode(foodCode);
            foodRepository.save(food);
            return true;

        }catch (Exception e){
            throw new BadRequestException("Food is not found"+ e.getMessage());
        }
    }

    public boolean updateFood(FoodDTO foodDTO,Integer id) throws BadRequestException {
        try{
            modelMapper.typeMap(FoodDTO.class,Food.class).addMappings(
                    mapper -> {
                        mapper.skip(Food::setId);
                        mapper.skip(Food::setCreator);
                        mapper.skip(Food::setEditor);
                    });
            Food food = foodRepository.findById(id).orElseThrow(()->new NotFoundException("Food is not found"));
            Employee employee = employeeRepository.findById(foodDTO.getIdEditor()).orElseThrow(()->new NotFoundException("Editor is not found"));
            modelMapper.map(foodDTO, food);
            food.setEditor(employee);
            food.setModifiedDate(LocalDate.now());
            foodRepository.save(food);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Update food is failed"+ e.getMessage());
        }

    }

    public boolean deleteFood(Integer id) throws BadRequestException {
        try{
            foodRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            throw new BadRequestException("Delete food is failed"+ ex.getMessage());
        }
    }
}
