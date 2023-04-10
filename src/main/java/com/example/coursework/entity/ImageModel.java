package com.example.coursework.entity;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Arrays;


@Data
@Entity
@Table(name = "image_model")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long imageId;
    @Column(nullable = false)
    private String name;
    @Lob
    @Column(length=90000)
    private byte[] imageBytes;
    @JsonIgnore
    private Long userId;
    @JsonIgnore
    private Long recipeId;

    public ImageModel() {
    }

    public ImageModel(Long imageId, String name, byte[] imageBytes, Long userId, Long recipeId) {
        this.imageId = imageId;
        this.name = name;
        this.imageBytes = imageBytes;
        this.userId = userId;
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "ImageModel{" +
                "imageId=" + imageId +
                ", name='" + name + '\'' +
                ", imageBytes=" + Arrays.toString(imageBytes) +
                ", userId=" + userId +
                ", recipeId=" + recipeId +
                '}';
    }

}
