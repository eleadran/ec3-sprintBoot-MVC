package pe.edu.idat.ec3_sprintBoot_MVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.ec3_sprintBoot_MVC.model.DescuentoModel;

@Controller
public class DescuentoController {

    @GetMapping("/descuento")
    public String formularioDescuento(Model model) {
        model.addAttribute("descuentoModel", new DescuentoModel());
        model.addAttribute("visualizarResultado", false);
        return "descuento";
    }

    @PostMapping("/calcular-descuento")
    public String calcularDescuento(
            @ModelAttribute("descuentoModel") DescuentoModel descuento,
            Model model) {

        Integer añosAntiguedad = descuento.getañosAntiguedad();
        Double montoCompra = descuento.getMontoCompra();

        Double porcentajeDescuento = 0.0;
        String rangoAntiguedad = "";
        String estiloAlerta = "alert-secondary";

        if(añosAntiguedad < 1) {
            porcentajeDescuento = 0.02;
            rangoAntiguedad = "Menos de 1 año";
            estiloAlerta = "alert-primary";
        } else if(añosAntiguedad <= 3) {
            porcentajeDescuento = 0.05;
            rangoAntiguedad = "Entre 1 y 3 años";
            estiloAlerta = "alert-success";
        } else if(añosAntiguedad <= 5) {
            porcentajeDescuento = 0.08;
            rangoAntiguedad = "Entre 3 y 5 años";
            estiloAlerta = "alert-warning";
        } else {
            porcentajeDescuento = 0.12;
            rangoAntiguedad = "Más de 5 años";
            estiloAlerta = "alert-danger";
        }

        Double montoDescuento = montoCompra * porcentajeDescuento;
        Double totalPagar = montoCompra - montoDescuento;

        String resultado = String.format(
                "Antigüedad: %d años (%s) | Descuento: %.0f%% | Monto descuento: $%.2f | Total a pagar: $%.2f",
                añosAntiguedad, rangoAntiguedad, porcentajeDescuento * 100, montoDescuento, totalPagar
        );

        model.addAttribute("resultado", resultado);
        model.addAttribute("visualizarResultado", true);
        model.addAttribute("estiloAlerta", estiloAlerta);

        return "descuento";
    }
}