package com.nps.testboot.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/bookList")
public class BookListController {
    
    private BookListRepository bookListRepository;

    @Autowired
    public BookListController(BookListRepository bookListRepository){
        this.bookListRepository = bookListRepository;
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model){
        List<Book> bookList = bookListRepository.findByReader(reader);
        if(bookList != null){
            model.addAttribute("books", bookList);
        }
        return "bookList";
    }

    @RequestMapping(value = "/{reader}", method = RequestMethod.POST)
    public String addToBookList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        bookListRepository.save(book);
        return "redirect:/bookList/{reader}";
    }

}
