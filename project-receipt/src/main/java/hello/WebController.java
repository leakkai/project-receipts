package hello;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@RequestMapping(path="/")
public class WebController extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(PersonForm personForm) {


    	return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "form";
        }
        
        
        
//        return "redirect:/results";
        return "forward:/results";
//        return "results";
    }
    
    @GetMapping("/results")
    public String showResults(@RequestParam(value="name")String name, @RequestParam(value="age")String age, Model model) {
    	model.addAttribute("name", name);
    	model.addAttribute("age", age);
    	
        return "results";
    }
    
    @PostMapping("/results")
    public String showPostResults(@Valid PersonForm personForm, Model model) {
    	model.addAttribute("name", personForm.getName());
    	model.addAttribute("age", personForm.getAge());
    	
        return "results";
    }
    

}
