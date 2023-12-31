package fa.edu.api.controllers;

import fa.edu.api.entities.Book;
import fa.edu.api.requests.BookForm;
import fa.edu.api.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Book controller class.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 31/07/2023
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/books")
@Slf4j
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  /**
   * Get number of pages.
   */
  @GetMapping(path = "/pages-number")
  public ResponseEntity<List<Long>> getPagesNumber(
      @RequestParam(value = "search", defaultValue = "", required = false) String searchKey,
      @RequestParam(value = "categoryId", defaultValue = "0", required = false) int categoryId
  ) {
    log.info("get Pages Number");
    return ResponseEntity.ok().body(bookService.getPagesNumber(searchKey, categoryId));
  }

  /**
   * Get all the books.
   *
   * @return book list
   */
  @GetMapping
  public ResponseEntity<List<Book>> getAllBook(
      @RequestParam(value = "search", defaultValue = "", required = false) String searchKey,
      @RequestParam(value = "page", defaultValue = "1", required = false) int page
  ) {
    log.info("get All Book");
    List<Book> books = bookService.findAll(searchKey, page);
    return ResponseEntity.ok().body(books);
  }

  /**
   * Get all the books by category.
   *
   * @param idCategory of the category
   * @return list of books
   */
  @GetMapping(path = "/category/{idCategory}")
  public ResponseEntity<List<Book>> getAllBookByCategoryId(
      @PathVariable(name = "idCategory") Long idCategory,
      @RequestParam(value = "search", defaultValue = "", required = false) String searchKey
  ) {
    log.info("get All Book by category id");
    List<Book> books = bookService.findAllByCategoryId(idCategory, searchKey);
    return ResponseEntity.ok().body(books);
  }

  /**
   * Get book by id.
   *
   * @param bookId the id of book
   * @return book
   */
  @GetMapping(path = "/{bookId}")
  public ResponseEntity<Book> getAllBookById(@PathVariable(value = "bookId") Long bookId) {
    log.info("get All Book By Id");
    Book book = bookService.findById(bookId);
    return ResponseEntity.ok().body(book);
  }

  /**
   * Add new Book.
   *
   * @param bookForm information about the book
   * @return book
   */
  @PostMapping()
  public ResponseEntity<Book> addNewBook(@ModelAttribute BookForm bookForm) {
    log.info("add New Book");
    Book book = bookService.addBook(bookForm);
    return ResponseEntity.ok().body(book);
  }

  /**
   * Update Book.
   *
   * @param bookForm information about the book
   * @return book
   */
  @PutMapping()
  public ResponseEntity<Book> updateBook(@ModelAttribute BookForm bookForm) {
    log.info("update Book");
    Book book = bookService.updateBook(bookForm);
    return ResponseEntity.ok().body(book);
  }

  /**
   * Delete Book.
   *
   * @param bookId need to delete
   * @return status delete
   */
  @DeleteMapping(path = "/{bookId}")
  public ResponseEntity<Boolean> deleteBook(@PathVariable(name = "bookId") Long bookId) {
    log.info("delete Book");
    boolean status = bookService.deleteBook(bookId);
    return ResponseEntity.ok().body(status);
  }

  /**
   * Top good price book.
   */
  @GetMapping(path = "/top-good-price")
  public ResponseEntity<List<Book>> getTopGoodPriceBook() {
    log.info("top good price book");
    return ResponseEntity.ok().body(bookService.topGoodPriceBook(5));
  }
}
