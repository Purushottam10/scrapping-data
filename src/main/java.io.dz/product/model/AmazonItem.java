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
public class AmazonItem implements Serializable {
  private String brand;
  private String vendorUrl;
  private Integer vendorId;
  private BigDecimal sellingPrice = BigDecimal.ZERO;
  private Integer discount = 0;
  private String source;
  private Integer reviews = 0;
  private BigDecimal basePrice = BigDecimal.ZERO;

}
