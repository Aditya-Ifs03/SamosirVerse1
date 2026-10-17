package com.samosirverse.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class ProductRequest {

    @NotNull(message = "ID Kreator pembuat produk wajib diisi")
    private Long creatorId;

    @NotBlank(message = "Nama produk UMKM wajib diisi")
    @Size(max = 150, message = "Nama produk maksimal 150 karakter")
    private String name;

    @NotBlank(message = "Kategori produk wajib dipilih")
    private String category;

    @NotNull(message = "Harga produk wajib diisi")
    @DecimalMin(value = "1000.0", message = "Harga produk minimal Rp 1.000")
    private BigDecimal price;

    @NotBlank(message = "Deskripsi produk wajib diisi")
    private String description;

    @NotBlank(message = "URL foto produk wajib diisi")
    private String imageUrl;

    private String stockStatus = "TERSEDIA";
    private Boolean isAuthenticHandcrafted = true;

    public ProductRequest() {}

    public Long getCreatorId() { return creatorId; }
    public void setCreatorId(Long creatorId) { this.creatorId = creatorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getStockStatus() { return stockStatus; }
    public void setStockStatus(String stockStatus) { this.stockStatus = stockStatus; }

    public Boolean getIsAuthenticHandcrafted() { return isAuthenticHandcrafted; }
    public void setIsAuthenticHandcrafted(Boolean isAuthenticHandcrafted) { this.isAuthenticHandcrafted = isAuthenticHandcrafted; }
}
