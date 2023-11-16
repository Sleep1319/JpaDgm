package jpaDgm.jpaDgm.controller;

import jpaDgm.jpaDgm.domain.Item;
import jpaDgm.jpaDgm.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ItemService itemService;

    @GetMapping(name = "/")
    public String mainPage(Model model) {
        List<Item> proteinList = new ArrayList<>();
        List<Item> equipmentList = new ArrayList<>();
        List<Item> noneCategoryItem = new ArrayList<>();

        List<Item> itemList = itemService.findItems();
        for( Item item : itemList) {
            switch (item.getCategory()) {
                case "protein":
                    proteinList.add(item);
                    break;
                case "equipment":
                    equipmentList.add(item);
                    break;
                default:
                    noneCategoryItem.add(item);
                    break;
            }
        }
        model.addAttribute("proteinList", proteinList);
        model.addAttribute("equipmentList", equipmentList);
        return "index";
    }


}
