package mate.academy.bookshop.dto.book;

public record BookSearchParameters(String[] titles, String[] authors, String[] prices) {
}
