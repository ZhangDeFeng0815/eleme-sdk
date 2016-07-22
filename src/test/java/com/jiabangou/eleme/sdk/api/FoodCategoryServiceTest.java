package com.jiabangou.eleme.sdk.api;

import com.jiabangou.eleme.sdk.exception.ElemeErrorException;
import com.jiabangou.eleme.sdk.model.FoodCategory;
import com.jiabangou.eleme.sdk.model.FoodCategoryDetailSave;
import com.jiabangou.eleme.sdk.model.FoodCategorySave;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by freeway on 16/7/14.
 */
public class FoodCategoryServiceTest extends ServiceTest {

    @Test
    public void testAddUpdateRemove() throws ElemeErrorException {
        FoodCategoryService foodCategoryService = elemeClient.getFoodCategoryService();
        FoodCategorySave foodCategorySave = new FoodCategorySave();
        foodCategorySave.setName("今日大减价");
        foodCategorySave.setRestaurant_id(62028381L);
        foodCategorySave.setWeight(1);
        foodCategorySave.setFood_category_id(foodCategoryService.add(foodCategorySave));
        System.out.print(foodCategorySave);

        FoodCategory foodCategory = foodCategoryService.getById(foodCategorySave.getFood_category_id());
        Assert.assertEquals("今日大减价",foodCategory.getName());
        Assert.assertEquals(new Integer(1), foodCategory.getWeight());
        System.out.println(foodCategory);

        foodCategorySave.setName("今日不减价");
        foodCategoryService.update(foodCategorySave);
        foodCategory = foodCategoryService.getById(foodCategorySave.getFood_category_id());
        Assert.assertEquals("今日不减价",foodCategory.getName());
        System.out.println(foodCategory);
//        foodCategoryService.remove(foodCategorySave.getFood_category_id());
//        foodCategory = foodCategoryService.getById(foodCategorySave.getFood_category_id());
//        Assert.assertNull(foodCategory);
    }

    @Test
    public void testGetsByRestaurantId() throws ElemeErrorException {
        FoodCategoryService foodCategoryService = elemeClient.getFoodCategoryService();

        System.out.println(foodCategoryService.getsByRestaurantId(804622L));
        foodCategoryService.removeAll(804622L);
        System.out.println(foodCategoryService.getsByRestaurantId(804622L));
    }

    @Test
    public void testAddAll() throws ElemeErrorException {
        List<FoodCategoryDetailSave> cfoods = new ArrayList<>();
        List<FoodCategoryDetailSave.Food> foods = new ArrayList<>();
        FoodCategoryDetailSave foodCategoryFoods = new FoodCategoryDetailSave();
        foodCategoryFoods.setRestaurant_id(804622L);
        foodCategoryFoods.setName("精品商务套餐");
        foodCategoryFoods.setWeight(100);
        FoodCategoryDetailSave.Food food = new FoodCategoryDetailSave.Food();
        food.setDescription("香辣鸭丁超值套餐");
        food.setName("香辣鸭丁超值套餐");
        food.setImage_hash(elemeClient.getImageService().uploadByUrl("http://img.waimai.bdimg.com/pb/9e49d09f1f874d6578c35f745319896f58"));
        food.setPrice(9.9f);
        food.setMax_stock(Integer.MAX_VALUE);
        food.setStock(100);
        food.setPacking_fee(0.5f);
        foods.add(food);

        food = new FoodCategoryDetailSave.Food();
        food.setDescription("京葱爆鸭心套餐");
        food.setName("京葱爆鸭心套餐");
        food.setPrice(29.9f);
        food.setImage_hash(elemeClient.getImageService().uploadByUrl("http://img.waimai.bdimg.com/pb/ce336f3aa87601fa7b3bab66ba0985aca6"));

        food.setMax_stock(Integer.MAX_VALUE);
        food.setStock(100);
        foods.add(food);
        cfoods.add(foodCategoryFoods);
        foodCategoryFoods.setFoods(foods);


        foods = new ArrayList<>();
        foodCategoryFoods = new FoodCategoryDetailSave();
        foodCategoryFoods.setRestaurant_id(804622L);
        foodCategoryFoods.setName("点心");
        foodCategoryFoods.setWeight(99);

        food = new FoodCategoryDetailSave.Food();
        food.setDescription("叉烧酥");
        food.setName("叉烧酥");
        food.setImage_hash(elemeClient.getImageService().uploadByUrl("http://img.waimai.bdimg.com/pb/baf131973afc47838c457a3b809538a366"));
        food.setPrice(32f);
        food.setMax_stock(Integer.MAX_VALUE);
        food.setStock(100);
        food.setPacking_fee(0.0f);
        foods.add(food);

        food = new FoodCategoryDetailSave.Food();
        food.setDescription("小鸭酥");
        food.setName("小鸭酥");
        food.setPrice(24f);
        food.setImage_hash(elemeClient.getImageService().uploadByUrl("http://img.waimai.bdimg.com/pb/67f72dd23cf68f1f932902eb684b012967"));

        food.setMax_stock(Integer.MAX_VALUE);
        food.setStock(100);
        foods.add(food);

        foodCategoryFoods.setFoods(foods);
        cfoods.add(foodCategoryFoods);


        foodCategoryFoods = new FoodCategoryDetailSave();
        foodCategoryFoods.setRestaurant_id(804622L);
        foodCategoryFoods.setName("主食");
        foodCategoryFoods.setWeight(98);

        foods = new ArrayList<>();
        food = new FoodCategoryDetailSave.Food();
        food.setDescription("香米饭");
        food.setName("香米饭");
        food.setImage_hash(elemeClient.getImageService().uploadByUrl("http://img.waimai.bdimg.com/pb/6ec96ad5cd7e8d9c76ae6c72cef66741e2"));
        food.setPrice(2f);
        food.setMax_stock(Integer.MAX_VALUE);
        food.setStock(100);
        food.setPacking_fee(0.0f);
        foods.add(food);
        foodCategoryFoods.setFoods(foods);
        cfoods.add(foodCategoryFoods);

        System.out.println(elemeClient.getFoodCategoryService().addCategoryAndFoods(cfoods));
    }
}
