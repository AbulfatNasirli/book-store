package com.company.controller;

import com.company.dto.request.BookOrderRequest;
import com.company.model.Book;
import com.company.model.Order;
import com.company.service.BookService;
import com.company.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/bookstore")
@Tag(name = "Bookstore services")
public class BookStoreController {

    private final OrderService orderService;
    private final BookService bookService;

    public BookStoreController(OrderService orderService, BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }

    @GetMapping("/books")
    @Operation(summary = "This get all books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/orders")
    @Operation(summary = "this get all orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderList = orderService.getAllOrders();
        return ResponseEntity.ok(orderList);
    }

    @PostMapping
    public ResponseEntity<Order> putAnOrder(@RequestBody BookOrderRequest bookOrderRequest) {
        Order order = orderService.putAnOrder(bookOrderRequest.getBookIdList(), bookOrderRequest.getUserName());

        return ResponseEntity.ok(order);
    }

    @PostMapping("/addBook")
    @Operation(summary = "this add book")
    public void insertBook(@Valid @RequestBody Book book) {
        bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "this remove book")
    public void deleteBook(@PathVariable Integer id) {
        bookService.removeBook(id);
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "search book by name and author")
    public List<Book> findByNameAndAuthor(@RequestParam("name") String name,
                                          @RequestParam("author") String author) {
        return bookService.findBookByNameAndAuthor(name, author);
    }
}