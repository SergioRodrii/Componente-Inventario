package com.universidad.ComponenteInventario.services;

import com.universidad.ComponenteInventario.models.ActualizarProductoDTO;
import com.universidad.ComponenteInventario.models.Producto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class InventarioService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final String INVENTARIO_PATH = "src/main/resources/inventario.json";
    private final String REQUERIMIENTOS_PATH = "src/main/resources/requerimientos.json";

    public List<Producto> cargarInventario() {
        try {
            return mapper.readValue(new File(INVENTARIO_PATH), new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo inventario.json", e);
        }
    }

    public List<Producto> actualizarCantidadProducto(List<ActualizarProductoDTO> productosActualizar, int numero) {

        int nuevaCantidad;

        try {
            List<Producto> inventario = cargarInventario();

            for (ActualizarProductoDTO actualizacion : productosActualizar) {
                for (Producto p : inventario) {
                    if (p.getId().equals(actualizacion.getId())) {
                        if (numero == 1) {
                            nuevaCantidad = p.getCantidad() + actualizacion.getCantidad();
                            p.setCantidad(Math.max(nuevaCantidad, 0));
                            break;
                        } else {
                            nuevaCantidad = p.getCantidad() - actualizacion.getCantidad();
                            p.setCantidad(Math.max(nuevaCantidad, 0));
                            break;
                        }
                    }
                }
            }
            mapper.writeValue(new File(INVENTARIO_PATH), inventario);
            return inventario;
        } catch (Exception e) {
            throw new RuntimeException("Error actualizando inventario.json", e);
        }
    }

    public List<Producto> cargarRequerimientos() {
        try {
            return mapper.readValue(new File(REQUERIMIENTOS_PATH), new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo requerimientos.json", e);
        }
    }
}
