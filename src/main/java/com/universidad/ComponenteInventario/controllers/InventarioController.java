package com.universidad.ComponenteInventario.controllers;

import com.universidad.ComponenteInventario.models.Producto;
import com.universidad.ComponenteInventario.models.ActualizarProductoDTO;
import com.universidad.ComponenteInventario.services.InventarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;


    @Operation(
        summary = "Cargar productos",
        description = "Obtiene una lista de productos disponibles en inventario (Tienda)",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de productos disponibles",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class),
                    examples = {
                        @ExampleObject(
                            name = "Primer producto en inventario",
                            value = "{\"id\":1,\"nombreProducto\":\"Refrigerador LG\",\"precio\":1050.0,\"cantidad\":5}"
                        ),
                        @ExampleObject(
                            name = "Segundo producto en inventario",
                            value = "{\"id\":2,\"nombreProducto\":\"Aspiradora Philips\",\"precio\":199.99,\"cantidad\":15}"
                        ),
                        @ExampleObject(
                            name = "Tercer producto en inventario",
                            value = "{\"id\":3,\"nombreProducto\":\"Horno eléctrico Mabe\",\"precio\":420.0,\"cantidad\":12}"
                        )
                    }
                )
            )
        }
    )
    @GetMapping("/cargarProductos")
    public List<Producto> cargarProductos() {
        return inventarioService.cargarInventario();
    }


    @Operation(
        summary = "Actualizar inventario",
        description = "Actualiza la cantidad de un producto existente (Contabilidad)",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Producto a actualizar",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Producto.class),
                examples = {
                    @ExampleObject(
                        name = "Actualizar cantidad de Licuadora Oster. (Cantidad Actualizada: 15-5 = 10)",
                        value = "{\"id\":9,\"cantidad\":5}"
                    )
                }
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Inventario actualizado")
        }
    )
    @PutMapping("/actualizarInventario/{numero}")
    public ResponseEntity<List<Producto>> actualizarInventario(@RequestBody List<ActualizarProductoDTO> productosActualizar, @PathVariable int numero) {
         
        List<Producto> actualizados = inventarioService.actualizarCantidadProducto(productosActualizar, numero);
        return ResponseEntity.ok(actualizados);
    }
    
    
    @Operation(
        summary = "Cargar requerimientos de productos",
        description = "Obtiene la lista de productos que deben reabastecerse (Proveedores)",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Lista de productos que requieren reabastecimiento",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class),
                    examples = {
                        @ExampleObject(
                            name = "Primer producto en requerimientos",
                            value = "{\"id\":1,\"nombreProducto\":\"Refrigerador Samsung\",\"precio\":1200.0,\"cantidad\":0}"
                        ),
                        @ExampleObject(
                            name = "Segundo producto en requerimientos",
                            value = "{\"id\":2,\"nombreProducto\":\"Microondas LG\",\"precio\":350.0,\"cantidad\":0}"
                        ),
                        @ExampleObject(
                            name = "Tercer producto en requerimientos",
                            value = "{\"id\":3,\"nombreProducto\":\"Licuadora Oster\",\"precio\":89.99,\"cantidad\":0}"
                        )
                    }
                )
            )
        }
    )
    @GetMapping("/cargarRequerimientosProductos")
    public List<Producto> cargarRequerimientosProductos() {
        return inventarioService.cargarRequerimientos();
    }
}
