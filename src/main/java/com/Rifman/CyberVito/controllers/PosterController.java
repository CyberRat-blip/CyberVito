package com.Rifman.CyberVito.controllers;

import com.Rifman.CyberVito.models.Post;
import com.Rifman.CyberVito.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PosterController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/post")
    public String postMain(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "post-main";
    }

    @GetMapping("/post/add")
    public String postAdd(Model model){
        return "post-add";
    }
    @PostMapping("/post/add")
    public String ticketAdd(@RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model){
        Post post = new Post(title,anons,full_text);
        postRepository.save(post);
        return "redirect:/post";
    }

    @GetMapping("/post/{id}")
    public String postDetails(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/post";
        }
       Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
       model.addAttribute("post",res);
        return "post-details";
    }

    @GetMapping("/post/{id}/edit")
    public String postEdit(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/post";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "post-edit";
    }

    @PostMapping("/post/{id}/edit")
    public String ticketUdate(@PathVariable(value = "id") long id,@RequestParam String title, @RequestParam String anons,@RequestParam String full_text, Model model){
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);

        return "redirect:/post";
    }
}
