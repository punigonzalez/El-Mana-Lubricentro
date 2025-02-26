package com.elmanalubricentro.ElMana.cajero.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.elmanalubricentro.ElMana.caja.entity.Caja;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cajero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_cajero;
    
    private String nombre;

    // Esto deberia estar manejado por spring security pero de momento esta aca xd

    private String usuario;
    private String contrasenia;

    // ---------------------------------------------------------------------------

   // private List<Caja> cajas;
    private boolean activo;


}
