package practice;

import java.util.Stack;


class Element{

    int element;
    int lowIdx;
    int highIdx;

    public Element(int element, int lowIdx, int highIdx){
        this.element  = element;
        this.lowIdx = lowIdx;
        this.highIdx = highIdx;
    }
}
public class MaxHistogramArea {


    public  int maxRectangleArea(int area[]){

        int n =area.length;

        if (n == 0){
            return 0;
        }

        Stack<Element> stack = new Stack<>();

        int maxArea = 0;

        for (int i =0;i< n;i++){
            maxArea =Math.max(maxArea,area[i]);
            maxArea = Math.max(maxArea,maxAreaOnInsert(stack,area[i]));
            if (stack.isEmpty()){
                stack.push(new Element(area[i],0,i));
            }else{
                stack.push(new Element(area[i],stack.peek().highIdx+1, i));
            }
        }
        maxArea = Math.max(maxArea,maxAreaOnInsert(stack, Integer.MIN_VALUE));
        return maxArea;
    }


    private  int maxAreaOnInsert(Stack<Element> stack, int toInsert){
        int maxArea = 0;
        int topIndex = -1;
        if (!stack.empty()){
            topIndex = stack.peek().highIdx;
        }
        while (!stack.empty() && stack.peek().element > toInsert){
            Element element = stack.pop();
            maxArea = Math.max((topIndex - element.lowIdx + 1)*element.element, maxArea);
        }
        return maxArea;
    }



    public static void main(String[] args) {
        int []area = {7,8,9,1,2,3};
        MaxHistogramArea maxHistogramArea = new MaxHistogramArea();
        System.out.println(maxHistogramArea.maxRectangleArea(area));

    }
}
