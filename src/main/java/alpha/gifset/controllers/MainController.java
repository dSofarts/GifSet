package alpha.gifset.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    public String currencyName;
    public double currency;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Курс обмена валют");
        model.addAttribute("currency", currency);
        return "index";
    }
}