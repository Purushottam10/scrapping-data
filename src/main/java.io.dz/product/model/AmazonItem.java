package product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmazonItem  implements Serializable {
  private Integer id;
  private String brand = StringUtils.EMPTY;
  private String dealName = StringUtils.EMPTY;
  private Integer categoryId;
  private String dealType = StringUtils.EMPTY;
  private Integer vendorId;
  private String vendorUrl = StringUtils.EMPTY;
  private String shortUrl = StringUtils.EMPTY;
  private String images = StringUtils.EMPTY;
  private String snapshotUrl = StringUtils.EMPTY;
  private String tags = StringUtils.EMPTY;
  private Float basePrice = 0f;
  private Float sellingPrice = 0f;
  private Integer discount = 0;
  private Boolean active = true;
  private Boolean enabled;
  private Integer reviews = 0;
  private Integer rating = 0;
  private String source = StringUtils.EMPTY;

  private String dealkey = StringUtils.EMPTY;
  private String dealURLHash = StringUtils.EMPTY;
  private Date createTimestamp;
  private Date updateTimestamp;

  @Override
  public String toString() {
    return "Deal{" +
            "brand='" + brand + '\'' +
            ", dealName='" + dealName + '\'' +
            ", categoryId=" + categoryId +
            ", dealType='" + dealType + '\'' +
            ", vendorId=" + vendorId +
            ", vendorUrl='" + vendorUrl + '\'' +
            ", shortUrl='" + shortUrl + '\'' +
            ", images='" + images + '\'' +
            ", snapshotUrl='" + snapshotUrl + '\'' +
            ", tags='" + tags + '\'' +
            ", basePrice=" + basePrice +
            ", sellingPrice=" + sellingPrice +
            ", discount=" + discount +
            ", active=" + active +
            ", enabled=" + enabled +
            ", reviews=" + reviews +
            ", rating=" + rating +
            ", source='" + source + '\'' +
            ", dealkey='" + dealkey + '\'' +
            ", dealURLHash='" + dealURLHash + '\'' +
            ", createTimestamp=" + createTimestamp +
            ", updateTimestamp=" + updateTimestamp +
            '}';
  }

}
