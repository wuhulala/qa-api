package com.wuhulala;


import java.util.Stack;

public class Solution {

    public static void main(String[] args) {

        String[] tokens ={"2", "1", "+", "3", "*"};
        new Solution().evalRPN(tokens);
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            try {
                stack.add(Integer.parseInt(tokens[i]));
            } catch (Exception e) {
                int b = stack.pop();
                int a = stack.pop();
                stack.add(cal(a, b, tokens[i]));
            }
        }
        System.out.println(stack.pop());
        return stack.pop();
    }

    public int cal(int a, int b, String ope) {
        switch (ope) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                return 0;
        }
    }
}