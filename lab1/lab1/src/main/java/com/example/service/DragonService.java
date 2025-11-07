package com.example.service;

import com.example.dao.DragonDao;
import com.example.dao.PersonDao;
import com.example.dto.DragonDto;
import com.example.entity.Dragon;
import com.example.entity.Person;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class DragonService {

    @Inject
    private DragonDao dragonDao;

    @Inject
    private PersonDao personDao;

    public DragonDto findById(Long id) {
        Dragon dragon = dragonDao.findById(id);
        return dragon != null ? new DragonDto(dragon) : null;
    }

    public List<DragonDto> findAll() {
        return dragonDao.findAll().stream()
                .map(DragonDto::new)
                .collect(Collectors.toList());
    }

    public List<DragonDto> findAllPaginated(int page, int size) {
        return dragonDao.findAllPaginated(page, size).stream()
                .map(DragonDto::new)
                .collect(Collectors.toList());
    }

    public List<DragonDto> findWithFilters(Map<String, Object> filters, int page, int size, String sortBy, String sortOrder) {
        return dragonDao.findWithFilters(filters, page, size, sortBy, sortOrder).stream()
                .map(DragonDto::new)
                .collect(Collectors.toList());
    }

    public Long countWithFilters(Map<String, Object> filters) {
        return dragonDao.countWithFilters(filters);
    }

    public DragonDto save(@Valid DragonDto dragonDto) {
        Dragon dragon = convertToEntity(dragonDto);

        if (dragonDto.getKiller() != null && dragonDto.getKiller().getId() != null) {
            Person killer = personDao.findById(dragonDto.getKiller().getId());
            dragon.setKiller(killer);
        }

        Dragon saved = dragonDao.save(dragon);
        return new DragonDto(saved);
    }

    public void delete(Long id, Long newKillerId) {
        Dragon dragon = dragonDao.findById(id);
        if (dragon != null) {
            if (newKillerId != null) {
                List<Dragon> relatedDragons = dragonDao.findAll().stream()
                        .filter(d -> d.getKiller() != null && d.getKiller().getId().equals(id))
                        .collect(Collectors.toList());

                Person newKiller = personDao.findById(newKillerId);
                for (Dragon related : relatedDragons) {
                    related.setKiller(newKiller);
                    dragonDao.save(related);
                }
            }
            dragonDao.delete(id);
        }
    }

    private Dragon convertToEntity(DragonDto dto) {
        Dragon dragon = new Dragon();
        dragon.setId(dto.getId());
        dragon.setName(dto.getName());
        dragon.setCoordinates(dto.getCoordinates());
        dragon.setCave(dto.getCave());
        dragon.setAge(dto.getAge());
        dragon.setWeight(dto.getWeight());
        dragon.setColor(dto.getColor());
        dragon.setCharacter(dto.getCharacter());
        dragon.setHead(dto.getHead());
        return dragon;
    }

    // Специальные операции
    public Long getSumOfAges() {
        return dragonDao.getSumOfAges();
    }

    public List<DragonDto> findByWeightGreaterThan(Float weight) {
        return dragonDao.findByWeightGreaterThan(weight).stream()
                .map(DragonDto::new)
                .collect(Collectors.toList());
    }

    public List<Integer> findUniqueAges() {
        return dragonDao.findUniqueAges();
    }
}