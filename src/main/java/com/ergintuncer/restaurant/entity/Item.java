package com.ergintuncer.restaurant.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "item", schema = "public")
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String slug;
    private String summary;
    private int type;
    private short cooking;
    private String sku;
    private double price;
    private double quantity;
    private int unit;
    private String recipe;
    private String instructions;
    @CreatedDate
    private Timestamp createdAt;
    @LastModifiedDate
    private Timestamp updatedAt;
    private String content;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 75)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "slug", nullable = false, length = 100)
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Basic
    @Column(name = "summary", nullable = true, length = -1)
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "cooking", nullable = false)
    public short getCooking() {
        return cooking;
    }

    public void setCooking(short cooking) {
        this.cooking = cooking;
    }

    @Basic
    @Column(name = "sku", nullable = false, length = 100)
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "quantity", nullable = false, precision = 0)
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "unit", nullable = false)
    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "recipe", nullable = true, length = -1)
    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    @Basic
    @Column(name = "instructions", nullable = true, length = -1)
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Basic
    @Column(name = "createdAt", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updatedAt", nullable = true)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                type == item.type &&
                cooking == item.cooking &&
                Double.compare(item.price, price) == 0 &&
                Double.compare(item.quantity, quantity) == 0 &&
                unit == item.unit &&
                Objects.equals(title, item.title) &&
                Objects.equals(slug, item.slug) &&
                Objects.equals(summary, item.summary) &&
                Objects.equals(sku, item.sku) &&
                Objects.equals(recipe, item.recipe) &&
                Objects.equals(instructions, item.instructions) &&
                Objects.equals(createdAt, item.createdAt) &&
                Objects.equals(updatedAt, item.updatedAt) &&
                Objects.equals(content, item.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, slug, summary, type, cooking, sku, price, quantity, unit, recipe, instructions, createdAt, updatedAt, content);
    }
}
