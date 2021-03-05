package controller;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//import service.CustomerService;
import service.ICustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
@Autowired
      public ICustomerService customerService;

    @GetMapping("")
    public ModelAndView showList() {
        List<Customer> list = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("customerList", list);
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView search(@RequestParam String search) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Customer> result = customerService.findByName(search);
        modelAndView.addObject("customerList", result);
        return modelAndView;
    }

//    @PostMapping("edit/{id}")
//    public ModelAndView editCustomer(@RequestParam String name, String email, String address, @PathVariable int id) {
//        Customer customer = new Customer(id, name, email, address);
//        customerService.edit(id, customer);
//        return new ModelAndView("list", "customerList", customerService.findAll());
//    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView showFormEdit(@PathVariable int id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public ModelAndView editCustomer(@PathVariable int id,@ModelAttribute Customer customer){
        customer.setId(id);
        customerService.edit(id,customer);
        return new ModelAndView("list", "customerList", customerService.findAll());
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        customerService.delete(id);
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("c", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Customer c){
        int id = customerService.findAll().size();
        c.setId(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        customerService.create(c);
//        ModelAndView modelAndView = new ModelAndView("create", "c", new Customer());
//        modelAndView.addObject("message", "Tao moi thanh cong customer " + c.getName());
        return modelAndView;

    }
}
