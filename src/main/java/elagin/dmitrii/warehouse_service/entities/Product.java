package elagin.dmitrii.warehouse_service.entities;

import jakarta.validation.constraints.Size;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @Column(name = "article_number", nullable = false)
    private Long articleNumber;

    @Column(nullable = false)
    @Size(min = 2, max = 128)
    private String name;

    @Column(name = "purchase_price", nullable = false)
    private BigDecimal purchasePrice;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    public Product() {
    }

    public Product(Long articleNumber, String name, BigDecimal purchasePrice, BigDecimal salePrice) {
        this.articleNumber = articleNumber;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
    }

    public Product(Long articleNumber, String name, BigDecimal purchasePrice) {
       this(articleNumber, name, purchasePrice, null);
    }

    public Long getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(Long articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal sellPrice) {
        this.salePrice = sellPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "articleNumber=" + articleNumber +
                ", name='" + name + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", sellPrice=" + salePrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return articleNumber.equals(product.articleNumber) && name.equals(product.name) && purchasePrice.equals(product.purchasePrice) && Objects.equals(salePrice, product.salePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleNumber, name, purchasePrice, salePrice);
    }
}
