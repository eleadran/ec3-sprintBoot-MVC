package pe.edu.idat.ec3_sprintBoot_MVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.ec3_sprintBoot_MVC.model.ImpuestoModel;

@Controller
public class ImpuestoController {

    @GetMapping("/impuesto")
    public String formularioProducto(Model model) {
        model.addAttribute("ImpuestoModel", new ImpuestoModel());
        model.addAttribute("visualizarResultado", false);
        return "impuesto";
    }

    @PostMapping("/calcularimpuesto")
    public String calcularImpuesto(
            @ModelAttribute("ImpuestoModel") ImpuestoModel producto,
            Model model
    ) {
        Double precio = producto.getPrecio();
        String categoria = producto.getCategoria();
        Double impuesto = 0.0;
        String estiloAlerta = "alert-secondary";

        // Determinar el impuesto según la categoría
        switch (categoria) {
            case "Electrónica":
                impuesto = 0.15;
                estiloAlerta = "alert-danger";
                break;
            case "Alimentos":
                impuesto = 0.05;
                estiloAlerta = "alert-success";
                break;
            case "Ropa":
                impuesto = 0.08;
                estiloAlerta = "alert-primary";
                break;
            case "Muebles":
                impuesto = 0.10;
                estiloAlerta = "alert-warning";
                break;
        }

        Double montoImpuesto = precio * impuesto;
        Double totalConImpuesto = precio + montoImpuesto;

        String resultado = String.format(
                "Precio: $%.2f | Categoría: %s | Impuesto (%.0f%%): $%.2f | Total: $%.2f",
                precio, categoria, impuesto * 100, montoImpuesto, totalConImpuesto
        );

        model.addAttribute("resultado", resultado);
        model.addAttribute("visualizarResultado", true);
        model.addAttribute("estiloAlerta", estiloAlerta);

        return "producto";
    }

    // Lista de categorías para el dropdown
    @ModelAttribute("categorias")
    public String[] getCategorias() {
        return new String[]{"Electrónica", "Alimentos", "Ropa", "Muebles"};
    }
}