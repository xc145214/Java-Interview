package com.crossoverjie.design.pattern.factory;

/**
 * Function:
 *
 * @author crossoverJie
 * Date: 19/03/2018 14:34
 * @since JDK 1.8
 */
public class Main {
    public static void main(String[] args) {
        AnimalFactory factory = new CatFactory();
        Animal animal = factory.createAnimal();
        animal.setName("猫咪");
        animal.desc();

        AnimalFactory fishfactory = new FishFactory();
        animal = fishfactory.createAnimal();
        animal.setName("小鱼");
        animal.desc();
    }
}
