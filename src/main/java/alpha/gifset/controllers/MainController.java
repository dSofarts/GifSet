package alpha.gifset.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static alpha.gifset.model.MainModel.getDateYesterday;
import static alpha.gifset.model.MainModel.getUrlContent;

@Controller
public class MainController {

    @Value("${openexchangerates.url.general}")
    private String url;
    @Value("${openexchangerates.app.id}")
    private String api;

    public double currency;
    public double newCurr;
    public String pastCurr;

    @GetMapping("/")
    public String home(Model model) {
        String nowOutput = getUrlContent( url + "/latest.json?app_id=" + api);
        String pastOutput = getUrlContent(url + "/historical/" + getDateYesterday() + ".json?app_id=" + api);
        if (!nowOutput.isEmpty() && !pastOutput.isEmpty()) {
            JSONObject nowObj = new JSONObject(nowOutput);
            JSONObject pastObj = new JSONObject(pastOutput);
            newCurr = nowObj.getJSONObject("rates").getDouble("RUB");
            double pastCurrDouble = Math.round((newCurr - pastObj.getJSONObject("rates").getDouble("RUB")) * 100) / 100.00;
            if (pastCurrDouble > 0) {
                pastCurr = "+ " + Double.toString(pastCurrDouble);
            } else {
                pastCurr = Double.toString(pastCurrDouble);
            }
        }
        model.addAttribute("title", "Курс обмена валют");
        model.addAttribute("currency", currency);
        model.addAttribute("newCurr", newCurr);
        model.addAttribute("pastCurr", pastCurr);
        return "index";
    }
}