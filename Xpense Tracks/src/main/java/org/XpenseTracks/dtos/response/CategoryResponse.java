package org.XpenseTracks.dtos.response;

import lombok.Data;
import org.XpenseTracks.data.model.CategoryType;
@Data
public class CategoryResponse {
    private String categoryId;
    private String budgetId;
    private CategoryType categoryType;
    private String message;
}
