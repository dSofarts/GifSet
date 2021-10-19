package alpha.gifset.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static alpha.gifset.model.MainModel.getDateYesterday;
import static alpha.gifset.model.MainModel.getUrlContent;
import static alpha.gifset.model.MainModel.gifUrl;

@Controller
public class MainController {

    @Value("${openexchangerates.url.general}")
    private String url;
    @Value("${openexchangerates.app.id}")
    private String api;
    @Value("${openexchangerates.currency.key}")
    private String currencyKey;

    @Value("${giphy.url.general}")
    private String gif_url;
    @Value("${giphy.app.id}")
    private String gif_api;

    public double currency;
    public double newCurr;
    public String pastCurr;
    public double pastCurrDouble;

    @GetMapping("/")
    public String home(Model model) {
        String nowOutput = getUrlContent( url + "/latest.json?app_id=" + api);
        String pastOutput = getUrlContent(url + "/historical/" + getDateYesterday() + ".json?app_id=" + api);
        if (!nowOutput.isEmpty() && !pastOutput.isEmpty()) {
            JSONObject nowObj = new JSONObject(nowOutput);
            JSONObject pastObj = new JSONObject(pastOutput);
            newCurr = nowObj.getJSONObject("rates").getDouble(currencyKey);
            pastCurrDouble = Math.round((newCurr - pastObj.getJSONObject("rates").getDouble(currencyKey)) * 100) / 100.00;
            if (pastCurrDouble > 0) {
                pastCurr = "+" + Double.toString(pastCurrDouble);
            } else {
                pastCurr = Double.toString(pastCurrDouble);
            }
        }
        model.addAttribute("title", "Курс обмена валют");
        model.addAttribute("currency", currency);
        model.addAttribute("newCurr", newCurr);
        model.addAttribute("pastCurr", pastCurr);
        model.addAttribute("currencyKey", currencyKey);
        return "index";
    }
    @GetMapping("/gif/{tag}")
    public String gifGiver(@PathVariable(value = "tag") String tag, Model model) {
        String titleGif;
        if (tag.equals("rich")) {
            titleGif = "УРА! ВЫ БОГАТЫ!";
        } else {
            titleGif = "УВЫ, ВЫ БЕДНЫ";
        }
        String gifIMG = getUrlContent(gifUrl(gif_url, gif_api, tag));
        String gifImgUrl = "";
        if (!gifIMG.isEmpty()) {
            JSONObject gifImgJson = new JSONObject(gifIMG);
            gifImgUrl = gifImgJson.getJSONObject("data").getJSONObject("images").getJSONObject("downsized_large").getString("url");
        }
        model.addAttribute("title", titleGif);
        model.addAttribute("currency", currency);
        model.addAttribute("newCurr", newCurr);
        model.addAttribute("pastCurr", pastCurr);
        model.addAttribute("currencyKey", currencyKey);
        model.addAttribute("gifIMG", gifImgUrl);
        return "index";
    }
}