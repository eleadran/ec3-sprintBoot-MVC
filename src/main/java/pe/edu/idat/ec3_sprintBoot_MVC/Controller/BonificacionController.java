package pe.edu.idat.ec3_sprintBoot_MVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.ec3_sprintBoot_MVC.model.BonificacionModel;

@Controller

public class BonificacionController {
    @GetMapping("/bonificacion")
    public String formularioBonificacion(Model model) {
        model.addAttribute("bonificacionmodel", new BonificacionModel());
        model.addAttribute("visualizaralerta", false);
        return "bonificacion";
    }

    @PostMapping("/calcularbonificacion")
    public String calcularBonificacion(@ModelAttribute("bonificacionmodel") BonificacionModel bonificacion,
                                       Model model) {

        Double monto = bonificacion.getMonto();
        Integer dias = bonificacion.getDias();
        Double descuento = 0.0;
        String rango = "";
        String estiloAlerta = "alert-danger";

        if(dias < 7) {
            descuento = 0.10;
            rango = "Menos de 7 días";
            estiloAlerta = "alert-success";
        } else if(dias <= 15) {
            descuento = 0.05;
            rango = "Entre 7 y 15 días";
            estiloAlerta = "alert-warning";
        } else {
            descuento = 0.0;
            rango = "Más de 15 días";
            estiloAlerta = "alert-dark";
        }

        Double total = monto * (1 - descuento);

        String resultado = String.format("Monto: $%.2f | Días: %d (%s) | Descuento: %.0f%% | Total: $%.2f",
                monto, dias, rango, descuento * 100, total);

        model.addAttribute("resultado", resultado);
        model.addAttribute("visualizaralerta", true);
        model.addAttribute("estiloalerta", estiloAlerta);

        return "bonificacion";
    }
}
