package pers.yuzhyn.azylee.study.designpattern.structural.decorator;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}