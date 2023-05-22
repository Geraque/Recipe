package com.example.coursework.facade;

import com.example.coursework.dto.RecipeDTO;
import com.example.coursework.entity.Recipe;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RecipeFacade {

    public RecipeDTO recipeToRecipeDTO(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setUsername(recipe.getUser().getUsername());
        recipeDTO.setRecipeId(recipe.getRecipeId());
        recipeDTO.setDescription(recipe.getDescription());
        recipeDTO.setLikes(recipe.getLikes());
        recipeDTO.setUsersLiked(recipe.getLikedUsers());
        recipeDTO.setRecipeName(recipe.getRecipeName());
        recipeDTO.setRecipeNutrition(recipe.getRecipeNutrition());
        return recipeDTO;
    }

    public ByteArrayResource exportRecipesToExcel(List<Recipe> recipes) {
        String[] header = {"Id", "Recipe Name", "Description", "Nutrition", "Likes"};

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Recipes");

            // Запись заголовков
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < header.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header[i]);
            }

            // Запись данных
            int rowNum = 1;
            for (Recipe recipe : recipes) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(recipe.getRecipeId());
                row.createCell(1).setCellValue(recipe.getRecipeName());
                row.createCell(2).setCellValue(recipe.getDescription());
                row.createCell(4).setCellValue(recipe.getLikes());
            }

            workbook.write(byteArrayOutputStream);
            return new ByteArrayResource(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error occurred during exporting recipes to Excel");
        }
    }
}
