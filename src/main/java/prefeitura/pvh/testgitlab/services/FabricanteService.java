package prefeitura.pvh.testgitlab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prefeitura.pvh.testgitlab.dto.FabricanteDTO;
import prefeitura.pvh.testgitlab.entities.Fabricante;
import prefeitura.pvh.testgitlab.repositories.FabricanteRepository;
import prefeitura.pvh.testgitlab.services.exceptions.DatabaseException;
import prefeitura.pvh.testgitlab.services.exceptions.ResourceNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
public class FabricanteService {

    @Autowired
    private FabricanteRepository repository;


    @Transactional(readOnly = true)
    public Page<FabricanteDTO> findAllPaged(Pageable pageable) {
        Page<Fabricante> list = repository.findAll(pageable);
        return list.map(x -> new FabricanteDTO(x));
    }

    @Transactional(readOnly = true)
    public FabricanteDTO findaById(Long id) {
        Optional<Fabricante> obj = repository.findById(id);
        Fabricante entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity no found"));
        return new FabricanteDTO(entity);
    }

    @Transactional
    public FabricanteDTO insert(FabricanteDTO dto) {
        Fabricante entity = new Fabricante();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new FabricanteDTO(entity);
    }

    @Transactional
    public FabricanteDTO update(Long id, FabricanteDTO dto) {
        try {
            Fabricante entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new FabricanteDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }

    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }

    private void copyDtoToEntity(FabricanteDTO dto, Fabricante entity) {
        entity.setNome(dto.getNome());
        entity.setSite(dto.getSite());
    }
}
