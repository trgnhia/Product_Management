package org.example.product_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    List<T> content;
    int page;
    int size;
    private Long totalElements;
    private int totalPages;
    private boolean isLast;
}
