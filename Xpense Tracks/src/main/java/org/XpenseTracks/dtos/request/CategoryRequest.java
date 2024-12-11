package org.XpenseTracks.dtos.request;

import lombok.Data;
import org.XpenseTracks.data.model.CategoryType;
@Data
public class CategoryRequest {
    private String budgetId;
    private CategoryType categoryType;
}
