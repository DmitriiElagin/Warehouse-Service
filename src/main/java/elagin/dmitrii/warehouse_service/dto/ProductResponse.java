package elagin.dmitrii.warehouse_service.dto;

import elagin.dmitrii.warehouse_service.entities.Product;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductResponse {

    private Long articleNumber;

    private String name;

    private BigDecimal purchasePrice;

    private BigDecimal salePrice;

    public ProductResponse() {
    }

    public ProductResponse(Long articleNumber, String name, BigDecimal purchasePrice, BigDecimal salePrice) {
        this.articleNumber = articleNumber;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
    }

    public ProductResponse(Product product) {
        this(product.getArticleNumber(), product.getName(), product.getPurchasePrice(), product.getSalePrice());
    }

    public ProductResponse(Long articleNumber, String name, BigDecimal purchasePrice) {
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

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
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
        ProductResponse product = (ProductResponse) o;
        return articleNumber.equals(product.articleNumber) && name.equals(product.name) && purchasePrice.equals(product.purchasePrice) && Objects.equals(salePrice, product.salePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleNumber, name, purchasePrice, salePrice);
    }
}
