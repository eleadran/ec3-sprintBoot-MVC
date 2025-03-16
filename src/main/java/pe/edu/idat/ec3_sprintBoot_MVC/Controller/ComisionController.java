package pe.edu.idat.ec3_sprintBoot_MVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.ec3_sprintBoot_MVC.model.ComisionModel;

@Controller
public class ComisionController {
    @GetMapping("/comision")
    public String formularioComision(Model model) {
        model.addAttribute("comisionmodel", new ComisionModel());
        model.addAttribute("visualizaralerta", false);
        return "comision";
    }

    @PostMapping("/calcularcomision")
    public String calcularComision(@ModelAttribute("comisionmodel") ComisionModel comision,
                                   Model model) {

        Double ventas = comision.getVentas();
        Double porcentaje = 0.0;
        String rango = "";
        String estiloAlerta = "alert-danger";

        if(ventas < 1000) {
            porcentaje = 0.03;
            rango = "Menos de $1000";
            estiloAlerta = "alert-primary";
        } else if(ventas <= 5000) {
            porcentaje = 0.05;
            rango = "Entre $1000 y $5000";
            estiloAlerta = "alert-success";
        } else if(ventas <= 10000) {
            porcentaje = 0.07;
            rango = "Entre $5000 y $10000";
            estiloAlerta = "alert-warning";
        } else {
            porcentaje = 0.10;
            rango = "Más de $10000";
            estiloAlerta = "alert-dark";
        }

        Double comisionCalculada = ventas * porcentaje;

        String resultado = String.format("Ventas: $%.2f | Rango: %s | Comisión (%.0f%%): $%.2f",
                ventas, rango, porcentaje * 100, comisionCalculada);

        model.addAttribute("resultado", resultado);
        model.addAttribute("visualizaralerta", true);
        model.addAttribute("estiloalerta", estiloAlerta);

        return "comision";
    }
}
