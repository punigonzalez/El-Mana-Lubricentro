
package com.elmanalubricentro.ElMana.venta.service;
import com.elmanalubricentro.ElMana.cliente.entity.Cliente;
import com.elmanalubricentro.ElMana.cliente.repository.ClienteRepository;
import com.elmanalubricentro.ElMana.metodoPago.entity.MetodoPago;
import com.elmanalubricentro.ElMana.metodoPago.repository.IMetodoPagoRepository;
import com.elmanalubricentro.ElMana.producto.entity.Producto;
import com.elmanalubricentro.ElMana.producto.repository.IProductoRepository;
import com.elmanalubricentro.ElMana.venta.entity.ProductoVentaDTO;
import com.elmanalubricentro.ElMana.venta.entity.Venta;
import com.elmanalubricentro.ElMana.venta.entity.VentaProducto;
import com.elmanalubricentro.ElMana.venta.entity.VentaRequestDTO;
import com.elmanalubricentro.ElMana.venta.repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class VentaService {


    private final VentaRepository ventaRepository;
    private final IProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    private final IMetodoPagoRepository metodoPagoRepository;

    public VentaService(VentaRepository ventaRepository, IProductoRepository productRepository, ClienteRepository clienteRepository, IMetodoPagoRepository metodoPagoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productRepository;
        this.clienteRepository = clienteRepository;
        this.metodoPagoRepository = metodoPagoRepository;
    }

    public Optional<Venta> getByID(Long id){
        return ventaRepository.findById(id);
    }

    @Transactional
    public Venta crearVenta(VentaRequestDTO ventaDTO) {
        // Obtener o crear cliente
        Cliente cliente = obtenerOCrearCliente(ventaDTO.getClienteId(), ventaDTO.getClienteData());

        // Obtener método de pago
        MetodoPago metodoPago = obtenerMetodoPago(ventaDTO.getMetodoPagoId());

        // Crear la venta
        Venta venta = new Venta();
        venta.setFecha(LocalDate.now());
        venta.setHora(LocalTime.now());
        venta.setDetalle(ventaDTO.getDetalle());
        venta.setCliente(cliente);
        venta.setMetodoPago(metodoPago);

        // Guardar la venta para obtener su ID
        venta = ventaRepository.save(venta);

        // Procesar productos y calcular total
        Set<VentaProducto> ventaProductos = procesarProductos(venta, ventaDTO.getProductos());
        venta.setVentaProductos(ventaProductos);

        // Calcular y establecer el total
        Double total = calcularTotal(ventaProductos);
        venta.setTotal(total);

        // Guardar la venta actualizada
        return ventaRepository.save(venta);
    }

    private Cliente obtenerOCrearCliente(Long clienteId, Cliente clienteData) {
        if (clienteId != null) {
            // Buscar cliente existente por ID
            return clienteRepository.findById(clienteId)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Cliente con ID " + clienteId + " no encontrado"));
        } else if (clienteData != null) {
            // Crear nuevo cliente
            return clienteRepository.save(clienteData);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Debe proporcionar un ID de cliente o datos para crear uno nuevo");
        }
    }

    private MetodoPago obtenerMetodoPago(Long metodoPagoId) {
        if (metodoPagoId == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Debe proporcionar un ID de método de pago");
        }

        return metodoPagoRepository.findById(metodoPagoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Método de pago con ID " + metodoPagoId + " no encontrado"));
    }

    private Set<VentaProducto> procesarProductos(Venta venta, Set<ProductoVentaDTO> productosDTO) {
        if (productosDTO == null || productosDTO.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Debe proporcionar al menos un producto para la venta");
        }

        Set<VentaProducto> ventaProductos = new HashSet<>();

        for (ProductoVentaDTO productoDTO : productosDTO) {
            // Obtener el producto
            Producto producto = productoRepository.findById(productoDTO.getProductoId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Producto con ID " + productoDTO.getProductoId() + " no encontrado"));

            // Verificar stock
            if (producto.getStock() < productoDTO.getCantidad()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Stock insuficiente para el producto " + producto.getProducto_name() +
                                ". Disponible: " + producto.getStock() + ", Solicitado: " + productoDTO.getCantidad());
            }

            // Actualizar stock
            producto.setStock(producto.getStock() - productoDTO.getCantidad());
            productoRepository.save(producto);

            // Crear el registro de VentaProducto
            VentaProducto ventaProducto = new VentaProducto();
            ventaProducto.setVenta(venta);
            ventaProducto.setProducto(producto);
            ventaProducto.setCantidad(productoDTO.getCantidad());

            // Usar el precio actual del producto
            ventaProducto.setPrecioUnitario(producto.getPrice());

            ventaProductos.add(ventaProducto);
        }

        return ventaProductos;
    }

    private Double calcularTotal(Set<VentaProducto> ventaProductos) {
        double total = 0.0;

        for (VentaProducto ventaProducto : ventaProductos) {
            BigDecimal subtotal = ventaProducto.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(ventaProducto.getCantidad()));
            total += subtotal.doubleValue();
        }

        return total;
    }

}