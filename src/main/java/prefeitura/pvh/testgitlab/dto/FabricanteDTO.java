package prefeitura.pvh.testgitlab.dto;

import prefeitura.pvh.testgitlab.entities.Fabricante;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;


public class FabricanteDTO implements Serializable {
    private Long id;
    @NotBlank(message = "Campo requerido")
    private String nome;
    private String site;

    public FabricanteDTO() {

    }

    public FabricanteDTO(Fabricante entity) {
        id = entity.getId();
        nome = entity.getNome();
        site = entity.getSite();
    }

    public FabricanteDTO(Long id, String nome, String site) {
        this.id = id;
        this.nome = nome;
        this.site = site;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSite() {
        return site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FabricanteDTO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}