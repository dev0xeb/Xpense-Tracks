package org.XpenseTracks.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Category {
    @Id
    private String categoryId;
    private String budgetId;
    private CategoryType categoryType;

}
