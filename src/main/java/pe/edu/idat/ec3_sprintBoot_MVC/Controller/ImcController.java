package pe.edu.idat.ec3_sprintBoot_MVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ImcController {
    @GetMapping("/formulario")
    public String formularioImc(){
        return "formulario";
    }
}
