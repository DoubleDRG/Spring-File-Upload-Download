package david.fileupload.controller;

import david.fileupload.controller.form.ItemForm;
import david.fileupload.domain.Item;
import david.fileupload.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ItemController
{
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String itemForm()
    {
        return "item-form";
    }

    @PostMapping("items/new")
    public String itemSave(@ModelAttribute ItemForm form) throws IOException
    {
        Long itemId = itemService.save(form).getId();
        return "redirect:/items/" + itemId;
    }

    @GetMapping("items/{itemId}")
    public String itemDetail(@PathVariable Long itemId, Model model)
    {
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "item-view";
    }
}
