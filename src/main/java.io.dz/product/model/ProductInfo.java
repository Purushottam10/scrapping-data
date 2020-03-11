package product.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class ProductInfo {
  public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
    WebClient client = new WebClient();
    client.getOptions().setCssEnabled(false);
    client.getOptions().setJavaScriptEnabled(false);
    String productUrl = "https://www.amazon.in/Whirlpool-1-5T-MAGICOOL-PRO-COPR/dp/B07P5HJJW8/ref=gbph_img_m-4_a3bd_43a06e36?smid=AT95IG9ONZD7S&pf_rd_p=ef5bf9a4-b2b7-4f3b-bd94-c71a6be1a3bd&pf_rd_s=merchandised-search-4&pf_rd_t=101&pf_rd_i=3474656031&pf_rd_m=A1VBAL9TL5WCBF&pf_rd_r=VFWDSDPG23GEK2S1PZDQ";

    HtmlPage page = client.getPage(productUrl);
    HtmlElement productNode = ((HtmlElement) page
            .getFirstByXPath("//*[@itemtype='https://schema.org/Product']"));
    URL imageUrl = new URL((((HtmlElement) productNode.getFirstByXPath("./img")))
            .getAttribute("src"));
    HtmlElement offers = ((HtmlElement) productNode.getFirstByXPath("./span[@itemprop='offers']"));

    BigDecimal price = new BigDecimal(((HtmlElement) offers.getFirstByXPath("./span[@itemprop='price']")).asText());
    String productName = (((HtmlElement) productNode.getFirstByXPath("./span[@itemprop='name']")).asText());
    String currency = (((HtmlElement) offers.getFirstByXPath("./*[@itemprop='priceCurrency']")).getAttribute("content"));
    String productSKU = (((HtmlElement) productNode.getFirstByXPath("./span[@itemprop='sku']")).asText());

    AmazonItem product = new AmazonItem();
    product.setBrand(productName);
    product.setBasePrice(price);
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = mapper.writeValueAsString(product) ;
    System.out.println(jsonString);
  }
}
