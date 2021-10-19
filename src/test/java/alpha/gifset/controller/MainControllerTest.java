package alpha.gifset.controller;

import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Value;
import static alpha.gifset.model.MainModel.getDateYesterday;
import static alpha.gifset.model.MainModel.gifUrl;

public class MainControllerTest extends TestCase {

    private String currencyChangeRich;
    private String currencyChangeBroke;

    @Value("${giphy.url.general}")
    private String gif_url;
    @Value("${giphy.app.id}")
    private String gif_api;
    @Value("${test.url}")
    private String expected;
    @Value("${test.date}")
    private String date;

    @Override
    protected void setUp() throws Exception {
        currencyChangeRich = "rich";
        currencyChangeBroke = "broke";

    }

    public void testGifGiverRich() {
        expected = expected + currencyChangeRich;
        String actual = gifUrl(gif_url, gif_api, currencyChangeRich);
        assertEquals(expected, actual);
    }

    public void testGifGiverBroke() {
        expected = expected + currencyChangeBroke;
        String actual = gifUrl(gif_url, gif_api, currencyChangeBroke);
        assertEquals(expected, actual);
    }
    public void testGetDateYesterday() {
        String expectedDate = date;
        String actual = getDateYesterday();
        assertEquals(expectedDate, actual);
    }

    @Override
    protected void tearDown() throws Exception {

    }
}
