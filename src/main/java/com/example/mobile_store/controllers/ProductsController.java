package com.example.mobile_store.controllers;

import com.example.mobile_store.models.Product;
import com.example.mobile_store.repository.ProductsRepository;
import com.example.mobile_store.request.LoginRequest;
import com.example.mobile_store.services.ProductsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductsController {

    @Autowired
    private ProductsService productsService;
    @Autowired
    private ProductsRepository productsRepository;

    private final AuthenticationManager authenticationManager;

    public ProductsController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // Trang chủ
    @GetMapping("/")
    public String homeProductList(Model model) {
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "products/index";
    }
    // @GetMapping("/login")
    // public String loginPage() {
    // return "login";
    // }

    // Trang bán chạy
    @GetMapping("/banchay")
    public String banchayProductList(Model model) {
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "products/banchay";
    }

    // Trang cart
    @GetMapping("/cart")
    public String cartProductList(Model model) {
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "products/cart";
    }

    // Trang lỗi
    @GetMapping("/custom-error")
    public String errorProductList(Model model) {
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "products/error";
    }

    // Trang showcart
    @GetMapping("/api/showcart")
    public String showProductList(Model model) {
        List<Product> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "products/showcart";
    }

    @GetMapping("/admin")
    public String adminProductList(Model model) {
        List<Product> products = productsService.getVisibleProducts();
        model.addAttribute("products", products);
        return "products/admin";
    }

    @GetMapping("/api/xemthem/{id}")
    public String showProductDetails(@PathVariable("id") Long productId, Model model) {
        Product product = productsService.getProductById(productId).orElse(null);
        model.addAttribute("product", product);
        return "products/xemthem";
    }

    @GetMapping("/api/cart/{id}")
    public String cartProductDetails(@PathVariable("id") Long productId, Model model) {
        Product product = productsService.getProductById(productId).orElse(null);
        model.addAttribute("product", product);
        return "products/cart";
    }

    // Trang chỉnh sửa sản phẩm
    @GetMapping("/admin/edit/{id}")
    public String editProduct(@PathVariable("id") Long productId, Model model) {
        Product product = productsService.getProductById(productId).orElse(null);
        model.addAttribute("product", product);
        return "products/edit";
    }

    // Xóa sản phẩm
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        productsRepository.deleteById(productId);
        return "redirect:/admin";
    }
    //Lấy giá trị ID từ URL và gán cho biến productId
    // @DeleteMapping("/delete/{id}")
    // public String deleteProduct(@PathVariable("id") Long id) {
    // boolean result = productsService.hideProduct(id);
    // if (result) {
    // return "redirect:/admin?success=true";
    // }
    // return "redirect:/admin?error=true";
    // }

    // Trang đăng nhập
    @GetMapping("/login")
    public String loginProductList(Model model, @RequestParam("error") Optional<String> error) {
        if (error.isPresent()) {
            model.addAttribute("error", "Đăng nhập thất bại");
        }
        model.addAttribute("loginRequest", new LoginRequest());
        return "products/login";
    }

    @GetMapping("/register")
    public String registerProductList(Model model) {
        return "products/register";
    }

    @PostMapping("/checkLogin")
    public String checkLogin(HttpServletRequest request,
            @ModelAttribute LoginRequest loginRequest,
            Model model) {
        System.out.println(loginRequest.getUsername());
        System.out.println(loginRequest.getPassword());
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                            "{noop}" + loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            HttpSession session = request.getSession();
            session.setAttribute("username", loginRequest.getUsername());

            session.setAttribute("userDetails", auth.getPrincipal());
            session.setAttribute("userName", auth.getName());

            SecurityContextHolder.getContext().setAuthentication(auth);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            return "redirect:/";

        } catch (Exception e) {
            return "redirect:/login?error=true";
        }

    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

}
