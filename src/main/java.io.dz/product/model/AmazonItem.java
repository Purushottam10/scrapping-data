package product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmazonItem  implements Serializable {
  private String brand;
  private String vendorUrl;
  private Integer vendorId;
  private BigDecimal sellingPrice;
  private Integer discount;
  private String source;
  private Integer reviews;
  private BigDecimal basePrice;

}
