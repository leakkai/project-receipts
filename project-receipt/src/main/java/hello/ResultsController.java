package hello;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Controller
public class ResultsController extends WebMvcConfigurerAdapter {

/*    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }*/

/*    @GetMapping("/results")
    public String showResults(Model model) {
    	model.addAttribute("name", "justenter");
    	model.addAttribute("age", "34");
    	
        return "results";
    }*/

/*    @PostMapping("/results")
    public String showPostREsults(@Valid PersonForm personForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "results";
        }

        return "redirect:/results";
//        return "results";
    }*/
}
