package tnt.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tnt.backend.DTO.request.ActorDTO;
import tnt.backend.Entity.Actor;
import tnt.backend.Exception.BadRequestException;
import tnt.backend.Exception.InternalServiceException;
import tnt.backend.Exception.NotFoundException;
import tnt.backend.Repository.ActorRepository;

import java.time.LocalDate;
import java.util.*;

@Service
public class ActorService {
    @Autowired
    ActorRepository actorRepository;

    //Find all actor for Page
    public Page<ActorDTO> findAll(Pageable pageable) {
        try {
            Page<ActorDTO> actorDTOS = actorRepository.findAll(pageable).map(actor -> new ActorDTO(actor));
            return actorDTOS;
        } catch (NotFoundException e) {
            throw new NotFoundException("Actor not found!");
        }

    }

    //Find actor
    public ActorDTO findById(Integer id) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new NotFoundException("Actor not found "));

        ActorDTO actorDTO = new ActorDTO(actor);
        return actorDTO;
    }

    //Detele actor by Actor Id
    public boolean deteletActor(Integer id) throws BadRequestException {
        Actor actors = actorRepository.findById(id).orElseThrow(() -> new NotFoundException("Actor not found"));
        try {
            actorRepository.delete(actors);
            return true;
        } catch (Exception e) {
            throw new BadRequestException("Delete actor failed");
        }
    }

    //Save octor
    public ActorDTO saveActor(ActorDTO actor) {
        try {
            String actorCode;
            do {
                actorCode = generateCodeActor(actor.getFullName());
            } while (actorRepository.existsActorByActorCode((actorCode)));
            Actor actorEntity = new Actor();
            actorEntity.setActorCode(actorCode);
            actorEntity.setCreatedDate(LocalDate.now());
            actorEntity.loadActor(actor);
            actorRepository.save(actorEntity);
            ActorDTO actorDTO = new ActorDTO(actorEntity);
            return actorDTO;
        } catch (Exception e) {
            throw new InternalServiceException("Server error, save failed");
        }

    }

    public ActorDTO updateActor(Integer id, ActorDTO actor) {
        Actor actorEntity = actorRepository.findById(id).orElseThrow(() -> new NotFoundException("Actor not found"));
        try {
            actorEntity.setFullName(actor.getFullName());
            actorEntity.setDateOfBirth(actor.getDateOfBirth());
            actorEntity.setDescribe(actor.getDescribe());
            actorEntity.setStatus(actor.isStatus());
            actorEntity.setModifiedDate(LocalDate.now());
            actorRepository.save(actorEntity);
            ActorDTO actorDTO = new ActorDTO(actorEntity);
            return actorDTO;
        } catch (Exception e) {
            throw new InternalServiceException("Server error, save failed");
        }
    }

    public static String generateCodeActor(String fullName) {
        String pathName[] = fullName.trim().split("\\s+");
        String firtLetter = pathName[0].substring(0, 1).toUpperCase();
        String lastLetter = pathName[pathName.length - 1].substring(0, 1).toUpperCase();
        String initials = firtLetter + lastLetter;
        int numberRandom = (int) (Math.random() * 10000);
        String code = initials + numberRandom;
        return code;
    }
}
