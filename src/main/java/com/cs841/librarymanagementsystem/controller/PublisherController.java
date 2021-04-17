package com.cs841.librarymanagementsystem.controller;

import java.util.Collection;
import java.util.List;

import com.cs841.librarymanagementsystem.entity.Publisher;
import com.cs841.librarymanagementsystem.service.PublisherService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PublisherController {

	private final PublisherService publisherService;


	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

	@RequestMapping("/publishers")
	public String findAllPublishers(Model model) {
		final List<Publisher> publishers = publisherService.findAllPublishers();

		model.addAttribute("publishers", publishers);
		//model.addAllAttributes()
		return "list-publishers";
	}

	@RequestMapping("/publisher/{id}")
	public String findPublisherById(@PathVariable("id") Long id, Model model) {
		final Publisher publisher = publisherService.findPublisherById(id);

		model.addAttribute("publisher", publisher);
		return "list-publisher";
	}

	@GetMapping("/addPublisher")
	public String showCreateForm(Publisher publisher) {
		return "add-publisher";
	}

	@RequestMapping("/add-publisher")
	public String createPublisher(Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-publisher";
		}

		publisherService.createPublisher(publisher);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

	@GetMapping("/updatePublisher/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Publisher publisher = publisherService.findPublisherById(id);

		model.addAttribute("publisher", publisher);
		return "update-publisher";
	}

	@RequestMapping("/update-publisher/{id}")
	public String updatePublisher(@PathVariable("id") Long id, Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			publisher.setId(id);
			return "update-publishers";
		}

		publisherService.updatePublisher(publisher);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

	@RequestMapping("/remove-publisher/{id}")
	public String deletePublisher(@PathVariable("id") Long id, Model model) {
		publisherService.deletePublisher(id);

		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

}
