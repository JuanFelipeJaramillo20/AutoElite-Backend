package com.autoelite.AutoElite.reportes;

import com.autoelite.AutoElite.errores.ErrorMessage;
import com.autoelite.AutoElite.security.ConfirmationMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reporte")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<?> getPublicaciones() {
        try {
            return ResponseEntity.ok(reporteService.getAllReportes());
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Reportes no encontrados"));
        }
    }

    @GetMapping("{idReporte}")
    public ResponseEntity<?> getReporteById(@PathVariable("idReporte") String id) {
        try {
            return ResponseEntity.ok(reporteService.getReporteById(id));
        }catch (NullPointerException e){
            String errorMessage = "Reporte no encontrado con id: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(errorMessage));
        }
    }

    @PostMapping
    public ResponseEntity<?> addReporte(@RequestBody ReporteRequest request) {
        reporteService.addReporte(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ConfirmationMessage("La publicación fue reportada exitosamente"));
    }

    @DeleteMapping("{idReporte}")
    public ResponseEntity<?> deleteReporte(@PathVariable("idReporte") String id) {
        if (reporteService.existsReporte(id)) {
            reporteService.deleteReportes(id);
            return ResponseEntity.ok(new ConfirmationMessage("Reporte con ID " + id + " ha sido eliminado exitosamente"));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Reporte con ID " + id + " no existe"));
        }
    }
}
