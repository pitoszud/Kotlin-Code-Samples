package DesignPatterns.Iterator

import java.util.*


class BreakfastMenu(
    val breakfastMenuArr: ArrayList<Meal> = arrayListOf(
        Poridge(Milk(), 110),
        EnglishBreakfast(Milk(), 300),
        Cerals(Milk(), 90)
    )
): Iterable<Meal> {
    override fun iterator(): Iterator<Meal> {
        return breakfastMenuArr.toMutableList().listIterator()
    }
}


class DinnerMenu(
    val dinnerMenuList: List<Meal> = listOf(
        TomatoeSoup(Meat(), 80),
        FishPie(Meat(), 250),
        JacketPotatoe(Meat(), 150)
    )
): Iterable<Meal> {
    override fun iterator(): Iterator<Meal> {
        return dinnerMenuList.listIterator()
    }
}


fun main() {
    val brMenu = BreakfastMenu()
    val dinMenu = DinnerMenu()
}





interface Meal{
    fun getIngredient(): Ingredient
    fun getCalories(): Int
    fun cook(): String
}

abstract class AbstractMeal(val mealIngredient: Ingredient, val mealCalories: Int): Meal{

    override fun getCalories(): Int{
        return mealCalories
    }

    override fun getIngredient(): Ingredient{
        return mealIngredient
    }

    override fun cook(): String{
        return "Cooking ${mealIngredient.getName()}"
    }



}

abstract class AbstractBreakfast(mealIngredient: Ingredient, mealCalories: Int): AbstractMeal(mealIngredient, mealCalories)

class Poridge(mealIngredient: Ingredient, mealCalories: Int): AbstractBreakfast(mealIngredient, mealCalories)
class EnglishBreakfast(mealIngredient: Ingredient, mealCalories: Int): AbstractBreakfast(mealIngredient, mealCalories)
class Cerals(mealIngredient: Ingredient, mealCalories: Int): AbstractBreakfast(mealIngredient, mealCalories)



abstract class AbstractDinner(mealIngredient: Ingredient, mealCalories: Int): AbstractMeal(mealIngredient, mealCalories)

class TomatoeSoup(mealIngredient: Ingredient, mealCalories: Int): AbstractDinner(mealIngredient, mealCalories)
class FishPie(mealIngredient: Ingredient, mealCalories: Int): AbstractDinner(mealIngredient, mealCalories)
class JacketPotatoe(mealIngredient: Ingredient, mealCalories: Int): AbstractDinner(mealIngredient, mealCalories)





interface Ingredient{
    fun getName(): String
}


class Milk: Ingredient{
    override fun getName(): String {
        return "Milk"
    }
}


class Meat: Ingredient{
    override fun getName(): String {
        return "meat"
    }

}



