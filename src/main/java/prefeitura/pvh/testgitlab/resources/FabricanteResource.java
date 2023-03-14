package prefeitura.pvh.testgitlab.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import prefeitura.pvh.testgitlab.dto.FabricanteDTO;
import prefeitura.pvh.testgitlab.services.FabricanteService;


import java.net.URI;

@RestController
@RequestMapping(value = "/fabricantes")
public class FabricanteResource {

    @Autowired
    private FabricanteService service;

    @GetMapping
    public ResponseEntity<Page<FabricanteDTO>> findAll(Pageable pageable) {
        Page<FabricanteDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FabricanteDTO> findById(@PathVariable Long id) {
        FabricanteDTO dto = service.findaById(id);
        return ResponseEntity.ok().body(dto);

    }

    @PostMapping
    public ResponseEntity<FabricanteDTO> insert(@RequestBody FabricanteDTO dto) {
        FabricanteDTO newDTO = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(newDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FabricanteDTO> update(@PathVariable Long id, @RequestBody FabricanteDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}