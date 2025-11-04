package tnt.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.ChairsDTO;
import tnt.backend.Entity.Chairs;
import tnt.backend.Entity.Employee;
import tnt.backend.Entity.Room;
import tnt.backend.Entity.Row;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.ChairRepository;
import tnt.backend.Repository.EmployeeRepository;
import tnt.backend.Repository.RoomRepository;
import tnt.backend.Repository.RowRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChairService {
    @Autowired
    private ChairRepository chairRepository;

    @Autowired
    private RoomRepository  roomRepository;

    @Autowired
    private RowRepository rowRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    public ChairsDTO findById(Integer id) throws BadRequestException {
        try{
            Chairs chairs = chairRepository.findById(id).orElseThrow(() -> new NotFoundException("Chairs not found!"));
            return new ChairsDTO(chairs);
        }catch(NotFoundException e){
            throw new BadRequestException("An error occured while searching chairs by id!");
        }
    }
    public Page<ChairsDTO> findAllByStatus(Pageable pageable) {
        try{
            Page<ChairsDTO> lstChairs = chairRepository.findAll(pageable).map(chair -> new ChairsDTO(chair));
            return lstChairs;

        }catch (Exception e){
            e.printStackTrace();
            throw new NotFoundException("Not found chairs");
        }
    }

    public ArrayList<ChairsDTO>  findAllByStatus() {
        ArrayList<ChairsDTO> list = new ArrayList<>();
        List<Chairs> lstChairs = chairRepository.findAll();
        try {
            if(lstChairs==null){
                for(Chairs chairs: lstChairs){
                    ChairsDTO chairsDTO = new ChairsDTO(chairs);
                    list.add(chairsDTO);
                }
            }
            return  list;
        }catch (Exception e){
            throw new NotFoundException("Chairs not found!");
        }
    }
    public boolean deleteChairs(Integer idChair) {
        Chairs chairs = chairRepository.findById(idChair).orElseThrow(()->new NotFoundException("Chair not found!"));
        try{
            chairRepository.delete(chairs);
            return true;
        }catch (Exception e){
            throw new NotFoundException("Delete chairs failed!");
        }
    }

    public boolean addChairs(ChairsDTO chairsDTO) throws BadRequestException {
        try{
            Room room = roomRepository.findById(chairsDTO.getIdRoom()).orElseThrow(()->new NotFoundException("Room not found!"));
            Row row = rowRepository.findById(chairsDTO.getIdRow()).orElseThrow(()->new NotFoundException("Row not found!"));
            Employee employeeCreator = employeeRepository.findById(chairsDTO.getIdCreator()).orElseThrow(()->new NotFoundException("Employee not found!"));
            Chairs chairs = new Chairs(chairsDTO,room,row,employeeCreator);
            chairRepository.save(chairs);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Add chairs failed!");
        }
    }

    public boolean updateChairs(ChairsDTO chairsDTO,Integer idChair) throws BadRequestException {
        try{
            Chairs chairs = chairRepository.findById(idChair).orElseThrow(()->new NotFoundException("Chair not found!"));
            Room room = roomRepository.findById(chairsDTO.getIdRoom()).orElseThrow(()->new NotFoundException("Room not found!"));
            Row row = rowRepository.findById(chairsDTO.getIdRow()).orElseThrow(()->new NotFoundException("Row not found!"));
            Employee employeeEditor = employeeRepository.findById(chairsDTO.getIdEditor()).orElseThrow(()->new NotFoundException("Employee editor not found!"));

            chairs.setRoom(room);
            chairs.setRow(row);
            chairs.setEditor(employeeEditor);
            chairs.setModifiedDate(LocalDate.now());
            chairs.setStatus(chairsDTO.isStatus());
            chairRepository.save(chairs);
            return true;
        }catch (Exception e){
            throw new BadRequestException("Update chairs failed!");
        }
    }


}
