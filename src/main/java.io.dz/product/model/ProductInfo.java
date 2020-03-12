package product.model;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ProductInfo {
  public static void main(String[] args) throws FailingHttpStatusCodeException, IOException {
    ProductInfo productInfo = new ProductInfo();
    String url = "https://www.amazon.in/s/ref=mega_elec_s23_7_3_1_2?rh=i%3Acomputers%2Cn%3A14095180031&ie=UTF8";
    productInfo.getProductInfo(url);
  }

  public List<AmazonItem> getProductInfo(String url) throws FailingHttpStatusCodeException, IOException {
    Document document = Jsoup.connect(url)
            .get();
    Elements products = document.select("ul > li.s-result-item > div.s-item-container");
    List<AmazonItem> amazonItems = new ArrayList<>();
    for (Element row : products) {
      AmazonItem amazonItem = new AmazonItem();
      amazonItem.setBasePrice(this.getActualPrice(row));
      amazonItem.setBrand(this.getBrandName(row));
      amazonItem.setDiscount(this.getSavingPrice(row));
      amazonItem.setDealName(this.getTitle(row));
      amazonItem.setShortUrl(this.getImageLocation(row));
      amazonItem.setRating(this.getRating(row));
      amazonItem.setReviews(this.getReview(row));
      amazonItem.setDiscount(this.getSavingPrice(row));
      amazonItem.setSellingPrice(this.getSellingPrice(row));
      amazonItem.setVendorUrl(this.getProductUrl(row));
      amazonItems.add(amazonItem);
    }
    System.out.println(amazonItems.size());
    return amazonItems;
  }

  private String getTitle(Element rowData) {
    return rowData.select("a.s-access-detail-page > h2").text();
  }

  private String getImageLocation(Element rowData) {
    return rowData.select("div.a-column.a-span12.a-text-center > a.a-link-normal.a-text-normal > img[src]").text();
  }

  private String getProductUrl(Element rowData) {
    return rowData.select("div.a-row.a-spacing-small > div.a-row.a-spacing-none > a[href]").text();
  }

  private int getReview(Element rowData) {
    String noOfReview = rowData.select("div.a-column.a-span5.a-span-last >" +
            "                div.a-row.a-spacing-mini > " +
            "                a.a-size-small.a-link-normal.a-text-normal").text();
    return Integer.parseInt(noOfReview.replaceAll(",", ""));
  }

  private float getSellingPrice(Element rowData) {
    String sellingPriceSelector[] = {"span.a-size-base.a-color-price.s-price.a-text-bold", "span.a-price-whole"};
    String sellingPrice = "0";
    for (int i = 0; i < sellingPriceSelector.length - 1; i++) {
      sellingPrice = rowData.select(sellingPriceSelector[i]).text();
      if (!sellingPrice.isEmpty())
        break;
    }
    if (sellingPrice.contains("-")) {
      String temp = sellingPrice.substring(0, sellingPrice.indexOf("-") - 1);
      return Float.parseFloat(temp.replaceAll(",", ""));
    } else {
      return Float.parseFloat(sellingPrice.replaceAll(",", ""));
    }

  }

  private String getBrandName(Element rowData) {
    String brandSelector[] = {"div.a-row.a-spacing-small > div.a-row.a-spacing-none > span:nth-child(2)",
            "div.a-row.a-spacing-small > div.a-row.a-spacing-none"};
    String brandName = StringUtils.EMPTY;
    for (int i = 0; i < brandSelector.length - 1; i++) {
      brandName = rowData.select(brandSelector[i]).text();
      if (!brandName.isEmpty())
        break;
    }
    return brandName;
  }

  private float getActualPrice(Element rowData) {
    String actualPriceSelector[] = {"span.a-size-small.a-color-secondary.a-text-strike", "span.a-price.a-text-price > span.a-offscreen",
            "span.a-color-price", "div.a-row.a-size-base.a-color-secondary > span.a-color-price"};
    String actualPrice = "0";
    Elements actualPriceEl;
    for (int i = 0; i < actualPriceSelector.length - 1; i++) {
      actualPriceEl = rowData.select(actualPriceSelector[i]);
      if (!actualPriceEl.isEmpty()) {
        actualPrice = actualPriceEl.get(0).text();
        break;
      }
    }
    if (actualPrice.contains("-")) {
      String temp = actualPrice.substring(0, actualPrice.indexOf("-") - 1);
      return Float.parseFloat(temp.replaceAll(",", ""));
    } else {
      return Float.parseFloat(actualPrice.replaceAll(",", ""));
    }
  }

  private int getSavingPrice(Element rowData) {
    String savingPriceSelector[] = {"span.a-size-small.a-color-price", "div.a-section.a-spacing-none.a-spacing-top-small > div > div > span:nth-child(3)"};
    String savingPrice = "0";
    String subSavingPrice = "";
    for (int i = 0; i < savingPriceSelector.length - 1; i++) {
      savingPrice = rowData.select(savingPriceSelector[i]).text();
      if (!savingPrice.isEmpty()) {
        subSavingPrice = savingPrice.substring(0, savingPrice.indexOf("(") - 1);
        break;
      }
    }
    if (subSavingPrice.isEmpty()) {
      return 0;
    } else {
      return Integer.parseInt(subSavingPrice.replaceAll(",", ""));
    }
  }

  private int getRating(Element rowData) {
    String rating = rowData.select("i.a-icon-star > span").text();
    String subRating = rating.substring(0, rating.indexOf(".") + 2);
    float ratingVal = Float.parseFloat(subRating);
    return Math.round(ratingVal);
  }
}