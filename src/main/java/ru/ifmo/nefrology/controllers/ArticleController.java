package ru.ifmo.nefrology.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ifmo.nefrology.entity.Article;
import ru.ifmo.nefrology.entity.User;
import ru.ifmo.nefrology.repository.ArticleRepository;

@Controller
public class ArticleController {
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @RequestMapping(value = "/article", method = RequestMethod.GET)
    public String articleForm(Model model) {
        model.addAttribute("article", new Article());
        return "article";
    }

    @RequestMapping(value = "/article", method = RequestMethod.POST)
    public String article(Article article) {
        articleRepository.save(article);
        System.out.println("ok");
        return "article";
    }

    @PostMapping("/articleFind") //Поиск статьи. метод должен вернуть Успех или причину проблемы
    public @ResponseBody
    String articleFind(@RequestParam(value = "title") String title) {
        if (!articleRepository.findByTitle(title).isEmpty()) { // метод вернет объект User по логину
            return articleRepository.findByTitle(title).get().getText();
        } else return "ERROR!!!";
    }
}