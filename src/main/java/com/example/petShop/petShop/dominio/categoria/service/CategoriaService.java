package com.example.petShop.petShop.dominio.categoria.service;

import com.example.petShop.petShop.dominio.categoria.entity.Categoria;
import com.example.petShop.petShop.dominio.categoria.entity.dtoS.CategoriaDTO;
import com.example.petShop.petShop.dominio.categoria.repository.ICategoriaRepository;
import com.example.petShop.petShop.exception.Service.ControllerNotFoundException;
import com.example.petShop.petShop.exception.Service.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository repo;

    public Page<CategoriaDTO> findAll(PageRequest pageRequest){
        Page<Categoria> list = repo.findAll(pageRequest);
        return list.map(x -> new CategoriaDTO(x.getId(), x.getNome(), x.getDataDeCriacao(), x.getProdutos()));
    }

    public CategoriaDTO findById(Long id){
        Optional<Categoria> entity = repo.findById(id);
        Categoria categoria = entity.orElseThrow(() -> new ControllerNotFoundException("Categoria não Encontrada"));

        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.getDataDeCriacao(), categoria.getProdutos());
    }

    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        mapperDtoToEntity(dto, entity);
        Categoria categoriaInserted = repo.save(entity);

        return new CategoriaDTO(categoriaInserted.getId(), categoriaInserted.getNome(), categoriaInserted.getDataDeCriacao(), categoriaInserted.getProdutos());
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        try{
            Categoria entity = repo.getOne(id);
            mapperDtoToEntity(dto, entity);
            entity = repo.save(entity);

            return new CategoriaDTO(entity.getId(), entity.getNome(), entity.getDataDeCriacao(), entity.getProdutos());
        }catch (EntityNotFoundException e){
            throw new ControllerNotFoundException("Categoria não Encontrada" + id);
        }
    }

    public void delete(Long id){
        try{
            repo.deleteById(id);

        } catch (EmptyResultDataAccessException e){
            throw new ControllerNotFoundException("Categoria não Encontrada " + id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade da base ");
        }
    }

    private void mapperDtoToEntity (CategoriaDTO dto, Categoria entity){
        entity.setNome(dto.nome());
    }
}
