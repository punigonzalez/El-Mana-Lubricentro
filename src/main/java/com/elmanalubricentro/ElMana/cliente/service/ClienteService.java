package com.elmanalubricentro.ElMana.cliente.service;
import com.elmanalubricentro.ElMana.cliente.entity.Cliente;
import com.elmanalubricentro.ElMana.cliente.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    public Optional<Cliente> getById(Long id){
        return clienteRepository.findById(id);
    }

    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getByName(String name){
        return clienteRepository.findByName(name);
    }

    public Cliente create(Cliente c){
        if (clienteRepository.findByName(c.getName()).isPresent()) {
            throw new IllegalArgumentException("El cliente "+ c.getName() +" ya está registrado.");
        }
        return clienteRepository.save(c);
    }

    public void deleteById(Long id){
        clienteRepository.deleteById(id);
    }

    public Cliente update(Long id, Cliente c) {
        // Busca el cliente existente en la base de datos
        Cliente newCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Copia solo las propiedades NO nulas de c a cliente, excluyendo el campo id
        BeanUtils.copyProperties(c, newCliente, "id");

        // Guarda el cliente actualizado en la base de datos
        return clienteRepository.save(newCliente);
    }




}
