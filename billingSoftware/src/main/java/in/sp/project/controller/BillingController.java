package in.sp.project.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.DocumentException;

import in.sp.project.entity.Billing_Product;
import in.sp.project.service.BillService;
import in.sp.project.service.PdfService;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BillingController {

    @Autowired
    private BillService billService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/billings")
    public String home() {
        return "home"; // Home page
    }

    @GetMapping("/generate-bill")
    public String generateBillPage() {
        return "generate-bill"; // Bill generation page
    }

    @PostMapping("/generate-bill/form")
    public String generateBillForm(@RequestParam String customerName, 
                                    @RequestParam String item,
                                    @RequestParam Double amount,
                                    @RequestParam String date,
                                    RedirectAttributes redirectAttributes) {
      
        Billing_Product bill = new Billing_Product();
        bill.setCustomerName(customerName);
        bill.setItem(item);
        bill.setAmount(amount);
        bill.setDate(date);

        billService.saveBill(bill);
        redirectAttributes.addFlashAttribute("message", "Bill generated successfully!");
        return "redirect:/bills"; 
    }

    @PostMapping("/generate-bill/api")
    public ResponseEntity<String> generateBillApi(@RequestBody Billing_Product bill) {
        billService.saveBill(bill);
        return ResponseEntity.ok("Bill generated successfully!");
    }

    @GetMapping("/bills")
    public String viewBills(Model model) {
        model.addAttribute("bills", billService.getAllBill()); 
        return "bills"; 
    }

    @GetMapping("/edit-bill/{id}")
    public String editBillPage(@PathVariable Long id, Model model) {
        Billing_Product editProduct = billService.getBillById(id);
        model.addAttribute("bill", editProduct);
        return "edit_bill";
    }

    @PostMapping("/edit-bill")
    public String editBill(@RequestParam Long id, @RequestParam String customerName, 
                           @RequestParam String item, @RequestParam Double amount, 
                           @RequestParam String date, RedirectAttributes redirectAttributes) {
        billService.updateBill(id, customerName, item, amount, date);
        redirectAttributes.addFlashAttribute("message", "Bill Updated Successfully!");
        return "redirect:/bills"; 
    }

    @GetMapping("/delete-bill/{id}")
    public String deleteById(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        billService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Bill Deleted Successfully!");
        return "redirect:/bills";
    }

    @GetMapping("/generate-bill/pdf/{id}")
    public void generateBillPdf(@PathVariable Long id, HttpServletResponse response) throws IOException, DocumentException {
        Billing_Product bill = billService.getBillById(id);
        pdfService.generatePdf(response, bill);  // Generate PDF to be downloaded
    }

    @GetMapping("/download-bill/{id}")
    public void downloadBill(@PathVariable Long id, HttpServletResponse response) {
        Billing_Product bill = billService.getBillById(id);
        try {
            pdfService.generatePdf(response, bill);  // Same as generateBillPdf for downloading
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/print-bill/{id}")
    public String printBillPage(@PathVariable Long id, Model model) {
        Billing_Product bill = billService.getBillById(id);
        model.addAttribute("bill", bill);
        return "print_bill"; 
    }
    
   
}




















