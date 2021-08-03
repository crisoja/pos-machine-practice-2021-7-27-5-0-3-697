package pos.machine;

import java.util.ArrayList;
import java.util.List;

import static pos.machine.ItemDataLoader.loadAllItemInfos;

public class PosMachine {


    public String generateFinalReceipt(List<String> barcodeList) {
       List<Item> item = barcodesToItems(barcodeList);
        return generateReceipt(item);

    }

    private List<Item> barcodesToItems(List<String> barcodeList) {
        List<Item> itemList  = new ArrayList<>();
        Item coke = new Item();
        Item sprite = new Item();
        Item battery = new Item();
        int cokeTotal = 0;
        int spriteTotal = 0;
        int battTotal = 0;
        int cokeCount = 0;
        int spriteCount = 0;
        int battCount = 0;

        List<ItemInfo> itemInfos = loadAllItemInfos();

        for (String barcode: barcodeList){
            if("ITEM000000".equals(barcode)){

                coke.setName(itemInfos.get(0).getName());
                coke.setUnitPrice(itemInfos.get(0).getPrice());
                cokeTotal += itemInfos.get(0).getPrice();
                coke.setSubTotal(cokeTotal);
                cokeCount++;
                coke.setQuantity(cokeCount);

            }
            else if("ITEM000001".equals(barcode)){

                sprite.setName(itemInfos.get(1).getName());
                sprite.setUnitPrice(itemInfos.get(1).getPrice());
                spriteTotal += itemInfos.get(1).getPrice();
                sprite.setSubTotal(spriteTotal);
                spriteCount++;
                sprite.setQuantity(spriteCount);

            }
            else if("ITEM000004".equals(barcode)){
                battery.setName(itemInfos.get(2).getName());
                battery.setUnitPrice(itemInfos.get(2).getPrice());
                battTotal += itemInfos.get(2).getPrice();
                battery.setSubTotal(battTotal);
                battCount++;
                battery.setQuantity(battCount);

            }
            else {
                System.out.println("Please verify barcode!");
            }
        }
        itemList.add(coke);
        itemList.add(sprite);
        itemList.add(battery);

        return  itemList;

}

    public int computeTotalPrice(List<Item> itemList){
        int totalPrice = 0;

      for(Item item : itemList){
            totalPrice += item.getSubTotal();
        }

        return totalPrice;
    }

    public String generateReceipt(List<Item> itemList){
        String receipt = "";
        for (Item item : itemList) {
            receipt += combinedItem(item);
        }
        return "***<store earning no money>Receipt***\n" +
                receipt + "----------------------\n" +
                "Total: " + computeTotalPrice(itemList) + " (yuan)\n" +
                "**********************";

    }

    public String combinedItem(Item item){

        return  "Name: " + item.getName() + ", Quantity: " + item.getQuantity() + ", Unit price: " +
                item.getUnitPrice() + " (yuan), Subtotal: " + item.getSubTotal() + " (yuan)\n";
    }
}
