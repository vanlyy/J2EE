package com.how2java.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
import com.how2java.pojo.Category;
import com.how2java.service.CategoryService;
import com.how2java.util.Page;
 
// 告诉spring mvc这是一个控制器类
@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
 
    @RequestMapping(value="/categories",method=RequestMethod.GET)
    public ModelAndView listCategory(Page page){
     
        ModelAndView mav = new ModelAndView();
        List<Category> cs= categoryService.list(page);
        int total = categoryService.total();
         
        page.caculateLast(total);
         
        // 放入转发参数
        mav.addObject("cs", cs);
        // 放入jsp路径
        mav.setViewName("listCategory");
        return mav;
    }
     
    @RequestMapping(value="/categories",method=RequestMethod.POST)
    public ModelAndView addCategory(Category category){
        System.out.println("category.getName():"+category.getName());
        categoryService.add(category);
        ModelAndView mav = new ModelAndView("redirect:/categories");
        return mav;
    }  
     
    @RequestMapping(value="/categories/{id}",method=RequestMethod.DELETE)
    public ModelAndView deleteCategory(Category category){
        categoryService.delete(category);
        ModelAndView mav = new ModelAndView("redirect:/categories");
        return mav;
    }  
    @RequestMapping(value="/categories/{id}",method=RequestMethod.GET)
    public ModelAndView editCategory(Category category){
        Category c= categoryService.get(category.getId());
        ModelAndView mav = new ModelAndView("editCategory");
        mav.addObject("c", c);
        return mav;
    }  
    @RequestMapping(value="/categories/{id}",method=RequestMethod.PUT)
    public ModelAndView updateCategory(Category category){
        categoryService.update(category);
        ModelAndView mav = new ModelAndView("redirect:/categories");
        return mav;
    }  
 
}